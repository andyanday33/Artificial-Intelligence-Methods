/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yzproje1.soru3;

/**
 *
 * @author pc
 */
public class Node {
    
    public Node[] nextStates;
    public int point;
    public int stateNo;
    
    public Node(int point, int stateNo){
            this.point = point;
            this.stateNo = stateNo;
            this.nextStates = new Node[5];
       }
    
    
}
