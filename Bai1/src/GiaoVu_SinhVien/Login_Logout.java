/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GiaoVu_SinhVien;

import static GiaoVu.Import_DSLop.copyFileDSLop;
import static GiaoVu.XemBangDiem.GiaoVuXemDiem;
import static GiaoVu.XemDSLop.XemDsLop;
import static GiaoVu_SinhVien.XuLyData.PrintCSV;
import java.io.File;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Win10
 */
public class Login_Logout {
    protected static String user;
    protected static boolean status = false; // Trạng thái offline chưa đăng nhập.
    protected static boolean flag = false; // Thông báo vừa thực thi xong chức năng.
    public static void main(String [] args){
        //boolean kq = KtraUser();
        //Login();
        //ImportDSLop();
        //GiaoVuXemDiem();
        XemDsLop();
        //PrintCSV();
    }
    public static boolean KtraUser(){
        String file = "User_pass.csv";
        Map<String, String> map = XuLyData.ReadFileCSV(file);
        map.entrySet().forEach((e) -> {
            System.out.println(e.getKey() + "," + e.getValue());
        });
        
        while(true){
            System.out.print("Nhập User: ");
            Scanner sc = new Scanner(System.in);
            user = sc.next();
//            map.entrySet().forEach((e) -> {
//            System.out.println(e.getKey() + "," + e.getValue(User_pass.csv));
            if(map.containsKey(user = user.toLowerCase())) {
                return true;
            }
            else
                System.out.println("User không hợp lệ.");
        }     
    }
    public static void Login(){
        if(KtraUser()){
            status = true;
            Menu();
        }
        else
            Login();
    }
    public static void Logout(){
        status = false;
        user = null;
    }
    public static void Menu(){
//        while(status){
//            if("giaovu".equals(user)){
//                MenuGiaoVu();
//                break;
//            }
//                
//            else{
//                MenuSinhVien();
//                break;
//            }       
//        }
        if("giaovu".equals(user)){
            MenuGiaoVu();
        }

        else{
            MenuSinhVien();
        }       
    }
    public static void MenuSinhVien(){
        System.out.println("1. Xem Điểm");  //void XemDiem()
        System.out.println("2. Đổi Mật Khẩu");  // void DoiPassword()
        while(status){
            System.out.print("Nhập chức năng bằng số: ");
            Scanner sc = new Scanner(System.in);
            int value = sc.nextInt();
            switch(value){
                case 0: Logout();
                    Login();
                    break;
                case 1: XemDiem();
                    break;
                case 2: DoiPassword();
                    break;
                default:
                    System.out.println("Chức năng thứ: " + value + " không tồn tại");
                    break;
            }
        }
    }
    public static void MenuGiaoVu(){
        System.out.println("1. Import Danh Sách Lớp"); // void ImportDSLop()
        System.out.println("2. Thêm Sinh Viên"); // void ThemSV()
        System.out.println("3. Import Thời Khóa Biểu"); // void ImportTKB()
        System.out.println("4. Thêm Xóa Sinh Viên Trong DS Môn Học"); // void XemDSSVHocPhan()
        System.out.println("5. Xem Danh Sách Lớp"); // void XemDSLop()
        System.out.println("6. Xem Lại Thời Khóa Biểu"); // void XemLaiTKB()
        System.out.println("7. Import Bảng Điểm"); // void ImportBangDiem()
        System.out.println("8. Xem Lại Bảng Điểm"); // void XemLaiBangDiem()
        System.out.println("9. Sửa Điểm Cho Sinh Viên"); // void SuaDiemThi()
        System.out.println("10. Đổi Mật Khẩu"); // void DoiPassword()
        while(status){
            System.out.print("Nhập chức năng bằng số: ");
            Scanner sc = new Scanner(System.in);
            int value = sc.nextInt();
            switch(value){
                case 0: Logout();
                    Login();
                    break;
                case 1: ImportDSLop();
                    break;
                case 2: ThemSV();
                    break;
                case 3: ImportTKB();
                    break;
                case 4: XemDSSVHocPhan();
                    break;
                case 5: XemDSLop();
                    break;
                case 6: XemLaiTKB();
                    break;
                case 7: ImportBangDiem();
                    break;
                case 8: XemLaiBangDiem();
                    break;
                 case 9: SuaDiemThi();
                    break;
                 case 10: DoiPassword();
                    break;
                 default:
                    System.out.println("Chức năng thứ: " + value + " không tồn tại");
                    break;
            }
        }
    }
    public static void HashMapToFile(Map<String, String> map, String file){
        File f = new File(file);
        int stt = 0;
        for(String key: map.keySet()){
            stt++;
            System.out.println(stt + ", " + key + ", " + map.get(key));
        }  
    }
//    Phần Chung
    public static void Login_Logout(){
        System.out.println("Login_Logout");
    }
    public static void DoiPassword(){
        System.out.println(user + ": Đổi Pass word");
    }
//    Phần Sinh Viên
//    Chức năng 1
    public static void XemDiem(){
        System.out.println("Điểm thi của " + user);
    }
//    Phần GiaoVu
//    Chức năng 1
    public static void ImportDSLop(){
        System.out.println(user + ": Muốn Import DS Lớp");
        copyFileDSLop();
    }
//    Chức năng 2
    public static void ThemSV(){
        System.out.println(user + ": Muốn Thêm Xóa SV trong DS Lớp");
    }
//    Chức năng 3
    public static void ImportTKB(){
        System.out.println(user + ": Muốn Import TKB");
    }
//    Chức năng 4
    public static void XemDSSVHocPhan(){
        System.out.println(user + ": Muốn Thêm Xóa SV trong DS Học Phần");
    }
//    Chức năng 5
    public static void XemDSLop(){
        System.out.println(user + " Xem lại Ds lop");
    }
//    Chức năng 6
    public static void XemLaiTKB(){
        System.out.println(user + " Xem lại thời khóa biểu");
    }
//    Chức năng 7
    public static void ImportBangDiem(){
        System.out.println(user + ": Muốn Import Bảng Điểm");
    }
//    Chức năng 8
    public static void XemLaiBangDiem(){
        System.out.println(user + " Muốn Xem Điểm thi của Sv");
    }

//    Chức năng 9
    public static void SuaDiemThi(){
        System.out.println(user + " Muốn sửa điểm thi");
    }
 
}
