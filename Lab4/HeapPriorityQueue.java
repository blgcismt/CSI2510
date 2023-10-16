/**
 * Array Heap implementation of a priority queue
 * @author Lachlan Plant
 */
public class HeapPriorityQueue <K extends Comparable, V>implements PriorityQueue <K, V> {

	private Entry [] storage; //The Heap itself in array form
	private int tail;    //Index of last element in the heap

	/**
	 * Default constructor
	 */
	public HeapPriorityQueue() {
		storage = new Entry[ 100 ];
		tail = -1;
	}


	/**
	 * HeapPriorityQueue constructor
	 * @param size maximum storage size
	 */
	public HeapPriorityQueue( int size ) {
		storage = new Entry[ size ];
		tail = -1;
	}


	/****************************************************
	*
	*             Priority Queue Methods
	*
	****************************************************/

	/**
	 * Returns the number of items in the priority queue.
	 * O(1)
	 * @return number of items
	 */
	public int size() {
		return tail + 1;
	} /* size */


	/**
	 * Tests whether the priority queue is empty.
	 * O(1)
	 * @return true if the priority queue is empty, false otherwise
	 */
	public boolean isEmpty() {
		return tail == -1;
	} /* isEmpty */


	/**
	 * Inserts a key-value pair and returns the entry created.
	 * O(log(n))
	 * @param key     the key of the new entry
	 * @param value   the associated value of the new entry
	 * @return the entry storing the new key-value pair
	 * @throws IllegalArgumentException if the heap is full
	 */
	public Entry <K, V>insert( K key, V value ) throws IllegalArgumentException {
		if ( tail == storage.length - 1 ) {
			throw new IllegalArgumentException( "Heap is full" );
		}
		Entry <K, V>newEntry = new Entry <K, V>( key, value );
		storage[ ++tail ] = newEntry;
		upHeap( tail );
		return newEntry;
	} /* insert */


	/**
	 * Returns (but does not remove) an entry with minimal key.
	 * O(1)
	 * @return entry having a minimal key (or null if empty)
	 */
	public Entry <K, V>min() {
		if ( isEmpty() ) {
			return null;
		}
		return storage[ 0 ];
	} /* min */


	/**
	 * Removes and returns an entry with minimal key.
	 * O(log(n))
	 * @return the removed entry (or null if empty)
	 */
	public Entry <K, V>removeMin() {
		if ( isEmpty() ) {
			return null;
		}
		Entry <K, V>min = storage[ 0 ];
		storage[ 0 ] = storage[ tail-- ];
		downHeap( 0 );
		return min;
	} /* removeMin */


	/****************************************************
	*
	*           Methods for Heap Operations
	*
	****************************************************/

	/**
	 * Algorithm to place element after insertion at the tail.
	 * O(log(n))
	 */
	private void upHeap( int location ) {
		while (location > 0){
			int parent = parent(location);

			if (storage[location].key.compareTo(storage[parent].key) < 0){
				swap(location,parent);
				location = parent;
			}
			else{
				break;
			}
		}
	} /* upHeap */


	/**
	 * Algorithm to place element after removal of root and tail element placed at root.
	 * O(log(n))
	 */
	private void downHeap( int location ) {
		while (location * 2 + 1 <= tail) {
            int leftChild = location * 2 + 1;
            int rightChild = leftChild + 1;
            int smallestChild = leftChild;
            if (rightChild <= tail && storage[rightChild].key.compareTo(storage[leftChild].key) < 0) {
                smallestChild = rightChild;
            }
            if (storage[location].key.compareTo(storage[smallestChild].key) > 0) {
                swap(location, smallestChild);
                location = smallestChild;
            } else {
                break;
            }
        }
	} /* downHeap */


	/**
	 * Find parent of a given location,
	 * Parent of the root is the root
	 * O(1)
	 */
	private int parent( int location ) {
		return ( location - 1 ) / 2;
	} /* parent */


	/**
	 * Inplace swap of 2 elements, assumes locations are in array
	 * O(1)
	 */
	private void swap( int location1, int location2 ) {
		Entry<K, V> temp = storage[location1];
        storage[location1] = storage[location2];
        storage[location2] = temp;
	} /* swap */


}
