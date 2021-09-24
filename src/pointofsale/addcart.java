package pointofsale;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;

public class addcart extends JFrame implements ActionListener{
    Pointofsale cc = new Pointofsale();
    JLabel l1,l2,l3,l4,l5,l11,l12,lls,lll;
    JButton b1,b2,b3;
    JTextField t1,t2,t12;
    JPanel p;
    int flag=1;
    JScrollPane ss;
    JTable tt;
    tm tm1 ;
    String k1;
    ArrayList<cart> data = new ArrayList<>();
    String ssss,pn,sst,mno;
    int count=0;
    int sss,s1,ss1;
    int sum,grandtotal=0;
    int a1=0;
    addcart()
    {
    setLayout(null);
    setSize(1000,600);
    setTitle("ADD TO CART");
    l1 = new JLabel("PRODUCT NAME");
    l1.setBounds(50,50,150,50);
    add(l1);
    t1 = new JTextField();
    t1.setBounds(170,65,200,20);
    add(t1); 
    b1 = new JButton("SEARCH");
    b1.setBounds(390,65,100,20);
    add(b1);
    p = new JPanel();
    p.setBounds(50,100,440,100);
    add(p);
    p.setBackground(Color.GREEN);
    p.setLayout(null);
    l3 = new JLabel("");
    l3.setBounds(10,3,100,50);
    p.add(l3);
    l4 = new JLabel("");
    l4.setBounds(110,3,100,50);
    p.add(l4);
    l5 = new JLabel("");
    l5.setBounds(270,3,100,70);
    p.add(l5);
    l2 = new JLabel("QUANTITY");
    l2.setBounds(50,210,150,50);
    add(l2);
    t2 = new JTextField();
    t2.setBounds(170,225,200,20);
    add(t2); 
    ss = new JScrollPane();
    ss.setBounds(50,300,350,200);
    add(ss);
    tt=new JTable();
    tm1 = new tm();
    tt.setModel(tm1);
    ss.setViewportView(tt);    
    b2 = new JButton("ADD");
    b2.setBounds(390,225,100,20);
    add(b2);
    lls = new JLabel("GRANDTOTAL");
    lls.setBounds(200,240,150,50);
    add(lls);
    lll = new JLabel("");
    lll.setBounds(320,240,150,50);
    add(lll);    
    b1.addActionListener(this);
    b2.addActionListener(this);  
    l12 = new JLabel("MOBILE NO.");
    l12.setBounds(50,510,100,20);
    add(l12);
    t12 = new JTextField();
    t12.setBounds(170,510,200,20);
    add(t12); 
    b3 = new JButton("CHECK OUT");
    b3.setBounds(400,510,100,20);
    add(b3);
    b3.addActionListener(this);
    Font f = new Font(Font.MONOSPACED,Font.BOLD,20);
    ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("pointofsale\\icons\\online4.jpg"));
    Image i = i1.getImage().getScaledInstance(1000,600,Image.SCALE_DEFAULT);
    ImageIcon i2 = new ImageIcon(i); 
    l11 = new JLabel(i2);
    l11.setBounds(0, 0, 1000, 600);
    add(l11);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setVisible(true);
    }
    public static void main(String[] args) {
        addcart obj = new addcart();
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==b2){
        t1.setText("");
        l3.setText("");
        l4.setText("");
        l5.setIcon(null);
        getfetch();    
        grandtotal();
        }
        else if(e.getSource()==b1)
        {
           pn = t1.getText();
   
           try{
           ResultSet rss1 = cc.st.executeQuery("select * from product where p_name='"+pn+"'");
           if(rss1.next())
           {
           String ks = rss1.getString("p_name");
           k1= Integer.toString(rss1.getInt("p_price"));
           String k2=rss1.getString("p_photo");
           System.out.println(ks);
           l3.setText(ks);
           l4.setText(k1);
           ImageIcon i11 = new ImageIcon(k2);
           Image i1 = i11.getImage().getScaledInstance(200,60,Image.SCALE_DEFAULT);
           ImageIcon i21 = new ImageIcon(i1); 
           l5.setIcon(i21);
           }
           else{
              JOptionPane.showMessageDialog(this,"THIS PRODUCT DOESNOT EXIST");                    
           }
           }
           catch(Exception m){
               m.printStackTrace();
           }
        }
        else if(e.getSource()==b3){
            if(t12.getText().equals(" ")){
               JOptionPane.showMessageDialog(this,"ENTER YOUR MOBILE NUMBER");                                   
            }
            else{
            try{
            mno=t12.getText();
            ResultSet rss1 = cc.st.executeQuery("select * from bill");
            rss1.moveToInsertRow();
            rss1.updateString("mobile_no",mno);
            rss1.updateInt("grandtotal",grandtotal);
            rss1.insertRow();
            ResultSet rss2 = cc.st.executeQuery("select MAX(b_id) as bid from bill");
            if(rss2.next()){
                int b_id = rss2.getInt("bid");
                ResultSet rss3 = cc.st.executeQuery("select * from bill_detail");
                for (int i = 0; i < data.size(); i++) {
                      rss3.moveToInsertRow();
                      rss3.updateInt("b_id",b_id);
                      rss3.updateInt("p_id",data.get(i).id);
                      rss3.updateString("p_name",data.get(i).name);
                      rss3.updateInt("p_price",data.get(i).price);
                      rss3.updateInt("p_quant",data.get(i).quantity);
                      rss3.updateInt("grandtotal",grandtotal);
                      rss3.insertRow();
                }
                JOptionPane.showMessageDialog(this,"BILL GENERATED SUCCESSFULLY");                                   
            }
           
            }
            catch(Exception aa){
                aa.printStackTrace();
            }}
        }
    }  
    class tm extends AbstractTableModel{
        String nn[]={"NAME","PRICE","QUANTITY","TOTAL PRICE"};
        @Override
        public int getRowCount() {
           return data.size();
        }
        @Override
        public int getColumnCount() {
            return 4;
        }
        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
 
          if(columnIndex==0){
          return (data.get(rowIndex).name); 
          }
          if(columnIndex==1){
          return (data.get(rowIndex).price); 
          }
          if(columnIndex==2){
          return (data.get(rowIndex).quantity); 
          }
          else{
              return data.get(rowIndex).total_price;
          }
          }
        @Override
          public String getColumnName(int columnIndex){
            return nn[columnIndex];  
          }
        }
