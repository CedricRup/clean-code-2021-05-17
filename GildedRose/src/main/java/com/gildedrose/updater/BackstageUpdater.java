package com.gildedrose.updater;

import com.gildedrose.Item;

public class BackstageUpdater implements QualityUpdater {

    private static final int MAX_QUALITY = 50;
    private final Item item;

    public BackstageUpdater(Item item) {
        this.item = item;
    }

    @Override
    public Item updateQuality() {
        if (item.quality < MAX_QUALITY) {
            item.quality++;

            if (item.sellIn < 11 && item.quality < MAX_QUALITY) {
                item.quality++;
            }

            if (item.sellIn < 6 && item.quality < MAX_QUALITY) {
                item.quality++;
            }
        }

        item.sellIn--;

        if (item.sellIn < 0) {
            item.quality = 0;
        }
        return item;
    }
}
