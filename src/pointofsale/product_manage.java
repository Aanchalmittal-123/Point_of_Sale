package pointofsale;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
public class product_manage extends JFrame implements ActionListener{
    Pointofsale cc = new Pointofsale();
    JLabel l11,l1,l2,l3,l4,l5,l12;
    JTextField f1,f3;
    JTextArea f2;
    JComboBox cb1,cb2;
    JButton b1,b2,b3,b4;
    JTable t1;
    JScrollPane jp;
    tm tm1 ;
    ArrayList<product> data = new ArrayList<>();

    product_manage(){
      setLayout(null);
      setSize(1000,600);
      setTitle("PRODUCT MANAGEMENT");
      Font f = new Font(Font.MONOSPACED,Font.BOLD,20);
      ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("pointofsale\\icons\\online4.jpg"));
      Image i = i1.getImage().getScaledInstance(1000,600,Image.SCALE_DEFAULT);
      ImageIcon i2 = new ImageIcon(i);
      l1 = new JLabel("CATEGORY NAME");
      l1.setBounds(50,50,250,20);
      l1.setBackground(Color.BLACK);
      l1.setForeground(Color.BLACK);
      l1.setFont(f);
      add(l1);
      cb1 = new JComboBox();
      cb1.setBounds(250,50,200,30);
      add(cb1);
      try{
      ResultSet rs = cc.st.executeQuery("select * from manage");
      while(rs.next()){
      String s = rs.getString("name");
      cb1.addItem(s);
      }}
      catch(Exception e){
          e.printStackTrace();
      }
      cb2 = new JComboBox();
      cb2.setBounds(650,50,200,30);
      add(cb2);
      try{
      ResultSet rs2 = cc.st.executeQuery("select * from manage");
      while(rs2.next()){
      String ss = rs2.getString("name");
      cb2.addItem(ss);
      }}
      catch(Exception e){
          e.printStackTrace();
      }
      l2 = new JLabel("PRODUCT NAME");
      l2.setBounds(50,100,200,30);
      l2.setBackground(Color.BLACK);
      l2.setForeground(Color.black);
      l2.setFont(f);
      add(l2);
      f1 = new JTextField();
      f1.setBounds(250,100,200,30);
      add(f1);
      l3 = new JLabel("DESCRIPTION");
      l3.setBounds(50,150,200,30);
      l3.setBackground(Color.BLACK);
      l3.setForeground(Color.black);
      l3.setFont(f);
      add(l3);
      f2 = new JTextArea();
      f2.setBounds(250,150,200,100);
      add(f2);
      l4 = new JLabel("PRICE");
      l4.setBounds(50,280,200,30);
      l4.setBackground(Color.BLACK);
      l4.setForeground(Color.black);
      l4.setFont(f);
      add(l4);
      f3 = new JTextField();
      f3.setBounds(250,280,200,30);
      add(f3);
      l5 = new JLabel("PHOTO");
      l5.setBounds(50,340,200,30);
      l5.setBackground(Color.BLACK);
      l5.setForeground(Color.BLACK);
      l5.setFont(f);
      add(l5);
      l12 = new JLabel();
      l12.setBounds(250,340,200,40);
      add(l12);
      b1 = new JButton("SELECT");
      b1.setBounds(480,340,100,30);
      b1.setBackground(Color.YELLOW);
      add(b1);
      b2 = new JButton("SUBMIT");
      b2.setBounds(400,480,150,30);
      b2.setBackground(Color.BLACK);
      b2.setForeground(Color.WHITE);
      b2.setFont(f);
      add(b2);
      b3 = new JButton("SEARCH");
      b3.setBounds(880,50,90,30);
      b3.setBackground(Color.BLACK);
      b3.setForeground(Color.WHITE);
      add(b3);
      b3.addActionListener(this);
      b1.addActionListener(this);
      b2.addActionListener(this);
      t1 = new JTable();
      jp = new JScrollPane();
      jp.setBounds(620,95,350,400);
      add(jp);
      jp.setViewportView(t1);
      b4 = new JButton("DELETE");
      b4.setBounds(890,500,80,30);
      b4.setBackground(Color.BLACK);
      b4.setForeground(Color.WHITE);
      add(b4);
      b4.addActionListener(this);
      l11 = new JLabel(i2);
      l11.setBounds(0, 0, 1000, 600);
      add(l11);
      setVisible(true);
      setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    public static void main(String[] args) {
        product_manage g1 = new product_manage();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==b1){
            JFileChooser ch = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Image files", "jpg", "png", "bmp");
            ch.setAcceptAllFileFilterUsed(false);
            ch.setFileFilter(filter);
            int r = ch.showOpenDialog(this);
            if(r==JFileChooser.APPROVE_OPTION)
            {
                File f = ch.getSelectedFile();
                String s = f.getPath();
                l12.setText(s);
            }
        }
        else if(e.getSource()==b2){
            if((f1.getText()).equals(" ") || (f2.getText()).equals(" ") || (f3.getText()).equals(" ") )
            {
            JOptionPane.showMessageDialog(this,"ALL FIELDS REQUIRED");
            }
            else{
                try{
                String s1 = f1.getText();
                String s2 = (String)cb1.getSelectedItem();
                String s3 = f2.getText();
                int s4 = Integer.parseInt(f3.getText());
                String s5 = l12.getText();
                ResultSet rs1 = cc.st.executeQuery("select * from product where p_name='"+s1+"' and name='"+s2+"'");
                if(rs1.next()){
                  JOptionPane.showMessageDialog(this,"PRODUCT ALREADY EXISTS");
                }
                else{
                    rs1.moveToInsertRow();
                    rs1.updateString("p_name",s1);
                    rs1.updateString("p_desc",s3);
                    rs1.updateInt("p_price",s4);
                    rs1.updateString("p_photo",s5);
                    rs1.updateString("name", s2);
                    rs1.insertRow();
                    JOptionPane.showMessageDialog(this,"INSERTED SUCCESSFULLY!");
                }
                }
                catch(Exception ae){
                    ae.printStackTrace();
                }
            }
        }
        else if(e.getSource()==b3){
            tm1 = new tm();
            t1.setModel(tm1);
            t1.getColumnModel().getColumn(4).setCellRenderer(new Ima());
            t1.setBackground(Color.YELLOW);
            t1.setForeground(Color.BLACK);
            getfetch();
        }
        else if(e.getSource()==b4){
            int pid=data.get(t1.getSelectedRow()).id;
            try{
                ResultSet rss = cc.st.executeQuery("select * from product where p_id="+pid);
                if(rss.next()){
                  rss.deleteRow();
                  getfetch();
                  JOptionPane.showMessageDialog(this,"Deleted Successfully");
                }
                else{
                  JOptionPane.showMessageDialog(this,"PLEASE SELECT THE ROW");
                    
                }
            }
            catch(Exception ee){
                ee.printStackTrace();
            }
        }
    }
