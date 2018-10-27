
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getAppendCount() {
		return myAppends;
	}

	@Override
	public char charAt(int index) {
		int count = 0;
		int dex = 0;
		Node list = myFirst;
		while (count != index) {
			count++;
			dex++;
			if (dex >= list.info.length()) {
				dex = 0;
				list = list.next;
			}
		}
		return list.info.charAt(dex);
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
