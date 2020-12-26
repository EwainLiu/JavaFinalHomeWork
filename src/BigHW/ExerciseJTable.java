package BigHW;/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author Administrator
 */
public class ExerciseJTable extends JFrame
{
protected void setGUI()
{
    this.setBounds(150, 150, 500, 300);
   String[] heads={"NAME","AGE","SALARY"};
   Object[][] data={{"mia",new Integer(35),new Double(3000)},
   {"Ham",new Integer(35),new Double(5000)}};
   JTable jt=new JTable(data,heads);
   JScrollPane jsp=new JScrollPane(jt);
   this.setLayout(new BorderLayout());
   this.add(jsp,BorderLayout.CENTER);
   this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   this.setVisible(true);
}

    public ExerciseJTable() {
        this.setGUI();
    }
public static void main(String[] args)
{
    new ExerciseJTable();
}
}
