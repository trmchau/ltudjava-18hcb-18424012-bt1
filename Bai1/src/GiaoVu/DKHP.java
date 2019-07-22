/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
// Đăng ký học phần _ Lưu sanh sách riêng cho từng môn học
package GiaoVu;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Win10
 */
public class DKHP {
    public static void copyFileDSLop(){
        Path source = Paths.get("F:\\HK\\III\\Standard\\My Homework\\Java\\Bài Tập\\Data\\18HCB–CTT001.csv");
//        System.out.println(source);
        Path f = source.getFileName();
//        System.out.println(f);
        Path des = Paths.get(System.getProperty("user.dir"));
//        System.out.println(des);
        des = Paths.get(des.getParent() + "/Data/HocPhan/" + f);
//        System.out.println(des);
        try {
            Files.copy(source, des, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            Logger.getLogger(ImportBangDiem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
