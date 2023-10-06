package Book;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BookTab extends BookLayout {
	BookTab(Object[] row) {
		this();
		setBook(row);
	}
	
	public BookTab() {
		lblPicURL.setText("도서 소개");
	}
	
	public void setBook(Object[] row) {
		tfISBN.setText(row[0].toString());
		tfTitle.setText(row[1].toString());
		tfAuthor.setText(row[2].toString());
		tfPublisher.setText(row[3].toString());
		Date d = (Date)row[4];
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		tfPdate.setText(format.format(d));
		taDescription.setText(row[5].toString());
		tfDiscount.setText(row[6].toString());
		
		String sPic = "<html><img src='" + row[7].toString() + "' width=170 height=200></html>";
		
		lblPic.setText(sPic);
	}
}
