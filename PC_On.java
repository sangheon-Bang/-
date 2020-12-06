package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Window;


import javax.swing.*;

import control.Vcontrol;

public class PC_On {
	private static String Time="ㅡㅡ";
	
	public PC_On(){
	
	
	Dimension dim = new Dimension(400,100);
	
	JFrame frame = new JFrame("PC 활성화");
	frame.setLocation(500,400);
	frame.setPreferredSize(dim);
	
	JTextField textfield = new JTextField();
	
	JLabel label = new JLabel("종료 시간을 입력해주세요(XX:XX)");
	label.setHorizontalAlignment(SwingConstants.CENTER);
	label.setVerticalAlignment(SwingConstants.CENTER);
	
	JButton button = new JButton("확인");
	button.addActionListener(new ActionListener() {
		
		public void actionPerformed(ActionEvent e) {
			String time =textfield.getText();
			
			roomInfo.getTime(time);
			frame.dispose();
		}

	});
	
	frame.add(textfield, BorderLayout.CENTER);
	frame.add(label, BorderLayout.NORTH);
	frame.add(button, BorderLayout.SOUTH);
	
	frame.pack();
	frame.setVisible(true);
	}
	
	
	
	
	public static void main(String[] args) {
		new PC_On();
	}
	
	
}
