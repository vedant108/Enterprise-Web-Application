import java.io.Serializable;


public class Store implements Serializable {
    private String  storeID;
    private String storeAddress;
    private int storeZIP;

    public Store(String storeID, String storeAddress, int storeZIP) {
        this.storeID = storeID;
        this.storeAddress = storeAddress;
        this.storeZIP = storeZIP;
    }

    public String getStoreID() {
        return storeID;
    }

    public void setStoreID(String storeID) {
        this.storeID = storeID;
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }

    public int getStoreZIP() {
        return storeZIP;
    }

    public void setStoreZIP(int storeZIP) {
        this.storeZIP = storeZIP;
    }
}
