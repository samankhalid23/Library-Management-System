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
import java.util.Date;
import java.text.SimpleDateFormat; 
import java.text.ParseException; 
import java.util.*;

public class CreateIssuance {
    
    JFrame f= new JFrame("Library Management: CREATE ISSUANCE");
    EventHandler handler = new EventHandler();
    ArrayList<String> ID=new ArrayList();
    ArrayList<String> ISBN=new ArrayList();
    JButton b=new JButton("Enter");
    JLabel l=new JLabel("Enter Data to Issue Book");
    JLabel l1=new JLabel("Enter Issue ID");
    JLabel l2=new JLabel("Enter Issue Date");
    JLabel l3=new JLabel("Enter Issue Due Date");
    JLabel l4=new JLabel("Enter Member's ID");
    JLabel l5=new JLabel("Enter Book's ISBN");
    JTextField tf1=new JTextField();
    JTextField tf2=new JTextField();
    JTextField tf3=new JTextField();
    JTextField tf4=new JTextField();
    JTextField tf5=new JTextField();
         
    
    public CreateIssuance(){
        f.setSize(700,500);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        b.setBounds(260,290,100,40);
        b.addActionListener(handler);
        l.setBounds(230,5,290,50);
        l.setFont(new Font("Times New Roman", Font.BOLD, 25));
        l1.setBounds(20,90,150,30);
        l2.setBounds(20,130,150,30);
        l3.setBounds(20,170,150,30);
        l4.setBounds(20,210,150,30);
        l5.setBounds(20,250,150,30);
        tf1.setBounds(160,90,150,30);
        tf2.setBounds(160,130,150,30);
        tf3.setBounds(160,170,150,30);
        tf4.setBounds(160,210,150,30);
        tf5.setBounds(160,250,150,30);
        f.add(b);
        f.add(l);  f.add(l2); f.add(l3); f.add(l4);
        f.add(l5); f.add(l1);
        f.add(tf1); f.add(tf2); f.add(tf3); f.add(tf4);
        f.add(tf5); 
        f.setLayout(null);
        f.setVisible(true);
    }
    
    private class EventHandler implements ActionListener{ 
        @Override
        public void actionPerformed(ActionEvent a) {
            
            if(a.getSource()==b){
                
                if(!(tf1.getText().trim().equals("") || tf2.getText().trim().equals("") || tf3.getText().trim().equals("") || tf4.getText().trim().equals("") || tf5.getText().trim().equals("")))
                {
                    String IssueID=tf1.getText().trim();
                    String IssueDDate=(tf2.getText()).trim();
                    String IssueDate=(tf3.getText()).trim();
                    String MemberId=tf4.getText().trim();
                    String BookISBN=tf5.getText().trim();
                    try{
                        Connection conn = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-6VDVMSE:1433;databaseName=LibraryManagment;userName=Hareem;password=123");
                        PreparedStatement statement1=conn.prepareStatement("Select BOOKISBN from Book");
                        ResultSet result=statement1.executeQuery();
                        while(result.next()) {
                            ISBN.add(result.getString("BookISBN").trim());
                        }
                        PreparedStatement statement2=conn.prepareStatement("Select MemberID from Member");
                        ResultSet result2=statement2.executeQuery();
                        while(result2.next()) {
                            ID.add(result2.getString("MemberID").trim());
                        }
                    
                        if(ISBN.contains(BookISBN) && ID.contains(MemberId)) {
                            Statement stmt = conn.createStatement();
                            stmt.executeUpdate("Insert into Book_Issuance values ('"+IssueID+"','"+IssueDDate+"','"+IssueDate+"','"+MemberId+"','"+BookISBN+"')");
                            JOptionPane.showMessageDialog(f,"Issuance created");
                            f.dispose();
                            Menu m=new Menu();
                        }
                        else {
                            JOptionPane.showMessageDialog(f,"Wrong BOOKISBN or MemberID entered");
                        }
                        
                        

                    } 
                    catch (SQLException x) {
                        x.printStackTrace(); 
                    }
                   
                        
                }
                else {
                    JOptionPane.showMessageDialog(null, "Fill all the fields" , "Message", JOptionPane.PLAIN_MESSAGE);
                    tf1.setText("");
                    tf2.setText("");
                    tf3.setText("");
                    tf4.setText("");
                    tf5.setText("");
                    f.setVisible(true);
                }
                
               
                
            }  
        }  
    
    }   


    public static void main(String[] args){
        new CreateIssuance();
    }

    
}