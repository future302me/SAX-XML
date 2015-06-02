package com.gui.royal.xmlpulltest;

import android.util.Log;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Created by Jeremy on 2015/5/30.
 * 实现ContentHandler事件处理类
 */
public class ContentHandler extends DefaultHandler {

    private String nodeName;
    private StringBuilder id;
    private StringBuilder name;
    private StringBuilder version;


    /**
     * 开始解析文档是调用，用于初始化一些数据
     * 创建三个StringBuilder对象用于存储解析出来的数据
     * @throws SAXException
     */
    @Override
    public void startDocument() throws SAXException {
        id = new StringBuilder();
        name = new StringBuilder();
        version = new StringBuilder();
    }

    /**
     * 开始解析节点是调用，可以得到一些节点信息，如节点名，属性等
     * @param uri 节点的命名空间
     * @param localName 当前节点名，不带前缀
     * @param qName  当前的节点名，带前缀
     * @param attributes 当前节点的属性
     *         attributes.getLocalName()  attributes.getValue()
     *         获取节点属性名              获取节点属性值
     * @throws SAXException
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        nodeName = localName; //保存当前节点名
    }


    /**
     * 解析节点内容是调用，最重要的方法
     * @param ch 解析出的内容，放在次char数组中
     * @param start 起始位置
     * @param length 数组长度
     * @throws SAXException
     */
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if ("id".equals(nodeName)) {   //根据节点名读取节点内容
            id.append(ch , start, length);
        } else if ("name".equals(nodeName)) {
            name.append(ch, start, length);
        } else if ("version".equals(nodeName)) {
            version.append(ch, start ,length);
        }
    }

    /**
     * 完成解析某个节点是调用，可存储内容，以及释放缓存对象等操作
     * @param uri 命名空间
     * @param localName 不带前缀的节点名
     * @param qName 带前缀的节点名
     * @throws SAXException
     */
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if ("app".equals(localName)) {
            Log.d("ContentHandler", "id = " + id.toString().trim() );
            Log.d("ContentHandler", "name = " + name.toString().trim());
            Log.d("ContentHandler", "version = " + version.toString().trim());
            id.setLength(0); //清空StringBuilder对象，以便解析下一个节点
            name.setLength(0);
            version.setLength(0);
        }
    }


    /**
     * 解析完成时调用
     * @throws SAXException
     */
    @Override
    public void endDocument() throws SAXException {

    }
}
