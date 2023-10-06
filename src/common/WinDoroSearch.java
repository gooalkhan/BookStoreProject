package common;
import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.mysql.cj.jdbc.MysqlDataSource;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import javax.swing.JList;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class WinDoroSearch extends JDialog {
	private JTextField tfDoro;
	static MysqlDataSource dataSource;
	private JList<String> list;
	private String strAddress;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		init();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WinDoroSearch dialog = new WinDoroSearch();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static void init() {
		dataSource = new MysqlDataSource();
		dataSource.setUrl("jdbc:mysql://localhost:3306/icidb");
		dataSource.setUser("root");
		dataSource.setPassword("1234");
	}

	/**
	 * Create the dialog.
	 */
	public WinDoroSearch(MysqlDataSource dataSource) {
		this();
		WinDoroSearch.dataSource = dataSource;
	}
	
	public WinDoroSearch() {
		init();
		setTitle("도로명 검색");
		setBounds(100, 100, 450, 300);

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);

		JLabel lblDoro = new JLabel("도로명");
		panel.add(lblDoro);

		tfDoro = new JTextField();
		tfDoro.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					searchDoro();
				}
			}
		});
		panel.add(tfDoro);
		tfDoro.setColumns(10);

		JButton btnSearch = new JButton("검색");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchDoro();
			}
		});
		panel.add(btnSearch);

		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));

		JScrollPane scrollPane = new JScrollPane();
		panel_1.add(scrollPane);

		list = new JList<>();
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				strAddress = list.getSelectedValue().toString();
				dispose();
			}
		});
		scrollPane.setViewportView(list);

	}

	protected void searchDoro() {
		String doroName = tfDoro.getText();
		Vector<String> al = new Vector<String>();
		try (Connection conn = dataSource.getConnection(); Statement stmt = conn.createStatement();) {
			
			String sql = "select * from addresstbl where doro='%s';";
			sql = String.format(sql, doroName);
			
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				String si = rs.getString("si");
				String gu = rs.getString("gu");
				String dong = rs.getString("dong");
				String doro = rs.getString("doro");
				al.add(si + " " + gu + " " + dong + " " + doro);
			}
		
			list.setListData(al);
			
		} catch (SQLException e) {

		}
	}
	
	public String getAddress() {
		return strAddress;
	}

}
