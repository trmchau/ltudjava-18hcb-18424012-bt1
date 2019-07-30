/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GiaoVu;

import static GiaoVu_SinhVien.XuLyData.ReadFileCSV;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Win10
 */
public class XemTKB {
    public static void GiaoVuXemTKB(){
        Path des = Paths.get(System.getProperty("user.dir"));
//        System.out.println(des);
        des = Paths.get(des.getParent() + "/Data/TKB/");
//        System.out.println(des);
        File dir = new File(des.toString());
        File[] files = dir.listFiles();
        int stt = 0;
        ArrayList<String> listFile = new ArrayList<>();
        for(File f: files){
            
            if(f.toString().endsWith(".csv")){
                listFile.add(stt, f.toString());
                stt++;
                String []name = f.getName().split("\\.");
                System.out.printf("%d. %s", stt, name[0]);
                //System.out.printf("%d. %s", stt, f);
                System.out.println();
            }
        }
//        Nếu trong thư mục Lớp tồn tại file thì mới xét
        String path = null;
        if(listFile.size() > 0){
            System.out.print("Chọn số STT của tên Lớp cần xem: ");
            int chon;
            while(true){
                Scanner sc = new Scanner(System.in);
                try {
                    chon = sc.nextInt();
                    chon = chon - 1;
                } catch (Exception e) {
                    System.out.println("Bạn nhập không phải số --> " + e);
                    continue;
                }
                if(chon < listFile.size() && chon >= 0){
                    path = listFile.get(chon);
                    break;
                }
                else
                    System.out.print("Nhập số có trong Danh Sách: ");
            }
            Map<String, String> map = (Map<String, String>)ReadFileCSV(path);
            String title = map.get("title");
            map.remove("title");
//            ﻿STT ,Mã môn ,Tên môn ,Phòng học
            String []arrItem = title.split(",");
            System.out.printf("%4s %-10s %-27s %8s",arrItem[0], arrItem[1],arrItem[2],arrItem[3]);
            System.out.println();
            map.keySet().forEach((key) -> {
                String []valItem = map.get(key).split(",");

                System.out.printf("%-4s %-10s %-30s %-8s \n",valItem[0], valItem[1],valItem[2],valItem[3]);
            }); 
//            System.out.println(title);
//            
//            map.keySet().forEach((key) -> {
//                System.out.println(map.get(key));
//            });       
        }
        
    }
}
