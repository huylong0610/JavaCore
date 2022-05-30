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
public class SoDep1 {
    public static boolean check(String str){
        int n = str.length();
        for(int i=0; i<n; i++){
            if(str.charAt(i)%2 != 0){
                return false;

            }
        }
        String str2 = new StringBuilder(str).reverse().toString();
        if(str2.compareTo(str)==1){
            return false;
        }
        return true;
    }
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        while(t-->0){
            String str1 = in.next();
            if(check(str1)){
                System.out.println("YES");
            }
            else    System.out.println("NO");
        }
    }
}
