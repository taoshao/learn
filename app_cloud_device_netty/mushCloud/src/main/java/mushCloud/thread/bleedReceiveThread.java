package mushCloud.thread;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
/**
 * 20151022
 * �������Ŀǰ�ڿ���̨չʾ��������Ҫ���ڴ�����ʾ��
 * ����߳���Ҫ��servlet�е��á�
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
		while(true){
//			System.out.println("���ս���");
			String s = is.readLine();
			if(!s.isEmpty()){
				System.out.println("Service:"+s);
			}
//			sleep(1);
//			String s_next = is.readLine();
////			System.out.println(s_next);
//			if(s.equals(s_next)){
//                os.println("close");
//                //��ͻ���������ַ���
//                os.flush();
//                System.out.println("Service:"+"close the failed device!");
//			}
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
