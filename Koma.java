package jtp.c.dendai.ac.jp.xmlsample;

import android.content.Context;
import android.widget.ImageView;

/**
 * Created by chiba on 2017/07/04.
 */

public class Koma extends android.support.v7.widget.AppCompatImageView{
    String name;
    boolean isSente;
    boolean isMochigoma;
    boolean isNari;
    int x;
    int y;

    public Koma(Context context) {
        super(context);
    }

    void setValue(String str, boolean s, boolean m, boolean n, int x, int y){
        this.name = str;
        this.isSente = s;
        this.isMochigoma = m;
        this.isNari = n;
        this.x = x;
        this.y = y;
    }
}
