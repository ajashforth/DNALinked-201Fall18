/**
 * Efficient implementation of IDnaStrand. \ This
 * implementation uses Nodes to create LinkStrands to represent genomic/DNA data.
 * @author alecashforth
 *
 */

public class LinkStrand implements IDnaStrand{

	/**
	 * This inner class allows other methods in the LinkStrand class to create
	 * Nodes which are the main building blocks of LinkStrands
	 *
	 */
	private class Node {
		String info;
	   	Node next;
	   	public Node(String s) {
	      	info = s;
	      	next = null;
	   	}
	}
	
	/**
	 * These are the instance variables needed for a LinkStrand object
	 */
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
	
	/**
	 * this method returns the size of "this" linkstrand
	 */
	@Override
	public long size() {
		return mySize;
	}

	/**
	 * this method initializes all of the instance variables
	 * 
	 * @param source is the string that will turn into the linkstrand object
	 */
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

	/**
	 * this method returns a linkstrand of the string "source"
	 * 
	 * @source is the string that will be turned into a linkstrand object
	 */
	@Override
	public IDnaStrand getInstance(String source) {
		return new LinkStrand(source);
	}

	/**
	 * this method adds a new string to a linkstrand by creating a new node and
	 * having the last node of "this" linkstrand point to this node that has
	 * the string as its info
	 * 
	 * @dna is the string that will be turned into the info of a node to be added
	 * to a linkstrand
	 */
	@Override
	public IDnaStrand append(String dna) {
		myLast.next = new Node(dna);
		myLast = myLast.next;
		mySize = mySize + dna.length();
		myAppends = myAppends + 1;
		return this;
	}

	/**
	 * this method reverses the linkstrand so that the last character of the new strand
	 * is the first character of the original strand
	 */
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
		return new LinkStrand(theString.toString());
	}

	/**
	 * this method finds the number of appends that have been used so far for this 
	 * linkstrand
	 */
	@Override
	public int getAppendCount() {
		return myAppends;
	}

	/**
	 * this method finds the character at a specific index of the linkstrand
	 * 
	 * @index is the integer spot where the method will go to that index of the 
	 * linkstrand and find its character
	 */
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
	
	/**
	 * This method converts the linkstrand into a string
	 */
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
