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

import static GiaoVu_SinhVien.Login_Logout.Menu;
import static GiaoVu_SinhVien.XuLyData.ReadFileCSV;
import static GiaoVu_SinhVien.XuLyData.SelectFile_DanhSach;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

/**
 *
 * @author Win10
 */
public class XemBangDiem {
    public static void GiaoVuXemBangDiem(){
        int dau = 0;
        Path des = Paths.get(System.getProperty("user.dir"));
//        System.out.println(des);
        des = Paths.get(des.getParent() + "/Data/Diem/");
        File ktra_file = new File(des.toString());
        if(!ktra_file.exists() || ktra_file.list().length == 0){
            System.out.println("Chưa có bảng điểm");
            Menu();
        }
//        System.out.println(des);
        String path = SelectFile_DanhSach(des);
        Map<String, String> map = (Map<String, String>)ReadFileCSV(path);
       
//        STT ,MSSV ,Họ tên ,Điểm GK,Điểm CK,Điểm khác,Điểm tổng
        String title = map.get("title");
        map.remove("title");
        String []arrItem = title.split(",");
        System.out.printf("%4s %-10s %-15s %8s %8s %8s %8s",arrItem[0], arrItem[1],arrItem[2],arrItem[3],arrItem[4],arrItem[5],arrItem[6]);
        System.out.println();
        for(String key: map.keySet()) {
            String []valItem = map.get(key).split(",");
//            System.out.println(key + "\t" + map.get(key));
            System.out.printf("%-4s %-10s %-20s %-8s %-8s %-8s %-8s \n",valItem[0], valItem[1],valItem[2],valItem[3],valItem[4],valItem[5],valItem[6]);
            try {
                float diem = Float.parseFloat(valItem[6]);
                if(diem >= 5)
                    dau++;
            } catch (NumberFormatException e) {
            }
        }
        System.out.printf("Thống kê: %d Đậu, %d Rớt \n", dau, map.size() - dau);
    }
}