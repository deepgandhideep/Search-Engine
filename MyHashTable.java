public class MyHashTable
{
	public MyLinkedList<WordEntry>[] mht=new MyLinkedList[676];
	public MyHashTable()
	{
		for (int i=0;i<676;i++)
		{
			mht[i]=new MyLinkedList<WordEntry>();
		}
	}
	private int getHashIndex(String str)
	{
		int i;
		if(str.length()>1)
		{
			i=(((str.charAt(0))-97)*26)+(str.charAt(1)-97);
			if (i<0)
			{
				i=i*(-1);
			}
			return (i%676);
		}
		else
		{
			i=str.charAt(0);
			return (i%676);
		}
	}
	void addPositionsForWord(WordEntry w)
	{
		int hashIndexForWord=getHashIndex(w.str);
		if(mht[hashIndexForWord]!=null){
			if(!mht[hashIndexForWord].isEmpty())
			{
				Node<WordEntry> temp = mht[hashIndexForWord].header.getNext();
				while (temp.next!=null)
				{
					if (temp.getElement().str.equals(w.str))
					{
						temp.getElement().addPositions(w.getAllPositionsForThisWord());
						break;
					}
					temp = temp.getNext();
				}
				if (temp.getNext()==null)
				{
					WordEntry wn=new WordEntry(w.str);
					wn.addPositions(w.getAllPositionsForThisWord());
					Node<WordEntry> ne=new Node<WordEntry>(wn,null,null);
					mht[hashIndexForWord].addLast(ne);
				}
			}
			else
			{
				WordEntry wn=new WordEntry(w.str);
				wn.addPositions(w.getAllPositionsForThisWord());
				Node<WordEntry> ne=new Node<WordEntry>(wn,null,null);
				mht[hashIndexForWord].addLast(ne);
			}
		}
		else
		{
			MyLinkedList<WordEntry> newll = new MyLinkedList<>();
			mht[hashIndexForWord]=newll;
			WordEntry wn=new WordEntry(w.str);
			wn.addPositions(w.getAllPositionsForThisWord());
			Node<WordEntry> ne=new Node<WordEntry>(wn,null,null);
			mht[hashIndexForWord].addLast(ne);
		}

	}
	public WordEntry getWord(String s)
	{
		int i=getHashIndex(s);
		Node<WordEntry> f=mht[i].header.getNext();
		for (int j=0;j<mht[i].size();j++)
		{
			if (s.equals(f.getElement().str))
			{
				return f.getElement();
			}
			f=f.getNext();
		}
		return null;
	}
}