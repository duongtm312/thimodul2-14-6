package controllers;

import io.ReaderAndWriter;
import model.Product;
import sort.SortbyAscending;
import validate.ValiDateProduct;

import java.util.ArrayList;
import java.util.Scanner;

public class MenuProduct {
    Scanner sc = new Scanner(System.in);
    ArrayList<Product> products = new ArrayList<>();
    ValiDateProduct valiDateProduct = new ValiDateProduct();
    ReaderAndWriter readerAndWriter= new ReaderAndWriter();

    public void menu() {
        System.out.println("----Chương trình quản lý sản phẩm");
        System.out.println("1.| Xem danh sách");
        System.out.println("2.| Thêm mới");
        System.out.println("3.| Cập nhật");
        System.out.println("4.| Xóa");
        System.out.println("5.| Sắp xếp");
        System.out.println("6.| Tìm kiếm sản phẩm giá đắt nhất");
        System.out.println("7.| Đọc từ File");
        System.out.println("8.| Ghi vào file");
        System.out.println("9.| Thoát!!!");
        int choice = -1;
        try {
            System.out.println("Chọn chức năng");
            ;
            choice = Integer.parseInt(sc.nextLine());
            if (choice < 0 || choice > 9) {
                throw new Exception();
            }
        } catch (Exception e) {
            System.err.println("Không có lựa chọn, vui lòng chọn lại");
        }

        switch (choice) {
            case 1:
                displayProduct(products);
                break;
            case 2:
                addProduct(createProduct());
                break;
            case 3:
                editProduct();
                break;
            case 4:
                deleteProduct();
                break;
            case 5:
                products.sort(new SortbyAscending());
                System.out.println("Sắp xếp thành công");
                break;
            case 6:
                searchExpensiveProduct();
                break;
            case 7:
               readFile();
                break;
            case 8:
               writeFile();
                break;
            case 9:
                System.exit(0);
                break;
        }
    }

    public void displayProduct(ArrayList<Product> products) {
        for (int i = 0; i < products.size(); i++) {
            if ((i + 1) % 5 == 0) {
                sc.nextLine();
                System.out.println(products.get(i));
            } else
                System.out.println(products.get(i));
        }
    }

    public Product createProduct() {
        int Id = valiDateProduct.valiDateId(products);
        String name = valiDateProduct.validateName();
        double price = valiDateProduct.valiDatePrice();
        int amount = valiDateProduct.valiDateAmount();
        System.out.println("Enter describe: ");
        String describe = sc.nextLine();
        return new Product(Id, name, price, amount, describe);
    }
    public Product upDateProduct(int id) {
        String name = valiDateProduct.validateName();
        double price = valiDateProduct.valiDatePrice();
        int amount = valiDateProduct.valiDateAmount();
        System.out.println("Enter describe: ");
        String describe = sc.nextLine();
        return new Product(id, name, price, amount, describe);
    }

    public void addProduct(Product product) {
        products.add(product);
        System.out.println("Tạo sản phẩm thành công");
    }

    public void editProduct() {
        while (true) {
            try {
                System.out.println("Nhập mã sản phẩm: ");
                int id = Integer.parseInt(sc.nextLine());
                int index = valiDateProduct.getIndex(id, products);
                if (index != -1) {
                    products.set(index, upDateProduct(id));
                    return;
                } else {
                    System.out.println("Mã sản không tồn tại");
                    System.out.println("Nhập y để tiếp tục");
                    String yes = sc.nextLine();
                    if (!yes.equals("y")){
                        break;
                    }

                }
            } catch (Exception e) {
                System.err.println("Bạn nhập sai định mã sản phẩm, mời nhập lại");
            }
        }

    }

    public void deleteProduct() {
        while (true) {
            try {
                System.out.println("Nhập mã sản phẩm: ");
                int Id = Integer.parseInt(sc.nextLine());
                int index = valiDateProduct.getIndex(Id, products);
                if (index == -1) {
                    System.out.println("Mã sản phẩm không tồn tại");
                    System.out.println("Nhập y để tiếp tục");
                    String yes = sc.nextLine();
                    if (!yes.equals("y")){
                        break;
                    }
                } else {
                    System.out.println("Nhập y để xóa sản phẩm: " + products.get(index).getName());
                    String yes = sc.nextLine();
                    if (yes.equals("y")){
                        products.remove(index);
                        break;
                    }else {
                        break;
                    }

                }
            } catch (Exception e) {
                System.err.println("Mời nhập lại");
            }
        }
    }
    public void searchExpensiveProduct (){
        double max = products.get(0).getPrice();
        int index = 0;
        for (int i = 1; i < products.size(); i++) {
            if (products.get(i).getPrice() > max ) {
                max = products.get(i).getPrice();
                index = i;
            }
        }
        System.out.println("Sản phẩm có giá đắt nhất là : " + products.get(index).getName() + " với giá: " + max);
    }
    public void readFile(){
        System.out.println("Đọc file sẽ xóa toàn bộ giữ liệu hiện tại");
        System.out.println("Nhập y để tiếp tục");
        String yes = sc.nextLine();
        if (yes.equals("y")){
            products =readerAndWriter.read();
            System.out.println("Đọc file thành công");
        }

    }
    public void writeFile(){
        System.out.println("Bạn sẽ thực hiện trức năng ghi file");
        System.out.println("Nhập y để tiếp tục");
        String yes = sc.nextLine();
        if (yes.equals("y")){
            readerAndWriter.write(products);
            System.out.println("Ghi file thành công");
        }
    }
}
