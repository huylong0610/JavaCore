/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg31bai;

/**
 *
 * @author 8460p
 */
import java.util.*;
public class ChuanHoa1 {
    public static String Chuan_hoa(String str) {
        str = str.trim();
        str = str.replaceAll("\\s+", " ");
        String temp[] = str.split(" ");
        String str_res = "";
        for(int i=0; i<temp.length;i++){
            str_res += String.valueOf(temp[i].charAt(0)).toUpperCase();
            str_res += temp[i].toLowerCase().substring(1);
            if(i != temp.length-1)
                str_res += " ";
        }
        return str_res;
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        in.nextLine();
        while(t-->0){
            String str = in.nextLine();
            System.out.print(Chuan_hoa(str));
            System.out.println();
        }
    }
}
