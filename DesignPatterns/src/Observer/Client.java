package Observer;

import java.util.Scanner;

public class Client {

    private static ProductPurchaser purchaser = new ProductPurchaser();


    public static void main(String args[]){

         String productId = "124";

         Scanner sc = new Scanner(System.in);

        while(true) {
            System.out.println("Do you want to buy product 124: Shoes? ");
            char ans = sc.next().toUpperCase().charAt(0);
            if(ans == 'Y') {
                if (purchaser.purchase(productId)) {
                    System.out.println("Purchased product 124 successfully");
                } else {
                    System.out.println("Purchase failed due to insufficient Stock of product");
                }
            }
            else{
                System.out.println("Do you want to close purchase?");
                ans = sc.next().toUpperCase().charAt(0);
                if(ans == 'Y') break;
            }
        }



    }
}
