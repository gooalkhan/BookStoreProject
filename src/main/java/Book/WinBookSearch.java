package Book;

import common.BookSearchCondition;
import common.JDialogType;
import common.MyTable;
import common.MyTaskListener;
import common.Searchable;
import common.WinSearch;

import javax.swing.DefaultComboBoxModel;
import java.util.Vector;

public class WinBookSearch extends WinSearch {
	/**
	 * Create the dialog.
	 */
	public WinBookSearch(JDialogType t) {
		super(t);
	}

	public WinBookSearch() {
		
	}

	@Override
	protected void searchByCondition() {
		table.clearTable();
		lblStatus.setText("");
		String searchWord = conditionPanel.getSearchWord();
		BookSearchCondition condition = (BookSearchCondition) conditionPanel.getSelectedCondition();

		int limit = t == JDialogType.ReadAll ? LIMIT : Integer.MAX_VALUE;

		this.setTask(new BookTask(condition.name(), searchWord, limit));
		task.addPropertyChangeListener(new MyTaskListener(this, task, t));
		task.execute();
	}

	@Override
	protected void setTitleByType() {
		switch (t) {
			case Create:
				setTitle("도서 검색 창 - 도서 등록");
				break;
			case Read:
				setTitle("도서 검색 창 - 도서 조회");
				break;
			case Update:
				setTitle("도서 검색 창 - 도서 변경");
				break;
			case Delete:
				setTitle("도서 검색 창 - 도서 삭제");
				break;
			case ReadAll:
				setTitle("도서 검색 창 - 도서 전부 검색(최대 %d건)".formatted(LIMIT));
				break;
			default:
				setTitle("도서 검색 창");
				break;
		}
	}

	@Override
	protected MyTable initTable() {
		return new BookTable();
	}

	@Override
	protected DefaultComboBoxModel<Enum> initModel() {
		// String[] header = new String[] { "ISBN", "제목", "저자", "출판사" };
		return new DefaultComboBoxModel<Enum>(BookSearchCondition.values());
	}

	@Override
	public void openReadAllWindow(Vector<Object[]> rows) {
		WinBookShowAll dlg = new WinBookShowAll(rows);
		dlg.setModal(true);
		dlg.setVisible(true);
	}

	@Override
	protected Searchable initSearchPanel() {
		return new PanelBookSearch(initModel());
	}
}
