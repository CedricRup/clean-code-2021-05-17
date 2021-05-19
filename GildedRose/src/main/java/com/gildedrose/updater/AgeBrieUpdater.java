package com.gildedrose.updater;

import com.gildedrose.Item;

public class AgeBrieUpdater implements QualityUpdater {

    private static final int MAX_QUALITY = 50;
    private final Item item;

    public AgeBrieUpdater(Item item) {
        this.item = item;
    }

    @Override
    public Item updateQuality() {
        if (item.quality < MAX_QUALITY) {
            item.quality++;
        }

        item.sellIn--;

        if (item.sellIn < 0) {
            if (item.quality < MAX_QUALITY) {
                item.quality++;
            }
        }
        return item;
    }
}
