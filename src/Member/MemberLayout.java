package Member;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.DefaultComboBoxModel;
import common.MemberGrade;
import javax.swing.JPasswordField;

public class MemberLayout extends JPanel {
	protected JLabel lblID;
	protected JTextField tfID;
	protected JLabel lblPW;
	protected JPasswordField tfPW1;
	protected JTextField tfName;
	protected JTextField tfMobile2;
	protected JTextField tfEmail1;
	protected JTextField tfAddress;
	protected JTextField tfBirthSolar;
	protected JLabel lblGrade;
	protected JComboBox<MemberGrade> cbGrade;
	protected JPasswordField tfPW2;
	protected JTextField tfMobile3;
	protected JTextField tfEmail2;
	protected JTextField tfBirthLunar;
	protected JLabel lblPic;
	protected JButton btnDuplicate;
	protected JCheckBox chckbxIsSame;
	protected JComboBox<String> cbMobile1;
	protected JButton btnAddress;
	protected JCheckBox chckbxIsLunar;
	protected JButton btnCalendar;
	protected JButton btnAdd;
	protected JButton btnDelete;
	protected JButton btnUpdate;
	protected JButton btnClose;

	/**
	 * Create the panel.
	 */
	public MemberLayout() {
		setLayout(null);
		
		lblPic = new JLabel("사진(195x250)");
		lblPic.setBackground(Color.WHITE);
		lblPic.setOpaque(true);
		lblPic.setBounds(12, 10, 195, 250);
		lblPic.setIcon(new javax.swing.ImageIcon(MemberLayout.class.getResource("/images/ex_sample.png")));
		add(lblPic);
		
		lblID = new JLabel("ID:");
		lblID.setBounds(229, 13, 57, 15);
		add(lblID);
		
		tfID = new JTextField();
		tfID.setBounds(305, 10, 116, 21);
		add(tfID);
		tfID.setColumns(10);
		
		lblPW = new JLabel("PW:");
		lblPW.setBounds(229, 46, 57, 15);
		add(lblPW);
		
		tfPW1 = new JPasswordField();
		tfPW1.setColumns(10);
		tfPW1.setBounds(305, 43, 116, 21);
		add(tfPW1);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(229, 79, 57, 15);
		add(lblName);
		
		tfName = new JTextField();
		tfName.setColumns(10);
		tfName.setBounds(305, 76, 116, 21);
		add(tfName);
		
		JLabel lblMobile = new JLabel("Mobile");
		lblMobile.setBounds(229, 112, 57, 15);
		add(lblMobile);
		
		tfMobile2 = new JTextField();
		tfMobile2.setColumns(10);
		tfMobile2.setBounds(376, 109, 80, 21);
		add(tfMobile2);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(229, 145, 57, 15);
		add(lblEmail);
		
		tfEmail1 = new JTextField();
		tfEmail1.setColumns(10);
		tfEmail1.setBounds(305, 142, 116, 21);
		add(tfEmail1);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setBounds(229, 178, 57, 15);
		add(lblAddress);
		
		tfAddress = new JTextField();
		tfAddress.setColumns(10);
		tfAddress.setBounds(305, 175, 236, 21);
		add(tfAddress);
		
		JLabel lblBirth = new JLabel("Birth");
		lblBirth.setBounds(229, 211, 57, 15);
		add(lblBirth);
		
		tfBirthSolar = new JTextField();
		tfBirthSolar.setColumns(10);
		tfBirthSolar.setBounds(357, 208, 91, 21);
		add(tfBirthSolar);
		
		lblGrade = new JLabel("Grade");
		lblGrade.setBounds(229, 244, 57, 15);
		add(lblGrade);
		
		cbGrade = new JComboBox<>();
		cbGrade.setBounds(305, 241, 116, 21);
		add(cbGrade);
		
		tfPW2 = new JPasswordField();
		tfPW2.setBounds(433, 43, 116, 21);
		add(tfPW2);
		tfPW2.setColumns(10);
		
		cbMobile1 = new JComboBox<>();
		cbMobile1.setEditable(true);
		cbMobile1.setBounds(305, 108, 59, 23);
		add(cbMobile1);
		
		tfMobile3 = new JTextField();
		tfMobile3.setBounds(468, 109, 80, 21);
		add(tfMobile3);
		tfMobile3.setColumns(10);
		
		tfEmail2 = new JTextField();
		tfEmail2.setBounds(449, 142, 116, 21);
		add(tfEmail2);
		tfEmail2.setColumns(10);
		
		JLabel lblAt = new JLabel("@");
		lblAt.setBounds(429, 145, 19, 15);
		add(lblAt);
		
		btnDuplicate = new JButton("중복확인");
		btnDuplicate.setBounds(433, 9, 97, 23);
		add(btnDuplicate);
		
		btnAddress = new JButton("주소검색...");
		btnAddress.setBounds(555, 174, 102, 23);
		add(btnAddress);
		
		chckbxIsLunar = new JCheckBox("음력");
		chckbxIsLunar.setBounds(301, 207, 57, 23);
		add(chckbxIsLunar);
		
		btnCalendar = new JButton("달력...");
		btnCalendar.setBounds(462, 207, 91, 23);
		add(btnCalendar);
		
		tfBirthLunar = new JTextField();
		tfBirthLunar.setColumns(10);
		tfBirthLunar.setBounds(566, 208, 91, 21);
		add(tfBirthLunar);
		
		chckbxIsSame = new JCheckBox("일치");
		chckbxIsSame.setEnabled(false);
		chckbxIsSame.setFocusable(false);
		chckbxIsSame.setBounds(553, 42, 115, 23);
		add(chckbxIsSame);
		
		btnAdd = new JButton("회원 가입");
		btnAdd.setBounds(291, 280, 97, 23);
		add(btnAdd);
		
		btnDelete = new JButton("회원 탈퇴");
		btnDelete.setBounds(291, 280, 97, 23);
		add(btnDelete);
		
		btnUpdate = new JButton("회원 변경");
		btnUpdate.setBounds(291, 280, 97, 23);
		add(btnUpdate);
		
		btnClose = new JButton("창 닫기");
		btnClose.setBounds(291, 280, 97, 23);
		add(btnClose);

	}
}
