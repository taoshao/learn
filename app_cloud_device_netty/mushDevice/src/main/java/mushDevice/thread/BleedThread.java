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
				socket = new Socket("127.0.0.1",7001);	//�򱾻���7001�˿ڷ����ͻ�����
				//��ϵͳ��׼�����豸����BufferedReader����
//				BufferedReader sin = new BufferedReader(new InputStreamReader(System.in));	
				//��Socket����õ��������������PrintWriter����
				os = new PrintWriter(socket.getOutputStream());
				//��Socket����õ�����������������Ӧ��BufferedReader����
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
//			Socket socket = new Socket("127.0.0.1",7001);	//�򱾻���7001�˿ڷ����ͻ�����
//			//��ϵͳ��׼�����豸����BufferedReader����
////			BufferedReader sin = new BufferedReader(new InputStreamReader(System.in));	
//			//��Socket����õ��������������PrintWriter����
//			PrintWriter os = new PrintWriter(socket.getOutputStream());
//			//��Socket����õ�����������������Ӧ��BufferedReader����
//			BufferedReader is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			for(;;){
				os.println(DeviceUtil.getStatus());			//����ϵͳ��׼���������ַ��������Server
				os.flush();						//ˢ���������ʹServer�����յ����ַ���
				System.out.println("Client1:" + DeviceUtil.getStatus());
				this.sleep(10000);
			}

	}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{

			os.close(); //�ر�Socket�����
			try{
				is.close(); //�ر�Socket������
				socket.close(); //�ر�Socket
			}catch(Exception e){}
		}
	}
}
