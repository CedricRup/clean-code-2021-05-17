package com.gildedrose.updater;

import com.gildedrose.Item;

public class RegularUpdater implements QualityUpdater {

    private final Item item;

    public RegularUpdater(Item item) {
        this.item = item;
    }

    @Override
    public Item updateQuality() {
        if (item.quality > 0) {
            item.quality--;
        }

        item.sellIn--;

        if (item.sellIn < 0) {
            if (item.quality > 0) {
                item.quality--;
            }
        }
        return item;
    }
}
