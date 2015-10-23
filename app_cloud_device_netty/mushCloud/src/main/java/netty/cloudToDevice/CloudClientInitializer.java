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
         * ����ط��� ����ͷ���˶�Ӧ�ϡ������޷���������ͱ���
         * 
         * ����ͱ��� �ҽ�������һ��Ϊ�����ϸ�Ľ��⡣�ٴ���ʱ������ϸ������
         * DelimiterBasedFrameDecoder��һ�������ַ�����βΪ��\n������β�ġ�
         * */
		pipeline.addLast("framer",new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
		pipeline.addLast("decoder",new StringDecoder());
		pipeline.addLast("encoder",new StringEncoder());
		
		//�ͻ����߼�
		pipeline.addLast("handleer",new CloudClientHandler());
		
	}
	
}
