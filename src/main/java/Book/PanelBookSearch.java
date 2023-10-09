package Book;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import common.NoConditionSelectedException;
import common.Searchable;

import java.util.HashMap;

import javax.swing.ComboBoxModel;
import javax.swing.JButton;

public class PanelBookSearch extends JPanel implements Searchable {
	protected JTextField tfSearch;
	protected JComboBox<Enum> cbCondition;
	protected JButton btnSearch;
	
	public PanelBookSearch(ComboBoxModel<Enum> model) {
		this();
		setModel(model);
	}
	
	public PanelBookSearch() {
		
		JLabel lblSearchWord = new JLabel("검색어");
		add(lblSearchWord);
		
		cbCondition = new JComboBox<>();
		add(cbCondition);
		
		tfSearch = new JTextField();
		add(tfSearch);
		tfSearch.setColumns(10);
		
		btnSearch = new JButton("검색");
		add(btnSearch);
	}
	
	public void setModel(ComboBoxModel<Enum> model) {
		this.cbCondition.setModel(model);
	}
	
	public JButton getBtnSearch() {
		return this.btnSearch;
	}
	
	public String getSearchWord() {
		return this.tfSearch.getText();
	}
	
	public Object getSelectedCondition() {
		return this.cbCondition.getSelectedItem();
	}

	@Override
	public HashMap<String, String> getSearchWordAndConditions() throws NoConditionSelectedException {
		// TODO Auto-generated method stub
		return null;
	}
	

}
