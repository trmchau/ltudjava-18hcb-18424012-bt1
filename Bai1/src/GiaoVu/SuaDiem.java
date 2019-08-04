/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//Sửa điểm 1 Sinh Viên
package GiaoVu;

import static GiaoVu_SinhVien.XuLyData.HashMapToFile;
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
public class SuaDiem {
    static String path = "";
    static String newDiem = "";
    static String mssv = "";
    public static void GiaoVuSuaDiem(String s){
        mssv = s;
        path = Chon_File_Diem();
        Map<String, String> map_Lop = ReadFileCSV(path);
//        for(String key: map_Lop.keySet())
//            System.out.println(map_Lop.get(key));
        Scanner sc = new Scanner(System.in);
        String title = "";
        System.out.print("Nhập Điểm theo định dạng Giữa kỳ, Cuối kỳ, Điểm khác, Điểm tổng --> VD: 9,9,9,9 \n");
        System.out.print("Nhập Điểm: ");
        newDiem = sc.nextLine().replaceAll("\\s+", "");
        
//        System.out.println(newDiem);
        String []arrDiem = newDiem.split(",");
        if(arrDiem.length == 4){
            while(true){
                try {
                    float ktra = Float.parseFloat(arrDiem[0]);
//                    System.out.println(ktra);
                    ktra = Float.parseFloat(arrDiem[1]);
//                    System.out.println(ktra);
                    ktra = Float.parseFloat(arrDiem[2]);
//                    System.out.println(ktra);
                    ktra = Float.parseFloat(arrDiem[3]);
//                    System.out.println(ktra);
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Bạn nhập điểm không hợp lệ");
                }

            }
            
            if(map_Lop.containsKey("title")){
                title = map_Lop.get("title");
                map_Lop.remove("title");
            }

            String row = map_Lop.get(mssv);
            String [] arr = row.split(",", 4);
            row = row.replace(arr[3], newDiem);
            map_Lop.put(mssv, row);
            HashMapToFile(map_Lop, title, path);
        }
    }
    public static String Chon_File_Diem(){
        Scanner sc = new Scanner(System.in);
        ArrayList <String> listDiemSV = new ArrayList<>();

        
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
        {   
            /* Hiển thị Ds điểm các môn --> chọn môn học cần sửa điểm
                Nhập điểm mới cho Sinh Viên
            */
            int chon;
          
            while(true){
                listDiemSV.forEach((item) -> { System.out.println(item);});
                try {
                    System.out.print("Nhập lựa chọn: ");
                    chon = Integer.parseInt(sc.next());
                } catch (NumberFormatException e) {
                    System.out.println("Bạn nhập không phải số --> " + e);
                    continue;
                }
                chon = chon - 1;
                if(chon < listSV_MH.size() && chon >= 0){
                    path = listSV_MH.get(chon);
//                    System.out.println(path);
                    break;
                }
                else{
                    listDiemSV.forEach((item) -> { System.out.println(item);});
                    System.out.print("Nhập lựa chọn: ");
                }       
            }
            
        }  
        return path;
    }
}
