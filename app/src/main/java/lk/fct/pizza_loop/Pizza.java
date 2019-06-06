package lk.fct.pizza_loop;

public class Pizza {
    int pizzaId;
    String name;
    String description;
    Double price;
    String imageURL;
    Double smallprice;
    Double mediumprice;
    Double largeprice;

    public Double getSmallprice() {
        return smallprice;
    }

    public void setSmallprice(Double smallprice) {
        this.smallprice = smallprice;
    }

    public Double getMediumprice() {
        return mediumprice;
    }

    public void setMediumprice(Double mediumprice) {
        this.mediumprice = mediumprice;
    }

    public Double getLargeprice() {
        return largeprice;
    }

    public void setLargeprice(Double largeprice) {
        this.largeprice = largeprice;
    }

    public Pizza(String name, String description, Double price, String imageurl, Double smallprice,Double mediumprice,Double largeprice) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageURL = imageurl;
        this.smallprice = smallprice;
        this.mediumprice = mediumprice;
        this.largeprice = largeprice;

    }

    public int getPizzaId() {
        return pizzaId;
    }

    public void setPizzaId(int pizzaId) {
        this.pizzaId = pizzaId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String details) {
        this.description = details;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
