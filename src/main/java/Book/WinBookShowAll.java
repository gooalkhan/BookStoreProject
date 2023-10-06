package Book;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.Vector;

import javax.swing.JDialog;
import javax.swing.JTabbedPane;

public class WinBookShowAll extends JDialog {
	JTabbedPane tp;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WinBookShowAll dialog = new WinBookShowAll();
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
	
	public WinBookShowAll(Vector<Object[]> rows) {
		this();
		
		String longTitle;
		
		for (Object[] row:rows) {
			longTitle = row[1].toString();
			String title = longTitle.length() > 13 ? longTitle.substring(0, 13) + "..." : longTitle; 
			tp.addTab(title, new BookTab(row));
		}
	}
	
	public WinBookShowAll() {
		setTitle("모든 도서 정보 보기 창");
		setBounds(100, 100, 550, 550);
		
		tp = new JTabbedPane(JTabbedPane.TOP);
		tp.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		
		getContentPane().add(tp, BorderLayout.CENTER);

	}

}
