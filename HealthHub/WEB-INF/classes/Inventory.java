import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;
import java.io.*;
import java.sql.*;
import org.json.JSONArray;
import org.json.JSONObject;
import com.google.gson.Gson;

@WebServlet("/Inventory")

public class Inventory extends HttpServlet {
	
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
        pw.print("<a style='font-size: 24px;'>Availability(Inventory)</a>");
        pw.print("</h2><div class='entry'>");


        HashMap<String,Com> alldoctors = new HashMap<String,Com> ();
		HashMap<String,Com> allhospitals = new HashMap<String,Com> ();
		HashMap<String,Com> allinsurance = new HashMap<String,Com> ();
		HashMap<String,Com> allclubs = new HashMap<String,Com> ();
	
		alldoctors = MySqlDataStoreUtilities.getDoctors();
		allhospitals= MySqlDataStoreUtilities.getHospitals();
		allinsurance = MySqlDataStoreUtilities.getInsurance();
		allclubs = MySqlDataStoreUtilities.getClubs();			

        

        pw.print("<table  class='gridtable'>");
        pw.print("<h3>Services with Available Inventory:</h3>");
		pw.print("<tr><td>Service name:</td>");
		pw.print("<td>Charges ($):</td>");
        pw.print("<td>Inventory Available:</td></tr>");
        
        for (Com tv : alldoctors.values()){
			
			
            pw.print("<tr>");	
            pw.print("<td>"+tv.getName()+"</td><td>"+tv.getPrice()+"</td><td>"+(int)tv.stock+"</td>");
            pw.print("</tr>");
        }
        for (Com tv : allhospitals.values()){
			
			
            pw.print("<tr>");	
            pw.print("<td>"+tv.getName()+"</td><td>"+tv.getPrice()+"</td><td>"+(int)tv.stock+"</td>");
            pw.print("</tr>");
        }
        for (Com tv : allinsurance.values()){
			
			
            pw.print("<tr>");	
            pw.print("<td>"+tv.getName()+"</td><td>"+tv.getPrice()+"</td><td>"+(int)tv.stock+"</td>");
            pw.print("</tr>");
        }
        for (Com tv : allclubs.values()){
			
			
            pw.print("<tr>");	
            pw.print("<td>"+tv.getName()+"</td><td>"+tv.getPrice()+"</td><td>"+(int)tv.stock+"</td>");
            pw.print("</tr>");
        }


        pw.print("</table>");

        
		 pw.print("<br>");
        pw.print("<br>");
        pw.print("<br>");
        pw.print("<h3><button id='clickhere'>Chart Of Availability</h3>");
        pw.println("<div id='chart_div'></div>");
        pw.println("<script type='text/javascript' src=\"https://www.gstatic.com/charts/loader.js\"></script>");
        pw.println("<script type='text/javascript' src='InventoryVisualisation.js'></script>");
        

        pw.print("</div></div></div>");
		
		
		

        utility.printHtml("Footer.html");
        
    }
	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {  

                    try {
                        getConnection();
                        String getData = "SELECT productName, Availability as productStock FROM productdetails;";
                        Statement statement = conn.createStatement();
                        ResultSet rs = statement.executeQuery(getData);
                        JSONArray json = new JSONArray();

                        while(rs.next()){
							JSONObject obj = new JSONObject();
                            obj.put("product_name", rs.getString("productName"));
                            obj.put("product_quantity", rs.getInt("productStock"));
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