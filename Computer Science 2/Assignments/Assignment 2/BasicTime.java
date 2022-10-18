/*
    Assignment: Assignment #2
    Author:     Hunter Matthews
    NetID:      hjm210004
    Course:     CS 2336.503 (Computer Science II)
    Instructor: Dr. Mohamed Belkoura
    Date Due:   September 26th, 2022 at 11:59pm.
*/

public class BasicTime {
	// Variable Declarations:
	private int hour;
	private int minute;
	private boolean morningAfternoon;
	
	// Get Initialization:
	public int getHour(){ return this.hour; }
	public int getMinute(){ return this.minute; }
	public boolean getMorning(){ return this.morningAfternoon; }
	
	// Set Initialization:
	public void setHour(int hour){ this.hour = hour; }
	public void setMinute(int minute){ this.minute = minute; }
	public void setMorning(boolean morningAfternoon){ this.morningAfternoon = morningAfternoon; }
	
	/**
	 * Default Constructor
	 * initializes with the default midnight value
	 */
	public BasicTime() {
		this(12,0,true);
	}
	
	/**
	 * Constructor #1:
	 * @param hour: hour
	 * @param minute: minute
	 * @param morning: morning or evening
	 */
	public BasicTime(int hour, int minute, boolean morningAfternoon) {
		setHour(hour);
		setMinute(minute);
		setMorning(morningAfternoon);
	}
	
	/**
	 * Constructor #2
	 * @param time: represents the time in the HH:MMXX format.
	 */
	public BasicTime(String time) {
		this(Integer.parseInt(time.split(":",2)[0]), Integer.parseInt(time.split(":", 2)[1].substring(0,2)), ((time.split(":", 2)[1].substring(2,3).equalsIgnoreCase("A")) ? true : false));
	}
	
	/**
	 * Get String arrangement of BasicTime
	 */
	public String toString() {
		return (this.getHour() + ":" + (String.valueOf(this.getMinute()).length() == 1 ? ("0" + this.getMinute()) : this.getMinute()) + (this.morningAfternoon ? "AM" : "PM"));
	}
	
	/**
	 * Method #1
	 * adds two BasicTime objects via the non-static method.
	 */
	public BasicTime add(BasicTime obj1, BasicTime obj2) {
		return BasicTime.addTo(obj1, obj2);
	}
	
	/**
	 * Method #2
	 * adds two BasicTime objects via the static method.
	 */
	public static BasicTime addTo(BasicTime obj1, BasicTime obj2) {
		int hour = (obj1.getMorning() ? obj1.getHour() : (obj1.getHour() % 12)) + (obj2.getMorning() ? obj2.getHour() : (obj2.getHour() % 12));
		int min = obj1.getMinute() + obj2.getMinute();
		
		if(min >= 60) {
			hour++;
			min -= 60;
		}
		
		if(hour >= 24) {
			hour -= 24;
		}
		
		boolean morningAfternoon = (hour >= 0 && hour <= 12) ? true : false;
		
		BasicTime results = new BasicTime(hour % 12, min, morningAfternoon);
		
		return results;
	}
	
	/**
	 * Method #3
	 * subtracts two BasicTime objects via the non-static method.
	 */
	public BasicTime sub(BasicTime obj1, BasicTime obj2) {
		return BasicTime.subTo(obj1, obj2);
	}
	
	/**
	 * Method #4
	 * subtracts two BasicTime objects via the static method.
	 */
	public static BasicTime subTo(BasicTime obj1, BasicTime obj2) {
		int hour = (obj1.getMorning() ? obj1.getHour() : (obj1.getHour() % 12)) - (obj2.getMorning() ? obj2.getHour() : (obj2.getHour() % 12));
		int min = obj1.getMinute() - obj2.getMinute();
		
		if(min < 0) {
			min *= -1;
			hour--;
		}
		
		boolean morningAfternoon = (hour >= 0 && hour <= 12) ? true : false;
		BasicTime results = new BasicTime(hour, min, morningAfternoon);
		
		return results;
	}
}
