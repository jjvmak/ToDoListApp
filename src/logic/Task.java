package logic;

import java.io.Serializable;

public class Task implements Serializable {
	
	private String description;
	private int dd;
	private int mm;
	private int yy;
	
	public Task(String description, int dd, int mm, int yy) {
		this.description = description;
		this.dd =  dd;
		this.mm =  mm;
		this.yy = yy;
	}

	public String getDescription() {
		return description;
	}

	public int getDd() {
		return dd;
	}

	public int getMm() {
		return mm;
	}

	public int getYy() {
		return yy;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setDd(int dd) {
		this.dd = dd;
	}

	public void setMm(int mm) {
		this.mm = mm;
	}

	public void setYy(int yy) {
		this.yy = yy;
	}
	
	public String getDate() {
		
		if (dd < 10 && mm < 10) {
			String date = "0"+dd+".0"+mm+"."+yy;
			return date;
		}
		
		if (dd < 10 && mm >= 10) {
			String date = "0"+dd+"."+mm+"."+yy;
			return date;
		}
		
		if (dd >= 10 && mm < 10) {
			String date = dd+".0"+mm+"."+yy;
			return date;
		}
		
		else {
			String date = dd+"."+mm+"."+yy;
			return date;
		}
		
	}

	@Override
	public String toString() {
		return dd+"."+mm+"."+yy+" "+description;
	}
	
	
}
