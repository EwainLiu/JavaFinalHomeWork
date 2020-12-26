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

public class StuUpDiag extends JDialog implements ActionListener {
    //��������Ҫ��swing���
    JLabel jl1,jl2,jl3,jl4,jl5,jl6;
    JTextField jf1,jf2,jf3,jf4,jf6;
    JPanel jp1,jp2,jp3,jpsex;
    JButton jb1,jb2;
    JRadioButton jf3Man,jf3Woman;
    JComboBox jf5;
    //owner���ʸ�����,title�Ǵ��ڵ�����,modalָ����ģʽ����()���߷�ģʽ����
    public StuUpDiag(Frame owner,String title, boolean modal,StuModel sm,int rowNum){
        //���ø��෽��
        super(owner,title,modal);

        jl1 = new JLabel("���ʺ�");

        jl2 = new JLabel("����");

        jl3 = new JLabel("�Ա�");
        jl4 = new JLabel("����");
        jl5 = new JLabel("����");
        
        jl6 = new JLabel("����");


        jf1 = new JTextField(10);
        jf1.setText((sm.getValueAt(rowNum, 0)).toString());

        jf2 = new JTextField(10);
        jf2.setText((String)sm.getValueAt(rowNum, 1));

        jf3Man = new JRadioButton("��");
        jf3Woman = new JRadioButton("Ů");
        ButtonGroup bg =new ButtonGroup();
        bg.add(jf3Man);
        bg.add(jf3Woman);

//        jf3 = new JTextField(10);
//        jf3.setText(sm.getValueAt(rowNum, 2).toString());

        jf4 = new JTextField(10);
        jf4.setText((sm.getValueAt(rowNum, 3)).toString());

//        jf5 = new JTextField(10);
//        jf5.setText((String)sm.getValueAt(rowNum, 4));
        jf5 = new JComboBox();
        jf5.setModel(new DefaultComboBoxModel(new String[] {"��ͨԱ��","����","�ܼ�","�ܲ�"}));


        jf6 = new JTextField(10);
        //jf6.setText((String)sm.getValueAt(rowNum, 5));
        jf6.setText(sm.getValueAt(rowNum,5).toString());

        jb1 = new JButton("�޸�");
        jb1.addActionListener(this);
        jb2 = new JButton("ȡ��");

        jp1 = new JPanel();
        jp2 = new JPanel();
        jp3 = new JPanel();

        //���ò���
        jp1.setLayout(new GridLayout(6,1));
        jp2.setLayout(new GridLayout(6,1));

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
        //jp2.add(jf3);
        jpsex = new JPanel();
        jpsex.add(jf3Man);
        jpsex.add(jf3Woman);
        jp2.add(jpsex);

        jp2.add(jf4);
        jp2.add(jf5);
        jp2.add(jf6);

        this.add(jp1, BorderLayout.WEST);
        this.add(jp2, BorderLayout.CENTER);
        this.add(jp3, BorderLayout.SOUTH);

        this.setSize(200,250);
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
                //1.��������
                Class.forName("com.mysql.cj.jdbc.Driver");
                System.out.println("���سɹ�");
                //2.�������ݿ�
                //���弸������
                String url = "jdbc:mysql://localhost:3306/misaki?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
                String user = "root";
                String passwd = "Ly993658396";
                ct = DriverManager.getConnection(url,user,passwd);

                //�����������

                String strsql = "insert into employer values(?,?,?,?,?,?)";
                pstmt = ct.prepareStatement(strsql);

                //������ֵ
                pstmt.setString(1,jf1.getText());
                pstmt.setString(2,jf2.getText());
                //pstmt.setString(3,jf3.getText());
                pstmt.setString(3,jf3Man.isSelected()?"��":"Ů");
                pstmt.setString(4,jf4.getText());
                String[] level = {"��ͨԱ��","����","�ܼ�","�ܲ�"};
                pstmt.setString(5,level[jf5.getSelectedIndex()]);
                pstmt.setString(6,jf6.getText());

                pstmt.executeUpdate();

                this.dispose();//�ر�ѧ���Ի���

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
