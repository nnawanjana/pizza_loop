package lk.fct.pizza_loop;

public class Pizza {
    int pizzaId;
    String name;
    String description;
    Double price;
    String imageURL;

    public Pizza(String name, String description, Double price, String imageurl) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageURL = imageurl;
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
