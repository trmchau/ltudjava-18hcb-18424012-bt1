//Finish
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//Xem lại bảng điểm.
//Thống kê đậu rớt.
//Cho biết phần trăm đậu rớt
package GiaoVu;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author Win10
 */
public class XemBangDiem {
    public static void GiaoVuXemDiem(){
        String path = "F:\\HK\\III\\Standard\\My Homework\\Java\\ltudjava-18hcb-18424012-bt1\\Data\\Lop\\User_pass.csv";
        Map<String, String> map = ReadFileCSV(path);
        map.keySet().forEach((key) -> {
                System.out.println(key + "\t" + map.get(key));
        });  
    }
    public static Map<String, String> ReadFileCSV(String file){
        try {
            File f = new File(file);
            FileReader fr = new FileReader(f);
//            String code = fr.getEncoding();
            Map<String, String> map;
            try (BufferedReader br = new BufferedReader(fr)) {
                String line;
                //Lấy ra dòng đầu tiên trong file làm tiêu đề cột
                String title = br.readLine();
                System.out.println(title);
                HashMap<String, String> list = new HashMap<>();
                while((line = br.readLine())!= null){
                    String []arrItem = line.split(",");
                    StringBuilder value = new StringBuilder("");
                    for(int i = 2; i < arrItem.length; i++){
                        value.append(arrItem[i]);
                        
                    }
//                    System.out.print(arrItem[1]);
//                    System.out.print(value);
                    list.put(arrItem[1], value.toString());
//                    System.out.println();
                    
                }   map = new TreeMap<>(list);
            }
            return map;
        }catch (IOException e) {
            
            System.out.println(e);
        }
        
        return null;
    }
}
