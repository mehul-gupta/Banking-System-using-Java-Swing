package design;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MyLoans {

	String aid,name;
	JFrame jf;
	JPanel jp;
	JLabel snlb,acclb,amtlb,statuslb;
	JButton back; 
	ArrayList<loan> lnlist;
	JTextField sntf[],acctf[],amttf[],statustf[];
	public MyLoans(ArrayList<loan> lnlist,String aid,String name) {
		
		sntf= new JTextField[lnlist.size()];
		acctf=new JTextField[lnlist.size()];
		amttf=new JTextField[lnlist.size()];
		statustf=new JTextField[lnlist.size()];
		this.lnlist= lnlist;
		this.aid=aid;
		this.name=name;
		
		 jf= new JFrame("Loan Requests ");
		 jp= new mypanel();
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
			 	 
			 i++;
			 y=y+30;
		 }
		 
		 jf.add(jp);
		 
		 jf.setVisible(true);
		 jf.setSize(jf.getToolkit().getScreenSize());
		 jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		
	class backn implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0) {
				
			jf.dispose();
			new UserHome(aid, name);
		}
	}

}
