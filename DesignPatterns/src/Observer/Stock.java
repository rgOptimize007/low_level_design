package Observer;

public class Stock {

    int productCapacity = 0;

    public Stock(int capacity){
        this.productCapacity = capacity;
    }

    public boolean sellProduct() {
        if(productCapacity > 0){
            productCapacity -= 1;
            return true;
        }
        return false;
    }

    public int getQuantity() {
        return productCapacity;
    }

    public String getName() {
        return "Shoes";
    }
}
