import java.io.*;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ProductModify")

public class ProductModify extends HttpServlet {
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		String action = request.getParameter("button");
		Utilities utility = new Utilities(request, pw);
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
		if(action.equals("Addproduct"))
		{
			
			
			pw.print("<div id='content'><div class='post'>");
			pw.print("<h2 class='title meta'><a style='font-size: 24px;'>Add Service</a></h2>"
					+ "<div class='entry'>");
				
			
			pw.print("<form method='get' action='ProductCrud'>"
					+ "<table id='bestseller'><tr><td>"
					+ "<h3>Service Type</h3></td><td><select name='producttype' class='input'><option value='Doctor' selected>Doctor</option>"
					+ "<option value='Hospital'>Hospital</option>"
					+ "<option value='Insurance'>Insurance</option>"
					+ "<option value='Clubs'>Health Clubs</option>"
					
					
					+ "</td></tr><tr><td>"

					+ "<h3>Service Id</h3></td><td><input type='text' name='productId' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<h3>Service Name</h3></td><td><input type='text' name='productName' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					
					+ "<h3>Service Price</h3></td><td><input type='number' step='any' placeholder='please enter numeric data' name='productPrice' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<h3>Service Image</h3></td><td><input type='text' name='productImage' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<h3>Manufacturer/Speciality</h3></td><td><input type='text' name='productManufacturer' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<h3>Address</h3></td><td><input type='text' name='address' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<h3>Zipcode</h3></td><td><input type='text' name='zipcode' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					
					+ "<input type='submit' class='btnbuy' name='button' value='add' style='float: right;height: 20px margin: 20px; margin-right: 10px;'></input>"
					
					+ "</td></tr><tr><td></td><td>"
					
					+ "</td></tr></table>"
					+ "</form>" + "</div></div></div>");
			
		
		
		}else if (action.equals("Updateproduct"))
		{
		     pw.print("<div id='content'><div class='post'>");
			pw.print("<h2 class='title meta'><a style='font-size: 24px;'>Update Service</a></h2>"
					+ "<div class='entry'>");
			
			pw.print("<form method='get' action='ProductCrud'>"
					+ "<table id='bestseller'><tr><td>"
					+ "<h3>Service Type</h3></td><td><select name='producttype' class='input'><option value='Doctor' selected>Doctor</option>"
					+ "<option value='Hospital'>Hospital</option>"
					+ "<option value='Insurance'>Insurance</option>"
					+ "<option value='Clubs'>Health Clubs</option>"
					
					+ "</td></tr><tr><td>"
					+ "<h3>Service Id</h3></td><td><input type='text' name='productId' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<h3>Service Name</h3></td><td><input type='text' name='productName' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<h3>Service Price</h3></td><td><input type='number' step='any' name='productPrice' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<h3>Service Image</h3></td><td><input type='text' name='productImage' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<h3>Manufacturer/Speciality</h3></td><td><input type='text' name='productManufacturer' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<h3>Address</h3></td><td><input type='text' name='address' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<h3>Zipcode</h3></td><td><input type='text' name='zipcode' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					
					+ "<input type='submit' class='btnbuy' name='button' value='update' style='float: right;height: 20px margin: 20px; margin-right: 10px;'></input>"
					+ "</td></tr><tr><td></td><td>"
					
					+ "</td></tr></table>"
					+ "</form>" + "</div></div></div>");	
		}else
		{
			pw.print("<div id='content'><div class='post'>");
			pw.print("<h2 class='title meta'><a style='font-size: 24px;'>Delete Service</a></h2>"
					+ "<div class='entry'>");
			
			pw.print("<form method='get' action='ProductCrud'>"
					+ "<table style='width:100%'><tr><td>"
					+ "<h3>ServiceId</h3></td><td><input type='text' name='productId' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<input type='submit' class='btnbuy' name='button' value='delete' style='float: right;height: 20px margin: 20px; margin-right: 10px;'></input>"
					+ "</td></tr></table>"
					+ "</form>" + "</div></div></div>");
		}
		//displayLogin(request, response, pw, false);
		utility.printHtml("Footer.html");
		}
	}