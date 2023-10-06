package main;

import java.awt.EventQueue;

import javax.swing.JDialog;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JToolBar;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;

public class WinMainLayout extends JDialog {
	protected JTextField tfSearchWord;
	protected JTable table;
	
	protected JButton btnBookAdd;
	protected JButton btnBookRemove;
	protected JButton btnBookUpdate;
	protected JButton btnBookSearch;
	protected JButton btnBookShowAll;
	protected JMenuItem mntmBookAdd;
	protected JMenuItem mntmBookRemove;
	protected JMenuItem mntmBookSearch;
	protected JMenuItem mntmBookUpdate;
	protected JMenuItem mntmBookShowAll;
	
	protected JLabel lblStatus;
	protected JTabbedPane tabbedPane;
	protected JScrollPane scrollPane;
	
	protected JButton btnMemberAdd;
	protected JButton btnMemberRemove;
	protected JButton btnMemberUpdate;
	protected JButton btnMemberSearch;
	protected JButton btnMemberShowAll;
	protected JMenuItem mntmMemberAdd;
	protected JMenuItem mntmMemberRemove;
	protected JMenuItem mntmMemberUpdate;
	protected JMenuItem mntmMemberSearch;
	protected JMenuItem mntmMemberShowAll;
	protected JProgressBar progressBar;
	private Component horizontalGlue_1;
	protected JComboBox<Object> cbCondition;
	protected JButton btnSearch;
	protected JMenu mnBorrow;
	protected JMenuItem mntmBorrow;
	
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WinMainLayout dialog = new WinMainLayout();
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
	public WinMainLayout() {
		setTitle("ICI 도서 대여점 프로그램 Ver 0.1");

		setBounds(100, 100, 950, 457);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnBookManager = new JMenu("도서관리(B)");
		mnBookManager.setMnemonic('b');
		menuBar.add(mnBookManager);

		mntmBookAdd = new JMenuItem("도서 추가...");
		mnBookManager.add(mntmBookAdd);

		mntmBookRemove = new JMenuItem("도서 삭제...");
		mnBookManager.add(mntmBookRemove);

		mntmBookUpdate = new JMenuItem("도서 변경...");
		mnBookManager.add(mntmBookUpdate);

		mntmBookSearch = new JMenuItem("도서 검색...");
		mnBookManager.add(mntmBookSearch);

		JSeparator separator = new JSeparator();
		mnBookManager.add(separator);

		mntmBookShowAll = new JMenuItem("전체 도서 보기...");
		mnBookManager.add(mntmBookShowAll);

		JMenu mnMember = new JMenu("회원관리(M)");
		mnMember.setMnemonic('m');
		menuBar.add(mnMember);

		mntmMemberAdd = new JMenuItem("회원 가입...");
		mnMember.add(mntmMemberAdd);

		mntmMemberRemove = new JMenuItem("회원 탈퇴...");
		mnMember.add(mntmMemberRemove);

		mntmMemberUpdate = new JMenuItem("회원정보 변경...");
		mnMember.add(mntmMemberUpdate);

		mntmMemberSearch = new JMenuItem("회원 검색...");
		mnMember.add(mntmMemberSearch);

		JSeparator separator_1 = new JSeparator();
		mnMember.add(separator_1);

		mntmMemberShowAll = new JMenuItem("전체 회원 보기...");
		mnMember.add(mntmMemberShowAll);
		
		mnBorrow = new JMenu("도서 대출(R)");
		mnBorrow.setMnemonic('r');
		menuBar.add(mnBorrow);
		
		mntmBorrow = new JMenuItem("도서 대출...");
		mnBorrow.add(mntmBorrow);

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.SOUTH);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

		lblStatus = new JLabel("준비 완료");
		panel.add(lblStatus);

		Component horizontalGlue = Box.createHorizontalGlue();
		panel.add(horizontalGlue);
		
		progressBar = new JProgressBar();
		progressBar.setStringPainted(true);
		panel.add(progressBar);
		
		horizontalGlue_1 = Box.createHorizontalGlue();
		panel.add(horizontalGlue_1);

		JToolBar toolBar = new JToolBar();
		toolBar.setRollover(true);
		toolBar.setFloatable(false);
		getContentPane().add(toolBar, BorderLayout.NORTH);

		btnBookAdd = new JButton("");
		btnBookAdd.setToolTipText("도서추가");
		btnBookAdd.setIcon(new ImageIcon(WinMainLayout.class.getResource("/images/icons8-add-book-30.png")));
		toolBar.add(btnBookAdd);

		btnBookRemove = new JButton("");
		btnBookRemove.setToolTipText("도서삭제");
		btnBookRemove.setIcon(new ImageIcon(WinMainLayout.class.getResource("/images/icons8-remove-book-30.png")));
		toolBar.add(btnBookRemove);

		btnBookUpdate = new JButton("");
		btnBookUpdate.setToolTipText("도서 변경");
		btnBookUpdate.setIcon(new ImageIcon(WinMainLayout.class.getResource("/images/icons8-return-book-30.png")));
		toolBar.add(btnBookUpdate);

		btnBookSearch = new JButton("");
		btnBookSearch.setToolTipText("도서 검색");
		btnBookSearch.setIcon(new ImageIcon(WinMainLayout.class.getResource("/images/icons8-search-30.png")));
		toolBar.add(btnBookSearch);

		btnBookShowAll = new JButton("");
		btnBookShowAll.setToolTipText("전체 도서");
		btnBookShowAll.setIcon(new ImageIcon(WinMainLayout.class.getResource("/images/icons8-books-30.png")));
		toolBar.add(btnBookShowAll);

		toolBar.addSeparator();
		
		cbCondition = new JComboBox<>();
//		cbCondition.setModel(new DefaultComboBoxModel(new String[] {"isbn"}));
		toolBar.add(cbCondition);

		tfSearchWord = new JTextField();
		toolBar.add(tfSearchWord);
		tfSearchWord.setColumns(10);
		
		btnSearch = new JButton("  검색  ");
		toolBar.add(btnSearch);

		toolBar.addSeparator();

		btnMemberAdd = new JButton("");
		btnMemberAdd.setToolTipText("회원 가입");
		btnMemberAdd.setIcon(new ImageIcon(WinMainLayout.class.getResource("/images/icons8-add-user-30.png")));
		toolBar.add(btnMemberAdd);

		btnMemberRemove = new JButton("");
		btnMemberRemove.setToolTipText("회원 탈퇴");
		btnMemberRemove.setIcon(new ImageIcon(WinMainLayout.class.getResource("/images/icons8-denied-30.png")));
		toolBar.add(btnMemberRemove);

		btnMemberUpdate = new JButton("");
		btnMemberUpdate.setToolTipText("회원정보 수정");
		btnMemberUpdate.setIcon(new ImageIcon(WinMainLayout.class.getResource("/images/icons8-change-user-30.png")));
		toolBar.add(btnMemberUpdate);

		btnMemberSearch = new JButton("");
		btnMemberSearch.setToolTipText("회원 검색");
		btnMemberSearch.setIcon(new ImageIcon(WinMainLayout.class.getResource("/images/icons8-find-user-male-30.png")));
		toolBar.add(btnMemberSearch);

		btnMemberShowAll = new JButton("");
		btnMemberShowAll.setToolTipText("전체 회원");
		btnMemberShowAll.setIcon(new ImageIcon(WinMainLayout.class.getResource("/images/icons8-users-30.png")));
		toolBar.add(btnMemberShowAll);

		scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		table.setEnabled(false);
		scrollPane.setViewportView(table);
		
		tabbedPane = new JTabbedPane();

	}
	
}
