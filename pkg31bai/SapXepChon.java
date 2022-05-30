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

public class SapXepChon {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int arr[] = new int [101];
        for(int i=1; i<=n; i++){
            arr[i] = in.nextInt();
        }
        for(int i=1; i<=n-1; i++){
            int min_index = i;
            for(int j=i; j<=n; j++){
                if(arr[j] < arr[min_index])
                    min_index = j;
            }
            int temp = arr[min_index];
            arr[min_index] = arr[i];
            arr[i] = temp;
            
            System.out.print("Buoc "+i+": ");
            for(int k=1; k<=n; k++){
                System.out.print(arr[k]+" ");
            }
            System.out.println();
        }
    }
}
