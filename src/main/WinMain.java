package main;

import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Vector;

import Book.BookTable;
import Book.BookTask;
import Book.WinBookAdd;
import Book.WinBookSearch;
import Member.MemberDB;
import Member.MemberTable;
import Member.WinMemberAdd;
import Member.WinMemberSearch;
import common.BookSearchCondition;
import common.JDialogType;
import common.MemberSearchCondition;
import common.MyListenerAttachable;
import common.MyTable;
import common.MyTask;
import common.MyTaskListener;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class WinMain extends WinMainLayout implements MyListenerAttachable{
	BookTable booktable;
	MemberTable membertable;
	private MyTask task;

	public static MemberDB memberdb;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		memberdb = Member.MemberDB.getInstance();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WinMain dialog = new WinMain();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public WinMain() {

		setTitle("ICI 도서 대여점 프로그램 Ver 0.1");

		booktable = new BookTable();
		membertable = new MemberTable();
		progressBar.setValue(0);
		
		Vector<Object> v = new Vector<>();
		BookSearchCondition[] bookCondition = BookSearchCondition.values();
		MemberSearchCondition[] memberCondition = MemberSearchCondition.values(); 
		for (BookSearchCondition a:bookCondition) v.add(a);
		for (MemberSearchCondition a:memberCondition) v.add(a);
		DefaultComboBoxModel<Object> model = new DefaultComboBoxModel<Object>(v);
		cbCondition.setModel(model);
		/// 여기까지 멤버등록
		
		btnSearch.addActionListener(e -> {
			Object selected = cbCondition.getSelectedItem();
			if (selected instanceof MemberSearchCondition) {
				String name = ((MemberSearchCondition)selected).name();
				searchMember(name, tfSearchWord.getText());
			} else {
				String name = ((BookSearchCondition)selected).name();
				searchBook(name, tfSearchWord.getText());
			}
		});

		mntmBookShowAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchBookAndDo(JDialogType.ReadAll);
			}
		});
		
		btnBookShowAll.addActionListener(e -> {
			searchBook("isbn", "");
		});

		btnBookSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchBookAndDo(JDialogType.Read);
			}
		});
		mntmBookSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchBookAndDo(JDialogType.Read);
			}
		});

		btnBookUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchBookAndDo(JDialogType.Update);
			}
		});
		mntmBookUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchBookAndDo(JDialogType.Update);
			}
		});

		btnBookRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchBookAndDo(JDialogType.Delete);

			}
		});
		mntmBookRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchBookAndDo(JDialogType.Delete);
			}
		});

		btnBookAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WinBookAdd dlg = new WinBookAdd();
				dlg.setModal(true);
				dlg.setVisible(true);
			}
		});
		mntmBookAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WinBookAdd dlg = new WinBookAdd();
				dlg.setModal(true);
				dlg.setVisible(true);
			}
		});

		booktable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				booktable.openJDialog(JDialogType.Read);
			}
		});

		membertable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				membertable.openJDialog(JDialogType.Read);
			}
		});

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				System.exit(0);
			}
		});

		/// 여기까지 도서관련 리스너

		mntmMemberAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WinMemberAdd dlg = new WinMemberAdd();
				dlg.setModal(true);
				dlg.setVisible(true);
			}
		});

		mntmMemberRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchMemberAndDo(JDialogType.Delete);
			}
		});

		mntmMemberUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchMemberAndDo(JDialogType.Update);
			}
		});

		mntmMemberSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchMemberAndDo(JDialogType.Read);
			}
		});

		mntmMemberShowAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchMemberAndDo(JDialogType.ReadAll);
			}
		});

		btnMemberAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WinMemberAdd dlg = new WinMemberAdd();
				dlg.setModal(true);
				dlg.setVisible(true);
			}
		});

		btnMemberRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchMemberAndDo(JDialogType.Delete);
			}
		});

		btnMemberUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchMemberAndDo(JDialogType.Update);
			}
		});

		btnMemberSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchMemberAndDo(JDialogType.Read);
			}
		});

		btnMemberShowAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchMember("id", "");
			}
		});

		/// 여기까지 멤버관련 리스너
		
		mntmBorrow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchMemberAndDo(JDialogType.Borrow);
			}
		});

	}
	
	private void searchBook(String condition, String searchWord) {
		booktable.clearTable();
		WinMain.this.scrollPane.setViewportView(booktable);
		lblStatus.setText("");
		BookTask task = new BookTask(condition, searchWord);
		this.setTask(task);
		task.addPropertyChangeListener(new MyTaskListener(this, task));
		task.execute();
	}
	
	private void searchMember(String condition, String searchWord) {
		try {
			Vector<Object[]> toShow = memberdb.searchMembers(condition, searchWord);
			membertable.addRows(toShow);
			WinMain.this.scrollPane.setViewportView(membertable);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	private void searchBookAndDo(JDialogType t) {
		WinBookSearch dlg = new WinBookSearch(t);
		dlg.setModal(true);
		dlg.setVisible(true);
	}

	private void searchMemberAndDo(JDialogType t) {
		WinMemberSearch dlg = new WinMemberSearch(t);
		dlg.setModal(true);
		dlg.setVisible(true);
	}

	@Override
	public synchronized void setTask(MyTask task) {
		if (this.task != null) {
			task.cancel(true);
		}
		this.task = task;

	}

	@Override
	public synchronized void removeTask() {
		if (this.task != null) {
			task.cancel(true);
		}
		this.task = null;
	}

	@Override
	public JProgressBar getProgressBar() {
		return progressBar;
	}

	@Override
	public JLabel getLblStatus() {
		return lblStatus;
	}

	@Override
	public MyTable getTable() {
		Component comp = this.scrollPane.getViewport().getView();
		if (comp instanceof MyTable) {
			return (MyTable)comp;
		}
		return null;
	}

	@Override
	public void openReadAllWindow(Vector<Object[]> rows) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'openReadAllWindow'");
	}

}
