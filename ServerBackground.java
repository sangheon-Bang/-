package chat.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerBackground {

    // �̽�1. ���� �޽����� �ְ�ް� �;��. 
	// �̽�2. �ϱ����� ���� GUI �� ���鵵�� �ϰڽ��ϴ�. 
	// �̽�3. ����
	
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
			System.out.println("���� �����...");
			socket = serverSocket.accept();
			System.out.println(socket.getInetAddress()+"���� �����߽��ϴ�.");
		
			out = new DataOutputStream(socket.getOutputStream());
			in = new DataInputStream(socket.getInputStream());
			
			msg = in.readUTF();
			out.writeUTF("������ ���͵帱���?\n");
			System.out.println("Ŭ���̾�Ʈ�κ����� �޽��� : "+msg);
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
