package Member;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JDialog;
import javax.swing.table.DefaultTableModel;

import common.JDialogType;
import common.MemberGrade;
import common.MyTable;
import Borrow.WinBookBorrow;

public class MemberTable extends MyTable {
	
	public MemberTable() {}

	@Override
	public void openJDialog(JDialogType t) {
		int row = this.getSelectedRow();
		String id = this.getValueAt(row, 0).toString();

		try {
			Object[] entry = MemberDB.getInstance().searchMemberByID(id);
		
		JDialog dlg;
			
		switch (t) {
		case Create:
			dlg = new WinMemberAdd();
			break;
		case Read:
			dlg = new WinMemberShow(entry);
			break;
		case Update:
			dlg = new WinMemberUpdate(entry);
			break;
		case Delete:
			dlg = new WinMemberRemove(entry);
			break;
		case ReadAll:
			dlg = null;
			break;
		case Borrow:
			dlg = new WinBookBorrow(entry);
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
	public void addRows(Vector<Object[]> rows) {
		model.setNumRows(0);
		Object[] temp;
		for (Object[] row : rows) {
			
			temp = new Object[10];
			
			temp[0] = row[0];
			temp[1] = row[2];
			temp[2] = row[3];
			temp[3] = row[4];
			temp[4] = row[5];
			temp[5] = row[6];
			temp[6] = row[7];
			temp[7] = row[8];
			temp[8] = row[9];
			temp[9] = row[10];
			
			model.addRow(temp);
		}
	}

	@Override
	protected DefaultTableModel initModel(String[] header) {
		DefaultTableModel model = new DefaultTableModel(header, 0) {
			@Override
			public Class<?> getColumnClass(int column) {
				switch (column) {
				case 5:
					return Date.class;
				case 6:
					return Boolean.class;
				case 7:
					return Date.class;		
				case 8:
					return MemberGrade.class;
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
		String[] header = new String[] { "ID", "회원이름", "연락처", "이메일", "주소", "출생연월일", "음력여부", "가입일", "등급", "사진경로" };
		return header;
	}
}
