package im_qq;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.util.StringTokenizer;

import javax.swing.*;

public class loginFrame extends JFrame implements ActionListener {

	public static JFrame loginf;
	JButton btLogin;
	JButton btRegister;
	public static JTextField txtName;
	JPasswordField txtPassword;
	public static String user;
	JTextField txtServerIP = new JTextField("127.0.0.1");
	JComboBox listOnline;
	JTextField regName;
	JPasswordField regPassword;
	JPasswordField regPassword2;
	JButton btReg;
	JButton btReturn;
	JFrame registerf;

	Socket socket = null;
	BufferedReader in = null;
	PrintWriter out = null;

	String strSend, strReceive, strKey, strStatus, strTalk;
	private StringTokenizer st;

	public loginFrame() {
		// 创建im qq登录界面

		loginf = new JFrame();
		loginf.setLocation(400, 200);
		loginf.setSize(450, 350);

		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel();
		JPanel panel4 = new JPanel();

		panel1.setSize(450, 150);
		panel2.setSize(200, 50);
		panel3.setSize(200, 50);
		panel4.setSize(500, 50);

		ImageIcon loginPicture = new ImageIcon("timg.jpg");
		ImageIcon loginbg = new ImageIcon("timg2.jpg");

		JLabel l1 = new JLabel(loginPicture);
		JLabel lblogbg = new JLabel(loginbg);
		lblogbg.setBounds(0, 0, loginbg.getIconWidth(), loginbg.getIconHeight());

		loginf.getLayeredPane().add(lblogbg, new Integer(Integer.MIN_VALUE));
		JPanel touming = (JPanel) loginf.getContentPane();
		touming.setOpaque(false);

		JLabel l2 = new JLabel("用户名："); // 创建用户名输入框
		txtName = new JTextField("", 22);

		JLabel l3 = new JLabel(" 密    码："); // 创建密码输入框
		txtPassword = new JPasswordField("", 22);

		btLogin = new JButton("登录"); // 创建登录按钮
		btRegister = new JButton("注册"); // 创建注册按钮
		btLogin.addActionListener(this);
		btRegister.addActionListener(this);

		panel1.add(l1);
		panel1.setBounds(0, 0, 450, 150);
		panel2.add(l2);
		panel2.add(txtName);
		panel3.add(l3);
		panel3.add(txtPassword);

		panel4.add(btLogin);
		panel4.add(btRegister);
		panel1.setOpaque(false);
		panel2.setOpaque(false);
		panel3.setOpaque(false);
		panel4.setOpaque(false);

		loginf.getContentPane().add(panel1);
		loginf.getContentPane().add(panel2);
		loginf.getContentPane().add(panel3);
		loginf.getContentPane().add(panel4);

		loginf.getContentPane().setLayout(new FlowLayout());

		loginf.setResizable(false);
		loginf.setVisible(true);
		// **************************注册界面*********************
		registerf = new JFrame();
		registerf.setLocation(500, 250);
		registerf.setSize(350, 250);

		JPanel regpanel1 = new JPanel();
		JPanel regpanel2 = new JPanel();
		JPanel regpanel3 = new JPanel();
		JPanel regpanel4 = new JPanel();
		JPanel regpanel5 = new JPanel();

		JLabel regl1 = new JLabel("注册账号                                 ");

		JLabel regl2 = new JLabel("用户名："); // 创建用户名输入框
		regName = new JTextField("", 12);

		JLabel regl3 = new JLabel("  密码：  ");
		regPassword = new JPasswordField("", 12);

		JLabel regl4 = new JLabel("请重复输入密码：");
		regPassword2 = new JPasswordField("", 12);

		btReg = new JButton("注册");
		btReturn = new JButton("返回");
		btReg.addActionListener(this);
		btReturn.addActionListener(this);

		regpanel1.add(regl1);
		regpanel2.add(regl2);
		regpanel2.add(regName);
		regpanel3.add(regl3);
		regpanel3.add(regPassword);
		regpanel4.add(regl4);
		regpanel4.add(regPassword2);
		regpanel5.add(btReg);
		regpanel5.add(btReturn);

		registerf.add(regpanel1);
		registerf.add(regpanel2);
		registerf.add(regpanel3);
		registerf.add(regpanel4);
		registerf.add(regpanel5);

		registerf.setLayout(new FlowLayout(FlowLayout.LEFT));
		registerf.setVisible(false);

	}

	public static void main(String[] args) {
		new loginFrame();
	}

