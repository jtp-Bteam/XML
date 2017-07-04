package jtp.c.dendai.ac.jp.xmlsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        XmlReader xr = new XmlReader(this);
        ArrayList<Koma> komaList = xr.getKomaList();
        Iterator<Koma> it = komaList.iterator();
        String format = "名前 = %s \t 持ち駒 = %b \t 先手 = %b \t 成 = %b  x座標 = %d \t y座標 = %d\n";

        while (it.hasNext()) {
            Koma k = it.next();
            System.out.printf(format, k.name, k.isMochigoma, k.isSente, k.isNari, k.x, k.y);
        }
    }
}
