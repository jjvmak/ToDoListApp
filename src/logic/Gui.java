package logic;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.JButton;
import java.awt.List;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import java.awt.Choice;
import java.awt.Font;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class Gui {

	private JFrame frame;
	private JPanel mainPanel;
	private List list;
	public JTextField editorPane;
	private JButton btnSubmit;
	private Back back;
	private JButton btnDone;
	private JLabel itemsOnListLabel;
	private JSpinner dd_spinner;
	private JSpinner mm_spinner;
	private JSpinner yy_spinner;
	private Choice choice;
	private Font font;
	private SpinnerNumberModel smDD;
	private SpinnerNumberModel smMM;
	private SpinnerNumberModel smYY;

	public Gui(Back back) {
		this.back = back;
		initialize();
		initSpinners();
		renderList();

	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 700, 479);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		mainPanel = new JPanel();
		mainPanel.setBounds(0, 0, 692, 452);
		frame.getContentPane().add(mainPanel);
		mainPanel.setLayout(null);

		list = new List();
		list.setBounds(10, 33, 672, 329);
		font = new Font("Areal", Font.BOLD,20);
		list.setFont(font);
		
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
		itemsOnListLabel.setBounds(487, 425, 195, 14);
		mainPanel.add(itemsOnListLabel);

		smDD = new SpinnerNumberModel(1, 1, 31, 1);
		smMM = new SpinnerNumberModel(1, 1, 12, 1);
		smYY = new SpinnerNumberModel(1, 1, 3000, 1);
		
		dd_spinner = new JSpinner(smDD);
		dd_spinner.setBounds(10, 423, 40, 18);
		mainPanel.add(dd_spinner);

		mm_spinner = new JSpinner(smMM);
		mm_spinner.setBounds(60, 423, 40, 18);
		mainPanel.add(mm_spinner);

		yy_spinner = new JSpinner(smYY);
		yy_spinner.setBounds(110, 423, 80, 18);
		mainPanel.add(yy_spinner);

		choice = new Choice();
		choice.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				renderList();
			}
		});

		choice.setBounds(10, 7, 90, 20);
		choice.add("All");
		choice.add("Today");
		choice.add("Coming");
		choice.add("Past");
		mainPanel.add(choice);
		
		JLabel lblNewLabel = new JLabel("Date: ");
		lblNewLabel.setBounds(10, 405, 46, 14);
		mainPanel.add(lblNewLabel);
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				onSubmit();
			}
		});
		frame.setVisible(true);
	}

	public void renderList() {

		String selected =  choice.getSelectedItem();
		System.out.println(selected);
		list.removeAll();
		ArrayList<Task> tmp = back.getTasks();
		
		switch (selected) {

		case "All":
			for (int i  = 0; i < tmp.size(); i++) {
				list.add(tmp.get(i).toString());
			}
			break;
			
		case "Today":
			for (int i  = 0; i < tmp.size(); i++) {
				if (tmp.get(i).getDate().equals(back.getDate())) {
					list.add(tmp.get(i).toString());
				}
			}
			break;
			
		case "Coming":
			for (int i  = 0; i < tmp.size(); i++) {
				if (tmp.get(i).getYy() > back.getCurrentYY() ||
					tmp.get(i).getYy() >= back.getCurrentYY() && tmp.get(i).getMm() > back.getCurrentMM() ||
					tmp.get(i).getYy() >= back.getCurrentYY() && tmp.get(i).getMm() >= back.getCurrentMM() && tmp.get(i).getDd() > back.getCurrentDD()) {
					
						list.add(tmp.get(i).toString());
				}
			}
			break;
			
		case "Past":
			for (int i  = 0; i < tmp.size(); i++) {
				if (tmp.get(i).getYy() < back.getCurrentYY() ||
					tmp.get(i).getYy() <= back.getCurrentYY() && tmp.get(i).getMm() < back.getCurrentMM() ||
					tmp.get(i).getYy() <= back.getCurrentYY() && tmp.get(i).getMm() <= back.getCurrentMM() && tmp.get(i).getDd() < back.getCurrentDD()) {
					
						list.add(tmp.get(i).toString());
				}
			}
			break;
			
		default:
			break;
		}
	}

	public void onSubmit() {

		if (editorPane.getText().length()>0) {
			Task task =  new Task(editorPane.getText(), (int) dd_spinner.getValue(), (int) mm_spinner.getValue(), (int) yy_spinner.getValue());
			back.addTask(task);
			back.serializeTaskList();	
			editorPane.setText("");
			
			initSpinners();
			renderList();
			setLabel();
		}
	}

	public void onDone() {

		if (isSomethingSelected()) {
			int selectedItem = list.getSelectedIndex();
			String dsc = list.getSelectedItem();
			back.removeTask(dsc);
			back.serializeTaskList();
			list.remove(selectedItem);
			
			initSpinners();
			renderList();
			setLabel();
		}
	}

	public boolean isSomethingSelected() {

		for (int i = 0; i < list.getItemCount(); i++) {
			if (list.isIndexSelected(i)) return true;
		}

		return false;
	}

	public void initSpinners() {
		String date = back.getDate();
		int dd = Integer.parseInt(date.substring(0, 2));
		int mm = Integer.parseInt(date.substring(3, 5));
		int yy = Integer.parseInt(date.substring(6, 10));
		dd_spinner.setValue(dd);
		mm_spinner.setValue(mm);
		yy_spinner.setValue(yy);
	}

	public String getSpinnersValue() {

		String date = dd_spinner.getValue()+"."+mm_spinner.getValue()+"."+yy_spinner.getValue();
		return date;

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
