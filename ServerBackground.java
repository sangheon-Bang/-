package chat.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerBackground {

    // 이슈1. 뭔가 메시지를 주고받고 싶어요. 
	// 이슈2. 하기전에 먼저 GUI 를 만들도록 하겠습니다. 
	// 이슈3. 연동
	
	private ServerSocket serverSocket;
	private Socket socket;
	private DataInputStream in;
	private DataOutputStream out;
	private ServerGui gui;
	private String msg;
	
	
	
	public final void setGui(ServerGui gui) {
		this.gui = gui;
	}

	public void setting(){
		try {
			serverSocket = new ServerSocket(7777);
			System.out.println("서버 대기중...");
			socket = serverSocket.accept();
			System.out.println(socket.getInetAddress()+"에서 접속했습니다.");
		
			out = new DataOutputStream(socket.getOutputStream());
			in = new DataInputStream(socket.getInputStream());
			
			msg = in.readUTF();
			out.writeUTF("무엇을 도와드릴까요?\n");
			System.out.println("클라이언트로부터의 메시지 : "+msg);
			gui.appendMsg(msg);
			
			while(in!=null) {
				msg=in.readUTF();
				gui.appendMsg(msg);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		ServerBackground serverBackground= new ServerBackground();
		serverBackground.setting();
			
	}

	public void sendMessage(String msg) {
		try {
			out.writeUTF(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
