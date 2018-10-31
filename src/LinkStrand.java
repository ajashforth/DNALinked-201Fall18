
public class LinkStrand implements IDnaStrand{

	private class Node {
		String info;
	   	Node next;
	   	public Node(String s) {
	      	info = s;
	      	next = null;
	   	}
	}
	
	private Node myFirst,myLast;
	private long mySize;
	private int myAppends;
	private int myIndex;
	private int myLocalIndex;
	private Node myCurrent;
	
	/**
	 * Default Constructor that sends the string "" to the other Constructor
	 */
	public LinkStrand(){
		this("");
	}
	/**
	 * Constructor that creates a strand representing s.
	 * 
	 * @param s is the source of cgat data for this strand
	 */
	public LinkStrand(String s) {
		initialize(s);
	}
	
	@Override
	public long size() {
		return mySize;
	}

	@Override
	public void initialize(String source) {
		myFirst = new Node(source);
		myLast = myFirst;
		mySize = source.length();
		myAppends = 0;
		myIndex = 0;
		myLocalIndex = 0;
		myCurrent = myFirst;
		
	}

	@Override
	public IDnaStrand getInstance(String source) {
		return new LinkStrand(source);
	}

	@Override
	public IDnaStrand append(String dna) {
		myLast.next = new Node(dna);
		myLast = myLast.next;
		mySize = mySize + dna.length();
		myAppends = myAppends + 1;
		return this;
	}

	@Override
	public IDnaStrand reverse() {
		Node thisNode = myFirst;
		String theString = "";
		Node newNode = null;
		while (thisNode != null) {
			Node firstNode = newNode;
			newNode = new Node(thisNode.info);
			newNode.next = firstNode;
			thisNode = thisNode.next;
		}
		while (newNode != null) {
			StringBuilder copy = new StringBuilder(newNode.info);
			theString += copy.reverse().toString();
			newNode = newNode.next;
		}
		return new LinkStrand(theString);
	}
	
	/**
	 * public IDnaStrand reverse() {
		Node current = myFirst;
		String theString = "";
		Node next = myFirst;
		Node prev = null;
		
		while (current != null) {
			next = current.next;
			current.next = prev;
			prev = current;
			current = next;
		}
		while (prev != null) {
			StringBuilder copy = new StringBuilder(prev.info);
			theString += copy.reverse().toString();
			prev = prev.next;
		}
		return new LinkStrand(theString);
	}
	 */

	@Override
	public int getAppendCount() {
		return myAppends;
	}

	@Override
	public char charAt(int index) {
		if (index < 0 || index >= mySize) {
			throw new IndexOutOfBoundsException("Out of Bounds");
		}
		if (index < myIndex) {
			myIndex = 0;
			myLocalIndex = 0;
			myCurrent = myFirst;
		}
		while (myIndex != index) {
			myIndex++;
			myLocalIndex++;
			if (myLocalIndex >= myCurrent.info.length()) {
				myLocalIndex = 0;
				myCurrent = myCurrent.next;
			}
		}
		return myCurrent.info.charAt(myLocalIndex);
	}
	
	@Override
	public String toString() {
		if(myFirst == null) {
			return "";
		}
		Node theNode = myFirst;
		StringBuilder linked = new StringBuilder();
		while (theNode != null && theNode.next != null) {
			linked.append(theNode.info);
			theNode = theNode.next;
		}
		if(theNode != null) {
			linked.append(theNode.info);
		}
		return linked.toString();
	}

}
