package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Main {
    private static int currentPage = 1;
    public static void main(String[] args) {

        try {
            for(int i=currentPage; i<currentPage + 5; i++) {

                Document doc = Jsoup.connect("https://turbo.az/autos/?page="+i).get();
                Elements adLinks = doc.select(".products-i__link");

                for (Element adLink : adLinks) {
                    String adUrl = adLink.attr("abs:href");
                    Document doc1 = Jsoup.connect(adUrl).get();
                    String name = doc1.select("h1.product-title ").text();
                    System.out.println("");
                    System.out.println(name);

                    Elements divs = doc1.select(".product-properties__column");
                    for(Element div: divs){
                        Elements elements = div.select(".product-properties__i ");
                            String price = doc1.select(".product-price__i.product-price__i--bold").text();
                            System.out.println("Price : "+price);
                        for (Element element : elements) {
                            String elementLabel = element.select("label").text();
                            String elementSpan = element.select("span").first().text();
                            System.out.println(elementLabel + " : " + elementSpan);
                        }
                    }

                }

            }
            currentPage += 5;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}