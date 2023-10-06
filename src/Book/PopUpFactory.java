package Book;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTable;

import common.JDialogType;

public class PopUpFactory {
	JPopupMenu popupMenu;
	BookTable table;
	
	PopUpFactory(BookTable table) {
		popupMenu = new JPopupMenu();
		
		JMenuItem mntmAddBook = new JMenuItem("도서 등록...");
		mntmAddBook.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				table.openJDialog(JDialogType.Create);
			}
			
		});
		
		JMenuItem mntmUpdateBook = new JMenuItem("도서 변경...");
		mntmUpdateBook.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				table.openJDialog(JDialogType.Update);
			}
			
		});
		JMenuItem mntmDeleteBook = new JMenuItem("도서 삭제...");
		mntmDeleteBook.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				table.openJDialog(JDialogType.Delete);
			}
			
		});
		
		JMenuItem mntmShowBook = new JMenuItem("도서 보기...");
		mntmShowBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table.openJDialog(JDialogType.Read);
			}
		});
		
		popupMenu.add(mntmAddBook);
		popupMenu.add(mntmUpdateBook);
		popupMenu.add(mntmDeleteBook);
		popupMenu.add(mntmShowBook);
		
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
}
