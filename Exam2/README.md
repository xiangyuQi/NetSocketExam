1启动服务端
	mvn clean compile exec:java -Dexec.mainClass="com.hand.Exam2.StartServer"
2.另外启动一个CMD启动客户端
	 mvn exec:java -Dexec.mainClass="com.hand.Exam2.StartClient"
请确保先执行了exam1
文件保存在项目根目录下 client.pdf