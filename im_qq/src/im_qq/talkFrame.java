package im_qq;

import java.awt.BorderLayout;  
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;  
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;  
  
import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
  
 
public class talkFrame extends JFrame 
{  
  
	private static final long serialVersionUID = -2397593626990759111L;  
  
	 JScrollPane scrollPane;  // ����  
	 public static JTextPane text ;  
	 Box box = null; // ���������������  
	 public static JButton b_insert , b_remove , b_icon ; // ���밴ť;�����ť;����ͼƬ��ť  
	 public static JTextArea addText = null; // ���������  
	 JComboBox fontName, fontSize, fontStyle, fontColor,fontBackColor; // ��������;�ֺŴ�С;������ʽ;������ɫ;���ֱ�����ɫ  
	 ImageIcon flod,sendTxt,sendPic,chatLog;
	 JLabel Jlflod,JlsendTxt,JlsendPic,JlchatLog;
	 private StyledDocument doc = null; // ����������ʽ 
	 ImageIcon background;
	 
 public talkFrame() {  
	
	 
	 	super(FriendView.beTalkedTo+"");
	 	
	 	text = new JTextPane();  
	 	text.setEditable(false); // ����¼��  
	 	doc = text.getStyledDocument(); // ���JTextPane��Document  
	 	scrollPane = new JScrollPane(text);  
		 addText = new JTextArea(6,3);  
		 String[] str_name = { "����", "����", "Dialog", "Gulim" };  
		 String[] str_Size = { "12", "14", "18", "22", "30", "40" };  
		 String[] str_Style = { "����", "б��", "����", "��б��" };  
		 String[] str_Color = { "��ɫ", "��ɫ", "��ɫ", "��ɫ", "��ɫ" };  
		 String[] str_BackColor = { "��ɫ", "��ɫ", "����", "����", "����", "����" };  
		 fontName = new JComboBox(str_name); // ��������  
		 fontSize = new JComboBox(str_Size); // �ֺ�  
		 fontStyle = new JComboBox(str_Style); // ��ʽ  
		 fontColor = new JComboBox(str_Color); // ��ɫ  
		 fontBackColor = new JComboBox(str_BackColor); // ������ɫ  
		 b_insert = new JButton("����"); // ����
		 loginFrame btListener=new loginFrame();
		 btListener.setVisible(false);
		 b_insert.addActionListener(btListener);
		 
		 b_remove = new JButton("���"); // ���  
		 b_icon = new JButton("ͼƬ"); // ����ͼƬ  
		
	 
		  flod = new ImageIcon("flod.jpg");
		  sendTxt = new ImageIcon("sendTxt.jpg");
		  sendPic = new ImageIcon("sendPic.jpg");
		  chatLog = new ImageIcon("chatLog.jpg");
		  Jlflod = new JLabel(flod);
		  JlsendTxt = new JLabel(sendTxt);
		  JlsendPic = new JLabel(sendPic);
		  JlchatLog = new JLabel(chatLog);
		  
		  b_remove.addActionListener(new ActionListener()
	 { // ����¼�  
		 public void actionPerformed(ActionEvent e)
		 {  
			  addText.setText("");  
		 }  
    });  
		  
		    JlsendPic.addMouseListener(new MouseListener()
		   {
				
		    	public void mouseClicked(MouseEvent arg0) 
				{
					// TODO Auto-generated method stub
					// ���������
		    		JFileChooser f = new JFileChooser(); // �����ļ�  
		    		f.showOpenDialog(null);  
		    		insertIcon(f.getSelectedFile()); // ����ͼƬ
		          
				}

				public void mouseEntered(MouseEvent arg0)
				{
					// TODO Auto-generated method stub
					JlsendPic.setToolTipText("����ͼƬ");
				}

				public void mouseExited(MouseEvent arg0) 
				{
					// TODO Auto-generated method stub
					JlsendPic.setToolTipText("");
				}

				
				public void mousePressed(MouseEvent arg0) {
					// TODO Auto-generated method stub
					System.out.println("lala");
				}

				public void mouseReleased(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				} 
			 	private void insertIcon(File file)
			 	{  
			 		text.setCaretPosition(doc.getLength()); // ���ò���λ��  
			 		text.insertIcon(new ImageIcon(file.getPath())); // ����ͼƬ  
			 		insert(new FontAttrib()); // ���������Ի���  
			 	}  
			  
		        });  
		  
		  box = Box.createVerticalBox(); // ���ṹ  
		  Box box_1 = Box.createHorizontalBox(); // ��ṹ  
		  Box box_2 = Box.createHorizontalBox(); // ��ṹ  
		  box.add(box_1);  
		  box.add(Box.createVerticalStrut(2)); // ���еļ��  
		  box.add(box_2);  
		  box.setBorder(BorderFactory.createEmptyBorder(2, 0, 0, 0)); // 8���ı߾�  
		  // ��ʼ����������������� 
		 
		  
		  box_1.add(Box.createHorizontalStrut(0));  
		  box_1.add(new JLabel(flod));  
		  
		  box_1.add(Box.createHorizontalStrut(0));  
		  box_1.add(new JLabel(sendTxt));  
		   
		  box_1.add(Box.createHorizontalStrut(0));  
		  box_1.add(new JLabel(sendPic));
		  
		  box_1.add(Box.createHorizontalStrut(0));  
		  box_1.add(new JLabel(chatLog));  
		  
		  
		  box_2.add(addText);  
		  box_2.add(Box.createHorizontalStrut(8));  
		  box_2.add(b_insert);  
		  box_2.add(Box.createHorizontalStrut(8));  
		  box_2.add(b_remove);  
		  this.getRootPane().setDefaultButton(b_insert); // Ĭ�ϻس���ť  
		  this.getContentPane().add(scrollPane);  
		  this.getContentPane().add(box, BorderLayout.SOUTH);  
		  this.setSize(600, 450);  
		  this.setLocationRelativeTo(null);  
		  this.setVisible(true);  
		  addText.requestFocus();  
 }  
  


