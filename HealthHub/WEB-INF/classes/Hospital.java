import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/HospitalList")

public class Hospital extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		String name = null;
		String CategoryName = request.getParameter("type");
	
		HashMap<String, Com> hm = new HashMap<String, Com>();
		if(CategoryName==null){
			hm = MySqlDataStoreUtilities.getHospitals();
			name = "";
		}
		else
		{

			for(Map.Entry<String,Com> entry : MySqlDataStoreUtilities.getHospitals().entrySet())
			{
				if(entry.getValue().getCategory().equalsIgnoreCase(CategoryName))
				{
					hm.put(entry.getValue().getId(),entry.getValue());
				}
			}
		}

		
		/* Header, Left Navigation Bar are Printed.

		All the TV and TV information are dispalyed in the Content Section

		and then Footer is Printed*/

		Utilities utility = new Utilities(request,pw);
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>"+(CategoryName==null ? "":CategoryName)+" Hospital</a>");
		pw.print("</h2><div class='entry'><table id='bestseller'>");
		int i = 1; int size= hm.size();

		for(Map.Entry<String, Com> entry : hm.entrySet())
		{
			Com tv = entry.getValue();
			if(i%3==1) pw.print("<tr>");
			pw.print("<td><div id='shop_item'>");
			pw.print("<h3>"+tv.getName()+"</h3>");
			pw.print("<strong>$"+tv.getPrice()+"</strong><ul>");
			pw.print("<li id='item'><img src='images/Hospital/"+tv.getImage()+"' alt='' /></li>");
	
			if(tv.stock>0){
			pw.print("<li><form method='post' action='Cart'>" +
					"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='Hospital'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value=''>"+
					"<input type='submit' class='btnbuy' value='Book Appointment'></form></li>");
			}else{
				pw.print("<h4>Not available</h4>");
			}
			pw.print("<li><form method='post' action='WriteReview'>"+"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='Hospital'>"+
					"<input type='hidden' name='maker' value='"+tv.getCategory()+"'>"+
					"<input type='hidden' name='price' value='"+tv.getPrice()+"'>"+
				    "<input type='submit' value='WriteReview' class='btnreview'></form></li>");
			pw.print("<li><form method='post' action='ViewReview'>"+"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='Hospital'>"+
					"<input type='hidden' name='maker' value='"+tv.getCategory()+"'>"+
					"<input type='hidden' name='price' value='"+tv.getPrice()+"'>"+
				    "<input type='submit' value='ViewReview' class='btnreview'></form></li>");
			pw.print("</ul></div></td>");
			if(i%3==0 || i == size) pw.print("</tr>");
			i++;
		}	
		pw.print("</table></div></div></div>");
   
		utility.printHtml("Footer.html");
		
	}
}
