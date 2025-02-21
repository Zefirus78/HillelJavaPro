package hw_33.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class NettyClient2 {

    private static final Logger LOGGER = LogManager.getLogger(NettyClient2.class);
    static final String HOST = "localhost";
    static final int PORT = 8080;

    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel sc) throws Exception {
                            ChannelPipeline pipeline = sc.pipeline();

                            pipeline.addLast(new StringDecoder());
                            pipeline.addLast(new StringEncoder());
                            pipeline.addLast(new ClientHandler());
                        }
                    });
            Channel channel = bootstrap.connect(HOST, PORT).sync().channel();
            LOGGER.info("[CLIENT] Connected to the server at host: {}, port: {} ", HOST,PORT);

            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNextLine()) {
                String message = scanner.nextLine();
                channel.writeAndFlush(message + "\n");
                if ("exit".equalsIgnoreCase(message)) {
                    LOGGER.info("[CLIENT] Disconnected from server.");
                    channel.closeFuture().sync();
                    break;
                }
            }
        } finally {
            group.shutdownGracefully();
            LOGGER.info("[CLIENT] Disconnected from host: {}, port: {} ", HOST,PORT);
        }
    }
}
