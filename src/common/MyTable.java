package common;

import java.awt.Component;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

public abstract class MyTable extends JTable {
	protected String[] header;
	protected DefaultTableModel model;
	
	public MyTable() {
		setEnabled(true);
		header = initHeader();
		model = initModel(header);
		setModel(model);
		
		new PopUpFactory(this);
	}

	public void clearTable() {
		model.setNumRows(0);
	}
	
	public void addRows(Vector<Object[]> rows) {
		clearTable();
		for (Object[] row : rows) {
			model.addRow(row);
		}
	}

	// public void addVectorizedRows(Vector<Vector<Object>> rows) {
	// 	Vector<Object[]> rowDataVector = new Vector<>();
	// 	for (int i = 0; i < model.getRowCount(); i++) {
    //         Vector<Object> rowVector = new Vector<>();
    //         for (int j = 0; j < model.getColumnCount(); j++) {
    //             rowVector.add(model.getValueAt(i, j));
    //         }
    //         rowDataVector.add(rowVector.toArray());
    //     }
	// }
	
	public void resizeColumnWidth(JTable table) {
		final TableColumnModel columnModel = table.getColumnModel();
		for (int column = 0; column < table.getColumnCount(); column++) {
			int width = 15; // Min width
			for (int row = 0; row < table.getRowCount(); row++) {
				TableCellRenderer renderer = table.getCellRenderer(row, column);
				Component comp = table.prepareRenderer(renderer, row, column);
				width = Math.max(comp.getPreferredSize().width + 1, width);
			}
			if (width > 300)
				width = 300;
			columnModel.getColumn(column).setPreferredWidth(width);
		}
	}
	
	protected abstract DefaultTableModel initModel(String[] header);
	
	protected abstract String[] initHeader();
	
	public abstract void openJDialog(JDialogType j);
	
}
