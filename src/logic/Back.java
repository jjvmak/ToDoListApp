package logic;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Back {

	private Gui gui;
	private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd"+"."+"MM"+"."+"yyyy");
	private LocalDate localDate = LocalDate.now();

	private int currentDD;
	private int currentMM;
	private int currentYY;

	private ArrayList<Task> tasks = new ArrayList<Task>();

	public Back() {
		deSerializeReservationsList();
		gui = new Gui(this);
		System.out.println(dtf.format(localDate));
		
		
		gui.setLabel();

		currentDD = Integer.parseInt(getDate().substring(0, 2));
		currentMM = Integer.parseInt(getDate().substring(3, 5));
		currentYY = Integer.parseInt(getDate().substring(6, 10));

	}

	public static void main(String[] args) {
		new Back();
	}

	public String getDate() {
		return dtf.format(localDate);
	}

	public void addTask(Task t) {
		tasks.add(t);
		System.out.println(t.toString());
	}

	public void removeTask(int i) {
		tasks.remove(i);
	}
	
	public void removeTask(String s) {
		for (int i = 0; i < tasks.size(); i++) {
			if (tasks.get(i).toString().equals(s)) {
				tasks.remove(i);
			}
		}
	}

	public void loadTasks() {
		for (int i = 0; i < tasks.size(); i++) {
			gui.loadTask(tasks.get(i).toString());
		}
	}

	public void serializeTaskList() {
		FileOutputStream fileOut;
		try {
			fileOut = new FileOutputStream("tasksNew.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(tasks);
			out.close();
			fileOut.close();

		} catch (Exception e) {
			e.printStackTrace();
		}	
	}

	public void deSerializeReservationsList() {
		try {
			FileInputStream fileIn = new FileInputStream("tasksNew.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			tasks = (ArrayList<Task>) in.readObject();
			in.close();
			fileIn.close();
		}catch(IOException i) {
			System.out.println("The file is not yet created.");

		}catch(ClassNotFoundException c) {

			c.printStackTrace();
		}
	}

	public Gui getGui() {
		return this.gui;
	}

	public int getTasksLenght() {
		return tasks.size();
	}

	public ArrayList<Task> getTasks() {
		return tasks;
	}
	
	public int getCurrentDD() {
		return currentDD;
	}

	public int getCurrentMM() {
		return currentMM;
	}

	public int getCurrentYY() {
		return currentYY;
	}
}
