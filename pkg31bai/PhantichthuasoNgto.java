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
public class PhantichthuasoNgto {
    private void Phantich(int n){
        if(n%2 == 0){
            int dem1 = 0;
            while(n%2==0){
                n=n/2;
                dem1++;
            }
            System.out.print("2("+dem1+") ");
        }
        for(int i=3; i<=n; i++){
            if(n%i==0){
                int dem2 = 0;
                while(n%i == 0){
                    n=n/i;
                    dem2++;
                }
                System.out.print(i+"("+dem2+") ");
            }
        }
    }
    
    private void start(){
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for(int i=1; i<=t; i++){
            int n = in.nextInt();
            System.out.print("Test "+i+": ");
            Phantich(n);
            System.out.println();
        }
    }
    
    public static void main(String[] args) {
        PhantichthuasoNgto PT = new PhantichthuasoNgto();
        PT.start();
    }
}

