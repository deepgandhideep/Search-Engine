import java.util.*;
public class MySort
{
    ArrayList<SearchResult> sortThisList(MySet<SearchResult> listOfSortableEntries)
    {
        ArrayList<SearchResult> al=new ArrayList<>();
        Node<SearchResult> n1=listOfSortableEntries.a.header.getNext();
        while(n1!=listOfSortableEntries.a.trailer)
        {
            al.add(n1.getElement());
            n1=n1.getNext();
        }
        Collections.sort(al);
        return al;
    }
}
