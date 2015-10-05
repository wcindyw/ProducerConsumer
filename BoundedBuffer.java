public interface BoundedBuffer<E> {
	public static void main(String[] args) {
		BoundedBufferABNBW<Integer> boundedBuffer = new BoundedBufferABNBW<Integer>(3);
		Item item_id = new Item(); 		// Each item has a unique id #
		int quota = 20; 				// Number of items produced/consumed by each producer/consumer
		int pThreads = 2; 				// Number of producers
		int cThreads = 2; 				// Number of consumers
		// Creates instance(s) of the threads
		for(int i = 1; i < pThreads + 1; i++) {
			Producer p = new Producer(boundedBuffer, i, quota, item_id);
			p.start();
		}
		for(int i = 1; i < cThreads + 1; i++) {
			Consumer<Object> c = new Consumer<Object>(boundedBuffer, i, quota);
			c.start();
		}
	}
}