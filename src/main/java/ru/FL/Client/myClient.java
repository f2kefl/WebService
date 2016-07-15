package ru.FL.Client;

import ru.FL.Service.myService;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

/**
 * Created by F4KEFLY on 15.07.2016.
 * Description: класс-клиент, осуществляет подключение к сервису и БД, запускает нужный веб-метод и логирование.
 */
public class myClient
{
    private static final int N = 100;

    public static void main(String[] args) throws MalformedURLException
    {
        URL url = new URL("http://localhost:8080/getArray?wsdl");
        QName qName = new QName("http://Service.FL.ru/", "myServiceImplService");
        Service service = Service.create(url, qName);
        myService ms = service.getPort(myService.class);

        System.out.println(Arrays.toString(ms.getArray(N)));
    }
}
