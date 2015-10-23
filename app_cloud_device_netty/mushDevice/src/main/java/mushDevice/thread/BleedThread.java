package mushDevice.thread;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import Util.DeviceUtil;

public class BleedThread extends Thread{
	private static String serverHost = "127.0.0.1";
	private String msg;
	private static BleedThread instance;
	private static Socket socket;
	private static PrintWriter os;
	private static BufferedReader is;
	private BleedThread(){}
	public static synchronized BleedThread getInstance(){
		if(instance == null){
			instance = new BleedThread();
			try {
				socket = new Socket("127.0.0.1",7001);	//向本机的7001端口发出客户请求
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
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			try {
				bleed();
//				this.sleep(10000L);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
	private  void bleed() {
		try {
//			Socket socket = new Socket("127.0.0.1",7001);	//向本机的7001端口发出客户请求
//			//由系统标准输入设备构造BufferedReader对象
////			BufferedReader sin = new BufferedReader(new InputStreamReader(System.in));	
//			//由Socket对象得到输出流，并构造PrintWriter对象
//			PrintWriter os = new PrintWriter(socket.getOutputStream());
//			//由Socket对象得到输入流，并构造相应的BufferedReader对象
//			BufferedReader is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			for(;;){
				os.println(DeviceUtil.getStatus());			//将从系统标准输入读入的字符串输出到Server
				os.flush();						//刷新输出流，使Server马上收到该字符串
				System.out.println("Client1:" + DeviceUtil.getStatus());
				this.sleep(10000);
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
