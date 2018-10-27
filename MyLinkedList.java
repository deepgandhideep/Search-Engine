class Node<X>
{
	public X element;
	public Node<X> next,prev;
	public Node(X e,Node<X> p,Node<X> n)
	{
		element=e;
		next=n;
		prev=p;
	}
	public X getElement()
	{
		return element;
	}
	public Node<X> getNext()
	{
		return next;
	}
	public Node<X> getPrev()
	{
		return prev;
	}
	public void setElement(X e)
	{
		element=e;
	}
	public void setNext(Node<X> n)
	{
		next=n;
	}
	public void setPrev(Node<X> p)
	{
		prev=p;
	}
}
public class MyLinkedList<X>
{
	protected Node<X> header,trailer;
	protected int size;
	public MyLinkedList()
	{
		header=new Node<X>(null,null,null);
		trailer=new Node<X>(null,header,null);
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
	public Node<X> getFirst()
	{
		if (isEmpty())
		{
			throw new IllegalStateException("List is empty"); 
		}
		return header.getNext();
	}
	public Node<X> getLast()
	{
		if (isEmpty())
		{
			throw new IllegalStateException("List is empty"); 
		}
		return trailer.getPrev();
	}
	public Node<X> getPrev(Node<X> v)
	{
		if (v==header)
		{
			throw new IllegalArgumentException("Cannot move before header"); 
		}
		return v.getPrev();
	}
	public Node<X> getNext(Node<X> v)
	{
		if (v==trailer)
		{
			throw new IllegalArgumentException("Cannot move after the trailer"); 
		}
		return v.getNext();
	}
	public void addBefore(Node<X> v,Node<X> z)
	{
		if (v==header)
		{
			throw new IllegalArgumentException("Cannot add before header");
		}
		Node<X> u=getPrev(v);
		z.setPrev(u);
		z.setNext(v);
		v.setPrev(z);
		u.setNext(z);
		size=size+1;
	}
	public void addAfter(Node<X> v,Node<X> z)
	{
		if (v==trailer)
		{
			throw new IllegalArgumentException("Cannot add before header");
		}
		Node<X> u=getNext(v);
		z.setPrev(v);
		z.setNext(u);
		u.setPrev(z);
		v.setNext(z);
		size=size+1;
	}	
	public void addLast(Node<X> v)
	{
		addBefore(trailer,v);
	}
	public void remove(Node<X> v)
	{
		if ((v==header)||(v==trailer))
		{
			throw new IllegalArgumentException("Cannot remove header or trailer");
		}
		try
		{
			if (isMember(v.getElement()))
			{
				Node<X> u=getPrev(v);
				Node<X> w=getNext(v);
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
	public boolean isMember(X a)
	{
		Node<X> c=header.getNext();
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