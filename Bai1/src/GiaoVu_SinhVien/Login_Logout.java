/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GiaoVu_SinhVien;

import static GiaoVu.Add_SV.GiaoVuAddSv;
import static GiaoVu.DKHP.DangKyHocPhan;
import static GiaoVu.ImportBangDiem.copyFileBangDiem;
import static GiaoVu.Import_DSLop.copyFileDSLop;
import static GiaoVu.Import_TKB.copyFileTKB;
import static GiaoVu.SuaDiem.GiaoVuSuaDiem;
import static GiaoVu.XemBangDiem.GiaoVuXemBangDiem;
import static GiaoVu.XemDSLop.GiaoVuXemDsLop;
import static GiaoVu.XemTKB.GiaoVuXemTKB;
import static GiaoVu_SinhVien.ChangePassword.NewPassword;
import static GiaoVu_SinhVien.XuLyData.ReadFileCSV;
import static SinhVien.XemBangDiemCaNhan.SinhVienXemBangDiem;
import java.nio.file.Path;
import java.nio.file.Paths;
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
        //Kiểm tra từng chức năng.
//        ImportDSLop();
//        ThemSV();
//        ImportTKB();
//        XemDSSVHocPhan();
//        XemDSLop();
//        XemLaiTKB();
//        ImportBangDiem();
//        XemLaiBangDiem();
//        SuaDiemThi();
        Login();
    }
    public static boolean KtraUser(){
        Path des = Paths.get(System.getProperty("user.dir"));
        String file = des + "\\User_pass.csv";
//        String file = "F:\\HK\\III\\Standard\\My Homework\\Java\\ltudjava-18hcb-18424012-bt1\\Data\\User_pass.csv";
        Map<String, String> map = ReadFileCSV(file);
        String title = map.get("title");
        System.out.println(title);
        map.remove("title");
        map.entrySet().forEach((e) -> {
            System.out.println(e.getValue());
        });
        
        while(true){
            System.out.print("Nhập User: ");
            Scanner sc = new Scanner(System.in);
            user = sc.next();
            if(map.containsKey(user = user.toLowerCase())) {
                String []arrItem = map.get(user).split(",");
                System.out.print("Nhập Password: ");
                while(true){
                    String pass = sc.next();

    //                arrItem[2] là cột password
                    if(pass.equals(arrItem[2]))
                        return true;
                    else{
                        System.out.print("Sai Password --> Nhập lại: ");
                    }
                }     
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
        System.out.println("0. Thoát Chương trình");
        
        System.out.println("1. Xem Điểm");  //void XemDiem()
        System.out.println("2. Đổi Mật Khẩu");  // void DoiPassword()
        System.out.println("3. Logout");
        
        while(status){
            System.out.print("Nhập chức năng bằng số: ");
            Scanner sc = new Scanner(System.in);
            
            String temp = sc.next();
            int value;
            if(temp.matches("\\d*")){
                value = Integer.valueOf(temp);
            }
            else
                continue;
            
            switch(value){
                case 0: 
                    System.exit(0);

                case 1: XemDiem();
                    break;
                case 2: DoiPassword();
                    break;
                case 3: Logout();
                    Login();
                    break;
                default:
                    System.out.println("Chức năng thứ: " + value + " không tồn tại");
                    break;
            }
        }
    }
    public static void MenuGiaoVu(){
        System.out.println("0. Thoát Chương trình");
        
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
        System.out.println("11. Logout");
        while(status){
            System.out.print("Nhập chức năng bằng số: ");
            Scanner sc = new Scanner(System.in);
            String temp = sc.next();
            int value;
            if(temp.matches("\\d*")){
                value = Integer.valueOf(temp);
                    switch(value){
                        case 0: 
                            System.exit(0);

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
                        case 11: Logout();
                            Login();
                            break;
                        default:
                            System.out.println("Chức năng thứ: " + value + " không tồn tại");
                            break;
                }
            }
        }
    }
    
//    Phần Chung
    public static void Login_Logout(){
        System.out.println("Login_Logout");
    }
    public static void DoiPassword(){
        System.out.println("********* Chức năng đổi Password");
        System.out.println(user + ": Đổi Pass word");
        NewPassword(user);
    }
//    Phần Sinh Viên
//    Chức năng 1
    public static void XemDiem(){
        System.out.println("********* Xem Điểm Thi");
        SinhVienXemBangDiem(user);
    }
//    Phần GiaoVu
//    Chức năng 1
    public static void ImportDSLop(){
        System.out.println("********* Chức năng Import DS Lớp");
        copyFileDSLop();
        System.out.println("OK");
    }
//    Chức năng 2
    public static void ThemSV(){
        System.out.println("********* Chức năng Thêm Sinh Viên");
        GiaoVuAddSv();
        System.out.println("OK");
    }
//    Chức năng 3
    public static void ImportTKB(){
        System.out.println("********* Chức năng Import Thời Khóa Biểu");
        copyFileTKB();
        System.out.println("OK");
    }
//    Chức năng 4
    public static void XemDSSVHocPhan(){
        System.out.println("********* Chức năng Thêm Xóa Danh Sách Sinh Viên Học Môn Học");
        DangKyHocPhan();
        System.out.println("OK");
    }
//    Chức năng 5
    public static void XemDSLop(){
        System.out.println("********* Chức năng Xem Danh Sách Lớp");
        GiaoVuXemDsLop();
    }
//    Chức năng 6
    public static void XemLaiTKB(){
        System.out.println("********* Chức năng Xem Thời Khóa Biểu");
        GiaoVuXemTKB();
    }
//    Chức năng 7
    public static void ImportBangDiem(){
        System.out.println("********* Chức năng Import Bảng Điểm");
        copyFileBangDiem();
        System.out.println(user + ": Đã Hoàn Thành Import Bảng Điểm");
    }
//    Chức năng 8
    public static void XemLaiBangDiem(){
        System.out.println("********* Chức năng Xem Bảng Điểm");
        GiaoVuXemBangDiem();
    }

//    Chức năng 9
    public static void SuaDiemThi(){
        System.out.println("********* Chức năng Sửa Điểm Thi");
        System.out.print("Nhập MSSV cần sửa Điểm: ");
        Scanner sc = new Scanner(System.in);
        String ktra;
        String mssv = sc.nextLine();
        while(true){
            GiaoVuSuaDiem(mssv);
            System.out.print("Tiếp tục sửa điểm cho SV không? Y/N: ");
            ktra = sc.next();
            if(!("Y".equalsIgnoreCase(ktra.trim()))){
                System.out.println(user + " Đã Hoàn Thành Chức Năng Sửa Điểm");
                Menu();    
            }
                
        }
    }
 
}
