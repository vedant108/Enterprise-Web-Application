public class ProductSales {
    private String name;
    private int count;
    private double price;
    private double proprice;

    public ProductSales(String name,double proprice, int count, double price) {
        this.name = name;
        this.count = count;
        this.price = price;
        this.proprice = proprice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
	
	public double getProprice() {
        return proprice;
    }

    public void setProprice(double proprice) {
        this.proprice = proprice;
    }
}