package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import control.Vcontrol;


public class Client_view implements ActionListener {
	static String time;
	JButton button1, button2, button3;
	Vcontrol vcm = Vcontrol.getInstance("시트팬");
	
	public Client_view(){
		
		
		Dimension dim = new Dimension(345,450);
		
		JFrame frame = new JFrame("103-1 PC");
		frame.setLocation(500,400);
		frame.setPreferredSize(dim);
		

		JLabel label = new JLabel("퇴실시간    "+time);
		label.setFont(new Font("배달의민족 한나",Font.BOLD,15));
		label.setPreferredSize(new Dimension(345,100));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setVerticalAlignment(SwingConstants.CENTER);
		
		JPanel jp = new JPanel();
		
		button1 = new JButton("이벤트");
		button1.addActionListener(this);
		button1.setPreferredSize(new Dimension(150, 50));
		button2 = new JButton("공지");
		button2.addActionListener(this);
		button2.setPreferredSize(new Dimension(150, 50));
		button3 = new JButton("메신저");
		button3.addActionListener(this);
		button3.setPreferredSize(new Dimension(305, 50));
		
		jp.add(button1);
		jp.add(button2);
		jp.add(button3);
		
		frame.add(label, BorderLayout.NORTH);
		frame.add(jp, BorderLayout.CENTER);
		frame.pack();
		frame.setVisible(true);
		}
		
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == button1) {
			
		} else if (e.getSource() == button2) {
			
		}
		 else if (e.getSource() == button3) {
				
			}
	}
		
		
	public static void getTime(String time1) {
		time=time1;	
	}
	
		public static void main(String[] args) {
			new Client_view();
		}
		
		
}

