package com.mashibing.nettychat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.concurrent.GlobalEventExecutor;

public class Server {

    public static ChannelGroup clients = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    public static void main(String[] args) throws Exception{
        //负责接客
        EventLoopGroup bossGroup = new NioEventLoopGroup(2);
        //负责服务
        EventLoopGroup workerGroup = new NioEventLoopGroup(4);
        //Server启动辅助类
        ServerBootstrap b = new ServerBootstrap();

        b.group(bossGroup,workerGroup);
        //异步全双工
        b.channel(NioServerSocketChannel.class);
        b.childHandler(new ServerInitializer());
        ChannelFuture future = b.bind(8888).sync();
        future.channel().closeFuture().sync();


        //b.bind(8888).sync();

        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();

    }

}

class ServerInitializer extends ChannelInitializer<SocketChannel>{
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        System.out.println("a client connect!");
        socketChannel.pipeline().addLast(new ServerChildHandler());
        socketChannel.pipeline().addLast(new ServerChildHandler());
    }
}

class ServerChildHandler extends ChannelInboundHandlerAdapter{
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Server.clients.add(ctx.channel());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //super.exceptionCaught(ctx, cause);
        cause.printStackTrace();
        Server.clients.remove(ctx.channel());
        ctx.close();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //ByteBuf buf = (ByteBuf)msg;
        //System.out.println(buf.toString());
        //buf.release();
        //ctx.writeAndFlush(msg);

        ByteBuf buf = null;


        buf = (ByteBuf)msg;
        byte[] bytes = new byte[buf.readableBytes()];
        buf.getBytes(buf.readerIndex(),bytes);
        String str = new String(bytes);
        if(str.equals("__bye__")){
            System.out.println("client ready to quit.");
            Server.clients.remove(ctx.channel());
            ctx.close();

            System.out.println(Server.clients.size());
        }else{
            Server.clients.writeAndFlush(buf);
        }

    }
}