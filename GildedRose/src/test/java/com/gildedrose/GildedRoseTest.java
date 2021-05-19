package com.gildedrose;

import org.approvaltests.combinations.CombinationApprovals;
import org.junit.jupiter.api.Test;

class GildedRoseTest {

	private static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
	private static final String AGED_BRIE = "Aged Brie";
	private static final String BACKSTAGE = "Backstage passes to a TAFKAL80ETC concert";

	@Test
	void should_decrease_item_quality() {
		// Given
		final String[] names = new String[] { "Regular Item", SULFURAS, AGED_BRIE, BACKSTAGE };
		final Integer[] sellIns = new Integer[] { -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 };
		final Integer[] qualities = new Integer[] { 0, 10, 48, 49, 50, 51, 52, 61, 1000 };

		// When / Then
		CombinationApprovals.verifyAllCombinations(this::getItem, names, sellIns, qualities);
	}

	private Item getItem(String name, int sellIn, int quality) {
		final Item regularItem = new Item(name, sellIn, quality);

		final GildedRose gildedRose = new GildedRose(new Item[] { regularItem });
		gildedRose.updateQuality();
		return regularItem;
	}

}
