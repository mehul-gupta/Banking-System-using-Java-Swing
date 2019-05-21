package design;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormatSymbols;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import design.UserLog.backn;

public class UserReg {
	JFrame Jf;
	JPanel Jp;
	JLabel name,fname,gender,dob,mailid,mob,add;
	JTextField nmtf,fnmtf,mailtf,mbtf;
	JTextArea addtf;
	JRadioButton mrb,frb;
	ButtonGroup bb;
	JButton subt,back;
	JComboBox<String> dtcb,mncb,yrcb;
	
	UserReg()
	{
		Jf=new JFrame("REGISTRATION");
		Jp=new JPanel();
		Jp.setLayout(null);							//Jf.setLayout(null);
		Jp.setBackground(Color.decode("#F9BF3B"));
		
		back=new JButton("< Back");
		back.setBounds(10,40,100,25);
		back.addActionListener(new backn());
		Jf.add(back);
		
		name=new JLabel("Name");
		name.setBounds(10,100,100,25);
		Font ff=new Font("Arial",Font.BOLD,13);
		name.setFont(ff);
		Jp.add(name);								//Jf.add(name);
		nmtf=new JTextField();
		nmtf.setBounds(150,100,100,25);
		Jp.add(nmtf);								//Jf.add(nmtf);
		
		fname=new JLabel("Father's Name");
		fname.setBounds(10,135,150,25);
		fname.setFont(ff);
		Jp.add(fname);								//Jf.add(fname);
		fnmtf=new JTextField();
		fnmtf.setBounds(150,135,150,25);
		Jp.add(fnmtf);								//Jf.add(fnmtf);
		
		gender=new JLabel("Gender");
		gender.setBounds(10,170,100,25);
		gender.setFont(ff);
		bb=new ButtonGroup();
		mrb=new JRadioButton("Male");
		mrb.setBounds(150,170,80,25);
		
		frb=new JRadioButton("Female");
		frb.setBounds(250,170,80,25);
		
		Jp.add(gender);								//Jf.add(gender);
		bb.add(mrb);
		bb.add(frb);
		Jp.add(mrb);								//Jf.add(mrb);
		Jp.add(frb);								//Jf.add(frb);
		
		dob=new JLabel("DOB (DD/MM/YY)");
		dob.setBounds(10,205,120,25);
		dob.setFont(ff);
		Jp.add(dob);								//Jf.add(dob);
		dtcb=new JComboBox<String>();
		dtcb.setBounds(150,205,100,25);
		dtcb.addItem(String.valueOf("Select"));
		for(int i=1;i<=31;i++)
		{
			dtcb.addItem(String.valueOf(i));
		}
		Jp.add(dtcb);								//Jf.add(dtcb);
		
		String[] months = new DateFormatSymbols().getMonths();
		mncb=new JComboBox<String>();
		mncb.setBounds(275,205,100,25);
		mncb.addItem(String.valueOf("Select"));
		for(int i=0;i<months.length-1;i++)
		{
			String month = months[i];
			mncb.addItem(String.valueOf(month));
		}
		Jp.add(mncb);								//Jf.add(mncb);
		
		yrcb=new JComboBox<String>();
		yrcb.setBounds(400,205,100,25);
		yrcb.addItem(String.valueOf("Select"));
		for(int i=1950;i<2006;i++)
		{
			yrcb.addItem(String.valueOf(i));
		}
		Jp.add(yrcb);								//Jf.add(yrcb);
		
		mailid=new JLabel("E-Mail");
		mailid.setBounds(10,240,100,25);
		mailid.setFont(ff);
		Jp.add(mailid);								//Jf.add(mailid);
		mailtf=new JTextField();
		mailtf.setBounds(150,240,200,25);
		Jp.add(mailtf);								//Jf.add(mailtf);
		
		mob=new JLabel("Mob");
		mob.setBounds(10,275,100,25);
		mob.setFont(ff);
		Jp.add(mob);								//Jf.add(mob);
		mbtf=new JTextField();
		mbtf.setBounds(150,275,100,25);
		Jp.add(mbtf);								//Jf.add(mbtf);
		
		add=new JLabel("Address");
		add.setBounds(10,310,100,25);
		add.setFont(ff);
		Jp.add(add);								//Jf.add(add);
		addtf=new JTextArea();
		addtf.setBounds(150,310,180,80);
		Jp.add(addtf);								//Jf.add(addtf);
		
		subt=new JButton("SUBMIT");
		subt.setBounds(50,430,100,25);
		Jp.add(subt);								//Jf.add(subt);
		
		Jf.add(Jp);
		Jf.setVisible(true);
		Jf.setSize(Jf.getToolkit().getScreenSize());
		Jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
	
}
