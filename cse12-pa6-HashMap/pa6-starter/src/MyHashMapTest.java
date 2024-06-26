import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.*;

public class MyHashMapTest {
	
	private DefaultMap<String, String> testMap; // use this for basic tests
	private DefaultMap<String, String> mapWithCap; // use for testing proper rehashing
	public static final String TEST_KEY = "Test Key";
	public static final String TEST_VAL = "Test Value";
	
	@Before
	public void setUp() {
		testMap = new MyHashMap<>();
		mapWithCap = new MyHashMap<>(4, MyHashMap.DEFAULT_LOAD_FACTOR);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testPut_nullKey() {
		testMap.put(null, TEST_VAL);
	}

	@Test
	public void testKeys_nonEmptyMap() {
		// You don't have to use array list 
		// This test will work with any object that implements List
		List<String> expectedKeys = new ArrayList<>(5);
		for(int i = 0; i < 5; i++) {
			// key + i is used to differentiate keys since they must be unique
			testMap.put(TEST_KEY + i, TEST_VAL + i);
			expectedKeys.add(TEST_KEY + i);
		}
		List<String> resultKeys = testMap.keys();
		// we need to sort because hash map doesn't guarantee ordering
		Collections.sort(resultKeys);
		assertEquals(expectedKeys, resultKeys);
	}
	
	/* Add more of your tests below */
	
	@Test
	public void testEmpty() {
		int Expected = 0;
		assertEquals(Expected, testMap.size());
	}

	@Test
	public void testIsEmpty() {
		boolean Expected = true;
		assertEquals(Expected, testMap.isEmpty());
	}
	@Test
	public void testGetEmpty() {
		//boolean Expected = true;
		assertNull(testMap.get("big"));
	}
	@Test
	public void testGetNotEmpty() {
		boolean Expected = false;
		testMap.put(TEST_KEY, TEST_VAL);
		assertEquals(Expected, testMap.isEmpty());
	}
	@Test
	public void testReplace() {
		testMap.put(TEST_KEY, null);
		testMap.replace(TEST_KEY, TEST_VAL);
		assertEquals(TEST_VAL, testMap.get(TEST_KEY));
	}
	@Test
	public void testReplace1() {
		testMap.put(TEST_KEY, null);
		//testMap.replace(TEST_KEY, TEST_VAL);
		testMap.remove(TEST_KEY);
		assertEquals(false, testMap.remove(TEST_KEY));
	}
//	@Test
//	public void testKeys_expand() {
//		// You don't have to use array list 
//		// This test will work with any object that implements List
//		List<String> expectedKeys = new ArrayList<>(25);
//		for(int k = 0; k < 25; k++) {
//			// key + i is used to differentiate keys since they must be unique
//			testMap.put(TEST_KEY + k, TEST_VAL + k);
//			expectedKeys.add(TEST_KEY + k);
//		}
//		List<String> resultKeys = testMap.keys();
//		// we need to sort because hash map doesn't guarantee ordering
//		Collections.sort(resultKeys);
//		assertEquals(expectedKeys, resultKeys);
//	}
}
