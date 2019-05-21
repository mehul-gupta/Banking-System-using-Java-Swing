package design;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormatSymbols;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Newacc {
	
	JFrame Jf;
	JPanel Jp;
	JLabel head,accno,name,fname,gender,dob,mailid,mob,add,acctype,secqlb,secalb;
	JTextField acctf,nmtf,fnmtf,mailtf,mobtf,secatf;
	JTextArea addtf;
	JRadioButton currb,savrb,mrb,frb;
	ButtonGroup accbb,genbb;
	JButton subt,checkbt,back;
	JComboBox<String> dtcb,mncb,yrcb,seccb;
	String aid,nm;
	
	Newacc(String aid, String nm)
	{
		this.aid=aid;
		this.nm=nm;
		Jf=new JFrame("New account");
		Jp=new JPanel();
		Jp.setLayout(null);							//Jf.setLayout(null);
		Jp.setBackground(Color.decode("#F9BF3B"));
		
		head=new JLabel("New account");
		head.setBounds(450,10,200,25);
		Font hf=new Font("Arial",Font.BOLD,19);
		head.setFont(hf);
		Jp.add(head);
		
		back=new JButton("< Back");
		back.setBounds(10,10,100,25);
		back.addActionListener(new backn());
		Jf.add(back);
		
		accno=new JLabel("Account no");
		accno.setBounds(10,65,100,25);
		Font ff=new Font("Arial",Font.BOLD,13);
		accno.setFont(ff);
		Jp.add(accno);
		acctf=new JTextField();
		acctf.setBounds(150,65,100,25);
		Jp.add(acctf);
		
		checkbt=new JButton("Check");
		checkbt.setBounds(350,65,100,25);
		checkbt.addActionListener(new checkbtn());
		Jp.add(checkbt);
		
		acctype=new JLabel("Account Type");
		acctype.setBounds(10,100,100,25);
		acctype.setFont(ff);
		accbb=new ButtonGroup();
		currb=new JRadioButton("Current");
		currb.setBounds(150,100,80,25);
		currb.setSelected(true);
		
		savrb=new JRadioButton("Saving");
		savrb.setBounds(250,100,80,25);
		
		Jp.add(acctype);								//Jf.add(gender);
		accbb.add(currb);
		accbb.add(savrb);
		Jp.add(currb);								//Jf.add(mrb);
		Jp.add(savrb);
		
		name=new JLabel("Name");
		name.setBounds(10,135,100,25);
		name.setFont(ff);
		Jp.add(name);								//Jf.add(name);
		nmtf=new JTextField();
		nmtf.setBounds(150,135,100,25);
		Jp.add(nmtf);								//Jf.add(nmtf);
		
		fname=new JLabel("Father's Name");
		fname.setBounds(10,170,150,25);
		fname.setFont(ff);
		Jp.add(fname);								//Jf.add(fname);
		fnmtf=new JTextField();
		fnmtf.setBounds(150,170,150,25);
		Jp.add(fnmtf);								//Jf.add(fnmtf);
		
		gender=new JLabel("Gender");
		gender.setBounds(10,205,100,25);
		gender.setFont(ff);
		genbb=new ButtonGroup();
		mrb=new JRadioButton("Male");
		mrb.setBounds(150,205,80,25);
		mrb.setSelected(true);						/*otherwise gen="" and will throw
		 											exception as accinfo column will be null  */
		
		frb=new JRadioButton("Female");
		frb.setBounds(250,205,80,25);
		
		Jp.add(gender);								//Jf.add(gender);
		genbb.add(mrb);
		genbb.add(frb);
		Jp.add(mrb);								//Jf.add(mrb);
		Jp.add(frb);								//Jf.add(frb);
		
		dob=new JLabel("DOB (DD/MM/YY)");
		dob.setBounds(10,240,120,25);
		dob.setFont(ff);
		Jp.add(dob);								//Jf.add(dob);
		dtcb=new JComboBox<String>();
		dtcb.setBounds(150,240,100,25);
		dtcb.addItem(String.valueOf("Select"));
		for(int i=1;i<=31;i++)
		{
			dtcb.addItem(String.valueOf(i));
		}
		Jp.add(dtcb);								//Jf.add(dtcb);
		
		String[] months = new DateFormatSymbols().getMonths();
		mncb=new JComboBox<String>();
		mncb.setBounds(275,240,100,25);
		mncb.addItem(String.valueOf("Select"));
		for(int i=0;i<months.length-1;i++)
		{
			String month = months[i];
			mncb.addItem(String.valueOf(month));
		}
		Jp.add(mncb);								//Jf.add(mncb);
		
		yrcb=new JComboBox<String>();
		yrcb.setBounds(400,240,100,25);
		yrcb.addItem(String.valueOf("Select"));
		for(int i=1950;i<2006;i++)
		{
			yrcb.addItem(String.valueOf(i));
		}
		Jp.add(yrcb);								//Jf.add(yrcb);
		
		mailid=new JLabel("E-Mail");
		mailid.setBounds(10,275,100,25);
		mailid.setFont(ff);
		Jp.add(mailid);								//Jf.add(mailid);
		mailtf=new JTextField();
		mailtf.setBounds(150,275,200,25);
		Jp.add(mailtf);								//Jf.add(mailtf);
		
		mob=new JLabel("Mob");
		mob.setBounds(10,310,100,25);
		mob.setFont(ff);
		Jp.add(mob);								//Jf.add(mob);
		mobtf=new JTextField();
		mobtf.setBounds(150,310,100,25);
		Jp.add(mobtf);								//Jf.add(mbtf);
		
		add=new JLabel("Address");
		add.setBounds(10,345,100,25);
		add.setFont(ff);
		Jp.add(add);								//Jf.add(add);
		addtf=new JTextArea();
		addtf.setBounds(150,345,180,80);
		Jp.add(addtf);								//Jf.add(addtf);
		
		secqlb=new JLabel("Security Question");
		secqlb.setBounds(10,435,200,25);
		secqlb.setFont(ff);
		Jp.add(secqlb);	
		seccb=new JComboBox<String>();
		seccb.setBounds(150,435,400,25);
		seccb.addItem(String.valueOf("Select"));
		seccb.addItem(String.valueOf("Who is your favourite cricketer?"));
		seccb.addItem(String.valueOf("What is your middle name?"));
		seccb.addItem(String.valueOf("What was your childhood name?"));
		seccb.addItem(String.valueOf("What is your dream company?"));
		Jp.add(seccb);
		
		secalb=new JLabel("Security Answer");
		secalb.setBounds(10,470,200,25);
		secalb.setFont(ff);
		Jp.add(secalb);
		secatf=new JTextField();
		secatf.setBounds(150,470,100,25);
		Jp.add(secatf);
		
		subt=new JButton("SUBMIT");
		subt.setBounds(50,520,100,25);
		subt.addActionListener(new subbtn());
		Jp.add(subt);								//Jf.add(subt);
		
		Jf.add(Jp);
		Jf.setVisible(true);
		Jf.setSize(Jf.getToolkit().getScreenSize());
		Jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	class backn implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0) {
			
			Jf.dispose();
			new AdminHome(aid, nm);
		}
	}
	
	class checkbtn implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0){
			
			String accno=acctf.getText();
			int acc=Integer.parseInt(accno);
			Connection cnt= DataBaseConnection.javaConnection();
			 try {
				Statement stt= cnt.createStatement();
				String qq= "Select accno from accountinfo where accno='"+acc+"'";
				ResultSet rr= stt.executeQuery(qq);
				if(rr.next())
				{
					JOptionPane.showMessageDialog(null, "Account no. not available");
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Account no. available");
				}
			} catch (SQLException e) {
			
				e.printStackTrace();
			}
		}
	}
	
	class subbtn implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0) {
			
			String acc=acctf.getText();
			int accno=Integer.parseInt(acc);
			String nm=nmtf.getText();
			String fnm=fnmtf.getText();
			String acctype="";
			if(currb.isSelected())
			{
				acctype="Current";
			}
			else if(savrb.isSelected())
			{
				acctype="Saving";
			}
			
			String gen="";
			if(mrb.isSelected())
			{
				gen="Male";
			}
			else if(frb.isSelected())
			{
				gen="Female";
			}
			
			String email=mailtf.getText();
			String mob=mobtf.getText();
			String add=addtf.getText();
			String secq=(String)(seccb.getSelectedItem());
			String seca=secatf.getText();
			
			Connection cnt=DataBaseConnection.javaConnection();
			try{
				Statement stt= cnt.createStatement();
				String qq= "Insert into accountinfo(accno, name, fname, address, mobile, email, sec_question, sec_answer, gender, accounttype) values('"+accno+"','"+nm+"','"+fnm+"','"+add+"','"+mob+"','"+email+"','"+secq+"','"+seca+"','"+gen+"','"+acctype+"')";
				int rr= stt.executeUpdate(qq);
				if(rr>0)
				{
					JOptionPane.showMessageDialog(null, "Account successfully created");
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Some error occured");
				}
				Jf.dispose();
				new AdminHome(aid,nm);
			} catch (SQLException e) {
			
				e.printStackTrace();
			}
			
		}
	}
}
