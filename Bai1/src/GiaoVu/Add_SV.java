/*
    Mục Đích Hiện Thị Danh Sách Lớp Cho Người Dùng Chọn
    Nhập Vào Chuỗi Thông Tin SV Cần Thêm Vào
    Ghi Xuống File
 */
package GiaoVu;

import static GiaoVu.DKHP.title;
import static GiaoVu.XemDSLop.Chon_Lop;
import static GiaoVu.XemDSLop.Lop;
import static GiaoVu_SinhVien.Login_Logout.Menu;
import static GiaoVu_SinhVien.XuLyData.FormatFile;
import static GiaoVu_SinhVien.XuLyData.HashMapToFile;
import static GiaoVu_SinhVien.XuLyData.ReadFileCSV;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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
public class Add_SV {
    static String title;
    static final int LOP = 1;
    public static void GiaoVuAddSv(){
        /* Đầu tiên giáo vụ có 2 lựa chọn là:
            Chuyển 1 sinh viên từ lớp này sang lớp khác --> Nhập mã sinh viên --> Chọn lớp cũ --> Chọn lớp mới
            Thêm mới hoàn toàn 1 sv vào 1 lớp cố định --> Nhập Thông tin SV theo mẫu vào 1 file --> Chọn tên Lớp cần thêm sv.
            Sau khi đã lựa chọn thì Add tự động sv và những môn thuộc thời biểu của lớp.
        */
//        System.out.println(LOP);
        System.out.println("1. Chuyển lớp cho Sinh viên");
        System.out.println("2. Thêm mới Danh Sách Sinh Viên vào lớp");
        Scanner sc = new Scanner(System.in);
        int chon;
        System.out.print("Nhập lựa chọn:");
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
            System.out.print("Chọn lớp Sinh Viên muốn chuyển đến \n");

            String newLop = Lop(LOP);

            if((map_oldLop.containsKey(mssv)) && !("title".equals(mssv))){
                String row = map_oldLop.get(mssv);
                try(PrintWriter pw = new PrintWriter(new FileWriter(newLop, true))) {
                    pw.println();
                    pw.append(row);
                    map_oldLop.remove(mssv);
                    System.out.printf("Đã chuyển chuyển %s từ %s sang %s \n", mssv, Paths.get(oldLop).getFileName(), Paths.get(newLop).getFileName());
                } catch (Exception e) {
                }
            }
            else{
                System.out.println(mssv + " Không tồn tại");
            }
            title = map_oldLop.get("title");
            map_oldLop.remove("title");

            HashMapToFile(map_oldLop, title, oldLop);      
        }
        else if(chon == 2){
//                String oldLop = Chon_Lop(); // Trả về 1 đường dẫn file lớp đã chọn
            System.out.print("Chọn lớp muốn thêm Sinh vào \n");
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
            System.out.println("Tạm Dừng để thêm nội dung vào file --> Y/N tiếp tục");
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
