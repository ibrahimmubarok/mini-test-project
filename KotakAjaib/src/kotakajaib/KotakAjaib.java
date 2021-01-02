/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kotakajaib;

import java.util.Scanner;

/**
 *
 * @author Dell
 */
public class KotakAjaib {

    /**
     * @param args the command line arguments
     */
    
    private int[][][] matrix = new int[3][3][3];
    private int[] sumRow = new int[3];
    private int[] sumCol = new int[3];
    private int sumDia = 0;
    private int n = 1;
    
    public static void main(String[] args) {
        // TODO code application logic here
        
        KotakAjaib kotakAjaib = new KotakAjaib();
        
        Scanner input = new Scanner(System.in);       
        
        System.out.println("Input Data : ");
        for(int i=0; i<kotakAjaib.n; i++){
            for(int j=0; j<kotakAjaib.matrix.length; j++){
                for (int k=0; k<kotakAjaib.matrix.length; k++){
                    System.out.print("Masukkan Nilai pada matriks("+j+")("+k+") *Nilai Input (1-9) : ");
                    kotakAjaib.matrix[i][j][k] = input.nextInt();
                }
            }
        }
        
        kotakAjaib.makingSquare(kotakAjaib.matrix);
    }
    
    private void makingSquare(int[][][] matrix){
        int[] sumRow = new int[3];
        int[] sumCol = new int[3];
        int sumDia = 0;
        int max = 0;
        int x = 0;
        
        for(int i=0; i<matrix.length; i++){
            for(int j=0; j<matrix.length; j++){
                sumRow[i] = sumRow[i] + matrix[0][i][j];
            }
        }
        
        for(int i=0; i<matrix.length; i++){
            for(int j=0; j<matrix.length; j++){
                sumCol[i] = sumCol[i] + matrix[0][j][i];
            }
        }
        
        for(int i=0; i<matrix.length; i++){
            sumDia = sumDia + matrix[0][i][i];
        }
        
        max = sumDia;
        
        for(int i=0; i<sumRow.length; i++){
            if (sumRow[i] > max || sumCol[i] > max){
                if (sumRow[i] > sumCol[i]){
                    max = sumRow[i];
                }else if(sumCol[i] > sumRow[i]){
                    max = sumCol[i];
                }else{
                    max = sumCol[i];
                }
            }
        }
        
        for(int i=0; i<n; i++){
            for(int j=0; j<matrix.length; j++){
                for (int k=0; k<matrix.length; k++){
                    System.out.print(matrix[i][j][k]);
                    System.out.print(" ");
                }
                System.out.println(" ");
            }
        }
        
        for(int i=0; i<3; i++){
            System.out.println("Jumlah ROW("+(i+1)+") : "+sumRow[i]);
            System.out.println("Jumlah COLUMN("+(i+1)+") : "+sumCol[i]);
        }
        
        System.out.println("Jumlah DIAGONAL : "+sumDia);
        System.out.println("NILAI TERBESAR : "+max);
        
        x = max / 3;
        
        matrix[1][0][0] = matrix[1][2][0] = matrix[1][2][2] = x+1;
        matrix[1][1][0] = matrix[1][1][1] = matrix[1][2][1] = x;
        matrix[1][0][1] = matrix[1][1][2] = x+2;
        matrix[1][0][2] = x-1;
        
        for(int i=0; i<n; i++){
            for(int j=0; j<matrix.length; j++){
                for (int k=0; k<matrix.length; k++){
                    System.out.print(matrix[1][j][k]);
                    System.out.print(" ");
                }
                System.out.println(" ");
            }
        }
        
    }
}