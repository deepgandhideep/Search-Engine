class Node1
{
	private WordEntry element;
	private Node1 next,prev;
	public Node1(WordEntry e,Node1 p,Node1 n)
	{
		element=e;
		next=n;
		prev=p;
	}
	public WordEntry getElement()
	{
		return element;
	}
	public Node1 getNext()
	{
		return next;
	}
	public Node1 getPrev()
	{
		return prev;
	}
	public void setElement(WordEntry e)
	{
		element=e;
	}
	public void setNext(Node1 n)
	{
		next=n;
	}
	public void setPrev(Node1 p)
	{
		prev=p;
	}
}
public class Map
{
	protected Node1 header,trailer;
	protected int size;
	public Map()
	{
		header=new Node1(null,null,null);
		trailer=new Node1(null,header,null);
		header.setNext(trailer);
		size=0;
	}
	public int size()
	{
		return size;
	}
	public boolean isEmpty()
	{
		return (size==0);
	}
	public Node1 getFirst()
	{
		if (isEmpty())
		{
			throw new IllegalStateException("List is empty"); 
		}
		return header.getNext();
	}
	public Node1 getLast()
	{
		if (isEmpty())
		{
			throw new IllegalStateException("List is empty"); 
		}
		return trailer.getPrev();
	}
	public Node1 getPrev(Node1 v)
	{
		if (v==header)
		{
			throw new IllegalArgumentException("Cannot move before header"); 
		}
		return v.getPrev();
	}
	public Node1 getNext(Node1 v)
	{
		if (v==trailer)
		{
			throw new IllegalArgumentException("Cannot move after the trailer"); 
		}
		return v.getNext();
	}
	public void addBefore(Node1 v,Node1 z)
	{
		if (v==header)
		{
			throw new IllegalArgumentException("Cannot add before header");
		}
		Node1 u=getPrev(v);
		z.setPrev(u);
		z.setNext(v);
		v.setPrev(z);
		u.setNext(z);
		size=size+1;
	}
	public void addAfter(Node1 v,Node1 z)
	{
		if (v==trailer)
		{
			throw new IllegalArgumentException("Cannot add before header");
		}
		Node1 u=getNext(v);
		z.setPrev(v);
		z.setNext(u);
		u.setPrev(z);
		v.setNext(z);
		size=size+1;
	}	
	public void addLast(Node1 v)
	{
		addBefore(trailer,v);
	}
	public void remove(Node1 v)
	{
		if ((v==header)||(v==trailer))
		{
			throw new IllegalArgumentException("Cannot remove header or trailer");
		}
		try
		{
			if (isMember(v.getElement()))
			{
				Node1 u=getPrev(v);
				Node1 w=getNext(v);
				v.setPrev(null);
				v.setNext(null);
				u.setNext(w);
				w.setPrev(u);
				size=size-1;
			}
			else
			{
				throw new Exception();
			}
		}
		catch(Exception e)
		{
			System.out.println("Element not found");
		}
	}
	public boolean isMember(WordEntry a)
	{
		Node1 c=header.getNext();
		while ((c.getElement()!=a)&&(c!=trailer))
		{
			c=c.getNext();
		}
		if (c!=trailer)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}