public class PageIndex
{
	public MyLinkedList<WordEntry> pill=new MyLinkedList<WordEntry>();
	void addPositionForWord(String str,Position p)
	{
		int s=pill.size(),i=0;
		Node<WordEntry> f=pill.header.getNext();
		while ((i<s)&&(!(f.getElement().str).equals(str)))
		{
			i++;
			f=f.getNext();
		}
		if (i<s)
		{
			f.getElement().addPosition(p);
		}
		else
		{
			WordEntry w=new WordEntry(str);
			w.addPosition(p);
			Node<WordEntry> n=new Node<WordEntry>(w,null,null);
			pill.addLast(n);
		}
	}
	MyLinkedList<WordEntry> getWordEntries()
	{
		return pill;
	}
}