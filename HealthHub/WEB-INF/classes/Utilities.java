import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@WebServlet("/Utilities")

/* 
	Utilities class contains class variables of type HttpServletRequest, PrintWriter,String and HttpSession.

	Utilities class has a constructor with  HttpServletRequest, PrintWriter variables.
	  
*/

public class Utilities extends HttpServlet{
	HttpServletRequest req;
	PrintWriter pw;
	String url;
	HttpSession session; 
	public Utilities(HttpServletRequest req, PrintWriter pw) {
		this.req = req;
		this.pw = pw;
		this.url = this.getFullURL();
		this.session = req.getSession(true);
	}



	/*  Printhtml Function gets the html file name as function Argument, 
		If the html file name is Header.html then It gets Username from session variables.
		Account ,Cart Information ang Logout Options are Displayed*/

	public void printHtml(String file) {
		String result = HtmlToString(file);
		//to print the right navigation in header of username cart and logout etc
		if (file == "Header.html") {
				result=result+"<div id='menu' style='float: right;'><ul>";
			if (session.getAttribute("username")!=null){
				String username = session.getAttribute("username").toString();
				username = Character.toUpperCase(username.charAt(0)) + username.substring(1);
				if(session.getAttribute("usertype").equals("manager"))
				{

					result = result + "<li><div class='dropdown'>";
					result = result + "<a class='dropbtn'> Actions <i class='arrow down'></i></a>";
					result = result + "<div class='dropdown-content'>";
					result = result + "<a href='ProductModify?button=Addproduct'>&nbsp Add</a>";
					result = result + "<a href='ProductModify?button=Deleteproduct'>&nbsp Delete</a>";
					result = result + "<a href='ProductModify?button=Updateproduct'>&nbsp Update</a>";
					result = result + "</div>";
					result = result + "</div></li>";

					result = result + "<li><div class='dropdown'>";
					result = result + "<a class='dropbtn'> Reports <i class='arrow down'></i></a>";
					result = result + "<div class='dropdown-content'>";
					result = result + "<a href='Inventory'>Inventory</a>";
					result = result + "<a href='SalesReport'>&nbsp Sales</a>";
					result = result + "</div>";
					result = result + "</div></li>";

					result = result +"<li><a href='Trending'><span class='glyphicon'>Trending</span></a></li>"
						+"<li><a href='DataAnalytics'><span class='glyphicon'>DataAnalytics</span></a></li>"
						+ "<li><a><span class='glyphicon'>Hello,"+username+"</span></a></li>"
						+ "<li><a href='Logout'><span class='glyphicon'>Logout</span></a></li>";
				}
				
				else if(session.getAttribute("usertype").equals("retailer")){
					result = result + "<li><a href='Registration'><span class='glyphicon'>Create Customer</span></a></li>"
						+ "<li><a href='ViewOrder'><span class='glyphicon'>View MyActivity</span></a></li>"
						+ "<li><a><span class='glyphicon'>Hello,"+username+"</span></a></li>"
						+ "<li><a href='Logout'><span class='glyphicon'>Logout</span></a></li>";
				}
				else
				{
				result = result + "<li><a href='ViewOrder'><span class='glyphicon'>View MyActivity</span></a></li>"
						+ "<li><a><span class='glyphicon'>Hello,"+username+"</span></a></li>"
						+ "<li><a href='Account'><span class='glyphicon'>Account</span></a></li>"
						+ "<li><a href='Logout'><span class='glyphicon'>Logout</span></a></li>";
			    }
			}
			else
				result = result +"<li><a href='ViewOrder'><span class='glyphicon'>View MyActivity</span></a></li>"+ "<li><a href='Login'><span class='glyphicon'>Login</span></a></li>";
				result = result +"<li><a href='Cart'><span class='glyphicon'>Cart("+CartCount()+")</span></a></li></ul></div></div><div id='page'>";
				pw.print(result);
		} else
				pw.print(result);
	}
	

	/*  getFullURL Function - Reconstructs the URL user request  */

	public String getFullURL() {
		String scheme = req.getScheme();
		String serverName = req.getServerName();
		int serverPort = req.getServerPort();
		String contextPath = req.getContextPath();
		StringBuffer url = new StringBuffer();
		url.append(scheme).append("://").append(serverName);

		if ((serverPort != 80) && (serverPort != 443)) {
			url.append(":").append(serverPort);
		}
		url.append(contextPath);
		url.append("/");
		return url.toString();
	}

