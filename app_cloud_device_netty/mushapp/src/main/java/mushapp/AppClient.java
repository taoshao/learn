package mushapp;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * Client active 
Service say:Welcome to cvt2895 service!
Service say:Received your message !
order : open sented!
 * */
public class AppClient extends HttpServlet {
	public static String host = "127.0.0.1";
	public static int port = 7878;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
//		super.doGet(req, resp);
		String order = req.getParameter("order");

		
//		String data = this.getData();
		sent(order);
		System.out.println("order : " + order + " sented!");
		resp.setContentType("text/html;charset=GBK");
		resp.getOutputStream().write(("order : " + order + " sented!").toString().getBytes("GBK"));
	}
	private void sent(String order) {
		// TODO Auto-generated method stub
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			Bootstrap b = new Bootstrap();
			b.group(group)
			.channel(NioSocketChannel.class)
			.handler(new AppClientInitializer());
			
			//连接服务端
			Channel ch = b.connect(host,port).sync().channel();
			
                /*
                 * 向服务端发送在控制台输入的文本 并用"\r\n"结尾
                 * 之所以用\r\n结尾 是因为我们在handler中添加了 DelimiterBasedFrameDecoder 帧解码。
                 * 这个解码器是一个根据\n符号位分隔符的解码器。所以每条消息的最后必须加上\n否则无法识别和解码
                 * */
				ch.writeAndFlush(order + "\r\n");
			
		} catch (Exception e) {
			// TODO: handle exception
            // The connection is closed automatically on shutdown.
			group.shutdownGracefully();
			
		}
	}
}
