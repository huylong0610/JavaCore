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

public class DemSoLanXuatHien {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for(int i = 1; i<=t; i++){
            int n = in.nextInt();
            int arr[] = new int [101];
            int dem[] = new int [100001];
            boolean check[] = new boolean[100001];
            Arrays.fill(dem, 0);
            Arrays.fill(check, false);
            for(int j=0; j<n; j++){
                arr[j] = in.nextInt();
                dem[arr[j]]++;
            }
            System.out.println("Test " +i+": ");
            for(int k=0; k<n; k++){
                if(check[arr[k]] == false && dem[arr[k]]!=0){
                    System.out.println(arr[k] + " xuat hien " + dem[arr[k]]+" lan");
                    check[arr[k]] = true;
                }
            }
        }
    }
}
