public class SearchResult implements Comparable<SearchResult>
{
    public PageEntry page;
    public float relevance;
    public SearchResult(PageEntry p,float r)
    {
        page=p;
        relevance=r;
    }
    public PageEntry getPageEntry()
    {
        return page;
    }
    public float getRelevance()
    {
        return relevance;
    }
    public int compareTo(SearchResult otherObject)
    {
        if (otherObject.relevance>relevance)
        {
            return 1;
        }
        else if (otherObject.relevance<relevance)
        {
            return -1;
        }
        else
        {
            return 0;
        }
    }
}
