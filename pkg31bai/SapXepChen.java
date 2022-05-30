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

public class SapXepChen {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int arr[] = new int[101];
        for(int i=0; i<n; i++){
            arr[i] = in.nextInt();
        }
        System.out.print("Buoc 0: "+arr[0]);
        System.out.println();
        for(int i=1; i<n; i++){
            int value = arr[i];
            int key = i-1;
            while(key >= 0 && arr[key] > value){
                arr[key+1] = arr[key];
                key--;
            }
            arr[key+1] = value;
            System.out.print("Buoc "+i+": ");
            for(int k=0; k<=i; k++){
                System.out.print(arr[k]+" ");
            }
            System.out.println();
        }
    }
}
