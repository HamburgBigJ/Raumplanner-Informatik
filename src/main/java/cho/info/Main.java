package cho.info;

import cho.info.furniture.Chair;
import cho.info.furniture.WallCabinet;

public class Main {
    public static void main(String[] args) {
        new Chair();
        var cab = new WallCabinet(3);
        cab.show();
        cab.setCabinets(2);
        cab.resizeWidth(100);
    }
}
