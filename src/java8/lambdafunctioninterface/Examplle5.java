package java8.lambdafunctioninterface;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

class Product {
    int id;
    String name;
    float price;

    public Product(int id, String name, float price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}

public class Examplle5 {
    public static void main(String[] args) {
        List<Product> listProduct = new ArrayList<>();
        listProduct.add(new Product(1, "SamSung", 4000F));
        listProduct.add(new Product(1, "Sony", 6000F));
        listProduct.add(new Product(1, "Nokia", 2000F));
        listProduct.add(new Product(1, "Xiaomi", 4000F));
        listProduct.add(new Product(1, "Lenovo", 3000F));
        Stream<Product> filtered = listProduct.stream().filter(product -> product.price > 3000F);
        filtered.forEach(product -> System.out.println(product.name));
    }
}
