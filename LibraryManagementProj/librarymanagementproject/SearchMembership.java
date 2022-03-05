/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarymanagementproject;

import java.awt.Font;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.util.*;

public class SearchMembership {
    
    ArrayList<String> MID=new ArrayList();
    JFrame f= new JFrame("Library Management: SEARCH MEMBERSHIP");
    JLabel l=new JLabel("Search Membership");
    JLabel l1=new JLabel("Enter ID");
    JTextField tf1=new JTextField();
    JButton b=new JButton("Search");
    JButton menu=new JButton("Go To Main menu");
    JLabel ID=new JLabel();
    JLabel Fname=new JLabel();
    JLabel LName=new JLabel();
    JLabel No=new JLabel();
    JLabel Email=new JLabel();
    JLabel Address=new JLabel();
    JLabel ZipCode=new JLabel();
    JLabel LID=new JLabel();
                    JLabel LFName=new JLabel();
                    JLabel LLName=new JLabel();
                    JLabel LNo=new JLabel();
                    JLabel LEmail=new JLabel();
                    JLabel LAddress=new JLabel();
                    JLabel LZipCode=new JLabel();               
    EventHandler handler = new EventHandler();
    
    public SearchMembership(){
        
        f.setSize(700,500);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        l.setBounds(230,5,220,50);
        l.setFont(new Font("Times New Roman", Font.BOLD, 25));
        l1.setBounds(210,90,150,30);
        tf1.setBounds(280,90,170,30);
        b.setBounds(400,130,100,30);
        menu.setBounds(250,130,140,30);
        menu.addActionListener(handler);
        b.addActionListener(handler);
        ID.setBounds(220,170,100,30);
        Fname.setBounds(220,200,100,30);
        LName.setBounds(220,230,100,30);
        No.setBounds(220,260,100,30);
        Email.setBounds(220,290,100,30);
        Address.setBounds(220,320,100,30);
        ZipCode.setBounds(220,350,100,30);
        LID.setBounds(320,170,150,30);
        LFName.setBounds(320,200,150,30);
        LLName.setBounds(320,230,150,30);
        LNo.setBounds(320,260,150,30);
        LEmail.setBounds(320,290,150,30);
        LAddress.setBounds(320,320,150,30);
        LZipCode.setBounds(320,350,150,30);
        f.add(ID); f.add(Fname); f.add(LName); f.add(No); f.add(Email);
        f.add(Address); f.add(ZipCode); f.add(menu);
        f.add(LID); f.add(LFName); f.add(LLName); f.add(LNo); f.add(LEmail);
        f.add(LAddress); f.add(LZipCode);
        f.add(l); f.add(l1); f.add(tf1); f.add(b);
        f.setLayout(null);
        f.setVisible(true);  
    }
    
    private class EventHandler implements ActionListener{ 
        @Override
        public void actionPerformed(ActionEvent e) {
           
            if(e.getSource()==b){
                String MemID=tf1.getText().trim();
                try {
                    //Connection connection=DriverManager.getConnection("jdbc:sqlserver://DESKTOP-6VDVMSE:1433;databaseName=LibraryManagment","Hareem","123");
                    //PreparedStatement stat=connection.prepareStatement("Select MemberID from Member");
                    //ResultSet result=stat.executeQuery();
                    //while(result.next()) {
                     //   MID.add(result.getString("MemberID").trim());
                    //}
                    //if(MID.contains(MemID)) {
                        Connection con=DriverManager.getConnection("jdbc:sqlserver://DESKTOP-6VDVMSE:1433;databaseName=LibraryManagment","Hareem","123");
                        PreparedStatement st=con.prepareStatement("Select * from Member where MemberID='"+MemID+"'");
                        ResultSet res=st.executeQuery();
                        if(res.next()) {
                            ID.setText("Member ID");
                            Fname.setText("First Name");
                            LName.setText("Last Name");
                            No.setText("Phone Number");
                            Email.setText("Email");
                            Address.setText("Address");
                            ZipCode.setText("ZipCode");
                            LID.setText(res.getString("MemberID"));
                            LFName.setText(res.getString("MemberFName"));
                            LLName.setText(res.getString("MemberLName"));
                            LNo.setText(res.getString("MemberPhone"));
                            LEmail.setText(res.getString("MemberEmail"));
                            LAddress.setText(res.getString("MemberAddress"));
                            LZipCode.setText(res.getString("MemberZipCode"));
                        }
                        
                    //}
                    else {
                        JOptionPane.showMessageDialog(f,"Member dosent exist");
                    }   
                }
                catch(SQLException x) {
                    JOptionPane.showMessageDialog(f,"Connetion Error");
                    x.printStackTrace();
                }
           
                
            } 
            if(e.getSource()==menu) {
                f.dispose();
                Menu m=new Menu();
            }
    
        }   
    
    }
    public static void main(String[] args) {
        new SearchMembership();
    }
    
}
