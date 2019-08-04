/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//Chỉ được xem điểm của chính mình
package SinhVien;

import static GiaoVu_SinhVien.Login_Logout.Menu;
import static GiaoVu_SinhVien.XuLyData.ReadFileCSV;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Win10
 */
public class XemBangDiemCaNhan {
    public static void SinhVienXemBangDiem(String mssv){
        Scanner sc = new Scanner(System.in);
        ArrayList <String> listDiemSV = new ArrayList<>();

        
        ArrayList <String> listSV_MH = new ArrayList<>();
        Path des = Paths.get(System.getProperty("user.dir"));
        String file = des.getParent().toString() + "\\Data\\Diem\\";
        File dir = new File(file);
        if(!dir.exists() || dir.list().length == 0){
            System.out.println("Chưa có bảng điểm");
            Menu();
        }
        File[] files = dir.listFiles();
        int stt = 0;
        for(File f: files){
//            Lấy các file kết thúc với phần mở rộng .csv
            if(f.toString().endsWith(".csv")){
                Map<String, String> map = ReadFileCSV(f.toString());
                if(map.containsKey(mssv)){
                    stt++;
                    String name = f.getName().substring(0, f.getName().lastIndexOf('.'));
//                    System.out.println(name);
                    listDiemSV.add(stt + ") " + name + " --> " + map.get(mssv));
                    listSV_MH.add(f.toString());
                }   
            }
        }
        if(listSV_MH.isEmpty()){
            System.out.println("Sinh Viên có MSSV: " + mssv + " chưa có điểm thi");
        }
        else
            listDiemSV.forEach((item)->{System.out.println(item);});
    }
}
