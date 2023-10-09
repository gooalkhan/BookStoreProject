package Member;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Vector;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class WinMemberShowAll extends JDialog {
	//JTabbedPane tp;
	JPanel panel;

	/**
	 * Create the dialog.
	 */
	
	public WinMemberShowAll(Vector<Object[]> rows) {
		this();
		
		panel.setLayout(new GridLayout(rows.size(), 1, 10, 10));
		panel.setPreferredSize(new Dimension(700,300*rows.size()));
		
		for (Object[] row:rows) {
			Member m = new Member(row);
			m.setEnableAll(false);
			panel.add(m);
			//tp.addTab(row[0].toString(), m);
		}
	}
	
	public WinMemberShowAll() {
		setTitle("모든 도서 정보 보기 창");
		setBounds(100, 100, 700, 375);
		panel = new JPanel();
		
//		tp = new JTabbedPane(JTabbedPane.TOP);
//		tp.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
//		
//		getContentPane().add(tp, BorderLayout.CENTER);
		JScrollPane scrollPane = new JScrollPane(panel,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER
				);
		getContentPane().add(scrollPane, BorderLayout.CENTER);

	}

}
