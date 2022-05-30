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
public class GCD_largeNum {
    public static long reduce(long a, String b) {
        long res = 0;
        for(int i = 0; i<b.length(); i++){
            res = (res*10 + b.charAt(i)-'0') % a;
        }
        return res;
    }
    public static long gcd(long a, long b) {
        if(b == 0)  return a;
        else return gcd(b, a%b);
    }
    public static long gcdofLarge(long a, String b) {
        long num = reduce(a, b);
        return gcd(num, a);
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        while(t-->0){
            long a = in.nextLong();
            in.nextLine();
            String b = in.nextLine();
            System.out.println(gcdofLarge(a, b));
        }
    }
}
