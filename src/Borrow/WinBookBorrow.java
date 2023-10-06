package Borrow;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

import Book.BookDB;
import Book.BookTask;
import common.BookSearchCondition;
import common.JDialogType;
import common.MyListenerAttachable;
import common.MyTable;
import common.MyTask;
import common.MyTaskListener;

public class WinBookBorrow extends WinBookBorrowLayout implements MyListenerAttachable {
	private JButton btnSearch;
	private MyTask task;
	
	public WinBookBorrow() {
		member.setEnableAll(false);
		member.setVisibleInputButtons(false);
		panelCondition.setModel(new DefaultComboBoxModel<Enum>(BookSearchCondition.values()));
		btnSearch = panelCondition.getBtnSearch();
		
		btnSearch.addActionListener(e-> {
			table.clearTable();
			String searchWord = panelCondition.getSearchWord();
			BookSearchCondition condition = (BookSearchCondition) panelCondition.getSelectedCondition();

			int limit = Integer.MAX_VALUE;

			this.setTask(new BookTask(condition.name(), searchWord, limit));
			task.addPropertyChangeListener(new MyTaskListener(this, task, JDialogType.Read));
			System.out.println("executing job...");
			task.execute();
		});
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				String isbn = table.getValueAt(row, 0).toString();

				try {
					Object[] entry = BookDB.getInstance().searchBook(isbn);
					
					WinBookBorrow.this.book.setBook(entry);
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
	}
	
	public WinBookBorrow(Object[] row) {
		this();
		member.setMember(row);
		member.setVisiblePW(false);
		member.setVisibleGrade(false);
		member.setVisibleUnusedBirth(true);
	}

	@Override
	public JProgressBar getProgressBar() {
		// TODO Auto-generated method stub
		return this.progressBar;
	}

	@Override
	public JLabel getLblStatus() {
		// TODO Auto-generated method stub
		return this.lblStatus;
	}

	@Override
	public MyTable getTable() {
		// TODO Auto-generated method stub
		return this.table;
	}

	@Override
	public void setTask(MyTask task) {
		if (this.task != null) {
			task.cancel(true);
		}
		this.task = task;
		
	}

	@Override
	public void removeTask() {
		if (this.task != null) {
			task.cancel(true);
		}
		this.task = null;
	}

	@Override
	public void openReadAllWindow(Vector<Object[]> rows) {
		// TODO Auto-generated method stub
	}
}
