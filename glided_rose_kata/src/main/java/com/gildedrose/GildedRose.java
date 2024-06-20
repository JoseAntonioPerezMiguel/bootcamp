package com.gildedrose;

import java.util.Arrays;

class GildedRose {
	Item[] items;

	public GildedRose(Item[] items) {
		this.items = items;
	}

	public void updateQuality2() {
		for (int i = 0; i < items.length; i++) {
			if (!items[i].name.equals("Aged Brie")
					&& !items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {
				if (items[i].quality > 0) {
					if (!items[i].name.equals("Sulfuras, Hand of Ragnaros")) {
						items[i].quality = items[i].quality - 1;
					}
				}
			} else {
				if (items[i].quality < 50) {
					items[i].quality = items[i].quality + 1;

					if (items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {
						if (items[i].sellIn < 11) {
							if (items[i].quality < 50) {
								items[i].quality = items[i].quality + 1;
							}
						}

						if (items[i].sellIn < 6) {
							if (items[i].quality < 50) {
								items[i].quality = items[i].quality + 1;
							}
						}
					}
				}
			}

			if (!items[i].name.equals("Sulfuras, Hand of Ragnaros")) {
				items[i].sellIn = items[i].sellIn - 1;
			}

			if (items[i].sellIn < 0) {
				if (!items[i].name.equals("Aged Brie")) {
					if (!items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {
						if (items[i].quality > 0) {
							if (!items[i].name.equals("Sulfuras, Hand of Ragnaros")) {
								items[i].quality = items[i].quality - 1;
							}
						}
					} else {
						items[i].quality = items[i].quality - items[i].quality;
					}
				} else {
					if (items[i].quality < 50) {
						items[i].quality = items[i].quality + 1;
					}
				}
			}
		}
	}

	public void updateQuality() {
		Arrays.stream(items);
		for (int i = 0; i < items.length; i++) {
			switch (items[i].name) {
			case "Aged Brie":
				items[i] = updateBrie(items[i]);
				break;
			case "Sulfuras, Hand of Ragnaros":
				break;
			case "Backstage passes to a TAFKAL80ETC concert":
				items[i] = updatePass(items[i]);
				break;
			default:
				items[i] = updateDefault(items[i]);
				break;
			}
		}

	}

	private Item updateBrie(Item item) {
		if (item.quality < 50) {
			if (item.sellIn > 0) {
				item.quality++;
			} else {
				item.quality += 2;
			}
		}
		item.sellIn--;
		return item;
	}

	private Item updatePass(Item item) {
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
		return item;
	}

	private Item updateDefault(Item item) {
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
		return item;
	}
}
