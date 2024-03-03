package Observer;

public class AdminListener implements AlertListener {
    @Override
    public void generateAlert(Stock stock) {
        System.out.println("Need stock replenishment for stock : " + stock.getName());
    }
}
