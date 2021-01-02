/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pushrank;

import java.util.Scanner;

/**
 *
 * @author Dell
 */
public class PushRank {

    private int[] ranked;
    private int[] player;
    private int n, m;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        PushRank pr = new PushRank();
        
        Scanner input = new Scanner(System.in);
        
        pr.n = input.nextInt();
        
        pr.ranked = new int[pr.n];
        
        for (int i=0; i<pr.n; i++){
            pr.ranked[i] = input.nextInt();
        }

        pr.m = input.nextInt();
        
        pr.player = new int[pr.m];
        
        for(int i=0; i<pr.m; i++){
            pr.player[i] = input.nextInt();
        }
        
        System.out.println("");
        pr.getLatestRank(pr.ranked, pr.player);
    }
    
    private void getLatestRank(int[] ranked, int[] player){
        int indeks = m + n;
        int k = n;
        int p = indeks-1;
        int temp = 0;
        int indeksCek = 0;
        int[] totalRank = new int[indeks];
        int[] currentRank = new int[indeks];
        currentRank[0] = 1;
        
        for (int i=0; i<n; i++){
            totalRank[i] = ranked[i];
        }
        
        for (int i=0; i<m; i++){
            totalRank[k] = player[i];
            k++;
        }
        
        for (int i=0; i<indeks; i++){
            for (int j=1; j<(indeks-i); j++){
                if (totalRank[j-1] < totalRank[j]){
                    temp = totalRank[j-1];
                    totalRank[j-1] = totalRank[j];
                    totalRank[j] = temp;
                }
            }
        }
 
        for(int i=1; i<indeks; i++){
            if (totalRank[i] == totalRank[i-1]){
                currentRank[i] = currentRank[i-1];
            }else{
                currentRank[i] = currentRank[i-1] + 1;
            }
        }
        
        for (int i=indeks-1; i>-1; i--){
            if (indeksCek < m){
                if (totalRank[i] == player[indeksCek]){
                    System.out.println(currentRank[p]);
                    indeksCek++;
                }
            }
            p--;
        }
    }
}
