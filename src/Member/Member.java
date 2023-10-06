package Member;

import java.awt.AWTKeyStroke;
import java.awt.Image;
import java.awt.KeyboardFocusManager;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import common.DateUtil;
import common.MemberGrade;
import common.WinCalendar;
import common.WinDoroSearch;

public class Member extends MemberLayout2 {
	public static final String imgFilePath = "D:\\FileIO\\bookmember\\";
	SimpleDateFormat format;

	Member(Object[] row) {
		this();
		setMember(row);
	}

	public Member() {
		traversalSetting();
		format = new SimpleDateFormat("yyyy-MM-dd");

		cbMobile1.setEditable(false);

		cbMobile1.setModel(new DefaultComboBoxModel<String>(new String[] { "010", "011", "016", "017", "018", "019" }));
		cbGrade.setModel(new DefaultComboBoxModel<MemberGrade>(MemberGrade.values()));

		// 아이디 중복확인 체크는 WinMemberAdd에 있음
		// 라벨에 사진 넣는 코드는 WinMember, 왜냐면 판넬이 아니라 윈도우에서 열어야 하기 때문

		tfBirthLunar.setEnabled(false);

		btnAdd.setVisible(false);
		btnUpdate.setVisible(false);
		btnDelete.setVisible(false);
		btnClose.setVisible(false);

		chckbxIsLunar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String birthSolar = tfBirthSolar.getText().replace("-", "");
				String birthLunar = tfBirthLunar.getText().replace("-", "");

				if (chckbxIsLunar.isSelected()) {
					tfBirthSolar.setEnabled(false);
					tfBirthLunar.setEnabled(true);
					tfBirthSolar.setText("");
					birthLunar = DateUtil.toLunar(birthSolar).get("day").toString();
					birthLunar = birthLunar.substring(0, 4) + "-" + birthLunar.substring(4, 6) + "-"
							+ birthLunar.substring(6, 8);
					tfBirthLunar.setText(birthLunar);
				} else {
					tfBirthSolar.setEnabled(true);
					tfBirthLunar.setEnabled(false);
					birthSolar = DateUtil.toSolar(birthLunar, 0);
					birthSolar = birthSolar.substring(0, 4) + "-" + birthSolar.substring(4, 6) + "-"
							+ birthSolar.substring(6, 8);
					tfBirthSolar.setText(birthSolar);
					tfBirthLunar.setText("");
				}
			}
		});

		tfPW1.setInputVerifier(new InputVerifier() {

			@Override
			public boolean verify(JComponent input) {
				// TODO Auto-generated method stub
				if (tfPW1.getPassword().length >= 8) {
					tfPW2.requestFocus();
					return true;
				} else {
					JOptionPane.showMessageDialog(Member.this, "비밀번호는 8글자 이상이어야 합니다");
					return false;
				}
			}

		});

		tfPW2.setInputVerifier(new InputVerifier() {

			@Override
			public boolean verify(JComponent input) {
				// TODO Auto-generated method stub
				return isPWValid();
			}

		});

		tfName.setInputVerifier(new InputVerifier() {

			@Override
			public boolean verify(JComponent input) {
				// TODO Auto-generated method stub
				if (tfName.getText().length() < 2) {
					JOptionPane.showMessageDialog(Member.this, "이름란은 필수 입력 항목입니다");
					return false;
				} else {
					return true;
				}
			}

		});

		tfMobile2.setInputVerifier(new InputVerifier() {

			@Override
			public boolean verify(JComponent input) {
				// TODO Auto-generated method stub
				if (tfMobile2.getText().length() < 3 || tfMobile2.getText().length() > 4) {
					JOptionPane.showMessageDialog(Member.this, "숫자 세글자 또는 네글자를 입력해주세요");
					return false;
				} else {
					return true;
				}
			}

		});

		tfMobile3.setInputVerifier(new InputVerifier() {

			@Override
			public boolean verify(JComponent input) {
				// TODO Auto-generated method stub
				if (tfMobile3.getText().length() != 4) {
					JOptionPane.showMessageDialog(Member.this, "숫자 네글자를 입력해 주세요");
					return false;
				} else {
					return true;
				}
			}

		});

		btnCalendar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				WinCalendar dlg = new WinCalendar();
				dlg.setModal(true);
				dlg.setVisible(true);
				String date = dlg.getDate();
				if (chckbxIsLunar.isSelected())
					tfBirthLunar.setText(date);
				else
					tfBirthSolar.setText(date);
			}

		});

		btnAddress.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				WinDoroSearch winDoroSearch = new WinDoroSearch();
				winDoroSearch.setModal(true);
				winDoroSearch.setVisible(true);
				tfAddress.setText(winDoroSearch.getAddress());
				tfAddress.requestFocus();
			}

		});

	}

	private boolean isPWValid() {
		boolean result = false;
		String password1 = String.valueOf(tfPW1.getPassword());
		String password2 = String.valueOf(tfPW2.getPassword());

		if (password1.equals(password2)) {
			chckbxIsSame.setSelected(true);
			return !result;
		} else {
			JOptionPane.showMessageDialog(this, "비밀번호가 동일하지 않습니다");
			return result;
		}

	}

	private void traversalSetting() {
		/***
		 * 엔터로 component traverse
		 */
		final Set<AWTKeyStroke> forwardKeys = this.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS);
		final Set<AWTKeyStroke> newForwardKeys = new HashSet<AWTKeyStroke>(forwardKeys);
		newForwardKeys.add(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0));
		this.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, newForwardKeys);

	}

	public void setEnableAll(boolean b) {
		tfID.setEnabled(b);
		tfPW1.setEnabled(b);
		tfName.setEnabled(b);
		tfMobile2.setEnabled(b);
		tfEmail1.setEnabled(b);
		tfAddress.setEnabled(b);
		tfBirthSolar.setEnabled(b);
		cbGrade.setEnabled(b);
		tfPW2.setEnabled(b);
		tfMobile3.setEnabled(b);
		tfEmail2.setEnabled(b);
		tfBirthLunar.setEnabled(b);
		// lblPic.setEnabled(b);
		btnDuplicate.setEnabled(b);
		chckbxIsSame.setEnabled(b);
		cbMobile1.setEnabled(b);
		btnAddress.setEnabled(b);
		chckbxIsLunar.setEnabled(b);
		btnCalendar.setEnabled(b);
	}

	protected void clearAll() {
		tfID.setText("");
		tfPW1.setText("");
		tfName.setText("");
		tfMobile2.setText("");
		tfEmail1.setText("");
		tfAddress.setText("");
		tfBirthSolar.setText("");
		cbGrade.setSelectedIndex(0);
		tfPW2.setText("");
		tfMobile3.setText("");
		tfEmail2.setText("");
		tfBirthLunar.setText("");
		lblPic.setText("");
		lblPic.setIcon(null);
		chckbxIsSame.setSelected(false);
		cbMobile1.setSelectedIndex(0);
		chckbxIsLunar.setSelected(false);
	}

	public void setVisiblePW(boolean b) {
		this.panelPW.setVisible(b);
	}

	public void setVisibleGrade(boolean b) {
		this.panelGrade.setVisible(b);
	}

	public void setVisibleUnusedBirth(boolean b) {
		if (b) {
			if (chckbxIsLunar.isSelected()) {
				this.tfBirthSolar.setVisible(false);
				this.tfBirthLunar.setVisible(true);
			} else {
				this.tfBirthSolar.setVisible(true);
				this.tfBirthLunar.setVisible(false);
			}
		} else {
			this.tfBirthSolar.setVisible(true);
			this.tfBirthLunar.setVisible(true);
		}
	}

	public void setVisibleInputButtons(boolean b) {
		this.btnDuplicate.setVisible(b);
		this.btnCalendar.setVisible(b);
		this.btnAddress.setVisible(b);
	}

	public Object[] getAddData() throws ParseException {
		Object[] result = new Object[11];

		result[0] = tfID.getText();
		result[1] = String.valueOf(tfPW2.getPassword());
		result[2] = tfName.getText();
		result[3] = tfMobile2.getText().equals("") & tfMobile3.getText().equals("") ? null
				: cbMobile1.getSelectedItem().toString() + tfMobile2.getText() + tfMobile3.getText();
		result[4] = tfEmail1.getText().equals("") & tfEmail2.getText().equals("") ? null
				: tfEmail1.getText() + "@" + tfEmail2.getText();
		result[5] = tfAddress.getText().equals("") ? null : tfAddress.getText();

		String birth = chckbxIsLunar.isSelected() ? tfBirthLunar.getText() : tfBirthSolar.getText();

		result[6] = !birth.equals("") ? java.sql.Date.valueOf(birth) : null;
		result[7] = chckbxIsLunar.isSelected();

		Calendar cal = Calendar.getInstance();

		result[8] = new java.sql.Date(cal.getTimeInMillis());
		result[9] = "Bronze";
		result[10] = lblPic.getName() != null ? lblPic.getName() : null;

		return result;
	}

	public Object[] getUpdateData() throws ParseException {
		Object[] result = new Object[9];

		result[0] = String.valueOf(tfPW2.getPassword());
		result[1] = tfName.getText();
		result[2] = tfMobile2.getText() != ""
				? cbMobile1.getSelectedItem().toString() + tfMobile2.getText() + tfMobile3.getText()
				: null;
		result[3] = tfEmail1.getText() != "" ? tfEmail1.getText() + "@" + tfEmail2.getText() : null;
		result[4] = tfAddress.getText() != "" ? tfAddress.getText() : null;

		String birth = chckbxIsLunar.isSelected() ? tfBirthLunar.getText() : tfBirthSolar.getText();

		result[5] = !birth.equals("") ? java.sql.Date.valueOf(birth) : null;
		result[6] = chckbxIsLunar.isSelected();

		Calendar cal = Calendar.getInstance();

		result[7] = new java.sql.Date(cal.getTimeInMillis());
		result[8] = lblPic.getName() != null ? lblPic.getName() : null;

		return result;
	}

	public String getDeleteData() {
		return tfID.getText();
	}

	public void setMember(Object[] row) {
		tfID.setText(row[0].toString());
		tfPW1.setText(row[1].toString());
		tfPW2.setText(row[1].toString());
		tfName.setText(row[2].toString());

		String mobile = row[3].toString();
		int pos = mobile.length() == 10 ? 6 : 7;

		cbMobile1.setSelectedItem(mobile.substring(0, 3));
		tfMobile2.setText(mobile.substring(3, pos));
		tfMobile3.setText(mobile.substring(pos, mobile.length()));

		String email = row[4] != null ? row[4].toString() : "@";
		pos = email.indexOf("@");
		tfEmail1.setText(email.substring(0, pos));
		tfEmail2.setText(email.substring(pos + 1, email.length()));

		String address = row[5] != null ? row[5].toString() : "";
		tfAddress.setText(address);

		Date birth = row[6] != null ? (Date) row[6] : null;
		String birthString = birth != null ? format.format(birth) : "";

		boolean IsLunar = (boolean) row[7];
		chckbxIsLunar.setSelected(IsLunar);

		if (IsLunar)
			tfBirthLunar.setText(birthString);
		else
			tfBirthSolar.setText(birthString);

		cbGrade.setSelectedItem(MemberGrade.valueOf(row[9].toString()));

		String picpath = row[10] != null ? row[10].toString() : "";

		if (!picpath.equals("")) {

			Toolkit tk = Toolkit.getDefaultToolkit();
			Image img = tk.getImage(imgFilePath + picpath);
			img = img.getScaledInstance(195, 250, Image.SCALE_SMOOTH);
			ImageIcon icon = new ImageIcon(img);
			lblPic.setIcon(icon);

		} else {

		}
	}

}
