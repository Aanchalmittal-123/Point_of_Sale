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
public class managecat extends JFrame implements ActionListener {
    
    Pointofsale cc = new Pointofsale();
    JLabel l,l1,l2,l3,l4;
    JTextField tf1;
    JTextArea tf2;
    JTable t1;
    JButton b1,b2,b3;
    tm tm1 ;
    ArrayList<category> data = new ArrayList<>();
    managecat(){
        setLayout(null);
        setSize(1000,600);
        setTitle("MANAGE CATEGORY");
        Font f = new Font(Font.MONOSPACED,Font.BOLD,20);
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("pointofsale\\icons\\online4.jpg"));
        Image i = i1.getImage().getScaledInstance(1000,600,Image.SCALE_DEFAULT);
        ImageIcon i2 = new ImageIcon(i);
        Font f1 = new Font(Font.MONOSPACED,Font.BOLD,20);
        l1 = new JLabel("CATEGORY NAME");
        l1.setBounds(50,50,200,30);
        l1.setBackground(Color.BLACK);
        l1.setForeground(Color.BLACK);
        l1.setFont(f1);
        add(l1);
        tf1 = new JTextField();
        tf1.setBounds(250,50,200,30);
        add(tf1);
        l2 = new JLabel("CATEGORY DETAILS");
        l2.setBounds(50,100,200,30);
        l2.setBackground(Color.BLACK);
        l2.setForeground(Color.black);
        l2.setFont(f1);
        add(l2);
        tf2 = new JTextArea();
        tf2.setBounds(250,100,200,100);
        add(tf2);
        l3 = new JLabel("PHOTO");
        l3.setBounds(50,220,200,30);
        l3.setBackground(Color.BLACK);
        l3.setForeground(Color.BLACK);
        l3.setFont(f1);
        add(l3);
        l4 = new JLabel();
        l4.setBounds(250,220,200,30);
        add(l4);  
        b1 = new JButton("SELECT");
        b1.setBounds(480,220,100,30);
        b1.setBackground(Color.YELLOW);
        add(b1);
        b2 = new JButton("SUBMIT");
        b2.setBounds(400,420,180,30);
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);
        b2.setFont(f1);
        add(b2);
        b3 = new JButton("DELETE");
        b3.setBounds(670,480,180,30);
        b3.setBackground(Color.BLACK);
        b3.setForeground(Color.WHITE);
        b3.setFont(f1);
        add(b3);
        b3.addActionListener(this);
        JScrollPane jp = new JScrollPane();
        jp.setBounds(600,50,350,400);
        add(jp); 
        t1 = new JTable();
        tm1 = new managecat.tm();
        t1.setModel(tm1);
        t1.getColumnModel().getColumn(2).setCellRenderer(new Ima());
        t1.setBackground(Color.YELLOW);
        t1.setForeground(Color.BLACK);

        jp.setViewportView(t1);
        b1.addActionListener(this);
        b2.addActionListener(this);
        l = new JLabel(i2);
        l.setBounds(0, 0, 1000, 600);
        add(l);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getfetch();
    }
  
    
    public static void main(String[] args) {
        managecat obj = new managecat();
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
                ImageIcon i11 = new ImageIcon(s);
                Image i1 = i11.getImage().getScaledInstance(200,30,Image.SCALE_DEFAULT);
                ImageIcon i21 = new ImageIcon(i1); 
                l4.setIcon(i21);
            }
        }
        else if(e.getSource()==b3){
            String c_name=data.get(t1.getSelectedRow()).name;
            try{
                ResultSet rss = cc.st.executeQuery("select * from manage where name='"+c_name+"'");
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
        else if (e.getSource()==b2){
            try{
            String s1=tf1.getText();
            String s3=l4.getText();
            String fname = globalway.savefile(s3);
            System.out.println(fname);
            ResultSet rs = cc.st.executeQuery("select * from manage where name='"+s1+"'");
            if(rs.next()){
                JOptionPane.showMessageDialog(this,"CATEGORY ALREADY EXIST");
            }
            else{
              String s2=tf2.getText(); 
              rs.moveToInsertRow();
              rs.updateString("name", s1);
              rs.updateString("description", s2);
              rs.updateString("photo", fname);
              rs.insertRow();
              JOptionPane.showMessageDialog(this,"INSERTED SUCCESSFULLY!");
              getfetch();
              tf1.setText("");
              tf2.setText("");
              l4.setText("");              
            }
            }
            catch(Exception e1){
                e1.printStackTrace();
            }
        }
    }

void getfetch(){
      try{
        ResultSet rs1 = cc.st.executeQuery("select * from manage");
        data.clear();
        while(rs1.next()){
            String ss=rs1.getString("name");
            String ss1=rs1.getString("description");
            String ss2=rs1.getString("photo"); 
            data.add(new category(ss,ss1,ss2));
            System.out.println(ss);
        }
        tm1.fireTableDataChanged();
        }
        catch(Exception e2){
            e2.printStackTrace();
        }}

  class tm extends AbstractTableModel{
        String nn[]={"NAME","DESCRIPTION","PHOTO"};
        @Override
        public int getRowCount() {
           return data.size();
        }
        @Override
        public int getColumnCount() {
            return 3;
        }
        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
          if(columnIndex==0){
          return (data.get(rowIndex).name); 
          }
          if(columnIndex==1){
          return (data.get(rowIndex).description); 
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