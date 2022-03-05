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


public class SearchBookRecord {
    
    JFrame f= new JFrame("Library Management: SEARCH BOOK RECORD");
    JLabel l=new JLabel("Search Book Record");
    JLabel l1=new JLabel("Enter Book's ISBN");
    JLabel Title=new JLabel();
    JLabel BISBN=new JLabel();
    JLabel Language=new JLabel();
    JLabel Edition=new JLabel();
    JLabel Pages=new JLabel();
    JLabel Author=new JLabel();
    JLabel Publisher=new JLabel();
    JLabel genre=new JLabel();
    JLabel LISBN=new JLabel();
                    JLabel LLanguage=new JLabel();
                    JLabel LGenre=new JLabel();
                    JLabel LEdition=new JLabel();
                    JLabel LPages=new JLabel();
                    JLabel LTitle=new JLabel();
                    JLabel LAuthor=new JLabel();
                    JLabel LPublisher=new JLabel();
    JTextField tf1=new JTextField();
    JButton b=new JButton("Search");
    JButton menu=new JButton("Go To Main menu");
    EventHandler handler = new EventHandler();
    
    public SearchBookRecord(){
        f.setSize(700,500);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        l.setBounds(230,5,220,50);
        l.setFont(new Font("Times New Roman", Font.BOLD, 25));
        l1.setBounds(180,90,150,30);
        tf1.setBounds(300,90,150,30);
        b.setBounds(400,130,100,30);
        b.setBounds(400,130,100,30);
        menu.setBounds(250,130,140,30);
        menu.addActionListener(handler);
        BISBN.setBounds(220,170,100,30);
                    Language.setBounds(220,200,100,30);
                    genre.setBounds(220,230,100,30);
                    Edition.setBounds(220,260,100,30);
                    Pages.setBounds(220,290,100,30);
                    Title.setBounds(220,320,100,30);
                    Author.setBounds(220,350,100,30);
                    Publisher.setBounds(220,380,100,30);
                    LISBN.setBounds(320,170,150,30);
                    LLanguage.setBounds(320,200,150,30);
                    LGenre.setBounds(320,230,150,30);
                    LEdition.setBounds(320,260,150,30);
                    LPages.setBounds(320,290,150,30);
                    LTitle.setBounds(320,320,150,30);
                    LAuthor.setBounds(320,350,150,30);
                    LPublisher.setBounds(320,380,150,30);
        b.addActionListener(handler);
        f.add(l); f.add(l1); f.add(tf1); f.add(b); f.add(menu);
        f.add(Title);
        f.add(Title); f.add(BISBN); f.add(Language); f.add(genre); f.add(Edition);
                    f.add(Pages); f.add(Author); f.add(Publisher);
                    f.add(LTitle); f.add(LISBN); f.add(LLanguage); f.add(LGenre); f.add(LEdition);
                    f.add(LPages); f.add(LAuthor); f.add(LPublisher);
                    f.revalidate();
        f.setLayout(null);
        f.setVisible(true);
    }
    
    private class EventHandler implements ActionListener{ 
        @Override
        public void actionPerformed(ActionEvent e) {
        
            if(e.getSource()==b){
                String BookISBN=tf1.getText();
                  Title.setText("Title");
                    BISBN.setText("ISBN");
                    Language.setText("Language");
                    Edition.setText("Edition");
                    Pages.setText("Pages");
                    Author.setText("Author");
                    Publisher.setText("Publisher");
                    genre.setText("Genre");
                        
                                                
                try{
                    Connection connection = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-6VDVMSE:1433;databaseName=LibraryManagment", "Hareem", "123");
                    String sql="Select * from BOOK where BookISBN='"+BookISBN+"'";
                    PreparedStatement statement = connection.prepareStatement(sql);
                    ResultSet result = statement.executeQuery();
                    if(result.next())
                    {
                        String Isbn=result.getString("BookISBN");
                        LISBN.setText(Isbn);
                        String Language=result.getString("BookLanguage");
                        LLanguage.setText(Language);
                        String Genre=result.getString("BookGenre");
                        LGenre.setText(Genre);
                        String Edition=result.getString("BookEdition");
                        LEdition.setText(Edition);
                        String Pages=result.getString("BookPages");
                        LPages.setText(Pages);
                        String Title=result.getString("BookTitle");
                        LTitle.setText(Title);
                        String Author=result.getString("BookAuthor");
                        LAuthor.setText(Author);
                        String Publisher=result.getString("BookPublisher");
                        LPublisher.setText(Publisher);
                        
                        
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
        new SearchBookRecord();
    }

    
}