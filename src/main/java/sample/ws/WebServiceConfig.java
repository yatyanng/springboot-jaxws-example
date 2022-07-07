package sample.ws;

import java.util.List;
import javax.xml.ws.Endpoint;
import javax.xml.ws.WebServiceFeature;
import org.apache.cxf.Bus;
import org.apache.cxf.ext.logging.LoggingFeature;
import org.apache.cxf.ext.logging.LoggingInInterceptor;
import org.apache.cxf.ext.logging.LoggingOutInterceptor;
import org.apache.cxf.interceptor.Interceptor;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.message.Message;
import org.apache.cxf.metrics.MetricsFeature;
import org.apache.cxf.metrics.MetricsProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sample.ws.service.HelloPortImpl;

@Configuration
public class WebServiceConfig {

  @Autowired
  private Bus bus;

  @Autowired
  private MetricsProvider metricsProvider;

  @Bean
  public Endpoint endpoint() {

    LoggingFeature loggingFeature = new LoggingFeature();
    loggingFeature.setPrettyLogging(true);
    loggingFeature.setVerbose(true);
    loggingFeature.setLogMultipart(true);

    EndpointImpl endpoint = new EndpointImpl(bus, new HelloPortImpl(), null, null,
        new WebServiceFeature[] {loggingFeature, new MetricsFeature(metricsProvider)});
    endpoint.publish("/HelloPort");
    return endpoint;
  }

  @Bean
  public List<Interceptor<? extends Message>> inInterceptors() {
    return List.of(new LoggingInInterceptor());
  }

  @Bean
  public List<Interceptor<? extends Message>> outInterceptors() {
    return List.of(new LoggingOutInterceptor());
  }
}
