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
       
        int h;
        int moves = 0;
        int randomRestarts = 0;
        int[][] reportMatrix = new int[21][3]; //rapor matrisi
        final int maxTry = 20; //maksimum deneme sayısı
        
        Queen[] table = randomStart();
        
        printState(table);
        
        h = findHeuristic(table);
        System.out.println(h);
        
        while(h != 0){
            
            table = nextState(table, moves, randomRestarts);
            h = findHeuristic(table);
            printState(table);
            System.out.println(h);
            System.out.println("");
        }
        printState(table);
        System.out.println(findHeuristic(table));
        System.out.println(moves +" "+ randomRestarts);
        
    }
    
    public static Queen[] nextState(Queen[] table,int moves,int randomRestarts){
        
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
            randomRestarts++;
        }
        moves++;
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
