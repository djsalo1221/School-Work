

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * This is the main Simulator Class.
 * The main method reads Customer details from the given file.
 * Uses Event driven Simulation to display the waiting times and 
 * service time for each customer.
 */
public class Simulator 
{
	private static int customer_id = 1;
	
	public static void main(String[] args) 
	{
		Customer[] customers;

		System.out.println("Please enter the file name");
		Scanner in = new Scanner(System.in);
		String fileName = in.next();

		FileInputStream fis;

		try 
		{
			fis = new FileInputStream(fileName);
			BufferedReader br = new BufferedReader(new InputStreamReader(fis));
			String numOfCustomerString =  br.readLine();

			int numOfCustomers = new Integer(numOfCustomerString);
			customers = new Customer[numOfCustomers];

			String line = null;

			int i= 0;
			while ((line = br.readLine()) != null) 
			{
				Customer customer = readCustomerInfo(line);
				customers[i] = customer;
				i++;
			}

			br.close();

			process(customers);

			double waitingTime = 0;
			
			displayHeaders();

			// Display details for all customers
			for ( Customer customer : customers)
			{
				System.out.print(customer.getID() + "\t   " );
				System.out.print(customer.getArriveTime() + "\t   ");
				System.out.print(customer.getServiceTime()+ "\t   ");
				System.out.print(customer.getCompleteTime()+ "\t   ");
				System.out.print(customer.getWaiting()+ "\t   ");
				System.out.println();
				waitingTime+= customer.getWaiting();
			}

			double avgWaiting = waitingTime/numOfCustomers;
			
			System.out.printf("\nAverage waiting time = %2.2f", avgWaiting);
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		} 
	}

	/*
	 * Method to read Customer information from inputs file
	 * and return a Customer Object based on input data.
	 * @param customerInfo
	 * @return
	 * @throws Exception
	 */
	private static Customer readCustomerInfo(String customerInfo) throws Exception {

		String customerDetails[] = customerInfo.split(" ");

		if ( customerDetails.length != 3)
			throw new Exception("Invalid Customer information in file");
		
		// Arrive time
		String time = customerDetails[0];
		String hoursMin[] = time.split(":");
		String hour = hoursMin[0];
		String min = hoursMin[1];

		String period = customerDetails[1];

		boolean isMorning = false;
		if ( period.equalsIgnoreCase("AM"))
			isMorning = true;

		Time arrivalTime = new Time(new Integer(hour),new Integer(min), isMorning);

		// transaction duration
		int transaction = new Integer(customerDetails[2]);

		Customer customer = new Customer();
		customer.setArriveTime(arrivalTime);
		customer.setTransaction(transaction);
		customer.setID(customer_id);

		customer_id ++;

		return customer;
	}  


	/**
	 * Process all customers details.
	 * Use event simulation to calculate the service time and job completion time
	 * for each customer.
	 * 
	 * @param customers
	 */
	public static void process(Customer[] customers)
	{
		Time timeNow;
		boolean tellIsFree;
		PriorityQueue<Event> events = new PriorityQueue<Event>();
		//create an empty customer waiting queue (a FIFO queue);
		LinkedList<Customer> waitingQueue = new LinkedList<Customer>();

		tellIsFree = true;

		/*	for each customer
		insert a new event to the events priority queue
		(the event time is the same as the arrive time, 
				and the event type is for a New customer who enters the bank at this time)*/
		for ( Customer customer : customers)
		{
			Event event = new Event();
			event.setCustomer(customer);
			event.setEventType('N');
			event.setEventTime(customer.getArriveTime());
			events.add(event);
		}

		while ( !events.isEmpty())	//while there is still an event
		{
			Event event = events.remove();	//dequeue an event;

			timeNow = event.getEventTime();	//timeNow = this event’s eventTime;

			if ( event.getEventType() == 'N')//if the event is a new customer
			{
				if ( tellIsFree)
				{
					tellIsFree = false;

					//this customer’s service time is timeNow
					event.getCustomer().setServiceTime(timeNow);
					event.getCustomer().setWaiting(timeNow.timeDifference(event.getCustomer().getArriveTime()));
				
					//create and enqueue an event to terminate the transaction 
					//(event time is timeNow plus the time required for the transaction)
					Event endEvent = new Event();
					Time eventTime = timeNow.plus(event.getCustomer().getTransaction());
					endEvent.setEventTime(eventTime);
					endEvent.setEventType('D');
					endEvent.setCustomer(event.getCustomer());
					events.add(endEvent);
				}
				else
				{
					waitingQueue.add(event.getCustomer());//add the customer to the waiting queue
				}
			}
			else // a customer’s transaction is done
			{
				//update this customer’s completeTime in customers array;
				//if there is no customer waiting in the waiting queue
				//the teller is free now;
				//Time completionTime = event.getEventTime(); //event.getCustomer().getServiceTime().plus(event.getCustomer().getTransaction());
				event.getCustomer().setCompleteTime(timeNow);

				if ( !waitingQueue.isEmpty())
				{//dequeue a customer from the waiting queue
					Customer customer = waitingQueue.remove();
					customer.setServiceTime(timeNow);
					customer.setWaiting(timeNow.timeDifference(customer.getArriveTime()));

					//this customer’s service time is timeNow
					//create and enqueue an event to terminate the transaction 
					//the event time is timeNow plus the time required for the transaction)
					Event endEvent = new Event();
					Time eventTime = timeNow.plus(customer.getTransaction());
					endEvent.setEventTime(eventTime);
					endEvent.setEventType('D');
					endEvent.setCustomer(customer);
					events.add(endEvent);
				}
				else
				{
					tellIsFree = true;
				}
			}
		}
	}
	
	/**
	 * Method to display the headers while printing customer details.
	 */
	private static void displayHeaders() 
	{
		System.out.printf
		("%-6s%12s%16s%16s%10s", "Customer","Arrival","Service","Completing", "Time");
		System.out.println();
		System.out.printf
		("%-6s%12s%16s%16s%13s", "Number","Time","Time","Time", "Waited");
		System.out.println();
		System.out.println("--------------------------------------------------------------------");
	}
}
