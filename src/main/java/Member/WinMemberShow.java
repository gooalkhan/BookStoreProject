package Member;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

public class WinMemberShow extends WinMember {

	/**
	 * Create the dialog.
	 */
	public WinMemberShow(Object[] row) {
		super(row);
		init();
	}
	
	public WinMemberShow() {
		init();

	}
	
	private void init() {
		setTitle("회원 조회");
		
		member.setEnableAll(false);
		member.setVisibleInputButtons(false);
		member.setVisibleUnusedBirth(true);
		member.setVisiblePW(false);
		member.btnClose.setVisible(true);
		
		member.btnClose.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
			}
			
		});
		
	}

	@Override
	protected Component[] initFocusComponent() {
		Component[] focusOrder = new Component[] {member.btnClose};
		
		return focusOrder;
	}

}