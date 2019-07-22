/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GiaoVu_SinhVien;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Win10
 */
public class XuLyData {
//
//    public static void FormatTTSV(String []arr){
//        for(int i = 0; i < arr.length; i++)
//            arr[i] = arr[i].trim();
//        System.out.printf("%s \t %-8s %-18s %-10s %-10s\n", arr[0], arr[1], arr[2], arr[3], arr[4]);
//    }
    public static Map<String, String> ReadFileCSV(String file){
//        System.out.print("Nhập tên file input: ");
        
//        Scanner in = new Scanner(System.in);
//        String file = "../Data/" + in.nextLine();
       
        try {
            File f = new File(file);
            FileReader fr = new FileReader(f);
            //Lấy đường dẫn thư mục gốc Java Project
//            String path = System.getProperty("user.dir");
//            System.out.println("Java Project: " + path);
            String code = fr.getEncoding();
//            System.out.println(code);
//            FileInputStream fis = new FileInputStream(f);
//            InputStreamReader input = new InputStreamReader(fis, "UTF8");
            BufferedReader br = new BufferedReader(fr);
            String line;
            
//            Lấy ra dòng đầu tiên trong file làm tiêu đề cột
            String title = br.readLine();
            System.out.println(title);
            HashMap<String, String> list = new HashMap<>();
            while((line = br.readLine())!= null){
                String Str = new String(line.getBytes(), code);
                String []arrItem = Str.split(",");
                StringBuilder value = new StringBuilder("");
                // Dùng HashMap
                for(int i = 2; i < arrItem.length; i++){
//                    Loại bỏ cột stt trong file, Ví dụ: Lấy MSSV là key cho HashMap
                    value.append(arrItem[i]);
                }
                list.put(arrItem[1], value.toString());
                // Dùng foreach duyệt phần tử mảng.
//                for(String item: arrItem )
//                {
//                    System.out.print(item + "\t");
//                }
//                StringFormat(arrItem);
//                System.out.println();
                
            }
            br.close();
            Map<String, String> map = new TreeMap<>(list);
            return map;
//            map.forEach((key, value) -> System.out.println(key + "," + value));
        }catch (IOException e) {
            
            System.out.println(e);
        }
        return null;
    }
    public static void PrintCSV(){
        String file = "F:\\HK\\III\\Standard\\My Homework\\Java\\ltudjava-18hcb-18424012-bt1\\Data\\Lop\\Diem.csv";
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
}
