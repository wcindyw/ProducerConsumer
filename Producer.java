class Producer extends Thread {
	private BoundedBufferABNBW<Integer> item;
	private int thread_id;
	private int quota;
	private Item item_id;
	
	public Producer(BoundedBufferABNBW<Integer> boundedBuffer, int thread_id, int quota, Item item_id) {
		item = boundedBuffer; 			// from BoundedBuffer interface
		this.thread_id = thread_id; 	// unique producer thread id
		this.quota = quota; 			// number of items to be inserted
		this.item_id = item_id; 		// unique item id to be inserted in buffer
	}
	public synchronized void run() { 	// only one thread at a time
		for (int i = 1; i < this.quota + 1; i++) {
			try {
				Object p = (int) this.thread_id;
				item.insert(item_id.next(), p);
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Producer" + this.thread_id + " FINISHED producing " + quota + " items");
	}
}