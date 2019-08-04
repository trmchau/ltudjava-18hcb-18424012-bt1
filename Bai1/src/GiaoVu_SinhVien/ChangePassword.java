/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GiaoVu_SinhVien;

import static GiaoVu_SinhVien.XuLyData.HashMapToFile;
import static GiaoVu_SinhVien.XuLyData.ReadFileCSV;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Win10
 */
public class ChangePassword {
    public static void NewPassword(String user){
        System.out.print("New Password: ");
        Scanner sc = new Scanner(System.in);
        String newPass = sc.nextLine();
        while(newPass.trim().equals("")){
            System.out.print("Không để trống --> New Pass: ");
            newPass = sc.nextLine();
        }
        Path des = Paths.get(System.getProperty("user.dir"));
        String file = des + "/User_pass.csv";
        Map<String, String> map = ReadFileCSV(file);
        String title  = map.get("title");
        map.remove("title");
//        System.out.println(title);
        String data = map.get(user);
        String []arrItem = data.split(",");
        arrItem[2] = newPass;
        String value = arrItem[0] + "," + arrItem[1] + "," + arrItem[2];
        map.replace(user, value);
        HashMapToFile(map, title, file);
    }
}
