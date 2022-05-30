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
import java.math.BigInteger;
import java.util.*;
public class LCM_of_Numbers {
    public static BigInteger gcd(BigInteger a, BigInteger b) {
        return a.gcd(b);
    }
    public static BigInteger lcm(BigInteger a, BigInteger b) {
        return a.multiply(b).divide(gcd(a, b));
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        while(t-->0){
            int n = in.nextInt();
            BigInteger result = BigInteger.ONE;
            for(int i=1; i<=n; i++){
                result = lcm(result, BigInteger.valueOf(i));
            }
            System.out.println(result);
        }
    }
}
