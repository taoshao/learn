package mushDevice.thread;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class FailedBleedThread extends Thread{
	public static String host = "127.0.0.1";			//暂时没用
	public static int port = 7002;						//故障设备的接口
	private static Socket socket;
	private static PrintWriter os;
	private static BufferedReader is;
	private static String msg= "I'm chaos!";
	private static FailedBleedThread instance;
	private FailedBleedThread(){}
	public static synchronized FailedBleedThread getInstance(){
		if(instance == null){
			instance = new FailedBleedThread();
			try {
				socket = new Socket("127.0.0.1",7012);	//向本机的7001端口发出客户请求
				//由系统标准输入设备构造BufferedReader对象
//				BufferedReader sin = new BufferedReader(new InputStreamReader(System.in));	
				//由Socket对象得到输出流，并构造PrintWriter对象
				os = new PrintWriter(socket.getOutputStream());
				//由Socket对象得到输入流，并构造相应的BufferedReader对象
				is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return instance;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			try {
				
				this.bleed();
				this.sleep(1000L);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
	private  void bleed() {
		String closes = null;
		try {
//			Socket socket = new Socket("127.0.0.1",7002);	//向本机的7002端口发出客户请求
////			//由系统标准输入设备构造BufferedReader对象
//			BufferedReader sin = new BufferedReader(new InputStreamReader(System.in));	
////			//由Socket对象得到输出流，并构造PrintWriter对象
//			PrintWriter os = new PrintWriter(socket.getOutputStream());
////			//由Socket对象得到输入流，并构造相应的BufferedReader对象
//			BufferedReader is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//			System.out.println("调用进程");
//			if(!is.readLine().isEmpty())
//				closes = is.readLine();
			while(closes == null || !closes.equals("shutdown")){
//			while(true){
				for(int i = 0; i < 1000; i++){	//1000条/s的速度发送心跳包
					os.println(msg);			//将从系统标准输入读入的字符串输出到Server
					os.flush();						//刷新输出流，使Server马上收到该字符串
					System.out.println("Client2:"+ msg);
					this.sleep(10);
				}
//				this.sleep(1000);
				closes = is.readLine();
				System.out.println("Client:"+ "get the close order!");
//				if(!is.readLine().isEmpty())
//					closes = is.readLine();
						System.out.println(closes);
			}
			
	}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{

			os.close(); //关闭Socket输出流
			try{
				is.close(); //关闭Socket输入流
				socket.close(); //关闭Socket
			}catch(Exception e){}
		}
	}
	
	
}
