
package net.lightapi.portal.menu.command.handler;

import com.fasterxml.jackson.databind.JsonNode;
import com.networknt.config.Config;
import com.networknt.service.SingletonServiceFactory;
import com.networknt.utility.NioUtils;
import com.networknt.rpc.Handler;
import com.networknt.rpc.router.ServiceHandler;
import net.lightapi.portal.menu.MenuService;

import java.nio.ByteBuffer;
import java.util.concurrent.CompletableFuture;

@ServiceHandler(id="lightapi.net/portal/menu/create/0.1.0")
public class CreateMenu implements Handler {

    private MenuService service = SingletonServiceFactory.getBean(MenuService.class);

    @Override
    public ByteBuffer handle(Object input)  {

        JsonNode inputPara = Config.getInstance().getMapper().valueToTree(input);

        TodoInfo todo = new TodoInfo();
        todo.setTitle(inputPara.findPath("title").asText());
        todo.setCompleted(inputPara.findPath("completed").asBoolean());
        todo.setOrder(inputPara.findPath("order").asInt());

        CompletableFuture<TodoInfo> result = service.add(todo).thenApply((e) -> {
            TodoInfo m = e.getAggregate().getTodo();
            return m;
        });

        return NioUtils.toByteBuffer("{\"message\":" + result + "}");
    }
}
