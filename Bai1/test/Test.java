
import java.io.BufferedReader;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.util.Scanner;
import javax.sound.sampled.AudioFormat.Encoding;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Win10
 */
public class Test {
    public static void main(String[] args) throws UnsupportedEncodingException{

        
//        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out),true,"UTF-8"));
//        InputStreamReader isw = new InputStreamReader(System.in);
//        BufferedReader in = new BufferedReader(new InputStreamReader(System.in, isw.getEncoding()));
//        
//        OutputStreamWriter osw = new OutputStreamWriter(System.out);
//        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out),true,osw.getEncoding()));
//        
//        System.out.println(isw.getEncoding());
//        System.out.println(osw.getEncoding());
        System.out.print("Nhập chuỗi Tiếng Việt:  ");
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        byte []input = str.getBytes("UTF-8");
        String rs = new String (input, "UTF-8");
//        System.out.print(rs);
//        System.out.println(rs);
//        for(byte item: input)
//            System.out.print(item);
        
//            Charset iso = Charset.forName("ISO-8859-1");
//            CharsetDecoder isodecoder = iso.newDecoder();
//            ByteBuffer bbuf = ByteBuffer.wrap(input);
//            CharBuffer cbuf = isodecoder.decode(bbuf);  // Decode from ISO to UTF-16
//            
//            Charset utf8 = Charset.forName("UTF-8");
//            CharsetEncoder utf8encoder = utf8.newEncoder();
//            ByteBuffer outbuffer = utf8encoder.encode(cbuf);  // Encode from UTF-16 to UTF-8
//            String str1 = new String(outbuffer.array(), "UTF-8");    // Resultant String
//            System.out.println(str1);
    }
}