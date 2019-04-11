package com.app;

import java.io.*;
import java.util.*;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

public class OcadoApplication {

    public static void main(String[] args) {

        String filename = "product_data - product_data (1).csv";
        List<Product> products = mappingCsvJavaObject(filename);
        if (products != null) {

            Scanner sc = new Scanner(System.in);
            System.out.println("Podaj udzwig torby w gramach: ");
            int maxWeight = sc.nextInt();
            List<Product> choosenProducts = new ArrayList<>();
            System.out.println("Podaj id produktu: ");
            Scanner scan = new Scanner(System.in);
            String choose = scan.nextLine().trim();

            // weight of all choosen products
            int totalWeight = 0;

            do {
                for (int i = 0; i < products.size(); i++) {
                    if (products.get(i).getId().equals(choose)) {
                        System.out.println("poprawne ID produktu ");
                        choosenProducts.add(products.get(i));
                        totalWeight = totalWeight + Integer.parseInt(products.get(i).getWeight());
                    }

                }
                System.out.println("Podaj kolejny numer id produktu, jeśli chcesz zakonczyc wcisnij 'q': ");
                choose = scan.nextLine().trim();
            } while (!"q".equals(choose));

            Product[] simpleArray = new Product[choosenProducts.size()];
            choosenProducts.toArray(simpleArray);

            Bag bag;
            List<Product> used = new ArrayList<>();
            boolean tooHeavy = false;
            List<Product> toRemove = new ArrayList<>();
            int counter = 1;

            // weight of all packed products
            int countedWeight = 0;

            while (totalWeight != countedWeight) {

                for (int i = 0; i < choosenProducts.size(); i++) {
                    if (Integer.parseInt(choosenProducts.get(i).getWeight()) > maxWeight) {
                        System.out.println("Jeden lub wiecej przedmiotow ma zbyt duza wage na podana torbe");
                        tooHeavy = true;
                    }
                }

                if (tooHeavy) break;

                for (Product product1 : choosenProducts) {
                    for (Product product2 : used) {
                        if (product1 == product2) {
                            toRemove.add(product1);
                        }
                    }
                }
                choosenProducts.removeAll(toRemove);
                toRemove.removeAll(toRemove);
                simpleArray = new Product[choosenProducts.size()];
                choosenProducts.toArray(simpleArray);
                bag = new Bag(simpleArray, maxWeight);
                used.removeAll(used);
                used = bag.solution();

                System.out.println("Wybrane produkty w torbie numer: " + counter + " .");

                for (Product product : used) {
                    System.out.println("- " + product.toString());
                    countedWeight = countedWeight + Integer.parseInt(product.getWeight());

                }
                counter++;
            }
        } else {
            System.out.println("Pusta lista produktow otrzymanych z pliku");
        }

    }

    public static ArrayList<Product> mappingCsvJavaObject(String filename) {

        try {
            FileReader filereader = new FileReader(filename);
            CSVReader csvReader = new CSVReaderBuilder(filereader)
                    .withSkipLines(1)
                    .build();
            List<String[]> allData = csvReader.readAll();
            ArrayList<Product> productArrayList = new ArrayList<Product>();
            for (String[] row : allData) {
                if (row[3].equals("KG")) {
                    double kg = Double.parseDouble(row[2]);
                    int gram = (int) (kg * 1000);
                    row[2] = Integer.toString(gram);
                    row[3] = "GR";
                }
                productArrayList.add(new Product(row[0], row[1], row[2], row[3]));
            }
            return productArrayList;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
