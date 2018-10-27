import java.util.*;
import java.io.*;
public class PageEntry
{
	public PageIndex pi=new PageIndex();
	public AvlTree avtree=new AvlTree();
	String na;
	int wi,wi1;
	float grp;
	public PageEntry(String pageName)
	{
		try
		{
			na=pageName;
			FileInputStream pe=new FileInputStream("webpages/"+pageName);
			Scanner s=new Scanner(pe);
			Scanner hs,hs2;
			String w;
			String[] sa={"a","an","the","they","these","this","for","is","are","was","of","or","and","does","will","whose"};
			wi=0;
			wi1=0;
			while (s.hasNextLine())
			{
				hs=new Scanner(s.nextLine());
				while (hs.hasNext())
				{
					hs2=new Scanner(modify(hs.next()));
					while (hs2.hasNext())
					{
						w=hs2.next();
						w=w.toLowerCase();
						wi1++;
						if (w.toLowerCase().equals("stacks"))
						{
							w="stack";
						}
						else if (w.toLowerCase().equals("applications"))
						{
							w="application";
						}
						else if (w.toLowerCase().equals("structures"))
						{
							w="structure";
						}
						int f=0;
						for (int j=0;j<sa.length;j++)
						{
							if (w.equals(sa[j]))
							{
								f=1;
							}
						}
						if (f==0)
						{
							wi++;
							Position pos=new Position(this,wi1);
							pi.addPositionForWord(w,pos);
						}
					}
				}
			}
			Node<WordEntry> n1=pi.getWordEntries().header.getNext();
			Node<Position> n2;
			while (n1!=pi.getWordEntries().trailer)
			{
				n2=n1.getElement().getAllPositionsForThisWord().header.getNext();
				while (n2!=n1.getElement().getAllPositionsForThisWord().trailer)
				{
					avtree.insert(n2.getElement(),n1.getElement().str);
					n2=n2.getNext();
				}
				n1=n1.getNext();
			}
		}
		catch(FileNotFoundException e1)
		{
			System.out.println("file not found");
		}
	}
	int totalWords()
	{
		return wi;
	}
	float getRelevanceOfPage(String str[], boolean doTheseWordsRepresentAPhrase)
	{
		return grp;
	}
	String modify(String a)
	{
		char[] ca={'{','}','[',']','<','>','=','(',')','.',',',';','"','?','#','!','-',':','\''};
		char[] st=new char[a.length()];
		String re="";
		for (int k=0;k<a.length();k++)
		{
			st[k]=a.charAt(k);
		}
		for (int i=0;i<a.length();i++)
		{
			if ((65<=(int)a.charAt(i))&&((int)a.charAt(i)<=90))
			{
				st[i]=(char)(((int)a.charAt(i))+32);
			}
			for (int j=0;j<ca.length;j++)
			{
				if (a.charAt(i)==ca[j])
				{
					st[i]=' ';
				}
			}
		}
		for (int k=0;k<st.length;k++)
		{
			re=re+st[k];
		}
		if (a.toLowerCase().equals("stacks"))
		{
			a="stack";
			return a;
		}
		else if (a.toLowerCase().equals("applications"))
		{
			a="application";
			return a;
		}
		else if (a.toLowerCase().equals("structures"))
		{
			a="structure";
			return a;
		}
		return re;
	}
}