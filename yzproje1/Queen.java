/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yzproje1;
import java.math.*;
/**
 *
 * @author pc
 */
public class Queen implements Cloneable{
    
    private int row;
    private int column;
    
    public Queen(int row,int column){
        this.row = row;
        this.column = column;
    }
    
    public Object clone() throws CloneNotSupportedException
    {
        return super.clone();
    }
    
    public boolean conflict (Queen a){
        
        if(this.row == a.row){
            return true;
            
        }
        if(Math.abs(this.row - a.row) == Math.abs(this.column - a.column)){
            return true;
        }
        
       
        return false;
    }
    
    /*public void moveUp(){
        row++;
    }
    public void moveDown(){
        row--;
    }*/

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setColumn(int column) {
        this.column = column;
    }
    
    
}
