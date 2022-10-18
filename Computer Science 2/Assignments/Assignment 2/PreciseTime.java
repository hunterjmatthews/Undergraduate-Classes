/*
    Assignment: Assignment #2
    Author:     Hunter Matthews
    NetID:      hjm210004
    Course:     CS 2336.503 (Computer Science II)
    Instructor: Dr. Mohamed Belkoura
    Date Due:   September 26th, 2022 at 11:59pm.
*/

public class PreciseTime {
	// Variables Declarations:
	private int hour; 
	private int minute; 
	private int seconds;
	private boolean morningAfternoon; 
		
	// Get Initialization:
	public int getHour(){ return this.hour; }
	public int getMinute(){ return this.minute; }
	public int getSeconds(){ return this.seconds; }
	public boolean getMorning(){ return this.morningAfternoon; }  
		
	// Set Initialization:
	public void setHour(int hour){ this.hour = hour;}
	public void setMinute(int minute){ this.minute = minute;}
	public void setSeconds(int seconds){ this.seconds = seconds;}
	public void setMorning(boolean morning){ this.morningAfternoon = morning;}  
	
	/**
	 * Default Constructor
	 * initializes with default midnight value
	 */
	public PreciseTime(){
		this(12, 0, 0, true);   
	}
	
	/**
	 * Constructor 1
	 * @param hour: hour
	 * @param minute: minute
	 * @param seconds: seconds
	 * @param morning: morning or evening
	 */
	public PreciseTime(int hour, int minute, int seconds, boolean morningAfternoon) {
		setHour(hour); 
		setMinute(minute); 
		setSeconds(seconds);
		setMorning(morningAfternoon);
	}
		
	/**
	 * Constructor 2
	 * @param time: represents the time in the HH:MM:SSXX format.
	 */
	public PreciseTime(String time) { 
		this(Integer.parseInt(time.substring(0,2)), Integer.parseInt(time.substring(3,5)), Integer.parseInt(time.substring(6,8)), ((time.substring(8,9).equalsIgnoreCase("A")) ? true : false)); 
	}
		
	/**
	 * Get the String arrangement of PreciseTime
	 */
	public String toString() { 
		int hour = this.getHour();
		String min = (String.valueOf(this.getMinute()).length() ==1 ? "0" + this.getMinute() : this.getMinute() + "").trim();
		String sec = (String.valueOf(this.getSeconds()).length() ==1 ? "0" + this.getSeconds() : this.getSeconds() + "").trim();

		return (hour + ":" + min + ":" + sec + ":" + (this.morningAfternoon ? "AM" : "PM"));
	}
	
    
    /**
     * Method 1:
     * adds two PreciseTime objects via the non-static method.
     */
    public PreciseTime add(PreciseTime obj1, PreciseTime obj2) {
    	return PreciseTime.addTo(obj1, obj2); 
    }
    
	/**
	 * Method 2: 
	 * adds two PreciseTime objects via the static method.
	 */
    public static PreciseTime addTo(PreciseTime obj1, PreciseTime obj2){
    	int hour = (obj1.getMorning() ?  obj1.getHour() : (obj1.getHour() % 12)) + (obj2.getMorning() ? obj2.getHour() : (obj2.getHour() % 12));
	    int min = obj1.getMinute() + obj2.getMinute(); 
	    int sec = obj1.getSeconds() + obj2.getSeconds(); 
	    	
	    if(sec >= 60) {
	    	min++; 
	    	sec -= 60; 
	    }
	    	
	    if(min >= 60) {
	    	hour++; 
	    	min -= 60;
	    }
	    	
	    if(hour >= 24) {
	    	hour -= 24;
	    }
	    
	    // Get morning or evening.
	    boolean morningAfternoon = (hour >= 0 && hour <= 12) ? true : false; 
	    	 
	    PreciseTime result = new PreciseTime(hour%12, min, sec, morningAfternoon);
	    
	    // Return the result.
	    return result;
    }
    
    /**
     * Method 3:
     * subtracts two PreciseTime objects via the non-static method.
     */
    public PreciseTime sub(PreciseTime obj1, PreciseTime obj2) {
    	return PreciseTime.subTo(obj1, obj2); 
    }
	
    /**
     * Method 4:
     * subtracts two PreciseTime objects via the static method.
     */
	public static PreciseTime subTo(PreciseTime obj1, PreciseTime obj2) {
		int hour = (obj1.getMorning() ?  obj1.getHour() : (obj1.getHour() % 12)) - (obj2.getMorning() ? obj2.getHour() : (obj2.getHour() % 12));
	    int min = obj1.getMinute() - obj2.getMinute(); 
	    int sec = obj1.getSeconds() - obj2.getSeconds(); 
	    	
	    if(sec < 0){
	    	sec *= -1; 
	    	min--;
	    }
	    	
	    if(min < 0){
	    	min *= -1; 
	    	hour--;  
	    } 
	    
	    boolean morningAfternoon = (hour >= 0 && hour <= 12) ? true : false; 
	    	 
	    PreciseTime result = new PreciseTime(hour, min, sec, morningAfternoon); 
	    return result;
	}
}
