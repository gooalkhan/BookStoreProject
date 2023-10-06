package Member;

import java.awt.Component;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import common.MyTraversalPolicy;
import common.OpenAndSetPic;

public abstract class WinMember extends JDialog {
	protected Member member;
	protected String imgname;

	/**
	 * Create the dialog.
	 */
	public WinMember() {
		member = new Member();
		init();
	}

	public WinMember(Object[] row) {
		member = new Member(row);
		init();
	}

	private void init() {
		setBounds(100, 100, 690, 360);
		getContentPane().add(member);
		this.setFocusTraversalPolicy(new MyTraversalPolicy(initFocusComponent()));
	
	}
	
	protected abstract Component[] initFocusComponent();
}
