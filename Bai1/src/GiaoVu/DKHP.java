/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
// Đăng ký học phần _ Lưu sanh sách riêng cho từng môn học
package GiaoVu;

import static GiaoVu_SinhVien.Login_Logout.Menu;
import static GiaoVu_SinhVien.XuLyData.ReadFileCSV;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Win10
 */
public class DKHP {
    public static void DangKyHocPhan(String mssv, String lop){
        Map<String, String> map = ReadFileCSV(lop);
        String title = map.get("title");
//        map.remove("title");
        Scanner sc = new Scanner(System.in);
        int chon;
        if(map.containsKey(mssv)){
            System.out.printf("1. Cho phép %s không tham gia lớp: %s \n", mssv, lop);
            System.out.println("2. Trở về Home");
            while(true){
                try {
                System.out.println("Nhập lựa chọn: ");
                chon = sc.nextInt();
                    switch(chon){
                        case 1: 
                            System.out.println(mssv + " Đã xin nghỉ thành công");
                            break;
                        case 2:
                            Menu();
                            break;
                        default:
                            break;
                    }
                }catch (Exception e) {
                    System.out.println("Bạn nhập không phải số --> " + e);
                }
            }
        }else{
            System.out.printf("1. Cho phép %s nhập học lớp: %s \n", mssv, lop);
            System.out.println("2. Trở về Home");
            while(true){
                try {
                System.out.print("Nhập lựa chọn: ");
                chon = sc.nextInt();
                    switch(chon){
                        case 1: 
                            System.out.println(mssv + " xin cải thiện thành công");
                            break;
                        case 2:
                            Menu();
                            break;
                        default:
                            break;
                    }
                }catch (Exception e) {
                    System.out.println("Bạn nhập không phải số --> " + e);
                    break;
                }
            }
        }  
        
    }
}
