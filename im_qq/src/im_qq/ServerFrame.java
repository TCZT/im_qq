package im_qq;

/*
 * 类名：ServerFrame
 * 描述：服务器窗口
 */
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

// The Server UI.
public class ServerFrame extends JFrame implements ActionListener {
	JTabbedPane tpServer;

	// 服务器信息面板
	JPanel pnlServer, pnlServerInfo;
	JLabel lblNumber, lblServerName, lblIP, lblPort, lblLog;
	JTextField txtNumber, txtServerName, txtIP, txtPort;
	JButton btnStop, btnSaveLog;
	TextArea taLog;

	// 用户信息面板
	JPanel pnlUser;
	JLabel lblUser;
	JList lstUser;
	JScrollPane spUser;

	// 关于本软件
	JPanel pnlAbout;
	JLabel lblVersionNo, lblAbout;

	public ServerFrame() {
		super("WebChat 聊天服务器");
		setSize(550, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		Dimension scr = Toolkit.getDefaultToolkit().getScreenSize();// 在屏幕居中显示
		Dimension fra = this.getSize();
		if (fra.width > scr.width) {
			fra.width = scr.width;
		}
		if (fra.height > scr.height) {
			fra.height = scr.height;
		}
		this.setLocation((scr.width - fra.width) / 2,
				(scr.height - fra.height) / 2);

		// 服务器信息
		pnlServerInfo = new JPanel(new GridLayout(14, 1));
		pnlServerInfo.setBackground(SystemColor.activeCaption);
		pnlServerInfo.setFont(new Font("宋体", 0, 12));
		pnlServerInfo.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder(""),
				BorderFactory.createEmptyBorder(1, 1, 1, 1)));

		lblNumber = new JLabel("当前在线人数:");
		lblNumber.setForeground(SystemColor.window);
		lblNumber.setFont(new Font("宋体", 0, 12));
		txtNumber = new JTextField("0 人", 10);
		txtNumber.setBackground(Color.decode("#d6f4f2"));
		txtNumber.setFont(new Font("宋体", 0, 12));
		txtNumber.setEditable(false);

		lblServerName = new JLabel("服务器名称:");
		lblServerName.setForeground(SystemColor.text);
		lblServerName.setFont(new Font("宋体", 0, 12));
		txtServerName = new JTextField(10);
		txtServerName.setBackground(Color.decode("#d6f4f2"));
		txtServerName.setFont(new Font("宋体", 0, 12));
		txtServerName.setEditable(false);

		lblIP = new JLabel("服务器IP:");
		lblIP.setForeground(SystemColor.window);
		lblIP.setFont(new Font("宋体", 0, 12));
		txtIP = new JTextField(10);
		txtIP.setBackground(Color.decode("#d6f4f2"));
		txtIP.setFont(new Font("宋体", 0, 12));
		txtIP.setEditable(false);

		lblPort = new JLabel("服务器端口:");
		lblPort.setForeground(SystemColor.window);
		lblPort.setFont(new Font("宋体", 0, 12));
		txtPort = new JTextField("8888", 10);
		txtPort.setBackground(Color.decode("#d6f4f2"));
		txtPort.setFont(new Font("宋体", 0, 12));
		txtPort.setEditable(false);

