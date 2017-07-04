package jtp.c.dendai.ac.jp.xmlsample;



import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.XmlResourceParser;
import android.util.Log;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * Created by chiba on 2017/06/27.
 */

public class XmlReader {
    Context context;

    XmlReader(Context context) {
        this.context = context;
    }

    public ArrayList<Koma> getKomaList(){
        AssetManager am = context.getAssets();
        XmlPullParser xpp = Xml.newPullParser();
        ArrayList<Koma> komaList = new ArrayList<>();
        String komaName = "";
        boolean isSente = false, isNari = false, isMochigoma = false;
        int x = 0, y = 0;
        try {
            InputStream is = am.open("shogi1.xml");
            xpp.setInput(is, "UTF-8");
            int eventType = xpp.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                final String name = xpp.getName();
                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:

                        System.out.println(xpp.getName());
                        break;

                    case XmlPullParser.START_TAG:
                        for (int i = 0; i < xpp.getAttributeCount(); i++) {
                            switch (xpp.getAttributeName(i)) {
                                case "tejun":
                                    if (xpp.getAttributeValue(i).equals("sente")) {
                                        isSente = true;
                                    } else {
                                        isSente = false;
                                    }
                                    break;

                                case "mochigoma":
                                    if (xpp.getAttributeValue(i).equals("true")) {
                                        isMochigoma = true;
                                    } else {
                                        isMochigoma = false;
                                    }
                                    break;

                                case "nari":
                                    if (xpp.getAttributeValue(i).equals("true")) {
                                        isNari = true;
                                    } else {
                                        isNari = false;
                                    }
                                    break;

                                case "name":
                                    komaName = xpp.getAttributeValue(i);
                                    break;

                                case "x":
                                    x = Integer.parseInt(xpp.getAttributeValue(i));
                                    break;

                                case "y":
                                    y = Integer.parseInt(xpp.getAttributeValue(i));
                                    break;
                            }
                        }
                        break;

                    case XmlPullParser.END_TAG:
                        Koma koma = new Koma(context);
                        koma.setValue(komaName, isSente, isMochigoma, isNari, x, y);
                        komaList.add(koma);
                        //初期化
                        komaName = "";
                        isSente = false;
                        isMochigoma = false;
                        isNari = false;
                        x = 0;
                        y = 0;
                        break;

                    default:
                        break;
                }
                eventType = xpp.next();
            }
            Log.d("tag", "End Document");

        } catch (XmlPullParserException e) {
            Log.e("tag", e.toString());
        } catch (IOException e) {
            Log.e("tag", e.toString());
        } catch (Exception e) {
            Log.e("tag", e.toString());
        } finally {

        }
        return komaList;
    }
}
