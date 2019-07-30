/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//Sửa điểm 1 Sinh Viên
package GiaoVu;

import static GiaoVu_SinhVien.XuLyData.HashMapToFile;
import static GiaoVu_SinhVien.XuLyData.ReadFileCSV;
import static GiaoVu_SinhVien.XuLyData.SelectFile_DanhSach;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 *
 * @author Win10
 */
public class SuaDiem {
    protected static String MSSV;
    public static void GiaoVuSuaDiem(){
        System.out.print("Nhập MSSV cần sửa Điểm: ");
        Scanner sc = new Scanner(System.in);
        MSSV = sc.nextLine();
        
        ArrayList <String> listSV_MH = new ArrayList<>();
        Path des = Paths.get(System.getProperty("user.dir"));
        String file = des.getParent().toString() + "\\Data\\Diem\\";
        File dir = new File(file);
        File[] files = dir.listFiles();
        int stt = 0;
        
//        Dùng ArrayList lưu Path các file trong thư mục
        ArrayList<String> listFile = new ArrayList<>();
        for(File f: files){
//            Lấy các file kết thúc với phần mở rộng .csv
            if(f.toString().endsWith(".csv")){
                Map<String, String> map = ReadFileCSV(f.toString());
                if(map.containsKey(MSSV)){
                    listSV_MH.add(f.toString());
                }   
                listFile.add(stt, f.toString());
                stt++;
//                Tách phần tên và phần mở rộng (ko cần thiết, làm thêm cho nhớ
                String []name = f.getName().split("\\.");
                System.out.printf("%d. %s", stt, name[0]);
                //System.out.printf("%d. %s", stt, f);
                System.out.println();
            }
        }
        if(listSV_MH.isEmpty())
            System.out.println("Sinh Viên có MSSV: " + MSSV + " chưa có điểm thi");
        else
        {
            listSV_MH.forEach((item) -> { System.out.println(item);});
        }
    }
}
