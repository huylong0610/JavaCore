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
public class DiemCanBang {
    public static int findPos(int arr[], int n) {
        int SumOfArr = 0;
        int CurrentSum = 0;
        for(int i=0; i<n; i++){
            SumOfArr += arr[i];
        }
        for(int i=0; i<n; i++){
            if(CurrentSum == SumOfArr-(CurrentSum + arr[i]))
                return (i+1);
            CurrentSum += arr[i];
        }
        return -1;
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
            System.out.println(findPos(arr, n));
        }
    }
}
