/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GiaoVu;

import static GiaoVu_SinhVien.XuLyData.FormatDSLop;
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
public class XemDSLop {
    public static void GiaoVuXemDsLop(){
        Path des = Paths.get(System.getProperty("user.dir"));
//        System.out.println(des);
        des = Paths.get(des.getParent() + "/Data/Lop/");
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
                } catch (Exception e) {
                    System.out.println("Bạn nhập không phải số --> " + e);
                    continue;
                }
                chon = chon - 1;
                if(chon < listFile.size() && chon >= 0){
                    path = listFile.get(chon);
                    break;
                }
            }
            FormatDSLop(path);
        }
        
    }
}