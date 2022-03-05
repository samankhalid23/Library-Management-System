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
import java.sql.SQLException;
import java.sql.*;
import javax.swing.*;
import java.util.*;

public class GUILaunchPage{
    
    JFrame f= new JFrame("Library Management");
    //JPanel p=new JPanel();
    JLabel l=new JLabel("Login");
    JLabel l1=new JLabel("Login ID");
    JLabel l2=new JLabel("Password");
    JLabel l3 = new JLabel();
    JTextField f1=new JTextField();
    JTextField f2=new JTextField();
    JButton b1=new JButton("Login");
    EventHandler handler = new EventHandler();
    ArrayList<String> ids=new ArrayList();
    String password="123";
    
    GUILaunchPage() {
        
        //p.setBounds(0,0,700,500);
        //p.setBackground(Color.LIGHT_GRAY);
        f.setSize(700,500);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        l.setBounds(310,30,220,50);
        l.setFont(new Font("Times New Roman", Font.BOLD, 35));  
        l1.setBounds(180,150,120,40);        
        l2.setBounds(180,220,120,40); 
        l3.setBounds(180,300, 120,60);
        f1.setBounds(250,150,200,40);
        f2.setBounds(250,220,200,40);
        b1.setBounds(290,290,120,40);
        b1.addActionListener(handler);
        f.add(l);
        f.add(l1);
        f.add(l2);
        f.add(l3);
        f.add(f1);
        f.add(f2);
        f.add(b1);
        f.setLayout(null);
        f.setVisible(true);
    }
    

    private class EventHandler implements ActionListener{ 
        @Override
        public void actionPerformed(ActionEvent e) {
            
            if(e.getSource()==b1){
                    String s1=f1.getText().trim();
                    String s2=f2.getText();
                    try {
                        Connection conn=DriverManager.getConnection("jdbc:sqlserver://DESKTOP-6VDVMSE:1433;databaseName=LibraryManagment","Hareem","123");
                        PreparedStatement statement=conn.prepareStatement("Select MemberID from Member");
                        ResultSet result= statement.executeQuery();
                        while(result.next()) {
                            ids.add(result.getString("MemberID").trim());
                        }
                        
                        if(ids.contains(s1) && s2.equals(password)){
                        JOptionPane.showMessageDialog(f,"Login Successful");
                        f.dispose();
                        Menu myMenu = new Menu();      
                    }
                    else {
                        JOptionPane.showMessageDialog(f,"Incorrect Credentials");
                        
                        }
                    }
                    catch(SQLException x){
                        JOptionPane.showMessageDialog(f,"Connection Error");
                    }
                    
                    
            }  
        }        
  }
        
    public static void main( String[] args){
        
        new GUILaunchPage();
    }    
    
}