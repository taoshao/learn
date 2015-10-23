package mushDeviceServlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.SingleThreadModel;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mushDevice.thread.BleedThread;
import DeviceGetData.DeviceGetDateThread;
import Util.DeviceUtil;

public class DeviceClinet extends HttpServlet implements SingleThreadModel{
//	public static String host = "127.0.0.1";			//暂时没用
//	public static int port = 7001;
	private boolean flag_first = true;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
//		super.doGet(req, resp);
		String status = req.getParameter("status");
		BleedThread bleedThread = BleedThread.getInstance();
//		bleedThread.setMsg(status);
		DeviceUtil.setStatus(status);
		if(flag_first){
			bleedThread.start();
			this.flag_first = false;
		}
		DeviceGetDateThread  deviceGetDateThread =new DeviceGetDateThread();
		deviceGetDateThread.start();
		StringBuffer sb = new StringBuffer(("status : "  + status)) ;
		if(DeviceUtil.getStatus() != null && DeviceUtil.isGetDataCloudFlag()){
//			System.out.println("收到了状态信息");
//			sb.append("\n" + "get status from CLoud: " + DeviceUtil.getStatus());
			DeviceUtil.setGetDataCloudFlag(false);
		}
		System.out.println("status : " + status );
		resp.setContentType("text/html;charset=GBK");
		resp.getOutputStream().write(sb.toString().getBytes("GBK"));
	}
	
	
}
