/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg31bai;


import java.util.*;

/**
 *
 * @author 8460p
 */
public class BoBaSoPytago {
    public static boolean Pytago(int n, long arr[]) {
        for(int i = 0; i < n; i++){
            arr[i] = arr[i] * arr[i];
        }
        for(int i = 0; i < n - 1; i++)  {
            for(int j = i + 1; j < n; j++)  {
                if(arr[j] < arr[i]){
                    long temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        for(int i = n - 1; i >= 1; i--){
            int left = 0;
            int right = i - 1;
            while(left < right){
                //System.out.println(a[l] + " " + a[r] + " " + a[i]);
                if(arr[left] + arr[right] == arr[i])
                    return true;
                if(arr[left] + arr[right] < arr[i])
                    left++;
                else
                    right--;
            }
        }
        return false;
    }
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int  t = in.nextInt();
        while(t-- > 0){
            int n = in.nextInt();
            long arr[] = new long[n+1];
            for(int i = 0; i < n; i++)
                arr[i] = in.nextInt();
            if(Pytago(n, arr))
                System.out.println("YES");
            else
                System.out.println("NO");
        }
    }
}
