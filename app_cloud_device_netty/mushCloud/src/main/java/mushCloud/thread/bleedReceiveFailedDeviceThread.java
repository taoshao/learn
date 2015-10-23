package mushCloud.thread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;
/**
 * 20151022
 * �������Ŀǰ�ڿ���̨չʾ��������Ҫ���ڴ�����ʾ��
 * ����߳���Ҫ��servlet�е��á�
 * */
public class bleedReceiveFailedDeviceThread extends Thread {
//	private static BufferedReader is;
	private static ServerSocket server;
	private static int serverPost = 7012;					//���������豸�Ľӿ�
	private static bleedReceiveFailedDeviceThread instance;
	private static Socket socket;
	private static PrintWriter os;
	private static BufferedReader is;
	private String status;
	private int count_s = 0;
	private final String chaos_s = "I'm chaos!";
	private bleedReceiveFailedDeviceThread(){}
	public static synchronized bleedReceiveFailedDeviceThread getInstance(){
		if(instance == null){
			instance = new bleedReceiveFailedDeviceThread();
			try {			//��������
				
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
			server = new ServerSocket(serverPost);	//����һ��ServerSocket�ڶ˿�7001�����ͻ�����
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("can not listen to:"+e);
		}
		socket = null;
		try {
			socket = server.accept();//ʹ��accept()�����ȴ��ͻ������пͻ������������һ��Socket���󣬲�����ִ��
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error."+e);
		}
		String line;
		 //��Socket����õ�����������������Ӧ��BufferedReader����
		BufferedReader is=new BufferedReader(new InputStreamReader(socket.getInputStream()));
		//��Socket����õ��������������PrintWriter����
		PrintWriter os=new PrintWriter(socket.getOutputStream());
		//��ϵͳ��׼�����豸����BufferedReader����
		BufferedReader sin=new BufferedReader(new InputStreamReader(System.in));
		String sent_to_device;
//		int count_s = 0;
		while(true){
			String s = is.readLine(); 				//��ȡsocket������������
//			if(timeForCount(s) > 500){
//				break;
//			}
			if(chaos_s.equals(s)){
				count_s++;
			}
			System.out.println(s);
//			String[] arr = s.split("'");
//			count_s = arr.length;
			System.out.println(count_s);		
		switch(count_s/100 ){
		case 0:
		case 1:
		case 2:
		case 3:
		case 4:
		case 5: sent_to_device = new String("self-check");
			break;
		case 6:
		case 7:	sent_to_device = "close yourself";
			break;
		default: 
			sent_to_device = "shutdown";
			break;
		}
		
//			String s_next = is.readLine();
////			System.out.println(s_next);
//			if(s.equals(s_next)){
//		System.out.println("Service:"+ sent_to_device + " the failed devi123ce!");
                os.println(sent_to_device);
                //��ͻ���������ַ���
                os.flush();
                System.out.println("Service:"+ sent_to_device + " the failed device!");
		}
	} catch (Exception e) {
		// TODO: handle exception
	    System.out.println("Error:"+e.toString());
	}finally{
	     os.close(); //�ر�Socket�����
	     try{
	     is.close(); //�ر�Socket������
	     socket.close(); //�ر�Socket
	     server.close(); //�ر�ServerSocket
	     }catch(Exception e){}
	     
	 }
	}

	private int timeForCount(final String s){
		final Timer timer = new Timer();
//		int count_s = 0;
		TimerTask tt = new TimerTask(){
			public void run() {
				// TODO Auto-generated method stub
				try {
//					if(is.readLine() != null){
						if(s.equals(is.readLine())){
							bleedReceiveFailedDeviceThread.this.count_s ++;
//						}
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				timer.cancel();
			}
		};
		timer.schedule(tt, 1000);
		System.out.println("get the msg from the device : " + bleedReceiveFailedDeviceThread.this.count_s);
		return count_s;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
