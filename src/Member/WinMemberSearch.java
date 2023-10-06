package Member;

import common.JDialogType;
import common.MemberGrade;
import common.MemberSearchCondition;
import common.MyTable;
import common.NoConditionSelectedException;
import common.Searchable;
import common.WinSearch;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

import Book.PanelBookSearch;

public class WinMemberSearch extends WinSearch {
	private static MemberDB db = MemberDB.getInstance();

	public WinMemberSearch(JDialogType t) {
		// TODO Auto-generated constructor stub
		super(t);
	}

	@Override
	protected MyTable initTable() {
		// TODO Auto-generated method stub
		return new MemberTable();
	}

	@Override
	protected void searchByCondition() {
		// TODO Auto-generated method stub
		Vector<Object[]> toAdd = new Vector<>();
		HashMap<String, String> searchWordAndConditions;
		try {
			searchWordAndConditions = conditionPanel.getSearchWordAndConditions();

			// String searchWord = conditionPanel.getSearchWord();
			// MemberSearchCondition condition =
			// (MemberSearchCondition)conditionPanel.getSelectedCondition();

			int limit = t == JDialogType.ReadAll ? LIMIT : Integer.MAX_VALUE;

			try {
				toAdd = db.searchMembersByMultipleConditions(searchWordAndConditions, limit);

			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			if (toAdd != null & toAdd.size() != 0) {

				if (t != JDialogType.ReadAll)
					table.addRows(toAdd);
				else {
					WinMemberShowAll dlg = new WinMemberShowAll(toAdd);
					dlg.setModal(true);
					dlg.setVisible(true);
				}

			} else {
				JOptionPane.showMessageDialog(this, "검색 결과가 없습니다");
			}

		} catch (NoConditionSelectedException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
	}

	@Override
	protected void setTitleByType() {
		// TODO Auto-generated method stub
		switch (t) {
		case Create:
			setTitle("회원 검색 창 - 회원 등록");
			break;
		case Read:
			setTitle("회원 검색 창 - 회원 조회");
			break;
		case Update:
			setTitle("회원 검색 창 - 회원 변경");
			break;
		case Delete:
			setTitle("회원 검색 창 - 회원 탈퇴");
			break;
		case ReadAll:
			setTitle("회원 검색 창 - 회원 전부 검색(최대 %d건)".formatted(LIMIT));
			break;
		default:
			setTitle("회원 검색 창");
			break;
		}
	}

	@Override
	protected DefaultComboBoxModel<Enum> initModel() {
		// String[] header = new String[] { "ID", "이름", "연락처", "주소" };
		// TODO Auto-generated method stub
		return new DefaultComboBoxModel<Enum>(MemberGrade.values());
	}

	@Override
	public void openReadAllWindow(Vector<Object[]> rows) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'openReadAllWindow'");
	}

	@Override
	protected Searchable initSearchPanel() {
		// TODO Auto-generated method stub
		return new PanelMemberSearch(initModel());
	}

	/**
	 * Create the dialog.
	 */

}
