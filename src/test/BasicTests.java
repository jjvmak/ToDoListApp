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
		
		int lslen = gui.getListLenght();
		int tslen = back.getTasksLenght();
		
		gui.editorPane.setText("foo");
		gui.onSubmit();
		
		
		
		assertEquals(lslen+1, gui.getListLenght());
		assertEquals(tslen+1, back.getTasksLenght());
		
		gui.selectListItem(gui.getListLenght() - 1);
		gui.onDone();
		
		assertEquals(lslen, gui.getListLenght());
		assertEquals(tslen, back.getTasksLenght());
		
		
	}

}