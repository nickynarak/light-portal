
package net.lightapi.portal.menu.command.handler;

import com.networknt.utility.NioUtils;
import com.networknt.rpc.Handler;
import com.networknt.rpc.router.ServiceHandler;
import java.nio.ByteBuffer;

@ServiceHandler(id="lightapi.net/menu/deleteMenuItem/0.1.0")
public class DeleteMenuItem implements Handler {
    @Override
    public ByteBuffer handle(Object input)  {
        return NioUtils.toByteBuffer("");
    }
}
