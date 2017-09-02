package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import logic.Back;
import logic.Gui;

public class BasicTests {
	
	private Back back;
	private Gui gui;
	
	@Before
	public void init() {
		back = new Back();
		gui = back.getGui();
	}

	@Test
	public void test() {
		
		gui.editorPane.setText("foo");
		gui.onSubmit();
		
		assertEquals(1, gui.getListLenght());
		assertEquals(1, back.getTasksLenght());
		
		gui.selectListItem(0);
		gui.onDone();
		
		assertEquals(0, gui.getListLenght());
		assertEquals(0, back.getTasksLenght());
		
		
	}

}