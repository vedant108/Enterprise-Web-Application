import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Random;
import java.util.HashMap;
import java.util.Map;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

@WebServlet("/Payment")

public class Payment extends HttpServlet {
	
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		String msg = "good";
		String Customername= "";
		HttpSession session = request.getSession(true);

		Utilities utility = new Utilities(request, pw);
		if(!utility.isLoggedin())
		{
			session = request.getSession(true);				
			session.setAttribute("login_msg", "Please Login to Pay");
			response.sendRedirect("Login");
			return;
		}
		// get the payment details like credit card no address processed from cart servlet	


		String creditCardNo=request.getParameter("creditCardNo");
		
		String fullname = request.getParameter("fullname");
		
		String userAddress1 = request.getParameter("userAddress1");
		String userAddress2 = request.getParameter("userAddress2");
		String userAddress3 = request.getParameter("userAddress3");
		String userAddress4 = request.getParameter("userAddress4");
		String userAddress5 = request.getParameter("userAddress5");
		String userAddress = userAddress1+","+userAddress2+","+userAddress3+","+userAddress4+","+userAddress5;
		String total = request.getParameter("orderTotal");
		String appointmentDate = request.getParameter("appointmentdate");
		
		
		Double ordertotal = Double.parseDouble(total);
		
		if(session.getAttribute("usertype").equals("retailer")){
			Customername =request.getParameter("customername");
			try{
				HashMap<String,User> hm=new HashMap<String,User>();
				hm=MySqlDataStoreUtilities.selectUser();
				if(hm.containsKey(Customername)){
					if(hm.get(Customername).getUsertype().equals("customer")){
						msg ="good";
					}else {msg ="bad";}
						
				}else {msg ="bad";}
				
			}catch(Exception e)
			{
				
			}
		}

		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>Status</a>");
		pw.print("</h2><div class='entry'>");

		String message=MySqlDataStoreUtilities.getConnection();
		
		
			if (msg.equals("good") && !userAddress.isEmpty() && !creditCardNo.isEmpty())
			{
				Random rand = new Random(); 
				int ConfirmationNo = rand.nextInt(10000000);
				int orderId=utility.getOrderPaymentSize()+1;
				//iterate through each order
				LocalDate today = LocalDate.now();

				//add 2 week to the current date
				LocalDate deliveryDate = LocalDate.parse(appointmentDate);
			
			

			for (OrderItem oi : utility.getCustomerOrders()){
				String itemId = oi.getId(); 
				double a = oi.stock-1;
				MySqlDataStoreUtilities.updateQuantity(itemId, a);
			}
			
			for (OrderItem oi : utility.getCustomerOrders())
			{
				//set the parameter for each column and execute the prepared statement
				utility.storePayment(orderId,oi.getName(),oi.getPrice(),fullname,userAddress,creditCardNo, deliveryDate,today, ordertotal, oi.getType() );
				
			}

				//remove the order details from cart after processing
					
				OrdersHashMap.orders.remove(utility.username());
					
				pw.print("<h2>Your Appointment/Order");
				pw.print("&nbsp&nbsp");  
				pw.print("is stored ");
				pw.print("<br>Your Appointment/Order No is "+(orderId));
				pw.print("<br>Your Appointment/Order Confirmation No is "+(ConfirmationNo));
				pw.print("<br>Your Appointment/Order Date is  "+(deliveryDate));
			}else {
				pw.print("<h2>Customer does not exits</h2>");
			}		
		
		pw.print("</div></div></div>");		
		utility.printHtml("Footer.html");
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request, pw);
		
		
	}
}
