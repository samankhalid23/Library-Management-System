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

public class DeleteMember {
    
    JFrame f= new JFrame("Library Management: DELETE MEMBER");
    EventHandler handler = new EventHandler();
    JLabel l=new JLabel("Delete Membership");
    JLabel l1=new JLabel("Enter Membership No.");
    JTextField tf1=new JTextField();
    JButton b=new JButton("Delete");
    ArrayList<String> ids=new ArrayList();
    ArrayList<String> fkMember=new ArrayList();
    
    
    public DeleteMember(){
        
        f.setSize(700,500);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        l.setBounds(230,5,220,50);
        l.setFont(new Font("Times New Roman", Font.BOLD, 25));
        l1.setBounds(170,90,150,30);
        tf1.setBounds(310,90,150,30);
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
                String ID=tf1.getText().trim();
                try {
                    Connection connection=DriverManager.getConnection("jdbc:sqlserver://DESKTOP-6VDVMSE:1433;databaseName=LibraryManagment;userName=Hareem;password=123");
                    PreparedStatement statement = connection.prepareStatement("select MemberID from Member");
                    ResultSet result = statement.executeQuery();
                    while(result.next()) {
                        String IDS=result.getString("MemberID");
                        ids.add(IDS.trim());
                       
                   }
                    if(ids.contains(ID)) {
                        PreparedStatement stat=connection.prepareStatement("Select MemberID from Book_Issuance");
                        
                        ResultSet result2 = stat.executeQuery();
                      
                        while(result2.next()) {
                           
                            String Fk=result2.getString("MemberID").trim();
                            fkMember.add(Fk);
                        }
                        System.out.println(fkMember);
                        if(fkMember.contains(ID)) {
                            
                            JOptionPane.showMessageDialog(f,"Cannot be deleted Foreign Key Constraint");
                        }
                        else {
                            PreparedStatement statement2=connection.prepareStatement("Delete from Member where MemberID='"+ID+"'");
                            statement2.executeUpdate();
                            JOptionPane.showMessageDialog(f,"Member Deleted from Record");
                            f.dispose();
                            Menu m=new Menu();
                        }
                        
                       
                        
                    }
                    else {
                        JOptionPane.showMessageDialog(f,"Member doesnt exist in Record");
                    }
                }
                catch(SQLException x) {
                    JOptionPane.showMessageDialog(f,"Connection error");
                    x.printStackTrace();
                }
                
            }  
        }  
    
    }   


    public static void main(String[] args){
        new DeleteMember();
    }

    
}