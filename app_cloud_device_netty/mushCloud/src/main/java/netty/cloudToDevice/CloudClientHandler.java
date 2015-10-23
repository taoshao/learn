package netty.cloudToDevice;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class CloudClientHandler extends SimpleChannelInboundHandler<String>{

	@Override
	protected void channelRead0(ChannelHandlerContext arg0, String msg)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("DeviceService say:" + msg);
		
	}
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("CoudClient active ");
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("CloudClient close ");
        super.channelInactive(ctx);
    }

}
