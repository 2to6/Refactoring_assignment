package ac.kr.ajou.dirt;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class DirtySampleTest {
    Item[] testList = new Item[1];

    @Test
    public void 아이템_이름이_AgedBrie가_아니고_Backstage도_아니고_퀄리티가0초과에_이름이_Sulfuras도_아니면_퀄리티를_마이너스1 (){
        Item item1 = new Item("hh",0,1);
        System.out.println(item1.toString());
        testList[0] = item1;
        DirtySample dirtySample = new DirtySample(testList);
        dirtySample.updateQuality();

        System.out.println(dirtySample.items[0].quality);
        assertThat(dirtySample.items[0].quality, is(0));
    }


    @Test
    public void 아이템_이름이AgedBrie가_아니고_Backstage도_아닌게_틀리고_퀄리티가_50미만이면_퀄리티를_플러스1하고_이름이_Backstage이고_sellIn이_11미만이고_퀄리티가50미만이면_퀄리티를_플러스1 (){
        Item item1 = new Item("Backstage passes to a TAFKAL80ETC concert",9,1);
        System.out.println(item1.toString());
        testList[0] = item1;
        DirtySample dirtySample = new DirtySample(testList);
        dirtySample.updateQuality();
        System.out.println(dirtySample.items[0].quality);
        assertThat(dirtySample.items[0].quality, is(3));
    }

    @Test
    public void 아이템_이름이AgedBrie가_아니고_Backstage도_아닌게_틀리고_퀄리티가_50미만이면_퀄리티를_플러스1하고_이름이_Backstage이고_sellIn이_6미만이고_퀄리티가50미만이면_퀄리티를_플러스1 (){
        Item item1 = new Item("Backstage passes to a TAFKAL80ETC concert",5,1);
        System.out.println(item1.toString());
        testList[0] = item1;
        DirtySample dirtySample = new DirtySample(testList);
        dirtySample.updateQuality();
        System.out.println(dirtySample.items[0].quality);
        assertThat(dirtySample.items[0].quality, is(4)); //sellIn이 11미만까지 만족하므로 퀄리티가 두번 더해짐
    }


    @Test
    public void 이름이_Sulfras가_아니면_sellIn을_마이너스1 (){
        Item item1 = new Item("Backstage passes to a TAFKAL80ETC concert",5,1);
        System.out.println(item1.toString());
        testList[0] = item1;
        DirtySample dirtySample = new DirtySample(testList);
        dirtySample.updateQuality();
        System.out.println(dirtySample.items[0].quality);
        assertThat(dirtySample.items[0].quality, is(4));
    }

    @Test
    public void sellIn이_0미만이고_이름이Aged가_아니고_Back도_아니고_퀄리티도_0초과면서_이름이_Sulfras도_아니면_퀄리티를_마이너스1 (){
        Item item1 = new Item("hh",-1,1);
        System.out.println(item1.toString());
        testList[0] = item1;
        DirtySample dirtySample = new DirtySample(testList);
        dirtySample.updateQuality();
        System.out.println(dirtySample.items[0].quality);
        assertThat(dirtySample.items[0].quality, is(0));
    }

    @Test
    public void sellIn이_0미만이고_이름이Aged가_아닌데_Back이_맞으면_퀄리티를_0으로한다 () {
        Item item1 = new Item("Backstage passes to a TAFKAL80ETC concert",-1,1);
        System.out.println(item1.toString());
        testList[0] = item1;
        DirtySample dirtySample = new DirtySample(testList);
        dirtySample.updateQuality();
        System.out.println(dirtySample.items[0].quality);
        assertThat(dirtySample.items[0].quality, is(0));

    }

    @Test
    public void sellIn이_0미만이고_이름이Aged인데_퀄리티가_50미만이면_퀄리티를_플러스1 () {
        Item item1 = new Item("Aged Brie",-5,1);
        System.out.println(item1.toString());
        testList[0] = item1;
        DirtySample dirtySample = new DirtySample(testList);
        dirtySample.updateQuality();
        System.out.println(dirtySample.items[0].quality);
        assertThat(dirtySample.items[0].quality, is(3)); //두번쨰 루트에서 Aged Brie와 퀄리티 50미만을 만족하므로 퀄리티가 한번더 플러스됨
    }
}