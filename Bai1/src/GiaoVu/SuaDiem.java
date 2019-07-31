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
public class SuaDiem {
    protected static String MSSV;
    public static void GiaoVuSuaDiem(){
        ArrayList <String> listDiemSV = new ArrayList<>();
        System.out.print("Nhập MSSV cần sửa Điểm: ");
        Scanner sc = new Scanner(System.in);
        MSSV = sc.nextLine();
        
        ArrayList <String> listSV_MH = new ArrayList<>();
        Path des = Paths.get(System.getProperty("user.dir"));
        String file = des.getParent().toString() + "\\Data\\Diem\\";
        File dir = new File(file);
        File[] files = dir.listFiles();
        int stt = 0;
        for(File f: files){
//            Lấy các file kết thúc với phần mở rộng .csv
            if(f.toString().endsWith(".csv")){
                Map<String, String> map = ReadFileCSV(f.toString());
                if(map.containsKey(MSSV)){
                    stt++;
                    listDiemSV.add(map.get(MSSV));
                    listSV_MH.add(f.toString());
                }   
            }
        }
        if(listSV_MH.isEmpty())
            System.out.println("Sinh Viên có MSSV: " + MSSV + " chưa có điểm thi");
        else
        {
//            listSV_MH.forEach((item) -> { System.out.println(item);});
            listDiemSV.forEach((item) -> { System.out.println(item);});
            
        }
    }
}
