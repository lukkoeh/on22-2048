package dhbw.on22;
import javafx.scene.paint.Color;
import java.util.HashMap;
public class Tile {
    int val;
    Color tileClr;

    public Tile() {
        val = 0;
    }

    public Tile(int num) {
        val = num;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public Color getTileClr() {
        return tileClr;
    }

    public void setTileClr(int tileVal) {
        HashMap<Integer, Color> colors = new HashMap<Integer, Color>();
        colors.put(0, Color.rgb(37, 37, 37));
        colors.put(2, Color.rgb(255, 255, 255));
        colors.put(4, Color.rgb(234, 194, 162));
        colors.put(8, Color.rgb(235, 150, 75));
        colors.put(16, Color.rgb(230, 136, 69));
        colors.put(32, Color.rgb(255, 118, 77));
        colors.put(64, Color.rgb(255, 65, 51));
        colors.put(128, Color.rgb(255, 227, 89));
        colors.put(256, Color.rgb(237, 205, 76));
        colors.put(512, Color.rgb(230, 195, 67));
        colors.put(1024, Color.rgb(224, 185, 56));
        colors.put(2048, Color.rgb(217, 165, 22));

        tileClr = colors.get(tileVal);

    }

    public Color getColor() {
        this.setTileClr(this.val);
        return tileClr;
    }


    public String toString() {
        return Integer.toString(val);
    }


}
