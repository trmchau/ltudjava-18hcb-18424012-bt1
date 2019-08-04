/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GiaoVu;

import static GiaoVu_SinhVien.Login_Logout.Menu;
import static GiaoVu_SinhVien.XuLyData.FormatDSLop;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Win10
 */
public class XemDSLop {
    static String path = null;
    public static String Lop(int loai){
        if(loai == 1)
            System.out.println("Danh Sách Lớp - Sinh Viên ");
        if(loai == 2)
            System.out.println("Danh Sách Lớp - Môn Học");
        Path des = Paths.get(System.getProperty("user.dir"));
//        System.out.println(des);
        des = Paths.get(des.getParent() + "/Data/Lop/");
//        System.out.println(des);
        File dir = new File(des.toString());
        if(!dir.exists() || dir.list().length == 0){
            System.out.println("Chưa có bảng điểm");
            Menu();
        }
        File[] files = dir.listFiles();
        
        ArrayList<String> listFile = new ArrayList<>();
        ArrayList<String> listName = new ArrayList<>();
        for(File f: files){
            if(f.toString().endsWith(".csv")){
                String []name = f.getName().split("\\.");
//                System.out.println(name[0]);
                // Tên lớp
                if((loai == 1) && !(name[0].contains("-"))){
                    listName.add(name[0]);
                    listFile.add(f.toString());
                }
                // Tên lớp Môn học
                if((loai == 2) && (name[0].contains("-"))){
                    listName.add(name[0]);
                    listFile.add(f.toString());
                }
                      
                if(loai == 3){
                    listName.add(name[0]);
                    listFile.add(f.toString());
                   
                }
            }
        }
            
        for(int i = 0; i < listName.size(); i++){
            System.out.printf("%d) %s",i + 1, listName.get(i));
            //System.out.printf("%d. %s", stt, f);
            System.out.println();
        }
//        Nếu trong thư mục Lớp tồn tại file thì mới xét
        
        if(listFile.size() > 0){
            System.out.print("Nhập lựa chọn: ");
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
                else
                    System.out.print("Nhập lựa chọn: ");
            }
            
        }else
            System.out.println("Danh Sách Lớp NULL");
        return path;
    }
    public static String Chon_Lop(){
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
        
        if(listFile.size() > 0){
            System.out.print("Nhập lựa chọn: ");
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
                else
                    System.out.print("Nhập lựa chọn: ");
            }
            
        }
        return path;
    }
    public static void GiaoVuXemDsLop(){
        String path = Chon_Lop();
        FormatDSLop(path);
    }
}