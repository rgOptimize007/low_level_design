package Observer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ProductPurchaser {

    private Map<String,Stock> productStocks = new HashMap<>();
    private AlertGenerator pub = new AlertGenerator(new ArrayList<>());

    private int stockThresholdCapacity = 10;

    public ProductPurchaser(){
        productStocks.put("124",new Stock(15));
    }

    public boolean purchase(String productId) {

        Stock stock = productStocks.get(productId);
        if(stock.sellProduct()){
            checkStockCapacity(stock);
            return true;
        }
        return false;
    }

    private void checkStockCapacity(Stock stock) {
        if(stock.getQuantity() < 10) {
            pub.publish(stock);
        }
    }
}
