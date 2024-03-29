package com.jakimenko.testnetty.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.jakimenko.testnetty.exception.BadRequestException;
import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.QueryStringDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author konst
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public abstract class AbstractHandler implements IHttpHandler {

    private static final Logger logger = LoggerFactory.getLogger(AbstractHandler.class);

    private static final Pattern PATTERN = Pattern.compile("/(?<entity>\\w+)/(?<id>\\d+)");
    private static final Charset CHARSET = Charset.defaultCharset();
    private static final ObjectMapper MAPPER =  new ObjectMapper(); // di ?

    public static Integer asInteger(String value) {
        try {
            return Integer.valueOf(value);
        } catch (NumberFormatException e) {
            logger.error("Error: ", e);
            throw new BadRequestException("Bad value for integer [" + value + "] ");
        }
    }

    public static Long asLong(String value) {
        try {
            return Long.valueOf(value);
        } catch (NumberFormatException e) {
            logger.error("Error: ", e);
            throw new BadRequestException("Bad value for long [" + value + "] ");
        }
    }

    public static String param(Map<String, List<String>> queryParameters, String key, boolean required) {
        List<String> pq = queryParameters.get(key);
        String param = (pq != null ? pq.get(0) : null);
        if (required && (param == null || param.isEmpty())) {
            throw new BadRequestException("[" + key + "] must not be null!");
        }
        return param;
    }

    public static Long paramAsLong(Map<String, List<String>> queryParameters, String key, boolean required) {
        String param = param(queryParameters, key, required);
        if (!required && param == null) {
            return null;
        }
        return asLong(param);
    }

    public static Integer paramAsInteger(Map<String, List<String>> queryParameters, String key, boolean required) {
        String param = param(queryParameters, key, required);
        if (!required && param == null) {
            return null;
        }
        return asInteger(param);
    }

    public static Map<String, List<String>> parameters(FullHttpRequest request) {
        return new QueryStringDecoder(request.uri()).parameters();
    }

    public static Map<String, String> readJson(FullHttpRequest request) {
        try {
            TypeFactory typeFactory = MAPPER.getTypeFactory();
            MapType mapType = typeFactory.constructMapType(HashMap.class, String.class, String.class);

            ByteBuf buf = request.content();
            byte[] bytes = new byte[buf.readableBytes()];
            buf.readBytes(bytes);

            return MAPPER.readValue(bytes, mapType);
        } catch (Exception e) {
            logger.error("Error: ", e);
            throw new BadRequestException();
        }
    }

    public static int fetchId(String url) {
        try {
            String uri = url.substring(1);
            if (uri.contains("?")) {
                uri = uri.substring(0, uri.indexOf("?"));
            }

            uri = uri.substring(uri.lastIndexOf("/") + 1);
            uri = uri.substring(0, uri.contains("/") ? uri.lastIndexOf("/") : uri.length());
            return Integer.parseInt(uri);
        } catch (Exception e) {
            logger.error("Error: ", e);
            throw new BadRequestException(e.getMessage());
        }
    }
}