	private void initLogin() throws IOException // 登录，登录界面消失，出现好友界面
	{
		strReceive = in.readLine();
		st = new StringTokenizer(strReceive, "|");
		strKey = st.nextToken();
		if (strKey.equals("login")) {
			strStatus = st.nextToken();
			if (strStatus.equals("succeed")) {
				loginf.setEnabled(false);
				loginf.setVisible(false);
				loginf.dispose();
				new ClientThread(socket);
				out.println("init|online");
				popWindows("登录成功!", "登陆成功");
				new FriendView();
			}

		}
		if (strKey.equals("regsucceed")) {
			strStatus = st.nextToken();
			registerf.setEnabled(false);
			popWindows("注册成功", "注册成功");
			regName.setText("");
			regPassword.setText("");
			regPassword2.setText("");
			registerf.dispose();
		}
		if (strKey.equals("loginwarning")) {
			strStatus = st.nextToken();
			popWindows(strStatus, "登陆失败");
		}
		if (strKey.equals("regwarning")) {
			strStatus = st.nextToken();
			popWindows(strStatus, "注册失败");
		}
	}

	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj.equals(btLogin)) // 登录按钮
		{
			if ((txtServerIP.getText().length() > 0) && (txtName.getText().length() > 0)
					&& (String.valueOf(txtPassword.getPassword()).length() > 0)) {
				connectServer();
				user = txtName.getText();
				strSend = "login|" + txtName.getText() + "|" + String.valueOf(txtPassword.getPassword());
				
				out.println(strSend);
				try {
					initLogin();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else {
				popWindows("请输入账号密码,账号密码不能为空", "错误");
			}
		}

		if (obj.equals(btRegister))// 注册按钮，点击进入注册界面
		{
			registerf.setVisible(true);
		}

		if (obj.equals(btReturn)) // 返回按钮，点击返回登录界面
		{
			regName.setText("");
			regPassword.setText("");
			regPassword2.setText("");
			registerf.setVisible(false);
		}

		if (obj.equals(btReg)) {
			if ((regName.getText().length() > 0) && (regPassword.getPassword().toString().length() > 0)) {
				if (String.valueOf(regPassword.getPassword()).equals(String.valueOf(regPassword2.getPassword()))) {
					connectServer();
					strSend = "reg|" + regName.getText() + "|" + String.valueOf(regPassword.getPassword());
					out.println(strSend);
					try {
						initLogin();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					registerf.dispose();
				} else {
					popWindows("两次输入密码不一致", "错误");
				}
			} else {
				popWindows("账号密码不能为空", "错误");
			}
		}

		if (obj.equals(talkFrame.b_insert))
		{
			if (talkFrame.addText.getText().length() > 0)
			{
				connectServer();
				strSend = "talk|" + talkFrame.addText.getText() + "|" + user + "|" + FriendView.beTalkedTo;
				out.println(strSend);
				talkFrame.addText.setText("");
			}
		}
	}

	// 建立与服务端通信的字
	void connectServer() {
		try {
			socket = new Socket(txtServerIP.getText(), 8888);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
		} catch (ConnectException e) {
			JOptionPane.showMessageDialog(this, "连接服务器失败!", "ERROE", JOptionPane.INFORMATION_MESSAGE);
			txtServerIP.setText("");
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public void popWindows(String strWarning, String strTitle) // 弹出警示框！
	{
		JOptionPane.showMessageDialog(this, strWarning, strTitle, JOptionPane.INFORMATION_MESSAGE);
	}

	class ClientThread implements Runnable // 利用多线程实现socket传输
	{
		private Socket socket;
		private BufferedReader in;
		private PrintWriter out;
		private String strReceive, strKey;
		private Thread threadTalk;
		private StringTokenizer st;

		public ClientThread(Socket s) throws IOException {
			this.socket = s;
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			threadTalk = new Thread(this);
			threadTalk.start();
		}

		public void run() {
			while (true) {
				synchronized (this) {
					try {
						strReceive = in.readLine();
						st = new StringTokenizer(strReceive, "|");
						strKey = st.nextToken();
						if (strKey.equals("talk")) {
							String strTalk = st.nextToken();
							strTalk = talkFrame.text.getText() + "\r\n   " + strTalk;
							talkFrame.text.setText(strTalk);
						} else if (strKey.equals("online")) {
							String strOnline;
							while (st.hasMoreTokens()) {
								strOnline = st.nextToken();

							}
						} else if (strKey.equals("remove")) {
							String strRemove;
							while (st.hasMoreTokens()) {
								strRemove = st.nextToken();
								listOnline.removeItem(strRemove);
							}
						} else if (strKey.equals("warning")) {
							String strWarning = st.nextToken();
							popWindows(strWarning, "Warning");
						}
						Thread.sleep(1000);
					} catch (InterruptedException e) {
					} catch (IOException e) {
					}
				}
			}
		}
	}

}
