package design;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AdminTransferAmount {

	JFrame jf;
	JPanel jp;
	JTextField atf,amtf;
	 
	JLabel msglb,acclb,amlb;
	JButton trnsbt,rstbt;
	String aid,name;
	
	public AdminTransferAmount(String aid, String name) {
		
		this.aid=aid;
		this.name=name;
		jf= new JFrame("Admin Transfer");
		jp= new JPanel();
		jp.setLayout(null);
		
		acclb= new JLabel("Account No");
		acclb.setBounds(20,50,100,25);
		jp.add(acclb);
		
		amlb= new JLabel("Amount");
		amlb.setBounds(20,90,100,25);
		jp.add(amlb);
		
		atf= new JTextField();
		atf.setBounds(130, 50, 100, 25);
		jp.add(atf);
		
		amtf= new JTextField();
		amtf.setBounds(130, 90, 100, 25);
		jp.add(amtf);
		 
		trnsbt= new JButton("Transfer");
		trnsbt.setBounds(20,125,100,25);
		trnsbt.addActionListener(new transbtn());
		jp.add(trnsbt);
		
		rstbt= new JButton("Reset");
		rstbt.setBounds(125,125,100,25);
		jp.add(rstbt);
		
		
		jf.add(jp);
		jf.setVisible(true);
		jf.setSize(jf.getToolkit().getScreenSize());
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	class transbtn implements ActionListener
	{ 
		public void actionPerformed(ActionEvent arg0) {
			 
			String raccno= atf.getText();
			String amount= amtf.getText();
			int amnt= Integer.parseInt(amount);
			int res=JOptionPane.showConfirmDialog(null,"Transfer Rs."+amount+" to account "+raccno,"Confirm?",JOptionPane.YES_NO_OPTION);
			if(res==JOptionPane.YES_OPTION)
			{
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
						JOptionPane.showMessageDialog(null, "You dont have sufficient balance","Alert",JOptionPane.ERROR_MESSAGE);
					}
					
				}
				else
				{
					JOptionPane.showMessageDialog(null, "some error occured");
				}
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
				
				JOptionPane.showMessageDialog(null,"Rs. "+amount+" has been transferred to another account");
			}
			else if(res==JOptionPane.NO_OPTION)
			{
				JOptionPane.showMessageDialog(null,"Transfer cancelled");
			}
			
		}		
	}
}
