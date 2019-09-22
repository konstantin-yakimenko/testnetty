package com.jakimenko.testnetty.web;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jakimenko.testnetty.handler.IHttpHandler;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.AsciiString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static io.netty.buffer.Unpooled.wrappedBuffer;
import static io.netty.handler.codec.http.HttpHeaderValues.TEXT_PLAIN;

/**
 * @author konst
 */
@ChannelHandler.Sharable
@Component
public class HttpControllerHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    private static final Logger logger = LoggerFactory.getLogger(HttpControllerHandler.class);

    private static ObjectMapper jacksonObjectMapper = new ObjectMapper();
    private final PathHandlerProvider pathHandlerProvider;

    @Autowired
    public HttpControllerHandler(PathHandlerProvider pathHandlerProvider) {
        this.pathHandlerProvider = pathHandlerProvider;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest request) throws Exception {
        HttpResponseStatus responseStatus = HttpResponseStatus.OK;
        String responseBody = "";
        AsciiString mimeType = HttpHeaderValues.APPLICATION_JSON;

        try {
            IHttpHandler handler = pathHandlerProvider.getHandler(request);
            if (handler == null) {
                writeResponse(ctx, HttpResponseStatus.NOT_FOUND, TEXT_PLAIN, "Not found.");
                return;
            }

            Object response = handler.handleRequest(request);
            if (request.method().equals(HttpMethod.POST)) {
                responseStatus = HttpResponseStatus.CREATED;
            }

            if (response instanceof String) {
                responseBody = (String) response;
            } else if (response != null) {
                responseBody = toJson(response);
            }
        } catch (Exception e) {
            responseStatus = HttpResponseStatus.INTERNAL_SERVER_ERROR;
            responseBody = e.getMessage() == null ? "" : e.getMessage();
            mimeType = TEXT_PLAIN;
        }
        writeResponse(ctx, responseStatus, mimeType, responseBody);
    }

    private String toJson(Object object) {
        try {
            return jacksonObjectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            logger.error("Error while conert to json: ", e);
            return null;
        }
    }

    private void writeResponse(ChannelHandlerContext ctx, HttpResponseStatus status, AsciiString mimeType, String body) {
        ByteBuf buf = wrappedBuffer(body.getBytes());
        DefaultFullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, status, buf);

        response.headers().set(HttpHeaderNames.CONTENT_LENGTH, buf.readableBytes());
        response.headers().set(HttpHeaderNames.CONTENT_TYPE, mimeType.toString() + "; charset=UTF-8");
        HttpUtil.setKeepAlive(response, true);
        ctx.writeAndFlush(response);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.error("Something went wrong", cause);
        writeResponse(ctx, HttpResponseStatus.INTERNAL_SERVER_ERROR, TEXT_PLAIN, cause.getMessage() == null ? "" : cause.getMessage());
    }
}
