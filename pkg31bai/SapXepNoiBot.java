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

public class SapXepNoiBot {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int dem = 0;
        int a[] = new int[n + 2];
        for(int i = 0; i < n; i++)
            a[i] = scanner.nextInt();
        for(int i = 0; i < n; i++) {
            boolean is_sorted = true;
            for(int j = 1; j < n - i; j++)
                if(a[j - 1] > a[j]){
                    int tmp = a[j - 1];
                    a[j - 1] = a[j];
                    a[j] = tmp;
                    is_sorted = false;
                }
            if(is_sorted){
                break;
            }
            
            System.out.print("Buoc " + (i + 1) + ": ");
            for(int k = 0; k < n; k++)
                System.out.print(a[k] + " ");
            System.out.println();
        }
    }
}
