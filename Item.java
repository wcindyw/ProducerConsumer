import java.util.concurrent.atomic.AtomicInteger;

class Item {
	private AtomicInteger id = new AtomicInteger(0); 	// instance of integer that keeps track of item id
	
	public synchronized int next() { 					// only one thread at a time
		id.getAndIncrement(); 							// increase value of id
		return id.get(); 								// returns unique item id
	}
	public synchronized String toString(){ 				// returns string of item id
		String str = "Item #" + this.id;
		return str;
	}
}