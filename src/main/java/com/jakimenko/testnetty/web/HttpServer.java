package com.jakimenko.testnetty.web;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.*;
import javax.annotation.PreDestroy;

/**
 * @author konst
 */
@Component
public class HttpServer implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(HttpServer.class);

    @Value("${server.port}")
    private Integer port;

    private final HttpChannelInitializer httpChannelInitializer;
    private final EpollEventLoopGroup bossGroup = new EpollEventLoopGroup(1);
    private final EpollEventLoopGroup workerGroup = new EpollEventLoopGroup(12); // 12 or default ?

    @Autowired
    public HttpServer(HttpChannelInitializer httpChannelInitializer) {
        this.httpChannelInitializer = httpChannelInitializer;
    }

    @Override
    public void run(String... args) throws Exception {
        try {
            ServerBootstrap sb = new ServerBootstrap()
                .group(bossGroup, workerGroup)
                .channel(EpollServerSocketChannel.class)
                .childHandler(httpChannelInitializer)
                .option(ChannelOption.SO_BACKLOG, 512)
//                    .option(ChannelOption.SO_REUSEADDR, true)
//                    .childOption(ChannelOption.SO_SNDBUF, 128 * 1024)
//                    .childOption(ChannelOption.SO_RCVBUF, 128 * 1024)
//                    .childOption(ChannelOption.TCP_NODELAY, true)
//                    .childOption(ChannelOption.SO_REUSEADDR, true)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childOption(ChannelOption.CONNECT_TIMEOUT_MILLIS, 3000);

            ChannelFuture future = sb.bind(port);
            logger.info("Server is started on {} port.", port);

            future.sync(); // locking the thread until groups are going on
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            logger.error("Something went wrong: ", e);
        } finally {
            shutdown();
        }
    }

    @PreDestroy
    void shutdown() {
        logger.info("Server is shutting down.");
        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
    }
}
