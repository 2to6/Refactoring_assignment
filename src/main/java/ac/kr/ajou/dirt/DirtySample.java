package ac.kr.ajou.dirt;

class DirtySample {
    Item[] items;

    public DirtySample(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            if(isEqualsName(item, "Backstage passes to a TAFKAL80ETC concert")){
                update_Back_Quality(item);
            }
            else if(isEqualsName(item, "Aged Brie")){
                update_Aged_Quality(item);
            }
            else if(isEqualsName(item, "Sulfuras, Hand of Ragnaros")){
                continue;
            }
            else{
                update_other_quality(item);
            }
        }
    }

    private void update_other_quality(Item item) {
        if(item.quality < 50) item.quality -= 1;
        item.sellIn -= 1;
        if(item.sellIn <= 0 && item.quality > 0) item.quality -= 1;
    }

    private void update_Aged_Quality(Item item) {
        item.sellIn -= 1;
        if(item.quality < 50){
            if(item.sellIn <= 0) item.quality += 1;
            item.quality += 1;
        }
    }

    private void update_Back_Quality(Item item) {
        if(item.quality < 50) {
            if(item.sellIn >= 11) item.quality += 1;
            else if(item.sellIn >= 6) item.quality += 2;
            else item.quality += 3;
        }
        item.sellIn -= 1;
        if(item.sellIn <= 0) item.quality = 0;
    }

    private boolean isEqualsName(Item item, String s) {
        return item.name.equals(s);
    }
}