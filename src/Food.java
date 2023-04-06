public class Food {
    private FoodType name;
    public int quantity;



    public Food(FoodType type, int quantity) {
        this.name = type;
        this.quantity = quantity;
    }

    public String getName() {
        return name.toString();
    }


    public void setName(String name) {
        this.name = FoodType.valueOf(name);
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public void order(int num) {
        quantity += num;
    }



    public String getFoodInfo() {
        return this.name + " " + this.quantity;
    }

    public int getQuantity() {
        return this.quantity;
    }
    public void reduceStock() {
        quantity -= 1;
    }
    public boolean inStock() {
        return quantity > 0;
    }
}