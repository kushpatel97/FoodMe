package kushpatel.foodme.model;

public class Item {

    String foodName;
    Double price;

    public Item(String foodName, Double price){
        this.foodName = foodName;
        this.price = price;
    }

    public Item(){
        this.foodName = foodName;
        this.price = price;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
