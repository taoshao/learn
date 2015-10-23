package mushCloud.thread;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
/**
 * 20151022
 * 这个数据目前在控制台展示，后期需要在内存中显示。
 * 这个线程需要在servlet中调用。
 * */
public class bleedReceiveThread extends Thread {
//	private static BufferedReader is;
	private static ServerSocket server;
	private static int serverPost = 7001;
	private static bleedReceiveThread instance;
	private static Socket socket;
	private static PrintWriter os;
	private static BufferedReader is;
	private String status;
	private bleedReceiveThread(){}
	public static synchronized bleedReceiveThread getInstance(){
		if(instance == null){
			instance = new bleedReceiveThread();
			try {			//建立监听
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return instance;
	}
@Override
	public void run(){
//	public static void main(String[] args) {
		
	try {
		
		ServerSocket server = null;
		try {
			server = new ServerSocket(serverPost);	//创建一个ServerSocket在端口7001监听客户请求
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("can not listen to:"+e);
		}
		socket = null;
		try {
			socket = server.accept();//使用accept()阻塞等待客户请求，有客户请求到来则产生一个Socket对象，并继续执行
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error."+e);
		}
		String line;
		 //由Socket对象得到输入流，并构造相应的BufferedReader对象
		BufferedReader is=new BufferedReader(new InputStreamReader(socket.getInputStream()));
		//由Socket对象得到输出流，并构造PrintWriter对象
		PrintWriter os=new PrintWriter(socket.getOutputStream());
		//由系统标准输入设备构造BufferedReader对象
		BufferedReader sin=new BufferedReader(new InputStreamReader(System.in));
		while(true){
//			System.out.println("接收进程");
			String s = is.readLine();
			if(!s.isEmpty()){
				System.out.println("Service:"+s);
			}
//			sleep(1);
//			String s_next = is.readLine();
////			System.out.println(s_next);
//			if(s.equals(s_next)){
//                os.println("close");
//                //向客户端输出该字符串
//                os.flush();
//                System.out.println("Service:"+"close the failed device!");
//			}
		}

	} catch (Exception e) {
		// TODO: handle exception
	    System.out.println("Error:"+e.toString());
	}finally{
	     os.close(); //关闭Socket输出流
	     try{
	     is.close(); //关闭Socket输入流
	     socket.close(); //关闭Socket
	     server.close(); //关闭ServerSocket
	     }catch(Exception e){}
	     
	 }
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
