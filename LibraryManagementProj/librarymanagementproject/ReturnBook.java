/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarymanagementproject;
import javax.swing.*;
import java.sql.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;

public class ReturnBook {
    JFrame f= new JFrame("Library Management: RETURN BOOK");
    JLabel l=new JLabel("Return Book");
    JLabel l1=new JLabel("Enter Issue ID");
    JLabel l2=new JLabel("Enter Member ID");
    JLabel l3=new JLabel("Enter Return Date");
    JTextField tf1=new JTextField();
    JTextField tf2=new JTextField();
    JTextField tf3=new JTextField();
    JButton b=new JButton("Enter");
    JButton menu=new JButton("Go To Main menu");
    EventHandler handler = new EventHandler();
    ArrayList<String> MID=new ArrayList();
    ArrayList<String> IID=new ArrayList();
    
    public ReturnBook() {
        f.setSize(700,500);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        l.setBounds(230,5,220,50);
        l.setFont(new Font("Times New Roman", Font.BOLD, 25));
        l1.setBounds(20,90,150,30);
        l2.setBounds(20,130,150,30);
        l3.setBounds(20,170,150,30);
        tf1.setBounds(160,90,150,30);
        tf2.setBounds(160,130,150,30);
        tf3.setBounds(160,170,150,30);
        b.setBounds(240,210,80,30);
        menu.setBounds(330,210,140,30);
        b.addActionListener(handler);
        menu.addActionListener(handler);
        f.add(b); f.add(l1); f.add(menu);
        f.add(l);  f.add(l2); f.add(l3);
        f.add(tf1); f.add(tf2); f.add(tf3);
        f.setLayout(null);
        f.setVisible(true);
    }
    private class EventHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==b) {
                String IssID=tf1.getText().trim();
                String MemID=tf2.getText().trim();
                String Date=tf3.getText().trim();
                try {
                    Connection con=DriverManager.getConnection("jdbc:sqlserver://DESKTOP-6VDVMSE:1433;databaseName=LibraryManagment;userName=Hareem;password=123");
                    PreparedStatement stat=con.prepareStatement("Select IssueID from Book_Issuance" );
                    ResultSet result=stat.executeQuery();
                    while(result.next()) {
                        IID.add(result.getString("IssueID").trim());
                    }
                   PreparedStatement stat1=con.prepareStatement("Select MemberID from Book_Issuance" );
                   ResultSet result1=stat1.executeQuery();
                   while(result1.next()) {
                       MID.add(result1.getString("MemberID").trim());
                   }
                   if(IID.contains(IssID) && MID.contains(MemID)) {
                       PreparedStatement stat2=con.prepareStatement("Insert into Return_Book values ('"+IssID+"','"+MemID+"','"+Date+"')" );
                       stat2.executeUpdate();
                       JOptionPane.showMessageDialog(f,"Book Returned");
                   }
                   else {
                       JOptionPane.showMessageDialog(f,"Book couldn't be returned");
                   }
                }
                catch(SQLException x) {
                    JOptionPane.showMessageDialog(f,"Connection Error");
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
     ReturnBook r=new ReturnBook();   
}    
}

