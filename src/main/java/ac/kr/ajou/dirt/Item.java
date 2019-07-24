package ac.kr.ajou.dirt;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Item {

    public String name;
    public int expirePeriod;
    public int quality;


    @Override
    public String toString() {
        return this.name + ", " + this.expirePeriod + ", " + this.quality;
    }
}