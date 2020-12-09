package _client.view;

import java.awt.*;

import javax.swing.*;

import java.awt.event.*;
import java.net.*;
import java.io.*;

import _client.view.ImagePanel; 

//현재 사용중인 피시방 이용자의 이용시간과 이용요금창을 보여준다
public class ClientPc {// 클라이언트 클래스 시작
	
	private String pc; // 현재 사용중인 피시 번호 저장
	private JFrame clFrame;
	private JPanel imgpanel;
	private JLabel userPc;
	private JLabel userTime;
	private Socket socket;
	private DataOutputStream out;
	private DataInputStream in;
	private ClientChat chat;
	private Menu menu;
	protected static boolean doClient=true;

	ClientPc(String pc) {// 클라이언트 생성자시작
		
		this.pc = pc;
		clFrame = new JFrame("이용중");

		// 현재 스크린사이즈를 받아온다
		int width = Toolkit.getDefaultToolkit().getScreenSize().width;
		int height = Toolkit.getDefaultToolkit().getScreenSize().height;

		// 표시라벨
		userPc = new JLabel(pc.substring(0,3)+" - "+pc.substring(3,4));
		userTime = new JLabel("");

		JLabel pc_label = new JLabel("PC 번호:");
		JLabel time_label = new JLabel("퇴실 시간:");

		// 버튼
		JButton chatBtn = new JButton("채팅");
		JButton noticeBtn = new JButton("공지");

		// 컴포넌트가 붙을 패널 생성
		JPanel panel = new JPanel();
		imgpanel = new JPanel();
		imgpanel.setPreferredSize(new Dimension(500,500));


		// 컴포넌트 배치부
		pc_label.setBounds(30, 5, 95, 30);
		time_label.setBounds(30, 30, 95, 30);
		userPc.setBounds(130, 5, 95, 30);
		userTime.setBounds(130, 30, 95, 30);
		chatBtn.setBounds(30, 120, 95, 30);
		noticeBtn.setBounds(150, 120, 95, 30);

		// 컴포넌트 결합부
		panel.add(pc_label);
		panel.add(time_label);
		panel.add(userPc);
		panel.add(userTime);
		panel.add(chatBtn);
		panel.add(noticeBtn);
		panel.setLayout(null);
		clFrame.add(panel);
		clFrame.add(imgpanel,"South");
		imgpanel.setBackground(Color.white);
		
		// 버튼 이벤트 처리부
		chatBtn.addActionListener(new ChatEvent());
		noticeBtn.addActionListener(new NoticeEvent());

		// 현재 프레임 위치 및 크기
		clFrame.setBounds(1400, 0, 500, 700);//이미지가 잘리면 1400을 줄일것
		clFrame.setResizable(false);

		// 유저가 창을 강제 종료시키면 안되므로
		clFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		clFrame.setVisible(true);
		// 소켓 쓰레드시작
		new Thread(new ClientConnector()).start();

	}// 클라이언트 생성자종료

	// 챗이벤트클래스 시작(채팅창을 불러온다)
	private class ChatEvent implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {

			if (chat == null) {
				chat = new ClientChat(out, pc);
				return;
			}
			chat.chatFrameVisible();
			
		}

	}
	// 챗이벤트 클래스 종료
	// 메뉴표를 불러오기 위한 이벤트 클래스 시작
	private class NoticeEvent implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			//

		}

	}

	// 메뉴표를 불러오기 위한 이벤트 클래스 종료
	// 서버와 연결을 위한 클라이언트 커넥터
	private class ClientConnector implements Runnable {

		@Override
		public void run() {
			try {
				String serverIp = "127.0.0.1";// "172.168.0.80";
				socket = new Socket(serverIp, 7777);
				in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
				out = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
				
				int pcNum = Integer.parseInt(pc);
				pcNum=(pcNum/1000-1)*10+pcNum%1000/5+pcNum%10-3;
				out.writeInt(pcNum);
				out.writeUTF("사용 중");
				out.writeUTF("로그인");
				out.flush();
				
				String str;
				while (true) {
					str = in.readUTF();
					System.out.println(str);
					// 퇴실기간 처리부
					if (str.equals("퇴실시간")) {
						userTime.setText(in.readUTF());
						clFrame.setVisible(true);
					}
					
					// 채팅메시지 처리부
					if (str.equals("메시지")) {
						String msg = in.readUTF();
						System.out.println(msg);
						if (chat == null) {
							chat = new ClientChat(out, pc);
						}
						chat.chatFrameVisible();
						chat.addChat(msg);

					}
					
					
					// 공지이미지 처리부
					if (str.equals("공지이미지")) {
						BufferedOutputStream bos=new BufferedOutputStream(new FileOutputStream(new File("read.png")));
						File f;
						int len = in.readInt();
						for(int i=0;len!=i;i++) bos.write(in.read());
						while (true) {
							bos.write(0);
							f=new File("read.png");
							if(f.length()>=len) break;
						}
						Image img=Toolkit.getDefaultToolkit().createImage("read.png");
						imgpanel =new ImagePanel(img,500,500);
						imgpanel.setPreferredSize(new Dimension(500,500));
						clFrame.add(imgpanel,"South");
						clFrame.setVisible(true);
					}
					// 로그아웃 처리부
					if (str.equals("로그아웃")) {
						socket.close();
					}//if (!str.equals("")) 
					System.out.println("완료");
				}

			} catch (IOException e) {// 서버와 연결이 끊어질시 창이 변함
				System.out.println("연결종료");
				if (chat != null) {
					chat.closeFrame();
				}
				doClient=false;
				clFrame.dispose();
				ClientSet cl = new ClientSet();

			} finally {
				if (in != null) {
					try {
						in.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if (out != null) {
					try {
						out.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}

		}

	}// 클라이언트 커넥터종료

}// 클라이언트 클래스 종료
