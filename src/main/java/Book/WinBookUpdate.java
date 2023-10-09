package Book;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class WinBookUpdate extends Book {
	private JTextField tfISBN;
	private JTextField tfPicURL;
	private JButton btnUpdate;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WinBookUpdate dialog = new WinBookUpdate();
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
	public WinBookUpdate(String isbn) throws SQLException {
		super(isbn);
		init();
	}
	
	public WinBookUpdate(Object[] row) {
		super(row);
		init();
	}
	
	
	public WinBookUpdate() {
		init();
	}
	
	private void init() {
		setTitle("도서정보 변경창");
		
		btnUpdate = book.getBtnUpdate();
		tfPicURL = book.getTfPicURL();
		tfISBN = book.getTfISBN();
		
		book.getBtnCalendar().setVisible(true);
		book.getLblPicURL().setVisible(true);
		book.getBtnDuplicate().setVisible(true);
		
		btnUpdate.setVisible(true);
		tfPicURL.setVisible(true);
		tfISBN.setEnabled(false);
		
		btnUpdate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				updateBook();
			}
			
		});
	}
	
	private void updateBook() {
		Object[] row = new Object[8];
		
		row[0] = tfISBN.getText();
		row[1] = book.getTfTitle().getText();
		row[2] = book.getTfAuthor().getText();
		row[3] = book.getTfPublisher().getText();
		
		Date d = Date.valueOf(book.getTfPdate().getText());
		
		row[4] = d;
		row[5] = book.getTaDescription().getText();
		row[6] = Integer.parseInt(book.getTfDiscount().getText());
		row[7] = tfPicURL.getText();
		
		try {
			BookDB.getInstance().updateBook(row);
			JOptionPane.showMessageDialog(this, "도서가 수정되었습니다");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
