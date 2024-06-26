import static org.junit.Assert.*;

import org.junit.*;

public class FileDataTest {
	@Test
	public void testToString() {
		String expected = "{Name: Phillip, Directory: /root, Modified Date: 01/01/2021}";
		FileData toTest = new FileData("Phillip", "/root", "01/01/2021");
		assertEquals(expected, toTest.toString());
	}
	@Test
	public void testToString2() {
		String expected = "{Name: Phillip, Directory: , Modified Date: 01/01/2021}";
		FileData toTest = new FileData("Phillip", "", "01/01/2021");
		assertEquals(expected, toTest.toString());
	}
	@Test
	public void testToString3() {
		String expected = "{Name: Phillip, Directory: , Modified Date: 01/01/2021}";
		FileData toTest = new FileData("Phillip", null, "01/01/2021");
		assertEquals(expected, toTest.toString());
	}
}
