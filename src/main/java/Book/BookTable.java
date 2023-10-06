package Book;

import java.sql.Date;
import java.sql.SQLException;

import javax.swing.table.DefaultTableModel;

import common.JDialogType;
import common.MyTable;

public class BookTable extends MyTable {

	public BookTable() {}
	
	public void openJDialog(JDialogType t) {
		int row = this.getSelectedRow();
		String isbn = this.getValueAt(row, 0).toString();

		try {
			Object[] entry = BookDB.getInstance().searchBook(isbn);
		
		Book dlg;
			
		switch (t) {
		case Create:
			dlg = new WinBookAdd();
			break;
		case Read:
			dlg = new WinBookShow(entry);
			break;
		case Update:
			dlg = new WinBookUpdate(entry);
			break;
		case Delete:
			dlg = new WinBookRemove(entry);
			break;
		case ReadAll:
			dlg = null;
			break;
		default:
			dlg = null;
			break;
		}
		if (dlg != null) {
			dlg.setModal(true);
			dlg.setVisible(true);
		}

		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
	}

	@Override
	protected DefaultTableModel initModel(String[] header) {
		// TODO Auto-generated method stub
		DefaultTableModel model = new DefaultTableModel(header, 0) {
			@Override
			public Class<?> getColumnClass(int column) {
				switch (column) {
				case 4:
					return Date.class;
				case 6:
					return Integer.class;
				default:
					return String.class;
				}
			}
		};
		return model;
	}

	@Override
	protected String[] initHeader() {
		// TODO Auto-generated method stub
		String[] bookHeader = new String[] { "ISBN", "제목", "저자", "출판사", "출판일", "상세", "가격", "그림경로" };
		return bookHeader;
	}
	

}
