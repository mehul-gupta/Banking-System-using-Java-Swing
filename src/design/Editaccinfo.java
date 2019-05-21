package design;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class Editaccinfo {
	
	String aid;
	JFrame jf;
	JPanel jp;
	JLabel namelb,fnamelb,addlb,moblb,maillb,genlb,selectlb;
	JTextField nmtf,fnmtf,addtf,mobtf,mailtf,gentf;
	JButton updatebt;
	ButtonGroup genbb;
	JRadioButton mrb,frb;
	
	public Editaccinfo(accinfo a) {
		
		 aid=a.accno;
		 jf= new JFrame("Edit Account details");
		 jp= new JPanel();
		 jp.setLayout(null);
		 
		 namelb= new JLabel("Name");
		 namelb.setBounds(10, 10, 100, 25);
		 jp.add(namelb);
		 
		 fnamelb= new JLabel("Father's Name");
		 fnamelb.setBounds(120, 10, 100, 25);
		 jp.add(fnamelb);
		  	 
		 addlb= new JLabel("Address");
		 addlb.setBounds(230, 10, 100, 25);
		 jp.add(addlb);
		 
		 moblb= new JLabel("Mobile");
		 moblb.setBounds(340, 10, 120, 25);
		 jp.add(moblb);
		 
		 maillb= new JLabel("E-mail");
		 maillb.setBounds(470, 10, 160, 25);
		 jp.add(maillb);
		 
		 genlb= new JLabel("Gender");
		 genlb.setBounds(640, 10, 120, 25);
		 jp.add(genlb);
		 	 
		 nmtf= new JTextField(a.getName());
		 nmtf.setBounds(10, 40, 100, 25);
		 nmtf.setEditable(true);
		 jp.add(nmtf);
			 
		 fnmtf= new JTextField(a.getFname());
		 fnmtf.setBounds(120, 40, 100, 25);
		 fnmtf.setEditable(true);
		 jp.add(fnmtf);
		 
		 addtf= new JTextField(a.getAddress());
		 addtf.setBounds(230, 40, 100, 25);
		 addtf.setEditable(true);
		 jp.add(addtf);
		 
		 mobtf= new JTextField(a.getMobile());
		 mobtf.setBounds(340, 40, 120, 25);
		 mobtf.setEditable(true);
		 jp.add(mobtf);
		 
		 mailtf= new JTextField(a.getEmail());
		 mailtf.setBounds(470, 40, 160, 25);
		 mailtf.setEditable(true);
		 jp.add(mailtf);
		 
		 String gen=a.getGender();
		 mrb=new JRadioButton("Male");
		 mrb.setBounds(640, 40, 100, 25);
		 genbb=new ButtonGroup();
		 if(gen.equalsIgnoreCase("Male"))
		 {
			 mrb.setSelected(true);
		 }
		 
		 frb=new JRadioButton("Female");
		 frb.setBounds(750,40,100,25);
		 if(gen.equalsIgnoreCase("Female"))
		 {
			 frb.setSelected(true);
		 }
		 genbb.add(mrb);
		 genbb.add(frb);
		 jp.add(mrb);
		 jp.add(frb);
			 
		 updatebt=new JButton("Update");
		 updatebt.setBounds(20,100,100,25);
		 updatebt.addActionListener(new updatebtn());
		 jp.add(updatebt);
	 
		 jf.add(jp);
		 
		 jf.setVisible(true);
		 jf.setSize(jf.getToolkit().getScreenSize());
		 jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	class updatebtn implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0) {
			
			String nm=nmtf.getText();
			String fnm=fnmtf.getText();
			String add=addtf.getText();
			String mob=mobtf.getText();
			String mail=mailtf.getText();
			String gen="";
			if(mrb.isSelected())
			{
				gen="Male";
			}
			else if(frb.isSelected())
			{
				gen="Female";
			}
				
			Connection cnt=DataBaseConnection.javaConnection();
			try {
					Statement stt=cnt.createStatement();
					String qq= "update accountinfo set name='"+nm+"',fname='"+fnm+"',address='"+add+"',mobile='"+mob+"',email='"+mail+"',gender='"+gen+"' where accno='"+aid+"'";
					int rr= stt.executeUpdate(qq);
					if(rr>0)
					{
						JOptionPane.showMessageDialog(null, "Account info updated");
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Some error occured");
					}
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
			jf.dispose();
		}		
	}
	
}
