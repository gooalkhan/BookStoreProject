package Member;

import java.awt.Component;

public class WinMemberRemove extends WinMember {

	/**
	 * Create the dialog.
	 */
	public WinMemberRemove(Object[] row) {
		super(row);
		init();
	}
	
	public WinMemberRemove() {
		init();

	}
	
	private void init() {
		setTitle("회원 탈퇴");
		member.setEnableAll(false);
		member.btnDelete.setVisible(true);
	}

	@Override
	protected Component[] initFocusComponent() {
		Component[] focusOrder = new Component[] {member.btnDelete};
		
		return focusOrder;
	}

}