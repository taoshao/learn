package DeviceGetData;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.net.InetAddress;

import Util.DeviceUtil;

public class HelloServerHandler extends SimpleChannelInboundHandler<String> {
//	public static String order;
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, String msg)
			throws Exception {
		// TODO Auto-generated method stub
		DeviceUtil.setStatus(msg);
		DeviceUtil.setGetDataCloudFlag(true);
		System.out.println(ctx.channel().remoteAddress() + " Say : " + msg + " Device get status from Cloud:" + DeviceUtil.getStatus());
//		setOrder(msg);
		
		ctx.writeAndFlush("I'm Device ! I Received your message !\n");
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
