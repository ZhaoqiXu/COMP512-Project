package MidInterface;

import MidImpl.*;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.io.Serializable;
import java.util.*;
/** 
 * Simplified version from CSE 593 Univ. of Washington
 *
 * Distributed  System in Java.
 * 
 * failure reporting is done using two pieces, exceptions and boolean 
 * return values.  Exceptions are used for systemy things. Return
 * values are used for operations that would affect the consistency
 * 
 * If there is a boolean return value and you're not sure how it 
 * would be used in your implementation, ignore it.  I used boolean
 * return values in the interface generously to allow flexibility in 
 * implementation.  But don't forget to return true when the operation
 * has succeeded.
 */

public interface MiddleWare extends Remote, Serializable
{
    /* Add seats to a flight.  In general this will be used to create a new
     * flight, but it should be possible to add seats to an existing flight.
     * Adding to an existing flight should overwrite the current price of the
     * available seats.
     *
     * @return success.
     */
    public boolean addFlight(int id, int flightNum, int flightSeats, int flightPrice) 
	throws RemoteException; 
    
    /* Add cars to a location.  
     * This should look a lot like addFlight, only keyed on a string location
     * instead of a flight number.
     */
    public boolean addCars(int id, String location, int numCars, int price) 
	throws RemoteException; 
   
    /* Add rooms to a location.  
     * This should look a lot like addFlight, only keyed on a string location
     * instead of a flight number.
     */
    public boolean addRooms(int id, String location, int numRooms, int price) 
	throws RemoteException; 			    

			    
    /* new customer just returns a unique customer identifier */
    public int newCustomer(int id) 
	throws RemoteException; 
    
    /* new customer with providing id */
    public boolean newCustomer(int id, int cid)
    throws RemoteException;

    /**
     *   Delete the entire flight.
     *   deleteflight implies whole deletion of the flight.  
     *   all seats, all reservations.  If there is a reservation on the flight, 
     *   then the flight cannot be deleted
     *
     * @return success.
     */   
    public boolean deleteFlight(int id, int flightNum) 
	throws RemoteException; 
    
    /* Delete all Cars from a location.
     * It may not succeed if there are reservations for this location
     *
     * @return success
     */		    
    public boolean deleteCars(int id, String location) 
	throws RemoteException; 

    /* Delete all Rooms from a location.
     * It may not succeed if there are reservations for this location.
     *
     * @return success
     */
    public boolean deleteRooms(int id, String location) 
	throws RemoteException; 
    
    /* deleteCustomer removes the customer and associated reservations */
    public boolean deleteCustomer(int id,int customer) 
	throws RemoteException; 

    /* queryFlight returns the number of empty seats. */
    public int queryFlight(int id, int flightNumber) 
	throws RemoteException; 

    /* return the number of cars available at a location */
    public int queryCars(int id, String location) 
	throws RemoteException; 

    /* return the number of rooms available at a location */
    public int queryRooms(int id, String location) 
	throws RemoteException; 

    /* return a bill */
    public String queryCustomerInfo(int id,int customer) 
	throws RemoteException; 
    
    /* queryFlightPrice returns the price of a seat on this flight. */
    public int queryFlightPrice(int id, int flightNumber) 
	throws RemoteException; 

    /* return the price of a car at a location */
    public int queryCarsPrice(int id, String location) 
	throws RemoteException; 

    /* return the price of a room at a location */
    public int queryRoomsPrice(int id, String location) 
	throws RemoteException; 

    /* Reserve a seat on this flight*/
    public boolean reserveFlight(int id, int customer, int flightNumber) 
	throws RemoteException; 

    /* reserve a car at this location */
    public boolean reserveCar(int id, int customer, String location) 
	throws RemoteException; 

    /* reserve a room certain at this location */
    public boolean reserveRoom(int id, int customer, String locationd) 
	throws RemoteException; 


    /* reserve an itinerary */
    public boolean itinerary(int id,int customer,Vector flightNumbers,String location, boolean Car, boolean Room)
	throws RemoteException; 


    /* reserveItinerary which books a set of flights, and possibly a car and a hotel at
the final destination */
    public boolean reserveItinerary()
    throws RemoteException;     

    public int start() throws RemoteException;

    public int start(int transactionId) throws RemoteException;
    
    public boolean commit(int transactionId) throws RemoteException, TransactionAbortedException, InvalidTransactionException;
    
    public boolean local_commit(int transactionId)
    throws RemoteException, TransactionAbortedException, InvalidTransactionException;

    public void abort(int transactionId) throws RemoteException, InvalidTransactionException;
    
    public void local_abort(int transactionId) throws RemoteException, InvalidTransactionException;

    public boolean shutdown() throws RemoteException;

    /* prepare in 2PC*/
    public int prepare(int xid) throws RemoteException, TransactionAbortedException, InvalidTransactionException;

    /* set crash mode */
    public void setCrashMode(String which, int mode) throws RemoteException;

    /* build link from mw to restarted rm */
    public void buildLink(String rm_name) throws RemoteException;

    public boolean get_votes_result(int transactionId) throws RemoteException;

    public void removeTransactionId(int transaction) throws RemoteException;
}
