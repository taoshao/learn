package mushCloud;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.SingleThreadModel;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mushCloud.thread.bleedReceiveFailedDeviceThread;
import mushCloud.thread.bleedReceiveThread;
import netty.cloudToDevice.CloudClientThread;
import Util.mushCloudUtil;

public class CloudServlet  extends HttpServlet implements SingleThreadModel {
		private boolean flag_first = true;
		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
				throws UnsupportedEncodingException, IOException  {
			
//			String data = this.getData();
//			System.out.println("order : " + order + " sented!");
			bleedReceiveThread breceive = bleedReceiveThread.getInstance();
			bleedReceiveFailedDeviceThread brfDevice = bleedReceiveFailedDeviceThread.getInstance();
			if(flag_first){
				breceive.start();
				brfDevice.start();
				this.flag_first = false;
			}
			try {
				GetDataThread getDataThread = new GetDataThread();
				getDataThread.start();
			} catch (Exception e) {
				// TODO: handle exception
			}
//			mushCloudUtil.setStatus("opened!");
			CloudClientThread cloudClientThread = new CloudClientThread();
			cloudClientThread.start();
//			while(mushCloudUtil.getStatus() == null);
//			sleep(10000);
			for(int i = 0; i < 1000000; i++);
			System.out.println("get !!!!!" + mushCloudUtil.getStatus());
			mushCloudUtil.setResp(resp);
			
			resp.setContentType("text/html;charset=GBK");
			StringBuffer s =new StringBuffer( "recive from app ,the order is :" + mushCloudUtil.getStatus());
			if(mushCloudUtil.isOrderSentFlag() == true){
				s.append("\n" + "the order sented to Device!");
			}
			
			resp.getOutputStream().write(s.toString().getBytes("GBK"));
		}
		
}


class GetDataThread extends Thread{
    /**
     * 服务端监听的端口地址
     */
	public static final int portNumber = 7878;
	
	@Override
    public void run(){
		try {
			this.listen();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	private void listen() throws InterruptedException{
		// TODO Auto-generated method stub
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap b = new ServerBootstrap();
			b.group(bossGroup,workerGroup)
			.channel(NioServerSocketChannel.class)
			.childHandler(new CloudServerInitializer());
			
			// 服务器绑定端口监听
			ChannelFuture f = b.bind(portNumber).sync();
			
			// 监听服务器关闭监听
			f.channel().closeFuture().sync();
		} finally {
			// TODO: handle exception
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}
}
