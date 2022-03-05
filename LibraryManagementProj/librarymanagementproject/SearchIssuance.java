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

public class SearchIssuance {
    
    JFrame f= new JFrame("Library Management: SEARCH ISSUANCE");
    EventHandler handler = new EventHandler();
    JLabel l=new JLabel("Search Issuance");
    JLabel l1=new JLabel("Enter Issue ID");
    JTextField tf1=new JTextField();
    JButton b=new JButton("Search");
    JButton menu=new JButton("Go To Main menu");
    JLabel ID=new JLabel();
                    JLabel IssueDate=new JLabel();
                    JLabel IssueDueDate=new JLabel();
                    JLabel MembersID=new JLabel();
                    JLabel BookISBN=new JLabel();
    JLabel LID=new JLabel();
                    JLabel LIssueDate=new JLabel();
                    JLabel LIssueDueDate=new JLabel();
                    JLabel LMembersID=new JLabel();
                    JLabel LBookISBN=new JLabel();
    
    public SearchIssuance(){
        f.setSize(700,500);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        l.setBounds(230,5,220,50);
        l.setFont(new Font("Times New Roman", Font.BOLD, 25));
        l1.setBounds(200,90,150,30);
        tf1.setBounds(300,90,150,30);
        b.addActionListener(handler);
        b.setBounds(400,130,100,30);
        menu.setBounds(250,130,140,30);
        menu.addActionListener(handler);
        ID.setBounds(220,170,100,30);
        IssueDate.setBounds(220,200,100,30);
        IssueDueDate.setBounds(220,230,100,30);
        MembersID.setBounds(220,260,100,30);
        BookISBN.setBounds(220,290,100,30);
        LID.setBounds(320,170,150,30);
        LIssueDate.setBounds(320,200,150,30);
        LIssueDueDate.setBounds(320,230,150,30);
        LMembersID.setBounds(320,260,150,30);
        LBookISBN.setBounds(320,290,150,30);
        f.add(ID); f.add(IssueDate); f.add(IssueDueDate); f.add(MembersID); 
        f.add(BookISBN);
        f.add(LID); f.add(LIssueDate); f.add(LIssueDueDate); f.add(LMembersID); 
        f.add(LBookISBN);
        f.add(l); f.add(l1); f.add(tf1); f.add(b); f.add(menu);
        f.setLayout(null);
        f.setVisible(true);
    }
    
    private class EventHandler implements ActionListener{ 
        @Override
        public void actionPerformed(ActionEvent e) {
        
            if(e.getSource()==b){
                String IssueID=tf1.getText();
                ID.setText("IssueID");
                        IssueDate.setText("Issue Date");
                        IssueDueDate.setText("Issue Due Date");
                        MembersID.setText("Members ID");
                        BookISBN.setText("Book's ISBN");
                        
                                                
                try{
                    Connection connection = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-6VDVMSE:1433;databaseName=LibraryManagment","Hareem","123");
                    String sql="Select * from BOOK_ISSUANCE where IssueID='"+IssueID+"'";
                    PreparedStatement statement = connection.prepareStatement(sql);
                    ResultSet result = statement.executeQuery();
                    if(result.next())
                    {
                        String ID=result.getString("IssueID");
                        LID.setText(ID);
                        String date=result.getString("IssueDate");
                        LIssueDate.setText(date);
                        String dueDate=result.getString("IssueDueDate");
                        LIssueDueDate.setText(dueDate);
                        String memId=result.getString("MemberID");
                        LMembersID.setText(memId);
                        String ISBN=  result.getString("BookISBN");      
                        LBookISBN.setText(ISBN);
                        
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(f,"Not Found ");
                    }
                        
                }
                catch(SQLException s)
                {
                    JOptionPane.showMessageDialog(f,"Connection error");
                    s.printStackTrace();
                }
                }
               
                if(e.getSource()==menu) {
                    f.dispose();
                    Menu m=new Menu();
                 }
            }  
            
        }  
    public static void main(String[] args){
        new SearchIssuance();
    }
    }   


    

