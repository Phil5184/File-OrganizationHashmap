import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MyHashMap<K, V> implements DefaultMap<K, V> {
	public static final double DEFAULT_LOAD_FACTOR = 0.75;
	public static final int DEFAULT_INITIAL_CAPACITY = 16;
	public static final String ILLEGAL_ARG_CAPACITY = "Initial Capacity must be non-negative";
	public static final String ILLEGAL_ARG_LOAD_FACTOR = "Load Factor must be positive";
	public static final String ILLEGAL_ARG_NULL_KEY = "Keys must be non-null";
	
	private double loadFactor;
	private int capacity;
	private int size;

	// Use this instance variable for Separate Chaining conflict resolution
	private List<HashMapEntry<K, V>>[] buckets;  
	
	// Use this instance variable for Linear Probing
	private HashMapEntry<K, V>[] entries; 	

	public MyHashMap() {
		this(DEFAULT_INITIAL_CAPACITY, DEFAULT_LOAD_FACTOR);
	}
	
	/**
	 * 
	 * @param initialCapacity the initial capacity of this MyHashMap
	 * @param loadFactor the load factor for rehashing this MyHashMap
	 * @throws IllegalArgumentException if initialCapacity is negative or loadFactor not
	 * positive
	 */
	@SuppressWarnings("unchecked")
	public MyHashMap(int initialCapacity, double loadFactor) throws IllegalArgumentException {
		// TODO Finish initializing instance fields
		if (initialCapacity < 0) {
			throw new IllegalArgumentException();
		}
		else {
			this.capacity = initialCapacity;
		}
		if (loadFactor <= 0) {
			throw new IllegalArgumentException();
		}
		else {
			this.loadFactor = loadFactor;
		}
		this.size = 0;
		
		
		
		// if you use Separate Chaining
		buckets = (List<HashMapEntry<K, V>>[]) new List<?>[capacity];
		
//		for (int i = 0; i < buckets.length; i++) {
//			buckets[i] = new ArrayList<HashMapEntry<K, V>>();
//		}

		// if you use Linear Probing
		entries = (HashMapEntry<K, V>[]) new HashMapEntry<?, ?>[initialCapacity];
	}
	
	@Override
	public boolean put(K key, V value) throws IllegalArgumentException {
		 //can also use key.hashCode() assuming key is not null
		if ((double)(this.size)/this.capacity >= this.loadFactor) {
			this.expandCapacity();
		}
		int keyHash = Objects.hashCode(key) % buckets.length; 
		int index = Math.abs(keyHash % buckets.length);
		// TODO Auto-generated method stub
		if(key == null) {
			throw new IllegalArgumentException();
		}
		else {
			if (this.containsKey(key) == false) {
				HashMapEntry<K,V> entry = new HashMapEntry<>(key, value);
				if (buckets[index] == null) {
					buckets[index] = new ArrayList<HashMapEntry<K,V>>();
					buckets[index].add(entry);
				}
				else {
					buckets[index].add(entry);
				}
				this.size += 1;
				return true;
			}
		}
		return false;
	}
	
	@SuppressWarnings("unchecked")
	void expandCapacity() {
		List<HashMapEntry<K, V>>[] newBuckets = (List<HashMapEntry<K, V>>[]) new List<?>[this.capacity * 2];
		for (int i = 0; i < newBuckets.length; i++) {
			newBuckets[i] = new ArrayList<HashMapEntry<K, V>>();
		}
		List<HashMapEntry<K, V>>[] oldBuckets = this.buckets;
		this.buckets = newBuckets;
		
		this.size = 0;
		for (int i = 0; i < oldBuckets.length; i += 1) {
//			System.out.println(oldBuckets.length);
//			System.out.println(newBuckets.length);
//			System.out.println(this.buckets.length);
			if (oldBuckets[i] == null) {
				continue;
			}
			else {
				
				//System.out.println(oldBuckets[i].size());
				//System.out.println(oldBuckets[2].size());
				//int n = oldBuckets[i].size();
				for (int j = 0; j < oldBuckets[i].size(); j +=1) {
					//System.out.println(oldBuckets[i].get(j));
					//System.out.println(oldBuckets[i]);
					this.put(oldBuckets[i].get(j).getKey(), oldBuckets[i].get(j).getValue());
				}
			}
		}
	}
	
	@Override
	public boolean replace(K key, V newValue) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		int keyHash = Objects.hashCode(key);
		int index = Math.abs(keyHash % buckets.length);
		if(key == null) {
			throw new IllegalArgumentException();
		}
		
		else if (buckets[index] == null) {
			return false;
		}
		else {
			for (int i = 0; i < buckets[index].size(); i +=1) {
				if (buckets[index].get(i).getKey().equals(key)){
					//System.out.println("found key" + buckets[index].get(i).getValue());
					buckets[index].get(i).setValue(newValue);
					//System.out.println(buckets[index].get(i).getValue() + " " + buckets[index].get(i).getKey());
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean remove(K key) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		int keyHash = Objects.hashCode(key);
		int index = Math.abs(keyHash % buckets.length);
		if(key == null) {
			throw new IllegalArgumentException();
		}
		else if (buckets[index] == null) {
			return false;
		}
		else {
			for (int i = 0; i < buckets[index].size(); i +=1) {
				if (buckets[index].get(i).getKey().equals(key)) {
					buckets[index].remove(i);
					this.size -= 1;
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public void set(K key, V value) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		if(key == null) {
			throw new IllegalArgumentException();
		}
		else {
			if (this.containsKey(key) == true) {
				this.replace(key, value);
			}
			else {
				this.put(key, value);
			}
		}
	}

	@Override
	public V get(K key) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		if(key == null) {
			throw new IllegalArgumentException();
		}
		int keyHash = Objects.hashCode(key);
		int index = Math.abs(keyHash % buckets.length);
		if (buckets[index] == null) {
			return null;
		}
		else {
			//if (this.containsKey(key) == true) {
			for (int i = 0; i < buckets[index].size(); i +=1) {
				if (buckets[index].get(i).getKey().equals(key)) {
					return buckets[index].get(i).getValue();
				}
			}
		}
		
		return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
//		int total = 0;
//		for (int i = 0; i < this.capacity; i +=1) {
//			total += buckets[i].size();
//		}
//		return total;
		return this.size;
		//return 0;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		if (this.size() == 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean containsKey(K key) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		if(key == null) {
			throw new IllegalArgumentException();
		}
		int keyHash = Objects.hashCode(key);
		int index = Math.abs(keyHash % buckets.length);
		//System.out.println(index);
		if (buckets[index] == null) {
			return false;
		}
		else {
			for (int i = 0; i < buckets[index].size(); i +=1) {
				if (buckets[index].get(i).getKey().equals(key)) {
					return true;
				}
			}
		}
//		for (int i = 0; i < buckets[keyHash].size(); i +=1) {
//			if (buckets[keyHash].get(i).getKey().equals(key)) {
//				return true;
//			}
//		}
		return false;
	}

	
	@Override
	public List<K> keys() {
		// TODO Auto-generated method stub
		if (this.size == 0) {
			List<K> l1 = new ArrayList<>(0);
			return l1;
		}
//		
		List<HashMapEntry<K,V>> toLoop = new ArrayList<>();
		for (int i = 0; i < buckets.length; i +=1) {
			if (buckets[i] == null) {
				buckets[i] = new ArrayList<HashMapEntry<K,V>>();
			}
			toLoop.addAll(buckets[i]);
		}
		
		ArrayList<K> toReturn = new ArrayList<>();
		for (int j = 0; j < toLoop.size(); j +=1) {
			toReturn.add(toLoop.get(j).getKey());
		}
		return toReturn;
//		loop through the array
//		turn list into an array using toArray()
//		combine array together somehow
//		
//		loop through combined array
//		copy over the key from each element in the array into new array. 
//		
		//return null;
	}
	
	private static class HashMapEntry<K, V> implements DefaultMap.Entry<K, V> {
		
		K key;
		V value;
		
		private HashMapEntry(K key, V value) {
			this.key = key;
			this.value = value;
		}

		@Override
		public K getKey() {
			return key;
		}

		@Override
		public V getValue() {
			return value;
		}
		
		@Override
		public void setValue(V value) {
			this.value = value;
		}
	}
}
