
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Win10
 */
public class Ghi_Doc_Unicode {
    public static void main(String []args){
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        System.out.println(s);
        File f = new File("C:\\Users\\Win10\\Desktop\\test_unicode.txt");
        try(PrintWriter pw = new PrintWriter(new FileWriter(f))) {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//            PrintWriter out = new PrintWriter(System.console().writer(), true);
//            pw.append(s);
//            pw.append("\nChâu");
            String unicode = "";
            unicode = in.readLine();
            System.out.print(unicode);
        } catch (Exception e) {
        }
    }
}
