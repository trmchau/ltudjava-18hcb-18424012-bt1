//Finish
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GiaoVu;

import static GiaoVu_SinhVien.XuLyData.ReadFileCSV;
import static GiaoVu_SinhVien.XuLyData.XuLyPathImportFile;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Win10
 */
public class Import_TKB {
    public static void copyFileTKB(){
        String path = XuLyPathImportFile();
        if(!path.endsWith(".csv")){
            System.out.println("Import không thành công");
            return;
        }
        Path source = Paths.get(path);
//        System.out.println(source);
        Path f = source.getFileName();
//        System.out.println(f);
        Path des = Paths.get(System.getProperty("user.dir"));
        Path lop = Paths.get(des.getParent() + "/Data/Lop/" + f);
//        System.out.println(des);
        des = Paths.get(des.getParent() + "/Data/TKB/" + f);
        
//        System.out.println(des);
        try {
            Files.copy(source, des, StandardCopyOption.REPLACE_EXISTING);
//            Tách phần mở rộng
            
            CreateFileDKHP(lop, des);
        } catch (IOException ex) {
            Logger.getLogger(ImportBangDiem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void CreateFileDKHP(Path lop, Path fullNameTKB){
        Map<String, String> map = (Map<String, String>)ReadFileCSV(fullNameTKB.toString());
        String title = map.get("title");
        map.remove("title");
        for(String key : map.keySet()){
            String [] nameLop = lop.getFileName().toString().split("\\.");
            Path fileDSHP = Paths.get(lop.getParent() + "/" + nameLop[0] + "-" + key.trim() + ".csv");
            System.out.println(fileDSHP);
            File f = new File(fileDSHP.toString());
            if(!f.exists()){
                try {
                    Files.copy(lop, fileDSHP, StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e) {
                }
            }
                
        }
    }
}
