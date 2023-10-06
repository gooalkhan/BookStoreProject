package common;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTable;

import Member.MemberTable;

public class PopUpFactory {
	JPopupMenu popupMenu;
	MyTable table;
	String[] menuName;
	
	public PopUpFactory(MyTable table) {
		
		popupMenu = new JPopupMenu();
		this.table = table;
		menuName = initMenuName(table);
		
		JMenuItem mntmAdd = new JMenuItem(menuName[0]);
		mntmAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				table.openJDialog(JDialogType.Create);
			}
			
		});
		
		JMenuItem mntmShow = new JMenuItem(menuName[1]);
		mntmShow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table.openJDialog(JDialogType.Read);
			}
		});
		
		JMenuItem mntmUpdate = new JMenuItem(menuName[2]);
		mntmUpdate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				table.openJDialog(JDialogType.Update);
			}
			
		});
		JMenuItem mntmDelete = new JMenuItem(menuName[3]);
		mntmDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				table.openJDialog(JDialogType.Delete);
			}
			
		});
		
		popupMenu.add(mntmAdd);
		popupMenu.add(mntmUpdate);
		popupMenu.add(mntmDelete);
		popupMenu.add(mntmShow);
		
		addPopup(table);
	}
	
	public void addPopup(Component component) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					JTable source = (JTable)e.getSource();
					int row = source.rowAtPoint(e.getPoint());
					int col = source.columnAtPoint(e.getPoint());
					if(!source.isRowSelected(row)) {
						source.changeSelection(row, col, false, false);
					}
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				PopUpFactory.this.popupMenu.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
	
	public String[] initMenuName(MyTable table) {
		String[] menuname = new String[4];
		
		if (table instanceof MemberTable) {
			menuname[0] = "회원 등록...";
			menuname[1] = "회원 조회...";
			menuname[2] = "회원 변경...";
			menuname[3] = "회원 탈퇴...";
		} else {
			menuname[0] = "도서 등록...";
			menuname[1] = "도서 조회...";
			menuname[2] = "도서 변경...";
			menuname[3] = "도서 삭제...";
		}
		
		return menuname;
	}
}
