package com.gildedrose;

class GildedRose {

	private static final int MAX_QUALITY = 50;
	private static final String AGED_BRIE = "Aged Brie";
	private static final String BACKSTAGE = "Backstage passes to a TAFKAL80ETC concert";
	private static final String SULFURAS = "Sulfuras, Hand of Ragnaros";

	Item[] items;

	public GildedRose(Item[] items) {
		this.items = items;
	}

	public void updateQuality() {
		for (int i = 0; i < items.length; i++) {
			final Item item = items[i];

			if (item.name.equals(SULFURAS)) {
			} else {

				if (item.name.equals(AGED_BRIE) || item.name.equals(BACKSTAGE)) {
					if (isItemQualityUnderMax(item)) {
						item.quality++;

						if (item.name.equals(BACKSTAGE)) {
							if (item.sellIn < 11 && isItemQualityUnderMax(item)) {
								item.quality++;
							}

							if (item.sellIn < 6 && isItemQualityUnderMax(item)) {
								item.quality++;
							}
						}
					}
				} else if (item.quality > 0) {
					item.quality--;
				}

				item.sellIn--;

				if (item.sellIn < 0) {
					if (item.name.equals(AGED_BRIE)) {
						if (isItemQualityUnderMax(item)) {
							item.quality++;
						}
					} else if (item.name.equals(BACKSTAGE)) {
						item.quality = 0;
					} else if (item.quality > 0) {
						item.quality--;
					}
				}
			}
		}
	}

	private boolean isItemQualityUnderMax(final Item item) {
		return item.quality < MAX_QUALITY;
	}
}
