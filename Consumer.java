class Consumer<E> extends Thread {
	private BoundedBufferABNBW<Integer> item;
	private int thread_id;
	private int quota;
	
	public Consumer(BoundedBufferABNBW<Integer> c, int thread_id, int quota) {
		item = c; 						// item that will be removed
		this.thread_id = thread_id; 	// unique consumer thread id
		this.quota = quota; 			// number of items to be inserted
	}
	public synchronized void run() {
		for (int i = 1; i < this.quota + 1; i++) {
			try {
				Object c = (int) this.thread_id;
				item.remove(c);
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Consumer" + this.thread_id + " FINISHED consuming " + quota + " items");
	}
}