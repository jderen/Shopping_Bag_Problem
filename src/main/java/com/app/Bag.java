package com.app;

import java.util.ArrayList;
import java.util.List;

public class Bag {

    private Product[] products;
    private int maxWeight;


    public Bag(Product[] products, int maxWeight) {
        this.products = products;
        this.maxWeight = maxWeight;
    }


    public List<Product> solution(){

        int productsLength=products.length;
        int[][] matrix=new int[productsLength+1][maxWeight+1];
        
        for (int i = 1; i <= productsLength; ++i) {
            for (int j = 1; j <= maxWeight; ++j) {
                int first = matrix[i - 1][j];
                int second = -1;
                if (j - Integer.parseInt(products[i-1].weight)>= 0) { // No caching to keep this readable
                    second = matrix[i - 1][j - Integer.parseInt(products[i-1].weight)] + Integer.parseInt(products[i-1].weight);
                    if (second > j)
                        second = -1; // Not allowed
                }
                matrix[i][j] = Math.max(first, second);

            }
        }
        int res =matrix[productsLength][maxWeight];
        int w = maxWeight;
        List<Product> productsResult=new ArrayList<>();

        for(int i=productsLength;i>0&&res>0;i--){
            if(res !=matrix[i-1][w]){
                productsResult.add(products[i-1]);
                res-=Integer.parseInt(products[i-1].weight);
                w-=Integer.parseInt(products[i-1].weight);
            }
        }


        return productsResult;

    }
}
