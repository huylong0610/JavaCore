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

public class MangDoiXung {
    public static boolean check(int arr[], int n) {
        if(n == 1)  return true;
        int left = 0;
        int right = n-1;
        while(left <= right){
            if(arr[left] != arr[right]){
                return false;
            }
            else{
                left++;
                right--;
            }
        }
        return true;
    }
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        while(t --> 0){
            int n = in.nextInt();
            int arr[] = new int [n];
            for(int i = 0; i<n; i++){
                arr[i] = in.nextInt();
            }
            if(check(arr, n)){
                System.out.println("YES");
            }
            else{
                System.out.println("NO");
            }
        }
    }
}
