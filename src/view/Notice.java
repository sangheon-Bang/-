package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import control.Vcontrol;
@SuppressWarnings("serial")

public class Notice extends JFrame {
	
	private JFrame frame = new JFrame("전체 공지");
	private JPanel southpanel = new JPanel();
	private JButton btn_b = new JButton("공지 글 전송");
	private Dimension dimen_a;
	private Dimension dimen_b;
	private int xpos, ypos;
	public JTextArea ta = new JTextArea(25, 25);
	private String notivestring;
	Vcontrol vc;
	Socket socket;
	DataOutputStream out;

	public Notice() {
		southpanel.setBackground(Color.green);
		southpanel.add(btn_b);
		btn_b.addActionListener(new UploadString());
		frame.add(southpanel, "South");
		frame.setSize(300, 400);
		dimen_a = Toolkit.getDefaultToolkit().getScreenSize();
		dimen_b = Toolkit.getDefaultToolkit().getScreenSize();
		xpos = dimen_a.width / 5;
		ypos = dimen_b.height / 5;
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.setLocation(xpos, ypos);
		frame.add(ta, BorderLayout.CENTER);
		frame.setVisible(true);
		
		vc = Vcontrol.getInstance("공지글을 손님께");
	}
	

	private class UploadString implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			for (int i=0;i<50;i++) {
				try {
					socket = vc.clients.get(vc.pcseat[i]);
					if(socket==null) continue;
					out = new DataOutputStream(socket.getOutputStream());
					out.writeUTF("메시지");
					out.writeUTF(" < 전체 공지 >\n" + ta.getText());
			    }
			    catch (IOException e) {
			    	
			    }
			}
		}
	}
	
}
