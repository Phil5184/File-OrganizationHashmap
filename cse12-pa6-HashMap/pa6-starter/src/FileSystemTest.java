import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.*;

public class FileSystemTest {
	@Test
	public void testFileSystem() {
		FileSystem firstFile = new FileSystem("/Users/phil/documents/GitHub/cse12-pa6-HashMap/pa6-starter/src/input.txt");
		ArrayList<String> a1 = new ArrayList<>();
		a1.add("mySample.txt");
		System.out.println(firstFile.nameMap.keys());
		System.out.println(firstFile.nameMap.get("mySample.txt"));
		System.out.println(firstFile.dateMap.get("02/01/2021"));
		assertEquals(a1, firstFile.findAllFilesName());
	}
	@Test
	public void testFileSystem2() {
		FileSystem SecondFile = new FileSystem();
		ArrayList<String> a1 = new ArrayList<>();
		a1.add("MySample.txt");
		SecondFile.add("MySample.txt", "/home", "02/01/2021");
		assertEquals(a1, SecondFile.findAllFilesName());
	}
	@Test
	public void testFileSystem3() {
		FileSystem SecondFile = new FileSystem();
		ArrayList<String> a1 = new ArrayList<>();
		a1.add("My.txt");
		a1.add("MySample.txt");
		
		SecondFile.add("MySample.txt", "/home", "02/01/2021");
		SecondFile.add("My.txt", "/root", "02/01/2021");
		assertEquals(a1, SecondFile.findAllFilesName());
	}
}
