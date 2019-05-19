package design;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class AdminLog {
	JFrame Jf;
	JPanel Jp;
	JLabel wlcm,usrlb,pwlb;
	JTextField usrtf;
	JPasswordField usrpf;
	JButton logbt,rstbt,back,frwd,test;
	JScrollPane Jpane;
	
	AdminLog()
	{
		Jf=new JFrame("ADMIN LOGIN");
		Jp=new mypanel();
		Jp.setLayout(null);
		//Jp.setBackground(Color.decode("#F3FAB6"));
		
		wlcm=new JLabel("Admin Login");
		wlcm.setBounds(530,10,400,45);
		wlcm.setForeground(Color.RED);
		Font f1=new Font("Brush Script MT",Font.BOLD,46);
		wlcm.setFont(f1);
		Jp.add(wlcm);
		
		back=new JButton("< Back");
		back.setBounds(10,60,100,25);
		
		back.addActionListener(new backn());
		Jp.add(back);
		if(Welcome.bstack.empty())
		{
			back.setEnabled(false);
		}
		frwd=new JButton("Forward >");
		frwd.setBounds(120,60,100,25);
		if(Welcome.fstack.empty())
		{
			frwd.setEnabled(false);
		}
		frwd.addActionListener(new frwdn());
		Jp.add(frwd);
		
		usrlb=new JLabel("Admin ID");
		usrlb.setFont(new Font("Arial",Font.BOLD,17));
		usrlb.setBounds(400,180,100,25);
		Jp.add(usrlb);
		pwlb=new JLabel("Password");
		pwlb.setFont(new Font("Arial",Font.BOLD,17));
		pwlb.setBounds(650,180,100,25);
		Jp.add(pwlb);
		usrtf=new JTextField();
		usrtf.setBounds(490,180,130,30);
		Jp.add(usrtf);
		usrpf=new JPasswordField();
		usrpf.setBounds(740,180,130,30);
		Jp.add(usrpf);   
		
		logbt=new JButton("LOGIN");
		logbt.setBounds(520,250,100,35);
		logbt.addActionListener(new logbtn());
		Jp.add(logbt);
		
		rstbt=new JButton("Reset");
		rstbt.setBounds(630,250,100,35);
		rstbt.addActionListener(new rstbtn());
		Jp.add(rstbt);
				
		/*test=new JButton("Test");
		test.setBounds(500,600,100,30);
		Jf.add(test);
		
		Jpane = new JScrollPane(Jp,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		Jf.setPreferredSize(new Dimension(120,90));
		Jf.add(Jpane);*/
		
		//Jf.add(Jp);
		//Jf.setLocation(500,0);
		
		Jf.add(Jp);
		Jf.setVisible(true);
		Jf.setSize(Jf.getToolkit().getScreenSize());
		Jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	class mypanel extends JPanel
	{
		private static final long serialVersionUID = 1L;
		
		public void paintComponent(Graphics g)
		{
			Image i1= new ImageIcon("images/beautiful.jpeg").getImage();
			g.drawImage(i1,0,0,this.getWidth(),this.getHeight(),this);
	
		}
	}
	
	class logbtn implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0) {
			
			String aid=usrtf.getText();
			String pwd=usrpf.getText();
			Connection cnt=DataBaseConnection.javaConnection();
			try {
				String qq= "Select * from accountinfo where name=? and college=?";
				PreparedStatement stt = cnt.prepareStatement(qq);
				stt.setString(1,aid);
				stt.setString(2,pwd);
				ResultSet rr= stt.executeQuery();
				if(rr.next())
				{
					JLabel msg=new JLabel("Welcome Admin",JLabel.CENTER);
					JOptionPane.showMessageDialog(null,msg,"",JOptionPane.PLAIN_MESSAGE);
					Jf.dispose();
					new AdminHome(aid,rr.getString("name"));
				}
				else
				{
					JOptionPane.showMessageDialog(null,"Invalid Acc.no or password");
					usrtf.setText(null);
					usrpf.setText("");
				}
			} catch (SQLException e) {
			
				e.printStackTrace();
			}
		}
	}
	
	class backn implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			Jf.dispose();
			Class<?> clazz;
			try {
				clazz = Class.forName(Welcome.bstack.pop());
		    	Constructor<?> ctor = clazz.getConstructor();
		    	Object object = ctor.newInstance();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
	
	class frwdn implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			JOptionPane.showMessageDialog(null,"Pls Login to continue");
			//Welcome.j=0;
		}
	}
	
	class rstbtn implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			usrtf.setText(null);
			usrpf.setText("");
		}
	}
}
