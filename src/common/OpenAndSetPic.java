package common;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.filechooser.FileNameExtensionFilter;

import Member.Member;

public class OpenAndSetPic extends MouseAdapter {
	private final String savePath = Member.imgFilePath;
	private JLabel lblPic;
	
	public OpenAndSetPic(JLabel lblPic) {
		this.lblPic = lblPic;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
			JFileChooser chooser = new JFileChooser();
			chooser.setFileFilter(new FileNameExtensionFilter("Image Files", "jpg", "png", "tif"));
			int result = chooser.showOpenDialog(lblPic);
			if (result == JFileChooser.APPROVE_OPTION) {
				File f = chooser.getSelectedFile();
				String imgpath = f.getAbsolutePath();
				
				//클래스패스에 복사는 지원안함
				try {
					Files.copy(f.toPath(), new File(savePath+f.getName()).toPath(), StandardCopyOption.REPLACE_EXISTING);
					lblPic.setName(f.getName());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				//URL url = WinMember.class.getResource("images/books.jpg");
				//System.out.println(url.getPath());
				//File folder = new File(url.getPath());  
				
				//라벨 보여주기
				Toolkit tk = Toolkit.getDefaultToolkit();

				Image img = tk.getImage(imgpath);

				try {
					BufferedImage img2 = ImageIO.read(f);
					int height = img2.getHeight();
					int width = img2.getWidth();
					double scale = Double.valueOf(lblPic.getWidth()) / Double.valueOf(width);

					img = img.getScaledInstance(lblPic.getWidth(), (int) (height * scale), Image.SCALE_SMOOTH);
					lblPic.setIcon(new ImageIcon(img));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
	}
}
