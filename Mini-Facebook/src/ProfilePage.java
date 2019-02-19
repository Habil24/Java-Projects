import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.SystemColor;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import java.awt.Label;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class ProfilePage extends JFrame {
	SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
	Date dateOfBirth = new Date();
	Date lastLoginDate = new Date();
	private JPanel contentPane;
	private JTextField SearchField;
	private static JList<String> normalBlockedFriendList;
	private static User loggedInUser;
	private static String selectedFromList;
	private static DefaultListModel<String> normalBlockedList;
	public static AddPost adpost;

	/**
	 * Create the frame.
	 */
	public ProfilePage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 692, 702);
		contentPane = new JPanel();
		contentPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		JLabel UpperPart = new JLabel("");
		UpperPart.setBackground(SystemColor.textHighlight);
		UpperPart.setBounds(0, 0, 676, 39);
		UpperPart.setOpaque(true);
		contentPane.add(UpperPart);
		
		JButton LogOutButton = new JButton("Logout\r\n");
		LogOutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loggedInUser.setSignInFalse();
				Main.userProfile.setVisible(false);
			}
		});
		LogOutButton.setBackground(SystemColor.textHighlight);
		LogOutButton.setBounds(577, 11, 89, 23);
		UpperPart.add(LogOutButton);
		
		JButton CreatePost = new JButton("Create Post");
		CreatePost.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 adpost = new AddPost();
				adpost.setVisible(true);
			}
		});
		UpperPart.add(CreatePost);
		CreatePost.setBackground(SystemColor.textHighlight);
		CreatePost.setFont(new Font("Tahoma", Font.BOLD, 11));
		CreatePost.setBounds(451, 11, 118, 23);
		
		JLabel SearchLabel = new JLabel("Search Friends");
		UpperPart.add(SearchLabel);
		SearchLabel.setForeground(Color.WHITE);
		SearchLabel.setBounds(108, 4, 101, 35);
		
		SearchField = new JTextField();
		UpperPart.add(SearchField);
		SearchField.setBounds(196, 12, 196, 20);
		SearchField.setColumns(10);
		
		JLabel iconLabel = new JLabel("");
		iconLabel.setBounds(0, 39, 154, 141);
		Image img = new ImageIcon(this.getClass().getResource("/personicon8.png")).getImage();
		iconLabel.setIcon(new ImageIcon(img));
		contentPane.add(iconLabel);
		getLoggedInUser();
		JLabel UserNameLabel = new JLabel(loggedInUser.getName());
		UserNameLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		UserNameLabel.setBounds(196, 160, 173, 39);
		contentPane.add(UserNameLabel);
		
		Border border = BorderFactory.createLineBorder(Color.GRAY, 2);
		
		JLabel UserInformation = new JLabel("Information\r\n");
		UserInformation.setVerticalAlignment(SwingConstants.TOP);
		UserInformation.setBounds(10, 210, 216, 180);
		UserInformation.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		contentPane.add(UserInformation);
		
		JLabel lblDateOfBirth = new JLabel("Date Of Birth");
		lblDateOfBirth.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDateOfBirth.setBounds(20, 230, 122, 14);
		contentPane.add(lblDateOfBirth);
		
		JLabel userDateOfBirth = new JLabel(dateFormat.format(loggedInUser.getDateOfBirth()));
		userDateOfBirth.setBounds(20, 245, 91, 14);
		contentPane.add(userDateOfBirth);
		
		JLabel lblSchoolGraduated = new JLabel("School Graduated");
		lblSchoolGraduated.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSchoolGraduated.setBounds(20, 263, 160, 14);
		contentPane.add(lblSchoolGraduated);
		
		JLabel UserGraduatedSchool = new JLabel(loggedInUser.getGraduatedSchool());
		UserGraduatedSchool.setBounds(20, 281, 128, 14);
		contentPane.add(UserGraduatedSchool);
		
		
		JLabel UserRelationshipStatus = new JLabel("Relationship Status");
		UserRelationshipStatus.setFont(new Font("Tahoma", Font.BOLD, 12));
		UserRelationshipStatus.setBounds(20, 300, 128, 14);
		contentPane.add(UserRelationshipStatus);
		
		String[] relationshipTypes = {"Single","In a relationship", "Divorced", "Complicated"};
		JComboBox<Object> relationshipStatusComboBox = new JComboBox<Object>(relationshipTypes);
		relationshipStatusComboBox.setSelectedItem(loggedInUser.getRelationship_status());
		relationshipStatusComboBox.setBounds(20, 325, 128, 25);
		contentPane.add(relationshipStatusComboBox);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				loggedInUser.setRelationship_status(relationshipStatusComboBox.getSelectedItem());
				JOptionPane.showMessageDialog(null, "Relationship status has been updated to " + relationshipStatusComboBox.getSelectedItem());
			}
		});
		btnUpdate.setBounds(20, 361, 89, 23);
		contentPane.add(btnUpdate);
		
		JLabel lblFriends = new JLabel("Friends");
		lblFriends.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblFriends.setBounds(10, 395, 73, 25);
		contentPane.add(lblFriends);
		
		
		
		Border border2 = BorderFactory.createLineBorder(Color.gray, 2);;
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setSize(195, 183);
		scrollPane.setLocation(20, 439);
		contentPane.add(scrollPane);
		
		
		normalBlockedFriendList = new JList<String>();
		normalBlockedFriendList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(normalBlockedFriendList);
		normalBlockedFriendList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				 if (!event.getValueIsAdjusting()){
			            JList<?> source = (JList<?>)event.getSource();
			            selectedFromList = source.getSelectedValue().toString();
			            
			     }
			}
		});
		
		

		JRadioButton rdbtnNormal = new JRadioButton("Normal");
		rdbtnNormal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					normalBlockedList = NormalFriendList();
					normalBlockedFriendList.setModel(normalBlockedList);
				}catch(Exception e){
					
				}
				
				
			}
		});
		rdbtnNormal.setBounds(73, 397, 73, 23);
		contentPane.add(rdbtnNormal);
		
		JRadioButton rdbtnBlocked = new JRadioButton("Blocked\r\n");
		rdbtnBlocked.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					normalBlockedList = BlockedFriendList();
					normalBlockedFriendList.setModel(normalBlockedList);
				}catch(Exception e){
					
				}
				
			}
		});
		rdbtnBlocked.setBounds(148, 397, 73, 23);
		contentPane.add(rdbtnBlocked);
		
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnNormal);
		group.add(rdbtnBlocked);
		
		
		JButton btnRemoveSelectedUser = new JButton("Remove Selected User");
		btnRemoveSelectedUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(rdbtnNormal.isSelected()){
					for(int i=0;i<loggedInUser.getFriendList().size();i++){
						if(loggedInUser.getFriendList().get(i).getUserName().equals(selectedFromList)){
							loggedInUser.getFriendList().remove(i);
							JOptionPane.showMessageDialog(null, selectedFromList + " has been removed succesfully.");
						}
					}
				}else if(rdbtnBlocked.isSelected()){
					for(int i=0;i<loggedInUser.getBlockedUsers().size();i++){
						if(loggedInUser.getBlockedUsers().get(i).getUserName().equals(selectedFromList)){
							loggedInUser.getBlockedUsers().remove(i);
							JOptionPane.showMessageDialog(null, selectedFromList + " has been removed succesfully.");
						}
					}
				}
				
			}
		});
		btnRemoveSelectedUser.setBounds(22, 625, 193, 23);
		contentPane.add(btnRemoveSelectedUser);
		JLabel FriendInfoPart = new JLabel("");
		FriendInfoPart.setBounds(10, 427, 216, 234);
		contentPane.add(FriendInfoPart);
		FriendInfoPart.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
	}
	public static void getLoggedInUser(){
		for(int i=0;i<UserCollection.getUser_objects().size();i++){
			if(UserCollection.getUser_objects().get(i).isSignIn()==true){
				loggedInUser = UserCollection.getUser_objects().get(i);
			}
		}
	}
	
	public DefaultListModel<String> NormalFriendList(){
		DefaultListModel<String> DLM = new DefaultListModel<>();
		
		for(int i=0;i<loggedInUser.getFriendList().size();i++){
			DLM.addElement(loggedInUser.getFriendList().get(i).getUserName());
		}
		return DLM;
	}
	
	public DefaultListModel<String> BlockedFriendList(){
		DefaultListModel<String> DLM = new DefaultListModel<>();
		
		for(int i=0;i<loggedInUser.getBlockedUsers().size();i++){
			DLM.addElement(loggedInUser.getBlockedUsers().get(i).getUserName());
		}
		return DLM;
	}
	
	
}
