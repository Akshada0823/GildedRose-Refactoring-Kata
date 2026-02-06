package com.gildedrose

class GildedRose(val items: List<Item>) {
    public final var CONJURED ="Conjured Mana Cake"
    /**
   * Updates quality for given type of item as per the business rules.
     */
    fun updateQuality() {
        items.forEach { item ->
            when (item.name) {
                "Aged Brie" -> updateAgedBrie(item)
                "Backstage passes to a TAFKAL80ETC concert" -> updateBackstagePass(item)
                "Sulfuras, Hand of Ragnaros" -> { /* legendary, no operations */
                }

                else -> updateNormalorConjuredItem(item)
            }
        }

    }

    /**
     * Updates items that degrade in quality over time.
     *
     * Business rules:
     * - Normal items degrade by 1 per day.
     * - Conjured items degrade twice as fast.
     * - After the sell date, degradation doubles again.
     * - Quality is never negative.
     */
    private fun updateNormalorConjuredItem(item: Item) {

        val degradeRate = if (item.name == CONJURED) 2 else 1

        repeat(degradeRate) { decreaseQuality(item) }

        item.sellIn--

        if (item.sellIn < 0) {
            repeat(degradeRate) { decreaseQuality(item) }
        }
    }

    /**
     * Aged Brie increases in quality the older it gets.
     * Quality is capped at 50 .
     */
    private fun updateAgedBrie(item: Item) {
        increaseQuality(item)
        item.sellIn--

        if (item.sellIn < 0) {
            increaseQuality(item)
        }
    }

    /**
     * Backstage passes:
     *
     * +1 quality normally
     * +2 when 10 days or less
     * +3 when 5 days or fewer
     * Drops to 0 after the concert.
     */
    private fun updateBackstagePass(item: Item) {
        increaseQuality(item)

        if (item.sellIn < 11) increaseQuality(item)
        if (item.sellIn < 6) increaseQuality(item)

        item.sellIn--

        if (item.sellIn < 0) {
            item.quality = 0
        }
    }

    /**
     * Decreases quality but never below 0.
     */
    private fun decreaseQuality(item: Item) {
        if (item.quality > 0) {
            item.quality--
        }
    }
    /**
     * Increases quality but never above 50.
     */
    private fun increaseQuality(item: Item) {
        if (item.quality < 50) {
            item.quality++
        }
    }


}

