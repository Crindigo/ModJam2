package com.crindigo.minetcg.card;

import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import com.crindigo.minetcg.item.TCGItems;

public class Card
{
	public static enum Rarity
	{
		Common("Common", 55, EnumRarity.common),
		Uncommon("Uncommon", 25, EnumRarity.uncommon),
		Rare("Rare", 15, EnumRarity.rare),
		Secret("Secret", 5, EnumRarity.epic);
		
		public final String name;
		public final int weight;
		public final EnumRarity mcRarity;
		
		Rarity(String name, int weight, EnumRarity mcRarity)
		{
			this.name = name;
			this.weight = weight;
			this.mcRarity = mcRarity;
		}
	}
	
	public static enum Element
	{
		// technology disproves magic, magic manipulates nature, nature reclaims technology
		Tech("Technology"), // great against magic
		Magic("Magic"), // great against nature
		Nature("Nature"), // great against tech
		
		Magitech("Magitech"), // tech + magic, good against both, weak against nature
		Aura("Aura"), // magic + nature, good against both, weak against tech
		Green("Green"), // tech + nature, good against both, weak against magic
		Supreme("Supreme"); // good against all
		
		public final String name;
		
		Element(String name)
		{
			this.name = name;
		}
	}
	
	public static class Dir
	{
		// casting on bitwise operations which always return int was annoying
		public static int T = 1;
		public static int TR = 2;
		public static int R = 4;
		public static int BR = 8;
		public static int B = 16;
		public static int BL = 32;
		public static int L = 64;
		public static int TL = 128;
		
		// shortcut definitions
		public static int ALL = T | TR | R | BR | B | BL | L | TL;
		public static int ALL_TOP = TL | T | TR;
		public static int ALL_RIGHT = TR | R | BR;
		public static int ALL_BOTTOM = BR | B | BL;
		public static int ALL_LEFT = BL | L | TL;
		public static int ALL_DIAG = TR | BR | BL | TL;
		
		public static int opposite(int dir)
		{
			return dir < B ? (dir << 4) : (dir >> 4);
		}
	}
	
	public static class Stats
	{
		public short level = 1;
		public int experience = 0;
		
		public short power = 0;
		public short defense = 0;
		public short health = 0;
		public short curHealth = 0;
		
		public Stats(int power, int defense, int health)
		{
			this.power = (short) power;
			this.defense = (short) defense;
			this.health = (short) health;
			this.curHealth = this.health;
		}
		
		public Stats(int power, int defense, int health, short level, int experience)
		{
			this(power, defense, health);
			this.level = level;
			this.experience = experience;
		}
		
		private void levelUp()
		{
			if ( level >= 10 ) {
				return;
			}
			
			while ( experience >= 1000 ) {
				experience -= 1000;
				level++;
				double rnd = Math.random();
				if ( rnd < 1.0 / 3.0 ) {
					power++;
				} else if ( rnd <= 2.0 / 3.0 ) {
					defense++;
				} else {
					health += 2;
				}
				
				if ( level >= 10 ) {
					break;
				}
			}
		}
		
		public Stats copy()
		{
			return new Stats(power, defense, health, level, experience);
		}
	}
	
	private String id;
	private String name;
	private String description;
	private Rarity rarity;
	private int dirs = 0;
	private Element element;
	private Stats stats;
	
	public Card(String id, String name, String description, Rarity rarity, int dirs, Element element, Stats stats)
	{
		this.id = id;
		this.name = name;
		this.description = description;
		this.rarity = rarity;
		this.dirs = dirs;
		this.element = element;
		this.stats = stats;
	}
	
	public String getId()
	{
		return id;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getDescription()
	{
		return description;
	}
	
	public Rarity getRarity()
	{
		return rarity;
	}
	
	public int getDirs()
	{
		return dirs;
	}
	
	public Element getElement()
	{
		return element;
	}
	
	public Stats getStats()
	{
		return stats;
	}
	
	public boolean hasDir(int dir)
	{
		return (dirs & dir) != 0;
	}
	
	public void defeatedCard(Card other)
	{
		int exp = 100;
		exp += 10 * (other.getStats().level - this.stats.level); // more for beating higher level, less for lower level
		
		if ( this.element == Element.Supreme ) {
			exp /= 2; // takes longer to level up
		} else if ( this.element == Element.Magitech || this.element == Element.Aura || this.element == Element.Green ) {
			exp = (int)(exp * 0.75);
		}
		
		this.stats.experience += exp;
		this.stats.levelUp();
	}
	
	public ItemStack getItemStack()
	{
		ItemStack stack = new ItemStack(TCGItems.card);
		
		NBTTagCompound cardNbt = new NBTTagCompound();
		cardNbt.setString("Id", id);
		cardNbt.setShort("Lv", stats.level);
		cardNbt.setInteger("Xp", stats.experience);
		cardNbt.setShort("Hp", stats.health);
		cardNbt.setShort("Pwr", stats.power);
		cardNbt.setShort("Def", stats.defense);
		stack.setTagInfo("tcgCard", cardNbt);
		stack.setItemName(name);
		
		return stack;
	}
	
	public static Card createFromItemStack(ItemStack stack)
	{
		if ( !stack.hasTagCompound() || !stack.getTagCompound().hasKey("tcgCard") ) {
			return null;
		}

		NBTTagCompound nbt = stack.getTagCompound().getCompoundTag("tcgCard");
		if ( !CardRegistry.has(nbt.getString("Id")) ) {
			return null;
		}
		
		Card card = CardRegistry.get(nbt.getString("Id")).copy();
		card.stats.level = nbt.getShort("Lv");
		card.stats.experience = nbt.getInteger("Xp");
		card.stats.health = nbt.getShort("Hp");
		card.stats.power = nbt.getShort("Pwr");
		card.stats.defense = nbt.getShort("Def");
		
		return card;
	}
	
	public Card copy()
	{
		return new Card(id, name, description, rarity, dirs, element, stats.copy());
	}
}