void getfetch(){
      try{
        ResultSet rs3 = cc.st.executeQuery("select * from product");
        data.clear();
        while(rs3.next()){
            String s1=Integer.toString(rs3.getInt("p_id"));
            String ss=rs3.getString("p_name");
            String ss1=rs3.getString("p_desc");
            String ss2=Integer.toString(rs3.getInt("p_price")); 
            String ss3=rs3.getString("p_photo");           
            data.add(new product(s1,ss,ss1,ss2,ss3));
        }
        tm1.fireTableDataChanged();
        }
        catch(Exception e2){
            e2.printStackTrace();
        }}

  class tm extends AbstractTableModel{
        String nn[]={"ID","NAME","DESC","PRICE","PHOTO"};
        @Override
        public int getRowCount() {
           return data.size();
        }
        @Override
        public int getColumnCount() {
            return 5;
        }
        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
          if(columnIndex==0){
          return (data.get(rowIndex).id); 
          }
          if(columnIndex==1){
          return (data.get(rowIndex).name); 
          }
          if(columnIndex==2){
          return (data.get(rowIndex).description); 
          }
          if(columnIndex==3){
          return (data.get(rowIndex).price); 
          }
          else{
              return data.get(rowIndex).photo;
          }
          }
        @Override
          public String getColumnName(int columnIndex){
            return nn[columnIndex];  
          }
        }
 class Ima extends DefaultTableCellRenderer {
     JLabel lb11 = new JLabel();
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            try {
                t1.setRowHeight(80);
                ImageIcon i1 = new ImageIcon(data.get(row).photo);
                Image i2 = i1.getImage();
                Image i3 = i2.getScaledInstance(120, 200, Image.SCALE_SMOOTH);
                lb11.setIcon(new ImageIcon(i3));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return lb11;
        }
 }
}
