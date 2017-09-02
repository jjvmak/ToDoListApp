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
	
	private ArrayList<String> tasks = new ArrayList<>();
	
	public Back() {
		gui = new Gui(this);
		System.out.println(dtf.format(localDate));
		deSerializeReservationsList();
		loadTasks();
		gui.setLabel();
	}
	
	public static void main(String[] args) {
		new Back();
	}
	
	public String getDate() {
		return dtf.format(localDate);
	}
	
	public void addTask(String s) {
		tasks.add(s);
	}
	
	public void removeTask(int i) {
		tasks.remove(i);
	}
	
	public void loadTasks() {
		for (int i = 0; i < tasks.size(); i++) {
			gui.loadTask(tasks.get(i));
		}
	}
	
	public void serializeTaskList() {
		FileOutputStream fileOut;
		try {
			fileOut = new FileOutputStream("tasks.ser");
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
			FileInputStream fileIn = new FileInputStream("tasks.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			tasks = (ArrayList<String>) in.readObject();
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
}
