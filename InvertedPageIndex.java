
import java.lang.*;
public class InvertedPageIndex
{
	public MyHashTable ht=new MyHashTable();
	public MyLinkedList<String> pet=new MyLinkedList<String>();
	public MyLinkedList<PageEntry> peo=new MyLinkedList<>();
	void addPage(PageEntry p)
	{
		Node<String> n=new Node<String>(p.na,null,null);
		Node<PageEntry> ne=new Node<PageEntry>(p,null,null);
		pet.addLast(n);
		peo.addLast(ne);
		Node<WordEntry> fir=p.pi.pill.header.getNext();
		for (int i=0;i<p.pi.pill.size();i++)
		{
			ht.addPositionsForWord(fir.getElement());
			fir=fir.getNext();
		}
	}
	MySet<PageEntry> getPagesWhichContainWord(String str)
	{
		WordEntry w=ht.getWord(str);
		if (w!=null)
		{
			MySet<PageEntry> re=new MySet<PageEntry>();
			Node<Position> f=w.getAllPositionsForThisWord().header.getNext();
			int s=w.getAllPositionsForThisWord().size();
			for (int i=0;i<s;i++)
			{
				re.addElement(f.getElement().getPageEntry());
				f=f.getNext();
			}
			return re;
		}
		else
		{
			return null;
		}
	}
	MySet<PageEntry>getPagesWhichContainPhrase(String str[])
	{
		MySet<PageEntry> ans=new MySet<>();
		MySet<PageEntry> pe=this.getPagesWhichContainWord(str[0]);
		WordEntry w=null;
		Node<WordEntry> n2=null;
		Node<Position> n3=null;
		Noode n4=null;
		int flag=0;
		int len=str.length;
		for (int i=1;i<len;i++)
		{
			if (pe==null)
            {
                System.out.println("null");
            }
            else
                {
                pe = pe.intersection(getPagesWhichContainWord(str[i]));
            }
		}
		Node<PageEntry> n1=pe.a.header.getNext();
		while (n1!=pe.a.trailer)
		{
			n2=n1.getElement().pi.pill.header.getNext();
			while (n2!=n1.getElement().pi.pill.trailer)
			{
				if (n2.getElement().str.equals(str[0]))
				{
					w=n2.getElement();
					break;
				}
				n2=n2.getNext();
			}
			n3=w.getAllPositionsForThisWord().header.getNext();
			while (n3!=w.getAllPositionsForThisWord().trailer)
			{
				n4=n1.getElement().avtree.find(n3.getElement()).h;
				for (int i=1;i<len;i++)
				{
					if (n1.getElement().avtree.successor(n4)==null)
					{
						break;
					}
					else if (n1.getElement().avtree.successor(n4).word.equals(str[i]))
					{
						n4=n1.getElement().avtree.successor(n4);
						if (i==len-1)
						{
							flag=1;
							break;
						}
					}
					else
					{
						break;
					}
				}
				if (flag==1)
				{
					ans.addElement(n1.getElement());
					break;
				}
				n3=n3.getNext();
			}
			n1=n1.getNext();
		}
		return ans;
	}
	double inverseDocumentFrequency (String s)
    {
        float N=peo.size();
        float nw=getPagesWhichContainWord(s).a.size();
        double re=Math.log(N/nw);
        return re;
    }
	void getRelevance(String str[], boolean doTheseWordsRepresentAPhrase, PageEntry p)
    {
        float rel=0;
        float m=0;
        WordEntry w=null;
        int flag=0;
        if (doTheseWordsRepresentAPhrase)
        {
            if (getPagesWhichContainPhrase(str).IsMember(p))
            {
                Node<WordEntry> n2=p.pi.pill.header.getNext();
                while (n2!=p.pi.pill.trailer)
                {
                    if (n2.getElement().str.equals(str[0]))
                    {
                        w=n2.getElement();
                        break;
                    }
                    n2=n2.getNext();
                }
                Node<Position> n3=w.getAllPositionsForThisWord().header.getNext();
                while (n3!=w.getAllPositionsForThisWord().trailer)
                {
                    Noode n4 =p.avtree.find(n3.getElement()).h;
                    for (int i = 1; i < str.length; i++)
                    {
                        if (p.avtree.successor(n4) == null)
                        {
                            break;
                        }
                        else if (p.avtree.successor(n4).word.equals(str[i]))
                        {
                            n4 = p.avtree.successor(n4);
                            if (i == str.length - 1)
                            {
                                flag = 1;
                                m++;
                            }
                        }
                        else
                        {
                            break;
                        }
                    }
                    n3=n3.getNext();
                }
                rel=(m/(p.totalWords()-(str.length-1)*m))*((float)(Math.log((float)peo.size/(float)getPagesWhichContainPhrase(str).a.size())));
            }
            else
            {
                rel=0;
            }
        }
        else
        {
            Node<WordEntry> n2;
            WordEntry w1=null;
            for (int i=0;i<str.length;i++)
            {
				w1=null;
                n2=p.pi.pill.header.getNext();
                while (n2!=p.pi.pill.trailer)
                {
                    if (n2.getElement().str.equals(str[i]))
                    {
                        w1=n2.getElement();
                        break;
                    }
                    n2=n2.getNext();
                }
                if (w1!=null)
                {
                    rel = rel+w1.getTermFrequency(p.na)* (float) inverseDocumentFrequency(str[i]);
                }
            }
        }
        p.grp=rel;
    }
}