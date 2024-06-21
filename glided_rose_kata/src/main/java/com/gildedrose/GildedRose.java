package com.gildedrose;

class GildedRose {
	Item[] items;

	public GildedRose(Item[] items) {
		this.items = items;
	}

	public void updateQuality() {
		for (int i = 0; i < items.length; i++) {
			switch (items[i].name) {
			case "Aged Brie":
				updateBrie(items[i]);
				break;
			case "Sulfuras, Hand of Ragnaros":
				break;
			case "Backstage passes to a TAFKAL80ETC concert":
				updatePass(items[i]);
				break;
			case "Conjured Mana Cake":
				updateConjured(items[i]);
				break;
			default:
				updateDefault(items[i]);
				break;
			}
		}

	}

	private void updateConjured(Item item) {
		if(item.quality > 0) {
			if(item.sellIn >= 0) {
				item.quality -= 2;
			} else {
				item.quality -= 4;
			}
		}
		item.sellIn--;
	}

	private void updateBrie(Item item) {
		if (item.quality < 50) {
			if (item.sellIn > 0) {
				item.quality++;
			} else {
				item.quality += 2;
			}
		}
		item.sellIn--;
	}

	private void updatePass(Item item) {
		if (item.quality < 50) {
			if (item.sellIn > 10) {
				item.quality++;
			} else if (item.sellIn <= 10 && item.sellIn > 5) {
				item.quality += 2;
			} else if (item.sellIn <= 5 && item.sellIn > 0) {
				item.quality += 3;
			}
		} else if (item.sellIn == 0) {
			item.quality = 0;
		}
		item.sellIn--;
	}

	private void updateDefault(Item item) {
		if (item.quality > 0) {
			if (item.sellIn >= 0) {
				item.quality--;
			} else if (item.quality == 1) {
				item.quality = 0;
			} else {
				item.quality -= 2;
			}

		}
		item.sellIn--;
	}
}
