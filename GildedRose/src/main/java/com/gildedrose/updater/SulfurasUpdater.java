package com.gildedrose.updater;

import com.gildedrose.Item;

public class SulfurasUpdater implements QualityUpdater {

    private final Item item;

    public SulfurasUpdater(Item item) {
        this.item = item;
    }

    @Override
    public Item updateQuality() {

        return item;
    }
}
