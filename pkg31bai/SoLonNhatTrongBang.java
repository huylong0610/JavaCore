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
public class SoLonNhatTrongBang {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        long row = Integer.MAX_VALUE;
        long column = Integer.MAX_VALUE;
        long res = 0;
        while(n-->0){
            long a = in.nextInt();
            long b = in.nextInt();
            if(a<row){
                row = a;
            }
            if(b<column){
                column = b;
            }
        }
        res = row * column;
        System.out.println(res);
    }
}

