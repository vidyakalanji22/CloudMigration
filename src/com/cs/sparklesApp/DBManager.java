package com.cs.sparklesApp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.cs.sparklesApp.model.Customer;
import com.cs.sparklesApp.model.Employee;
import com.cs.sparklesApp.model.Office;
import com.cs.sparklesApp.model.Order;
import com.cs.sparklesApp.model.OrderDetails;
import com.cs.sparklesApp.model.Payment;
import com.cs.sparklesApp.model.Product;
import com.cs.sparklesApp.model.ProductLine;

public class DBManager {
	MigrateManager mgr = new MigrateManager();

	/**
	 * Get all the customers from db
	 * @return List<Customer>
	 */
	public List<Customer> getCustomers(){
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Customer customer = null;
		List<Customer> customers = new ArrayList<Customer>();
		try{
			connection = mgr.connectDBInstance();
			preparedStatement = connection.prepareStatement("select * from customers");
			resultSet = preparedStatement.executeQuery();

			while(resultSet.next()){
				customer = new Customer(resultSet.getInt("customerNumber"), resultSet.getString("customerName"), resultSet.getString("contactLastName"), resultSet.getString("contactFirstName"), resultSet.getString("phone"), resultSet.getString("addressLine1"), resultSet.getString("addressLine2"), resultSet.getString("city"), resultSet.getString("state"), resultSet.getString("postalCode"), resultSet.getString("country"), new Employee(resultSet.getInt("salesRepEmployeeNumber")));
				customers.add(customer);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return customers;
	}

	/**
	 * Getting all the eempolyees
	 * @return List<Employees>
	 */
	public List<Employee> getEmployees(){
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Employee employee = null;
		List<Employee> employees = new ArrayList<Employee>();
		try{
			connection = mgr.connectDBInstance();
			preparedStatement = connection.prepareStatement("select * from employees");
			resultSet = preparedStatement.executeQuery();

			while(resultSet.next()){
				employee = new Employee(resultSet.getInt("employeeNumber"), resultSet.getString("lastName"), resultSet.getString("firstName"), resultSet.getString("extension"), resultSet.getString("email"), resultSet.getString("jobCode"),new Office(resultSet.getString("officeCode")), new Employee(resultSet.getInt("employeeNumber")));
				employees.add(employee);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return employees;
	}
/**
 * Getting all the office table details from RDS database
 * @return List<Office>
 */
	public List<Office> getOffices(){
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Office office = null;
		List<Office> offices = new ArrayList<Office>();
		try{
			connection = mgr.connectDBInstance();
			preparedStatement = connection.prepareStatement("select * from offices");
			resultSet = preparedStatement.executeQuery();

			while(resultSet.next()){
				office = new Office(resultSet.getString("officeCode"), resultSet.getString("city"), resultSet.getString("phone"), resultSet.getString("addressLine1"), resultSet.getString("addressLine2"), resultSet.getString("state"), resultSet.getString("country"), resultSet.getString("postalCode"), resultSet.getString("territory"));
				offices.add(office);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return offices;
	}

	/**
	 * Getting the data from orders tables
	 * @return List<Order>
	 */
	public List<Order> getOrders(){
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Order order = null;
		List<Order> orders = new ArrayList<Order>();
		try{
			connection = mgr.connectDBInstance();
			preparedStatement = connection.prepareStatement("select * from orders");
			resultSet = preparedStatement.executeQuery();

			while(resultSet.next()){
				order = new Order(resultSet.getInt("orderNumber"), resultSet.getDate("orderDate"), resultSet.getDate("requiredDate"), resultSet.getDate("shippedDate"), resultSet.getString("status"), resultSet.getString("comment"), new Customer(resultSet.getInt("customerNumber")));
				orders.add(order);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return orders;
	}
	
	/**
	 * getting the Order details from order details table
	 * @return List<OrderDetails>
	 */

	public List<OrderDetails> getOrderDetails(){
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		OrderDetails orderDetails = null;
		List<OrderDetails> orders = new ArrayList<OrderDetails>();
		try{
			connection = mgr.connectDBInstance();
			preparedStatement = connection.prepareStatement("select * from orderdetails");
			resultSet = preparedStatement.executeQuery();

			while(resultSet.next()){
				orderDetails = new OrderDetails(resultSet.getInt("quantityOrdered"), resultSet.getDouble("priceEach"), resultSet.getShort("orderLineNumber"), new Order(resultSet.getInt("orderNumber")) , new Product(resultSet.getString("productCode"))); 
				orders.add(orderDetails);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return orders;
	}

	/**
	 * getting the payment table data
	 * @return List<Payment>
	 */
	public List<Payment> getPayments(){
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Payment payment = null;
		List<Payment> payments = new ArrayList<Payment>();
		try{
			connection = mgr.connectDBInstance();
			preparedStatement = connection.prepareStatement("select * from payments");
			resultSet = preparedStatement.executeQuery();

			while(resultSet.next()){
				payment = new Payment(new Customer(resultSet.getInt("customerNumber")), resultSet.getString("checkNumber"), resultSet.getDate("paymentDate"), resultSet.getDouble("amount"));
				payments.add(payment);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return payments;
	}

	/**
	 * Getting the data from Products table
	 * @return List<Product>
	 */
	public List<Product> getProducts(){
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Product product = null;
		List<Product> products = new ArrayList<Product>();
		try{
			connection = mgr.connectDBInstance();
			preparedStatement = connection.prepareStatement("select * from products");
			resultSet = preparedStatement.executeQuery();

			while(resultSet.next()){
				product = new Product(resultSet.getString("productCode"), resultSet.getString("productName"), new ProductLine(resultSet.getString("productLine")), resultSet.getString("productScale"), resultSet.getString("productVendor"), resultSet.getString("productDescription"), resultSet.getShort("quantityInStock"), resultSet.getDouble("buyPrice"), resultSet.getDouble("msrp"));
				products.add(product);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return products;
	}

	/**
	 * getting the data from productline table
	 * @return List<ProductLine> 
	 */
	public List<ProductLine> getProductLines(){
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ProductLine productLine = null;
		List<ProductLine> productLines = new ArrayList<ProductLine>();
		try{
			connection = mgr.connectDBInstance();
			preparedStatement = connection.prepareStatement("select * from productlines");
			resultSet = preparedStatement.executeQuery();

			while(resultSet.next()){
				productLine = new ProductLine(resultSet.getString("productLine"), resultSet.getString("textDescription"), resultSet.getString("htmlDescription"), resultSet.getBlob("image"));
				productLines.add(productLine);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return productLines;
	}

	public static void main(String args[]) {
		DBManager dbMgr = new DBManager();
		List<ProductLine> list = dbMgr.getProductLines();
		System.out.println("list is "+list);
	}

}
