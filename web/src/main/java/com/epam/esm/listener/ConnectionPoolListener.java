package com.epam.esm.listener;

import com.epam.esm.config.WebSpringConfig;
import com.epam.esm.pool.ConnectionPool;
import com.epam.esm.util.PropertyReaderUtil;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.IOException;

@WebListener
public class ConnectionPoolListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            PropertyReaderUtil.loadProperties();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ConnectionPool.getInstance().destroyPool();
    }
}