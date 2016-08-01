package com.hand.Exam2;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread{

	private ServerSocket ss;  
	private Socket socket;  
	BufferedOutputStream writer;

	public void run() {
		try {
			ss = new ServerSocket(12345);

			while(true){
				//获取试题一项目更路径下的 pdf
				File file = new File("../Exam1/target.pdf");
				FileInputStream fis = new FileInputStream(file);
				BufferedInputStream bfis = new BufferedInputStream(fis);
				socket = ss.accept();
				System.out.println("一个客户端接入，开始传输文件");
				writer = new BufferedOutputStream(socket.getOutputStream());
				int len ;
				byte [] bytes = new byte[1000];
				while((len = bfis.read(bytes))!=-1){
					writer.write(bytes,0,len);
				}
				writer.flush();
				bfis.close();
				fis.close();
				writer.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				if(writer!=null)
					writer.close();
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}



