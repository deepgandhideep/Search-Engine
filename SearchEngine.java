import java.util.*;
public class SearchEngine
{
	public InvertedPageIndex ipi;
	public SearchEngine()
	{
		ipi=new InvertedPageIndex();
	}
	void performAction(String actionMessage)
	{
		if((actionMessage.substring(0,7)).equals("addPage"))
		{
			String[] st=actionMessage.split(" ");
			PageEntry p=new PageEntry(st[1]);
			ipi.addPage(p);
		}
		else if ((actionMessage.substring(0,30)).equals("queryFindPagesWhichContainWord"))
		{
			String[] st=actionMessage.split(" ");
			st[1]=st[1].toLowerCase();
			if (st[1].equals("stacks"))
			{
				st[1]="stack";
			}
			else if (st[1].equals("applications"))
			{
				st[1]="application";
			}
			else if (st[1].equals("structures"))
			{
				st[1]="structure";
			}
			MySet<PageEntry> my=ipi.getPagesWhichContainWord(st[1]);
			if (my==null)
			{
				System.out.println("No webpage contains word "+st[1]);
				
			}
			else
			{
				Node<PageEntry> fi=my.a.header.getNext();
				String[] ar=new String[my.a.size()];
				WordEntry w1=ipi.ht.getWord(st[1]);
				for (int i=0;i<my.a.size();i++)
				{
					ar[i]=fi.getElement().na;
					fi=fi.getNext();
				}
				String temp;
				for (int i=0;i<my.a.size();i++)
				{
					for (int j=0;j<my.a.size()-i-1;j++)
					{
						if (w1.getTermFrequency(ar[j])<w1.getTermFrequency(ar[j+1]))
						{
							temp=ar[j];
							ar[j]=ar[j+1];
							ar[j+1]=temp;
						}
					}
				}
				String re="";
				for (int i=0;i<my.a.size()-1;i++)
				{
					re=re+ar[i]+", ";
				}
				re=re+ar[my.a.size()-1];
				System.out.println(re);
			}
		}
		else if ((actionMessage.substring(0,31)).equals("queryFindPositionsOfWordInAPage"))
		{
			String[] st=actionMessage.split(" ");
			st[1]=st[1].toLowerCase();
			if (st[1].equals("stacks"))
			{
				st[1]="stack";
			}
			else if (st[1].equals("applications"))
			{
				st[1]="application";
			}
			else if (st[1].equals("structures"))
			{
				st[1]="structure";
			}
			int flag=0,f=0;
			MyLinkedList<PageEntry> hpe=ipi.peo;
			Node<PageEntry> n=hpe.header.getNext();
			PageEntry hp=null;
			WordEntry wee=null;
			String rs="";
			while (n!=hpe.trailer)
			{
				if (n.getElement().na.equals(st[2]))
				{
					hp=n.getElement();
					f=1;
					break;
				}
				n=n.getNext();
			}
			if (f==1)
			{
				MyLinkedList<WordEntry> hwe=hp.pi.getWordEntries();
				Node<WordEntry> n1=hwe.header.getNext();
				while (n1!=hwe.trailer)
				{
					if (n1.getElement().str.equals(st[1]))
					{
						flag=1;
						wee=n1.getElement();
					}
					n1=n1.getNext();
				}
			}
			else
			{
				System.out.println("No webpage "+st[2]+" found");
			}
			if (flag==1)
			{
				MyLinkedList<Position> hpo=wee.getAllPositionsForThisWord();
				Node<Position> n2=hpo.header.getNext();
				while (n2!=hpo.trailer.getPrev())
				{
					rs = rs + n2.getElement().getWordIndex() + ", ";
					n2 = n2.getNext();
				}
				rs=rs+n2.getElement().getWordIndex();
				System.out.println(rs);
			}
			else if(f==1)
			{
				System.out.println("Webpage "+st[2]+" does not contain word "+st[1]);
			}
		}
		else if ((actionMessage.substring(0,32)).equals("queryFindPagesWhichContainPhrase"))
		{
			String[] str = actionMessage.split(" ");
			String[] str1=new String[str.length-1];
			for (int k=0;k<str1.length;k++)
			{
				str1[k]=str[k+1];
				str1[k]=str1[k].toLowerCase();
				if (str1[k].equals("stacks"))
				{
					str1[k]="stack";
				}
				else if (str1[k].equals("applications"))
				{
					str1[k]="application";
				}
				else if (str1[k].equals("structures"))
				{
					str1[k]="structure";
				}
			}
			MySet<PageEntry> pe = ipi.getPagesWhichContainPhrase(str1);
			if (pe==null)
			{
				System.out.println("No Webpage conntains "+str1);
			}
			else
			{
				SearchResult[] sr = new SearchResult[pe.a.size()];
				MySet<SearchResult> mysr = new MySet<>();
				Node<PageEntry> n1 = pe.a.header.getNext();
				int i = 0;
				while (n1 != pe.a.trailer) {
					ipi.getRelevance(str1, true, n1.getElement());
					sr[i] = new SearchResult(n1.getElement(), n1.getElement().getRelevanceOfPage(str, true));
					mysr.addElement(sr[i]);
					n1 = n1.getNext();
					i++;
				}
				MySort ms = new MySort();
				ArrayList<SearchResult> alsr = ms.sortThisList(mysr);
				String re = "";
				for (int j = 0; j < alsr.size() - 1; j++) {
					re = re + alsr.get(j).page.na + ", ";
				}
				re = re + alsr.get(alsr.size() - 1).page.na;
				System.out.println(re);
			}
		}
		else if ((actionMessage.substring(0,34)).equals("queryFindPagesWhichContainAllWords"))
		{
			String[] str=actionMessage.split(" ");
			for (int k=0;k<str.length;k++)
			{
				str[k]=str[k].toLowerCase();
				if (str[k].equals("stacks"))
				{
					str[k]="stack";
				}
				else if (str[k].equals("applications"))
				{
					str[k]="application";
				}
				else if (str[k].equals("structures"))
				{
					str[k]="structure";
				}
			}
			MySet<PageEntry> pe=ipi.getPagesWhichContainWord(str[1]);
			int len=str.length;
			for (int i=2;i<len;i++)
			{
				pe=pe.intersection(ipi.getPagesWhichContainWord(str[i]));
			}
			if (pe==null)
			{
				System.out.println("No webpage contain all words "+actionMessage.substring(35,actionMessage.length()));
			}
			else
			{
				SearchResult[] sr = new SearchResult[pe.a.size()];
				MySet<SearchResult> mysr = new MySet<>();
				Node<PageEntry> n1 = pe.a.header.getNext();
				int i = 0;
				while (n1 != pe.a.trailer) {
					ipi.getRelevance(str, false, n1.getElement());
					sr[i] = new SearchResult(n1.getElement(), n1.getElement().getRelevanceOfPage(str, false));
					mysr.addElement(sr[i]);
					n1 = n1.getNext();
					i++;
				}
				MySort ms = new MySort();
				ArrayList<SearchResult> alsr = ms.sortThisList(mysr);
				String re ="";
				for (int j = 0; j < alsr.size() - 1; j++) {
					re = re + alsr.get(j).page.na +", ";
				}
				re = re + alsr.get(alsr.size() - 1).page.na;
				System.out.println(re);
			}
		}
		else if ((actionMessage.substring(0,41)).equals("queryFindPagesWhichContainAnyOfTheseWords"))
		{
			String[] str = actionMessage.split(" ");
			for (int k=0;k<str.length;k++)
			{
				str[k]=str[k].toLowerCase();
				if (str[k].equals("stacks"))
				{
					str[k]="stack";
				}
				else if (str[k].equals("applications"))
				{
					str[k]="application";
				}
				else if (str[k].equals("structures"))
				{
					str[k]="structure";
				}
			}
			MySet<PageEntry> pe = ipi.getPagesWhichContainWord(str[1]);
			int len = str.length;
			for (int i = 2; i < len; i++)
			{
				pe = pe.union(ipi.getPagesWhichContainWord(str[i]));
			}
			if (pe==null)
			{
				System.out.println("No webpage contain all words "+actionMessage.substring(35,actionMessage.length()));
			}
			else
			{
				SearchResult[] sr = new SearchResult[pe.a.size()];
				MySet<SearchResult> mysr = new MySet<>();
				Node<PageEntry> n1 = pe.a.header.getNext();
				int i = 0;
				while (n1 != pe.a.trailer) {
					ipi.getRelevance(str, false, n1.getElement());
					sr[i] = new SearchResult(n1.getElement(), n1.getElement().getRelevanceOfPage(str, false));
					mysr.addElement(sr[i]);
					n1 = n1.getNext();
					i++;
				}
				MySort ms = new MySort();
				ArrayList<SearchResult> alsr = ms.sortThisList(mysr);
				String re ="";
				for (int j = 0; j < alsr.size() - 1; j++) {
					re = re + alsr.get(j).page.na + ", ";
				}
				re = re + alsr.get(alsr.size() - 1).page.na;
				System.out.println(re);
			}
		}
		else
		{
			System.out.println("Invalid action message");
		}
	}
}