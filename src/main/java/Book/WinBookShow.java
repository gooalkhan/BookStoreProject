package Book;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.text.JTextComponent;

public class WinBookShow extends Book {
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WinBookShow dialog = new WinBookShow();
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
	 * @throws SQLException 
	 */
	public WinBookShow(String isbn) throws SQLException {
		super(isbn);
	}
	
	public WinBookShow(Object[] row) {
		super(row);
		init();
	}
	
	public WinBookShow() {
		init();
	}
	
	private void init() {
		setTitle("도서 정보 보기 창");
		book.getLblPicURL().setText("도서 소개");
		
	}
	
}