 	private void insert(FontAttrib attrib) {  
 		try { // �����ı�  
 			doc.insertString(doc.getLength(), attrib.getText() + "/n", attrib.getAttrSet());  
 		} catch (BadLocationException e) {  
 			e.printStackTrace();  
 		}  
 }  
  
 /** 
  * ��ȡ����Ҫ���������� 
  *  
  *
  */  
		 private FontAttrib getFontAttrib() {  
		  FontAttrib att = new FontAttrib();  
		  att.setText(addText.getText());  
		  att.setName((String) fontName.getSelectedItem());  
		  att.setSize(Integer.parseInt((String) fontSize.getSelectedItem()));  
		  String temp_style = (String) fontStyle.getSelectedItem();  
		  if (temp_style.equals("����")) {  
		   att.setStyle(FontAttrib.GENERAL);  
		  } else if (temp_style.equals("����")) {  
		   att.setStyle(FontAttrib.BOLD);  
		  } else if (temp_style.equals("б��")) {  
		   att.setStyle(FontAttrib.ITALIC);  
		  } else if (temp_style.equals("��б��")) {  
		   att.setStyle(FontAttrib.BOLD_ITALIC);  
		  }  
		  String temp_color = (String) fontColor.getSelectedItem();  
		  if (temp_color.equals("��ɫ")) {  
		   att.setColor(new Color(0, 0, 0));  
		  } else if (temp_color.equals("��ɫ")) {  
		   att.setColor(new Color(255, 0, 0));  
		  } else if (temp_color.equals("��ɫ")) {  
		   att.setColor(new Color(0, 0, 255));  
		  } else if (temp_color.equals("��ɫ")) {  
		   att.setColor(new Color(255, 255, 0));  
		  } else if (temp_color.equals("��ɫ")) {  
		   att.setColor(new Color(0, 255, 0));  
		  }  
		  String temp_backColor = (String) fontBackColor.getSelectedItem();  
		  if (!temp_backColor.equals("��ɫ")) {  
		   if (temp_backColor.equals("��ɫ")) {  
		    att.setBackColor(new Color(200, 200, 200));  
		   } else if (temp_backColor.equals("����")) {  
		    att.setBackColor(new Color(255, 200, 200));  
		   } else if (temp_backColor.equals("����")) {  
		    att.setBackColor(new Color(200, 200, 255));  
		   } else if (temp_backColor.equals("����")) {  
		    att.setBackColor(new Color(255, 255, 200));  
		   } else if (temp_backColor.equals("����")) {  
		    att.setBackColor(new Color(200, 255, 200));  
		   }  
		  }  
		  return att;  
		 }  
  
