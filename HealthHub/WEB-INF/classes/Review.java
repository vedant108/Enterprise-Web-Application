import java.io.IOException;
import java.io.*;


/* 
	Review class contains class variables username,productname,reviewtext,reviewdate,reviewrating

	Review class has a constructor with Arguments username,productname,reviewtext,reviewdate,reviewrating
	  
	Review class contains getters and setters for username,productname,reviewtext,reviewdate,reviewrating
*/

public class Review implements Serializable{
	private String productName;
	private String userName;
	private String productType;
	private String productMaker;
	private String reviewRating;
	private String reviewDate;
	private String reviewText;
	private String retailerpin;
	private String price;
	private String retailercity;
	private String age;
	private String gender;
	private String occupation;
	private String state;
	private String productonsale;
	private String storeid;
	private String manufacturerebate;
	
	public Review (String productName,String userName,String productType,String productMaker,String reviewRating,String reviewDate,
	String reviewText,String retailerpin,String price,String retailercity,
	String age,String gender,String occupation, String state,String productonsale,
	String storeid,String manufacturerebate){
		this.productName=productName;
		this.userName=userName;
		this.productType=productType;
		this.productMaker=productMaker;
	 	this.reviewRating=reviewRating;
		this.reviewDate=reviewDate;
	 	this.reviewText=reviewText;
		this.retailerpin=retailerpin;
		this.price= price;
		this.retailercity= retailercity;
		this.age = age;
		this.gender = gender;
		this.occupation = occupation;
		this.state = state;
		this.productonsale = productonsale;
		this.storeid = storeid;
		this.manufacturerebate=manufacturerebate;
	}

	public Review(String productName, String retailerpin, String reviewRating, String reviewText) {
       this.productName = productName;
       this.retailerpin = retailerpin;
       this.reviewRating = reviewRating;
       this.reviewText = reviewText;
    }

	public String getProductName() {
		return productName;
	}
	public String getUserName() {
		return userName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getProductMaker() {
		return productMaker;
	}

	public void setProductMaker(String productMaker) {
		this.productMaker = productMaker;
	}

	public String getReviewRating() {
		return reviewRating;
	}

	public String getReviewText() {
		return reviewText;
	}
	public void setReviewText(String reviewText) {
		this.reviewText = reviewText;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setReviewRating(String reviewRating) {
		this.reviewRating = reviewRating;
	}
	public String getReviewDate() {
		return reviewDate;
	}

	public void setReviewDate(String reviewDate) {
		this.reviewDate = reviewDate;
	}
    
		public String getRetailerPin() {
		return retailerpin;
	}

	public void setRetailerPin(String retailerpin) {
		this.retailerpin = retailerpin;
	}
			public String getRetailerCity() {
		return retailercity;
	}

	public void setRetailerCity(String retailercity) {
		this.retailercity = retailercity;
	}
	
			public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getAge(){
		return age;
	}
	public void setAge(String age){
		this.age = age;
	}

	public String getGender(){
		return gender;
	}
	public void setGender(String gender){
		this.gender = gender;
	}

	public String getOccupation(){
		return occupation;
	}
	public void setOccupation(String occupation){
		this.occupation = occupation;
	}

	public String getState(){
		return state;
	}
	public void setState(String state){
		this.state = state;
	}

	public String getProductOnSale(){
		return productonsale;
	}

	public void setProductOnSale(String productonsale){
		this.productonsale=productonsale;
	}

	public String getStoreId(){
		return storeid;
	}

	public void setStoreId(String storeid){
		this.storeid= storeid;
	}

	public String getManufactureRebate(){
		return manufacturerebate;
	}

	public void setManufactureRebate(String manufacturerebate){
		this.manufacturerebate= manufacturerebate;
	}
}
