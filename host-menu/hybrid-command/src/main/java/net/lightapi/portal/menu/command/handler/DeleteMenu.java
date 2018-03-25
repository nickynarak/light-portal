
package net.lightapi.portal.menu.command.handler;

import com.fasterxml.jackson.databind.JsonNode;
import com.networknt.config.Config;
import com.networknt.eventuate.common.AggregateRepository;
import com.networknt.eventuate.common.EventuateAggregateStore;
import com.networknt.service.SingletonServiceFactory;
import com.networknt.utility.NioUtils;
import com.networknt.rpc.Handler;
import com.networknt.rpc.router.ServiceHandler;
import io.undertow.server.HttpServerExchange;
import net.lightapi.portal.menu.MenuService;
import net.lightapi.portal.menu.MenuServiceImpl;
import net.lightapi.portal.menu.domain.MenuAggregate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.ByteBuffer;
import java.util.concurrent.CompletableFuture;

@ServiceHandler(id="lightapi.net/menu/deleteMenu/0.1.0")
public class DeleteMenu implements Handler {
    static final Logger logger = LoggerFactory.getLogger(DeleteMenu.class);
    private EventuateAggregateStore eventStore  = (EventuateAggregateStore) SingletonServiceFactory.getBean(EventuateAggregateStore.class);
    private AggregateRepository repository = new AggregateRepository(MenuAggregate.class, eventStore);
    private MenuService service = new MenuServiceImpl(repository);
    @Override
    public ByteBuffer handle(HttpServerExchange exchange, Object input)  {
        JsonNode inputPara = Config.getInstance().getMapper().valueToTree(input);

        String id = inputPara.findPath("id").asText();

        System.out.println("delete menu id:" + id);


        try {
            CompletableFuture<String> result =  service.remove(id).thenApply((e) -> {
                String s = e.getAggregate().getMenu();
                return s;
            });
            return NioUtils.toByteBuffer(result.get());
        } catch (Exception e) {
            logger.error("Error converting map to json", e);
            // TODO return error code
            return NioUtils.toByteBuffer("error");
        }
    }
}