 public static void main(String args[]) 
 {  
  new talkFrame();  
 }  
  
	 private class FontAttrib {  
	  public static final int GENERAL = 0; // ����  
	  
	  public static final int BOLD = 1; // ����  
	  
	  public static final int ITALIC = 2; // б��  
	  
	  public static final int BOLD_ITALIC = 3; // ��б��  
	  
	  private SimpleAttributeSet attrSet = null; // ���Լ�  
	  
	  private String text = null, name = null; // Ҫ������ı�����������  
	  
	  private int style = 0, size = 0; // ��ʽ���ֺ�  
	  
	  private Color color = null, backColor = null; // ������ɫ�ͱ�����ɫ  
	  
	  /** 
	   * һ���յĹ��죨�ɵ�������ʹ�ã� 
	   */  
	  public FontAttrib() 
	  {  
	  }  
	  
	  /** 
	   * �������Լ� 
	   rn 
	   */  
	  public SimpleAttributeSet getAttrSet() {  
	   attrSet = new SimpleAttributeSet();  
	   if (name != null)  
	    StyleConstants.setFontFamily(attrSet, name);  
	   if (style == FontAttrib.GENERAL) {  
	    StyleConstants.setBold(attrSet, false);  
	    StyleConstants.setItalic(attrSet, false);  
	   } else if (style == FontAttrib.BOLD) {  
	    StyleConstants.setBold(attrSet, true);  
	    StyleConstants.setItalic(attrSet, false);  
	   } else if (style == FontAttrib.ITALIC) {  
	    StyleConstants.setBold(attrSet, false);  
	    StyleConstants.setItalic(attrSet, true);  
	   } else if (style == FontAttrib.BOLD_ITALIC) {  
	    StyleConstants.setBold(attrSet, true);  
	    StyleConstants.setItalic(attrSet, true);  
	   }  
	   StyleConstants.setFontSize(attrSet, size);  
	   if (color != null)  
	    StyleConstants.setForeground(attrSet, color);  
	   if (backColor != null)  
	    StyleConstants.setBackground(attrSet, backColor);  
	   return attrSet;  
	  }  
	  
	  public void setAttrSet(SimpleAttributeSet attrSet) {  
	   this.attrSet = attrSet;  
	  }  
	  
	 
	  
	  public String getText() {  
	   return text;  
	  }  
	  
	  public void setText(String text) {  
	   this.text = text;  
	  }  
	  
	  public Color getColor() {  
	   return color;  
	  }  
	  
	  public void setColor(Color color) {  
	   this.color = color;  
	  }  
	  
	  public Color getBackColor() {  
	   return backColor;  
	  }  
	  
	  public void setBackColor(Color backColor) {  
	   this.backColor = backColor;  
	  }  
	  
	  public String getName() {  
	   return name;  
	  }  
	  
	  public void setName(String name) {  
	   this.name = name;  
	  }  
	  
	  public int getSize() {  
	   return size;  
	  }  
	  
	  public void setSize(int size) {  
	   this.size = size;  
	  }  
	  
	  public int getStyle() {  
	   return style;  
	  }  
	  
	  public void setStyle(int style) {  
	   this.style = style;  
	  }  
	 }


}


