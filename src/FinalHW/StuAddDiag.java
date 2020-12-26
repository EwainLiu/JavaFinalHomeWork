package FinalHW;

import javax.swing.JDialog;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.*;
import java.util.Calendar;

public class StuAddDiag extends JDialog implements ActionListener {
    //定义我需要的swing组件
    JLabel jl1,jl2,jl3,jl4,jl5,jl6;
    JTextField jf1,jf2,jf4,jf6;
    JRadioButton jf3Man,jf3Woman;
    JComboBox jf5;
    JPanel jp1,jp2,jp3,jpsex;//,jp4,jp5;
    JButton jb1,jb2;
    //owner代笔父窗口,title是窗口的名字,modal指定是模式窗口()或者非模式窗口
    public StuAddDiag(Frame owner,String title, boolean modal){
        //调用父类方法
        super(owner,title,modal);

        jl1 = new JLabel("工资号");
        jl2 = new JLabel("姓名");
        jl3 = new JLabel("性别");
        jl4 = new JLabel("工资");
        jl5 = new JLabel("级别");
        jl6 = new JLabel("就职时间");

        jf1 = new JTextField(10);
        jf2 = new JTextField(10);


        jf3Man = new JRadioButton("男");
        jf3Woman = new JRadioButton("女");
        ButtonGroup bg =new ButtonGroup();
        bg.add(jf3Man);
        bg.add(jf3Woman);

        jf4 = new JTextField(8);
        jf5 = new JComboBox();
        jf5.setModel(new DefaultComboBoxModel(new String[] {"普通员工","经理","总监","总裁"}));
        jf6 = new JTextField(8);

        jb1 = new JButton("添加");
        jb1.addActionListener(this);
        jb2 = new JButton("取消");

        jp1 = new JPanel();
        jp2 = new JPanel();
        //jp4 = new JPanel();
        //jp5 = new JPanel();
        jp3 = new JPanel();


        //设置布局
        jp1.setLayout(new GridLayout(6,1));
        jp2.setLayout(new GridLayout(6,1));
        //jp4.setLayout(new GridLayout(1,1));
        //jp5.setLayout(new GridLayout(3,1));

        jp3.add(jb1);
        jp3.add(jb2);

        jp1.add(jl1);
        jp1.add(jl2);
        jp1.add(jl3);
        jp1.add(jl4);
        jp1.add(jl5);
        jp1.add(jl6);

        jp2.add(jf1);
        jp2.add(jf2);
        jpsex = new JPanel();
        jp2.add(jpsex);
        jpsex.add(jf3Man);
        jpsex.add(jf3Woman);
        jp2.add(jf4);
        jp2.add(jf5);
        jp2.add(jf6);

        this.add(jp1, BorderLayout.WEST);
        this.add(jp2, BorderLayout.CENTER);
        this.add(jp3, BorderLayout.SOUTH);

        this.setSize(300,250);
        this.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if(e.getSource() == jb1){
            Connection ct = null;
            PreparedStatement pstmt = null;
            ResultSet rs = null;

            try{
                //1.加载驱动
                Class.forName("com.mysql.cj.jdbc.Driver");
                System.out.println("加载成功");
                //2.连接数据库
                //定义几个常量
                String url = "jdbc:mysql://localhost:3306/misaki?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
                String user = "root";
                String passwd = "Ly993658396";
                ct = DriverManager.getConnection(url,user,passwd);

                //与编译语句对象

                String strsql = "insert into employer values(?,?,?,?,?,?)";
                pstmt = ct.prepareStatement(strsql);

                //给对象赋值
                pstmt.setString(1,jf1.getText());
                pstmt.setString(2,jf2.getText());
                pstmt.setString(3,jf3Man.isSelected()?"男":"女");
                pstmt.setDouble(4,Double.parseDouble(jf4.getText().trim()));
                String[] level = {"普通员工","经理","总监","总裁"};
                pstmt.setString(5,level[jf5.getSelectedIndex()]);
                Calendar date = Calendar.getInstance();
                int year = date.get(Calendar.YEAR);
                pstmt.setInt(6,year-Integer.parseInt(jf6.getText().trim()));

                pstmt.executeUpdate();

                this.dispose();//关闭学生对话框

            }catch(Exception arg1){
                arg1.printStackTrace();
            }finally{
                try{
                    if(rs!=null){
                        rs.close();
                        rs = null;
                    }
                    if(pstmt != null){
                        pstmt.close();
                        pstmt = null;
                    }
                    if(ct != null){
                        ct.close();
                        ct = null;
                    }
                }catch(Exception arg2){
                    arg2.printStackTrace();
                }
            }

        }

    }
}
