package design;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class UserTransferAmount {
	
	JFrame Jf;
	JPanel Jp;
	JTextField atf,amtf;
	 
	JLabel msglb,acclb,amlb;
	JButton back,trnsbt,rstbt;
	String aid,name;
	
	public UserTransferAmount(String aid, String name) {
		
		this.aid=aid;
		this.name=name;
		Jf= new JFrame("User Transfer");
		Jp= new JPanel();
		Jp.setLayout(null);
		
		back=new JButton("< Back");
		back.setBounds(10,40,100,25);
		back.addActionListener(new backn());
		Jp.add(back);
		
		acclb= new JLabel("Account No");
		acclb.setBounds(50,130,100,25);
		Jp.add(acclb);
		
		amlb= new JLabel("Amount");
		amlb.setBounds(50,170,100,25);
		Jp.add(amlb);
		
		atf= new JTextField();
		atf.setBounds(160, 130, 100, 25);
		Jp.add(atf);
		
		amtf= new JTextField();
		amtf.setBounds(160, 170, 100, 25);
		Jp.add(amtf);
		 
		trnsbt= new JButton("Transfer");
		trnsbt.setBounds(50,210,100,25);
		trnsbt.addActionListener(new transbtn());
		Jp.add(trnsbt);
		
		rstbt= new JButton("Reset");
		rstbt.setBounds(155,210,100,25);
		rstbt.addActionListener(new rstbtn());
		Jp.add(rstbt);
		
		Jf.add(Jp);
		Jf.setVisible(true);
		Jf.setSize(Jf.getToolkit().getScreenSize());
		Jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	class backn implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0){
		
			Jf.dispose();
			new UserHome(aid,name);
		}
	}
	
	class rstbtn implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0){
			
			atf.setText(null);
			amtf.setText(null);
		}
	}
	
	class transbtn implements ActionListener
	{ 
		public void actionPerformed(ActionEvent arg0) {
			String raccno= atf.getText();
			String amount= amtf.getText();
			int amnt= Integer.parseInt(amount);
			Connection cnt= DataBaseConnection.javaConnection();
			try {
				Statement stt= cnt.createStatement();
				String qq= "select balance from accountinfo where accno='"+aid+"'";
				ResultSet rr= stt.executeQuery(qq);
				if(rr.next())
				{
					if(Integer.parseInt(rr.getString("balance"))>amnt)
					{
						String qq1= "update accountinfo set balance=balance-"+amnt+" where accno='"+aid+"'";
						int rr1= stt.executeUpdate(qq1);
						String qq2= "update accountinfo set balance=balance+"+amnt+" where accno='"+raccno+"'";
						int rr2= stt.executeUpdate(qq2);
						if(rr1>0 && rr2>0)
						{
							Date d= new Date();
							String inq= "insert into transaction(tdate, accno, balance, ttype) values ('"+d+"','"+aid+"','"+amnt+"','Transfer-withdraw')";
							stt.executeUpdate(inq);
							String inq1= "insert into transaction(tdate, accno, balance, ttype) values ('"+d+"','"+raccno+"','"+amnt+"','transfer-deposit')";
							stt.executeUpdate(inq1);
							JOptionPane.showMessageDialog(null, "Rs. "+amount+" has been transferred successfully");
						}
						else
						{
							String qq3= "update accountinfo set balance=balance+"+amnt+" where accno='"+aid+"'";
							int rr3= stt.executeUpdate(qq3);
							if(rr3>0)
								JOptionPane.showMessageDialog(null, "Some error occured in transfer");
							else
								JOptionPane.showMessageDialog(null, "Some error occured in updation");
						}
					}
					else
					{
						JOptionPane.showMessageDialog(null, "You dont have sufficient balance");
					}
					
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Some error occured");
				}
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}		
	}
}

