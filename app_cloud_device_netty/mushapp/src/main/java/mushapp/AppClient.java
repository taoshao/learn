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
			
			//���ӷ����
			Channel ch = b.connect(host,port).sync().channel();
			
                /*
                 * �����˷����ڿ���̨������ı� ����"\r\n"��β
                 * ֮������\r\n��β ����Ϊ������handler������� DelimiterBasedFrameDecoder ֡���롣
                 * �����������һ������\n����λ�ָ����Ľ�����������ÿ����Ϣ�����������\n�����޷�ʶ��ͽ���
                 * */
				ch.writeAndFlush(order + "\r\n");
			
		} catch (Exception e) {
			// TODO: handle exception
            // The connection is closed automatically on shutdown.
			group.shutdownGracefully();
			
		}
	}
}
