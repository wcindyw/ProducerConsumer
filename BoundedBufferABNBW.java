class BoundedBufferABNBW<E> implements BoundedBuffer<E> {
	private final E[] buffer;
	private final int N; 			// size of buffer
	public boolean status = true; 	// boolean that determines what (producer or consumer) should be running
	private int count = 0; 			// number of items in the buffer
	private int in = 0; 			// slot where item will be inserted
	private int out = 0; 			// slot where item will be removed
	
	public BoundedBufferABNBW(int size) {
		N = size; 					// size of buffer
		buffer = (E[]) new Object[N];
	}
	public synchronized void insert(E item, Object p) throws InterruptedException {
		if(count == N) { 		// if full, then wait()
			status = false;
		}
		else { 					// insertion is possible, continue running
			status = true;
		}
		while (status == false) {
			if(count != N) {
				break;
			}
			System.out.println("Producer" + p.toString() + " waiting to insert Item #" + item.toString());
			wait();
		}
		status = false;
		buffer[in] = item; 		// insert item into buffer
		in = (in + 1) % N; 		// next slot where insertion will occur
		notifyAll(); 			// alerts all threads that are waiting
		count++; 				// increment count; keeps track of number of items in buffer
		System.out.println("Producer" + p.toString() + " inserted Item #" + item.toString() + "\t(" + count + " of " + N + " slots full)");
	}
	public synchronized E remove(Object c) throws InterruptedException {
		if(count == 0) { 		// if empty, then wait()
			status = true;
		}
		else { 					// removal is possible, continue running
			status = false;
		}
		while (status == true) {
			if(count != 0) {
				break;
			}
			System.out.println("Consumer" + c.toString() + " waiting to remove an item");
			wait();
		}
		status = true;
		E item = buffer[out]; 	// E item assigned the value of the item to be removed 
		buffer[out] = null; 	// Removed item
		out = (out + 1) % N; 	// next slot where insertion will occur
		notifyAll(); 			// alerts all threads that are waiting
		count--; 				// decrement count; keeps track of number of items in the buffer
		System.out.println("Consumer" + c.toString() + " removed Item #" + item.toString() + "\t(" + count + " of " + N + " slots full)");
		return item; 			// returns value of item that was removed
	}
}