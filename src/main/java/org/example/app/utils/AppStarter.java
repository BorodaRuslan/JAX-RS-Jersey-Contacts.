package org.example.app.utils;

import jakarta.ws.rs.core.UriBuilder;
import org.glassfish.jersey.netty.httpserver.NettyHttpContainerProvider;
import org.glassfish.jersey.server.ResourceConfig;

import java.net.MalformedURLException;
import java.net.URI;

public class AppStarter {

    public static void runApp() throws MalformedURLException {

        // Создаем URI с использованием URI_TEMPLATE и устанавливаем порт из константы PORT
        URI baseUri = UriBuilder.fromUri(Constant.URI_TEMPLATE)
                .port(Constant.PORT)
                .build();
        // Создаем конфигурацию Jersey и указываем пакеты, которые нужно сканировать для ресурсов.
        ResourceConfig resourceConfig = new ResourceConfig().packages(Constant.PACKS);

        // Создаем HTTP-сервер с использованием NettyHttpContainerProvider
        // и настраиваем его на указанный baseUri
        // Последний аргумент (false) указывает, что сервер не будет автоматически запущен.
        NettyHttpContainerProvider.createServer(baseUri, resourceConfig, false);

        // Выводим сообщение о запуске сервера на указанном URI
        System.out.println("Application running: " + baseUri.toURL().toExternalForm());
    }


}
