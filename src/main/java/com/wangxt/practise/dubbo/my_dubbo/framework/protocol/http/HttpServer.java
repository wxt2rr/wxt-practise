package com.wangxt.practise.dubbo.my_dubbo.framework.protocol.http;

import org.apache.catalina.Host;
import org.apache.catalina.Server;
import org.apache.catalina.Service;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.core.StandardEngine;
import org.apache.catalina.core.StandardHost;
import org.apache.catalina.startup.Tomcat;

/**
 * 服务提供者服务
 */
public class HttpServer {

    public void start(String host, int port){
        // 嵌入式tomcat
        try{
            Tomcat tomcat = new Tomcat();

            Server server = tomcat.getServer();
            Service service = server.findService("Tomcat");

            Connector connector = new Connector();
            connector.setPort(port);

            StandardEngine engine = new StandardEngine();
            engine.setDefaultHost(host);

            Host h = new StandardHost();
            h.setName(host);

            String context = "";
            StandardContext standardContext = new StandardContext();
            standardContext.setPath(context);
            standardContext.addLifecycleListener(new Tomcat.FixContextListener());

            h.addChild(standardContext);
            engine.addChild(h);

            service.setContainer(engine);
            service.addConnector(connector);

            tomcat.addServlet(context, "dis", new DispatcherServlet());
            standardContext.addServletMappingDecoded("/*", "dis");

            tomcat.start();
            tomcat.getServer().await();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
