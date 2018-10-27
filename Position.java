public  class Position
{
	private PageEntry page;
	private int wordPosition;
	public Position(PageEntry p,int wordIndex)
	{
		page=p;
		wordPosition=wordIndex;
	}
	PageEntry getPageEntry()
	{
		return page;
	}
	int getWordIndex()
	{
		return wordPosition;
	}
}