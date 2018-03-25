
package com.networknt.portal.certification.handler;

import com.networknt.config.Config;
import com.networknt.portal.certification.model.Issue;
import com.networknt.utility.NioUtils;
import com.networknt.rpc.Handler;
import com.networknt.rpc.router.ServiceHandler;
import io.undertow.server.HttpServerExchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.ByteBuffer;
import java.util.List;
import java.util.Map;

@ServiceHandler(id="lightapi.net/certification/certifyEndpoint/0.1.0")
public class Endpoint implements Handler {
    static final Logger logger = LoggerFactory.getLogger(Endpoint.class);

    @Override
    public ByteBuffer handle(HttpServerExchange exchange, Object input)  {
        Map<String, String> map = (Map<String, String>)input;
        String host = map.get("host");
        String path = map.get("path");
        String token = map.get("token");
        String environment = map.get("environment");
        try {
            Map<String, Object> si = ServerInfoRetriever.retrieve(host, path, token);
            if(logger.isDebugEnabled()) logger.debug("server info = " + Config.getInstance().getMapper().writeValueAsString(si));
            List<Issue> list = ServerInfoProcessor.process(si);
            if(logger.isDebugEnabled()) logger.debug("list = " + Config.getInstance().getMapper().writeValueAsString(list));
            return NioUtils.toByteBuffer(Config.getInstance().getMapper().writeValueAsString(list));
        } catch (Exception e) {
            logger.error("Exception:", e);
            String error = "";
            try {
                error = Config.getInstance().getMapper().writeValueAsString(e.getStackTrace());
            } catch (Exception er) {
                logger.error("Exception:", e);
            }
            return NioUtils.toByteBuffer(error);
        }
    }
}
