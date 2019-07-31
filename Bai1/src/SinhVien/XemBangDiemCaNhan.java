/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//Chỉ được xem điểm của chính mình
package SinhVien;

import static GiaoVu_SinhVien.XuLyData.ReadFileCSV;
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
public class XemBangDiemCaNhan {
    public static void SinhVienXemBangDiem(String MSSV){

        HashMap <String, String> mapDiemSV = new HashMap<>();
        
        Path des = Paths.get(System.getProperty("user.dir"));
        String file = des.getParent().toString() + "\\Data\\Diem\\";
        File dir = new File(file);
        File[] files = dir.listFiles();
        for(File f: files){
//            Lấy các file kết thúc với phần mở rộng .csv
            if(f.toString().endsWith(".csv")){
                Map<String, String> map = ReadFileCSV(f.toString());
                if(map.containsKey(MSSV)){              
                    mapDiemSV.put(f.toString(), map.get(MSSV));
                }   
            }
        }
        if(mapDiemSV.isEmpty())
            System.out.println("Sinh Viên có MSSV: " + MSSV + " chưa có điểm thi");
        else
        {
            int stt = 0;
            // key is Môn Học, value is Số Điểm
            for(String key: mapDiemSV.keySet()){
                String []MH = key.split("\\.");
                String monhoc = MH[0].substring(MH[0].length() - 6);
                for(String item: MH){
                    System.out.println(item);

                }
                System.out.println(monhoc + "-->" + mapDiemSV.get(key));
            }
        }
    }    
}
