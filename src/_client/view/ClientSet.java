package _client.view;

import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import _client.dbprocess.LoginMemberProcess;

public class ClientSet {// clientlogin 클래스 시작

	
	private JTextField pc;
	private JFrame login;

	ClientSet()// clientlogin 생성자 시작부
	{
		// 프레임 생성
		login = new JFrame("PC 설정");

		// 라벨 생성
		JLabel pc_label = new JLabel("피씨번호 : ");
		JPanel panel = new JPanel();

		// 버튼 생성
		JButton log_btn = new JButton("설정 완료");

		// id,pass,pc객체 생성
		pc = new JTextField();

		// 입력글자수 제한
		pc.setDocument(new JTextFieldLimit(4));

		// 현재 스크린사이즈를 받아온다
		int width = Toolkit.getDefaultToolkit().getScreenSize().width;
		int height = Toolkit.getDefaultToolkit().getScreenSize().height;

		// //컴포넌트 배치부
		pc.setPreferredSize(new Dimension(100,30));
		panel.add(pc_label);
		panel.add(pc);
		login.add(panel, "North");
		login.add(log_btn, "South");
		// 버튼에 이벤트 리스너 결합부
		log_btn.addActionListener(new LoginProcess());

		login.setBounds(width - 300, height / 5 - 100, 240, 100);
		login.setResizable(false);
		login.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		login.setVisible(true);

	}// client_login 생성자 종료부

	// JTextFieldLimit클래스 시작(아이디 비번 입력 글자수 제한)

	private class JTextFieldLimit extends PlainDocument {
		private int limit; // 제한할 길이

		private JTextFieldLimit(int limit) // 생성자 : 제한할 길이를 인자로 받음
		{
			super();
			this.limit = limit;
		}

		// 텍스트 필드를 채우는 메써드 : 오버라이드
		public void insertString(int offset, String str, AttributeSet attr)
				throws BadLocationException {
			if (str == null)
				return;
			if (getLength() + str.length() <= limit)
				super.insertString(offset, str, attr);
		}
	}// JTextFieldLimit클래스 종료


	// LoginProcess 클래스 시작 (login을 처리한다)
	private class LoginProcess implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			JOptionPane.showMessageDialog(null, "로그인 되었습니다.");
			login.dispose();
			ClientPc.doClient=true;
			ClientPc cl = new ClientPc(pc.getText());
		}
	}
	
	// LoginProcess 클래스 종료
}// client_login 클래스 종료부
