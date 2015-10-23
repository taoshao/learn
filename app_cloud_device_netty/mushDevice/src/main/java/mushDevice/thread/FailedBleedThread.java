package mushDevice.thread;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class FailedBleedThread extends Thread{
	public static String host = "127.0.0.1";			//��ʱû��
	public static int port = 7002;						//�����豸�Ľӿ�
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
				socket = new Socket("127.0.0.1",7012);	//�򱾻���7001�˿ڷ����ͻ�����
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
//			Socket socket = new Socket("127.0.0.1",7002);	//�򱾻���7002�˿ڷ����ͻ�����
////			//��ϵͳ��׼�����豸����BufferedReader����
//			BufferedReader sin = new BufferedReader(new InputStreamReader(System.in));	
////			//��Socket����õ��������������PrintWriter����
//			PrintWriter os = new PrintWriter(socket.getOutputStream());
////			//��Socket����õ�����������������Ӧ��BufferedReader����
//			BufferedReader is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//			System.out.println("���ý���");
//			if(!is.readLine().isEmpty())
//				closes = is.readLine();
			while(closes == null || !closes.equals("shutdown")){
//			while(true){
				for(int i = 0; i < 1000; i++){	//1000��/s���ٶȷ���������
					os.println(msg);			//����ϵͳ��׼���������ַ��������Server
					os.flush();						//ˢ���������ʹServer�����յ����ַ���
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

			os.close(); //�ر�Socket�����
			try{
				is.close(); //�ر�Socket������
				socket.close(); //�ر�Socket
			}catch(Exception e){}
		}
	}
	
	
}
