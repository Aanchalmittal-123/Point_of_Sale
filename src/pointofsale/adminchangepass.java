package pointofsale;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.JFrame;
import javax.swing.*;

public class adminchangepass extends JFrame implements ActionListener{
    JLabel l,l1,l2,l3,l4;
    JTextField tf;
    JPasswordField tf2,tf3,tf4;
    JButton b1;
    adminchangepass(){
        setLayout(null);
        setSize(600,600);
        Font f = new Font(Font.MONOSPACED,Font.BOLD,20);
        l1 = new JLabel("EMAIL");
        l1.setBounds(50,80,150,100);
        l1.setFont(f);
        add(l1);
        tf = new JTextField(" ");
        tf.setBounds(200,100,200,40);
        add(tf);
        tf2 = new JPasswordField(" ");
        tf2.setBounds(200,200,200,40);
        tf2.setBackground(Color.WHITE);
        add(tf2);
        l2 = new JLabel("OLD PASSWORD");
        l2.setBounds(20,160,150,100);
        l2.setFont(f);
        add(l2);
        l3 = new JLabel("NEW PASSWORD");
        l3.setBounds(20,240,150,100);
        l3.setFont(f);
        add(l3);
        l4 = new JLabel("PLEASE VERIFY");
        l4.setBounds(20,320,200,100);
        l4.setFont(f);
        add(l4);
        tf3 = new JPasswordField(" ");
        tf3.setBounds(200,280,200,40);
        add(tf3);
        tf4 = new JPasswordField(" ");
        tf4.setBounds(200,360,200,40);
        tf4.setBackground(Color.WHITE);
        add(tf4);
        b1 = new JButton("SUBMIT");
        b1.setBounds(400,500,180,40);
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        b1.setFont(f);
        b1.addActionListener(this);
        add(b1);
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("pointofsale\\icons\\online1.jpg"));
        Image i = i1.getImage().getScaledInstance(600,600,Image.SCALE_DEFAULT);
        ImageIcon i2 = new ImageIcon(i);
        l = new JLabel(i2);
        l.setBounds(0, 0, 600, 600);
        add(l);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    public static void main(String[] args) {
        adminchangepass obj = new adminchangepass();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       if((tf.getText()).equals(" ") || (tf2.getText()).equals(" ") || (tf3.getText()).equals(" ") || (tf4.getText()).equals(" "))
        {
            JOptionPane.showMessageDialog(this,"ALL FIELDS REQUIRED");
        }
       else{
        try{
        String s= tf.getText();
        String s1=tf2.getText();
        String s2=tf3.getText();
        String s3=tf4.getText();
        if(s2.equals(s3)){
        Pointofsale cc1 = new Pointofsale();
        ResultSet rs = cc1.st.executeQuery("select * from admin where email='"+s+"' and password='"+s1+"'");
        if(rs.next()){
            rs.moveToCurrentRow();
            rs.updateString("password", s2);
            rs.updateRow();
            JOptionPane.showMessageDialog(this,"PASSWORD CHANGED!!");           
        }
        else{
            JOptionPane.showMessageDialog(this,"CANT RECOGNISE YOU");
        }}
        else{
            JOptionPane.showMessageDialog(this,"NEW PASSWORD MISMATCH");
        }
    }catch(Exception e3){e3.printStackTrace();}}
}
}