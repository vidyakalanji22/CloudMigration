package com.cs.sparklesApp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import com.amazonaws.util.json.JSONObject;
import com.cs.sparklesApp.model.Customer;
import com.cs.sparklesApp.model.RestCustomer;

@Path("/app")
public class SparklesEndPoint {
	
	/**
	 * End point to create the database
	 * @param fileDet
	 * @return
	 */
	@POST
	@Path("/createDB")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response createDB(@FormDataParam("data") String fileDet) {
		MigrateManager mgr = new MigrateManager();
		JSONObject data = null;
		try {
			data = new JSONObject(fileDet);
			String dbName = data.getString("dbName");
			String dbUsername = data.getString("dbUsername");
			String dbPassword = data.getString("dbPassword");
			mgr.createDB(dbName, dbUsername, dbPassword);
		} catch (Exception exp) {
			System.out.println("An error occurred while uploading the file : "	+ exp);
		}
		return Response.status(Status.OK).build();
	}
	
	/**
	 * File upload
	 * @param uploadedInputStream
	 * @param fileDetail
	 * @return
	 */
	@POST
	@Path("/fileUpload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadApp(
			@FormDataParam("file") InputStream uploadedInputStream,
			@FormDataParam("file") FormDataContentDisposition fileDetail) {

		File temp;
		MigrateManager mgr = new MigrateManager();
		
		try {
			
			temp = File.createTempFile("temp-file-name", ".tmp"); //Create temp file location for file upload
			String absolutePath = temp.getAbsolutePath();
			String tempFilePath = absolutePath.substring(0, absolutePath.lastIndexOf(File.separator));
			String uploadedFileLocation = tempFilePath + "/"+fileDetail.getFileName();
			
			writeToFile(uploadedInputStream, uploadedFileLocation);
			
			File file = new File(uploadedFileLocation);
			mgr.uploadFile(file);
			mgr.migrateDB(file);
		} catch (Exception exp) {
			System.out.println("An error occurred while uploading the file : "	+ exp);
		}finally{
			try {
				uploadedInputStream.close();
			} catch (IOException e) {
				System.out.println("Error occurred while closing stream");
			}
		}
		return Response.status(Status.OK).build();
	}
	
	/**
	 * writing to the file
	 * @param uploadedInputStream
	 * @param uploadedFileLocation
	 * @throws IOException
	 */
	
	private void writeToFile(InputStream uploadedInputStream,
			String uploadedFileLocation) throws IOException {

		try {
			OutputStream out = new FileOutputStream(new File(
					uploadedFileLocation));
			int read = 0;
			byte[] bytes = new byte[1024];

			out = new FileOutputStream(new File(uploadedFileLocation));
			while ((read = uploadedInputStream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			out.flush();
			out.close();
		} catch (IOException e) {
			throw e;
		}

	}
	
	/**
	 * Listing all the customers
	 * @return
	 */
	
	@GET
	@Path("/customers")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCustomers() {
		DBManager dbManager = new DBManager();
		List<Customer> customers = dbManager.getCustomers();
		List<RestCustomer> restCustomers = new ArrayList<RestCustomer>();
		for (Customer customer : customers) {
			RestCustomer restCustomer = new RestCustomer(customer.getCustomerNumber(), customer.getCustomerName(), customer.getContactLastName(), customer.getContactFirstName(), customer.getPhone(), customer.getAddressLine1(), customer.getAddressLine2(), customer.getCity(), customer.getState(), customer.getPostalCode(), customer.getCountry());
			restCustomers.add(restCustomer);
		}
		System.out.println(restCustomers.size());
		GenericEntity<List<RestCustomer>> entity = new GenericEntity<List<RestCustomer>>(
				restCustomers) {
		};
		return Response.status(Status.OK).entity(entity).build();
	}

}
