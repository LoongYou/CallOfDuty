package testfile;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.content.Demo1;
//1212
import static org.junit.Assert.*;

public class Demo1Test {

	static Demo1 demo1;
	
	
	/*@BeforeClass
	 * note
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		demo1 = new Demo1();
	}

	/*@AfterClass
	 * 
	 * note
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		demo1 = null;
	}
	
	/*@Before
	 * 
	 * note:
	 */
	@Before
	public void setUp() {
		demo1.number = 2;
	}
	
	/*@After
	 *
	 * note:
	 */
	@After
	public void tearDown() {
		System.out.println(demo1.number);
	}
	
	@Test
	public final void testAdd() {
		assertEquals(5, demo1.add(4));//
	}

	@Test
	public final void testSub() {
		assertEquals(4, demo1.sub(5));
	}

	@Test
	public final void testMul() {
		assertEquals(10, demo1.mul(5));
	}

	/*@Ignore:
	 * 
	 */
	@Ignore
	@Test
	public final void testDiv() {
		assertEquals(5, demo1.div(10));
	}
}
