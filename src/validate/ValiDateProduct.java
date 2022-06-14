package validate;

import model.Product;

import java.util.ArrayList;
import java.util.Scanner;

public class ValiDateProduct {
    Scanner sc = new Scanner(System.in);

    public int valiDateId (ArrayList<Product> products){
        while (true){
            try {
                System.out.println("Nhập mã sản phẩm: ");
                int Id = Integer.parseInt(sc.nextLine());
                if (getIndex(Id,products) != -1 || Id < 0) throw new Exception();
                else return Id;
            } catch (Exception e) {
                System.out.println("Nhập mã sản phẩm sai, vui lòng nhập lại ");
            }
        }
    }

    public int getIndex (int Id, ArrayList <Product> products){
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == Id)
                return i;
        }
        return -1;
    }

    public String validateName (){
        while (true){
            System.out.println("Nhập tên sản phẩm: ");
            String name = sc.nextLine();
            if ("".equals(name)) {
                System.out.println("Tên không thể để trống, vui lòng nhập lại");
                continue;
            } else
                return name;
        }
    }
    public double valiDatePrice (){
        while (true){
            try {
                System.out.println("Nhập giá sản phẩm: ");
                Float price = Float.parseFloat(sc.nextLine());
                if (price < 0 ) throw new Exception();
                else return price;
            } catch (Exception e) {
                System.out.println("Giá sản phẩm không thể nhỏ hơn 0, vui lòng nhập lại");
            }
        }
    }
    public int valiDateAmount (){
        while (true){
            try {
                System.out.println("Nhập số lượng: ");
                int amount = Integer.parseInt(sc.nextLine());
                if (amount < 0) throw new Exception();
                else return amount;
            }catch (Exception e) {
                System.out.println("Số lượng không thể nhỏ hơn 0, vui lòng nhập lại");
            }
        }
    }
}
