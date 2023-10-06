package common;

import java.util.HashMap;

import javax.swing.JButton;

public interface Searchable {
	public JButton getBtnSearch();
	
	public String getSearchWord();
	
	public Object getSelectedCondition();
	
	public HashMap<String, String> getSearchWordAndConditions() throws NoConditionSelectedException;
}
