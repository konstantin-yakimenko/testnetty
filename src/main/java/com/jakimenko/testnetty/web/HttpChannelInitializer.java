package com.jakimenko.testnetty.web;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author konst
 */
@Component
public class HttpChannelInitializer extends ChannelInitializer<SocketChannel>{

    private final HttpControllerHandler httpControllerHandler;

    @Autowired
    public HttpChannelInitializer(HttpControllerHandler httpControllerHandler) {
        this.httpControllerHandler = httpControllerHandler;
    }

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new HttpRequestDecoder());
        pipeline.addLast(new HttpObjectAggregator(1048576));
        pipeline.addLast(new HttpResponseEncoder());
        pipeline.addLast(httpControllerHandler);
    }
}
