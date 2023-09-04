import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestCases {

	 TreapMap<Integer, String> list = new TreapMap<>();
	 TreapMap<Integer, Integer> list2 = new TreapMap<>();
	 

	    @Test
	    public void testPutAndGet() {
	        list.put(1, "One");
	        list.put(2, "Two");
	        list.put(3, "Three");

	        assertEquals("One", list.get(1));
	        assertEquals("Two", list.get(2));
	        assertEquals("Three", list.get(3));
	    }
	    @Test
	    public void testGet() {
	        list.put(1, "One");
	        list.put(2, "Two");
	        list.put(3, "Three");
	        assertEquals("One", list.get(1));
	        assertEquals("Two", list.get(2));
	        assertEquals("Three", list.get(3));
	        /** this test case is passed but it would only work if you set node to public instead private
	        assertEquals(this.list.root.value, list.get(4));
	    	*/
	    }
	    @Test
	    public void testGet2() {
	        list.put(5, "Five");
	        list.put(6, "Six");
	        list.put(3, "Three");	        
	        list.put(8, "Eight");
	        list.put(7, "Seven");
	        list.put(1, "One");
	        assertEquals("One", list.get(1));
	        assertEquals("Five", list.get(5));
	        assertEquals("Seven", list.get(7));
	        /** this test case is passed but it would only work if you set node to public instead private
	        assertEquals(this.list.root.value, list.get(4));
	    	*/
	    }
	    @Test
	    public void testRemove() {
	        list.put(1, "One");
	        list.put(2, "Two");
	        list.put(3, "Three");

	        list.remove(2);

	        assertEquals(2, list.size());
	        assertEquals("One", list.get(1));
	        assertEquals("Three", list.get(3));
	    }
	    @Test
	    public void testRemove2() {
	        list.put(1, "One");
	        list.put(2, "Two");
	        list.put(3, "Three");

	        list.remove(2);

	        assertEquals(2, list.size());
	        assertEquals("One", list.get(1));
	        assertEquals("Three", list.get(3));
	    }
	    @Test
	    public void testRemove3() {
	        list.put(1, "One");
	        list.put(2, "Two");
	        list.put(3, "Three");

	        list.remove(2);
	        list.remove(3);
	        assertEquals(1, list.size());
	        assertEquals("One", list.get(1));
	        assertEquals("One", list.get(3));
	        list.remove(1);
	        assertEquals(0, list.size());
	        
	        
	    }

	    
	    @Test
	    public void testSize() {
	        assertEquals(0, list.size());

	        list.put(1, "One");
	        list.put(2, "Two");

	        assertEquals(2, list.size());

	        list.remove(1);

	        assertEquals(1, list.size());
	    }
	    
	    @Test
	    public void toStringTest() {
	    	
	        assertEquals("[]", list.toString());

	    }
	    
	    @Test
	    public void toStringTest1() {
	        list.put(1, "One");
	        list.put(2, "Two");
	        list.put(3, "Three");
	        assertEquals("[1=One, 2=Two, 3=Three]", list.toString());

	    }	    
	    @Test
	    public void toStringTest2() {
	        list.put(5, "Five");
	        list.put(6, "Six");
	        list.put(3, "Three");	        
	        list.put(8, "Eight");
	        list.put(7, "Seven");
	        list.put(1, "One");
	        String result = list.toString();
	        System.out.print(result);

	    }

	    @Test
	    public void KeySetTest() {
	        list.put(5, "Five");
	        list.put(6, "Six");
	        list.put(3, "Three");	        
	        list.put(8, "Eight");
	        list.put(7, "Seven");
	        list.put(1, "One");
	        HashSet<Integer> list2 = new HashSet<Integer>();
	        list2 = (HashSet<Integer>) list.keySet(); 
	        System.out.print(list2.toString());
	       // assertEquals(true, list.keySet());

	    }



}

