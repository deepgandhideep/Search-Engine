public class WordEntry
{
	public String str;
	public MyLinkedList<Position> well=new MyLinkedList<Position>();
	public AvlTree avt=new AvlTree();
	public WordEntry(String word)
	{
		str=word;
	}
	void addPosition(Position position)
	{
		Node<Position> v=new Node<Position>(position,null,null);
		well.addLast(v);
		avt.insert(position,str);
	}
	void addPositions(MyLinkedList<Position> positions)
	{
		Node<Position> n1=positions.header.getNext();
		while (n1!=positions.trailer)
		{
			Node<Position> n2=new Node<>(n1.getElement(),null,null);
			well.addLast(n2);
			n1=n1.getNext();
		}
	}
	MyLinkedList<Position> getAllPositionsForThisWord()
	{
		return well;
	}
	float getTermFrequency(String  word)
	{
		Node<Position> fi=well.header.getNext();
		float c=0,j=500;
		float a;
		for (int i=0;i<well.size();i++)
		{
			if ((fi.getElement().getPageEntry().na).equals(word))
			{
				j=fi.getElement().getPageEntry().totalWords();
				c++;
			}
			fi=fi.getNext();
		}
        a=c/j;
		return  a;
	}
}