package sample.ws.service;

import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@javax.jws.WebService(serviceName = "HelloService", portName = "HelloPort",
    targetNamespace = "http://service.ws.sample/", endpointInterface = "sample.ws.service.Hello")
@Component
public class HelloPortImpl implements Hello {

  private static final Logger LOG = Logger.getLogger(HelloPortImpl.class.getName());

  @Resource
  private WebServiceContext context;

  @Value("${app.username}")
  private String username;

  @Value("${app.password}")
  private String password;

  public java.lang.String sayHello(java.lang.String myname) {
    LOG.info("Executing operation sayHello -> " + myname);
    try {
      if (isAuthenticated()) {
        return "Hello, Welcome to CXF Springboot, " + myname + "!!!";
      } else {
        return "You are not authorized, " + myname + "!!!";
      }
    } catch (java.lang.Exception ex) {
      ex.printStackTrace();
      throw new RuntimeException(ex);
    }
  }

  @SuppressWarnings({"rawtypes", "unchecked"})
  private boolean isAuthenticated() {
    MessageContext messageContext = context.getMessageContext();
    Map httpHeaders = (Map) messageContext.get(MessageContext.HTTP_REQUEST_HEADERS);
    List<String> authorization = (List<String>) httpHeaders.get("Authorization");

    LOG.info("authorization: " + authorization);

    String expectedHash =
        Base64.getEncoder().encodeToString((username + ":" + password).getBytes());
    LOG.info("expectedHash: " + expectedHash);

    if (authorization.contains("Basic " + expectedHash)) {
      return true;
    } else {
      return false;
    }
  }
}
