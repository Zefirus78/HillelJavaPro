package hw_33.server;


import io.netty.channel.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static hw_33.server.NettyServer.activeClients;
import static hw_33.server.NettyServer.clientCount;

@ChannelHandler.Sharable
public class ServerHandler extends SimpleChannelInboundHandler<String> {
    private static final Logger LOGGER = LogManager.getLogger(ServerHandler.class);
    private String clientName;

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        clientName = "client-" + clientCount++;
        activeClients.put(clientName, ctx.channel());
        LocalDateTime now = LocalDateTime.now();
        String formattedDateTime = now.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        System.out.printf("[SERVER] %s successfully connected at [%s] with socket: %s%n", clientName, formattedDateTime, ctx.channel());
        LOGGER.info("[SERVER] {} successfully connected at {} with socket: {}", clientName, formattedDateTime, ctx.channel());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) {
        if (msg.startsWith("/name ")) {
            String newName = msg.substring(6).trim();
            if (!newName.isEmpty() && !activeClients.containsKey(newName)) {
                activeClients.remove(clientName);
                activeClients.put(newName, ctx.channel());
                System.out.println("[SERVER] " + clientName + " changed the name to " + newName);
                clientName = newName;
            } else {
                ctx.writeAndFlush("[SERVER] This name is already taken or incorrect!\n");
            }
        } else if (msg.contains("exit")) {
            ctx.close();
        } else {
            System.out.println("[" + clientName + "]: " + msg);
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        activeClients.remove(clientName);
        System.out.println("[SERVER] " + clientName + " disconnected");
        LOGGER.info("[SERVER] {} disconnected", clientName);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        LOGGER.error("[SERVER] Error with {}", clientName, cause);
        ctx.close();
    }
}
