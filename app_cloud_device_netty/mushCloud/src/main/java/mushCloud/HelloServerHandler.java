package mushCloud;

import java.net.InetAddress;

import Util.mushCloudUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

public class HelloServerHandler extends SimpleChannelInboundHandler<String> {
//	public static String order;
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, String msg)
			throws Exception {
		// TODO Auto-generated method stub
		mushCloudUtil.setStatus(msg);
		
		System.out.println(ctx.channel().remoteAddress() + " Say : " + msg + "I'm Cloud! I  get status from APP :" + mushCloudUtil.getStatus());
//		setOrder(msg);
		
		ctx.writeAndFlush("Received your message !\n");
		ctx.writeAndFlush(new TextWebSocketFrame("[you]" + msg ));
//		if(mushCloudUtil.getResp() != null){
//			mushCloudUtil.getResp().getOutputStream().write(("hello,the order is :" + mushCloudUtil.getStatus()).toString().getBytes("GBK"));
//			System.out.println("get the resp!");
//		}
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("RamoteAddress : " + ctx.channel().remoteAddress() + " active!");
		ctx.writeAndFlush("Welcome to " + InetAddress.getLocalHost().getHostName() + " service !\n");
		super.channelActive(ctx);
	}

}
