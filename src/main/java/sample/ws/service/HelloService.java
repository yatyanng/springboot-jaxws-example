package sample.ws.service;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 3.5.2 2022-06-28T17:39:54.722+08:00 Generated source
 * version: 3.5.2
 *
 */
@WebServiceClient(name = "HelloService", wsdlLocation = "file:src/main/resources/helloservice.wsdl",
    targetNamespace = "http://service.ws.sample/")
public class HelloService extends Service {

  public final static URL WSDL_LOCATION;

  public final static QName SERVICE = new QName("http://service.ws.sample/", "HelloService");
  public final static QName HelloPort = new QName("http://service.ws.sample/", "HelloPort");
  static {
    URL url = null;
    try {
      url = new URL("file:src/main/resources/helloservice.wsdl");
    } catch (MalformedURLException e) {
      java.util.logging.Logger.getLogger(HelloService.class.getName()).log(
          java.util.logging.Level.INFO, "Can not initialize the default wsdl from {0}",
          "file:src/main/resources/helloservice.wsdl");
    }
    WSDL_LOCATION = url;
  }

  public HelloService(URL wsdlLocation) {
    super(wsdlLocation, SERVICE);
  }

  public HelloService(URL wsdlLocation, QName serviceName) {
    super(wsdlLocation, serviceName);
  }

  public HelloService() {
    super(WSDL_LOCATION, SERVICE);
  }

  public HelloService(WebServiceFeature... features) {
    super(WSDL_LOCATION, SERVICE, features);
  }

  public HelloService(URL wsdlLocation, WebServiceFeature... features) {
    super(wsdlLocation, SERVICE, features);
  }

  public HelloService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
    super(wsdlLocation, serviceName, features);
  }



  /**
   *
   * @return returns Hello
   */
  @WebEndpoint(name = "HelloPort")
  public Hello getHelloPort() {
    return super.getPort(HelloPort, Hello.class);
  }

  /**
   *
   * @param features A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.
   *        Supported features not in the <code>features</code> parameter will have their default
   *        values.
   * @return returns Hello
   */
  @WebEndpoint(name = "HelloPort")
  public Hello getHelloPort(WebServiceFeature... features) {
    return super.getPort(HelloPort, Hello.class, features);
  }

}
