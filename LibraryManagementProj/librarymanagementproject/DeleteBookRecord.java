/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarymanagementproject;

import java.awt.Font;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;
import java.util.*;

public class DeleteBookRecord {
    
    JFrame f= new JFrame("Library Management: DELETE BOOK RECORD");
    JLabel l=new JLabel("Delete Book Record");
    JLabel l1=new JLabel("Enter Book's ISBN");
    JTextField tf1=new JTextField();
    JButton b=new JButton("Delete");
    EventHandler handler = new EventHandler();
    ArrayList<String> ISBN=new ArrayList();
    
    public DeleteBookRecord(){
        
        f.setSize(700,500);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        l.setBounds(230,5,220,50);
        l.setFont(new Font("Times New Roman", Font.BOLD, 25));
        l1.setBounds(180,90,150,30);
        tf1.setBounds(300,90,150,30);
        b.setBounds(400,130,100,30);
        b.addActionListener(handler);
        f.add(l); f.add(l1); f.add(tf1); f.add(b);
        f.setLayout(null);
        f.setVisible(true);
    }
    
    private class EventHandler implements ActionListener{ 
        @Override
        public void actionPerformed(ActionEvent e) {
             if(e.getSource()==b){
                String BookISBN=tf1.getText(); 
                try{
                    Connection connection = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-6VDVMSE:1433;databaseName=LibraryManagment;userName=Hareem;password=123");
                    String sql="Select BookISBN FROM Book";
                    PreparedStatement statement = connection.prepareStatement(sql);
                    ResultSet result=statement.executeQuery();
                    while(result.next()) {
                        String isbn=result.getString("BookISBN");
                        ISBN.add(isbn.trim());
                        System.out.println(ISBN);
                    }
                    if(ISBN.contains(BookISBN)) {
                        PreparedStatement statement1=connection.prepareStatement("DELETE FROM Book WHERE BookISBN ='"+BookISBN+"'");
                        statement1.executeUpdate();
                   
                        JOptionPane.showMessageDialog(f,"Record Deleted");
                        f.dispose();
                        Menu m=new Menu();
                    }
                    else {
                        JOptionPane.showMessageDialog(f,"Book doesnt exist in Record");
                    }
                    
                } 
                catch (SQLException s){
                    
                    JOptionPane.showMessageDialog(f,"Record does not exist");
                    s.printStackTrace();
                } 
            }
  
          
        }  
    
    }   
    
    public static void main(String[] args){
        new DeleteBookRecord();
    }
}