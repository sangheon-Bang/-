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
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import _client.view.ClientSet;
import control.Vcontrol;
import view.ImagePanel;

import static model.SaveVariable.save_image;
@SuppressWarnings("serial")
public class AD extends JFrame {
	
	private JFrame frame = new JFrame("광고");
	private JPanel imgpan = new JPanel();
	private JPanel southpanel = new JPanel();
	private JButton btn_b = new JButton("광고 이미지 변경");
	private Dimension dimen_a;
	private Dimension dimen_b;
	private int xpos, ypos;
	private String imgname;

	public AD() {
		southpanel.setBackground(Color.green);
		southpanel.add(btn_b);
		btn_b.addActionListener(new UploadImage());
		frame.add(southpanel, "South");
		frame.setSize(500, 700);
		dimen_a = Toolkit.getDefaultToolkit().getScreenSize();
		dimen_b = Toolkit.getDefaultToolkit().getScreenSize();
		xpos = dimen_a.width / 5;
		ypos = dimen_b.height / 5;
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.setLocation(xpos, ypos);
		frame.setResizable(false);
		
		imgname = save_image;
		imgpan=new ImagePanel(Toolkit.getDefaultToolkit().createImage("noticeimg/" + imgname + ".png"),500,500);
		frame.add(imgpan, "Center");
		frame.setVisible(true);
	}

	public void getimage() {
		
		imgpan=new ImagePanel(Toolkit.getDefaultToolkit().createImage("noticeimg/" + imgname + ".png"),500,500);
		save_image = imgname;
		frame.add(imgpan, "Center");
		frame.setVisible(true);
		
		Vcontrol vc = Vcontrol.getInstance("공지이미지를 손님께");
		Socket socket;
		DataOutputStream out;
		BufferedInputStream bis;
		File f=new File("noticeimg/" + imgname + ".png");
		int len=(int)f.length();
		byte[] buffer=new byte[len];
		try {
			bis=new BufferedInputStream(new FileInputStream(f));
			while(bis.read(buffer)!=-1) {}
			bis.close();
	    }
	    catch (IOException e) {
	    	
	    }
		//전송
		for (int i=0;i<50;i++) {
			try {
				socket = vc.clients.get(vc.pcseat[i]);
				if(socket==null) continue;
				out = new DataOutputStream(socket.getOutputStream());
				out.writeUTF("공지이미지");
				out.writeInt(len);
				out.write(buffer);
		    }
		    catch (IOException e) {
		    	
		    }
		}
		
		
	}
	
	

	private class UploadString implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			//StringUpload stu= new StringUpload();
		}
	}

	private class UploadImage implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			ImageUpload imu= new ImageUpload();
		}
	}
	
	public class ImageUpload extends JFrame {

		private JTextField StringField = new JTextField(27);
		private JPanel centerPanel = new JPanel();
		private JButton insertBtn = new JButton("확인");
		private int xpos, ypos;
		private String prename;

		public ImageUpload() {
			super("이미지 파일명 입력");
			prename=imgname;

			xpos = Toolkit.getDefaultToolkit().getScreenSize().width / 5;
			ypos = Toolkit.getDefaultToolkit().getScreenSize().height / 3;
			
			centerPanel.add(StringField);
			centerPanel.add(insertBtn);
			add(centerPanel, BorderLayout.CENTER);
			StringField.setText(prename);

			insertBtn.addActionListener(new InsertProcess());
			setBounds(xpos, ypos, 400, 75);
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			setVisible(true);

		}

		public class InsertProcess implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				imgname = StringField.getText();

				if (imgname.equals("")) {
					JOptionPane.showMessageDialog(null, "파일명을 입력하세요");
					return;
				}
				
				else if (imgname.equals(prename)) {
					JOptionPane.showMessageDialog(null, "다른 파일명을 입력하세요");
					return;
				}
				
				StringField.setText(imgname);
				getimage();
				

				JOptionPane.showMessageDialog(null,"공지사항이 업로드 되었습니다.");
				dispose();
			}
		}
	}
	
}
