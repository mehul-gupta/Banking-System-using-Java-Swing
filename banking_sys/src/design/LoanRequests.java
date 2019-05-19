package design;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LoanRequests {

	String aid,name;
	JFrame jf;
	JPanel jp;
	JLabel snlb,acclb,amtlb,statuslb,selectlb;
	JButton back,approvebt,denybt; 
	JCheckBox chkbox[];
	ArrayList<loan> lnlist;
	JTextField sntf[],acctf[],amttf[],statustf[];
	public LoanRequests(ArrayList<loan> lnlist,String aid,String name) {
		
		sntf= new JTextField[lnlist.size()];
		acctf=new JTextField[lnlist.size()];
		amttf=new JTextField[lnlist.size()];
		statustf=new JTextField[lnlist.size()];
		chkbox=new JCheckBox[lnlist.size()];
		this.lnlist= lnlist;
		this.aid=aid;
		this.name=name;
		
		 jf= new JFrame("Loan Requests ");
		 jp= new JPanel();
		 jp.setLayout(null);
		 
		 back=new JButton("< Back");
		 back.setBounds(10,30,100,25);
		 back.addActionListener(new backn());
		 jp.add(back);
		 
		 snlb= new JLabel("S. No.");
		 snlb.setBounds(10, 80, 100, 25);
		 jp.add(snlb);
		 
		 acclb= new JLabel("Account no");
		 acclb.setBounds(120, 80, 100, 25);
		 jp.add(acclb);
		 
		 amtlb= new JLabel("Amount");
		 amtlb.setBounds(230, 80, 100, 25);
		 jp.add(amtlb);
		 
		 statuslb= new JLabel("Status");
		 statuslb.setBounds(340, 80, 100, 25);
		 jp.add(statuslb);
		 
		 selectlb= new JLabel("Select");
		 selectlb.setBounds(450,80,100,25);
		 jp.add(selectlb);
		 
		 int i=0,y=110;
		 for(loan l:lnlist)
		 {
			 
			 sntf[i]= new JTextField(String.valueOf(i+1));
			 sntf[i].setBounds(10, y, 100, 25);
			 sntf[i].setEditable(false);
			 jp.add(sntf[i]);
			 
			 acctf[i]= new JTextField(l.getAccno());
			 acctf[i].setBounds(120, y, 100, 25);
			 acctf[i].setEditable(false);
			 jp.add(acctf[i]);
			 
			 amttf[i]= new JTextField(l.getAmount());
			 amttf[i].setBounds(230, y, 100, 25);
			 amttf[i].setEditable(false);
			 jp.add(amttf[i]);
			 
			 statustf[i]= new JTextField(l.getStatus());
			 statustf[i].setBounds(340, y, 100, 25);
			 statustf[i].setEditable(false);
			 jp.add(statustf[i]);
			 
			 chkbox[i]=new JCheckBox();
			 chkbox[i].setBounds(450, y, 100, 25);
			 jp.add(chkbox[i]);
			 
			 i++;
			 y=y+30;
		 }
		 
		 approvebt=new JButton("Approve");
		 approvebt.setBounds(10,y+20,100,25);
		 approvebt.addActionListener(new approvebtn());
		 jp.add(approvebt);
		 
		 denybt=new JButton("Deny");
		 denybt.setBounds(120,y+20,100,25);
		 denybt.addActionListener(new denybtn());
		 jp.add(denybt);
		 
		 jf.add(jp);
		 
		 jf.setVisible(true);
		 jf.setSize(jf.getToolkit().getScreenSize());
		 jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	class denybtn implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0) {
			
			int nochk=0,deny=0;
			for(int i=0;i<lnlist.size();i++)
			{
				if(chkbox[i].isSelected())
				{
					nochk+=1;
					loan l=lnlist.get(i);
					Connection cnt= DataBaseConnection.javaConnection();
					try {
						Statement stt= cnt.createStatement();
						String qq= "update loan set status='denied' where Sno='"+l.getSno()+"'";
						int rr= stt.executeUpdate(qq);
						if(rr>0)
						{
							chkbox[i].setSelected(false);
							deny+=1;
						}
					
					}catch (SQLException e) {
						
						e.printStackTrace();
					}

				}
			}
			if(nochk==deny)
			{
				JOptionPane.showMessageDialog(null, "Loans approved Successfully");
				jf.dispose();
				new AdminHome(aid,name);
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Error... Selected loans not denied");
			}
		}
	}
	
	class approvebtn implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0) {
			
			int nochk=0,approve=0;
			for(int i=0;i<lnlist.size();i++)
			{
				if(chkbox[i].isSelected())
				{
					nochk+=1;
					loan l=lnlist.get(i);
					Connection cnt= DataBaseConnection.javaConnection();
					try {
						Statement stt= cnt.createStatement();
						String qq= "update loan set status='approved' where Sno='"+l.getSno()+"'";
						int rr= stt.executeUpdate(qq);
						if(rr>0)
						{
							chkbox[i].setSelected(false);
							approve+=1;
						}
					
					}catch (SQLException e) {
						
						e.printStackTrace();
					}

				}
			}
			if(nochk==approve)
			{
				JOptionPane.showMessageDialog(null, "Loans approved Successfully");
				jf.dispose();
				new AdminHome(aid,name);
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Error...Selected loans not approved");
			}
		}
	}
	
	class backn implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0) {
				
			jf.dispose();
			new AdminHome(aid, name);
		}
	}

}
