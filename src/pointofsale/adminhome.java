package pointofsale;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.JFrame;

public class adminhome extends JFrame implements ActionListener{
    JButton b1,b2,b3;
    JLabel l1;
    adminhome(){
        setLayout(null);
        setSize(600,600);
        Font f = new Font(Font.MONOSPACED,Font.BOLD,20);
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("pointofsale\\icons\\online3.jpg"));
        Image i = i1.getImage().getScaledInstance(600,600,Image.SCALE_DEFAULT);
        ImageIcon i2 = new ImageIcon(i); 
        b1 = new JButton("CHANGE PASSWORD");
        b1.setBounds(50,50,250,30);
        b1.setFont(f);
        b1.setBackground(Color. WHITE);
        b1.setForeground(Color.BLACK);
        add(b1);
        b1.addActionListener(this);
        b2 = new JButton("ADD CATEGORY");
        b2.setBounds(50,150,250,30);
        b2.setFont(f);
        b2.setBackground(Color. WHITE);
        b2.setForeground(Color.BLACK);
        add(b2);
        b3 = new JButton("ADD PRODUCT");
        b3.setBounds(50,250,250,30);
        b3.setFont(f);
        b3.setBackground(Color. WHITE);
        b3.setForeground(Color.BLACK);
        add(b3);
        setVisible(true);
        l1 = new JLabel(i2);
        l1.setBounds(0, 0, 600, 600);
        add(l1);
        b2.addActionListener(this);
        b3.addActionListener(this);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    
       }
    public static void main(String[] args) {
        adminhome obj = new adminhome();
        
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==b1){
        adminchangepass obj2 = new adminchangepass();}
        else if(e.getSource()==b2){
            managecat obb = new managecat();
        }
        else if(e.getSource()==b3){
            product_manage obbj = new product_manage();
        }
    }
}
