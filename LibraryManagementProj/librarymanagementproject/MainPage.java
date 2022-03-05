/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarymanagementproject;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.*;
import java.beans.EventHandler;
import javax.swing.*;
/**
 *
 * @author SAMAN
 */
public class MainPage {
    JFrame f= new JFrame("Library Management System");
    JLabel l1=new JLabel("Library");
    JLabel l2=new JLabel("Management");
    JLabel l3=new JLabel("System");
    JButton b1=new JButton("Login");
    EventHandler handler=new EventHandler();
    
    
    public MainPage()
    {
        f.setContentPane(new JLabel(new ImageIcon("C:\\Users\\SAMAN\\Documents\\Downloads\\15.jpeg")));
        f.setLayout(new FlowLayout());
        f.setSize(700,500);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        l1.setBounds(30,40,200,90);
        l1.setForeground(Color.BLACK);
        l1.setFont(new Font("Times New Roman", Font.BOLD, 48));
        l2.setBounds(230,60,300,60);
        l2.setForeground(Color.BLACK);
        l2.setFont(new Font("Times New Roman", Font.BOLD, 48));
        l3.setBounds(520,60,220,60);
        l3.setForeground(Color.BLACK);
        l3.setFont(new Font("Times New Roman", Font.BOLD, 48));
        b1.setBounds(280,310,150,30);
        b1.setForeground(Color.BLACK);
        b1.setFont(new Font("Times New Roman", Font.BOLD, 11));
        b1.addActionListener(handler);  
        f.add(l1);
        f.add(l2);
        f.add(l3);
        f.add(b1);
        f.setLayout(null);
        f.setVisible(true);
    }
    private class EventHandler implements ActionListener{
    
    
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==b1){
            f.dispose();
            GUILaunchPage gui;
            gui = new GUILaunchPage();
        }
        }
    }
    public static void main(String[] args){
        
        new MainPage();
    }
    }


