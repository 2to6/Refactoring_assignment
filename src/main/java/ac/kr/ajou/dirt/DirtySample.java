package ac.kr.ajou.dirt;

class DirtySample {
    Item[] items;

    private final String concertTicket = "Backstage passes to a TAFKAL80ETC concert";
    private final String cheese = "Aged Brie";
    private final String gameItem = "Sulfuras, Hand of Ragnaros";


    public DirtySample(Item[] items) {
        this.items = items;
    }
    
    public void updateQuality() {
        for (Item item : items) {
            if(isEqualsName(item, concertTicket)){
                updateTicketQuality(item);
                return;
            }
            if(isEqualsName(item, cheese)){
                updateCheeseQuality(item);
                return;
            }
            if(isEqualsName(item, gameItem)){
                return;
            }
            updateOtherItemQuality(item);
        }
        return;
    }

    private void updateOtherItemQuality(Item item) {
        item.expirePeriod -= 1;
        if(item.quality > 0) item.quality -= 1;
        if(item.expirePeriod <= 0) item.quality -= 1;
    }

    private void updateCheeseQuality(Item item) {
        item.expirePeriod -= 1;
        if(isQualityLessthan50(item)){
            item.quality += 1;
            if(item.expirePeriod <= 0 && isQualityLessthan50(item)) item.quality += 1;
        }
    }

    private boolean isQualityLessthan50(Item item) {
        return item.quality < 50;
    }

    private void updateTicketQuality(Item item) {
        if(isQualityLessthan50(item)) {
            if(item.expirePeriod >= 11 && isQualityLessthan50(item)) item.quality += 1;
            else if(item.expirePeriod >= 6 && isQualityLessthan50(item)) item.quality += 2;
            else item.quality += 3;
        }
        item.expirePeriod -= 1;
        if(item.expirePeriod <= 0) item.quality = 0;
    }

    private boolean isEqualsName(Item item, String itemName) {
        return item.name.equals(itemName);
    }
}
