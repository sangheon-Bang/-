package chat.client;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ClientGui  extends JFrame implements ActionListener{

    private JTextArea jta = new JTextArea(40, 25);
	private JTextField jtf = new JTextField(25);
	private ClientBackground client = new ClientBackground();
	
	
	public ClientGui() {
		
		add(jta, BorderLayout.CENTER);
		add(jtf, BorderLayout.SOUTH);
		jtf.addActionListener(this);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setBounds(800, 100, 400, 600);
		setTitle("n¹ø ¹æ °í°´");
		
		client.setGui(this);
		client.connet();
	}
	
	public static void main(String[] args) {
		new ClientGui();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String msg ="¼Õ´Ô :"+ jtf.getText()+"\n";
		jta.append(msg);
		System.out.print(msg);
		client.sendMessage(msg);
		jtf.setText("");
	}

	public void appendMsg(String msg) {
		jta.append(msg);
		
	}
}
