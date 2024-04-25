package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        System.out.println("Hello world!");

        try {
            Document doc = Jsoup.connect("https://turbo.az/autos/7970206-porsche-taycan-gts").get();
            System.out.println("get request");
            String brand = doc.select("h1.product-title ").text();
            System.out.println("set data");
            System.out.println(brand);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}