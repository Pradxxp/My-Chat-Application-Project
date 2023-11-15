package CHATAPP;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import java.util.*;
import java.text.*;
import java.net.*;
import java.io.*;






public class Client  implements ActionListener{
	static JFrame frame = new JFrame();
	static DataOutputStream dout;
	static Box vertical = Box.createVerticalBox();
	static JPanel p1 = new JPanel();
	static JPanel jp1 = new JPanel();
	Client(){ 
		frame.setLayout(null);
		frame.setTitle("Client");
		frame.setSize(400,800);
		frame.setLocation(800,0);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//this.getContentPane().setBackground(new Color(135, 26, 179));
		frame.getContentPane().setBackground(new Color(243, 222, 252));
		
		
		p1.setBackground(new Color(75, 4, 104 ));
		p1.setBounds(0,0,400,70);
		p1.setLayout(null);
		
		ImageIcon icon1 = new ImageIcon("C:\\Users\\Pradeep\\Downloads\\woman.png");
		Image icon2 = icon1.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
		ImageIcon icon3 = new ImageIcon(icon2);
	    JLabel pfp = new JLabel(icon3);
	    
	    ImageIcon icon4 = new ImageIcon("C:\\Users\\Pradeep\\Downloads\\close.png");
		Image icon5 = icon4.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
		ImageIcon icon6 = new ImageIcon(icon5);
		JLabel closeinfo = new JLabel(icon6);
		closeinfo.setBounds(275,73,20,20);
		
	    JLabel userinfo = new JLabel();
	    userinfo.setBackground(new Color(136, 9, 189));
	    userinfo.setBounds(0,0,300,400);
	    userinfo.setBorder(BorderFactory.createLineBorder(Color.white,3));
	    userinfo.setOpaque(true);
	    
		jp1.setBounds(5,75,375,640);
		jp1.setBackground(Color.white);
		
	    JLayeredPane layeredPane = new JLayeredPane();
	    JLayeredPane layeredPane2 = new JLayeredPane();
	    layeredPane.setBounds(0,0,400,800);
	    layeredPane2.setBounds(0,0,400,800);
	    layeredPane2.setBackground(new Color(231, 187, 249));
	    
	    layeredPane.add(userinfo, Integer.valueOf(1));
	    layeredPane.add(closeinfo,Integer.valueOf(2));
	    layeredPane2.add(jp1,Integer.valueOf(0));
	    
	    layeredPane.setVisible(true);
	    layeredPane.setVisible(false);
	    
		pfp.setBounds(5,10,50,50);
		pfp.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				layeredPane.setVisible(true);
				
				closeinfo.addMouseListener(new MouseAdapter(){
					public void mouseClicked(MouseEvent e) {
						layeredPane.setVisible(false);
						
					}
				});
			}
		});
		
		JLabel name = new JLabel("Person2");
		name.setForeground((Color.white));
		name.setBounds(70,15,100,20);
		name.setFont(new Font("Consolas",Font.BOLD,20));
		
		JLabel status = new JLabel("Online");
		status.setForeground((Color.white));
		status.setBounds(70,35,100,20);
		status.setFont(new Font("Consolas",Font.PLAIN,15));
		
		JTextField tf = new JTextField();
		tf.setBounds(5,720,310,40);
		tf.setFont(new Font("Consolas",Font.PLAIN,15));
		tf.setCaretColor(new Color(75, 4, 104));
		//tf.setText("Message");
		
		
		
		ImageIcon icon7 = new ImageIcon("C:\\Users\\Pradeep\\Downloads\\send.png");
		Image icon8 = icon7.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
		ImageIcon icon9 = new ImageIcon(icon8);
		JLabel send = new JLabel(icon9);
		send.setBounds(330,723,30,30);
		send.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				try {
				String out = tf.getText();
			    JPanel jpout = formatLabel(out);
				jp1.setLayout(new BorderLayout());
				
				JPanel right = new JPanel(new BorderLayout());
				right.add(jpout,BorderLayout.LINE_END);
				
				vertical.add(right);
				
				vertical.add(Box.createVerticalStrut(15));
				jp1.add(vertical,BorderLayout.PAGE_START);
				dout.writeUTF(out);
				tf.setText("");
				frame.repaint();
				frame.invalidate();
				frame.validate();
				}
				catch(Exception ex) {
					
				}
			}
			
		});
		
		
		
		
		layeredPane2.add(tf,Integer.valueOf(0));
		layeredPane2.add(send,Integer.valueOf(0));
		p1.add(name);
		p1.add(pfp);
		p1.add(status);
		frame.add(p1);
		
		frame.add(layeredPane);
		frame.add(layeredPane2);
		frame.add(tf);
		
		frame.setVisible(true);
		
		
	}
	public void actionPerformed(ActionEvent e) {
		
		
	}
	public static JPanel formatLabel(String out) {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		//JLabel output = new JLabel(out);
		JLabel output = new JLabel("<html><p style=\"width:150px\">"+out+"</p></html>");
		output.setFont(new Font("Consolas",Font.BOLD,15));
		output.setForeground(Color.white);
		output.setBackground(new Color(75, 4, 104 ));
		output.setOpaque(true);
		
		output.setBorder(new EmptyBorder(15,15,15,50));
		panel.add(output);
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		JLabel time  = new JLabel();
		time.setText(sdf.format(cal.getTime()));
		panel.add(time);
		
		return panel;
	}
	public static void main(String[] args) {
		new Client();
		try {
			Socket s = new Socket("127.0.0.1",6001);
			DataInputStream din = new DataInputStream(s.getInputStream());
			dout = new DataOutputStream(s.getOutputStream());
			while(true) {
				p1.setLayout(new BorderLayout());
				
				String msg = din.readUTF();
				JPanel jpin = formatLabel(msg);
				JPanel left = new JPanel(new BorderLayout());
				left.add(jpin,BorderLayout.LINE_START);
				vertical.add(left);
				vertical.add(Box.createVerticalStrut(15));
				jp1.add(vertical,BorderLayout.PAGE_START);
				
				frame.validate();
				
				
			}
			
		}
		catch(Exception ee){
			
		}
	}
}




