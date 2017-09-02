package logic;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class Gui {

	private JFrame frame;
	private JPanel mainPanel;
	private List list;
	public JTextField editorPane;
	private JButton btnSubmit;
	private Back back;
	private JButton btnDone;
	private JLabel itemsOnListLabel;

	public Gui(Back back) {
		this.back = back;
		initialize();
		
	}
	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 700, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		mainPanel = new JPanel();
		mainPanel.setBounds(0, 0, 692, 423);
		frame.getContentPane().add(mainPanel);
		mainPanel.setLayout(null);
		
		list = new List();
		list.setBounds(10, 24, 672, 338);
		mainPanel.add(list);
		
		
		editorPane = new JTextField();
		editorPane.setBounds(10, 373, 467, 28);
		mainPanel.add(editorPane);
		
		btnSubmit = new JButton("Add");
		btnSubmit.setBounds(487, 373, 91, 29);
		mainPanel.add(btnSubmit);
		
		btnDone = new JButton("Done");
		btnDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onDone();
			}
		});
		btnDone.setBounds(588, 373, 91, 29);
		mainPanel.add(btnDone);
		
		itemsOnListLabel = new JLabel("New label");
		itemsOnListLabel.setBounds(10, 4, 672, 14);
		mainPanel.add(itemsOnListLabel);
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				onSubmit();
			}
		});
		frame.setVisible(true);
	}
	
	public void onSubmit() {
		
		if (editorPane.getText().length()>0) {
			String text = back.getDate()+" "+editorPane.getText();
			list.add(text);
			editorPane.setText("");
			setLabel();
			back.addTask(text);
			back.serializeTaskList();
		}
		
		
	}
	
	public void onDone() {
		
		if (isSomethingSelected()) {
			int selectedItem = list.getSelectedIndex();
			list.remove(selectedItem);
			setLabel();
			back.removeTask(selectedItem);
			back.serializeTaskList();
		}
	}
	
	public boolean isSomethingSelected() {
		
		for (int i = 0; i < list.getItemCount(); i++) {
			if (list.isIndexSelected(i)) return true;
		}
		
		return false;
	}
	
	public void setLabel() {
		itemsOnListLabel.setText("Total: "+list.getItemCount()+" tasks");
	}
	
	public void loadTask(String s) {
		list.add(s);
	}
	
	public int getListLenght() {
		return list.getItemCount();
	}
	
	public void selectListItem(int i) {
		list.select(i);
	}

	
}
