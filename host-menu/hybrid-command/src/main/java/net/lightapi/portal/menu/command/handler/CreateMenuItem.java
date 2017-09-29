
package net.lightapi.portal.menu.command.handler;

import com.networknt.config.Config;
import com.networknt.eventuate.common.AggregateRepository;
import com.networknt.eventuate.common.EventuateAggregateStore;
import com.networknt.rpc.Handler;
import com.networknt.rpc.router.ServiceHandler;
import com.networknt.service.SingletonServiceFactory;
import com.networknt.utility.NioUtils;
import net.lightapi.portal.menu.MenuItemService;
import net.lightapi.portal.menu.MenuItemServiceImpl;
import net.lightapi.portal.menu.domain.MenuItemAggregate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.ByteBuffer;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@ServiceHandler(id="lightapi.net/menu/createMenuItem/0.1.0")
public class CreateMenuItem implements Handler {
    static final Logger logger = LoggerFactory.getLogger(CreateMenuItem.class);
    private EventuateAggregateStore eventStore  = (EventuateAggregateStore) SingletonServiceFactory.getBean(EventuateAggregateStore.class);
    private AggregateRepository repository = new AggregateRepository(MenuItemAggregate.class, eventStore);
    private MenuItemService service = new MenuItemServiceImpl(repository);

    @Override
    public ByteBuffer handle(Object input)  {
        System.out.println("input = " + input);
        try {
            CompletableFuture<String> result =  service.create(Config.getInstance().getMapper().writeValueAsString(input)).thenApply((e) -> {
                String s = e.getAggregate().getMenuItem();
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
