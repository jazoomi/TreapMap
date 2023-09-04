import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.Random;

/**
 * A Java implementation of Treapmap using generics from implement Map. 
 *
 * @author Ariyan Sahebghalam, Eyosyas Andarge, Sadiq Azmi
 */
public class TreapMap<K, V> implements Map<K,V> {
	
    int size = 0;
    Node<K,V> root;
    private static Random rand;
    
    //Class for implementation of a singular node of the map
    private static final class Node <K,V>{
    	V value;
    	K key;
    	int priority;
    	Node left;
    	Node right;
    	Node parent;
    	//empty constructor to make a node 
    	public Node() {
    		
    	}
    }
    /* Empty constructor to construct the TreapMap
     */
    public TreapMap() {
    }

    
   
    /**
     * put function to insert a new node into the heap map given its key and value, done in O(log(n)) time
     * @param key the value used to locate the node in the map
     * @param value the random generated value to be stored with the key
     * @return The old key value if a node is being replaced, otherwise null
     */
    @Override
    //place the put method with the name add, stuck when putting it as put
    public V put(K key, V value) {
    	
    	Node<K,V> node = new Node<>();
    	node.key =key;
    	node.value = value;
    	node.priority = new Random().nextInt();

    	//case where we are added the root
    	if ( size == 0) {
    		root = node;
    		size++;
        	return null;
    	}
    	size++;
    	
    	Node<K, V> current = root;
    	V priority = null;
    	
    	int found = 0;
    	//a loop to go through the map, it will exit once the key is found. or if it does not exist.
    	while (current != null && found == 0) {

    		int a =  ((Comparable) key).compareTo((Comparable)current.key);

    		 if ( a < 0) {
    			 if (current.left == null) {
    				 found = 1;
    				 break;
    			 }
    			current = current.left;
    		}
    		else if ( a > 0) {
    			if (current.right == null) {
    				found = 2;
    				break;
    			}
    			current = current.right;
    		}
    		else if ( a == 0) {
    			// key is already there
    			priority = current.value;
    			current.value = value;
    			current.priority = node.priority;
    			size--;
    			break;
    		}
    		else {
    			break;
    		}
    	}
    	
    	//setting the node either left or right depending on the BST search
    	if (found == 1 ) {
    		current.left = node;
        	node.parent = current;
    	}
    	 else if (found ==2) {
    		current.right = node;
        	node.parent = current;
    	}
    	
    	
    	Node<K,V> temp;
    	//if heap property is violated, rotate right or left, respectively
    	while (current != null) {
    		//rotate right
	    	if (current.left != null && current.left.priority < current.priority) {
	    		
	    		rotateRight(current);

	    	}
	    	
	    	//rotate left
	    	else if (current.right != null && current.right.priority < current.priority) {
	    		
	    		rotateLeft(current);
	    		
	    	}

	    	current = current.parent;
	    	}

    	
    	
    	return priority;
    	}
    	

    /**
     * Get function to obtain an associated priority with given key, done in O(log(n)) time
     * @param key the specific key to obtain the associated priority value with it.
     * @return Returns a the value priority associated with given key
     * @Override
     */
    public V remove(Object key) {

    	Node<K, V> current = root;
    	V priority = null;
    	
    	//a loop to go through the map, it will exit once the key is found. or if it does not exist.
    	while (current != null ) {

    		int a =  ((Comparable) key).compareTo((Comparable)current.key);

    		 if ( a < 0) {
    			current = current.left;
    		}
    		else if ( a > 0) {
    			current = current.right;
    		}
    		else if ( a == 0) {
    			priority = current.value;
    			size--;
    			current.priority = (int)Float.POSITIVE_INFINITY;
       			break;
    			
    		}

    	}
    	
    	
    	Node<K,V> temp;
    	//rotations
    	while( current.left != null || current.right != null) {
    		//rotate right
	    	if ( current.left != null && current.left.priority <= current.priority) {
	    		rotateRight(current);
	    	}
	    	//rotate left
	    	else if ( current.priority > current.right.priority) {
	    		rotateLeft(current);
	    	}

	    	
	    	}
    	
    	//now to remove the node
    	//special case where we are removing only one node
    	if (size == 0) {
    		root = null;
    		return priority;
    	}
    	//check if its at left side
    	else if (current.parent.left == current) {
    		current.parent.left = null;
    	}
    	//if not its at the right side
    	else {
    		current.parent.right = null;
    	}
    	
    	current.parent = null;
    	//the node is not accessible and therefore is removed and now return the value
    		
    	
		return priority;
    }
    
