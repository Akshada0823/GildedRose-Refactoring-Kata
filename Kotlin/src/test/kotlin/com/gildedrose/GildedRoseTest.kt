package com.gildedrose

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class GildedRoseTest {

    @Test
    fun foo() {
        val items = listOf(Item("foo", 0, 0))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals("fixme", app.items[0].name)

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


