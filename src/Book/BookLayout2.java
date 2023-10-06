package Book;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Dimension;

public class BookLayout2 extends JPanel {
	protected JLabel lblPic;
	protected JTextField tfISBN;
	protected JTextField tfTitle;
	protected JTextField tfAuthor;
	protected JTextField tfPublisher;
	protected JTextField tfPdate;
	protected JTextField tfDiscount;
	protected JTextArea taDescription;
	protected JButton btnCalendar;
	protected JButton btnAdd;
	protected JButton btnRemove;
	protected JButton btnUpdate;
	protected JTextField tfPicURL;
	protected JLabel lblPicURL;
	protected JButton btnDuplicate;
	
	public BookLayout2() {
		setMinimumSize(new Dimension(525, 470));
		setLayout(null);
		
		lblPic = new JLabel("책표지(170*200)");
		lblPic.setOpaque(true);
		lblPic.setBackground(Color.ORANGE);
		lblPic.setBounds(12, 20, 170, 200);
		add(lblPic);
		
		JLabel lblISBN = new JLabel("ISBN:");
		lblISBN.setBounds(208, 22, 57, 15);
		add(lblISBN);
		
		JLabel lblTitle = new JLabel("제목");
		lblTitle.setBounds(208, 59, 57, 15);
		add(lblTitle);
		
		JLabel lblAuthor = new JLabel("저자:");
		lblAuthor.setBounds(208, 96, 57, 15);
		add(lblAuthor);
		
		JLabel lblPublisher = new JLabel("출판사");
		lblPublisher.setBounds(208, 133, 57, 15);
		add(lblPublisher);
		
		JLabel lblPdate = new JLabel("출판일");
		lblPdate.setBounds(208, 170, 57, 15);
		add(lblPdate);
		
		JLabel lblDiscount = new JLabel("가격");
		lblDiscount.setBounds(208, 207, 57, 15);
		add(lblDiscount);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 273, 496, 145);
		add(scrollPane);
		
		taDescription = new JTextArea();
		taDescription.setLineWrap(true);
		scrollPane.setViewportView(taDescription);
		
		tfISBN = new JTextField();
		tfISBN.setBounds(277, 19, 116, 21);
		add(tfISBN);
		tfISBN.setColumns(10);
		
		tfTitle = new JTextField();
		tfTitle.setBounds(277, 56, 231, 21);
		add(tfTitle);
		tfTitle.setColumns(10);
		
		tfAuthor = new JTextField();
		tfAuthor.setColumns(10);
		tfAuthor.setBounds(277, 93, 231, 21);
		add(tfAuthor);
		
		tfPublisher = new JTextField();
		tfPublisher.setColumns(10);
		tfPublisher.setBounds(277, 130, 231, 21);
		add(tfPublisher);
		
		tfPdate = new JTextField();
		tfPdate.setColumns(10);
		tfPdate.setBounds(277, 167, 116, 21);
		add(tfPdate);
		
		tfDiscount = new JTextField();
		tfDiscount.setColumns(10);
		tfDiscount.setBounds(277, 204, 116, 21);
		add(tfDiscount);
		
		btnCalendar = new JButton("달력보기");
		btnCalendar.setBounds(411, 166, 97, 23);
		add(btnCalendar);
		btnCalendar.setVisible(false);
		
		btnAdd = new JButton("도서 등록");
		btnAdd.setBounds(60, 428, 97, 23);
		add(btnAdd);
		
		btnRemove = new JButton("도서 삭제");
		btnRemove.setBounds(215, 428, 97, 23);
		add(btnRemove);
		
		btnUpdate = new JButton("도서 변경");
		btnUpdate.setBounds(370, 428, 97, 23);
		add(btnUpdate);
		
		lblPicURL = new JLabel("사진URL");
		lblPicURL.setVisible(true);
		lblPicURL.setBounds(12, 243, 57, 15);
		add(lblPicURL);
		
		tfPicURL = new JTextField();
		tfPicURL.setVisible(false);
		tfPicURL.setBounds(91, 240, 417, 21);
		add(tfPicURL);
		tfPicURL.setColumns(10);
		
		btnDuplicate = new JButton("중복확인");
		btnDuplicate.setBounds(411, 18, 97, 23);
		add(btnDuplicate);

		btnAdd.setVisible(false);
		btnRemove.setVisible(false);
		btnUpdate.setVisible(false);
		btnDuplicate.setVisible(false);
	}
	public JTextField getTfISBN() {
		return tfISBN;
	}

	public JTextField getTfTitle() {
		return tfTitle;
	}

	public JTextField getTfAuthor() {
		return tfAuthor;
	}

	public JTextField getTfPublisher() {
		return tfPublisher;
	}

	public JTextField getTfPdate() {
		return tfPdate;
	}

	public JTextField getTfDiscount() {
		return tfDiscount;
	}

	public JLabel getLblPic() {
		return lblPic;
	}
	
	public JTextArea getTaDescription() {
		return taDescription;
	}

	public JButton getBtnCalendar() {
		return this.btnCalendar;
	}
	public JButton getBtnAdd() {
		return this.btnAdd;
	}
	public JButton getBtnRemove() {
		return this.btnRemove;
	}
	public JButton getBtnUpdate() {
		return this.btnUpdate;
	}
	
	public JLabel getLblPicURL() {
		return lblPicURL;
	}
	public JTextField getTfPicURL() {
		return tfPicURL;
	}
	
	public JButton getBtnDuplicate() {
		return btnDuplicate;
	}
}
