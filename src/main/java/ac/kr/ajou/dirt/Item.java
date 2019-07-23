package ac.kr.ajou.dirt;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Item {

    public String name;
    public int sellIn;
    public int quality;


    @Override
    public String toString() {
        return this.name + ", " + this.sellIn + ", " + this.quality;
    }
}