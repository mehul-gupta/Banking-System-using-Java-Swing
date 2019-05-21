package design;

import java.sql.*;
import java.util.ArrayList;

public class Datamethods {
	
	public static ArrayList<loan> myloans(String aid){
		
		ArrayList<loan> lnlist= new ArrayList<>();
		Connection cnt= DataBaseConnection.javaConnection();
		try {
			Statement stt= cnt.createStatement();
			String qq="Select * from loan where accno='"+aid+"'";
			ResultSet rr= stt.executeQuery(qq);
			while(rr.next())
			{
				loan e= new loan();
				e.setSno(rr.getString("Sno"));
				e.setAccno(rr.getString("accno"));
				e.setAmount(rr.getString("amount"));
				e.setStatus(rr.getString("status"));
				
				lnlist.add(e);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return lnlist;
	}
	
	public static ArrayList<loan> loanreq(){
		
		ArrayList<loan> lnlist= new ArrayList<>();
		Connection cnt= DataBaseConnection.javaConnection();
		try {
			Statement stt= cnt.createStatement();
			String qq="Select * from loan where status='pending'";
			ResultSet rr= stt.executeQuery(qq);
			while(rr.next())
			{
				loan e= new loan();
				e.setSno(rr.getString("Sno"));
				e.setAccno(rr.getString("accno"));
				e.setAmount(rr.getString("amount"));
				e.setStatus(rr.getString("status"));
				
				lnlist.add(e);
			}	
			/*for(transaction e:elist)
			{	 
				System.out.println(e.getIdt()+"  "+e.getTdate()+"  "+e.getAccno()+"  "+e.getBalance()+"  "+e.getTtype());
			}*/
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return lnlist;

	}
	

	public static ArrayList<transaction> transinfo(String aid) {
		
		ArrayList<transaction> elist= new ArrayList<>();
		Connection cnt= DataBaseConnection.javaConnection();
		try {
			Statement stt= cnt.createStatement();
			String qq="Select * from transaction where accno='"+aid+"'";
			ResultSet rr= stt.executeQuery(qq);
			while(rr.next())
			{
				transaction e= new transaction();
				e.setIdt(rr.getString("idt"));
				e.setTdate(rr.getString("tdate"));
				e.setAccno(rr.getString("accno"));
				e.setBalance(rr.getString("balance"));
				e.setTtype(rr.getString("ttype"));
				
				elist.add(e);
			}	
			/*for(transaction e:elist)
			{	 
				System.out.println(e.getIdt()+"  "+e.getTdate()+"  "+e.getAccno()+"  "+e.getBalance()+"  "+e.getTtype());
			}*/
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return elist;
	}
	
	public static ArrayList<accinfo> editacc(String aid) {
		
		ArrayList<accinfo> elist= new ArrayList<accinfo>();
		Connection cnt= DataBaseConnection.javaConnection();
		try {
			Statement stt= cnt.createStatement();
			String qq="Select * from accountinfo where name like '%"+aid+"%' or accno like '%"+aid+"%'";
			ResultSet rr= stt.executeQuery(qq);
			while(rr.next())
			{
				accinfo e= new accinfo();
				e.setAccno(rr.getString("accno"));
				e.setName(rr.getString("name"));
				e.setFname(rr.getString("fname"));
				e.setBalance(rr.getString("balance"));
				e.setAddress(rr.getString("address"));
				e.setMobile(rr.getString("mobile"));
				e.setEmail(rr.getString("email"));
				e.setMobile(rr.getString("mobile"));
				e.setGender(rr.getString("gender"));
				
				elist.add(e);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return elist;
	}

}
