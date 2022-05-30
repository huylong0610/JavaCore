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

public class SapXepTrucTiep {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int arr[] = new int [101];
        for(int i=1; i<=n; i++){
            arr[i] = in.nextInt();
        }
        
        for(int i=1; i<=n-1; i++){
            for(int j=i+1; j<=n;j++){
                if(arr[j] < arr[i]){
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
            System.out.print("Buoc "+i+": ");
                for(int k=1; k<=n; k++){
                    System.out.print(arr[k]+ " ");
                }
                System.out.println();
        }
    }
}
