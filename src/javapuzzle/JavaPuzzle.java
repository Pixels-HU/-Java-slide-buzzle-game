/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javapuzzle;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;
import java.util.Collections;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


/*
 * @author mmedh
 */

/*---------------------------  Our class implements ActionListener  ------------------------------------*/
public class JavaPuzzle implements ActionListener{
    JFrame frame; 
    int size = 4;
    JButton btn[][] = new JButton[size][size];
    
    /*------------------------  Constructor  ---------------------------*/
    /*
    we can define our app in the constructor and then call the constructor in main function
    */
    JavaPuzzle(){
        frame = new JFrame("Java Puzzle");
        frame.setVisible(true);
        frame.setSize(400, 400);
        frame.setLayout(new GridLayout(size, size));
        
//        frame.addKeyListener(new KeyListener(){
//            @Override
//            public void keyTyped(KeyEvent e) {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//            }
//
//            @Override
//            public void keyPressed(KeyEvent e) {
//                int keyCode = e.getKeyCode();
//                switch(keyCode){
//                    case KeyEvent.VK_UP:
//                        
//                        break;
//                }
//            }
//
//            @Override
//            public void keyReleased(KeyEvent e) {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//            }
//        });
        
        // we can customize font by using Font class
        Font font = new Font("Arial Rounded MT", Font.PLAIN , 40);
        // we can get the dimensions (width & height) of our screen by using Dimension class
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        /* I used setLocation to make my app shows in the middle of the screen */
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().width/2);
        String filled[] = {"14", "2", "", "5", "10", "8", "3", "1", "6", "11", "13", "4", "7", "15", "9", "12"};
        /* this is used for shuffle the array to make it different every time you open the game */
        Collections.shuffle(Arrays.asList(filled));
        int num = 0;
        /* this loop for add every button to frame and add array field to each button and to set the font that we made and also add ActionListener to every button */
        for(int i=0 ; i < size ; i++){
            for(int k=0 ; k < size ; k++){
                btn[i][k] = new JButton(filled[num++]);
                frame.add(btn[i][k]);
                btn[i][k].setFont(font);
                btn[i][k].setBackground(new Color(102,0,153));
                btn[i][k].setForeground(Color.white);
                btn[i][k].addActionListener(this);
            }
        }  
    }
    // this function executes when user click the button
     @Override
    public void actionPerformed(ActionEvent e) {
        /*the first two loops is to select every button on the board*/
        for(int i=0 ; i < size ; i++){
            for(int k=0 ; k < size ; k++){
                /* this if condition is to check if the button that i pressed is the button that selected from loop or not */
                if(e.getSource() == btn[i][k]){
                    /* we get the text of the button on String variable */
                    String s = btn[i][k].getText();
                    /*
                    the first if to check if button that selected is not in the first row of the board
                    */
                    if(i != 0){
                        
                        /*
                        we checked if the button before the one we selected (in the same column) is the empty button or not
                        if it's so switch the text from the two buttons
                        */
                        
                        if(btn[i-1][k].getText().equals("")){
                            btn[i-1][k].setText(s);
                            btn[i][k].setText("");
                        }
                    }
                    /*
                    the first if to check if button that selected is not in the last row of the board
                    */
                    if(i != size-1){
                        /*
                        we checked if the button after the one we selected (in the same column) is the empty button or not
                        if it's so switch the text from the two buttons
                        */
                        if(btn[i+1][k].getText().equals("")){
                            btn[i+1][k].setText(s);
                            btn[i][k].setText("");
                        }
                    }
                    /*
                    the first if to check if button that selected is not in the first column of the board
                    */
                    if(k != 0){
                        /*
                        we checked if the button before the one we selected (in the same row) is the empty button or not
                        if it's so switch the text from the two buttons
                        */
                        if(btn[i][k-1].getText().equals("")){
                            btn[i][k-1].setText(s);
                            btn[i][k].setText("");
                        }
                    }
                    /*
                    the first if to check if button that selected is not in the last column of the board
                    */
                    if(k != size-1){
                        /*
                        we checked if the button after the one we selected (in the same row) is the empty button or not
                        if it's so switch the text from the two buttons
                        */
                        if(btn[i][k+1].getText().equals("")){
                            btn[i][k+1].setText(s);
                            btn[i][k].setText("");
                        }
                    }
                }
            }
        }
        /* all this for detect if user won or not */
        boolean flag = true;
        int x = 0;
        for(int i=0 ; i < size ; i++){
            for(int k=0 ; k < size ; k++){
                String m = btn[i][k].getText();
                x++;
                
                if( !m.equals("") ){
                    int a = Integer.parseInt(m);
                    if(a != x){
                        flag = false;
                    }
                }
            }
        } 
        /* if we win please show optionPane */
        if(flag){
            btn[3][3].setText("16");
            JOptionPane.showMessageDialog(frame, "Congratulations !! You won");
        }
    }
    public static void main(String[] args) {    
        new JavaPuzzle();        
    }
}
