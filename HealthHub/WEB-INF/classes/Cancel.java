import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Random;
import java.util.ArrayList;

@WebServlet("/Cancel")

public class Cancel extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();


		Utilities utility = new Utilities(request, pw);
		if(!utility.isLoggedin())
		{
			HttpSession session = request.getSession(true);				
			session.setAttribute("login_msg", "Please Login to Pay");
			response.sendRedirect("Login");
			return;
		}
		
		String cancel1 = request.getParameter("maker");
		String name = request.getParameter("can");
				
		if(cancel1.equals("cancel"))
		{
			String us=utility.username();
			ArrayList<OrderItem> list = OrdersHashMap.orders.get(us);
			for(int i=0;i<list.size();i++){
				if(list.get(i).getName().equalsIgnoreCase(name)){
					list.remove(i);
					break;
				}
			}
			utility.printHtml("Header.html");
			utility.printHtml("LeftNavigationBar.html");
			pw.print("<a style='font-size: 24px;'>Order</a>");
			pw.print("</h2><div class='entry'>");
			pw.print("<h2>Your Item");
			pw.print("&nbsp&nbsp");  
			pw.print("has been removed from cart");
			pw.print("</h2></div></div></div>");
		}			
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request, pw);
		
		
	}
}









