package com.gildedrose

class GildedRose(val items: List<Item>) {

    fun updateQuality() {
        items.forEach { item ->
            when (item.name) {
                "Aged Brie" -> updateAgedBrie(item)
                "Backstage passes to a TAFKAL80ETC concert" -> updateBackstagePass(item)
                "Sulfuras, Hand of Ragnaros" -> { /* legendary, no-op */
                }

                else -> updateNormalorConjuredItem(item)
            }
        }

    }


    private fun updateNormalorConjuredItem(item: Item) {

        val degradeRate = if (item.name.startsWith("Conjured")) 2 else 1

        repeat(degradeRate) { decreaseQuality(item) }

        item.sellIn--

        if (item.sellIn < 0) {
            repeat(degradeRate) { decreaseQuality(item) }
        }
    }

    private fun updateAgedBrie(item: Item) {
        increaseQuality(item)
        item.sellIn--

        if (item.sellIn < 0) {
            increaseQuality(item)
        }
    }

    private fun updateBackstagePass(item: Item) {
        increaseQuality(item)

        if (item.sellIn < 11) increaseQuality(item)
        if (item.sellIn < 6) increaseQuality(item)

        item.sellIn--

        if (item.sellIn < 0) {
            item.quality = 0
        }
    }

    private fun decreaseQuality(item: Item) {
        if (item.quality > 0) {
            item.quality--
        }
    }

    private fun increaseQuality(item: Item) {
        if (item.quality < 50) {
            item.quality++
        }
    }


}