    /**
     * Get function to obtain an associated priority with given key, done in O(log(n)) time
     * @param key the specific key to obtain the associated priority value with it.
     * @return Returns a the value priority associated with given key
     * @Override
     */
    public V get(Object key) {
    	Node<K, V> current = root;
    	
    	
    	//a loop to go through the map, it will exit once the key is found. or if it does not exist.
    	while (true) {

    		int a =  ((Comparable) key).compareTo((Comparable)current.key);

    		 if ( a < 0) {
     			//case where the key doesn't exist
     			if ( current.left == null) {
     				return root.value;
     			}
    			current = current.left;
    		}
    		else if ( a > 0) {
    			//case where the key doesn't exist
    			if ( current.right == null) {
    				return root.value;
    			}
    			current = current.right;
    		}
    		 //they are equal
    		else {
    			return current.value;
    		}

    		
    	}
    	/** return the root's value if the value does not exist in the map
    	*  as per how Tree Map handles this case
    	*/

    }

    /**
     * A function to see the current size of the treap map 
     * @return Returns the number of nodes in the treap map
     * @Override
     */
    public int size() {
        return this.size;
    }
    /**
     * Function to remove the entire Map.
     */
    @Override
    public void clear() {
    	this.size = 0;
    	this.root = null;
    	
    }

    /**
     * gives a set equivalence of the Treap map's keys 
     * @return Returns a "set view" of keys for the treap map
     * @Override
     */
    // I guess this is just node traversal
    @Override
    public Set<K> keySet() {
        return keySetHelper(root);
    }

    /**
     * to string function which acts identical to Treemap's implementation in that it will 
     * print the key's and values in tostring fashion. Done in linear time.
     * @return Prints all keys and values
     * @Override
     */
    @Override
    public String toString() {
        String result = "[" + helperToString(root);

        // Remove the last comma and space if they exist
        int length = result.length();
        if (length >= 2) {
            result = result.substring(0, length - 2);
        }

        return result + "]";

    }
    
    //function to rotate to the left
    private void rotateLeft(Node<K,V> current) {
    	Node<K, V> temp;
    	//temp nodes
		temp = current.right;
		
		if (current == root) {
			root = temp;
		}
		temp.parent = current.parent;
		current.right = temp.left;
		current.parent = temp;
		


		if ( temp.left != null) {
		temp.left.parent = current;
		}
		temp.left = current;
		
		//case only if we aren't handling the root
		if (temp.parent != null) {
    		// now final step to set the parent of the upper node to point to the new node
    		// use if statement to check if its left side or right side
    		if (temp.parent.right == current) {
    			temp.parent.right = temp;
    		}
    		else if (temp.parent.left == current){
    			temp.parent.left = temp;
    		}	    
		}
    }
    
    //function to rotate to the right
    private void rotateRight(Node<K,V> current) {
    	
    	Node<K,V> temp;
		//temp nodes
		temp = current.left;
		
		//special case where we rotate the top node
		if (current == root) {
			root = temp;
		}
		
		temp.parent = current.parent;
		current.left = temp.right;
		current.parent = temp;
		
		if ( temp.right != null) {
		temp.right.parent = current;
		}
		temp.right = current;
		

		//case only if we aren't handling the root
		if (temp.parent != null) {
    		// now final step to set the parent of the upper node to point to the new node
    		// use if statement to check if its left side or right side
    		if (temp.parent.left == current) {
    			temp.parent.left = temp;
    		}
    		else if (temp.parent.right == current){
    			temp.parent.right = temp;
    		}
		}
    }
    
    // Helper for recursion purposes
    private Set<K> keySetHelper(Node root) {
        HashSet<K> mySet = new HashSet<>();

        if (root != null) {
        	
            mySet.addAll(keySetHelper(root.left));
            mySet.add((K)root.key);
            mySet.addAll(keySetHelper(root.right));
        }

        return mySet;
    }

    // Helper for toString purposes
    private String helperToString(Node root) {
        String str = "";

        if (root != null) {
            str += helperToString(root.left) +  ""+ root.key + "=" + root.value + ", " + helperToString(root.right) ;
            

        }
        return str;
    }



    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsValue(Object value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void putAll(Map m) {
        throw new UnsupportedOperationException();
    }


    @Override
    public Collection values() {
        throw new UnsupportedOperationException();
    }



    @Override
    public Object getOrDefault(Object key, Object defaultValue) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void forEach(BiConsumer action) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void replaceAll(BiFunction function) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object putIfAbsent(Object key, Object value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean remove(Object key, Object value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean replace(Object key, Object oldValue, Object newValue) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object replace(Object key, Object value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object computeIfAbsent(Object key, Function mappingFunction) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object computeIfPresent(Object key, BiFunction remappingFunction) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object compute(Object key, BiFunction remappingFunction) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object merge(Object key, Object value, BiFunction remappingFunction) {
        throw new UnsupportedOperationException();
    }



	@Override
	public boolean containsKey(Object key) {
        throw new UnsupportedOperationException();
	}



	@Override
	public Set<Entry<K, V>> entrySet() {
        throw new UnsupportedOperationException();
	}



}
