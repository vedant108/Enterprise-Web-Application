import java.sql.*;
import java.util.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.sql.Date;
import java.lang.Math; 
import java.util.UUID;
                	
public class MySqlDataStoreUtilities
{
static Connection conn = null;
static String message;
public static String getConnection()
{

	try
	{
	Class.forName("com.mysql.jdbc.Driver").newInstance();
	conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/exampledatabase","root","root");							
	message="Successfull";
	return message;
	}
	catch(SQLException e)
	{
		message="unsuccessful";
		     return message;
	}
	catch(Exception e)
	{
		message=e.getMessage();
		return message;
	}
}


public static HashMap<String,Com> getDoctors()
{	
	HashMap<String,Com> hm=new HashMap<String,Com>();
	try 
	{
		getConnection();
		
		String selectTablet="select * from  Productdetails where ProductType=?";
		PreparedStatement pst = conn.prepareStatement(selectTablet);
		pst.setString(1,"Doctor");
		ResultSet rs = pst.executeQuery();
		
		while(rs.next())
		{	Com sound = new Com(rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("productCategory"),rs.getString("productType"),
						rs.getString("address"), rs.getDouble("zipcode"),rs.getDouble("rating"), rs.getDouble("Availability"));
				hm.put(rs.getString("Id"), sound);
				sound.setId(rs.getString("Id"));

		}
	}
	catch(Exception e)
	{
	}
	return hm;			
}

public static HashMap<String,Com> getHospitals()
{	
	HashMap<String,Com> hm=new HashMap<String,Com>();
	try 
	{
		getConnection();
		
		String selectTablet="select * from  Productdetails where ProductType=?";
		PreparedStatement pst = conn.prepareStatement(selectTablet);
		pst.setString(1,"Hospital");
		ResultSet rs = pst.executeQuery();
	
		while(rs.next())
		{	Com sound = new Com(rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("productCategory"),rs.getString("productType"),
						rs.getString("address"), rs.getDouble("zipcode"),rs.getDouble("rating"),rs.getDouble("Availability"));
				hm.put(rs.getString("Id"), sound);
				sound.setId(rs.getString("Id"));

		}
	}
	catch(Exception e)
	{
	}
	return hm;			
}

public static HashMap<String,Com> getInsurance()
{	
	HashMap<String,Com> hm=new HashMap<String,Com>();
	try 
	{
		getConnection();
		
		String selectTablet="select * from  Productdetails where ProductType=?";
		PreparedStatement pst = conn.prepareStatement(selectTablet);
		pst.setString(1,"Insurance");
		ResultSet rs = pst.executeQuery();
	
		while(rs.next())
		{	Com sound = new Com(rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("productCategory"),rs.getString("productType"),
						rs.getString("address"), rs.getDouble("zipcode"),rs.getDouble("rating"),rs.getDouble("Availability"));
				hm.put(rs.getString("Id"), sound);
				sound.setId(rs.getString("Id"));

		}
	}
	catch(Exception e)
	{
	}
	return hm;			
}

public static HashMap<String,Com> getClubs()
{	
	HashMap<String,Com> hm=new HashMap<String,Com>();
	try 
	{
		getConnection();
		
		String selectTablet="select * from  Productdetails where ProductType=?";
		PreparedStatement pst = conn.prepareStatement(selectTablet);
		pst.setString(1,"Healthclub");
		ResultSet rs = pst.executeQuery();
	
		while(rs.next())
		{	Com sound = new Com(rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("productCategory"),rs.getString("productType"),
						rs.getString("address"), rs.getDouble("zipcode"),rs.getDouble("rating"),rs.getDouble("Availability"));
				hm.put(rs.getString("Id"), sound);
				sound.setId(rs.getString("Id"));

		}
	}
	catch(Exception e)
	{
	}
	return hm;			
}

public static String addproducts(String productId,String productName,double productPrice,String productImage, String productCategory, String productType, String address, double zipcode, double rating,double stock)
{
	String msg = "Product is added successfully";
	try{
		
		getConnection();
		String addProductQurey = "INSERT INTO  Productdetails(Id,productName,productPrice,productImage,productCategory,ProductType,address, zipcode,rating,Availability)" +
		"VALUES (?,?,?,?,?,?,?,?,?,?);";
		   	        			
			PreparedStatement pst = conn.prepareStatement(addProductQurey);
			pst.setString(1,productId);
			pst.setString(2,productName);
			pst.setDouble(3,productPrice);
			pst.setString(4,productImage);
			pst.setString(5,productCategory);
			pst.setString(6,productType);
			pst.setString(7,address);
			pst.setDouble(8,zipcode);
			pst.setDouble(9,rating);
			pst.setDouble(10,stock);
			
			pst.executeUpdate();	
		
	}
	catch(Exception e)
	{
		msg = "Erro while adding the product";
		e.printStackTrace();
		
	}
	return msg;
}
public static String updateproducts(String productId,String productName,double productPrice,String productImage, String productCategory, String productType, String address, double zipcode, double rating,double stock)
{ 
    String msg = "Product is updated successfully";
	try{
		
		getConnection();
		String updateProductQurey = "UPDATE Productdetails SET productName=?,productPrice=?,productImage=?,productCategory=?,productType=?, address=?, zipcode=?, rating=?, Availability=? where Id =?;" ;	
		
		   
				        			
			PreparedStatement pst = conn.prepareStatement(updateProductQurey);
			
			pst.setString(1,productName);
			pst.setDouble(2,productPrice);
			pst.setString(3,productImage);
			pst.setString(4,productCategory);
			pst.setString(5,productType);
			pst.setString(6,address);
			pst.setDouble(7,zipcode);
			pst.setDouble(8,rating);
			pst.setDouble(9,stock);
			pst.setString(10,productId);
			
			pst.executeUpdate();
		
	}
	catch(Exception e)
	{
		msg = "Product cannot be updated";
		e.printStackTrace();
		
	}
 return msg;	
}

public static String deleteproducts(String productId)
{   String msg = "Product is deleted successfully";
	try
	{
		
		getConnection();
		String deleteproductsQuery ="Delete from Productdetails where Id=?";
		PreparedStatement pst = conn.prepareStatement(deleteproductsQuery);
		pst.setString(1,productId);
		
		pst.executeUpdate();
	}
	catch(Exception e)
	{
			msg = "Proudct cannot be deleted";
	}
	return msg;
}

public static void insertUser(String username,String password,String repassword,String usertype, String name, String address, String pincode)
{
	try
	{	

		getConnection();
		String insertIntoCustomerRegisterQuery = "INSERT INTO Registration(username,password,repassword,usertype,Name,userAddress,Pincode) "
		+ "VALUES (?,?,?,?,?,?,?);";	
				
		PreparedStatement pst = conn.prepareStatement(insertIntoCustomerRegisterQuery);
		pst.setString(1,username);
		pst.setString(2,password);
		pst.setString(3,repassword);
		pst.setString(4,usertype);
		pst.setString(5,name);
		pst.setString(6,address);
		pst.setString(7,pincode);
		pst.execute();
	}
	catch(Exception e)
	{
	
	}	
}

public static HashMap<String,User> selectUser()
{	
	HashMap<String,User> hm=new HashMap<String,User>();
	try 
	{
		getConnection();
		Statement stmt=conn.createStatement();
		String selectCustomerQuery="select * from  Registration";
		ResultSet rs = stmt.executeQuery(selectCustomerQuery);
		while(rs.next())
		{	User user = new User(rs.getString("username"),rs.getString("password"),rs.getString("usertype"));
				hm.put(rs.getString("username"), user);
		}
	}
	catch(Exception e)
	{
	}
	return hm;			
}

public static HashMap<Integer, ArrayList<OrderPayment>> selectOrder()
{	

	HashMap<Integer, ArrayList<OrderPayment>> orderPayments=new HashMap<Integer, ArrayList<OrderPayment>>();
		
	try
	{					

		getConnection();
        //select the table 
		String selectOrderQuery ="select * from customerorders";			
		PreparedStatement pst = conn.prepareStatement(selectOrderQuery);
		ResultSet rs = pst.executeQuery();	
		ArrayList<OrderPayment> orderList=new ArrayList<OrderPayment>();
		while(rs.next())
		{
			if(!orderPayments.containsKey(rs.getInt("OrderId")))
			{	
				ArrayList<OrderPayment> arr = new ArrayList<OrderPayment>();
				orderPayments.put(rs.getInt("orderId"), arr);
			}
			ArrayList<OrderPayment> listOrderPayment = orderPayments.get(rs.getInt("OrderId"));		
			// System.out.println("data is"+rs.getInt("OrderId")+orderPayments.get(rs.getInt("OrderId")));

			//add to orderpayment hashmap
			OrderPayment order= new OrderPayment(rs.getInt("OrderId"),rs.getString("UserID"),rs.getString("productId"),rs.getDouble("orderPrice"),rs.getString("CustomerAddress"),rs.getString("creditCardNo"),rs.getDate("AppointmentDate").toLocalDate(),rs.getDate("orderDate").toLocalDate());
			listOrderPayment.add(order);
					
		}
				
					
	}
	catch(Exception e)
	{
		System.out.println(e);
	}
	return orderPayments;
}

public static void insertOrder(int orderId,String userName, String customerName, String orderName,double orderPrice,String userAddress,String creditCardNo, LocalDate deliveryDate, String productID, double totalSales, String category)
{
	try
	{
		getConnection();
		String insertIntoCustomerOrderQuery = "INSERT INTO customerOrders(OrderId,UserID,productId,category,CustomerName,OrderPrice,CustomerAddress,creditCardNo,orderDate,AppointmentDate,totalsales) "
		+ "VALUES (?,?,?,?,?,?,?,?,?,?,?);";
		
		LocalDate orderDate = LocalDate.now();
		int quantity = 1;
		Date OrderDate = Date.valueOf(orderDate);
		Date date = Date.valueOf(deliveryDate);
		PreparedStatement pst = conn.prepareStatement(insertIntoCustomerOrderQuery);
		//set the parameter for each column and execute the prepared statement
		pst.setInt(1,orderId);
		pst.setString(2,userName);
		pst.setString(3, productID);
		pst.setString(4,category);
		pst.setString(5,customerName);
		pst.setDouble(6,orderPrice);
		pst.setString(7, userAddress);
		pst.setString(8, creditCardNo);
		pst.setDate(9, OrderDate);
		pst.setDate(10, date);
		pst.setDouble(11, totalSales);
		

		pst.execute();
		insertTransaction(orderId,userName,customerName,orderPrice,userAddress,creditCardNo,deliveryDate,category,productID,totalSales);
	}
	catch(Exception e)
	{
		System.out.println(e);
	}		
}
public static void deleteOrder(int orderId,String orderName)
{
	try
	{
		
		getConnection();
		String deleteOrderQuery ="Delete from customerorders where OrderId=? and productId=?";
		PreparedStatement pst = conn.prepareStatement(deleteOrderQuery);
		pst.setInt(1,orderId);
		pst.setString(2,orderName);
		pst.executeUpdate();
	}
	catch(Exception e)
	{
				System.out.println(e);
	}
}

public static HashMap<String,Product> getData()
	{
		HashMap<String,Product> hm=new HashMap<String,Product>();
		try
		{
			getConnection();
			Statement stmt=conn.createStatement();
			String selectCustomerQuery="select * from  productdetails";
			ResultSet rs = stmt.executeQuery(selectCustomerQuery);
			while(rs.next())
			{	
				// System.out.println(rs.getString("Id")+" "+rs.getString("productName")+" "+rs.getDouble("productPrice")+" "+rs.getString("productImage")+" "+rs.getString("productCategory")+" "+rs.getString("ProductType"));
		
		
		Product p = new Product(rs.getString("Id"),rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("productCategory"),rs.getString("ProductType"));
				hm.put(rs.getString("Id"), p);
			}
		}
		catch(Exception e)
		{

			
			e.printStackTrace();	
		}
		return hm;			
	}

public static ArrayList<ProductSales> getProductSales(){
	ArrayList<ProductSales> productSalesList = new ArrayList<ProductSales>();

	try {
		getConnection();
		String getData = "SELECT productId,orderPrice,count(productId) as count,(count(productId)*orderPrice) as GrandTotal FROM customerorders group by productId;";
		Statement statement = conn.createStatement();
		ResultSet rs = statement.executeQuery(getData);
		while(rs.next()){
			ProductSales prodsales = new ProductSales(rs.getString("productId"),rs.getDouble("orderPrice"),rs.getInt("count"), rs.getDouble("GrandTotal"));
			productSalesList.add(prodsales);
		}

	} catch (Exception e) {
		//TODO: handle exception
	}
	return productSalesList;
}

public static void updateQuantity(String Id, double productStock){
	try{
		getConnection();
		String updateCount = "UPDATE productDetails set Availability=? where Id=?";

		PreparedStatement pst = conn.prepareStatement(updateCount);
		pst.setDouble(1,productStock);
		pst.setString(2,Id);
		pst.execute();
	} catch(Exception e){
	}
}

public static ArrayList<Sales> getSalesbyDate(){
	ArrayList<Sales> SalesonaDay = new ArrayList<Sales>();
	try {
		getConnection();
		Statement statement = conn.createStatement();
		String getSalesonADay = "SELECT orderDate,sum(totalsales) as sale from (select distinct orderDate, orderID, totalsales from customerorders) a group by 1" ;
		ResultSet rs = statement.executeQuery(getSalesonADay);
		while(rs.next()){
			Sales sl = new Sales(rs.getDate("orderDate").toString(), rs.getDouble("sale"));
			SalesonaDay.add(sl);
		}
	} catch (Exception e) {
		//TODO: handle exception
	}
	return SalesonaDay;
}

public static void insertTransaction(int orderId,String userName,String customerName ,double orderPrice,String userAddress,String creditCardNo, LocalDate apppointmentDate, String category, String productID, double totalSales)
{
	try
	{
		getConnection();
		String getData = "Select productName from productdetails where Id=?";
		PreparedStatement pstget = conn.prepareStatement(getData);
		pstget.setString(1,productID);
		ResultSet rs = pstget.executeQuery();	
		String productName = "";
		while(rs.next())
		{	
			productName = rs.getString("productName");
		}
		String insertTransactionQuery = "INSERT INTO transactions(login_ID,Customer_Name,Customer_Age,Customer_Occupation,Credit_Card_Number,Order_ID,Order_Date,apppointmentDate,Product_ID,Product_Name,Category,Review_Rating,Tracking_ID,Transaction_Status)"
			+" VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
		
		String[] occupation = new String[]{"Purchasing_Agent","Developer","Doctor","Farmer","Police_Officer"};
		String[] status = new String[]{"Approved","Disputed"};
		String[] yes_or_no = new String[]{"Yes","No"};
		
		LocalDate purchasedate = LocalDate.now();
		int quantity = 1;
		Date purchaseDate = Date.valueOf(purchasedate);
		Date date = Date.valueOf(apppointmentDate);
		LocalDate actualdate = purchasedate.plusDays( (long)(Math.random() * 30));
		int age = (int)(Math.random() * 50) + 15;

		PreparedStatement pst = conn.prepareStatement(insertTransactionQuery);
		
		//set the parameter for each column and execute the prepared statement
		pst.setString(1,userName);
		pst.setString(2,customerName);
		pst.setInt(3,age);
		pst.setString(4,occupation[((int)(Math.random() * 4))]);
		pst.setString(5,creditCardNo);
		pst.setInt(6,orderId);
		pst.setDate(7,purchaseDate);
		pst.setDate(8,date);
		pst.setString(9, productName);
		pst.setString(10, productName); 
		pst.setString(11, category);
		pst.setInt(12, (int)(Math.random() * 5));
		pst.setString(13, UUID.randomUUID().toString());
		pst.setString(14,status[((int)(Math.random() * 2))]);
		
		pst.execute();
	}
	catch(Exception e)
	{
		System.out.println(e);
	}		
}

	
}	