//Finish
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GiaoVu;

import static GiaoVu_SinhVien.XuLyData.XuLyPathImportFile;
import java.io.File;
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
public class Import_DSLop {
    public static void copyFileDSLop(){
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
//        System.out.println(des);
        des = Paths.get(des.getParent() + "/Data/Lop/" + f);
//        System.out.println(des);
        File createdir = new File(des.getParent().toString());
        
        if(!createdir.exists())
            createdir.mkdirs();
        try {
            Files.copy(source, des, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            Logger.getLogger(ImportBangDiem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
