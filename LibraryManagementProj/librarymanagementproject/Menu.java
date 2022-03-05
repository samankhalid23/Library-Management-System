/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarymanagementproject;

import java.awt.Font;
import java.awt.event.*;
import javax.swing.*;


public class Menu {
    
    JFrame f1= new JFrame("Library Management MENU");
    JLabel l=new JLabel("MAIN MENU");
    JButton b1=new JButton("Add Book Record");
    JButton b2=new JButton("Search Book Record");
    JButton b3=new JButton("Delete Book Record");
    JButton b4=new JButton("Add New Member");
    JButton b5=new JButton("Delete Member");
    JButton b6=new JButton("Search Membership");
    JButton b7=new JButton("Create Issuance");
    JButton b8=new JButton("Search Issuance");
    EventHandler handler = new EventHandler();
    
    public Menu(){
        
        f1.setSize(700,500);
        f1.setLocationRelativeTo(null);
        f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        l.setBounds(280,5,180,50);
        l.setFont(new Font("Times New Roman", Font.BOLD, 25));
        b1.setBounds(265,50,180,30);
        b1.addActionListener(handler);
        b2.setBounds(265,100,180,30);
        b2.addActionListener(handler);
        b3.setBounds(265,150,180,30);
        b3.addActionListener(handler);
        b4.setBounds(265,200,180,30);
        b4.addActionListener(handler);
        b5.setBounds(265,250,180,30);
        b5.addActionListener(handler);
        b6.setBounds(265,300,180,30);
        b6.addActionListener(handler);
        b7.setBounds(265,350,180,30);
        b7.addActionListener(handler);
        b8.setBounds(265,400,180,30);
        b8.addActionListener(handler);
        f1.add(l);
        f1.add(b1);
        f1.add(b2);
        f1.add(b3); f1.add(b4); f1.add(b5); f1.add(b6); f1.add(b7); f1.add(b8);
        
        f1.setLayout(null);
        f1.setVisible(true);
        
    }
    
    private class EventHandler implements ActionListener{
    
    
    public void actionPerformed(ActionEvent e) {

         if(e.getSource()==b1){
        
             f1.dispose();
             AddNewBook n = new AddNewBook();
         }
         else if(e.getSource()==b2){
             f1.dispose();
             SearchBookRecord s=new SearchBookRecord();
         }
         else if(e.getSource()==b3){
             f1.dispose();
             DeleteBookRecord d=new DeleteBookRecord();
         }
         else if(e.getSource()==b4){
             f1.dispose();
             AddNewMember m=new AddNewMember();
         }
         else if(e.getSource()==b5){
             f1.dispose();
             DeleteMember d=new DeleteMember();
         }
         else if(e.getSource()==b6){
             f1.dispose();
             SearchMembership m=new SearchMembership();
         }
         else if(e.getSource()==b7){
             f1.dispose();
             CreateIssuance i=new CreateIssuance();
         }
         else if(e.getSource()==b8){
             f1.dispose();
             SearchIssuance i=new SearchIssuance();
         }
    }
    
    } 
    
    public static void main(String[] args){
        
        new Menu();
    }
}