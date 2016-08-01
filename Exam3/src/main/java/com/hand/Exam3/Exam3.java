package com.hand.Exam3;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;



public class Exam3 {
	
	private static String name,open,close,current,high,low;
	
	static String  getStr(String Url){
		StringBuffer sb = new StringBuffer();
		try {
			URL url = new URL(Url);

			URLConnection conn = url.openConnection();
			InputStream is = conn.getInputStream();
			//编码问题
			InputStreamReader isr = new InputStreamReader(is,"GBK");
			BufferedReader br = new BufferedReader(isr);
			String len ;
			if((len =br.readLine())!=null){
				sb.append(len);
			}
			//System.out.println(sb.toString());
			br.close();
			isr.close();
			is.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
	
	public static void creatXML(){
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.newDocument();
			Element root = document.createElement("xml");	
			Element stock = document.createElement("stock");
			Element nameT = document.createElement("name");
			nameT.setTextContent(name);
			Element openT = document.createElement("open");
			openT.setTextContent(open);
			Element closeT = document.createElement("close");
			closeT.setTextContent(close);
			Element currentT = document.createElement("current");
			currentT.setTextContent(current);
			Element highT = document.createElement("high");
			highT.setTextContent(high);
			Element lowT = document.createElement("low");
			lowT.setTextContent(low);
			
			stock.appendChild(nameT);
			stock.appendChild(openT);
			stock.appendChild(closeT);
			stock.appendChild(currentT);
			stock.appendChild(highT);
			stock.appendChild(lowT);
			
			root.appendChild(stock);
			//root.appendChild(name3);
			document.appendChild(root);
			//创建完毕，写入
			 try {
				TransformerFactory factory2 = TransformerFactory.newInstance();
				Transformer transformer = factory2.newTransformer();
				StringWriter writer = new StringWriter();
				transformer.transform(new DOMSource(document),new StreamResult(writer));
				//System.out.println(writer);
				transformer.transform(new DOMSource(document), new StreamResult(new File("XMLDATA.xml")));
			} catch (TransformerConfigurationException e) {
				e.printStackTrace();
			} catch (TransformerException e) {
				e.printStackTrace();
			}
			 System.out.println("xml格式保存成功,在当前项目根目录下,文件名为XMLDATA.xml");
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		
	}
	
	static void createJson(){
		JsonObject obj = new JsonObject();
		obj.addProperty("name",name);
		obj.addProperty("open",Double.parseDouble(open));
		obj.addProperty("close", Double.parseDouble(close));
		obj.addProperty("current",Double.parseDouble(current));
		obj.addProperty("high", Double.parseDouble(high));
		obj.addProperty("low", Double.parseDouble(low));
		
		try {
			File file =  new File("JSONDATA.json");
			FileOutputStream fos = new FileOutputStream(file);
			BufferedOutputStream writer = new BufferedOutputStream(fos);
			writer.write(obj.toString().getBytes("UTF-8"));
			writer.flush();
			writer.close();
			fos.close();
			System.out.println("json格式保存成功,在当前项目根目录下,文件名为JSONDATA.xml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		String str = getStr("http://hq.sinajs.cn/list=sz300170");
		//System.out.println(str);
		//找到等号在的位置
		int denghao = str.indexOf("=");
		//找到第一个逗号所在位置
		int douhao = str.indexOf(",");
		String ss[] = str.split(",", denghao); 
		name = ss[0].substring(denghao+2, douhao);
		open = ss[1];
		close = ss[2];
		current = ss[3];
		high = ss[4];
		low = ss[5];
		creatXML();
		createJson();
	}
}
