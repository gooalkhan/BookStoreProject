package Member;

import java.util.HashMap;

import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JPanel;

import common.NoConditionSelectedException;
import common.Searchable;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.JComboBox;

public class PanelMemberSearch extends JPanel implements Searchable {
	private JTextField tfName;
	private JTextField tfMobile;
	private JComboBox<Enum> cbGrade;
	private JButton btnSearch;
	private JCheckBox chckbxName;
	private JCheckBox chckbxMobile;
	private JCheckBox chckbxGrade;

	public PanelMemberSearch(ComboBoxModel<Enum> model) {
		this();
		cbGrade.setModel(model);
		
	}
	
	public PanelMemberSearch() {
		
		chckbxName = new JCheckBox("이름");
		add(chckbxName);
		
		tfName = new JTextField();
		add(tfName);
		tfName.setColumns(10);
		
		chckbxMobile = new JCheckBox("전화번호");
		add(chckbxMobile);
		
		tfMobile = new JTextField();
		add(tfMobile);
		tfMobile.setColumns(10);
		
		chckbxGrade = new JCheckBox("등급");
		add(chckbxGrade);
		
		cbGrade = new JComboBox<>();
		add(cbGrade);
		
		btnSearch = new JButton("검색");
		add(btnSearch);
		
	}
	
	@Override
	public JButton getBtnSearch() {
		return this.btnSearch;
	}

	@Override
	public String getSearchWord() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public HashMap<String, String> getSearchWordAndConditions() throws NoConditionSelectedException {
		HashMap<String, String> map = new HashMap<String, String>();
		
		if (chckbxName.isSelected() || chckbxMobile.isSelected() || chckbxGrade.isSelected()) {
			if (chckbxName.isSelected()) map.put("name" , tfName.getText());
			if (chckbxMobile.isSelected()) map.put("mobile" , tfMobile.getText());
			if (chckbxGrade.isSelected()) map.put("grad" , cbGrade.getSelectedItem().toString());
		} else {
			throw new NoConditionSelectedException("조건이 하나도 선택되지 않았습니다");
		}
		
		return map;
	}

	@Override
	public Object getSelectedCondition() {
		// TODO Auto-generated method stub
		return null;
	}

}
