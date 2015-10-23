package mushDeviceServlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mushDevice.thread.BleedThread;
import mushDevice.thread.FailedBleedThread;

public class failedDevice extends HttpServlet {

	private boolean flag_first = true;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
//		super.doGet(req, resp);
//		String status = req.getParameter("status");
//		BleedThread bleedThread = BleedThread.getInstance();
//		bleedThread.setMsg(status);
		FailedBleedThread failedBleed = FailedBleedThread.getInstance();
		if(flag_first){
			failedBleed.start();
			this.flag_first = false;
		}

		System.out.println("failed device"  );
		resp.setContentType("text/html;charset=GBK");
		resp.getOutputStream().write(("故障设备...乱发信息ing--1000t/s").toString().getBytes("GBK"));
	}
}