		btnStop = new JButton("关闭服务器(C)");
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				closeServer();
			}
		});
		btnStop.setBackground(SystemColor.inactiveCaptionBorder);
		btnStop.setFont(new Font("宋体", 0, 12));

		pnlServerInfo.setBounds(5, 5, 100, 400);
		pnlServerInfo.add(lblNumber);
		pnlServerInfo.add(txtNumber);
		pnlServerInfo.add(lblServerName);
		pnlServerInfo.add(txtServerName);
		pnlServerInfo.add(lblIP);
		pnlServerInfo.add(txtIP);
		pnlServerInfo.add(lblPort);
		pnlServerInfo.add(txtPort);

		// 服务器面板
		pnlServer = new JPanel();
		pnlServer.setLayout(null);
		pnlServer.setBackground(SystemColor.activeCaption);

		lblLog = new JLabel("[服务器日志]");
		lblLog.setForeground(SystemColor.text);
		lblLog.setFont(new Font("宋体", 0, 12));
		taLog = new TextArea(20, 50);
		taLog.setFont(new Font("宋体", 0, 12));

		btnSaveLog = new JButton("保存日志(S)");
		btnSaveLog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				saveLog();
			}
		});
		btnSaveLog.setBackground(SystemColor.inactiveCaptionBorder);
		btnSaveLog.setFont(new Font("宋体", 0, 12));

		lblLog.setBounds(110, 5, 100, 30);
		taLog.setBounds(110, 35, 300, 370);
		btnStop.setBounds(200, 410, 120, 30);
		btnSaveLog.setBounds(320, 410, 120, 30);

		//
		pnlServer.add(pnlServerInfo);
		pnlServer.add(lblLog);
		pnlServer.add(taLog);
		pnlServer.add(btnStop);
		pnlServer.add(btnSaveLog);

		// 用户面板
		pnlUser = new JPanel();
		pnlUser.setLayout(null);
		pnlUser.setBackground(SystemColor.activeCaption);
		pnlUser.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder(""),
				BorderFactory.createEmptyBorder(1, 1, 1, 1)));

		lblUser = new JLabel("[在线用户列表]");
		lblUser.setBackground(SystemColor.text);
		lblUser.setFont(new Font("宋体", 0, 12));
		lblUser.setForeground(SystemColor.text);

		lstUser = new JList();
		lstUser.setFont(new Font("宋体", 0, 12));
		lstUser.setVisibleRowCount(17);
		lstUser.setFixedCellWidth(180);
		lstUser.setFixedCellHeight(18);

		spUser = new JScrollPane();
		spUser.setBackground(Color.cyan);
		spUser.setFont(new Font("宋体", 0, 12));
		spUser.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		spUser.getViewport().setView(lstUser);

		pnlUser.setBounds(50, 5, 300, 400);
		lblUser.setBounds(50, 10, 100, 30);
		spUser.setBounds(50, 35, 200, 360);

		pnlUser.add(lblUser);
		pnlUser.add(spUser);

		// 软件信息
		pnlAbout = new JPanel();
		pnlAbout.setLayout(null);
		pnlAbout.setBackground(SystemColor.activeCaption);
		pnlAbout.setFont(new Font("宋体", 0, 14));

		lblVersionNo = new JLabel("Version No. v1.51");
		lblVersionNo.setFont(new Font("宋体", 0, 14));
		lblVersionNo.setForeground(SystemColor.window);

		lblAbout = new JLabel();
		lblAbout.setFont(new Font("Consolas", 0, 14));
		lblAbout.setText("xxx……");
		lblAbout.setForeground(SystemColor.window);

		lblVersionNo.setBounds(110, 5, 100, 30);
		lblAbout.setBounds(110, 35, 400, 50);

		pnlAbout.add(lblVersionNo);
		pnlAbout.add(lblAbout);

		// 主标签面板
		tpServer = new JTabbedPane(JTabbedPane.TOP);
		tpServer.setBackground(Color.CYAN);
		tpServer.setFont(new Font("宋体", 0, 14));

		tpServer.add("服务器管理", pnlServer);
		tpServer.add("在线用户", pnlUser);
		tpServer.add("关于本软件", pnlAbout);

		this.getContentPane().add(tpServer);
		setVisible(true);
	}

	protected void closeServer() {
		this.dispose();
	}

	protected void saveLog() {
		try {
			FileOutputStream fileoutput = new FileOutputStream("log.txt", true);
			String temp = taLog.getText();
			fileoutput.write(temp.getBytes());
			fileoutput.close();
			JOptionPane.showMessageDialog(null, "记录保存在log.txt");
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void actionPerformed(ActionEvent evt) {
	}

	// 服务器窗口
	public static void main(String[] args) {
		new ServerFrame();
	}
}
