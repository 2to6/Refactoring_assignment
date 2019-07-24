package ac.kr.ajou.dirt;

class DirtySample {
    Item[] items;

    public DirtySample(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            if(isEqualsName(item, "Backstage passes to a TAFKAL80ETC concert")){
                updateBackQuality(item);
            }
            else if(isEqualsName(item, "Aged Brie")){
                updateAgedQuality(item);
            }
            else if(isEqualsName(item, "Sulfuras, Hand of Ragnaros")){
                continue;
            }
            else{
                updateOtherQuality(item);
            }
        }
    }

    private void updateOtherQuality(Item item) {
        if(item.quality > 0) item.quality -= 1;
        item.sellIn -= 1;
        if(item.sellIn <= 0) item.quality -= 1;
    }

    private void updateAgedQuality(Item item) {
        item.sellIn -= 1;
        if(isQualityLessthan50(item)){
            item.quality += 1;
            if(item.sellIn <= 0 && isQualityLessthan50(item)) item.quality += 1;
        }
    }

    private boolean isQualityLessthan50(Item item) {
        return item.quality < 50;
    }

    private void updateBackQuality(Item item) {
        if(isQualityLessthan50(item)) {
            if(item.sellIn >= 11 && isQualityLessthan50(item)) item.quality += 1;
            else if(item.sellIn >= 6 && isQualityLessthan50(item)) item.quality += 2;
            else item.quality += 3;
        }
        item.sellIn -= 1;
        if(item.sellIn <= 0) item.quality = 0;
    }

    private boolean isEqualsName(Item item, String s) {
        return item.name.equals(s);
    }
}
