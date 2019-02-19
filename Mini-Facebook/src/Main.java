import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSplitPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.DefaultListModel;

import java.awt.Font;
import java.awt.HeadlessException;

import javax.swing.JFormattedTextField;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import java.awt.Scrollbar;
import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.SystemColor;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Main extends JFrame {

	private JPanel contentPane;
	private static JTextField usernameField;
	private static JPasswordField passwordField;
	private static String selected = "";
	private static String pass,username;
	public static ProfilePage userProfile;
	
	public static void appStart(String file){
		try {
			// Reading text file line by line 
			 Scanner scanner = new Scanner(new File(file));
			 while(scanner.hasNextLine()){
				 String line = scanner.nextLine();
				 line = line.trim();
				 // check if line is not empty
				 if(!line.isEmpty()){
					 // Split lines for arguments and execute needed functions
					 String[] array_as_line = line.split("	");
					 if(array_as_line[0].equals("ADDFRIEND")){
						 for(int i=0;i<UserCollection.getUser_objects().size();i++){
							 if(UserCollection.getUser_objects().get(i).getUserName().equals(array_as_line[1])){
								 UserCollection.getUser_objects().get(i).addFriend(array_as_line[2]);
							 }
						 }
						 for(int i=0;i<UserCollection.getUser_objects().size();i++){
							 if(UserCollection.getUser_objects().get(i).getUserName().equals(array_as_line[2])){
								 UserCollection.getUser_objects().get(i).addFriend(array_as_line[1]);
							 }
						 } 
					 }else if(array_as_line[0].equals("BLOCKFRIEND")){
						 for(int i=0;i<UserCollection.getUser_objects().size();i++){
							 if(UserCollection.getUser_objects().get(i).getUserName().equals(array_as_line[1])){
								 UserCollection.getUser_objects().get(i).blockUser(array_as_line[2]);
							 }
						 }
						 for(int i=0;i<UserCollection.getUser_objects().size();i++){
							 if(UserCollection.getUser_objects().get(i).getUserName().equals(array_as_line[2])){
								 UserCollection.getUser_objects().get(i).blockUser(array_as_line[1]);
							 }
						 }
					 }else if(array_as_line[0].equals("ADDPOST-TEXT")){
						 for(int i=0;i<UserCollection.getUser_objects().size();i++){
							 if(UserCollection.getUser_objects().get(i).getUserName().equals(array_as_line[1])){
								 UserCollection.getUser_objects().get(i).addTextPost(array_as_line[2],Double.parseDouble( array_as_line[3]), Double.parseDouble(array_as_line[4]), array_as_line[5]);
							 }
						 }
					 }else if(array_as_line[0].equals("ADDPOST-IMAGE")){
						 for(int i=0;i<UserCollection.getUser_objects().size();i++){
							 if(UserCollection.getUser_objects().get(i).getUserName().equals(array_as_line[1])){
								 UserCollection.getUser_objects().get(i).addImagePost(array_as_line[2], Double.parseDouble( array_as_line[3]), Double.parseDouble( array_as_line[4]), array_as_line[5], array_as_line[6], array_as_line[7]);
							 }
						 }
					 }else if(array_as_line[0].equals("ADDPOST-VIDEO")){
						 for(int i=0;i<UserCollection.getUser_objects().size();i++){
							 if(UserCollection.getUser_objects().get(i).getUserName().equals(array_as_line[1])){
								 UserCollection.getUser_objects().get(i).addImagePost(array_as_line[2], Double.parseDouble( array_as_line[3]), Double.parseDouble( array_as_line[4]), array_as_line[5], array_as_line[6], array_as_line[7]);
							 }
						 }
					 }
				 }
			 }
			 scanner.close();
		}catch (FileNotFoundException ex) {
			 System.out.println("No File Found!");
			 return;
		}	
	}

	/**
	 * Launch the application.
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
		// read text file into ArrayList
		UserCollection.fill_user_arraylist(args);
		// add users as objects
		UserCollection.addUserfromTxtFile();
		//System Initialization
		for(int i=0;i<UserCollection.getUser_objects().size();i++){
			UserCollection.getUser_objects().get(i).setSignInTrue();
		}
		appStart(args[1]);
		for(int i=0;i<UserCollection.getUser_objects().size();i++){
			UserCollection.getUser_objects().get(i).setSignInFalse();
		}
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 536, 389);
		contentPane = new JPanel();
		contentPane.setForeground(Color.WHITE);
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel userName = new JLabel("Username");
		userName.setBounds(309, 90, 63, 20);
		userName.setBackground(Color.WHITE);
		contentPane.add(userName);
		
		usernameField = new JTextField();
		usernameField.setBounds(382, 90, 128, 20);
		contentPane.add(usernameField);
		usernameField.setColumns(10);
		
		JLabel password = new JLabel("Password");
		password.setBounds(309, 121, 63, 14);
		contentPane.add(password);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(382, 118, 128, 20);
		contentPane.add(passwordField);
		
		JButton loginButton = new JButton("Login");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				username = usernameField.getText();
				pass = passwordField.getText();
				int check = 0;
				for(int i=0;i<UserCollection.getUser_objects().size();i++){
					if(UserCollection.getUser_objects().get(i).getUserName().equals(username) && UserCollection.getUser_objects().get(i).getPassword().equals(pass)){
						check++;
						UserCollection.getUser_objects().get(i).setSignInTrue();
					}
				}
				if(check>0){
					userProfile = new ProfilePage();
					userProfile.setVisible(true);
					
				}else{
					JOptionPane.showMessageDialog(null, "Wrong password and username combination.Please try again.");
				}
				
			}
		});
		loginButton.setBounds(382, 153, 132, 23);
		contentPane.add(loginButton);
		
		JLabel lblRegisteredUsers = new JLabel("Registered Users");
		lblRegisteredUsers.setBounds(10, 216, 128, 14);
		contentPane.add(lblRegisteredUsers);
		
		JButton removeUser = new JButton("Remove User");
		removeUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int n = JOptionPane.showConfirmDialog(null, "Are you sure that you want to remove " + selected + "?","Remove User",JOptionPane.YES_NO_OPTION);
				if(n == JOptionPane.YES_OPTION){
					for(int i = 0;i<UserCollection.getUser_objects().size();i++){
						if(UserCollection.getUser_objects().get(i).getUserName().equals(selected)){
							UserCollection.getUser_objects().remove(i);
						}
					}
					
				}	
			}
		});
		removeUser.setBounds(10, 322, 128, 23);
		contentPane.add(removeUser);
		
		JButton addUser = new JButton("New User\r\n");
		addUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateUser addUser = new CreateUser();
				addUser.setVisible(true);
			}
		});
		addUser.setBounds(386, 322, 128, 23);
		contentPane.add(addUser);
		
		JLabel facebookIcon = new JLabel("");
		facebookIcon.setHorizontalAlignment(SwingConstants.LEFT);
		facebookIcon.setBounds(10, 47, 306, 158);
		Image img = new ImageIcon(this.getClass().getResource("/Facebooklogo1.png")).getImage();
		facebookIcon.setIcon(new ImageIcon(img));
		contentPane.add(facebookIcon);
		DefaultListModel<String>usernameList = UserNameList();
		
		
		
		JLabel MainUpperPart = new JLabel("System");
		MainUpperPart.setFont(new Font("Tahoma", Font.PLAIN, 12));
		MainUpperPart.setBounds(0, 0, 520, 23);
		MainUpperPart.setOpaque(true);
		contentPane.add(MainUpperPart);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 241, 500, 64);
		contentPane.add(scrollPane);
		
		
		JList<String> Userlist = new JList<String>();
		scrollPane.setViewportView(Userlist);
		Userlist.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				 if (!event.getValueIsAdjusting()){
			            JList source = (JList)event.getSource();
			            selected = source.getSelectedValue().toString();
			            fillUsername(selected);
			            fillPassword(selected);
			        }
			}
		});
		
		Userlist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		Userlist.setBackground(Color.WHITE);
		Userlist.setVisibleRowCount(2);
		Userlist.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		Userlist.setModel(usernameList);
	}
	public DefaultListModel<String> UserNameList(){
		DefaultListModel<String> DLM = new DefaultListModel<>();
		
		for(int i=0;i<UserCollection.getUser_objects().size();i++){
			DLM.addElement(UserCollection.getUser_objects().get(i).getUserName());
		}
		return DLM;
	}
	
	public void fillUsername(String user) {
		usernameField.setText("");
		usernameField.setText(usernameField.getText()+user);
    }
	
	public void fillPassword(String user){
		passwordField.setText("");
		for(int i=0;i<UserCollection.getUser_objects().size();i++){
			if(UserCollection.getUser_objects().get(i).getUserName().equals(user)){
				passwordField.setText(passwordField.getText()+UserCollection.getUser_objects().get(i).getPassword());
			}
		}
	}
}
