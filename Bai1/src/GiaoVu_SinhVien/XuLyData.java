/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GiaoVu_SinhVien;

import static GiaoVu_SinhVien.Login_Logout.Menu;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Win10
 */
public class XuLyData {
    public static void FormatDSLop(String file){
        Map<String, String> map = (Map<String, String>)ReadFileCSV(file);
        String title = map.get("title");
        map.remove("title");
//            [﻿STT , MSSV , Họ tên , Giới tính , CMND]
        String []arrItem = title.split(",");
        System.out.printf("%4s %-10s %-15s %8s %-8s \n",arrItem[0], arrItem[1],arrItem[2],arrItem[3],arrItem[4]);
        for(String key: map.keySet())
        {
            String [] valIem = map.get(key).split(",");
            System.out.printf("%-4s %-10s %-16s %8s %-8s \n",valIem[0], valIem[1],valIem[2],valIem[3],valIem[4]);
        }
    }
    public static Map<String, String> ReadFileCSV(String file){
        Map<String, String> map = new HashMap<>();
//        System.out.print("Nhập tên file input: ");
        
//        Scanner in = new Scanner(System.in);
//        String file = "../Data/" + in.nextLine();
        System.out.println("Xem file: " + file);
        File f = new File(file);
        
        //Lấy đường dẫn thư mục gốc Java Project
//            String path = System.getProperty("user.dir");
//            System.out.println("Java Project: " + path);
        try {
            FileReader fr = new FileReader(f);
            String code = fr.getEncoding();
//            System.out.println(code);
//            FileInputStream fis = new FileInputStream(f);
//            InputStreamReader input = new InputStreamReader(fis, "UTF8");
            try(BufferedReader br = new BufferedReader(fr)){
                String line;

//                Lấy ra dòng đầu tiên trong file làm tiêu đề cột
                String title = br.readLine();
//                System.out.println(title);
                map.put("title", title);
                
               
                
                while((line = br.readLine())!= null){

//                    String Str = new String(line.getBytes(), code);
//                    String []arrItem = Str.split(",");
                    String []arrItem = line.split(",");
                    //Lấy MSSV làm key
                    String key = arrItem[1];
//                    Systegiaom.out.println(list.get(1));
//                    System.out.println(list);
                    map.put(key, line);
                }
                br.close();
//                for(Map.Entry<String, List<String>> entry: map.entrySet())
//                    System.out.println(entry.getValue());
//                map = new TreeMap<>(list);

                
//                map.forEach((key, value) -> System.out.println(key + "\t" + value));
//                Chạy thử Kiểm trả hàm HashMapToFile
//                HashMapToFile(map, "F:\\HK\\III\\Standard\\My Homework\\Java\\Bai Tap\\Data\\temp.csv");
            }catch(IOException e){}

        }catch (IOException e) {
            System.out.println(e);
            System.exit(0);
        }
        TreeMap<String, String> tree = new TreeMap<>(map);
        return tree;
    }
    public static void HashMapToFile(Map<String, String> map, String title, String file){
        if(file == null)
            return;
        File f = new File(file);
//        System.out.println("Chức năng HashMapToFile");
        try(PrintWriter pw = new PrintWriter(new FileWriter(file))) {
            pw.println(title);
            for(String key: map.keySet()){
                pw.println(map.get(key));
            }  
        } catch (Exception e) {
            System.out.println("Không cập nhật được dữ liệu ==> Lỗi");
            System.exit(0);
        }
        
    }
    public static void PrintCSV(String file){
//        String file = "F:\\HK\\III\\Standard\\My Homework\\Java\\ltudjava-18hcb-18424012-bt1\\Data\\Lop\\Diem.csv";
        try {
            File f = new File(file);
            FileReader fr = new FileReader(f);
            String code = fr.getEncoding();
            Map<String, String> map;
            try (BufferedReader br = new BufferedReader(fr)) {
                String line;
                //Lấy ra dòng đầu tiên trong file làm tiêu đề cột
                String title = br.readLine();
                System.out.println(title);
                while((line = br.readLine())!= null)
                    System.out.println(line);
  
            }catch (IOException e) {
            
            System.out.println(e);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(XuLyData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void DoiGiaTriCot(int cot, String oldValue, String newValue){
        
    }
    public static String XuLyPathImportFile(){
        String path = null;
        String s;
        System.out.println("Nhập đường dẫn đến Thư Mục chứa file cần import: ");
        Scanner sc = new Scanner(System.in);
        s = sc.nextLine();
        if(s.isEmpty())
            Menu();
        
        File dir = new File(s);
        if(dir.exists()){
            File[] files = dir.listFiles();
            int stt = 0;
            ArrayList<String> listFile = new ArrayList<>();
            if(files.length == 0){
                System.out.println("Thư mục rỗng --> Chọn lại chức năng");
            }

            for(File f: files){            
                if(f.toString().endsWith(".csv")){
                    listFile.add(stt, f.toString());
                    stt++;
                    System.out.printf("%d. %s", stt, f.getName());
                    System.out.println();
                }
            }
    //        Nếu trong thư mục Lớp tồn tại file thì mới xét

            if(!listFile.isEmpty()){         
                while(true){
                    System.out.print("Chọn số STT của tên Tập Tin bạn cần: ");
                    int chon;
                    // Lỗi nhập vào là ko phải số.
                    try{
                        chon = Integer.parseInt(sc.nextLine());
                    }catch(NumberFormatException ex){
                        System.out.println("Bạn nhập không phải số -->" + ex);
                        continue;
                    } 
                    chon = chon - 1;
                    if(chon < listFile.size() && chon >= 0){
                        path = listFile.get(chon);
                        System.out.println(path);
                        break;
                    }
                }
            }
//            Thư mục không có tập tin cần import gọi Hàm Menu();
            else{
                System.out.println("Thư mục bạn chọn không có file để import --> Chọn lại chức năng; Nếu thoát chương trình gõ 'exit'");
//                Dừng lại màn hình
                if("exit".equals(sc.next()))
                    System.exit(0);
                Menu();
            }
                
        }
//        Đường dẫn nhập không tồn tại trên hệ thống gọi Hàm Menu để chọn lại chức năng.
        else{
            System.out.println("Đường dẫn bạn nhập không tồn tại --> Chọn lại chức năng; Nếu thoát chương trình gõ 'exit'");
            if("exit".equals(sc.next()))
                System.exit(0);
            Menu();
        }
        return path;
    }
    public static String SelectFile_DanhSach(Path des){
//        Biến path là nơi lưu đường dẫn file được chọn và trả về cho hàm
        String path = null;
        File dir = new File(des.toString());
        File[] files = dir.listFiles();
        int stt = 0;
        
//        Dùng ArrayList lưu Path các file trong thư mục
        ArrayList<String> listFile = new ArrayList<>();
        for(File f: files){
//            Lấy các file kết thúc với phần mở rộng .csv
            if(f.toString().endsWith(".csv")){
                listFile.add(stt, f.toString());
                stt++;
//                Tách phần tên và phần mở rộng (ko cần thiết, làm thêm cho nhớ
                String []name = f.getName().split("\\.");
                System.out.printf("%d. %s", stt, name[0]);
                //System.out.printf("%d. %s", stt, f);
                System.out.println();
            }
        }
//        Nếu trong thư mục Lớp tồn tại file thì mới xét
        
        if(listFile.size() > 0){
            System.out.print("Chọn số STT của tên Lớp cần xem: ");
            int chon;
            while(true){
                Scanner sc = new Scanner(System.in);
                try {
                    chon = Integer.parseInt(sc.nextLine());
                } catch (NumberFormatException e) {
                    System.out.print("Bạn nhập không phải số --> " + e + " --> Nhập lại: ");
                    continue;
                }
                chon = chon - 1;
                if(chon < listFile.size() && chon >= 0){
                    path = listFile.get(chon);
                    break;
                }
                else
                    System.out.print("Số bạn nhập không thuộc miền cho phép --> Nhập lại: ");
            }
        }
        return path;
    }
}
