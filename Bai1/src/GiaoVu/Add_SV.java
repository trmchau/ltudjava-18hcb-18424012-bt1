/*
    Mục Đích Hiện Thị Danh Sách Lớp Cho Người Dùng Chọn
    Nhập Vào Chuỗi Thông Tin SV Cần Thêm Vào
    Ghi Xuống File
 */
package GiaoVu;

import static GiaoVu.XemDSLop.Chon_Lop;
import static GiaoVu_SinhVien.XuLyData.FormatFile;
import static GiaoVu_SinhVien.XuLyData.HashMapToFile;
import static GiaoVu_SinhVien.XuLyData.ReadFileCSV;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
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
    public static void GiaoVuAddSv(){
        /* Đầu tiên giáo vụ có 2 lựa chọn là:
            Chuyển 1 sinh viên từ lớp này sang lớp khác --> Nhập mã sinh viên --> Chọn lớp cũ --> Chọn lớp mới
            Thêm mới hoàn toàn 1 sv vào 1 lớp cố định --> Nhập Thông tin SV theo mẫu vào 1 file --> Chọn tên Lớp cần thêm sv.
            Sau khi đã lựa chọn thì Add tự động sv và những môn thuộc thời biểu của lớp.
        */
        System.out.println("1. Chuyển lớp cho Sinh viên");
        System.out.println("2. Thêm mới Danh Sách Sinh Viên vào lớp");
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print("Nhập lựa chọn: ");
            int chon = sc.nextInt();
            if(chon == 1){
                System.out.print("Nhập Mã Sinh Viên: ");
                String mssv = sc.next();
                System.out.print("Chọn lớp Sinh Viên đang học \n");
                String oldLop = Chon_Lop(); // Trả về 1 đường dẫn file lớp đã chọn
                
                Map<String, String> map_oldLop = ReadFileCSV(oldLop);
                System.out.print("Chọn lớp Sinh Viên muốn chuyển đến \n");
                String newLop = Chon_Lop();
                
                if((map_oldLop.containsKey(mssv)) && !("title".equals(mssv))){
                    String row = map_oldLop.get(mssv);
                    try(PrintWriter pw = new PrintWriter(new FileWriter(newLop, true))) {
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
                String newLop = Chon_Lop();
                
                System.out.println("Mở file theo đường dẫn sau để nhập liệu theo cấu trúc tiêu đề");
                Path des = Paths.get(System.getProperty("user.dir"));

                des = Paths.get(des.getParent() + "/Data/template.csv");
                        
                String template = des.toString();
                System.out.println(template);
                File f = new File(template);
                if(!f.exists()){
                    Files.copy(Paths.get(newLop), des, StandardCopyOption.REPLACE_EXISTING);
                    try(PrintWriter pw = new PrintWriter(new FileWriter(template))){
                        pw.println(title);
                    }
                }
                sc.next();
//                Map<String, String> map_oldLop = ReadFileCSV(template);
                try(PrintWriter pw = new PrintWriter(new FileWriter(newLop, true))) {
                    BufferedReader br = new BufferedReader(new FileReader(template));
                    String title = br.readLine(); //Lấy tiêu đề
                    String line;
                    while((line = br.readLine()) != null){
                        pw.println();
                        pw.append(line);  
                    }
                    if(br != null)
                        br.close();
                    System.out.printf("Đã thêm sinh viên từ %s sang %s \n", Paths.get(template).getFileName(), Paths.get(newLop).getFileName());
                } catch (Exception e) {
                }
                FormatFile(newLop);
            }
        } catch (Exception e) {
            
        }
    } 
}
