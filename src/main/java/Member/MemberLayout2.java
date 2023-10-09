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
import java.awt.BorderLayout;
import javax.swing.JSplitPane;
import javax.swing.BoxLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Component;
import javax.swing.Box;

public class MemberLayout2 extends JPanel {
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
	
	protected JPanel panelPW;
	protected JPanel panelGrade;	
	private Component horizontalStrut;
	private Component horizontalStrut_1;
	private Component horizontalStrut_2;
	private Component horizontalStrut_3;
	private Component horizontalStrut_4;
	private Component horizontalStrut_5;
	private Component horizontalStrut_6;
	private Component horizontalStrut_7;

	/**
	 * Create the panel.
	 */
	public MemberLayout2() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panelButtons = new JPanel();
		add(panelButtons, BorderLayout.SOUTH);
		
		btnAdd = new JButton("회원 가입");
		panelButtons.add(btnAdd);
		
		btnClose = new JButton("창 닫기");
		panelButtons.add(btnClose);
		
		btnDelete = new JButton("회원 탈퇴");
		panelButtons.add(btnDelete);
		
		btnUpdate = new JButton("회원 변경");
		panelButtons.add(btnUpdate);
		
		JSplitPane splitPane = new JSplitPane();
		add(splitPane, BorderLayout.CENTER);
		
		lblPic = new JLabel("");
		lblPic.setToolTipText("사진(195*250)");
		lblPic.setMaximumSize(new Dimension(195, 250));
		lblPic.setMinimumSize(new Dimension(195, 250));
		lblPic.setOpaque(true);
		lblPic.setBackground(Color.WHITE);
		splitPane.setLeftComponent(lblPic);
		
		JPanel panelRight = new JPanel();
		splitPane.setRightComponent(panelRight);
		panelRight.setLayout(new BoxLayout(panelRight, BoxLayout.Y_AXIS));
		
		JPanel panelID = new JPanel();
		panelID.setMaximumSize(new Dimension(32767, 35));
		FlowLayout flowLayout = (FlowLayout) panelID.getLayout();
		flowLayout.setAlignment(FlowLayout.LEADING);
		panelRight.add(panelID);
		
		lblID = new JLabel("ID:");
		panelID.add(lblID);
		
		horizontalStrut = Box.createHorizontalStrut(20);
		horizontalStrut.setPreferredSize(new Dimension(36, 0));
		panelID.add(horizontalStrut);
		
		tfID = new JTextField();
		tfID.setColumns(10);
		panelID.add(tfID);
		
		btnDuplicate = new JButton("중복확인");
		panelID.add(btnDuplicate);
		
