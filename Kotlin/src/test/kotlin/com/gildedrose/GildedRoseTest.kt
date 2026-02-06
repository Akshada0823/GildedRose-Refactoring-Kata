package com.gildedrose

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class GildedRoseTest {

    @Test
    fun `normal item decreases quality by 1 before sell date`() {
        val item = Item("Normal Item", 10, 20)
        val app = GildedRose(listOf(item))

        app.updateQuality()

        assertEquals(19, item.quality)
        assertEquals(9, item.sellIn)
    }

    @Test
    fun `normal item decreases quality twice after sell date`() {
        val item = Item("Normal Item", 0, 20)
        val app = GildedRose(listOf(item))

        app.updateQuality()

        assertEquals(18, item.quality)
        assertEquals(-1, item.sellIn)
    }

    @Test
    fun `quality never goes below zero`() {
        val item = Item("Normal Item", 5, 0)
        val app = GildedRose(listOf(item))

        app.updateQuality()

        assertEquals(0, item.quality)
    }

    @Test
    fun `aged brie increases in quality`() {
        val item = Item("Aged Brie", 5, 10)
        val app = GildedRose(listOf(item))

        app.updateQuality()

        assertEquals(11, item.quality)
        assertEquals(4, item.sellIn)
    }

    @Test
    fun `aged brie increases twice after sell date`() {
        val item = Item("Aged Brie", 0, 10)
        val app = GildedRose(listOf(item))

        app.updateQuality()

        assertEquals(12, item.quality)
    }

    @Test
    fun `aged brie quality never exceeds 50`() {
        val item = Item("Aged Brie", 5, 50)
        val app = GildedRose(listOf(item))

        app.updateQuality()

        assertEquals(50, item.quality)
    }

    @Test
    fun `backstage pass increases by 1 when sellIn greater than 10`() {
        val item = Item("Backstage passes to a TAFKAL80ETC concert", 15, 20)
        val app = GildedRose(listOf(item))

        app.updateQuality()

        assertEquals(21, item.quality)
    }

    @Test
    fun `backstage pass increases by 2 when sellIn is 10 or less`() {
        val item = Item("Backstage passes to a TAFKAL80ETC concert", 10, 20)
        val app = GildedRose(listOf(item))

        app.updateQuality()

        assertEquals(22, item.quality)
    }

    @Test
    fun `backstage pass increases by 3 when sellIn is 5 or less`() {
        val item = Item("Backstage passes to a TAFKAL80ETC concert", 5, 20)
        val app = GildedRose(listOf(item))

        app.updateQuality()

        assertEquals(23, item.quality)
    }

    @Test
    fun `backstage pass drops to zero after concert`() {
        val item = Item("Backstage passes to a TAFKAL80ETC concert", 0, 20)
        val app = GildedRose(listOf(item))

        app.updateQuality()

        assertEquals(0, item.quality)
    }

    @Test
    fun `sulfuras never changes`() {
        val item = Item("Sulfuras, Hand of Ragnaros", 0, 80)
        val app = GildedRose(listOf(item))

        app.updateQuality()

        assertEquals(80, item.quality)
        assertEquals(0, item.sellIn)
    }

    @Test
    fun `conjured item degrades twice as fast before sell date`() {

        val items = listOf(Item("Conjured Mana Cake", 10, 20))
        val app = GildedRose(items)

        app.updateQuality()

        assertEquals(18, items[0].quality)
        assertEquals(9, items[0].sellIn)
    }

    @Test
    fun `conjured item degrades four times as fast after sell date`() {

        val items = listOf(Item("Conjured Mana Cake", 0, 20))
        val app = GildedRose(items)

        app.updateQuality()

        assertEquals(16, items[0].quality)
        assertEquals(-1, items[0].sellIn)
    }

    @Test
    fun `conjured item quality never negative`() {

        val items = listOf(Item("Conjured Mana Cake", 5, 1))
        val app = GildedRose(items)

        app.updateQuality()

        assertEquals(0, items[0].quality)
    }

}