package view;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.net.Socket;
import java.awt.Window;

import model.People;
import view_HUD.Manage_Fr_Hud;

import java.io.IOException;

import javax.swing.*;

import control.Vcontrol;

public class PC_Set {
	private static String Time="ㅡㅡ";
	Vcontrol vc;
	Manage mf_;
	
	
	public PC_Set(int num){
	Vcontrol vc = Vcontrol.getInstance("퇴실시간을 손님께");
	this.mf_=vc.mf;
	
	Dimension dim = new Dimension(400,100);
	
	JFrame frame = new JFrame("퇴실시간 설정");
	frame.setLocation(500,400);
	frame.setPreferredSize(dim);
	
	JTextField textfield = new JTextField();
	
	JLabel label = new JLabel("퇴실 시간을 입력해주세요(XX:XX)");
	label.setHorizontalAlignment(SwingConstants.CENTER);
	label.setVerticalAlignment(SwingConstants.CENTER);
	
	JButton button = new JButton("확인");
	button.addActionListener(new ActionListener() {
		
		public void actionPerformed(ActionEvent e) {
			String time =textfield.getText();
			frame.dispose();
			
			//소켓보내기 : 종료시간
			Socket socket;
			DataOutputStream out;
			try {
				socket = vc.clients.get(vc.pcseat[num]);
				out = new DataOutputStream(socket.getOutputStream());
				out.writeUTF("퇴실시간");
				out.writeUTF(time);
		    }
		    catch (IOException i) {
		    	System.out.println("퇴실시간  설정 오류");
		    }
			mf_.pan[num].label[0].setForeground(Color.red);
			mf_.pan[num].label[2].setText(time);
			
			roomInfo.getTime(time);
			//Client_view.getTime(time);
			//Client_view.main(null);
		}

	});
	
	frame.add(textfield, BorderLayout.CENTER);
	frame.add(label, BorderLayout.NORTH);
	frame.add(button, BorderLayout.SOUTH);
	
	frame.pack();
	frame.setVisible(true);
	}
	
	
	
}
