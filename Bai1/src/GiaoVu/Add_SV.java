/*
    Mục Đích Hiện Thị Danh Sách Lớp Cho Người Dùng Chọn
    Nhập Vào Chuỗi Thông Tin SV Cần Thêm Vào
    Ghi Xuống File
 */
package GiaoVu;

import static GiaoVu_SinhVien.XuLyData.HashMapToFile;
import static GiaoVu_SinhVien.XuLyData.ReadFileCSV;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 *
 * @author Win10
 */
public class Add_SV {
    public static void GiaoVuAddSv() throws FileNotFoundException, UnsupportedEncodingException{
        ArrayList <File> files_csv = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        System.out.print("Thêm SV vào Hệ Thống --> VD: Thêm SV  vào lớp 17HCB ");
        System.out.print("1742006 – Trần Kiều X – 987612345 \n");
        
        Path des = Paths.get(System.getProperty("user.dir"));
        String file = des.getParent().toString() + "\\Data\\Lop\\";
        File dir = new File(file);
//        Hiển thị Danh sách lớp
        File[] files = dir.listFiles();
        for(File f: files){
//            Lọc các file kết thúc với phần mở rộng .csv
            if(f.toString().endsWith(".csv")){
                files_csv.add(f);
            }
        }
        int chon = 0;
        if(files_csv.isEmpty()){
            return;
        }else
        {
            int stt = 1;
            for(File lop: files_csv){
                String []arrLop = lop.getName().split("\\.");
                System.out.printf("%d. %s\n", stt++, arrLop[0]);
            }
            File f_chon;
            System.out.print("Chọn số stt lớp cần thêm: ");     
            while(true){
                
                try {
                    chon = Integer.parseInt(sc.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Bạn nhập không phải số --> Nhập lại: ");
                }
               
                if(chon <= files_csv.size() && chon > 0){
                    f_chon = files_csv.get(chon - 1);
                    break;
                }
                    
            }
//            Convert : 1742006 – Trần Kiều X – 987612345 --> 1, 1742006, Trần Kiều X, 987612345

            Map<String, String> map = ReadFileCSV(f_chon.toString());
            String title = map.get("title");
            map.remove("title");
            try(PrintWriter pw = new PrintWriter(new FileWriter(f_chon))) {
                pw.println(title);
                for(String key: map.keySet())
                    pw.println(map.get(key));
                System.out.print("Thêm SV ");
        
                String sv = sc.nextLine();
                sv = sv.replace("-", ",");

                sv = (map.size() + 1) + "," + sv;
                pw.println(sv);
            } catch (Exception e) {
                System.out.println("Không cập nhật được dữ liệu ==> Lỗi");
                System.exit(0);
            }
        }
    }
}
