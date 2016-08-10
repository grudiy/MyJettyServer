package com.grudiy.servlets.myjetty;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHandler;

/*
create application with embedded Jetty servlet container
 */
public class Main {

    public static void main(String[] args) {
        // Create a basic Jetty server object that will listen on port 8080.  Note that if you set this to port 0
        // then a randomly available port will be assigned that you can either look in the logs for the port,
        // or programmatically obtain it for use in test cases.
        Server server = new Server(9090);

        try {
            // The ServletHandler is a dead simple way to create a context handler
            // that is backed by an instance of a Servlet.
            // This handler then needs to be registered with the Server object.

//            ServletHandler handlerHello = new ServletHandler();
//            server.setHandler(handlerHello);


            // Realization with Context

            ServletContextHandler context = new ServletContextHandler(
                    ServletContextHandler.SESSIONS);
            context.setContextPath("/");
            context.setResourceBase(System.getProperty("java.io.tmpdir"));
            server.setHandler(context);

            // Passing in the class for the Servlet allows jetty to instantiate an
            // instance of that Servlet and mount it on a given context path.

            // IMPORTANT:
            // This is a raw Servlet, not a Servlet that has been configured
            // through a web.xml @WebServlet annotation, or anything similar.

           // handlerHello.addServletWithMapping(HelloServlet.class, "/hello"); // /hello or /hello/ ???

//            // the same for another servlet
//            ServletHandler handlerBye = new ServletHandler();
//            server.setHandler(handlerBye);
//            handlerBye.addServletWithMapping(ByeServlet.class, "/bye");

            context.addServlet(CalcServlet.class, "/calc");
            context.addServlet(HelloServlet.class, "/hello");
            context.addServlet(ByeServlet.class, "/bye");
            context.addServlet(DefaultServlet.class, "/*");

            // Start things up!
            server.start();

            // The use of server.join() the will make the current thread join and
            // wait until the server is done executing.
            // See
            // http://docs.oracle.com/javase/7/docs/api/java/lang/Thread.html#join()
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
