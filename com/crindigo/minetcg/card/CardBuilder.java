package com.crindigo.minetcg.card;

public class CardBuilder
{
	private String _id;
	private String _name;
	private String _description;
	private Card.Rarity _rarity;
	private int _dirs;
	private Card.Element _element;
	private Card.Stats _stats;
	
	public CardBuilder(String id)
	{
		_id = id;
	}
	
	public CardBuilder name(String name)
	{
		_name = name;
		return this;
	}
	
	public CardBuilder desc(String desc)
	{
		_description = desc;
		return this;
	}
	
	public CardBuilder rarity(Card.Rarity rarity)
	{
		_rarity = rarity;
		return this;
	}
	
	public CardBuilder dirs(int dirs)
	{
		_dirs = dirs;
		return this;
	}
	
	public CardBuilder dirs(String dirspec)
	{
		int dirInt = 0;
		String[] parts = dirspec.split(",");
		for ( String part : parts ) {
			if ( part.equals("T") ) {
				dirInt |= Card.Dir.T;
			} else if ( part.equals("TR") ) {
				dirInt |= Card.Dir.TR;
			} else if ( part.equals("R") ) {
				dirInt |= Card.Dir.R;
			} else if ( part.equals("BR") ) {
				dirInt |= Card.Dir.BR;
			} else if ( part.equals("B") ) {
				dirInt |= Card.Dir.B;
			} else if ( part.equals("BL") ) {
				dirInt |= Card.Dir.BL;
			} else if ( part.equals("L") ) {
				dirInt |= Card.Dir.L;
			} else if ( part.equals("TL") ) {
				dirInt |= Card.Dir.TL;
			}
		}
		
		return dirs(dirInt);
	}
	
	public CardBuilder element(Card.Element element)
	{
		_element = element;
		return this;
	}
	
	public CardBuilder stats(Card.Stats stats)
	{
		_stats = stats;
		return this;
	}
	
	public Card get()
	{
		return new Card(_id, _name, _description, _rarity, _dirs, _element, _stats);
	}
}
