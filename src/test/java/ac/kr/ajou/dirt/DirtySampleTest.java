package ac.kr.ajou.dirt;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class DirtySampleTest {
    private Item[] testList;

    @Test
    public void Aged_Brie의_SellIn이_0이상이면_퀄리티가_플러스1 () {
        testList = new Item[1];
        Item item1 = new Item("Aged Brie",2,49);
        testList[0] = item1;
        int testQuality = item1.quality + 1;

        DirtySample dirtySample = new DirtySample(testList);
        dirtySample.updateQuality();

        testEqualsQuality(testQuality, dirtySample, 0);
    }

    @Test
    public void Aged_Brie의_SellIn이_0미만이면_퀄리티가_플러스2 () {
        testList = new Item[1];
        Item item1 = new Item("Aged Brie",-1,5);
        testList[0] = item1;
        int testQuality = item1.quality + 2;

        DirtySample dirtySample = new DirtySample(testList);
        dirtySample.updateQuality();

        testEqualsQuality(testQuality, dirtySample, 0);
    }

    @Test
    public void Backstage가_11일이상이면_퀄리티가_플러스1 () {
        testList = new Item[1];
        Item item1 = new Item("Backstage passes to a TAFKAL80ETC concert",12,5);
        testList[0] = item1;
        int testQuality = item1.quality + 1;

        DirtySample dirtySample = new DirtySample(testList);
        dirtySample.updateQuality();

        testEqualsQuality(testQuality, dirtySample, 0);
    }


    @Test
    public void Backstage가_6일이상_11일미만이면_퀄리티가_플러스1 () {
        testList = new Item[1];
        Item item1 = new Item("Backstage passes to a TAFKAL80ETC concert",7,5);
        testList[0] = item1;
        int testQuality = item1.quality + 2;

        DirtySample dirtySample = new DirtySample(testList);
        dirtySample.updateQuality();

        testEqualsQuality(testQuality, dirtySample, 0);
    }

    @Test
    public void Backstage가_0일초과_6일미만이면_퀄리티가_플러스3 () {
        testList = new Item[1];
        Item item1 = new Item("Backstage passes to a TAFKAL80ETC concert",3,5);
        testList[0] = item1;
        int testQuality = item1.quality + 3;

        DirtySample dirtySample = new DirtySample(testList);
        dirtySample.updateQuality();

        testEqualsQuality(testQuality, dirtySample, 0);
    }

    @Test
    public void Backstage가_0일이면_퀄리티는_무조건_0 () {
        testList = new Item[1];
        Item item1 = new Item("Backstage passes to a TAFKAL80ETC concert",0,5);
        testList[0] = item1;
        int testQuality = 0;

        DirtySample dirtySample = new DirtySample(testList);
        dirtySample.updateQuality();

        testEqualsQuality(testQuality, dirtySample, 0);
    }


    @Test
    public void Sulfuras는_절대_SellIn과_퀄리티가_변하지_않는다 () {
        testList = new Item[1];
        Item item1 = new Item("Sulfuras, Hand of Ragnaros",23,100000000);
        testList[0] = item1;
        int testQuality = item1.quality;

        DirtySample dirtySample = new DirtySample(testList);
        dirtySample.updateQuality();

        testEqualsQuality(testQuality, dirtySample, 0);
    }



    @Test
    public void 그외_상품일때_sellIn이_0일이하면_퀄리티가_마이너스2이고_1일이상이면_퀄리티가_마이너스1 () {
        testList = new Item[4];
        Item item1 = new Item("hhh",12,60);
        Item item2 = new Item("hhh",9,11);
        Item item3 = new Item("hhh",4,11);
        Item item4 = new Item("hhh",0,60);


        testList[0] = item1;
        testList[1] = item2;
        testList[2] = item3;
        testList[3] = item4;

        int testQuality1 = item1.quality - 1;
        int testQuality2 = item2.quality - 1;
        int testQuality3 = item3.quality - 1;
        int testQuality4 = item4.quality - 2;

        DirtySample dirtySample = new DirtySample(testList);
        dirtySample.updateQuality();

        testEqualsQuality(testQuality1, dirtySample, 0);
        testEqualsQuality(testQuality2, dirtySample, 1);
        testEqualsQuality(testQuality3, dirtySample, 2);
        testEqualsQuality(testQuality4, dirtySample, 3);
    }


    @Test
    public void Aged_Brie의_퀄리티는_50이_최대 () {
        testList = new Item[1];
        Item item1 = new Item("Aged Brie",5,15);
        testList[0] = item1;
        int testQuality = 50;

        DirtySample dirtySample = new DirtySample(testList);

        for(int i = 0 ; i < 50 ; i++){
            dirtySample.updateQuality();
        }

        testEqualsQuality(testQuality, dirtySample, 0);
    }


    @Test
    public void Backstage의_퀄리티는_나중에_무조건_0 () {
        testList = new Item[1];
        Item item1 = new Item("Backstage passes to a TAFKAL80ETC concert",30,15);

        testList[0] = item1;
        int testQuality = 0;

        DirtySample dirtySample = new DirtySample(testList);

        for(int i = 0 ; i < 40 ; i++){
            dirtySample.updateQuality();
        }

        testEqualsQuality(testQuality, dirtySample, 0);
    }


    private void testEqualsQuality(int testQuality, DirtySample dirtySample, int index) {
        assertThat(dirtySample.items[index].quality, is(testQuality));
    }
}