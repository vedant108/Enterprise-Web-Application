import java.util.*;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Com")

public class Com extends HttpServlet{
	private String id;
	private String name;
	private double price;
	private String image;
	private String category;  //Doctor,Hospital,Club - speciality, Insurance - Company,  
	private String type;
	private String address;
	private double zipcode;
	private double rating;
	public double stock;
	
	
	public Com(String name, double price, String image, String category, String type, String address, double zipcode, double rating, double stock){
		this.name=name;
		this.price=price;
		this.image=image;
		this.category = category;
		this.type = type;
		this.address = address;
		this.zipcode = zipcode;
		this.rating = rating;
		this.stock= stock;
	}
	
	public Com(){
		
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	public String getType(){
		return this.type;
	}
	
	public void setType(String type){
		this.type = type;
	}
	
	public String getAddress(){
		return this.address;
	}
	
	public void setAddress(String address){
		this.address = address;
	}
	
	public double getZipcode() {
		return zipcode;
	}
	public void setZipcode(double zipcode) {
		this.zipcode = zipcode;
	}
	
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
}
