import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/OrderItem")

/* 
	OrderItem class contains class variables name,price,image,retailer.

	OrderItem  class has a constructor with Arguments name,price,image,retailer.
	  
	OrderItem  class contains getters and setters for name,price,image,retailer.
*/

public class OrderItem extends HttpServlet{
	private String name;
	private double price;
	private String image;
	private String retailer;
	private double discount;
	private String type;
	private String id;
	public double stock;
	public OrderItem(String name, double price, String image, String retailer, double discount, String type, String id, double stock){
		this.name=name;
		this.price=price;
		this.image=image;
		this.retailer = retailer;
		this.discount = discount;
		this.type = type;
		this.id = id;
		this.stock =stock;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getRetailer() {
		return retailer;
	}

	public void setRetailer(String retailer) {
		this.retailer = retailer;
	}
	
	public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
