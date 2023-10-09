package Book;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Date;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

import javax.swing.InputVerifier;
import javax.swing.JButton;
import javax.swing.JComponent;

public class WinBookAdd extends Book {
	JButton btnDuplicate;
	JTextField tfISBN;
	JTextField tfPicURL;
	JButton btnAdd;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WinBookAdd dialog = new WinBookAdd();
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
	
	public WinBookAdd() {
		super();
		init();
	}
	
	private void init() {
		setTitle("도서 등록창");
		
		btnDuplicate = book.getBtnDuplicate();
		tfISBN = book.getTfISBN();
		btnAdd = book.getBtnAdd();
		tfPicURL = book.getTfPicURL();
		
		///여기까지 멤버생성
		
		book.getBtnCalendar().setVisible(true);
		book.getLblPicURL().setVisible(true);
		tfPicURL.setVisible(true);
		btnAdd.setVisible(true);
		btnDuplicate.setVisible(true);
		this.disableAll();
		
		///여기까지 불필요 콤포넌트 비활성처리
		
		btnDuplicate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String isbn = tfISBN.getText();
				
				try {
					Object[] nullData = BookDB.getInstance().searchBook(isbn);
					if (nullData == null) {
						WinBookAdd.this.enableAll();
					} else {
						JOptionPane.showMessageDialog(WinBookAdd.this, "중복 ISBN이 있습니다");
					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		});
		
		btnAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				addBook();
			}
			
		});

		///여기까지 리스너
		
		tfISBN.setInputVerifier(new InputVerifier() {

			@Override
			public boolean verify(JComponent input) {
				// TODO Auto-generated method stub
				JTextComponent jtc = (JTextComponent)input;
				if (jtc.getText().length() == 13) {
					return true;
				} else {
					JOptionPane.showMessageDialog(WinBookAdd.this, "유효한 ISBN이 아닙니다");
					return false;
				}
			}
			
		});
		
		///여기까지 입력검증
		
	}
	
	private void addBook() {
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
			BookDB.getInstance().addBook(row);
			JOptionPane.showMessageDialog(this, "도서가 추가되었습니다");
			this.clearAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
