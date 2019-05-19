package design;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class Editacc {
	
	JFrame jf;
	JPanel jp;
	JLabel namelb,fnamelb,ballb,addlb,moblb,maillb,genlb,selectlb;
	ArrayList<accinfo> elist;
	JTextField nmtf[],fnmtf[],baltf[],addtf[],mobtf[],mailtf[],gentf[];
	JButton editbt;
	ButtonGroup rbtgrp;
	JRadioButton rbbt[];
	
	public Editacc(ArrayList<accinfo> elist) {
		
		this.elist=elist;
		nmtf= new JTextField[elist.size()];
		fnmtf=new JTextField[elist.size()];
		baltf=new JTextField[elist.size()];
		addtf=new JTextField[elist.size()];
		mobtf=new JTextField[elist.size()];
		mailtf=new JTextField[elist.size()];
		gentf=new JTextField[elist.size()];
		rbbt=new JRadioButton[elist.size()];
		 jf= new JFrame("Edit Account details");
		 jp= new JPanel();
		 jp.setLayout(null);
		 
		 namelb= new JLabel("Name");
		 namelb.setBounds(10, 10, 100, 25);
		 jp.add(namelb);
		 
		 fnamelb= new JLabel("Father's Name");
		 fnamelb.setBounds(120, 10, 100, 25);
		 jp.add(fnamelb);
		 
		 ballb= new JLabel("Balance");
		 ballb.setBounds(230, 10, 100, 25);
		 jp.add(ballb);
		 	 
		 addlb= new JLabel("Address");
		 addlb.setBounds(340, 10, 100, 25);
		 jp.add(addlb);
		 
		 moblb= new JLabel("Mobile");
		 moblb.setBounds(450, 10, 120, 25);
		 jp.add(moblb);
		 
		 maillb= new JLabel("E-mail");
		 maillb.setBounds(580, 10, 160, 25);
		 jp.add(maillb);
		 
		 genlb= new JLabel("Gender");
		 genlb.setBounds(750, 10, 120, 25);
		 jp.add(genlb);
		 
		 selectlb= new JLabel("Select");
		 selectlb.setBounds(880,10,120,25);
		 jp.add(selectlb);
		 
		 rbtgrp=new ButtonGroup();
		 
		 int i=0,y=40;
		 for(accinfo t:elist)
		 {
			 nmtf[i]= new JTextField(t.getName());
			 nmtf[i].setBounds(10, y, 100, 25);
			 nmtf[i].setEditable(false);
			 jp.add(nmtf[i]);
			 
			 fnmtf[i]= new JTextField(t.getFname());
			 fnmtf[i].setBounds(120, y, 100, 25);
			 fnmtf[i].setEditable(false);
			 jp.add(fnmtf[i]);
			 
			 baltf[i]= new JTextField(t.getBalance());
			 baltf[i].setBounds(230, y, 100, 25);
			 baltf[i].setEditable(false);
			 jp.add(baltf[i]);
			 
			 addtf[i]= new JTextField(t.getAddress());
			 addtf[i].setBounds(340, y, 100, 25);
			 addtf[i].setEditable(false);
			 jp.add(addtf[i]);
			 
			 mobtf[i]= new JTextField(t.getMobile());
			 mobtf[i].setBounds(450, y, 120, 25);
			 mobtf[i].setEditable(false);
			 jp.add(mobtf[i]);
			 
			 mailtf[i]= new JTextField(t.getEmail());
			 mailtf[i].setBounds(580, y, 160, 25);
			 mailtf[i].setEditable(false);
			 jp.add(mailtf[i]);
			 
			 gentf[i]= new JTextField(t.getGender());
			 gentf[i].setBounds(750, y, 120, 25);
			 gentf[i].setEditable(false);
			 jp.add(gentf[i]);
			 
			 rbbt[i]= new JRadioButton("");
			 rbbt[i].setBounds(880,y,120,25);
			 rbtgrp.add(rbbt[i]);
			 jp.add(rbbt[i]);
			 
			 i++;
			 y=y+30;
		 }
		 
		 editbt=new JButton("Edit");
		 editbt.setBounds(20,y+30,100,25);
		 editbt.addActionListener(new editbtn());
		 jp.add(editbt);
	 
		 jf.add(jp);
		 
		 jf.setVisible(true);
		 jf.setSize(jf.getToolkit().getScreenSize());
		 jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	class editbtn implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0) {
				
			for(int i=0;i<elist.size();i++)
			{
				if(rbbt[i].isSelected())
				{
					accinfo a=elist.get(i);
					new Editaccinfo(a);
				}
			}
			jf.dispose();
		}
		
	}
}
