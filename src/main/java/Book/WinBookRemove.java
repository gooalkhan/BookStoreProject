package Book;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class WinBookRemove extends Book {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WinBookRemove dialog = new WinBookRemove();
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
	public WinBookRemove(String isbn) throws SQLException {
		super(isbn);
		init();
	}
	
	public WinBookRemove(Object[] row) {
		super(row);
		init();
	}

	public WinBookRemove() {
		init();

	}

	private void init() {
		setTitle("도서 삭제창");

		JButton btnRemove = book.getBtnRemove();

		btnRemove.setVisible(true);
		book.getLblPicURL().setText("도서 소개");

		btnRemove.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				int i = JOptionPane.showConfirmDialog(WinBookRemove.this, "삭제하시겠습니까?");
				if (i == JOptionPane.YES_OPTION) {
					String isbn = book.getTfISBN().getText();
					try {
						BookDB.getInstance().deleteBookByISBN(isbn);
						WinBookRemove.this.clearAll();
						JOptionPane.showMessageDialog(WinBookRemove.this, "삭제가 완료되었습니다");

					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
			}

		});

	}

}
