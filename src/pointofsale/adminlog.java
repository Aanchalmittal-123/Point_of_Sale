package pointofsale;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.sql.*;
public class adminlog extends JFrame implements ActionListener{
    JLabel l1,l2,l3;
    JTextField tf;
    JPasswordField tf2;
    JButton b1;
    adminlog(){
        setSize(600,600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.yellow);
        Font f = new Font(Font.MONOSPACED,Font.BOLD,25);
        l1 = new JLabel("EMAIL");
        l1.setBounds(50,80,150,100);
        l1.setFont(f);
        add(l1);
        l2 = new JLabel("PASSWORD");
        l2.setBounds(20,180,150,100);
        l2.setFont(f);
        add(l2);
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("pointofsale\\icons\\user1.jpg"));
        Image i2 = i1.getImage().getScaledInstance(600,600,Image.SCALE_DEFAULT);
        ImageIcon i3 =  new ImageIcon(i2);
        l3 = new JLabel(i3);
        l3.setBounds(0,0,600,600);
        l3.setFont(f);
        add(l3);
        tf = new JTextField(" ");
        tf.setBounds(200,100,200,40);
        add(tf);
        tf2 = new JPasswordField(" ");
        tf2.setBounds(200,200,200,40);
        tf2.setBackground(Color.WHITE);
        add(tf2);
        setLayout(null);
        setVisible(true);
        b1 = new JButton("OKAY");
        b1.setBounds(400,500,100,40);
        b1.addActionListener(this);
        b1.setBackground(Color.BLACK);
        b1.setBackground(Color.WHITE);
        b1.setFont(f);
        add(b1);
    }
    public static void main(String[] args) {
        adminlog obj = new adminlog();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if((tf.getText()).equals(" ") || (tf2.getText()).equals(" "))
        {
            JOptionPane.showMessageDialog(this,"ALL FIELDS REQUIRED");
        }
        else{
        try{
        String s= tf.getText();
        String s1=tf2.getText();
        System.out.println(s);
        Pointofsale cc = new Pointofsale();
        ResultSet rs = cc.st.executeQuery("select * from admin where email='"+s+"' and password='"+s1+"'");
        if(rs.next()){
            dispose();
            adminhome obj3 = new adminhome();
        }
        else{
            System.out.println("hello1");
        }}
        catch(Exception e1){
            e1.printStackTrace();
        }}
    }
}
