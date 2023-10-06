package Book;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.text.JTextComponent;

import common.WinCalendar;

import javax.swing.BoxLayout;
import javax.swing.JButton;

public class Book extends JDialog {
	protected BookLayout book;
	
	public Book(String isbn) throws SQLException {
		this(BookDB.getInstance().searchBook(isbn));

	}
	
	public Book(Object[] row) {
		this();
		setText(book.getTfISBN(), row[0].toString());
		setText(book.getTfTitle(),row[1].toString());
		setText(book.getTfAuthor(), row[2].toString());
		setText(book.getTfPublisher(), row[3].toString());
		Date d = (Date)row[4];
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		setText(book.getTfPdate(), format.format(d));
		setText(book.getTaDescription(), row[5].toString());
		setText(book.getTfDiscount(), row[6].toString());
		setLblPic(book.getLblPic(), row[7].toString());
		setText(book.getTfPicURL(), row[7].toString());
	}
	
	public Book() {
		setBounds(100, 100, 550, 550);
		
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
		book = new BookLayout();
		getContentPane().add(book);
		
		JButton btnCalendar = book.getBtnCalendar();
		btnCalendar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				WinCalendar winCalendar = new WinCalendar();
				winCalendar.setModal(true);
				winCalendar.setVisible(true);
				book.getTfPdate().setText(winCalendar.getDate());
			}
			
		});

	}
	
	private void setText(JTextComponent comp, String text) {
		comp.setText(text);
		comp.setCaretPosition(0);
	}
	
	private void setLblPic(JLabel lblPic, String link) {
		String sPic = "<html><img src='" + link + "' width=170 height=200></html>";
		lblPic.setText(sPic);
	}
	
	protected void clearAll() {
		book.getTfISBN().setText("");
		book.getTfTitle().setText("");
		book.getTfAuthor().setText("");
		book.getTfPublisher().setText("");
		book.getTfPdate().setText("");
		book.getTaDescription().setText("");
		book.getTfDiscount().setText("");
		book.getLblPic().setText("");
		book.getTfPicURL().setText("");
	}
	
	protected void disableAll() {
		setEnableAll(false);
	}
	
	protected void enableAll() {
		setEnableAll(true);
	}
	
	private void setEnableAll(boolean b) {
		book.getTfTitle().setEnabled(b);
		book.getTfAuthor().setEnabled(b);
		book.getTfPublisher().setEnabled(b);
		book.getTfPdate().setEnabled(b);
		book.getTaDescription().setEnabled(b);
		book.getTfDiscount().setEnabled(b);
		book.getTfPicURL().setEnabled(b);
	}
}
