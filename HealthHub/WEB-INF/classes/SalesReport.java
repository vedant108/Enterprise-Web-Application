import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONObject;
import java.sql.*;

@WebServlet("/SalesReport")

public class SalesReport extends HttpServlet {
		
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
	
	
	
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

		Utilities utility = new Utilities(request, pw);
		//check if the user is logged in
		if(!utility.isLoggedin()){
			HttpSession session = request.getSession(true);				
			session.setAttribute("login_msg", "Please Login to View your Orders");
			response.sendRedirect("Login");
			return;
        }

        String username=utility.username();
		utility.printHtml("Header.html");
        utility.printHtml("LeftNavigationBar.html");
        pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
        pw.print("<a style='font-size: 24px;'>Sales Report</a>");
        pw.print("</h2><div class='entry'>");
        
        ArrayList<Sales> sale = new ArrayList<Sales>();
        ArrayList<ProductSales> prodSales = new ArrayList<ProductSales>();


        try {
            prodSales = MySqlDataStoreUtilities.getProductSales();
            sale = MySqlDataStoreUtilities.getSalesbyDate();
        } catch (Exception e) {
            System.out.println(e);
        }

        
        pw.print("<table  class='gridtable'>");
		pw.print("<h3>SALES TABLE:</h3>");
		pw.print("<tr><td>Service Name:</td>");
        pw.print("<td>Charges(per unit):</td>");
        pw.print("<td>Items Sold:</td>");
        pw.print("<td>Total Sale of Service:</td></tr>");
        
        for(ProductSales s : prodSales){
            pw.print("<tr>");	
            pw.print("<td>"+s.getName()+"</td><td>"+s.getProprice()+"</td><td>"+s.getCount()+"</td><td>"+s.getPrice()+"</td>");
            pw.print("</tr>");
        }

        pw.print("</table>");

        pw.print("<br>");
        pw.print("<br>");
        pw.print("<br>");

       

        pw.print("<table  class='gridtable'>");
		pw.print("<h3>TOTAL DAILY SALES TRANSACTIONS:</h3>");
		pw.print("<tr><td>Date:</td>");
        pw.print("<td>Sales:</td></tr>");

        for(Sales s : sale){
            pw.print("<tr>");	
            pw.print("<td>"+s.getOrderDate()+"</td><td>"+s.getSales()+"</td>");
            pw.print("</tr>");
        }
        pw.print("</table>");


		pw.print("<br>");
        pw.print("<br>");
        pw.print("<br>");
        pw.print("<h3><button id='clickhere'>View Chart</h3>");
        pw.println("<div id='chart_div'></div>");
        pw.println("<script type='text/javascript' src=\"https://www.gstatic.com/charts/loader.js\"></script>");
        pw.println("<script type='text/javascript' src='SalesVisualisation.js'></script>");
		
        // pw.print("<br>");
        // pw.print("<br>");
        // pw.print("<br>");
        // pw.print("<h3><button id='clickhere'>View Chart</h3>");
        // pw.println("<div id='chart_div'></div>");
        // pw.println("<script type='text/javascript' src=\"https://www.gstatic.com/charts/loader.js\"></script>");
        // pw.println("<script type='text/javascript' src='DataVisualization.js'></script>");
		
		
		pw.print("</div></div></div>");

        utility.printHtml("Footer.html");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {  

                    try {
                        getConnection();
                        String getData = "SELECT productId,(count(productId)*orderPrice) as GrandTotal FROM customerorders group by productId;";
                        Statement statement = conn.createStatement();
                        ResultSet rs = statement.executeQuery(getData);
                        JSONArray json = new JSONArray();
						System.out.println("AAAA");
                        while(rs.next()){
                            JSONObject obj = new JSONObject();
                            obj.put("product_name", rs.getString("productId"));
                            obj.put("product_sales", rs.getDouble("GrandTotal"));
                            json.put(obj);
                        }

                            String jsonSales = new Gson().toJson(json);
                            response.setContentType("application/JSON");
                            response.setCharacterEncoding("UTF-8");
                            response.getWriter().write(jsonSales);
                
                    } catch (Exception e) {
                        //TODO: handle exception
                    }                
                }
}

