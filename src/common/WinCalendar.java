package common;
import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.time.YearMonth;
import java.util.Calendar;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Cursor;
import java.awt.Dimension;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Component;

import javax.swing.BoxLayout;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class WinCalendar extends JDialog {
	private JComboBox<Integer> cbMonth;
	private JPanel panel_calendar;
	private JComboBox<Integer> cbYear;
	private Calendar cal;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WinCalendar dialog = new WinCalendar();
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
	public WinCalendar() {
		setTitle("달력");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

		JPanel panel = new JPanel();
		panel.setMaximumSize(new Dimension(32767, 20));
		getContentPane().add(panel);

		cbYear = new JComboBox<>();
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		for (int i = year - 100; i < year + 100; i++) {
			cbYear.addItem(i);
		}

		cbYear.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				showCalendar();
			}
		});

		JButton btnChangeYearMinus = new JButton("<<");
		btnChangeYearMinus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int cur = cbYear.getSelectedIndex();
				if (cur != 0) {
					cbYear.setSelectedIndex((cur - 1));
				}
			}
		});
		panel.add(btnChangeYearMinus);

		JButton btnChangeMonMinus = new JButton("<");
		btnChangeMonMinus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int cur = cbMonth.getSelectedIndex();
				if (cur - 1 < 0) {
					cbMonth.setSelectedIndex(11);
					if (cbYear.getSelectedIndex() != 0) {
						cbYear.setSelectedIndex(cbYear.getSelectedIndex() - 1);
					}
				} else {
					cbMonth.setSelectedIndex(cur - 1);
				}

			}
		});
		panel.add(btnChangeMonMinus);

		panel.add(cbYear);

		JLabel lblYear = new JLabel("년");
		panel.add(lblYear);

		cbMonth = new JComboBox<>();
		cbMonth.setModel(new DefaultComboBoxModel<>(new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 }));
		int month = cal.get(Calendar.MONTH);
		cbMonth.setSelectedItem(month + 1);
		cbMonth.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				showCalendar();
			}
		});
		panel.add(cbMonth);

//		JButton btnRun = new JButton("실행");
//		btnRun.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//		btnRun.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				showCalendar();
//			}
//		});

		JLabel lblMonth = new JLabel("월");
		panel.add(lblMonth);
//		panel.add(btnRun);

		JPanel panel_days = new JPanel();
		panel_days.setSize(new Dimension(0, 15));
		panel_days.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_days.setMaximumSize(new Dimension(32767, 15));
		getContentPane().add(panel_days);
		panel_days.setLayout(new GridLayout(0, 7, 0, 0));

		JLabel lblNewLabel = new JLabel("일");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel_days.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("월");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel_days.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("화");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		panel_days.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("수");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		panel_days.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("목");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		panel_days.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("금");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		panel_days.add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("토");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		panel_days.add(lblNewLabel_6);

		panel_calendar = new JPanel();
		getContentPane().add(panel_calendar);
		panel_calendar.setLayout(new GridLayout(0, 7, 0, 0));

		cbYear.setSelectedItem(year);

		JButton btnChangeMonPlus = new JButton(">");
		btnChangeMonPlus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int cur = cbMonth.getSelectedIndex();
				int later = (cur + 1);
				if (later > 11) {
					cbMonth.setSelectedIndex(later % 12);
					if (cbYear.getSelectedIndex() != cbYear.getModel().getSize() - 1) {
						cbYear.setSelectedIndex(cbYear.getSelectedIndex() + 1);
					}
				} else {
					cbMonth.setSelectedIndex(later);
				}

			}
		});
		panel.add(btnChangeMonPlus);

		JButton btnChangeYearPlus = new JButton(">>");
		btnChangeYearPlus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int cur = cbYear.getSelectedIndex();

				if (cbYear.getModel().getSize() - 1 != cur) {
					cbYear.setSelectedIndex(cur + 1);
				}
			}
		});
		panel.add(btnChangeYearPlus);

		showCalendar();

	}

	private void showCalendar() {
		// TODO Auto-generated method stub

		panel_calendar.removeAll();
		panel_calendar.revalidate();
		panel_calendar.repaint();

		cal = Calendar.getInstance();
		// cal.clear();
		int year = (int) cbYear.getSelectedItem();
		int month = (int) cbMonth.getSelectedItem();
		int today = cal.get(Calendar.DATE);
		YearMonth ym = YearMonth.of(year, month);
		cal.set(year, month - 1, 0);

		int day = cal.get(Calendar.DAY_OF_WEEK) % 7;
		int days = ym.lengthOfMonth();

		for (int i = 0; i < day; i++) {
			panel_calendar.add(new JLabel());
		}

		JButton j;
		for (int i = 1; i <= days; i++) {
			j = new JButton((String.valueOf(i)));
			j.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			if ((day + i - 1) % 7 == 0 || (day + i - 1) % 7 == 6) {
				j.setForeground(Color.RED);
			}

			j.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					cal.set(year, month, Integer.parseInt(((JButton) e.getSource()).getText()));
					dispose();
				}

			});

			panel_calendar.add(j);
		}
		Component[] child = panel_calendar.getComponents();
		child[today + 1].setBackground(new Color(255, 255, 0));

	}

	public String getDate() {
		String result = "%d-%02d-%02d";
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
		int date = cal.get(Calendar.DATE);

		return String.format(result, year, month, date);
	}
}
