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
public class SoDep3 {
    public static boolean Prime(char c) {
        if(c == '2'|| c == '3' || c == '5'|| c == '7')
            return true;
        return false;
    }
    public static boolean check3(String str) {
        int n = str.length();
        for(int i=0; i<n; i++){
            if(!Prime(str.charAt(i)))
                return false;
        }
        String str1 = new StringBuilder(str).reverse().toString();
        if(str1.compareTo(str)!=0)  return false;
        return true;
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        while(t-->0){
            String str = in.next();
            if(check3(str)){
                System.out.println("YES");
            }
            else{
                System.out.println("NO");
            }
        }
    }
}
