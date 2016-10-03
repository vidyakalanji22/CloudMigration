package com.cs.sparklesApp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.ibatis.jdbc.ScriptRunner;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2Client;
import com.amazonaws.services.ec2.model.DescribeSecurityGroupsResult;
import com.amazonaws.services.ec2.model.SecurityGroup;
import com.amazonaws.services.rds.AmazonRDS;
import com.amazonaws.services.rds.AmazonRDSClient;
import com.amazonaws.services.rds.model.CreateDBInstanceRequest;
import com.amazonaws.services.rds.model.DBInstance;
import com.amazonaws.services.rds.model.DescribeDBInstancesRequest;
import com.amazonaws.services.rds.model.DescribeDBInstancesResult;
import com.amazonaws.services.rds.model.Endpoint;

public class MigrateManager {
	private Connection connection;
	private AWSCredentials awsCredentials;
	private Properties dbProperties = PropertyLoader.getDBProperties();
	private AmazonRDS amazonRDSClient;
	private Region region;

	/**
	 * Constructor 
	 */
	MigrateManager(){ 
		region = Region.getRegion(Regions.US_WEST_2);
		awsCredentials = new BasicAWSCredentials(System.getProperty("accessKey"), System.getProperty("secretKey"));
		amazonRDSClient = new AmazonRDSClient(awsCredentials);
		amazonRDSClient.setRegion(region);
	}


	/**
	 * This method uploads the file
	 * @param file
	 */
	public void uploadFile(File file){
		S3Util util = new S3Util();
		Boolean bool = util.uploadApp(file);
		System.out.println(bool);
	}

	/**
	 * creates databse in RDS
	 * @param dbName
	 * @param dbUserName
	 * @param dbPassword
	 */
	public void createDB(String dbName, String dbUserName, String dbPassword){
		try {
			AmazonEC2 amazonEC2 = new AmazonEC2Client(awsCredentials);
			amazonEC2.setRegion(region);

			DescribeSecurityGroupsResult describeSecurityGroupsResult = amazonEC2.describeSecurityGroups();
			List<SecurityGroup> securityGroups =describeSecurityGroupsResult.getSecurityGroups();

			SecurityGroup web106ec2 = null;
			for(SecurityGroup securityGroup : securityGroups){
				if(securityGroup.getGroupName().equals("d-92673a123d_controllers")){
					web106ec2 = securityGroup;
					break;
				}
			}

			if(web106ec2 != null) {
				List<String> ids = new ArrayList<String>();
				ids.add(web106ec2.getGroupId());
				CreateDBInstanceRequest createDBInstanceRequest = new CreateDBInstanceRequest();
				createDBInstanceRequest
				.withDBName(dbName)
				.withAllocatedStorage(5)
				.withDBInstanceClass("db.t2.micro")
				.withEngine("mysql")
				.withMasterUsername(dbUserName)
				.withMasterUserPassword(dbPassword)
				.withDBInstanceIdentifier(dbName)
				.withMultiAZ(false)
				.withVpcSecurityGroupIds(ids);

				DBInstance dbInstance = amazonRDSClient.createDBInstance(createDBInstanceRequest);
				System.out.println(dbInstance.getDBInstanceStatus());
			}
			dbProperties.setProperty("dbName", dbName);
			dbProperties.setProperty("dbUsername", dbUserName);
			dbProperties.setProperty("dnPassword", dbPassword);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * Connecting to RDS database
	 * @return
	 */

	public Connection connectDBInstance(){

		String dbName = dbProperties.getProperty("dbName");
		String dbUsername = dbProperties.getProperty("dbUsername");
		String dbPassword = dbProperties.getProperty("dbPassword");

		DescribeDBInstancesRequest dbRequest = new DescribeDBInstancesRequest();
		DescribeDBInstancesResult dbResult = amazonRDSClient.describeDBInstances(dbRequest);
		List<DBInstance> dbInstances = dbResult.getDBInstances();
		for (DBInstance dbInstance : dbInstances) {
			String dbInstanceName = dbInstance.getDBName();
			if(dbInstanceName.equals(dbName)){
				Endpoint endpoint = dbInstance.getEndpoint();
				String url = "jdbc:mysql://"+endpoint.getAddress()+":3306/";
				//String driver = "com.mysql.cj.jdbc.Driver";
				String driver = "com.mysql.jdbc.Driver";
				try {
					Class.forName(driver);
					connection = DriverManager.getConnection(url+"classicmodels", dbUsername, dbPassword);

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return connection;
	}
	
	public Connection connectNewDBInstance(){

		String dbName = dbProperties.getProperty("dbName");
		String dbUsername = dbProperties.getProperty("dbUsername");
		String dbPassword = dbProperties.getProperty("dbPassword");

		DescribeDBInstancesRequest dbRequest = new DescribeDBInstancesRequest();
		DescribeDBInstancesResult dbResult = amazonRDSClient.describeDBInstances(dbRequest);
		List<DBInstance> dbInstances = dbResult.getDBInstances();
		for (DBInstance dbInstance : dbInstances) {
			String dbInstanceName = dbInstance.getDBName();
			if(dbInstanceName.equals(dbName)){
				Endpoint endpoint = dbInstance.getEndpoint();
				String url = "jdbc:mysql://"+endpoint.getAddress()+":3306/";
				//String driver = "com.mysql.cj.jdbc.Driver";
				String driver = "com.mysql.jdbc.Driver";
				try {
					Class.forName(driver);
					connection = DriverManager.getConnection(url+dbName, dbUsername, dbPassword);

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return connection;
	}

	/**
	 * Migrating the database to RDS
	 * @param file
	 */
	public void migrateDB(File file){
		connection = connectNewDBInstance();
		String path = file.getAbsolutePath();
		try {
			// Initialize object for ScripRunner
			ScriptRunner sr = new ScriptRunner(connection);

			// Give the input file to Reader
			Reader reader = new BufferedReader(
					new FileReader(path));

			// Exctute script
			sr.runScript(reader);
			connection.commit();

		} catch (Exception e) {
			System.err.println("Failed to Execute" + path
					+ " The error is " + e.getMessage());
		}finally{
			try {
				if(connection!=null)
					connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
