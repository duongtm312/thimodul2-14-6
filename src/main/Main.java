package main;

import controllers.MenuProduct;

public class Main {
    public static void main(String[] args) {
        MenuProduct menuProduct = new MenuProduct();
        while (true){
            menuProduct.menu();
        }
    }
}
