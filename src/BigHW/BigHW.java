/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package BigHW;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.*;
import java.io.*;
/**
 *
 * @author Administrator
 */
public class BigHW extends JFrame implements ActionListener
{
    MenuBar mb;
    Menu menu;
    MenuItem[] items;
    LinkedList data;
    TextArea ta;
    final int NUM=6;
    final String Message="This is a login system";
    final String[] colNames={"USER","PASSWORD"};
    final String fileName="D:\\T\\a.data";

    public BigHW()  {
        this.data=new LinkedList();
        this.setGUI();
    }

protected void setGUI()
{
    this.setBounds(150, 150, 500, 300);
    mb=new MenuBar();
    menu=new Menu("OPTIONS");
    menu.setFont(new Font("Arial Black",Font.BOLD,22));
    int i;
    items=new MenuItem[NUM];
    for(i=0;i<this.NUM;i++)
    {
        items[i]=new MenuItem();
        items[i].addActionListener(this);
        menu.add(items[i]);
        items[i].setFont(new Font("Arial Black",Font.BOLD,22));
    }
    items[0].setLabel("Add User"); items[1].setLabel("Print Listings");
     items[2].setLabel("Save Data"); items[3].setLabel("Load Data");
      items[4].setLabel("Search User"); items[5].setLabel("Delete User");
   this.setMenuBar(mb);
   //this.setMenuBar(mb);
   mb.add(menu);
   ta=new TextArea(this.Message,20,25);
   ta.setFont(new Font("Arial Black",Font.BOLD,22));
   //Container c=this.getContentPane();
   ta.setEnabled(false);
   this.setLayout(new BorderLayout());
   this.add(ta,BorderLayout.CENTER);
   this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   this.setVisible(true);
}
protected void addUser()
{
    final JFrame f1=new JFrame("Add A New User");
    f1.setBounds(250, 250, 500, 300);
    String[][] inputData = {{"Input user here","Input password here"}};
    final JTable inputTab=new JTable(inputData,this.colNames);
    inputTab.setFont(new Font("Arial",Font.PLAIN,16));
    inputTab.getTableHeader().setFont(new Font("Arial",Font.PLAIN,16));
    JButton b1=new JButton("RESET");
    JButton b2=new JButton("SUBMIT");
    b1.addActionListener(
    new ActionListener()
    {
        public void actionPerformed(ActionEvent e)
        {
            TableModel jm1=inputTab.getModel();
            for(int i=0;i<inputTab.getRowCount();i++)
            {
                for(int j=0;j<inputTab.getColumnCount();j++)
                {
                    jm1.setValueAt("", i, j);
                }
            }
        }
    }
            );
  b2.addActionListener(
    new ActionListener()
    {
        public void actionPerformed(ActionEvent e)
        {
            TableModel jm1=inputTab.getModel();
            for(int i=0;i<inputTab.getRowCount();i++)
            {
              data.add(new USER((String)jm1.getValueAt(i, 0),(String)jm1.getValueAt(i, 1)));
            }
        }
    }
            );
    JScrollPane jsp=new JScrollPane(inputTab);
    JPanel jp=new JPanel();
    jp.add(b1); jp.add(b2);
    f1.setLayout(new BorderLayout());
    f1.add(jsp,BorderLayout.CENTER);
    f1.add(jp,BorderLayout.SOUTH);
    //f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f1.addWindowListener(
    new WindowAdapter()
    {
        public void windowClosing(WindowEvent we)
        {
            f1.dispose();
        }
    }
            );
    f1.setVisible(true);
}
protected void printListings()
{
     final JFrame f1=new JFrame("Add A New User");
    f1.setBounds(250, 250, 500, 300);
    String[][] inputData = new String[data.size()][2];
    Iterator ptr=data.iterator();
    int i=-1;
    while(ptr.hasNext())
    {
        i++;
        USER u=(USER)ptr.next();
        inputData[i][0]=u.getUser();
        inputData[i][1]=u.getPassword();
    }
    final JTable inputTab=new JTable(inputData,this.colNames);
     inputTab.setFont(new Font("Arial",Font.PLAIN,16));
    inputTab.getTableHeader().setFont(new Font("Arial",Font.PLAIN,16));
     JScrollPane jsp=new JScrollPane(inputTab);
     f1.setLayout(new BorderLayout());
    f1.add(jsp,BorderLayout.CENTER);
    f1.addWindowListener(
    new WindowAdapter()
    {
        public void windowClosing(WindowEvent we)
        {
            f1.dispose();
        }
    }
            );
    f1.setVisible(true);
}
public void searchUser()
{
     final JFrame f1=new JFrame("Search An User");
    f1.setBounds(250, 250, 500, 300);
    String[][] inputData = {{"Input the user name",""}};
    final JTable inputTab=new JTable(inputData,this.colNames);
    inputTab.setFont(new Font("Arial",Font.PLAIN,16));
    inputTab.getTableHeader().setFont(new Font("Arial",Font.PLAIN,16));
    JButton b1=new JButton("RESET");
    JButton b2=new JButton("SUBMIT");
    b1.addActionListener(
    new ActionListener()
    {
        public void actionPerformed(ActionEvent e)
        {
            TableModel jm1=inputTab.getModel();
            for(int i=0;i<inputTab.getRowCount();i++)
            {
                for(int j=0;j<inputTab.getColumnCount();j++)
                {
                    jm1.setValueAt("", i, j);
                }
            }
        }
    }
            );
  b2.addActionListener(
    new ActionListener()
    {
        public void actionPerformed(ActionEvent e)
        {
            TableModel jm1=inputTab.getModel();
            String name=(String)jm1.getValueAt(0,0);
            Iterator ptr=data.iterator();
            boolean found=false;
            String password=null;
            while(ptr.hasNext())
            {
                 USER u=(USER)ptr.next();
                 if(name.equals(u.getUser()))
                 {
                     found=true;password=u.getPassword();
                     break;
                 }
            }
            if(found)jm1.setValueAt(password, 0, 1);
            else jm1.setValueAt("not found", 0, 1);
            /*
            for(int i=0;i<inputTab.getRowCount();i++)
            {
              data.add(new USER((String)jm1.getValueAt(i, 0),(String)jm1.getValueAt(i, 1)));
            }*/
        }
    }
            );
    JScrollPane jsp=new JScrollPane(inputTab);
    JPanel jp=new JPanel();
    jp.add(b1); jp.add(b2);
    f1.setLayout(new BorderLayout());
    f1.add(jsp,BorderLayout.CENTER);
    f1.add(jp,BorderLayout.SOUTH);
    //f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f1.addWindowListener(
    new WindowAdapter()
    {
        public void windowClosing(WindowEvent we)
        {
            f1.dispose();
        }
    }
            );
    f1.setVisible(true);
}
public void saveData()
{
    try
    {
        FileOutputStream fos=new FileOutputStream(this.fileName);
        ObjectOutputStream oos=new ObjectOutputStream(fos);
        oos.writeObject(this.data);
        oos.flush();
        oos.close();
        fos.close();
    }catch(Exception e)
    {
        e.printStackTrace();
    }
}
public void loadData()
{
    try
    {
        FileInputStream fos=new FileInputStream(this.fileName);
        ObjectInputStream oos=new ObjectInputStream(fos);
        LinkedList oldData = (LinkedList)oos.readObject();
        this.data.addAll(oldData);
    }catch(Exception e)
    {
        e.printStackTrace();
    }
}

public static void main(String[] args)
{
    new BigHW();
}
    public void actionPerformed(ActionEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
        if(e.getSource()==items[0])this.addUser();
        else if(e.getSource()==items[1])this.printListings();
        else if(e.getSource()==items[2])this.saveData();
        else if(e.getSource()==items[3])this.loadData();
        else if(e.getSource()==items[4])this.searchUser();
    }

}
class USER implements Serializable
{
    private String user,password;

    public USER(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public USER() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

}