package design;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.Constructor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class UserLog {
	
	JFrame Jf;
	JPanel Jp;
	JLabel wlcm,usrlb,pwlb,msg;
	JTextField usrtf;
	JPasswordField usrpf;
	JButton logbt,rstbt,nwbt;
	static JButton back,frwd;;
	
	UserLog()
	{
		Jf=new JFrame("USER LOGIN");
		Jp=new mypanel();
		//Jp.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		Jp.setLayout(null);
		/*Color cc=new Color(243,250,182);
		Jp.setBackground(cc);*/
		//Jp.setBackground(Color.WHITE);	
		
		wlcm=new JLabel("User Login");
		wlcm.setBounds(530,10,400,45);
		wlcm.setForeground(Color.RED);
		Font f1=new Font("Brush Script MT",Font.BOLD,46);
		wlcm.setFont(f1);
		Jp.add(wlcm);
		
		usrlb=new JLabel("Account no");
		usrlb.setFont(new Font("Arial",Font.BOLD,17));
		usrlb.setBounds(380,150,100,25);
		Jp.add(usrlb);
		
		pwlb=new JLabel("Password");
		pwlb.setFont(new Font("Arial",Font.BOLD,17));
		pwlb.setBounds(640,150,100,25);
		Jp.add(pwlb);
		
		//usrtf=new JTextField("eg. default");
		usrtf=new JTextField();
		usrtf.setBounds(480,150,130,30);
		usrtf.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e)
			{
				usrtf.setText("");
			}
		});
		Jp.add(usrtf);
		
		//usrpf=new JPasswordField("mypass");
		usrpf=new JPasswordField();
		usrpf.setBounds(730,150,130,30);
		usrpf.addKeyListener(new logbtn());
		usrpf.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e)
			{
				usrpf.setText("");
			}
		});
		Jp.add(usrpf);   
		
		logbt=new JButton("LOGIN");
		logbt.setBounds(520,250,100,35);
		logbt.addActionListener(new logbtn());
		logbt.addKeyListener(new logbtn());
		
		Jp.add(logbt);
		
		rstbt=new JButton("Reset");
		rstbt.setBounds(630,250,100,35);
		rstbt.addActionListener(new rstbtn());
		Jp.add(rstbt);
		
		nwbt=new JButton("New user?");
		nwbt.setBounds(580,300,100,35);
		nwbt.addActionListener(new nwbtn());
		Jp.add(nwbt);
		
		back=new JButton("< Back");
		back.setBounds(10,60,100,25);
		back.addActionListener(new backn());
		Jp.add(back);
		if(Welcome.bstack.empty()){
			back.setEnabled(false);
		}
//		if(Welcome.i==0)
//		{
//			back.setEnabled(false);
//		}
		frwd=new JButton("Forward >");
		frwd.setBounds(120,60,100,25);
		if(Welcome.fstack.empty()){
			frwd.setEnabled(false);
		}
//		if(Welcome.j==0)
//		{
//			frwd.setEnabled(false);
//		}
		frwd.addActionListener(new frwdn());
		Jp.add(frwd);
		
		Jf.add(Jp);
		Jf.setVisible(true);
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
	
		}
	}
	
	class backn implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			Jf.dispose();
	    	Class<?> clazz;
			try {
				clazz = Class.forName(Welcome.bstack.pop());
		    	Constructor<?> ctor = clazz.getConstructor();
		    	Object object = ctor.newInstance();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
//			Welcome.i--;
//			Welcome.j++;
//			Jf.dispose();
//			new Welcome();
		}
	}
	
	class frwdn implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			JOptionPane.showMessageDialog(null,"Pls Login to continue");
			//Welcome.j=0;
		}
	}
	
	class rstbtn implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			usrtf.setText(null);
			usrpf.setText("");
		}
	}
	
	class nwbtn implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			Welcome.i++;
			if(Welcome.j!=0)
			{
				Welcome.j--;
			}
			Jf.dispose();
			new UserReg();
		}
	}
	
	class logbtn implements ActionListener,KeyListener
	{
		public void actionPerformed(ActionEvent e)
		{
			String aid=usrtf.getText();
			String pwd=usrpf.getText();
			Connection cnt=DataBaseConnection.javaConnection();
			try 
			{
				String qur="select * from accountinfo where accno=? and password=?";
				PreparedStatement stt = cnt.prepareStatement(qur);
				stt.setString(1,aid);
				stt.setString(2,pwd);
				ResultSet rr=stt.executeQuery();
				if(rr.next())
				{
					msg=new JLabel("Welcome "+rr.getString("name"),JLabel.CENTER);
					JOptionPane.showMessageDialog(null,msg,"",JOptionPane.PLAIN_MESSAGE);
					Welcome.i++;	
					Jf.dispose();
					new UserHome(aid,rr.getString("name"));
				}
				else
				{
					JOptionPane.showMessageDialog(null,"Invalid Acc.no or password");
					usrtf.setText(null);
					usrpf.setText("");
				}
			}
			catch (SQLException ex) 
			{
				ex.printStackTrace();
			}
		}

		public void keyPressed(KeyEvent arg0) {
			int key= arg0.getKeyCode();
			if(key== arg0.VK_ENTER)
			{
				actionPerformed(null);
			}	
		}

		public void keyReleased(KeyEvent arg0) {
			
		}

		public void keyTyped(KeyEvent arg0) {
				
		}
	}
}

