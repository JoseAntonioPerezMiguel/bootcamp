package com.gildedrose;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class GildedRoseTest {
	
	@ParameterizedTest(name = "Caso {index}: Item = {0}, SellIn = {1}, Quality = {2}")
	@CsvSource(value = {"'Aged Brie', 3, 0, 2, 1",
			"'Aged Brie', 0, 0, -1, 2",
			"'Aged Brie', 10, 54, 9, 54",
			"'Backstage passes to a TAFKAL80ETC concert', 10, 0, 9, 2",
			"'Backstage passes to a TAFKAL80ETC concert', 5, 0, 4, 3",
			"'Backstage passes to a TAFKAL80ETC concert', 0, 50, -1, 0",
			"'Backstage passes to a TAFKAL80ETC concert', 15, 24, 14, 25",
			"'Backstage passes to a TAFKAL80ETC concert', 24, 70, 23, 70",
			"'Sulfuras, Hand of Ragnaros', 3, 10, 3, 10",
			"'Backstage passes to a TAFKAL80ETC concert', 3, 50, 2, 50",
			"'Patata', 3, 0, 2, 0",
			"'Patata', 3, 3, 2, 2",
			"'Patata', -1, 3, -2, 1",
			"'Patata', -1, 1, -2, 0",
			"'Conjured Mana Cake', 3, 6, 2, 4",
			"'Conjured Mana Cake', -1, 6, -2, 2"})
	void updateQualityTest(String name, int sellIn, int quality, int expectedSellIn, int expectedQuality) {
		Item[] items = new Item[] { new Item(name, sellIn, quality) };
		GildedRose app = new GildedRose(items);
		app.updateQuality();
		assertEquals(expectedQuality, app.items[0].quality);
		assertEquals(expectedSellIn, app.items[0].sellIn);
	}
}
