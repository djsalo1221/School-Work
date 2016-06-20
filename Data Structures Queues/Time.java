
class Time {
	private int hour;
	private int minute;
	private boolean isMorning;

	public Time() {
		// constructor to initialize the time to default value

	}

	public Time(int h, int m, boolean morning) 
	{
		this.hour = h;
		this.minute = m;
		this.isMorning = morning;
	}

	public void setTime(int h, int m, boolean morning) 
	{
		this.hour = h;
		this.minute = m;
		this.isMorning = morning;
	}

	public int getHour() {
		return hour;
	}

	public int getMinute() {
		return minute;
	}

	public boolean isAM() {
		return isMorning;
	}
	
	
	/*
	*return the time string in the format of HH:MM XM
	*/
	public String toString() 
	{ 
		String hourString = "" + hour;
		String minString = "" + minute;
		String periodString = null;
		
		if ( hour <= 9)
			hourString = "0" + hour;
		if ( minute <= 9)
			minString = "0" + minute;
		if ( isMorning )
		{
			periodString = "AM";
			return hourString + ":" + minString + " " + periodString;	
		}
		
		periodString = "PM";
		return hourString + ":" + minString + " " + periodString;		
	}

	/*
	*create and return a Time object which is n
	*minutes from this time
	*/
	public Time plus(int n) 
	{ 
		boolean morning = true; 
		int minutes = totalMinutes() + n;
		int hours;
		
		if ( minutes >= 720)
			morning = false;
		
		hours = minutes / 60;
		minutes = minutes % 60;
		
		if ( hours > 12)
			hours = hours % 12;
		
		return new Time(hours, minutes, morning);			 
	}

	/*
	*how many minutes (nonnegative integer) between Time t and this time?
	*/
	public int timeDifference(Time t) 
	{		
		int hour = t.hour;
		int minuteSinceMng;
		int timeDifference;
		
		if ( !t.isMorning && t.hour != 12)
			hour += 12;	
		
		minuteSinceMng = hour * 60 + t.minute;
		
		timeDifference = Math.abs(minuteSinceMng - totalMinutes());
		
		return timeDifference;
	}
	
	/*
	*return an integer that is < 0, = 0 or > 0 depending on
	*whether this time is < (earlier than), = or > (later than) the parameter Time t
	*/
	public int compareTo(Time t) 
	{
		int greater = 0;
		int hour = t.hour;
		int minuteSinceMngForGivenTime;
		int minuteSinceMngthisTime;
		
		if (!t.isMorning && t.hour != 12)
			hour += 12;
		
		minuteSinceMngForGivenTime = hour * 60 + t.minute;

		minuteSinceMngthisTime = totalMinutes();

		if (minuteSinceMngthisTime < minuteSinceMngForGivenTime)
			greater = -1;
		else if (minuteSinceMngthisTime > minuteSinceMngForGivenTime)
			greater = 1;
		
		return greater;
	}

	/*
	*returns an int that is total amount of minutes since 12:00 am of this time.
	*This method is called internally by timeDifference and compareTo.
	*/
	private int totalMinutes() 
	{
		Time thisTime = new Time(this.hour, this.minute, this.isMorning);
		int hour = thisTime.hour;
		int totalMinutes;
		
		if (!thisTime.isMorning && thisTime.hour != 12)
			hour += 12;

		totalMinutes = hour * 60 + thisTime.minute;
		
		return totalMinutes;
	}
}