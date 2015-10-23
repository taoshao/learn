package mushCloud;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Util.mushCloudUtil;

public class CloudServer extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
//		super.doGet(req, resp);
		try {
			GetDataThread getDataThread = new GetDataThread();
			getDataThread.start();
		} catch (Exception e) {
			// TODO: handle exception
		}
//		while( HelloServerHandler.order == null) 
			System.out.println("get !!!!!" + mushCloudUtil.getStatus());
//		System.out.println("order : " + order + " sented!");
		resp.setContentType("text/html;charset=GBK");
		resp.getOutputStream().write(("recive from app ,the order is :" + mushCloudUtil.getStatus()).toString().getBytes("GBK"));
	}
	
	
}
//
//private class GetDataThread extends Thread{
//    /**
//     * 服务端监听的端口地址
//     */
//	public static final int portNumber = 7878;
//	
//	@Override
//    public void run(){
//		try {
//			this.listen();
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//		
//	}
//	
//	private void listen() throws InterruptedException{
//		// TODO Auto-generated method stub
//		EventLoopGroup bossGroup = new NioEventLoopGroup();
//		EventLoopGroup workerGroup = new NioEventLoopGroup();
//		try {
//			ServerBootstrap b = new ServerBootstrap();
//			b.group(bossGroup,workerGroup)
//			.channel(NioServerSocketChannel.class)
//			.childHandler(new CloudServerInitializer());
//			
//			// 服务器绑定端口监听
//			ChannelFuture f = b.bind(portNumber).sync();
//			
//			// 监听服务器关闭监听
//			f.channel().closeFuture().sync();
//		} finally {
//			// TODO: handle exception
//			bossGroup.shutdownGracefully();
//			workerGroup.shutdownGracefully();
//		}
//	}
//}