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
public class SoDep2 {
    public static boolean check2(String str1) {
        int n = str1.length();
        int sum = 0;
        for(int i=0; i<n; i++){
            sum+=(int)(str1.charAt(i)-'0');
        }
        if(sum%10 != 0) return false;
        if(str1.charAt(n-1)!= '8' || str1.charAt(0) != '8')  return false;
      
        String str2 = new StringBuilder(str1).reverse().toString();
        if(str2.compareTo(str1)!=0) return false;
        return true;
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        while(t-->0){
            String str1 = in.next();
            if(check2(str1))    System.out.println("YES");
            else    System.out.println("NO");
        }
    }
}
