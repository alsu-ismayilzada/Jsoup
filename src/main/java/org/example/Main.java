package org.example;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Main {
    private static int currentPage = 1;

    public static void main(String[] args) {
        Workbook wb = new HSSFWorkbook();
        try (OutputStream os = new FileOutputStream("autos1.xls")) {
            Sheet sheet = wb.createSheet("Autos");
            Row header = sheet.createRow(0);
            header.createCell(0).setCellValue("Price");

            for (int i = currentPage; i < currentPage + 1; i++) {
                Document doc = Jsoup.connect("https://turbo.az/autos/?page=" + i).get();
                Elements adLinks = doc.select(".products-i__link");
                for (Element adLink : adLinks) {
                    String adUrl = adLink.attr("abs:href");
                    Document doc1 = Jsoup.connect(adUrl).get();
                    String name = doc1.select("h1.product-title").text();
                    System.out.println("");
                    System.out.println(name);

                    String price = doc1.select(".product-price__i.product-price__i--bold").text();
                    System.out.println("Price : " + price);

                    Elements divs = doc1.select(".product-properties__column");
                    Row newRow = sheet.createRow(sheet.getLastRowNum() + 1);
                    newRow.createCell(0).setCellValue(price);
                    int countCell = 1;
                    for (Element div : divs){
                        Elements elements = div.select(".product-properties__i ");
                        for (Element element : elements) {}
                        for (Element element : elements) {
                            String elementLabel = element.select("label").text();
                            String elementSpan = element.select("span").first().text();
                            int m = newRow.getLastCellNum();

                            header.createCell(countCell).setCellValue(elementLabel);
                            newRow.createCell(countCell++).setCellValue(elementSpan);
                            System.out.println(elementLabel + " : " + elementSpan);
                        }
                    }
                }
            }
            wb.write(os);
            currentPage += 5;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}


//package org.example;
//
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;
//import org.jsoup.select.Elements;
//
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.OutputStream;
//
//public class Main {
//    private static int currentPage = 1;
//    public static void main(String[] args) {
//        int countRow = 1;
//
//        Workbook wb = new HSSFWorkbook();
//        try(OutputStream os = new FileOutputStream("autos90.xls")) {
//            Sheet sheet = wb.createSheet("Autos");
//            Row header = sheet.createRow(0);
//            for(int i=currentPage; i<currentPage + 3; i++) {
//                Document doc = Jsoup.connect("https://turbo.az/autos/?page="+i).get();
//                Elements adLinks = doc.select(".products-i__link");
//
//                for (Element adLink : adLinks) {
//                    int countCell = 0;
//                    String adUrl = adLink.attr("abs:href");
//                    Document doc1 = Jsoup.connect(adUrl).get();
//                    String name = doc1.select("h1.product-title ").text();
//                    System.out.println("");
//                    System.out.println(name);
//
//                    Row newRow = sheet.createRow(countRow++);
//
//                    Elements divs = doc1.select(".product-properties__column");
//                    Cell newCell  = newRow.createCell(countCell);
//                    Cell headerCell  = header.createCell(countCell);
//                    headerCell.setCellValue("Price");
//                    countCell++;
//                    for(Element div: divs){
//                        String price = doc1.select(".product-price__i.product-price__i--bold").text();
//                        System.out.println("Price : "+price);
//
//                        newCell.setCellValue(price);
//                        Elements elements = div.select(".product-properties__i ");
//
//                        for (Element element : elements) {
//
//                            String elementLabel = element.select("label").text();
//                            Cell headerCell1  = header.createCell(countCell);
//                            headerCell1.setCellValue(elementLabel);
//
//                            String elementSpan = element.select("span").first().text();
//                            Cell newCell1  = newRow.createCell(countCell);
//                            newCell1.setCellValue(elementSpan);
//
//                            System.out.println(elementLabel + " : " + elementSpan);
//                            countCell++;
//                        }
//                    }
//                    wb.write(os);
//                }
//            }
//            currentPage += 5;
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//}