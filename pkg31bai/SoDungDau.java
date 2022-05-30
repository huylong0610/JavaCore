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
public class SoDungDau {
    public static void findMax(int arr[], int n) {
        Stack<Integer> st = new Stack<>();
        int max = -1;
        for(int i=n-1; i>=0; i--){
            if(arr[i] > max){
                max = arr[i];
                st.push(arr[i]);
            }
        }
        while(!st.empty()){
            System.out.print(st.lastElement()+" ");
            st.pop();
        }
        System.out.println();
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        while(t-->0){
            int n = in.nextInt();
            int arr[] = new int[n];
            for(int i=0; i<n; i++){
                arr[i] = in.nextInt();
            }
            findMax(arr,n);
        }
    }
}
