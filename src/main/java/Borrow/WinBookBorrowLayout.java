package Borrow;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JSplitPane;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import Book.BookTab;
import Book.BookTable;
import Book.PanelBookSearch;
import Member.Member;
import common.BookSearchCondition;

import java.awt.Dimension;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Rectangle;
import java.awt.FlowLayout;
import java.awt.GridLayout;

public class WinBookBorrowLayout extends JDialog {
	BookTable table;
	BookTab book;
	Member member;
	PanelBookSearch panelCondition;
	JLabel lblStatus;
	JProgressBar progressBar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WinBookBorrowLayout dialog = new WinBookBorrowLayout();
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
	public WinBookBorrowLayout() {
		setTitle("도서 대출");
		setBounds(100, 100, 1276, 782);
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		getContentPane().add(splitPane, BorderLayout.CENTER);
		
		JPanel panelLeft = new JPanel();
		panelLeft.setMinimumSize(new Dimension(10, 280));
		splitPane.setLeftComponent(panelLeft);
		panelLeft.setLayout(new BoxLayout(panelLeft, BoxLayout.Y_AXIS));
		
		panelCondition = new PanelBookSearch();
		panelCondition.setMaximumSize(new Dimension(32767, 35));
		panelLeft.add(panelCondition);
		
		JPanel panelTable = new JPanel();
		panelLeft.add(panelTable);
		panelTable.setLayout(new BoxLayout(panelTable, BoxLayout.X_AXIS));
		
		JScrollPane scrollPane = new JScrollPane();
		panelTable.add(scrollPane);
		
		table = new BookTable();
		scrollPane.setViewportView(table);
		
		JPanel panelAction = new JPanel();
		panelAction.setMaximumSize(new Dimension(32767, 50));
		panelLeft.add(panelAction);
		
		JButton btnBorrow = new JButton("대출");
		btnBorrow.setFont(new Font("굴림", Font.PLAIN, 20));
		panelAction.add(btnBorrow);
		
		JButton btnReturn = new JButton("반납");
		btnReturn.setFont(new Font("굴림", Font.PLAIN, 20));
		panelAction.add(btnReturn);
		
		JPanel panelRight = new JPanel();
		splitPane.setRightComponent(panelRight);
		panelRight.setLayout(new BoxLayout(panelRight, BoxLayout.X_AXIS));
		
//		panelMember = new Member();
//		panelRight.add(panelMember);
		
		book = new BookTab();
		JPanel panelBook = new JPanel();
		panelBook.setLayout(new BorderLayout(0, 0));
		panelBook.add(book);
		panelRight.add(panelBook);
		
		member = new Member();
		JPanel panelMember = new JPanel();
		panelMember.setLayout(new BoxLayout(panelMember, BoxLayout.X_AXIS));
		panelMember.add(member);
		panelRight.add(panelMember);
		
		JPanel panelStatus = new JPanel();
		getContentPane().add(panelStatus, BorderLayout.SOUTH);
		panelStatus.setLayout(new BoxLayout(panelStatus, BoxLayout.X_AXIS));
		
		lblStatus = new JLabel("준비완료");
		panelStatus.add(lblStatus);
		
		Component horizontalGlue = Box.createHorizontalGlue();
		panelStatus.add(horizontalGlue);
		
		progressBar = new JProgressBar();
		progressBar.setStringPainted(true);
		panelStatus.add(progressBar);
		
		Component horizontalGlue_1 = Box.createHorizontalGlue();
		panelStatus.add(horizontalGlue_1);

	}
}
