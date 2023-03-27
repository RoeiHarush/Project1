public class Food {
    //TODO:  foodType ENUM
    private String name;
    private int quantity;

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Food(String type, int quantity) {
        this.name = type;
        this.quantity = quantity;
    }


    public void reduceStock() {
        quantity -= 1;
    }


    public String getFoodInfo() {
        return this.name + " " + this.quantity;
    }

}