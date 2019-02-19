import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JEditorPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.Date;
import java.awt.event.ActionEvent;

public class CreateUser extends JFrame {
	
	private JPanel contentPane;
	private JTextField username;
	private JPasswordField password;
	private JPasswordField passwordReType;
	private JTextField NameSurname;
	private JTextField SchoolGraduated;
	private JTextField birthdate;
	private static String uName,pass,passRe,nameSurname,schoolGraduatedFrom,relationshipstatus;
	private static String birthDate;
	

	

	/**
	 * Create the frame.
	 */
	public CreateUser() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 359, 451);
		contentPane = new JPanel();
		contentPane.setToolTipText("Single");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel faceIcon = new JLabel("");
		faceIcon.setBounds(21, 0, 323, 107);
		Image img = new ImageIcon(this.getClass().getResource("/Facebooklogo1.png")).getImage();
		faceIcon.setIcon(new ImageIcon(img));
		contentPane.add(faceIcon);
		
		JLabel lblCreateUser = new JLabel("Create User");
		lblCreateUser.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCreateUser.setForeground(SystemColor.desktop);
		lblCreateUser.setBounds(113, 118, 115, 28);
		contentPane.add(lblCreateUser);
		
		JLabel userName = new JLabel("Username");
		userName.setBounds(21, 165, 75, 14);
		contentPane.add(userName);
		
		username = new JTextField();
		username.setBounds(161, 162, 125, 20);
		contentPane.add(username);
		username.setColumns(10);
		
		JLabel passWord = new JLabel("Password");
		passWord.setBounds(21, 193, 75, 14);
		contentPane.add(passWord);
		
		password = new JPasswordField();
		password.setBounds(161, 190, 125, 20);
		contentPane.add(password);
		
		JLabel lblPasswordretype = new JLabel("Password(re-type)");
		lblPasswordretype.setBounds(21, 220, 130, 14);
		contentPane.add(lblPasswordretype);
		
		passwordReType = new JPasswordField();
		passwordReType.setBounds(161, 217, 125, 20);
		contentPane.add(passwordReType);
		
		JLabel lblNameSurname = new JLabel("Name Surname");
		lblNameSurname.setBounds(21, 245, 88, 14);
		contentPane.add(lblNameSurname);
		
		NameSurname = new JTextField();
		NameSurname.setBounds(161, 242, 125, 20);
		contentPane.add(NameSurname);
		NameSurname.setColumns(10);
		
		JLabel lblSchoolGraduated = new JLabel("School Graduated");
		lblSchoolGraduated.setBounds(21, 270, 103, 14);
		contentPane.add(lblSchoolGraduated);
		
		SchoolGraduated = new JTextField();
		SchoolGraduated.setBounds(161, 267, 125, 20);
		contentPane.add(SchoolGraduated);
		SchoolGraduated.setColumns(10);
		
		JLabel lblBirthDate = new JLabel("Birth Date");
		lblBirthDate.setBounds(21, 296, 76, 14);
		contentPane.add(lblBirthDate);
		
		birthdate = new JTextField();
		birthdate.setBounds(161, 298, 125, 20);
		contentPane.add(birthdate);
		birthdate.setColumns(10);
		
		JLabel lblRelationshipStatus = new JLabel("Relationship Status");
		lblRelationshipStatus.setBounds(21, 324, 130, 14);
		contentPane.add(lblRelationshipStatus);
		
		
		String[] relationshipTypes = {"Single","In a relationship", "Divorced", "Complicated"};
		JComboBox<Object> relationShipStatus = new JComboBox<Object>(relationshipTypes);
		relationShipStatus.setToolTipText("");
		relationShipStatus.setBounds(161, 321, 125, 20);
		contentPane.add(relationShipStatus);
		
		JButton btnCreate = new JButton("Create");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				uName = username.getText();
				relationshipstatus = relationShipStatus.getSelectedItem().toString();
				birthDate = birthdate.getText();
				schoolGraduatedFrom = SchoolGraduated.getText();
				nameSurname = NameSurname.getText();
				passRe = passwordReType.getText();
				pass = password.getText();
				if(!(uName.isEmpty()) && !(relationshipstatus.isEmpty()) && !(birthDate.isEmpty()) && !(schoolGraduatedFrom.isEmpty()) && !(nameSurname.isEmpty()) && !(pass.isEmpty()) && !(passRe.isEmpty())){
					if(pass.equals(passRe)){
						try {
							UserCollection.addNewUser(nameSurname, uName, pass, birthDate, schoolGraduatedFrom, relationshipstatus);
							JOptionPane.showMessageDialog(null, "New user " + uName + " has been added to the system succesfully.");
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}else{
						JOptionPane.showMessageDialog(null, "Passwords do not match.Please try again.");
					}
				}else{
					JOptionPane.showMessageDialog(null, "Please fill in all the blanks.");
				}
			}
		});
		btnCreate.setBounds(113, 366, 115, 23);
		contentPane.add(btnCreate);
	}
}
