package ac.kr.ajou.dirt;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class DirtySampleTest {
    Item[] testList;

    //TODO : Test코드 역으로 재구성 필요
    /**
     Dirty Code Refactoring
     Sulfuras를 제외한 모든 상품의 quality는 최대 50(최고가치)이다.
     퀄리티의 최소값은 0이다.

     Aged Brie(치즈)
     → sellIn이 0이상이면 퀄리티 + 1
     → sellIn이 0미만이면 퀄리터 + 2
     치즈는 시간이 지날수록 숙성이 된다는 의미

     Backstage passes to a TAFKAL80ETC(콘서트 티켓)
     →공연이 가까워 질 수록 quality는 올라간다. (세부 조건 있음)
     세부조건) 5일이하 2증가, 6일 이상 10일 이하 2증가
     → sellIn이 0이하라면 공연이 이미 끝났으므로, quality가 0으로 떨어진다.

     Sulfuras, Hand of Ragnaros(게임 속 전설의 아이템)
     →아무변화 없음(게임 아이템이라서 sellIn과, quality가 변화하지 않는다.)

     그 외(일반상품)
     →sellin이
     →sellin이 0 이하일 경우 quality는 2씩 감소 (quality 최소는 0)

     * */

    @Test
    public void Aged_Brie의_SellIn이_0이상이면_퀄리티가_플러스1 () {
        testList = new Item[1];
        Item item1 = new Item("Aged Brie",2,49);
        System.out.println("업데이트 전 : " + item1.toString());
        testList[0] = item1;
        int testQuality = item1.quality + 1;

        DirtySample dirtySample = new DirtySample(testList);
        dirtySample.updateQuality();

        System.out.println("업데이트 후 : " + item1.toString());
        assertThat(dirtySample.items[0].quality, is(testQuality));
    }

    @Test
    public void Aged_Brie의_SellIn이_0미만이면_퀄리티가_플러스2 () {
        testList = new Item[1];
        Item item1 = new Item("Aged Brie",-1,5);
        System.out.println("업데이트 전 : " + item1.toString());
        testList[0] = item1;
        int testQuality = item1.quality + 2;

        DirtySample dirtySample = new DirtySample(testList);
        dirtySample.updateQuality();

        System.out.println("업데이트 후 : " + item1.toString());
        assertThat(dirtySample.items[0].quality, is(testQuality));
    }

    @Test
    public void Backstage가_11일이상이면_퀄리티가_플러스1 () {
        testList = new Item[1];
        Item item1 = new Item("Backstage passes to a TAFKAL80ETC concert",12,5);
        System.out.println("업데이트 전 : " + item1.toString());
        testList[0] = item1;
        int testQuality = item1.quality + 1;

        DirtySample dirtySample = new DirtySample(testList);
        dirtySample.updateQuality();

        System.out.println("업데이트 후 : " + item1.toString());
        assertThat(dirtySample.items[0].quality, is(testQuality));
    }


    @Test
    public void Backstage가_6일이상_11일미만이면_퀄리티가_플러스1 () {
        testList = new Item[1];
        Item item1 = new Item("Backstage passes to a TAFKAL80ETC concert",7,5);
        System.out.println("업데이트 전 : " + item1.toString());
        testList[0] = item1;
        int testQuality = item1.quality + 2;

        DirtySample dirtySample = new DirtySample(testList);
        dirtySample.updateQuality();

        System.out.println("업데이트 후 : " + item1.toString());
        assertThat(dirtySample.items[0].quality, is(testQuality));
    }

    @Test
    public void Backstage가_0일초과_6일미만이면_퀄리티가_플러스3 () {
        testList = new Item[1];
        Item item1 = new Item("Backstage passes to a TAFKAL80ETC concert",3,5);
        System.out.println("업데이트 전 : " + item1.toString());
        testList[0] = item1;
        int testQuality = item1.quality + 3;

        DirtySample dirtySample = new DirtySample(testList);
        dirtySample.updateQuality();

        System.out.println("업데이트 후 : " + item1.toString());
        assertThat(dirtySample.items[0].quality, is(testQuality));
    }

    @Test
    public void Backstage가_0일이면_퀄리티는_무조건_0 () {
        testList = new Item[1];
        Item item1 = new Item("Backstage passes to a TAFKAL80ETC concert",0,5);
        System.out.println("업데이트 전 : " + item1.toString());
        testList[0] = item1;
        int testQuality = 0;

        DirtySample dirtySample = new DirtySample(testList);
        dirtySample.updateQuality();

        System.out.println("업데이트 후 : " + item1.toString());
        assertThat(dirtySample.items[0].quality, is(testQuality));
    }


    @Test
    public void Sulfuras는_절대_SellIn과_퀄리티가_변하지_않는다 () {
        testList = new Item[1];
        Item item1 = new Item("Sulfuras, Hand of Ragnaros",23,100000000);
        System.out.println("업데이트 전 : " + item1.toString());
        testList[0] = item1;
        int testQuality = item1.quality;

        DirtySample dirtySample = new DirtySample(testList);
        dirtySample.updateQuality();

        System.out.println("업데이트 후 : " + item1.toString());
        assertThat(dirtySample.items[0].quality, is(testQuality));
    }



    @Test
    public void 그외_상품일때_sellIn이도_아닌경우에 () {
        testList = new Item[4];
        Item item1 = new Item("hhh",12,60);
        Item item2 = new Item("hhh",9,11);
        Item item3 = new Item("hhh",4,11);
        Item item4 = new Item("hhh",0,60);

        System.out.println("업데이트 전 1 : " + item1.toString());
        System.out.println("업데이트 전 2 : " + item2.toString());
        System.out.println("업데이트 전 3 : " + item3.toString());
        System.out.println("업데이트 전 4 : " + item4.toString());
        System.out.println("업데이트 전 5 : " + item4.toString());

        testList[0] = item1;
        testList[1] = item2;
        testList[2] = item3;
        testList[3] = item4;

//        int testQuality = item1.quality + 1;

        DirtySample dirtySample = new DirtySample(testList);
        dirtySample.updateQuality();

        System.out.println();
        System.out.println("업데이트 후 1 : " + item1.toString());
        System.out.println("업데이트 후 2 : " + item2.toString());
        System.out.println("업데이트 후 3 : " + item3.toString());
        System.out.println("업데이트 후 4 : " + item4.toString());
//        assertThat(dirtySample.items[0].quality, is(testQuality));
    }


    @Test
    public void Aged_Brie의_퀄리티는_50이_최대 () {
        testList = new Item[1];
        Item item1 = new Item("Aged Brie",5,15);
        System.out.println("업데이트 전 1 : " + item1.toString());

        testList[0] = item1;
        int testQuality = 50;

        DirtySample dirtySample = new DirtySample(testList);

        for(int i = 0 ; i < 2000 ; i++){
            dirtySample.updateQuality();
        }
        System.out.println();
        System.out.println("업데이트 후 1 : " + item1.toString());
        assertThat(dirtySample.items[0].quality, is(testQuality));
    }


    @Test
    public void Backstage의_퀄리티는_50이_최대 () {
        testList = new Item[1];
        Item item1 = new Item("Backstage passes to a TAFKAL80ETC concert",30,15);
        System.out.println("업데이트 전 1 : " + item1.toString());

        testList[0] = item1;
        int testQuality = 50;

        DirtySample dirtySample = new DirtySample(testList);

        for(int i = 0 ; i < 30 ; i++){
            dirtySample.updateQuality();
        }
        System.out.println();
        System.out.println("업데이트 후 1 : " + item1.toString());
        assertThat(dirtySample.items[0].quality, is(testQuality));
    }

}