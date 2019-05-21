package design;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
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

public class UserChangepass {

	JFrame Jf;
	JPanel Jp;
	JButton back,subbt,rstbt;
	JPasswordField oldtf,nwtf,cnwtf;
	JLabel oldlb,nwlb,cnwlb;
	
	String aid,name;
	
	public UserChangepass(String aid, String name) {

		this.aid=aid;
		this.name=name;
		Jf= new JFrame("User Change Password");
		Jp= new mypanel();
		Jp.setLayout(null);
		
		oldlb= new JLabel("Old Password");
		oldlb.setBounds(20,50,200,25);
		Jp.add(oldlb);
		
		nwlb= new JLabel("New Password");
		nwlb.setBounds(20,90,200,25);
		Jp.add(nwlb);
		
		cnwlb= new JLabel("Confirm New Password");
		cnwlb.setBounds(20,130,200,25);
		Jp.add(cnwlb);
		
		oldtf= new JPasswordField();
		oldtf.setBounds(230,50,100,25);
		Jp.add(oldtf);
		
		nwtf= new JPasswordField();
		nwtf.setBounds(230,90,100,25);
		Jp.add(nwtf);
		
		cnwtf= new JPasswordField();
		cnwtf.setBounds(230,130,100,25);
		Jp.add(cnwtf);
		 
		subbt= new JButton("Submit");
		subbt.setBounds(20,190,100,25);
		subbt.addActionListener(new subbtn());
		Jp.add(subbt);
		
		rstbt= new JButton("Reset");
		rstbt.setBounds(150,190,100,25);
		rstbt.addActionListener(new rstbtn());
		Jp.add(rstbt);
		
		back=new JButton("< Back");
		back.setBounds(50,230,100,25);
		back.addActionListener(new backbtn());
		Jp.add(back);
		
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
	
	class backbtn implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0) {
			
			Jf.dispose();
			new UserHome(aid,name);
		}
	}
	
	class rstbtn implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0) {
			
			oldtf.setText(null);
			nwtf.setText("");
			cnwtf.setText("");
		}
	}
	
	class subbtn implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0) {
		
			String oldpwd= oldtf.getText();
			String nwpwd= nwtf.getText();
			String cnwpwd= cnwtf.getText();
			Connection cnt= DataBaseConnection.javaConnection();
			try {
				Statement stt= cnt.createStatement();
				String qq= "Select password from accountinfo where accno='"+aid+"'";
				ResultSet rr= stt.executeQuery(qq);
				if(rr.next())
				{
					if(oldpwd.equals(rr.getString("password")) && nwpwd.equals(cnwpwd))
					{
						String qq1= "update accountinfo set password='"+nwpwd+"' where accno='"+aid+"'";
						int rr1= stt.executeUpdate(qq1);
						if(rr1>0)
						{
							JOptionPane.showMessageDialog(null, "Password changed successfully");
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Some error occured in updation");
						}
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Pls check your Password");
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "Some error occured");
				}
				
				oldtf.setText(null);
				nwtf.setText("");
				cnwtf.setText("");

			}catch (SQLException e) {
		
				e.printStackTrace();
			}
		}
	}
}
