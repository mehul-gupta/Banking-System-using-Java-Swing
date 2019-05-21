package design;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class UserHome {
	JFrame Jf;
	JPanel Jp;
	JLabel wlcm;
	JButton balbt,trnbt,wthbt,cpassbt,lnrbt,lndepbt,mnst,depbt,mylnbt;
	JButton back,frwd;
	String aid,name;
	
	UserHome(String aid, String name)
	{
		this.aid= aid;
		this.name=name;
		Jf=new JFrame("USER HOME");
		Jp=new mypanel();
		Jp.setLayout(null);
		//Jp.setBackground(Color.decode("#F3FAB6"));
		
		wlcm=new JLabel("Welcome "+name);
		wlcm.setBounds(450,10,400,45);
		wlcm.setForeground(Color.RED);
		Font ff=new Font("Arial",Font.BOLD,40);
		wlcm.setFont(ff);
		Jp.add(wlcm);
		
		back=new JButton("Logout");
		back.setBounds(10,60,100,25);
		
		back.addActionListener(new backn());
		Jf.add(back);
		if(Welcome.i==0)
		{
			back.setEnabled(false);
		}
		frwd=new JButton("Forward >");
		frwd.setBounds(120,60,100,25);
		frwd.setEnabled(false);
		Jf.add(frwd);
		
		balbt=new JButton("Balance Enquiry");
		balbt.setBounds(50,120,150,30);
		balbt.addActionListener(new balenqbtn());
		Jp.add(balbt);
		
		mnst=new JButton("Mini Statement");
		mnst.setBounds(220,120,150,30);
		mnst.addActionListener(new mnstbtn());
		Jp.add(mnst);
		
		wthbt=new JButton("Withdraw");
		wthbt.setBounds(50,170,150,30);
		wthbt.addActionListener(new withbtn());
		Jp.add(wthbt);
		
		trnbt=new JButton("Transfer");
		trnbt.setBounds(220,170,150,30);
		trnbt.addActionListener(new transbtn());
		Jp.add(trnbt);
		
		lnrbt=new JButton("Loan Request");
		lnrbt.setBounds(50,220,150,30);
		lnrbt.addActionListener(new lnrbtn());
		Jp.add(lnrbt);
		
		lndepbt=new JButton("Loan Deposit");
		lndepbt.setBounds(220,220,150,30);
		Jp.add(lndepbt);
		
		depbt=new JButton("Deposit");
		depbt.setBounds(50,270,150,30);
		depbt.addActionListener(new depbtn());
		Jp.add(depbt);
		
		cpassbt=new JButton("Change Password");
		cpassbt.setBounds(220,270,150,30);
		cpassbt.addActionListener(new cpassbtn());
		Jp.add(cpassbt);
		
		mylnbt=new JButton("My Loans");
		mylnbt.setBounds(50,320,150,30);
		mylnbt.addActionListener(new mylnbtn());
		Jp.add(mylnbt);
		
		Jf.add(Jp);
		
		Jf.setVisible(true);
		//Jf.setLocation(0,400);
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
			Image i2= new ImageIcon("images/userpic.png").getImage();
			g.drawImage(i2,900,80,200,200,this);
		}
	}
	
	class mylnbtn implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0) {
		
			ArrayList<loan> lnlist=new ArrayList<>();
			lnlist=Datamethods.myloans(aid);
			Jf.dispose();
			new MyLoans(lnlist,aid,name);
		}
	}

	class lnrbtn implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0) {
			
			/*Object[] options = { "Request", "cancel" };
			int amnt=JOptionPane.showOptionDialog(null, "Enter amount of loan: ", "Input",
			JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
			*/
			
			String amt=JOptionPane.showInputDialog(null, "Enter amount of loan: ");
			int amnt=Integer.parseInt(amt);
			Connection cnt=DataBaseConnection.javaConnection();
			try {
				Statement stt=cnt.createStatement();
				String qq="Insert into loan(accno, amount) values('"+aid+"','"+amnt+"')";
				int rr=stt.executeUpdate(qq);
				if(rr>0)
				{
					JOptionPane.showMessageDialog(null, "Your request is submitted successfully");
				}
				
			} catch (SQLException e){
				e.printStackTrace();
			}
		}
	}
	
	class cpassbtn implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0) {
		
			Jf.dispose();
			new UserChangepass(aid,name);
		}
	}
	
	class mnstbtn implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0) {

			ArrayList<transaction> elist=new ArrayList<>();
			elist=Datamethods.transinfo(aid);
			Jf.dispose();
			MiniStatement.i=0;
			new MiniStatement(elist,aid,name);
		}
	}
	
	class depbtn implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0) {
			String amt=JOptionPane.showInputDialog(null, "Pls enter amount you want to deposit");
			int amnt= Integer.parseInt(amt);
			Connection cnt= DataBaseConnection.javaConnection();
			 try {
				Statement stt= cnt.createStatement();
				String qq= "select balance from accountinfo where accno='"+aid+"'";
				ResultSet rr= stt.executeQuery(qq);
				if(rr.next())
				{
					String qq1= "update accountinfo set balance=balance+"+amnt+" where accno='"+aid+"'";
					int rr1= stt.executeUpdate(qq1);
					if(rr1>0)
					{
						Date d= new Date();
						String inq= "insert into transaction(tdate, accno, balance, ttype) values ('"+d+"','"+aid+"','"+amt+"','deposit')";
						stt.executeUpdate(inq);
						JOptionPane.showMessageDialog(null, "Your balance is deposited successfully");
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Some error occured in updation");
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
	
	class balenqbtn implements ActionListener
	{
 
		public void actionPerformed(ActionEvent arg0) {
			 Connection cnt= DataBaseConnection.javaConnection();
			 try {
				Statement stt= cnt.createStatement();
				String qq= "Select balance from accountinfo where accno='"+aid+"'";
				ResultSet rr= stt.executeQuery(qq);
				if(rr.next())
				{
					JOptionPane.showMessageDialog(null, "Your balance is Rs. "+rr.getString("balance"));
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

	
	class transbtn implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0) {
			Jf.dispose();
			new UserTransferAmount(aid,name);	
		}		
	}

	
	class withbtn implements ActionListener
	{ 
		public void actionPerformed(ActionEvent arg0) {
			String amt=JOptionPane.showInputDialog(null, "Pls enter amount you want to withdraw");
			int amnt= Integer.parseInt(amt);
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
						if(rr1>0)
						{
							Date d= new Date();
							String inq= "insert into transaction(tdate, accno, balance, ttype) values ('"+d+"','"+aid+"','"+amt+"','withdraw')";
							stt.executeUpdate(inq);
							JOptionPane.showMessageDialog(null, "Your balance is withdraw successfully");
						}
						else
						{
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
	
	class backn implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			Welcome.i--;
			Welcome.j++;
			Jf.dispose();
			new UserLog();
		}
	}
	
	/*class trnbtn implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			String amt=JOptionPane.showInputDialog("Enter Amount: ");
			int am=Integer.parseInt(amt);
			int res=JOptionPane.showConfirmDialog(null,"Transfer Rs."+am+" to another account","Confirm?",JOptionPane.YES_NO_OPTION);
			if(res==JOptionPane.YES_OPTION)
			{
				JOptionPane.showMessageDialog(null,"Rs. "+am+" has been transferred to another account");
			}
			else if(res==JOptionPane.NO_OPTION)
			{
				JOptionPane.showMessageDialog(null,"Transfer cancelled");
			}
		}
	}*/

}
