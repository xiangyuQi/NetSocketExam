package com.hand.Exam2;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client extends Thread{
	public void run() {


		Socket socket;
		try {
			File file = new File("client.pdf");
			FileOutputStream fos = new FileOutputStream(file);
			BufferedOutputStream bfos = new BufferedOutputStream(fos);
			socket = new Socket("127.0.0.1", 12345);
			BufferedInputStream reader =  new BufferedInputStream(socket.getInputStream());
			int len ;
			byte [] bytes = new byte[1000];
			while((len = reader.read(bytes))!=-1){
				bfos.write(bytes,0,len);
			}
			bfos.flush();
			bfos.close();
			fos.close();
			reader.close();
			socket.close();  
			System.out.println("文件接收成功，保存在项目根路径下,文件名为client.pdf");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}  
	}
}
