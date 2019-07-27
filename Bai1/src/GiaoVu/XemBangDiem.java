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

import static GiaoVu_SinhVien.XuLyData.ReadFileCSV;
import static GiaoVu_SinhVien.XuLyData.SelectFile_DanhSach;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Win10
 */
public class XemBangDiem {
    public static void GiaoVuXemBangDiem(){
        Path des = Paths.get(System.getProperty("user.dir"));
//        System.out.println(des);
        des = Paths.get(des.getParent() + "/Data/Diem/");
//        System.out.println(des);
        String path = SelectFile_DanhSach(des);
        Map<String, String> map = (Map<String, String>)ReadFileCSV(path);
        map.keySet().forEach((key) -> {
            System.out.println(key + "\t" + map.get(key));
        });       
    }
}