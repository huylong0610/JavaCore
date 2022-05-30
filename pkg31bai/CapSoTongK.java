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
public class CapSoTongK {
    public static long CountPairs(int n, long arr[], long k) {
        HashMap<Long, Integer> hashmap1 = new HashMap<>();
        for(int i = 0; i<n; i++){
            if(!hashmap1.containsKey(arr[i])){
                hashmap1.put(arr[i], 0);
            }
            hashmap1.put(arr[i], hashmap1.get(arr[i])+1);
        }
        long count = 0; // dem 1 cai 2 lan
        for(int i=0; i<n; i++){
            if(hashmap1.get(k - arr[i]) != null){
                count += hashmap1.get(k - arr[i]);
            }
            if(arr[i] + arr[i] == k){
                count --;
            }
        }
        return count/2;
    }
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        while(t-->0){
            int n = in.nextInt();
            long k = in.nextLong();
            long arr[] = new long[n+1];
            for(int i=0; i<n; i++){
                arr[i] = in.nextLong();
            }
            System.out.println(CountPairs(n, arr, k));
        }
    }
}
