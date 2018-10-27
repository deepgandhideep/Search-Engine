public class MySet<X>
{
	public MyLinkedList<X> a=new MyLinkedList<X>();
	public boolean IsEmpty()
	{
		return (a.isEmpty());
	}
	public boolean IsMember(X o)
	{
		return (a.isMember(o));
	}
	public void addElement(X Element)
	{
		if (!(IsMember(Element)))
		{
			Node<X> n=new Node<X>(Element,null,null);
			a.addLast(n);
		}
	}
	public void Delete(X o)
	{
		
		if (a.isMember(o))
		{
			Node<X> c=a.header.getNext();
			while (c.getElement()!=o)
			{
				c=c.getNext();
			}
			a.remove(c);
		}
	}
	public MySet<X> union(MySet<X> otherSet)
	{
		MySet<X> b=new MySet<X>();
		if ((this==null)&&(otherSet==null))
		{
			return null;
		}
		else if (this==null)
		{
			return otherSet;
		}
		else
		{
			Node<X> th=this.a.header.getNext();
			while (th!=this.a.trailer)
			{
				b.addElement((th.getElement()));
				th=th.getNext();
			}
			MyLinkedList<X> l=otherSet.a;
			Node<X> c=l.header.getNext();
			while (c!=l.trailer)
			{
				if (this.IsMember((c.getElement()))==false)
				{
					b.addElement((c.getElement()));
				}
				c=c.getNext();
			}
			return b;
		}
	}
	public MySet<X> intersection(MySet<X> otherSet)
	{
		MySet<X> b=new MySet<X>();
		if ((this==null)||(otherSet==null))
		{
			return null;
		}
		else
		{	
			MyLinkedList<X> l=otherSet.a;
			Node<X> c=l.header.getNext();
			while (c!=l.trailer)
			{
				if (this.IsMember((c.getElement())))
				{
					b.addElement((c.getElement()));
				}
				c=c.getNext();
			}
			return b;
		}	
	}	
}