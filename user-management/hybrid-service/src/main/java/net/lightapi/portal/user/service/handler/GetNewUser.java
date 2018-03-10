
package net.lightapi.portal.user.service.handler;

import com.networknt.utility.NioUtils;
import com.networknt.rpc.Handler;
import com.networknt.rpc.router.ServiceHandler;
import java.nio.ByteBuffer;

@ServiceHandler(id="lightapi.net/user/createUser/0.1.0")
public class GetNewUser implements Handler {
    @Override
    public ByteBuffer handle(Object input)  {
        return NioUtils.toByteBuffer("");
    }
}
