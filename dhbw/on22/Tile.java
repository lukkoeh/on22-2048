package dhbw.on22;
import javafx.scene.paint.Color;
public class Tile {
    int val;
    Color tileClr;

    public Tile(){
        val=0;
    }
    public Tile(int num){
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
        tileClr = Color.hsb(calcHue(tileVal), calcSat(tileVal), calcLight(tileVal));
    }

    public Color getColor(){
        this.setTileClr(this.val);
        return tileClr;
    }


    public String toString(){
        return "" + val;
    }

    int calcHue(int value){
        double hueModifier = Math.sqrt(value)*1.5;
        if (value < 64)
            return round(hueModifier);
        else
            return round(48-hueModifier);
    }

    float calcSat(int value){
        float satModifier = value/10.24f;
        return satModifier/100;
    }

    float calcLight(int value){
        float lightModifier = value/25.6f;
        return 1-(lightModifier/100);
    }
    int round(double value){
        return (int) ((value * 100 + 0.5)/100);
    }
}
