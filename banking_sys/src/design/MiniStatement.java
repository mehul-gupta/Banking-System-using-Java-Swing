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

public class MiniStatement {
	
	String aid,name;
	static int i=0;
	JFrame jf;
	JPanel jp;
	JLabel snlb,tidlb,dtlb,amlb,acclb,ttplb;
	JButton back; 
	ArrayList<transaction> elist;
	JTextField sntf[],tidtf[],dttf[],amtf[],acctf[],ttptf[];
	public MiniStatement(ArrayList<transaction> elist,String aid,String name) {
		
		sntf= new JTextField[elist.size()];
		tidtf=new JTextField[elist.size()];
		dttf=new JTextField[elist.size()];
		amtf=new JTextField[elist.size()];
		acctf=new JTextField[elist.size()];
		ttptf=new JTextField[elist.size()];
		this.elist= elist;
		this.aid=aid;
		this.name=name;
		
		 jf= new JFrame("Mini statement user");
		 jp= new mypanel();
		 jp.setLayout(null);
		 
		 back=new JButton("< Back");
		 back.setBounds(10,30,100,25);
		 back.addActionListener(new backn());
		 jp.add(back);
		 
		 snlb= new JLabel("S. No.");
		 snlb.setBounds(10, 80, 100, 25);
		 jp.add(snlb);
		 
		 tidlb= new JLabel("Transaction id");
		 tidlb.setBounds(120, 80, 100, 25);
		 jp.add(tidlb);
		 
		 acclb= new JLabel("Account No");
		 acclb.setBounds(230, 80, 100, 25);
		 jp.add(acclb);
		 
		 amlb= new JLabel("Amount");
		 amlb.setBounds(340, 80, 100, 25);
		 jp.add(amlb);
		 
		 dtlb= new JLabel("Date");
		 dtlb.setBounds(450, 80, 120, 25);
		 jp.add(dtlb);
		 
		 ttplb= new JLabel("Type");
		 ttplb.setBounds(580, 80, 120, 25);
		 jp.add(ttplb);
		 
		 int i=0,y=110;
		 for(transaction t:elist)
		 {
			 
			 sntf[i]= new JTextField(String.valueOf(i+1));
			 sntf[i].setBounds(10, y, 100, 25);
			 sntf[i].setEditable(false);
			 jp.add(sntf[i]);
			 
			 tidtf[i]= new JTextField(t.getIdt());
			 tidtf[i].setBounds(120, y, 100, 25);
			 tidtf[i].setEditable(false);
			 jp.add(tidtf[i]);
			 
			 acctf[i]= new JTextField(t.getAccno());
			 acctf[i].setBounds(230, y, 100, 25);
			 acctf[i].setEditable(false);
			 jp.add(acctf[i]);
			 
			 amtf[i]= new JTextField(t.getBalance());
			 amtf[i].setBounds(340, y, 100, 25);
			 amtf[i].setEditable(false);
			 jp.add(amtf[i]);
			 
			 dttf[i]= new JTextField(t.getTdate());
			 dttf[i].setBounds(450, y, 120, 25);
			 dttf[i].setEditable(false);
			 jp.add(dttf[i]);
			 
			 ttptf[i]= new JTextField(t.getTtype());
			 ttptf[i].setBounds(580, y, 120, 25);
			 ttptf[i].setEditable(false);
			 jp.add(ttptf[i]);
			 
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
			if(MiniStatement.i==1)
			{	
				new AdminHome(aid, name);
			}
			else
			{
				new UserHome(aid, name);
			}
		}
	}

}
