/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yzproje1;

import java.util.Arrays;
import java.util.Random;
/**
 *
 * @author pc
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        long[][] reportMatrix = new long[21][3]; //rapor matrisi
        
        long gecenSure = 0;
        
        for(int i=0; i < 20; i++){
            int h;
            int moves = 0;
            int randomRestarts = 0;
            long baslangicSuresi = gecenSure;
            final int maxTry = 20; //maksimum deneme sayısı
            
            Queen[] table = randomStart();

            printState(table);

            h = findHeuristic(table);
            System.out.println(h);

            while(h != 0){
                int tempH = h;
                table = nextState(table);
                h = findHeuristic(table);
                printState(table);
                System.out.println("Çakışma sayısı: "+h);
                System.out.println("");
                if(h < tempH){
                    moves++;
                }else{
                    randomRestarts++;
                }
            }

            System.out.println("Final state");

            printState(table);
            System.out.println("Çakışma sayısı: "+findHeuristic(table));
            System.out.println("Vezir oynatma sayısı: "+ moves +" \nRastegele yeniden başlatma sayısı: "+ randomRestarts);
            reportMatrix[i][0] = moves;
            reportMatrix[i][1] = randomRestarts;
            
            
            //Zaman hesabı ve daha okunabilir hale çevrilmesi
            gecenSure = System.nanoTime()- baslangicSuresi;
            /*String gecenSureStr = String.valueOf(gecenSure);
            gecenSureStr = gecenSureStr.substring(0,3);
            gecenSure = Integer.parseInt(gecenSureStr);
            */
            reportMatrix[i][2] = gecenSure;
            
            System.out.println("geçen süre (nanosaniye) : " + gecenSure);
        }
        
        System.out.println("----Rapor----");
        long ortYerd = 0;
        long ortRandomr = 0;
        long ortSure = 0;
        
        for(int j = 0;j < 20; j++){
            
            System.out.println("Çözüm "+ j + ": ");
            System.out.println("Yer değiştirme: " + reportMatrix[j][0] + " Yeniden başlatma: " +
                    reportMatrix[j][1] +
                    " Geçen süre: " + reportMatrix[j][2]);
            ortYerd += reportMatrix[j][0];
            ortRandomr += reportMatrix[j][1];
            ortSure += reportMatrix[j][2];
            
        }
        long x = 1223543;
        System.out.println("Ortalamalar:");
        System.out.println("Yer değiştirme: " + ortYerd/20 +
                " Yeniden başlatma: " + ortRandomr/20 +
                " Geçen süre: " + ortSure/20);
            
        
        
    }
    
    public static Queen[] nextState(Queen[] table){
        
        Queen[] maxTable = new Queen[8];
        try{
        for(int i = 0; i < 8; i++){maxTable[i] =(Queen) table[i].clone();} //copying process
        }catch(Exception CloneNotSupportedException){
            System.out.println("Error");
        }
        
        
        int bestHeuristic = findHeuristic(table);
        int tmpH = 99999;
        boolean localMax = true; //default true
        
        for(int i = 0; i < 8; i ++){
            
            Queen[] tempTable = new Queen[8];
            try{
                for(int a = 0; a < 8; a++){tempTable[a] =(Queen) table[a].clone();} //copying process
                }
            catch(Exception CloneNotSupportedException){
            System.out.println("Error");
            }
            
            
            for(int ii = 0; ii < 8; ii++){
                tempTable[i].setRow(ii);
                tmpH = findHeuristic(tempTable);
                if(tmpH < bestHeuristic){
                    bestHeuristic = tmpH;
                    try{
                        for(int x = 0; x < 8; x++){maxTable[x] =(Queen) tempTable[x].clone();} //copying process
                        }
                    catch(Exception CloneNotSupportedException){
                        System.out.println("Error");
                        }
                    localMax = false;
                }
            }
        }
        
        if(localMax){ //local max konumundaysak random restart atıyoruz
            maxTable = randomStart();
            
        }
       
        return maxTable;
    }
    
    public static Queen[] randomStart(){
        
        Random x = new Random();
        
        Queen[] table = new Queen[8];
        
        for(int i = 0; i < 8; i++){
            table[i] = new Queen(x.nextInt(8), i);
        }
        
        return table;
        
    }
    
    public static int findHeuristic(Queen[] table){
        int h = 0;
        for(int i = 0; i < 8; i++){
            Queen a = table[i];
            for(int ii = i + 1; ii < 8; ii++){
                if(a.conflict(table[ii])){
                    h++;
                }
            }
        }
        return h;
    }
    
    public static void printState(Queen[] table){
        
        for(int i = 0; i < 8; i++){
           int[] indexes = new int[8];
            for(int ii = 0; ii < 8; ii++){
                if(table[ii].getRow() == i){
                    indexes[table[ii].getColumn()] = 1;
                }
                
            }
            for(int x = 0; x < 8; x++){
                System.out.print(indexes[x]);
            }
            System.out.println("");
        }
        
    }
    
}