void getfetch(){
      try{
        String ss3=pn;
        System.out.println("----->--->>>>"+ss3);
        int k=0;
        int c= tt.getRowCount();
        int s22=Integer.parseInt(t2.getText()); 
        for (int i = 0; i < c; i++) {
            String c_name=(String) tt.getModel().getValueAt(i,0);
            System.out.println("***"+c_name);
            System.out.println(ss3);
            if(ss3.equals(c_name)){
            flag=0;
            k =i;
            sum=sum+s22;
            break;
            }
          }
        
          System.out.println("...>>>>"+flag);
        if(flag==1){
        ResultSet rs1 = cc.st.executeQuery("select * from product where p_name='"+pn+"'");
        //data.clear();
        while(rs1.next()){
            s1=rs1.getInt("p_id");
            sst=rs1.getString("p_name");
            ss1=rs1.getInt("p_price");
            sss=Integer.parseInt(t2.getText()); 
            a1=ss1*sss;
            data.add(new cart(s1,sst,ss1,sss,a1));
            System.out.println(ss);
        }
        tm1.fireTableDataChanged();
      }
        else{
           int f=Integer.parseInt(t2.getText());
           JOptionPane.showMessageDialog(this,"SAME ITEM SELECTED");  
           for(cart cs : data){
               System.out.println("--->"+cs.id+"-----"+s1);
               if(cs.id==s1){
                   sss=cs.quantity+f;
                   System.out.println("1----->"+sss);
                   data.remove(cs);
                   break;
               }
           }
           a1=sss*ss1;
           data.add(new cart(s1,sst,ss1,sss,a1));
           tm1.fireTableDataChanged();           
        }
        grandtotal();
      }
        catch(Exception e2){
            e2.printStackTrace();
        }}
void grandtotal(){
    grandtotal=0;
    for(cart cd : data){
        grandtotal+=cd.total_price;
    }
    lll.setText(grandtotal+" ");
}
}
