package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GildedRoseTest {

//    @Test
//    void foo() {
//        Item[] items = new Item[] { new Item("foo", 0, 0) };
//        GildedRose app = new GildedRose(items);
//        app.updateQuality();
//        assertEquals("fixme", app.items[0].name);
//    }

	@Test
	void givenAgedBrie_WhenUpdatingQuality_ThenQualityRises() {
		Item[] items = new Item[] { new Item("Aged Brie", 3, 0) };
		GildedRose app = new GildedRose(items);
		app.updateQuality();
		assertEquals(1, app.items[0].quality);
	}
	
	@Test
	void givenAgedWithSellInZero_WhenUpdatingQuality_ThenQualityDoubleRises() {
		Item[] items = new Item[] { new Item("Aged Brie", 0, 0) };
		GildedRose app = new GildedRose(items);
		app.updateQuality();
		assertEquals(2, app.items[0].quality);
	}
	
	@Test
	void givenBackstagePassWithSellInTen_WhenUpdatingQuality_ThenQualityDoubleRises() {
		Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 10, 0) };
		GildedRose app = new GildedRose(items);
		app.updateQuality();
		assertEquals(2, app.items[0].quality);
	}
	
	@Test
	void givenBackstagePassWithSellInFive_WhenUpdatingQuality_ThenQualityTripleRises() {
		Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 5, 0) };
		GildedRose app = new GildedRose(items);
		app.updateQuality();
		assertEquals(3, app.items[0].quality);
	}
	
	@Test
	void givenBackstagePassWithSellInZero_WhenUpdatingQuality_ThenQualityDecrementsToZero() {
		Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 0, 50) };
		GildedRose app = new GildedRose(items);
		app.updateQuality();
		assertEquals(0, app.items[0].quality);
	}
	
	@Test
	void givenSulfurasWithTenQuality_WhenUpdatingQuality_ThenQualityStays() {
		Item[] items = new Item[] { new Item("Sulfuras, Hand of Ragnaros", 3, 10) };
		GildedRose app = new GildedRose(items);
		app.updateQuality();
		assertEquals(10, app.items[0].quality);
	}
	
	@Test
	void givenItemWith50Quality_WhenUpdatingQuality_ThenQualityDoesntRise() {
		Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 3, 50) };
		GildedRose app = new GildedRose(items);
		app.updateQuality();
		assertEquals(50, app.items[0].quality);
	}
	
	@Test
	void givenItemWith0Quality_WhenUpdatingQuality_ThenQualityStays() {
		Item[] items = new Item[] { new Item("Patata", 3, 0) };
		GildedRose app = new GildedRose(items);
		app.updateQuality();
		assertEquals(0, app.items[0].quality);
	}
	
	@Test
	void givenItemWith3Quality_WhenUpdatingQuality_ThenQualityDecrementsOne() {
		Item[] items = new Item[] { new Item("Patata", 3, 3) };
		GildedRose app = new GildedRose(items);
		app.updateQuality();
		assertEquals(2, app.items[0].quality);
	}
	
	@Test
	void givenItemWith3QualityAndSellin0_WhenUpdatingQuality_ThenQualityDecrementsDouble() {
		Item[] items = new Item[] { new Item("Patata", 0, 3) };
		GildedRose app = new GildedRose(items);
		app.updateQuality();
		assertEquals(1, app.items[0].quality);
	}
}
