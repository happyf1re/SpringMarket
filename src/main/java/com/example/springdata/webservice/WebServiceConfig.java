package com.example.springdata.webservice;


import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {
    @Bean
    public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean(servlet, "/ws/*");
    }

//    @Bean(name = "greeting")
//    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema xsdSchema) {
//        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
//        wsdl11Definition.setPortTypeName("GreetingPort");
//        wsdl11Definition.setLocationUri("/ws");
//        wsdl11Definition.setTargetNamespace("http://example.com/springdata/webservice");
//        wsdl11Definition.setSchema(xsdSchema);
//        return wsdl11Definition;
//    }
//
//    @Bean
//    public XsdSchema xsdSchema() {
//        return new SimpleXsdSchema(new ClassPathResource("greeting.xsd"));
//    }

    @Bean(name = "products")
    public DefaultWsdl11Definition productsWsdlDefinition() {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("ProductsPort");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace("http://example.com/springdata/webservice");
        wsdl11Definition.setSchema(xsdProductsSchema());
        return wsdl11Definition;
    }

    @Bean("productsSchema")
    public XsdSchema xsdProductsSchema() {
        return new SimpleXsdSchema(new ClassPathResource("products.xsd"));
    }
}


