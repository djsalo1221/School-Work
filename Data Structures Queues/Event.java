

class Event implements Comparable<Event>
{
	Customer customer;// Customer for which the event is performed
	Time eventTime;// Member to hold event time
	char eventType;// 'N' for new customer entering the bank,'D' this customer’s transaction is done	
	
	/*
	*Return an integer that is < 0, = 0 or > 0 depending on
	*whether this Event is <, = or > the parameter Event e
	*based on their event time. Note that when two events
	*have the same event time, use customer’s ID to
	*determine the order.
	*/
	public int compareTo(Event e)
	{ 
		if ( eventTime.compareTo(e.eventTime) == 0)
		{
			if ( customer.getID() < e.getCustomer().getID())
				return -1;
			return 1;
		}
		
		return eventTime.compareTo(e.eventTime); 
	}

	/**
	 * @return the customer
	 */
	public Customer getCustomer() 
	{
		return customer;
	}

	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(Customer customer) 
	{
		this.customer = customer;
	}

	/**
	 * @return the eventTime
	 */
	public Time getEventTime() 
	{
		return eventTime;
	}

	/**
	 * @param eventTime the eventTime to set
	 */
	public void setEventTime(Time eventTime) 
	{
		this.eventTime = eventTime;
	}

	/**
	 * @return the eventType
	 */
	public char getEventType() 
	{
		return eventType;
	}

	/**
	 * @param eventType the eventType to set
	 */
	public void setEventType(char eventType){
		this.eventType = eventType;
	}
}