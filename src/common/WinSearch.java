package common;

import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Book.PanelBookSearch;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JProgressBar;
import javax.swing.BoxLayout;

public abstract class WinSearch extends JDialog implements MyListenerAttachable {
	protected Searchable conditionPanel;
	protected JProgressBar progressBar;
	protected JLabel lblStatus;
	protected MyTable table;
	protected JDialogType t;
	protected final int LIMIT = 10;
	protected MyTask task;

	/**
	 * Create the dialog.
	 */
	public WinSearch(JDialogType t) {
		this();
		this.t = t;
		setTitleByType();
	}

	public WinSearch() {

		setTitle("멤버 검색창");

		setBounds(100, 100, 727, 300);

		conditionPanel = initSearchPanel();
		getContentPane().add((JPanel)conditionPanel, BorderLayout.NORTH);
//		JPanel panel = new JPanel();
//		getContentPane().add(panel, BorderLayout.NORTH);
//
//		JLabel lblSearchWord = new JLabel("검색어");
//		lblSearchWord.setToolTipText("검색어");
//		panel.add(lblSearchWord);
//
//		cbCondition = new JComboBox<>();
//		cbCondition.setModel(initModel());
//		panel.add(cbCondition);
//
//		tfSearch = new JTextField();
//		panel.add(tfSearch);
//		tfSearch.setColumns(10);
//
//		JButton btnSearch = new JButton("찾기");
//		panel.add(btnSearch);

		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);

		table = initTable();
		scrollPane.setViewportView(table);

		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));

		lblStatus = new JLabel("");
		panel_1.add(lblStatus);

		Component horizontalGlue = Box.createHorizontalGlue();
		panel_1.add(horizontalGlue);

		progressBar = new JProgressBar();
		progressBar.setStringPainted(true);
		panel_1.add(progressBar);

		Component horizontalGlue_1 = Box.createHorizontalGlue();
		panel_1.add(horizontalGlue_1);

		////// 여기까지 레이아웃

		conditionPanel.getBtnSearch().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchByCondition();
			}
		});

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				table.openJDialog(t);
				searchByCondition();
			}
		});

	}

	protected abstract MyTable initTable();

	protected abstract DefaultComboBoxModel<Enum> initModel();

	protected abstract void searchByCondition(); // 디비 연결은 상속 클래스에서 각자

	protected abstract void setTitleByType();
	
	protected abstract Searchable initSearchPanel();

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
		return table;
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

}
