package design;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class AdminHome {
	
	JFrame Jf;
	JPanel Jp;
	JLabel wlcm;
	JButton newaccbt,editbt,baleqbt,withbt,depbt,transbt,ministbt,lnrbt;
	JButton back;
	String aid,name;
	
	AdminHome(String aid, String name)
	{
		this.aid=aid;
		this.name=name;
		Jf=new JFrame("ADMIN HOME");
		Jp=new mypanel();
		Jp.setLayout(null);
		Jp.setBackground(Color.decode("#F3FAB6"));
		
		wlcm=new JLabel("Welcome Admin");
		wlcm.setBounds(450,10,400,45);
		wlcm.setForeground(Color.RED);
		Font ff=new Font("Arial",Font.BOLD,40);
		wlcm.setFont(ff);
		Jp.add(wlcm);
		
		back=new JButton("Logout");
		back.setBounds(10,60,100,25);
		back.addActionListener(new backn());
		Jf.add(back);
		
		newaccbt=new JButton("New Account");
		newaccbt.setBounds(50,120,150,30);
		newaccbt.addActionListener(new newaccbtn());
		Jp.add(newaccbt);
		
		editbt=new JButton("Edit Info");
		editbt.setBounds(220,120,150,30);
		editbt.addActionListener(new editbtn());
		Jp.add(editbt);
		
		baleqbt=new JButton("Balance enquiry");
		baleqbt.setBounds(50,170,150,30);
		baleqbt.addActionListener(new baleqbtn());
		Jp.add(baleqbt);
		
		withbt=new JButton("Withdraw");
		withbt.setBounds(220,170,150,30);
		withbt.addActionListener(new withbtn());
		Jp.add(withbt);
		
		depbt=new JButton("Deposit");
		depbt.setBounds(50,220,150,30);
		depbt.addActionListener(new depbtn());
		Jp.add(depbt);
		
		transbt=new JButton("Transfer");
		transbt.setBounds(220,220,150,30);
		Jp.add(transbt);
		
		ministbt=new JButton("Mini Statement");
		ministbt.setBounds(50,270,150,30);
		ministbt.addActionListener(new ministbtn());
		Jp.add(ministbt);
		
		lnrbt=new JButton("Loan Requests");
		lnrbt.setBounds(220,270,150,30);
		lnrbt.addActionListener(new lnrbtn());
		Jp.add(lnrbt);
		
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
			Image i2= new ImageIcon("images/user-icon-6.png").getImage();
			g.drawImage(i2,900,80,200,200,this);
		}
	}
	
	class lnrbtn implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0) {
			
			ArrayList<loan> lnlist=new ArrayList<>();
			lnlist=Datamethods.loanreq();
			Jf.dispose();
			new LoanRequests(lnlist,aid,name);
		}
		
	}
	
	class editbtn implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0) {
			
			String amt=JOptionPane.showInputDialog(null, "Enter name or account number: ");
			ArrayList<accinfo> elist=new ArrayList<>();
			elist=Datamethods.editacc(amt);
			Jf.dispose();
			new Editacc(elist);
		}
	}
	
	class ministbtn implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0){
			
			String amt=JOptionPane.showInputDialog(null, "Enter account number: ");
			ArrayList<transaction> elist=new ArrayList<>();
			elist=Datamethods.transinfo(amt);
			MiniStatement.i=1;
			Jf.dispose();
			new MiniStatement(elist,aid,name);
		}
	}
	
	class backn implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0) {
			
			Jf.dispose();
			new AdminLog();
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
	
	class transbtn implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0) {
				
			Jf.dispose();
			new AdminTransferAmount(aid,name);
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
				String qq= "Select balance from accountinfo where accno='"+aid+"'";
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
						JOptionPane.showMessageDialog(null, "You dont have sufficient balance","Alert",JOptionPane.ERROR_MESSAGE);
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
	
	class newaccbtn implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0) {
			
			Jf.dispose();
			new Newacc(aid,name);
		}
	}
	
	class baleqbtn implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0) {
		
			String amt=JOptionPane.showInputDialog("Enter Account no: ");
			int am=Integer.parseInt(amt);
			Connection cnt=DataBaseConnection.javaConnection();
			try {
				Statement stt= cnt.createStatement();
				String qq= "Select balance from accountinfo where accno='"+am+"'";
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
	
}
