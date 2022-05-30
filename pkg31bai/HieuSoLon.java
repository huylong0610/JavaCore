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
public class HieuSoLon {
    public static String Hieu2SoLon(String str1, String str2) {
        if (str1.length() < str2.length()){
            String t = str1; 
            str1 = str2; 
            str2 = t; 
        }
        String str = "";
        int n1 = str1.length(), n2 = str2.length();  
        str1=new StringBuilder(str1).reverse().toString(); 
        str2=new StringBuilder(str2).reverse().toString(); 
  
        int borrow = 0;  
        for(int i=0; i<n2; i++){
            int sub = ((int)str1.charAt(i)-'0') - ((int)str2.charAt(i)-'0') - borrow;
            if(sub<0){
                sub = sub + 10;
                borrow = 1;
            }
            else borrow = 0;
            str += (char)(sub+'0');
        }
        for(int i=n2; i<n1; i++){
            int sub = ((int)str1.charAt(i)-'0') - borrow;
            if(sub < 0){
                sub = sub + 10;
                borrow = 1;
            }
            else borrow = 0;
            str += (char)(sub + '0');
        }
        str = new StringBuilder(str).reverse().toString(); 
  
        return str;    
    }    
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        while(t-->0){
            String str1 = in.next();
            String str2 = in.next();
            System.out.println(Hieu2SoLon(str1, str2));
        }
    }
}
