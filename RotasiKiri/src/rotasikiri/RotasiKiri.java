/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rotasikiri;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Dell
 */
public class RotasiKiri {

    /**
     * @param args the command line arguments
     */
    
    private ArrayList<Integer> listNilai = new ArrayList<>();
    private int numOfRatation;
    private int n;
    
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner input = new Scanner(System.in);
        
        RotasiKiri rk = new RotasiKiri();
        
        System.out.print("Masukkan jumlah nilai List (n) : ");
        rk.n = input.nextInt();
        
        for(int i=0; i<rk.n; i++){
            System.out.print("Masukkan Nilai Pada Indeks("+i+") : ");
            int data = input.nextInt();
            rk.listNilai.add(data);
        }
        
        System.out.println("ArrayList sebelum ada pergeseran : ");
        rk.outputData(rk.listNilai);
        
        System.out.println("");
        
        System.out.print("Masukkan element untuk menggeser : ");
        rk.numOfRatation = input.nextInt();
        
        rk.rotateLeft(rk.listNilai, rk.numOfRatation);
        
        System.out.println("ArrayList Sesudah ada pergeseran : ");
        rk.outputData(rk.listNilai);
    }
    
    private void rotateLeft(ArrayList<Integer> listNilai, int numOfRotation){
        int indeks = 0;
        ArrayList<Integer> dummyList = new ArrayList<>();
        for (int i=numOfRotation; i<listNilai.size(); i++){
            dummyList.add(listNilai.get(i));
            listNilai.remove(i);
            listNilai.add(indeks, dummyList.get(indeks));
            indeks++;
        }
    }
    
    private void outputData(ArrayList<Integer> listNilai){
        for(int i=0; i<listNilai.size(); i++){
            System.out.print(listNilai.get(i));
            System.out.print(",");
        }
    }
}
