
public class Sales {
    private String orderDate;
    private  double sales;

    public Sales(String orderDate, double sales) {
        this.orderDate = orderDate;
        this.sales = sales;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public double getSales() {
        return sales;
    }

    public void setSales(double sales) {
        this.sales = sales;
    }
}
