class Customer {
	
	private int ID;//id of the customer
	private Time arriveTime; // the customer arrive time
	private Time serviceTime; // the time that the customer get served
	private Time completeTime; // the time that the transaction completes
	private int transaction; // the amount of time (in minutes) required
	private int waiting ;
	// for the transaction
	
	/**
	 * @return the iD
	 */
	public int getID() {
		return ID;
	}
	/**
	 * @param iD the iD to set
	 */
	public void setID(int iD) {
		ID = iD;
	}
	/**
	 * @return the arriveTime
	 */
	public Time getArriveTime() {
		return arriveTime;
	}
	/**
	 * @param arriveTime the arriveTime to set
	 */
	public void setArriveTime(Time arriveTime) {
		this.arriveTime = arriveTime;
	}
	/**
	 * @return the serviceTime
	 */
	public Time getServiceTime() {
		return serviceTime;
	}
	/**
	 * @param serviceTime the serviceTime to set
	 */
	public void setServiceTime(Time serviceTime) {
		this.serviceTime = serviceTime;
	}
	/**
	 * @return the completeTime
	 */
	public Time getCompleteTime() {
		return completeTime;
	}
	/**
	 * @param completeTime the completeTime to set
	 */
	public void setCompleteTime(Time completeTime) {
		this.completeTime = completeTime;
	}
	/**
	 * @return the transaction
	 */
	public int getTransaction() {
		return transaction;
	}
	/**
	 * @param transaction the transaction to set
	 */
	public void setTransaction(int transaction) {
		this.transaction = transaction;
	}
	/**
	 * @return the waiting
	 */
	public int getWaiting() {
		return waiting;
	}
	/**
	 * @param waiting the waiting to set
	 */
	public void setWaiting(int waiting) {
		this.waiting = waiting;
	}
	
	
	
}