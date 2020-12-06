package chat.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientBackground {
	
	private Socket socket;
	private DataInputStream in;
	private DataOutputStream out;
	private ClientGui gui;
	private String msg;
	private String nickName;
	
	public final void setGui(ClientGui gui) {
		this.gui = gui;
	}

	public void connet() {
		try {
			socket = new Socket("127.0.0.1", 7777);
			System.out.println("서버 연결됨");
			
			out= new DataOutputStream(socket.getOutputStream());
			in = new DataInputStream(socket.getInputStream());
		
			out.writeUTF("n번 방에서 채팅시스템에 접속하셨습니다.\n");
			System.out.println("메세지 전송완료");
			
			while(in!=null) {
				msg=in.readUTF();
				gui.appendMsg(msg);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		ClientBackground clientBackground = new ClientBackground();
		clientBackground.connet();
	}

	public void sendMessage(String msg2) {
		try {
			out.writeUTF(msg2);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	
}
