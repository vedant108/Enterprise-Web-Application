import java.io.*;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ProductCrud")

public class ProductCrud extends HttpServlet {
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
			response.setContentType("text/html");
			PrintWriter pw = response.getWriter();
			Utilities utility = new Utilities(request, pw);
			String action = request.getParameter("button");
			
			String msg = "good";
			String producttype= "",productId="",productName="",productImage="",productManufacturer="",productCondition="",zipcode = "",address="";
			double productPrice=0.0,productDiscount = 0.0;

			HashMap<String,Com> all = new HashMap<String,Com> ();
			
			
			if (action.equalsIgnoreCase("add") || action.equalsIgnoreCase("update"))
			{	
				 producttype = request.getParameter("producttype");
				 productId   = request.getParameter("productId");
				 productName = request.getParameter("productName"); 
				 productPrice = Double.parseDouble(request.getParameter("productPrice"));
				 productImage = request.getParameter("productImage");
				 productManufacturer = request.getParameter("productManufacturer");
				 address = request.getParameter("address");
				 zipcode = request.getParameter("zipcode");
				 
			}
			else{
				productId   = request.getParameter("productId");
			}	
			utility.printHtml("Header.html");
			utility.printHtml("LeftNavigationBar.html");

			if(action.equalsIgnoreCase("add"))
			{
			  
			  if (msg.equalsIgnoreCase("good"))
			  {  
				  try
				  {
					  msg = MySqlDataStoreUtilities.addproducts(productId,productName,productPrice,productImage,productManufacturer,producttype,address, Double.valueOf(zipcode),3.5, 20.0);
				  }
				  catch(Exception e)
				  { 
					msg = "Product cannot be inserted";
				  }
				  msg = "Product has been successfully added";
			  }					
			}else if(action.equalsIgnoreCase("update"))
			{
				
			  if (msg.equalsIgnoreCase("good"))
			  {		
				  try
				  {
					msg = MySqlDataStoreUtilities.updateproducts(productId,productName,productPrice,productImage,productManufacturer,producttype,address, Double.valueOf(zipcode),3.5, 20.0);
				  }
				  catch(Exception e)
				  { 
					msg = "Product cannot be updated";
				  }
				  msg = "Product has been successfully updated";
			  } 
			}else if(action.equalsIgnoreCase("delete"))
			{
				  msg = "good";
				  
		       		
				  if (msg.equalsIgnoreCase("good"))
				  {		
					  try
					  {  
						 msg = MySqlDataStoreUtilities.deleteproducts(productId);
					  }
					  catch(Exception e)
					  { 
						msg = "Product cannot be deleted";
					  }
					   msg = "Product has been successfully deleted";
				  }else{
					  msg = "Product not available";
				  }
			}	
				
			pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
			pw.print("<a style='font-size: 24px;'>Order</a>");
			pw.print("</h2><div class='entry'>");
			pw.print("<h4 style='color:blue'>"+msg+"</h4>");
			pw.print("</div></div></div>");		
			utility.printHtml("Footer.html");
	}
}