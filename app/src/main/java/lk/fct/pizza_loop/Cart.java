package lk.fct.pizza_loop;

public class Cart {
    int cartId;
    String imageUrl;
    String pizza_name;
    String pizza_crust;
    String pizza_size;
    String extra;
    int qty;
    double totalprice;
    String status;
    String userID;


    public Cart(int cartId, String imageUrl,String pizza_name, String pizza_crust, String pizza_size, String extra,int qty,  double totalprice) {
        this.cartId = cartId;
        this.imageUrl=imageUrl;
        this.pizza_name = pizza_name;
        this.pizza_crust = pizza_crust;
        this.pizza_size = pizza_size;
        this.extra = extra;
        this.qty = qty;
        this.totalprice = totalprice;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public String getPizza_name() {
        return pizza_name;
    }

    public void setPizza_name(String pizza_name) {
        this.pizza_name = pizza_name;
    }

    public String getPizza_crust() {
        return pizza_crust;
    }

    public void setPizza_crust(String pizza_crust) {
        this.pizza_crust = pizza_crust;
    }

    public String getPizza_size() {
        return pizza_size;
    }

    public void setPizza_size(String pizza_size) {
        this.pizza_size = pizza_size;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(double totalprice) {
        this.totalprice = totalprice;
    }

}
