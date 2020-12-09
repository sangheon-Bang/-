package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import control.stock.dbprocess.RoomInitProcess;
import control.stock.dbprocess.RoomSearchProcess;
import control.stock.dbprocess.RoomUpdateProcess;


public class roomInfo{
	static String end_time="";
	String header[]  = { "객실번호", "피씨번호", "객실등급", "입실여부","종료시간" };
	
	DefaultTableModel model = new DefaultTableModel(header,0);
	JTable table= new JTable(model);
	JScrollPane jsp = new JScrollPane(table);
	
	
	public roomInfo(){
	Dimension dim = new Dimension(600,800);
	JFrame frame = new JFrame("객실현황");
	frame.setLocation(450,10);
	frame.setPreferredSize(dim);
	
	frame.add(jsp);
	RoomSearchProcess.readRoom(model);
	
	frame.pack();
	frame.setVisible(true);
	
	JButton button = new JButton("초기화");
	button.addActionListener(new ActionListener() {
		
		public void actionPerformed(ActionEvent e) {
			RoomInitProcess.roomInfoInit("-",103,1);
			frame.dispose();
		}

	});
	
	frame.add(button, BorderLayout.SOUTH);
	
	}
	
	
	
	public static void getTime(String time) {
		end_time=time;
		RoomUpdateProcess.roomInfoChange(end_time,103,1);
		
	}
	
	
	public static void main(String[] args) {
		new roomInfo();
	}
}