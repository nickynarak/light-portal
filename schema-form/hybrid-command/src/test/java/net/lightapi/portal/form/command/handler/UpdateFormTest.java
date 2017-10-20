
package net.lightapi.portal.form.command.handler;

import com.networknt.client.Http2Client;
import com.networknt.eventuate.common.impl.JSonMapper;
import com.networknt.exception.ApiException;
import com.networknt.exception.ClientException;
import io.undertow.UndertowOptions;
import io.undertow.client.ClientConnection;
import io.undertow.client.ClientRequest;
import io.undertow.client.ClientResponse;
import io.undertow.util.Headers;
import io.undertow.util.Methods;
import net.lightapi.portal.form.common.model.*;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xnio.IoUtils;
import org.xnio.OptionMap;

import java.net.URI;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

public class UpdateFormTest {
    @ClassRule
    public static TestServer server = TestServer.getInstance();

    static final Logger logger = LoggerFactory.getLogger(UpdateForm.class); 
    static final boolean enableHttp2 = server.getServerConfig().isEnableHttp2();
    static final boolean enableHttps = server.getServerConfig().isEnableHttps();
    static final int httpPort = server.getServerConfig().getHttpPort();
    static final int httpsPort = server.getServerConfig().getHttpsPort();
    static final String url = enableHttp2 || enableHttps ? "https://localhost:" + httpsPort : "http://localhost:" + httpPort;

    @Test
    public void testUpdateForm() throws ClientException, ApiException {
/*
        final Http2Client client = Http2Client.getInstance();
        final CountDownLatch latch = new CountDownLatch(1);
        final ClientConnection connection;
        try {
            connection = client.connect(new URI(url), Http2Client.WORKER, Http2Client.SSL, Http2Client.POOL, enableHttp2 ? OptionMap.create(UndertowOptions.ENABLE_HTTP2, true): OptionMap.EMPTY).get();
        } catch (Exception e) {
            throw new ClientException(e);
        }
        FormRequest formRequest = new FormRequest("lightapi.net", "form", "updateForm", "0.1.0");
        Form form = new Form();
        form.setDescription("test Form");
        form.setVersion("1.0");
        form.setFormId("111-222-333");
        Action action = new Action("lightapi.net", "form", "createForm", "0.1.0", "POST");
        form.setAction(action);
        FormField formField = new FormField("Key_field", "text", false, "1");
        form.addFormField(formField);
        Property property = new Property("Key", "text", "Key_field", false, 1);
        Schema schema = new Schema("FormSchema", "Form Schema");
        schema.addProperties(property);
        form.setSchema(schema);
        formRequest.setData(form);

        String json = JSonMapper.toJson(formRequest);
        System.out.println("\n request json string: " + json);

        final AtomicReference<ClientResponse> reference = new AtomicReference<>();
        try {
            ClientRequest request = new ClientRequest().setPath("/api/json").setMethod(Methods.POST);
            request.getRequestHeaders().put(Headers.CONTENT_TYPE, "application/json");
            request.getRequestHeaders().put(Headers.TRANSFER_ENCODING, "chunked");
            connection.sendRequest(request, client.createClientCallback(reference, latch, json));
            latch.await();
        } catch (Exception e) {
            logger.error("Exception: ", e);
            throw new ClientException(e);
        } finally {
            IoUtils.safeClose(connection);
        }
        int statusCode = reference.get().getResponseCode();
        String body = reference.get().getAttachment(Http2Client.RESPONSE_BODY);
        Assert.assertEquals(200, statusCode);
        Assert.assertNotNull(body);
*/
    }
}