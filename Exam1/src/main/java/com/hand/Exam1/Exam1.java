package com.hand.Exam1;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Exam1 {
	public static void main(String[] args) {
		try {
			//在项目根目录下径下创建文件
			File file = new File("target.pdf");

			file.createNewFile();

			//文件写入流
			FileOutputStream fos = new FileOutputStream(file);
			BufferedOutputStream bfos = new BufferedOutputStream(fos,10000);

			URL url = new URL("http://files.saas.hand-china.com/java/target.pdf");
			URLConnection conn = url.openConnection();
			InputStream is = conn.getInputStream();
			BufferedInputStream bis = new BufferedInputStream(is,10000);
			byte[] bytes = new byte[1000];
			int len;
			while((len =bis.read(bytes))!=-1){
				fos.write(bytes,0,len);
			}
			//强制输出缓存区内容
			fos.flush();
			//System.out.println(sb.toString());
			bfos.close();
			fos.close();
			bis.close();
			is.close();
			System.out.println("保存成功，文件在当前项目根目录下，文件名为target.pdf");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