	/*  HtmlToString - Gets the Html file and Converts into String and returns the String.*/
	public String HtmlToString(String file) {
		String result = null;
		try {
			String webPage = url + file;
			URL url = new URL(webPage);
			URLConnection urlConnection = url.openConnection();
			InputStream is = urlConnection.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);

			int numCharsRead;
			char[] charArray = new char[1024];
			StringBuffer sb = new StringBuffer();
			while ((numCharsRead = isr.read(charArray)) > 0) {
				sb.append(charArray, 0, numCharsRead);
			}
			result = sb.toString();
		} 
		catch (Exception e) {
		}
		return result;
	} 

	/*  logout Function removes the username , usertype attributes from the session variable*/

	public void logout(){
		session.removeAttribute("username");
		session.removeAttribute("usertype");
	}
	
	/*  logout Function checks whether the user is loggedIn or Not*/

	public boolean isLoggedin(){
		if (session.getAttribute("username")==null)
			return false;
		return true;
	}

	/*  username Function returns the username from the session variable.*/
	
	public String username(){
		if (session.getAttribute("username")!=null)
			return session.getAttribute("username").toString();
		return null;
	}
	
	/*  usertype Function returns the usertype from the session variable.*/
	public String usertype(){
		if (session.getAttribute("usertype")!=null)
			return session.getAttribute("usertype").toString();
		return null;
	}
	
	/*  getUser Function checks the user is a customer or retailer or manager and returns the user class variable.*/
	public User getUser(){
		String usertype = usertype();
		HashMap<String, User> hm=new HashMap<String, User>();
		try
		{		
			hm=MySqlDataStoreUtilities.selectUser();
		}
		catch(Exception e)
		{
		}	
		User user = hm.get(username());
		return user;
	}
	
	/*  getCustomerOrders Function gets  the Orders for the user*/
	public ArrayList<OrderItem> getCustomerOrders(){
		ArrayList<OrderItem> order = new ArrayList<OrderItem>(); 
		if(OrdersHashMap.orders.containsKey(username()))
			order= OrdersHashMap.orders.get(username());
		return order;
	}

	/*  getOrdersPaymentSize Function gets  the size of OrderPayment */
	public int getOrderPaymentSize(){
		
		HashMap<Integer, ArrayList<OrderPayment>> orderPayments = new HashMap<Integer, ArrayList<OrderPayment>>();
		int size=0;
		try
		{
			orderPayments = MySqlDataStoreUtilities.selectOrder();
				
		}
		catch(Exception e)
		{
			
		}
		for(Map.Entry<Integer, ArrayList<OrderPayment>> entry : orderPayments.entrySet()){
				size=entry.getKey();
		}
			
		return size;	
	}

	/*  CartCount Function gets  the size of User Orders*/
	public int CartCount(){
		if(isLoggedin())
		return getCustomerOrders().size();
		return 0;
	}
	
	/* StoreProduct Function stores the Purchased product in Orders HashMap according to the User Names.*/

	public void storeProduct(String name,String type,String maker, String acc){
		if(!OrdersHashMap.orders.containsKey(username())){	
			ArrayList<OrderItem> arr = new ArrayList<OrderItem>();
			OrdersHashMap.orders.put(username(), arr);
		}
		ArrayList<OrderItem> orderItems = OrdersHashMap.orders.get(username());
		
		HashMap<String,Com> all = new HashMap<String,Com> ();
		
		
		if(type.equalsIgnoreCase("Doctor")){
			Com pet;
			try{
				all = MySqlDataStoreUtilities.getDoctors();
			}
			catch(Exception e){
				
			}
			pet = all.get(name);
			System.out.println(name);
			OrderItem orderitem = new OrderItem(pet.getName(), pet.getPrice(), pet.getImage(), pet.getCategory(),  0, type, pet.getId(), pet.stock);
			orderItems.add(orderitem);
		}
		if(type.equalsIgnoreCase("Hospital")){
			Com virtual;
			try{
				all = MySqlDataStoreUtilities.getHospitals();
			}
			catch(Exception e){
				
			}
			virtual = all.get(name);
			OrderItem orderitem = new OrderItem(virtual.getName(), virtual.getPrice(), virtual.getImage(), virtual.getCategory(), 0, type, virtual.getId(),virtual.stock);
			orderItems.add(orderitem);
		}
		if(type.equalsIgnoreCase("Insurance")){
			Com smart;
			try{
				all = MySqlDataStoreUtilities.getInsurance();
			}
			catch(Exception e){
				
			}
			smart = all.get(name);
			OrderItem orderitem = new OrderItem(smart.getName(), smart.getPrice(), smart.getImage(), smart.getCategory(), 0, type, smart.getId(),smart.stock);
			orderItems.add(orderitem);
		}
		if(type.equalsIgnoreCase("Clubs")){
			Com fitness;
			try{
				all = MySqlDataStoreUtilities.getClubs();
			}
			catch(Exception e){
				
			}
			fitness = all.get(name);
			OrderItem orderitem = new OrderItem(fitness.getName(), fitness.getPrice(), fitness.getImage(), fitness.getCategory(),0, type, fitness.getId(),fitness.stock);
			orderItems.add(orderitem);
		}
	}
	
	// store the payment details for orders
	public void storePayment(int orderId,
		String orderName,double orderPrice,String customerName,String userAddress,String creditCardNo, LocalDate AppointmentDate, LocalDate orderdate ,double totalSales ,String category){
		HashMap<Integer, ArrayList<OrderPayment>> orderPayments= new HashMap<Integer, ArrayList<OrderPayment>>();
			

			// // get the payment details file 
			try	{	
				orderPayments=MySqlDataStoreUtilities.selectOrder();
				}	
			catch(Exception e)	
			{
			}
			if(orderPayments==null)
			{
				orderPayments = new HashMap<Integer, ArrayList<OrderPayment>>();
			}
			// if there exist order id already add it into same list for order id or create a new record with order id
			
			if(!orderPayments.containsKey(orderId)){	
				ArrayList<OrderPayment> arr = new ArrayList<OrderPayment>();
				orderPayments.put(orderId, arr);
			}
			ArrayList<OrderPayment> listOrderPayment = orderPayments.get(orderId);	
		
			OrderPayment orderpayment = new OrderPayment(orderId,username(),orderName,orderPrice,userAddress,creditCardNo, AppointmentDate, orderdate);
			listOrderPayment.add(orderpayment);	
			
			// add order details into database

			try	{	
				
						
				MySqlDataStoreUtilities.insertOrder(orderId,username(),customerName,orderName,orderPrice,userAddress,creditCardNo, AppointmentDate, orderName,  totalSales, category);	
				
				}
			
			
		catch(Exception e){
				System.out.println("inside exception file not written properly" + e );
			}	
	}
	
	public String storeReview(String productname,String producttype,String productmaker,
	String reviewrating,String reatilerpin,String price,
	String userid,String age,String gender,String occupation,String zipcode,
	String retailercity,String storestate,String productonsale,
	String storeid,String manufacturerebate,
	String reviewdate,String reviewtext){
	String message=MongoDBDataStoreUtilities.insertReview(productname,username(),
	producttype,productmaker,reviewrating,reatilerpin,
	price,
	userid,age,gender,occupation,zipcode,retailercity,
	storestate,productonsale,storeid,manufacturerebate,reviewdate,reviewtext);
		if(!message.equals("Successfull"))
		{ return "UnSuccessfull";
		}
		else
		{
		HashMap<String, ArrayList<Review>> reviews= new HashMap<String, ArrayList<Review>>();
		try
		{
			reviews=MongoDBDataStoreUtilities.selectReview();
		}
		catch(Exception e)
		{
			
		}
		if(reviews==null)
		{
			reviews = new HashMap<String, ArrayList<Review>>();
		}
			// if there exist product review already add it into same list for productname or create a new record with product name
			
		if(!reviews.containsKey(productname)){	
			ArrayList<Review> arr = new ArrayList<Review>();
			reviews.put(productname, arr);
		}
		ArrayList<Review> listReview = reviews.get(productname);		
		Review review = new Review(productname,username(),producttype,productmaker,
		reviewrating,reviewdate,reviewtext,reatilerpin,retailercity,price,age,gender,
		occupation,storestate,productonsale,storeid,manufacturerebate);
		listReview.add(review);	
			
			// add Reviews into database
		
		return "Successfull";	
		}
	}


	

}
