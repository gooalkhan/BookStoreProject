package Member;

import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JDialog;

import common.OpenAndSetPic;

public class WinMemberUpdate extends WinMember {

	/**
	 * Create the dialog.
	 */
	public WinMemberUpdate(Object[] row) {
		super(row);
		init();
	}
	
	public WinMemberUpdate() {
		init();

	}
	
	private void init() {
		setTitle("회원 변경");
		member.tfID.setEnabled(false);
		member.btnDuplicate.setEnabled(false);
		member.btnUpdate.setVisible(true);
		member.cbGrade.setEnabled(false);
		member.lblPic.addMouseListener(new OpenAndSetPic(member.lblPic));
	}

	@Override
	protected Component[] initFocusComponent() {
		// TODO Auto-generated method stub
		Component[] focusOrder = new Component[] {member.tfPW1, member.tfPW2, member.tfName, member.cbMobile1, member.tfMobile2, member.tfMobile3, member.tfEmail1, member.tfEmail2, member.tfAddress, member.tfBirthSolar};
		
		return focusOrder;
	}

}
