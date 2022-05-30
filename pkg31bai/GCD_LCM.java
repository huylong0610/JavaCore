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

public class GCD_LCM {
    public static long gcd(long a, long b) {
        if(b==0)    return a;
        else return gcd(b, a%b);
    }
    
    public static long lcm(long a, long b) {
        return a*b / (gcd(a, b));
    }
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        while(t --> 0){
            long a = in.nextInt();
            long b = in.nextInt();
            System.out.print(lcm(a, b)+" ");
            System.out.print(gcd(a, b));
            System.out.println();
        }
    }
}
