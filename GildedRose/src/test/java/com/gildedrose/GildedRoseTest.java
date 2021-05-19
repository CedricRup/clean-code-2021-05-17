package com.gildedrose;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class GildedRoseTest {

    private static final String SULFURAS =  "Sulfuras, Hand of Ragnaros";

    @Test
    void should_decrease_item_quality(){
        Item regularItem = new Item("Regular Item", 3, 10);

        GildedRose gildedRose = new GildedRose(new Item[] { regularItem } );
        gildedRose.updateQuality();

        Assertions.assertEquals(9, regularItem.quality);
    }

    @Test
    void should_decrease_twice_item_quality(){
        Item regularItem = new Item("Regular Item", 1, 10);
        GildedRose gildedRose = new GildedRose(new Item[] { regularItem } );

        gildedRose.updateQuality();
        gildedRose.updateQuality();

        Assertions.assertEquals(7, regularItem.quality);
    }

    @Test
    void should_decrease_to_zero_item_quality(){
        Item regularItem = new Item("Regular Item", 1, 1);
        GildedRose gildedRose = new GildedRose(new Item[] { regularItem } );

        gildedRose.updateQuality();
        gildedRose.updateQuality();

        Assertions.assertEquals(0, regularItem.quality);
    }

    @Test
    void should_decrease_sulfuras_item_quality(){
        Item regularItem = new Item(SULFURAS, 1, 10);
        GildedRose gildedRose = new GildedRose(new Item[] { regularItem } );

        gildedRose.updateQuality();
        gildedRose.updateQuality();

        Assertions.assertEquals(10, regularItem.quality);
    }

}
