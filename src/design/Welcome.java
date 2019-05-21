package design; 

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
import java.util.Stack;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Welcome {
	
	public static int i=0,j=0;
	public static Stack<String> bstack;
	public static Stack<String> fstack;
	JFrame Jf;
	JPanel Jp;
	JLabel wlcm,msg,design,news;
	JButton usrbt,admbt,test;
	JButton back,frwd;
	static boolean b=false;
	public Welcome()
	{
		bstack=new Stack<String>();
		fstack=new Stack<String>();
		Jf=new JFrame("LOGIN");
		Jp=new mypanel();
		//Jp.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		Jp.setLayout(null);
		/*Color cc=new Color(243,250,182);
		Jp.setBackground(cc);*/
		//Jp.setBackground(Color.WHITE);
		
		wlcm=new JLabel("Welcome to 24x7 Bank");
		int screen_size=(int)Jf.getToolkit().getScreenSize().getWidth();
		wlcm.setBounds(screen_size/2-350,30,460,30);
		wlcm.setForeground(Color.BLUE);
		Font ff=new Font("Brush Script MT",Font.BOLD,50);
		wlcm.setFont(ff);
		Jp.add(wlcm);
		
		usrbt=new JButton("USER");
		usrbt.setFont(new Font("Arial",Font.BOLD,15));
		usrbt.setForeground(Color.RED);
		usrbt.setBounds(screen_size/2-250,240,120,45);
		usrbt.addActionListener(new usrbtn());
		Jp.add(usrbt);
		
		admbt=new JButton("ADMIN");
		admbt.setFont(new Font("Arial",Font.BOLD,15));
		admbt.setForeground(Color.RED);
		admbt.setBounds(screen_size/2-100,240,120,45);
		admbt.addActionListener(new admbtn());
		Jp.add(admbt);
		
		back=new JButton("< Back");
		back.setBounds(10,120,100,25);
		if(bstack.empty()){
			back.setEnabled(false);
		}
//		if(Welcome.i==0)
//		{
//			back.setEnabled(false);
//		}
		back.addActionListener(new backn());
		Jp.add(back);
		
		frwd=new JButton("Forward >");
		frwd.setBounds(120,120,100,25);
		if(fstack.empty()){
			frwd.setEnabled(false);
		}
//		if(Welcome.j==0)
//		{
//			frwd.setEnabled(false);
//		}
		frwd.addActionListener(new frwdn());
		Jp.add(frwd);
		
		msg=new JLabel("WELCOME TO ONLINE BANKING...!!",JLabel.CENTER);
		//JOptionPane.showMessageDialog(null,msg,"",JOptionPane.PLAIN_MESSAGE);
		
		design=new JLabel("<html>Designed and developed by<br>&emsp;MEHUL GUPTA</html>");
		design.setBounds((int)Jf.getToolkit().getScreenSize().getWidth()-500,720,400,100);
		design.setForeground(Color.BLUE);
		Font f2=new Font("Times New Roman",Font.BOLD,27);
		design.setFont(f2);
		Jp.add(design);
		
		news=new JLabel("<html>> Enjoy the ease of paying GST online through 24x7 bank Netbanking</html>");
		news.setBounds(30,250,180,160);
		news.setForeground(Color.BLACK);
		Font f3 = new Font("Tahoma",Font.PLAIN,18);
		news.setFont(f3);
		Jp.add(news);
		
		Jf.add(Jp);
		Jf.setVisible(true);
		Jf.setSize(Jf.getToolkit().getScreenSize());
		Jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	class mypanel extends JPanel
	{
		private static final long serialVersionUID = 1L;
		
		String list[]={
				"images/bank2-kq5H--621x414@LiveMint.jpg",
				"images/bank-650_650x400_51473756311.jpg",
				"images/emv-card-chip-web-banner.jpg",
				"images/currentaccount_small.jpg",
				"images/cooperative-bank-logo.jpg",
				"images/networktheor.jpg",
				"images/MultiAppMobileBanking.jpg",
				"images/New SA Web Banner-01 (NXPowerLite).jpg"
			};
		int x=0;
		JLabel pic;
		Timer tm;
		
		public mypanel()
		{
			pic=new JLabel();
			pic.setBounds((int)Jf.getToolkit().getScreenSize().getWidth()-600,(int)Jf.getToolkit().getScreenSize().getHeight()/2-300,450,260);
			SetImageSize(0);
			
			tm=new Timer(2000,new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					SetImageSize(x);
					x+=1;
					if(x>=list.length)
					{
						x=0;
					}
				}
			});
			this.add(pic);
			tm.start();
			
		}
		
		public void paintComponent(Graphics g)
		{
			Image i1= new ImageIcon("images/img1.jpg").getImage();
			g.drawImage(i1,0,0,this.getWidth(),this.getHeight(),this);
			//g.setColor(Color.decode("#F9BF3B"));
			//g.fillRect(0,0,this.getWidth(),this.getHeight());
			Image ii= new ImageIcon("images/logonew.png").getImage();
			g.drawImage(ii,(int)Jf.getToolkit().getScreenSize().getWidth()/2+120, 10, 280, 115, this);
			Image i2= new ImageIcon("images/secure.jpg").getImage();
			g.drawImage(i2,(int)Jf.getToolkit().getScreenSize().getWidth()-550, 550, 380, 150, this);
			
			Font font = new Font("Tahoma",Font.BOLD+Font.PLAIN,19);		
											//Font font = new Font("Tahoma",Font.BOLD+Font.PLAIN,24);
	        g.setFont(font);
	        g.setColor(Color.BLUE);
	        g.drawString("Pay GST Online",30,270);
	        Image i3= new ImageIcon("images/new.gif").getImage();
			g.drawImage(i3, 150, 240, 40, 20, this);

		}
		
		public void SetImageSize(int i){
			Image img=new ImageIcon(list[i]).getImage();
			Image newImg=img.getScaledInstance(pic.getWidth(),pic.getHeight(),Image.SCALE_SMOOTH);
			ImageIcon newImc=new ImageIcon(newImg);
			pic.setIcon(newImc);
		}
	}
	
	class backn implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(bstack.empty()){
				back.setEnabled(false);
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
			if(fstack.empty()){
				frwd.setEnabled(false);
			}else{
				Class<?> clazz;
				try {
					clazz = Class.forName(Welcome.fstack.pop());
			    	Constructor<?> ctor = clazz.getConstructor();
			    	Object object = ctor.newInstance();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
//			Welcome.j--;
//			Welcome.i++;
//			Jf.dispose();
//			if(Welcome.b==false)
//			{
//				new UserLog();
//			}
//			else
//			{
//				new AdminLog();
//			}
		}
	}
	
	class usrbtn implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			bstack.push("design.Welcome");
//			Welcome.b=false;
//			Welcome.i++;
//			if(Welcome.j!=0)
//			{
//				Welcome.j--;
//			}
			Jf.dispose();
			new UserLog();
		}
	}
	
	class admbtn implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			bstack.push("design.Welcome");
//			Welcome.b=true;
//			Welcome.i++;
//			if(Welcome.j!=0)
//			{
//				Welcome.j--;
//			}
			Jf.dispose();
			new AdminLog();
		}
	}

}
