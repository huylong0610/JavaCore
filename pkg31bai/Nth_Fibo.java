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
public class Nth_Fibo {
    long fibo[] = new long[100];
    
    private void solve() {
         fibo[1] = 1;
         fibo[2] = 1;
         for(int i = 3; i<= 92; i++){
             fibo[i] = fibo[i-1] + fibo[i-2];
         }
    }
    private void start(){
        solve();
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        while(t-->0){
            int n = in.nextInt();
            System.out.println(fibo[n]);
        }
        
    }
    
    public static void main(String[] args) {
        Nth_Fibo nth_fibo = new Nth_Fibo();
        nth_fibo.start();
    }
}
