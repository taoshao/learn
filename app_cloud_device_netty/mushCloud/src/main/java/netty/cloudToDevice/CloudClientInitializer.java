package netty.cloudToDevice;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class CloudClientInitializer extends ChannelInitializer<SocketChannel> {

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		// TODO Auto-generated method stub
		ChannelPipeline pipeline = ch.pipeline();
		 /*
         * 这个地方的 必须和服务端对应上。否则无法正常解码和编码
         * 
         * 解码和编码 我将会在下一张为大家详细的讲解。再次暂时不做详细的描述
         * DelimiterBasedFrameDecoder是一个根据字符串结尾为“\n”来结尾的。
         * */
		pipeline.addLast("framer",new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
		pipeline.addLast("decoder",new StringDecoder());
		pipeline.addLast("encoder",new StringEncoder());
		
		//客户端逻辑
		pipeline.addLast("handleer",new CloudClientHandler());
		
	}
	
}
