/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarymanagementproject;

import java.awt.Font;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;


public class AddNewMember {
    
    JFrame f= new JFrame("Library Management: ADD NEW MEMBER");
    JLabel l=new JLabel("Add New Member");
    JLabel l1=new JLabel("Enter ID");
    JLabel l2=new JLabel("Enter First Name");
    JLabel l3=new JLabel("Enter Last Name");
        JLabel l4=new JLabel("Enter Phone Number");
        JLabel l5=new JLabel("Enter Email");
        JLabel l6=new JLabel("Enter Address");
        JLabel l7=new JLabel("Enter ZipCode");
        JTextField tf1=new JTextField();
        JTextField tf2=new JTextField();
        JTextField tf3=new JTextField();
        JTextField tf4=new JTextField();
        JTextField tf5=new JTextField();
        JTextField tf6=new JTextField();
        JTextField tf7=new JTextField();
        JButton b=new JButton("Enter");
        EventHandler handler = new EventHandler();
    
    public AddNewMember(){
        
        f.setSize(700,500);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        l.setBounds(230,5,220,50);
        l.setFont(new Font("Times New Roman", Font.BOLD, 25));
        l1.setBounds(20,90,150,30);
        l2.setBounds(20,130,150,30);
        l3.setBounds(20,170,150,30);
        l4.setBounds(20,210,150,30);
        l5.setBounds(20,250,150,30);
        l6.setBounds(20,290,150,30);
        l7.setBounds(20,330,150,30);
        tf1.setBounds(160,90,150,30);
        tf2.setBounds(160,130,150,30);
        tf3.setBounds(160,170,150,30);
        tf4.setBounds(160,210,150,30);
        tf5.setBounds(160,250,150,30);
        tf6.setBounds(160,290,150,30);
        tf7.setBounds(160,330,150,30);
        b.setBounds(270,370,100,30);
        b.addActionListener(handler);
        f.add(b);
        f.add(l);  f.add(l2); f.add(l3); f.add(l4);
        f.add(l5); f.add(l6); f.add(l7); 
        f.add(l1);
        f.add(tf1); f.add(tf2); f.add(tf3); f.add(tf4);
        f.add(tf5); f.add(tf6); f.add(tf7); f.add(tf7); 
        f.setLayout(null);
        f.setVisible(true);
        
        
    }
    
    private class EventHandler implements ActionListener{ 
        @Override
        public void actionPerformed(ActionEvent e) {
           
            if(e.getSource()==b){
                if(!(tf1.getText().trim().equals("") || tf2.getText().trim().equals("") || tf3.getText().trim().equals("") || tf4.getText().trim().equals("") || tf5.getText().trim().equals("") || tf6.getText().trim().equals("") 
               || tf7.getText().trim().equals(""))) {
                    String ID=tf1.getText();
                    String FName=tf2.getText();
                    String LName=tf3.getText();
                    String Phone=tf4.getText();
                    String Email=tf5.getText();
                    String Address=tf6.getText();
                    String ZipCode=tf7.getText();
                    try{
                        Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=MyLibSystem", "ariba", "ariba1401");
                        Statement stmt1 = conn.createStatement();
                        stmt1.executeUpdate("Insert into Member values ('"+ID+"','"+FName+"','"+LName+"','"+Phone+"','"+Email+"','"+Address+"','"+ZipCode+"')");
                        }
                    catch (SQLException f) {
                    f.printStackTrace();
                 }     
                    JOptionPane.showMessageDialog(f,"New Member Added");
                    f.dispose();
                    Menu m=new Menu();
                }
                else {
                    JOptionPane.showMessageDialog(null, "Fill all the fields" , "Message", JOptionPane.PLAIN_MESSAGE);
                    tf1.setText("");
                    tf2.setText("");
                    tf3.setText("");
                    tf4.setText("");
                    tf5.setText("");
                    tf6.setText("");
                    tf7.setText("");
                    f.setVisible(true);
                }
            }  
        }  
    
    }   
    
    public static void main(String[] args){
        new AddNewMember();
    }
    
}