		panelPW = new JPanel();
		panelPW.setMaximumSize(new Dimension(32767, 35));
		FlowLayout flowLayout_1 = (FlowLayout) panelPW.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEADING);
		panelRight.add(panelPW);
		
		lblPW = new JLabel("PW:");
		panelPW.add(lblPW);
		
		horizontalStrut_1 = Box.createHorizontalStrut(20);
		horizontalStrut_1.setPreferredSize(new Dimension(27, 0));
		panelPW.add(horizontalStrut_1);
		
		tfPW1 = new JPasswordField();
		tfPW1.setColumns(10);
		panelPW.add(tfPW1);
		
		tfPW2 = new JPasswordField();
		tfPW2.setColumns(10);
		panelPW.add(tfPW2);
		
		chckbxIsSame = new JCheckBox("일치");
		chckbxIsSame.setFocusable(false);
		chckbxIsSame.setEnabled(false);
		panelPW.add(chckbxIsSame);
		
		JPanel panelName = new JPanel();
		panelName.setMaximumSize(new Dimension(32767, 35));
		FlowLayout flowLayout_2 = (FlowLayout) panelName.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEADING);
		panelRight.add(panelName);
		
		JLabel lblName = new JLabel("Name:");
		panelName.add(lblName);
		
		horizontalStrut_2 = Box.createHorizontalStrut(20);
		horizontalStrut_2.setPreferredSize(new Dimension(13, 0));
		panelName.add(horizontalStrut_2);
		
		tfName = new JTextField();
		tfName.setColumns(10);
		panelName.add(tfName);
		
		JPanel panelMobile = new JPanel();
		panelMobile.setMaximumSize(new Dimension(32767, 35));
		FlowLayout flowLayout_3 = (FlowLayout) panelMobile.getLayout();
		flowLayout_3.setAlignment(FlowLayout.LEADING);
		panelRight.add(panelMobile);
		
		JLabel lblMobile = new JLabel("Mobile:");
		panelMobile.add(lblMobile);
		
		horizontalStrut_3 = Box.createHorizontalStrut(20);
		horizontalStrut_3.setPreferredSize(new Dimension(9, 0));
		panelMobile.add(horizontalStrut_3);
		
		cbMobile1 = new JComboBox<String>();
		cbMobile1.setEditable(true);
		panelMobile.add(cbMobile1);
		
		tfMobile2 = new JTextField();
		tfMobile2.setColumns(4);
		panelMobile.add(tfMobile2);
		
		tfMobile3 = new JTextField();
		tfMobile3.setColumns(4);
		panelMobile.add(tfMobile3);
		
		JPanel panelEmail = new JPanel();
		panelEmail.setMaximumSize(new Dimension(32767, 35));
		FlowLayout flowLayout_4 = (FlowLayout) panelEmail.getLayout();
		flowLayout_4.setAlignment(FlowLayout.LEADING);
		panelRight.add(panelEmail);
		
		JLabel lblEmail = new JLabel("Email:");
		panelEmail.add(lblEmail);
		
		horizontalStrut_4 = Box.createHorizontalStrut(20);
		horizontalStrut_4.setPreferredSize(new Dimension(15, 0));
		panelEmail.add(horizontalStrut_4);
		
		tfEmail1 = new JTextField();
		tfEmail1.setColumns(10);
		panelEmail.add(tfEmail1);
		
		JLabel lblAt = new JLabel("@");
		panelEmail.add(lblAt);
		
		tfEmail2 = new JTextField();
		tfEmail2.setColumns(10);
		panelEmail.add(tfEmail2);
		
		JPanel panelAddress = new JPanel();
		panelAddress.setMaximumSize(new Dimension(32767, 35));
		FlowLayout flowLayout_5 = (FlowLayout) panelAddress.getLayout();
		flowLayout_5.setAlignment(FlowLayout.LEADING);
		panelRight.add(panelAddress);
		
		JLabel lblAddress = new JLabel("Address:");
		panelAddress.add(lblAddress);
		
		horizontalStrut_7 = Box.createHorizontalStrut(20);
		horizontalStrut_7.setPreferredSize(new Dimension(0, 0));
		panelAddress.add(horizontalStrut_7);
		
		tfAddress = new JTextField();
		tfAddress.setColumns(25);
		panelAddress.add(tfAddress);
		
		btnAddress = new JButton("주소검색...");
		panelAddress.add(btnAddress);
		
		JPanel panelBirth = new JPanel();
		panelBirth.setMaximumSize(new Dimension(32767, 35));
		FlowLayout flowLayout_6 = (FlowLayout) panelBirth.getLayout();
		flowLayout_6.setAlignment(FlowLayout.LEADING);
		panelRight.add(panelBirth);
		
		JLabel lblBirth = new JLabel("Birth:");
		panelBirth.add(lblBirth);
		
		horizontalStrut_5 = Box.createHorizontalStrut(20);
		horizontalStrut_5.setPreferredSize(new Dimension(18, 0));
		panelBirth.add(horizontalStrut_5);
		
		chckbxIsLunar = new JCheckBox("음력");
		panelBirth.add(chckbxIsLunar);
		
		tfBirthSolar = new JTextField();
		tfBirthSolar.setColumns(10);
		panelBirth.add(tfBirthSolar);
		
		btnCalendar = new JButton("달력...");
		panelBirth.add(btnCalendar);
		
		tfBirthLunar = new JTextField();
		tfBirthLunar.setColumns(10);
		panelBirth.add(tfBirthLunar);
		
		panelGrade = new JPanel();
		panelGrade.setMaximumSize(new Dimension(32767, 35));
		FlowLayout flowLayout_7 = (FlowLayout) panelGrade.getLayout();
		flowLayout_7.setAlignment(FlowLayout.LEADING);
		panelRight.add(panelGrade);
		
		lblGrade = new JLabel("Grade:");
		panelGrade.add(lblGrade);
		
		horizontalStrut_6 = Box.createHorizontalStrut(20);
		horizontalStrut_6.setPreferredSize(new Dimension(13, 0));
		panelGrade.add(horizontalStrut_6);
		
		cbGrade = new JComboBox<MemberGrade>();
		panelGrade.add(cbGrade);

	}
}
