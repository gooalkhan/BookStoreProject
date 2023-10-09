package Member;

import java.awt.Component;
import java.awt.Container;
import java.awt.FocusTraversalPolicy;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Arrays;

import javax.swing.InputVerifier;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeListener;

import common.MyTraversalPolicy;
import common.OpenAndSetPic;
import main.WinMain;

import javax.swing.event.ChangeEvent;

public class WinMemberAdd extends WinMember {
	Component[] focusOrder;
	JButton btnDuplicate;
	/**
	 * Create the dialog.
	 */
	
	public WinMemberAdd() {

		setTitle("회원 등록");
		member.lblGrade.setVisible(false);
		member.cbGrade.setVisible(false);
		member.btnAdd.setVisible(true);
		
		member.lblPic.addMouseListener(new OpenAndSetPic(member.lblPic));
		
		btnDuplicate = member.btnDuplicate;
		this.focusOrder = initFocusComponent(); //포커스 동적변경을 위한 내부멤버화
		
		member.chckbxIsLunar.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if(member.chckbxIsLunar.isSelected()) {
					focusOrder[focusOrder.length-1] = member.tfBirthLunar;
				} else {
					focusOrder[focusOrder.length-1] = member.tfBirthSolar;
				}
			}
		});
		
		btnDuplicate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				isIdDuplicate();
			}
			
		});
		
		member.btnAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					addMember(member.getAddData());
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		});
		
		member.tfID.setInputVerifier(new InputVerifier() {

			@Override
			public boolean verify(JComponent input) {
				// TODO Auto-generated method stub
				return isIdDuplicate();
			}
			
		});
		
		this.setFocusTraversalPolicy(new MyTraversalPolicy(focusOrder));
	}
	
	private boolean isIdDuplicate() {
		boolean result = false;
		String id = member.tfID.getText();
		
		if (id.length() == 0) {
			JOptionPane.showMessageDialog(WinMemberAdd.this, "아이디를 입력해 주세요");
			return result;
		}

		try {
			Object[] match = MemberDB.getInstance().searchMemberByID(id);
			
			if (match[0] == null) {
				JOptionPane.showMessageDialog(WinMemberAdd.this, "사용할 수 있는 아이디입니다");
				return !result;
			} else {
				JOptionPane.showMessageDialog(WinMemberAdd.this, "중복된 아이디가 있습니다");
				return result;
			}
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return result;
		}
		
	}

	@Override
	protected Component[] initFocusComponent() {
		// TODO Auto-generated method stub
		Component[] focusOrder = new Component[] {member.tfID, member.tfPW1, member.tfPW2, member.tfName, member.cbMobile1, member.tfMobile2, member.tfMobile3, member.tfEmail1, member.tfEmail2, member.tfAddress, member.tfBirthSolar};
		
		return focusOrder;
	}
	
	private void addMember(Object[] row) {
		try {
			MemberDB.getInstance().addMember(row);
			member.clearAll();
			JOptionPane.showMessageDialog(this, "회원 가입이 완료되었습니다");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
