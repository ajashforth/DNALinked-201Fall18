
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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void initialize(String source) {
		myFirst = new Node(Character.toString(source.charAt(0)));
		myLast = new Node(Character.toString(source.charAt(source.length() - 1)));
		mySize = source.length();
		myAppends = 0;
	}

	@Override
	public IDnaStrand getInstance(String source) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IDnaStrand append(String dna) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IDnaStrand reverse() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getAppendCount() {
		// TODO Auto-generated method stub
		return 0;
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

}
