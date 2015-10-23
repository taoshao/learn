package DeviceGetData;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




public class DeviceGetDateThread extends Thread{
    /**
     * 服务端监听的端口地址
     */
	public static final int portNumber = 7575;				//监听端口7979
	
	@Override
    public void run(){
		try {
			listen();
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
			.childHandler(new DeviceServerInitializer());
			
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
