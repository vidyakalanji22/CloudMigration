package com.cs.sparklesApp;

import java.io.File;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.PutObjectRequest;

public class S3Util {

	public static final String S3_BUCKET_NAME = "cmpesparkles";
	public static final AmazonS3 s3;
	    
	static {
		
		 s3 = new AmazonS3Client();
		 createBucket();
	 }
    	
	/**
	 * Creating the bucket to store our application's war file
	 */
	public static void createBucket(){
		if(!s3.doesBucketExist(S3_BUCKET_NAME))
		  s3.createBucket(S3_BUCKET_NAME);
	}
  
	/**
	 * uploads our app to created bucket in S3
	 * @param file
	 * @return
	 */
   	public boolean uploadApp(File file){
   		System.out.println("in uploadApp");
   		String name = file.getName();
   		int pos = name.lastIndexOf(".");
   		if (pos > 0) {
   		    name = name.substring(0, pos);
   		}
   		//String fileName = S3_BUCKET_NAME + "001"+name;
   		boolean uploadedSuccess = false;
    		try{
    	        PutObjectRequest putObjectRequest = new PutObjectRequest(S3_BUCKET_NAME, name, file); // object key is the projectId
    	        s3.putObject(putObjectRequest);
    	        uploadedSuccess= true;
    	        System.out.println("Successfully uploaded the object into bucket : " + S3_BUCKET_NAME);
    		}catch(Exception exp){
    			System.out.println("An exception occurred while uploading the file into S3 " + exp);
    		}
    	return uploadedSuccess;
    }
}
