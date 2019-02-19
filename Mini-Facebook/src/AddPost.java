import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JComboBox;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddPost extends JFrame {

	private JPanel contentPane;
	private JTextField postTextField;
	private JTextField latitudeField;
	private JTextField LongtitudeField;
	private static JLabel lblFilename;
	private static JTextField fileNameField;
	private static JLabel lblWidth;
	private static JTextField widthField;
	private static JLabel lblHeight;
	private static JTextField HeightField;
	private static JLabel lblDuration;
	private static JTextField DurationtextField;
	private static User loggedUser;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddPost frame = new AddPost();
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
	public AddPost() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPostType = new JLabel("Post Type");
		lblPostType.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPostType.setBounds(10, 21, 67, 20);
		contentPane.add(lblPostType);
		
		String[] postTypes = {"TextPost","ImagePost", "VideoPost"};
		JComboBox<Object> postTypeBox = new JComboBox<Object>(postTypes);
		postTypeBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(postTypeBox.getSelectedItem().toString().equals("TextPost")){
					lblFilename.hide();
					fileNameField.hide();
					lblWidth.hide();
					widthField.hide();
					lblHeight.hide();
					HeightField.hide();
					lblDuration.hide();
					DurationtextField.hide();
				}
				else if(postTypeBox.getSelectedItem().toString().equals("ImagePost")){
					lblFilename.setVisible(true);
					fileNameField.setVisible(true);
					lblWidth.setVisible(true);
					widthField.setVisible(true);
					lblHeight.setVisible(true);
					HeightField.setVisible(true);
					lblDuration.hide();
					DurationtextField.hide();
					
				}else if(postTypeBox.getSelectedItem().toString().equals("VideoPost")){
					lblWidth.hide();
					widthField.hide();
					lblHeight.hide();
					HeightField.hide();
					lblFilename.setVisible(true);
					fileNameField.setVisible(true);
					lblDuration.setVisible(true);
					DurationtextField.setVisible(true);
					
				}
			}
		});
		postTypeBox.setBounds(87, 22, 133, 20);
		contentPane.add(postTypeBox);
		
		JButton addPostButton = new JButton("Add Post");
		addPostButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(postTypeBox.getSelectedItem().toString().equals("TextPost")){
					String text = postTextField.getText();
					String latitude = latitudeField.getText();
					String longtitude = LongtitudeField.getText();
					getLoggedInUser();
					loggedUser.addTextPost(text, Double.parseDouble(longtitude), Double.parseDouble(latitude), ":");
					JOptionPane.showMessageDialog(null,"Text Post has been added succesfully.");
					ProfilePage.adpost.setVisible(false);
					
				}else if(postTypeBox.getSelectedItem().toString().equals("ImagePost")){
					String text = postTextField.getText();
					String latitude = latitudeField.getText();
					String longtitude = LongtitudeField.getText();
					String fileName = fileNameField.getText();
					String width = widthField.getText();
					String height = HeightField.getText();
					String res = width + "<x>" + height;
					getLoggedInUser();
					loggedUser.addImagePost(text, Double.parseDouble(longtitude), Double.parseDouble(latitude), ":", fileName, res);
					JOptionPane.showMessageDialog(null,"ImagePost Post has been added succesfully.");
					ProfilePage.adpost.setVisible(false);
				}else{
					String text = postTextField.getText();
					String latitude = latitudeField.getText();
					String longtitude = LongtitudeField.getText();
					String fileName = fileNameField.getText();
					String duration = DurationtextField.getText();
					getLoggedInUser();
					loggedUser.addVideoPost(text, Double.parseDouble(longtitude), Double.parseDouble(latitude), ":", fileName, Integer.parseInt(duration));
					JOptionPane.showMessageDialog(null,"VideoPost Post has been added succesfully.");
					ProfilePage.adpost.setVisible(false);
				}
			}
		});
		addPostButton.setBounds(313, 21, 100, 23);
		contentPane.add(addPostButton);
		
		JLabel lblText = new JLabel("Text:");
		lblText.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblText.setBounds(10, 65, 67, 14);
		contentPane.add(lblText);
		
		postTextField = new JTextField();
		postTextField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		postTextField.setBounds(87, 63, 326, 20);
		contentPane.add(postTextField);
		postTextField.setColumns(10);
		
		JLabel lblLatitude = new JLabel("Latitude:");
		lblLatitude.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblLatitude.setBounds(10, 100, 67, 14);
		contentPane.add(lblLatitude);
		
		latitudeField = new JTextField();
		latitudeField.setBounds(87, 98, 92, 20);
		contentPane.add(latitudeField);
		latitudeField.setColumns(10);
		
		JLabel lblLongtitude = new JLabel("Longtitude:");
		lblLongtitude.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblLongtitude.setBounds(189, 101, 67, 14);
		contentPane.add(lblLongtitude);
		
		LongtitudeField = new JTextField();
		LongtitudeField.setBounds(257, 98, 92, 20);
		contentPane.add(LongtitudeField);
		LongtitudeField.setColumns(10);
		
		lblFilename = new JLabel("FileName:");
		lblFilename.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblFilename.setBounds(10, 153, 67, 14);
		contentPane.add(lblFilename);
		
		
		fileNameField = new JTextField();
		fileNameField.setBounds(87, 151, 199, 20);
		contentPane.add(fileNameField);
		fileNameField.setColumns(10);
		
		lblWidth = new JLabel("Width:");
		lblWidth.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblWidth.setBounds(10, 199, 67, 14);
		contentPane.add(lblWidth);
		
		widthField = new JTextField();
		widthField.setBounds(87, 197, 78, 20);
		contentPane.add(widthField);
		widthField.setColumns(10);
		
		lblHeight = new JLabel("Height:");
		lblHeight.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblHeight.setBounds(175, 199, 46, 14);
		contentPane.add(lblHeight);
		
		HeightField = new JTextField();
		HeightField.setBounds(219, 197, 67, 20);
		contentPane.add(HeightField);
		HeightField.setColumns(10);
		
		lblDuration = new JLabel("Duration:");
		lblDuration.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDuration.setBounds(10, 185, 55, 14);
		contentPane.add(lblDuration);
		
		DurationtextField = new JTextField();
		DurationtextField.setBounds(87, 182, 86, 20);
		contentPane.add(DurationtextField);
		DurationtextField.setColumns(10);
		lblFilename.hide();
		fileNameField.hide();
		lblWidth.hide();
		widthField.hide();
		lblHeight.hide();
		HeightField.hide();
		lblDuration.hide();
		DurationtextField.hide();
			
	}
	public static void getLoggedInUser(){
		for(int i=0;i<UserCollection.getUser_objects().size();i++){
			if(UserCollection.getUser_objects().get(i).isSignIn()==true){
				loggedUser = UserCollection.getUser_objects().get(i);
			}
		}
	}
}
