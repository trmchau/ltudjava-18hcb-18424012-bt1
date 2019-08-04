/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
// Đăng ký học phần _ Lưu sanh sách riêng cho từng môn học
package GiaoVu;

import static GiaoVu.XemDSLop.Lop;
import static GiaoVu_SinhVien.Login_Logout.Menu;
import static GiaoVu_SinhVien.XuLyData.HashMapToFile;
import static GiaoVu_SinhVien.XuLyData.ReadFileCSV;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Win10
 */
public class DKHP {
    static String title;
    static final int LOP = 2;
    public static void DangKyHocPhan(){
        System.out.println("1. Cho Phép Sinh Viên Hủy Học Phần");
        System.out.println("2. Thêm mới Danh Sách Sinh Viên Tham Gia Học Phần");
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Nhập lựa chọn: ");
        int chon;
        while(true){
            try {
                chon = sc.nextInt();
                break;
            } catch (Exception e) {
                System.out.print("Nhâp lại: ");
            }
        }
        
        if(chon == 1){
            System.out.print("Nhập Mã Sinh Viên: ");
            String mssv = sc.next();
            System.out.print("Chọn lớp Sinh Viên đang học \n");
            String oldLop = Lop(LOP); // Trả về 1 đường dẫn file lớp đã chọn

            Map<String, String> map_oldLop = ReadFileCSV(oldLop);
            title = map_oldLop.get("title");
            map_oldLop.remove("title");
            if((map_oldLop.containsKey(mssv)) && !("title".equals(mssv))){
                map_oldLop.remove(mssv);
                HashMapToFile(map_oldLop, title, oldLop);
            }
            else{
                System.out.println(mssv + " Không tồn tại");
            }     
        }
        else if(chon == 2){
//                String oldLop = Chon_Lop(); // Trả về 1 đường dẫn file lớp đã chọn
            String newLop = Lop(LOP);

            System.out.println("Mở file theo đường dẫn sau để nhập liệu theo cấu trúc tiêu đề");
            Path des = Paths.get(System.getProperty("user.dir"));

            des = Paths.get(des.getParent() + "/Data/template.csv");
            File createFile = new File(des.toString());
            if(!createFile.exists())
                createFile.mkdirs();
            Map<String, String> map_newLop = ReadFileCSV(newLop);
            title = map_newLop.get("title");
            String template = des.toString();
            System.out.println(template);
            File f = new File(template);
            try {
                Files.copy(Paths.get(newLop), Paths.get(template), StandardCopyOption.REPLACE_EXISTING);
                try (PrintWriter pw = new PrintWriter(new FileWriter(template))) {
                    pw.println(title);
                }
            } catch (IOException e) {
                System.out.println("Không tạo được file template");
            }
            System.out.print("Tạm Dừng để thêm nội dung vào file --> Y/N tiếp tục");
            String tamDung = sc.next();
            if(tamDung.equalsIgnoreCase("Y")){
                Map<String, String> map_oldLop = ReadFileCSV(template);
                map_newLop.putAll(map_oldLop);
                System.out.printf("Đã thêm sinh viên từ %s sang %s \n", Paths.get(template).getFileName(), Paths.get(newLop).getFileName());
                HashMapToFile(map_newLop, title, newLop);
            }else
                Menu();
        }
            
    }
}

