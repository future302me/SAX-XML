package com.gui.royal.xmlpulltest;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;


public class MainActivity extends ActionBarActivity {

    private  String xmlData = "<apps>" +
            "<app>" +
            "<id>1</id>" +
            "<name>Google Maps</name>" +
            "<version>1.0</version>" +
            "</app>" +
            "<app>" +
            "<id>2</id>" +
            "<name>Chrome</name>" +
            "<version>2.1</version>" +
            "</app>" +
            "<app>" +
            "<id>3</id>" +
            "<name>Google Play</name>" +
            "<version>2.3</version>" +
            "</app>" +
            "</apps>";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button pullParse = (Button) findViewById(R.id.btn_pullparse);
        pullParse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //SAX方式解析
                try {
                    SAXParserFactory factory =SAXParserFactory.newInstance();//生成工厂实例
                    XMLReader xmlReader = factory.newSAXParser().getXMLReader();//利用工厂类生成xmlReader对象
                    ContentHandler handler = new ContentHandler();//创建处理类实例
                    xmlReader.setContentHandler(handler);//绑定处理监听器
                    //StringReader是以流的方式读取一个String对象
                    xmlReader.parse(new InputSource(new StringReader(xmlData)));//解析对象
                } catch (Exception e) {
                    e.printStackTrace();
                }


                //Pull方式解析
               /** try {
                    XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                    XmlPullParser xmlPullParser = factory.newPullParser();
                    xmlPullParser.setInput(new StringReader(xmlData));
                    int eventType = xmlPullParser.getEventType();
                    String id = "";
                    String name = "";
                    String version = "";
                    while (eventType != XmlPullParser.END_DOCUMENT) {
                        String nodeName = xmlPullParser.getName();
                        switch (eventType) {
                            case XmlPullParser.START_TAG: {
                                if ("id".equals(nodeName)) {
                                    id = xmlPullParser.nextText();
                                }else if ("name".equals(nodeName)) {
                                    name = xmlPullParser.nextText();
                                }else if ("version".equals(nodeName)) {
                                    version = xmlPullParser.nextText();
                                }
                                break;
                            }
                            case XmlPullParser.END_TAG :{
                                if ("app".equals(nodeName)) {
                                    Log.d("id = " , id);
                                    Log.d("name = ", name);
                                    Log.d("version = ", version);
                                }
                                break;
                            }
                            default: break;
                        }
                        eventType = xmlPullParser.next();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }*/
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
