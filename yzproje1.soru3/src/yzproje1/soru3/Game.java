/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yzproje1.soru3;

import java.util.Random;
import java.util.Scanner;
/**
 *
 * @author pc
 */
public class Game {
    
    
    public int turn = 1; //0 = AI, 1 = User
    public Node[] possibleMoves;
    public Node currentState;
    public String playedStates = "A";
    
    public Game(){
    }
    
    
    public void startGame(){
        Scanner a = new Scanner(System.in);
        Random x = new Random();
        possibleMoves = new Node[5];
        
        for(int i = 0; i < 5; i++){
            //tüm düğümleri açıyoruz ve yaprak düğümlere rastgele değerler atıyoruz.
            possibleMoves[i] = new Node(0, i);
            for(int ii = 0; ii < 5; ii++){
                possibleMoves[i].nextStates[ii] = new Node(0, ii);
                for(int iii = 0 ; iii < 5; iii++){
                    possibleMoves[i].nextStates[ii].nextStates[iii] = new Node(0,iii);
                    for(int iv = 0; iv < 5; iv++){
                        int randomNum = x.nextInt(1000) + 1;//1, ile 1000 arasında olması için 1 ekledik
                        possibleMoves[i].nextStates[ii].nextStates[iii].nextStates[iv] = new Node(randomNum, iv);
                    }
                }
            }
        }
        
        //minimax'i tüm ağaç üzerinde uyguluyoruz
        for(int index = 0; index < 5; index++){
            
            miniMax(possibleMoves[index], turn);
            
        }
        
        //oynanış -kendime not: farklı bir metot olarak yazılabilir-
        for(int t = 0; t < 4; t++){
            
            
            
            System.out.print("Sıra:");

            if(turn == 1){
                System.out.println(" Kullanıcı");
            }else{
                System.out.println(" Bilgisayar");
            }

            System.out.println("Olası hamleler ve puanları:");

            for(int i = 0; i < 5; i++){
                System.out.println((i + 1) + ": " + possibleMoves[i].point);
            }

            if(turn == 1){
                System.out.print("Hamle numarası giriniz: ");
                int userInput = a.nextInt();
                currentState = possibleMoves[userInput - 1]; //iterating current state
                playedStates += userInput;
                }
            
            else{
                int minDeger = 1001;
                int hamle = 0;
                for(int c = 0; c < 5; c++){
                    if(possibleMoves[c].point < minDeger){
                        minDeger = possibleMoves[c].point;
                        hamle = c;
                    }
                }
                System.out.println("Bilgisayar oynadı: " + (hamle + 1));
                currentState = possibleMoves[hamle]; //iterating current state
                playedStates += (hamle + 1);
            }
            for(int c = 0; c < 5; c++){//iterating possible states
                possibleMoves[c] = currentState.nextStates[c];
            }

            
            if(turn == 1)
                turn = 0;
            else
                turn = 1;

            

            

        }
        System.out.println("Oyun sonu");
        System.out.println("Oynanan hamleler: " + playedStates);
        
    }
    
    public void miniMax(Node x, int turn){
        //Turn = 0 minimum, turn = 1 maksimum'a ulaşmaya çalışıyor
        
        while (x.point == 0){
            //alttaki node'un yaprak olup olmadığını kontrol et
            
            if(x.nextStates[0].point != 0){
                //Calculate min or max
                
                int deger = -1;
                boolean max;
                
                if(turn == 1)
                    max = true;
                else
                    max = false;
             
                for(int i = 0; i < 5 ; i++){
                    
                    int geciciDeger = x.nextStates[i].point;
                    //bir üstteki düğümün değer hesabı
                    if(deger == -1){
                        deger = geciciDeger;
                    }
                    
                    if(max == true){
                         
                         if(geciciDeger > deger)
                             deger = geciciDeger;
                    }
                    else{
                        if(geciciDeger < deger)
                            deger = geciciDeger;
                    }
                    
                }
                
                x.point = deger;    
                
                
            }else{
                int nextTurn;
                
                if(turn == 1)
                    nextTurn = 0;
                else
                    nextTurn = 1;
                
                for(int i = 0; i < 5; i++){
                    miniMax(x.nextStates[i], nextTurn);
                }
                
            }
            
        }
        
        
    }
    
}
