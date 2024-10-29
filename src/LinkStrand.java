public class LinkStrand implements IDnaStrand {

    private class Node {
        String info;
        Node next;
        
        public Node(String s) {
            info = s;
        }

        public Node(String s, Node n) {
            info = s;
            next = n;
        }
    }

    private Node myFirst, myLast;
    private long mySize;
    private int myAppends;
    private int myIndex;
    private Node myCurrent;
    private int myLocalIndex;

    public LinkStrand() {
        this("");
    }

    public LinkStrand(String s) {
        initialize(s);
    }

    @Override
    public long size() {
        // TODO Auto-generated method stub
        return mySize;
    }

    @Override
    public void initialize(String source) {
        // TODO Auto-generated method stub
        myFirst = new Node(source);
        myLast = myFirst;
        mySize = source.length();
        myAppends = 0;
        myIndex = 0;
        myCurrent = myFirst;
        myLocalIndex = 0;
    }

    @Override
    public IDnaStrand getInstance(String source) {
        // TODO Auto-generated method stub
        return new LinkStrand(source);
    }

    @Override
    public IDnaStrand append(String dna) {
        // TODO Auto-generated method stub
        myLast.next = new Node(dna);
        myLast = myLast.next;
        mySize += dna.length();
        myAppends++;
        return this;
    }

    @Override
    public IDnaStrand reverse() {
        // TODO Auto-generated method stub
        LinkStrand reverseStrand = new LinkStrand();
        Node current = myFirst;
        while (current != null) {
            StringBuilder dna = new StringBuilder(current.info);
            reverseStrand.myFirst = new Node(dna.reverse().toString(), reverseStrand.myFirst);
            current = current.next;
        }
        reverseStrand.myLast = reverseStrand.myFirst;
        reverseStrand.mySize = mySize;
        reverseStrand.myAppends = myAppends;
        reverseStrand.myIndex = myIndex;
        reverseStrand.myCurrent = reverseStrand.myFirst;
        reverseStrand.myLocalIndex = reverseStrand.myFirst.info.length() - 1;
        return reverseStrand;
    }

    @Override
    public int getAppendCount() {
        // TODO Auto-generated method stub
        return myAppends;
    }

    @Override
    public char charAt(int index) {
        // TODO Auto-generated method stub
        if (index < 0 || index >= mySize) {
            throw new IndexOutOfBoundsException();
        }
        if (myIndex > index) {
            myCurrent = myFirst;
            myIndex = 0;
            myLocalIndex = 0;
        }
        while (myIndex < index) {
            int charLeft = myCurrent.info.length() - myLocalIndex;
            if (index - myIndex >= charLeft) {
                myIndex += charLeft;
                myLocalIndex = 0;
                myCurrent = myCurrent.next;
            } else {
                myLocalIndex += (index - myIndex);
                myIndex = index;
            }
        }
        return myCurrent.info.charAt(myLocalIndex);
    }

    @Override
    public String toString() {
        Node current = myFirst;
        StringBuilder dna = new StringBuilder();
        while (current != null) {
            dna.append(current.info);
            current = current.next;
        }
        return dna.toString();
    }
    
}
