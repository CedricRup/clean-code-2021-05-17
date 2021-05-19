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

			switch (item.name) {
				case SULFURAS:
					break;
				case AGED_BRIE:
					updateAgedBrieQuality(item);
					break;
				case BACKSTAGE:
					updateBackstageQuality(item);
					break;
				default:
					updateRegularItemsQuality(item);
					break;
			}
		}
	}

	private void updateRegularItemsQuality(Item item) {
		if (item.quality > 0) {
			item.quality--;
		}

		item.sellIn--;

		if (item.sellIn < 0) {
			if (item.quality > 0) {
				item.quality--;
			}
		}
	}

	private void updateBackstageQuality(Item item) {
		if (isItemQualityUnderMax(item)) {
			item.quality++;

			if (item.sellIn < 11 && isItemQualityUnderMax(item)) {
				item.quality++;
			}

			if (item.sellIn < 6 && isItemQualityUnderMax(item)) {
				item.quality++;
			}
		}

		item.sellIn--;

		if (item.sellIn < 0) {
			item.quality = 0;
		}
	}

	private void updateAgedBrieQuality(Item item) {
		if (isItemQualityUnderMax(item)) {
			item.quality++;
		}

		item.sellIn--;

		if (item.sellIn < 0) {
			if (isItemQualityUnderMax(item)) {
				item.quality++;
			}
		}
	}

	private boolean isItemQualityUnderMax(final Item item) {
		return item.quality < MAX_QUALITY;
	}
}
