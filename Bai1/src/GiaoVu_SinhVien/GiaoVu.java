/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
// Tham khảo
// https://dzone.com/articles/read-utf-8-file-java
//Đăng nhập / Đăng xuất
//Đổi mặt khẩu
package GiaoVu_SinhVien;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;


/**
 *
 * @author Win10
 */

public class GiaoVu {
    public static void main(String []args){
        System.out.println("Hello Giáo Vụ");
        System.out.print("Nhập đường dẫn file input: ");
//        String file = "../Data/17HCB.csv";
        Scanner in = new Scanner(System.in);
        String file = in.nextLine();
        ReadFileCSV(file);
    }
    public static void StringFormat(String []arr){
        for(int i = 0; i < arr.length; i++)
            arr[i] = arr[i].trim();
        System.out.printf("%s \t %-8s %-18s %-10s %-10s\n", arr[0], arr[1], arr[2], arr[3], arr[4]);
    }
    public static void ReadFileCSV (String file){
        try {
            File f = new File(file);
            FileReader fr = new FileReader(f);
            //Lấy đường dẫn thư mục gốc Java Project
            String path = System.getProperty("user.dir");
            System.out.println("Java Project: " + path);
            String code = fr.getEncoding();
            System.out.println(code);
//            FileInputStream fis = new FileInputStream(f);
//            InputStreamReader input = new InputStreamReader(fis, "UTF8");
            BufferedReader br = new BufferedReader(fr);
            String line = null;
            while((line = br.readLine())!= null){
                String Str = new String(line.getBytes(), code);
                String []arrItem = Str.split(",");
//                for(String item: arrItem )
//                {
//                    System.out.print(item + "\t");
//                }
                StringFormat(arrItem);
//                System.out.println();
                
            }
        }catch (Exception e) {
            System.out.println(e);
        }
    }
}
