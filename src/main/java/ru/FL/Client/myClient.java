package ru.FL.Client;

import ru.FL.Database.DBConnector;
import ru.FL.Service.myService;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;

/**
 * Created by F4KEFLY on 15.07.2016.
 * Description: класс-клиент, осуществляет подключение к сервису и БД, запускает нужный веб-метод и логирование.
 */
public class myClient
{
    private static final int N = 100;
    private static final String INSERT = "INSERT INTO ws_log.ws_table_log VALUES(?,?)";

    public static void main(String[] args) throws MalformedURLException
    {
        URL url = new URL("http://localhost:8080/getArray?wsdl");
        QName qName = new QName("http://Service.FL.ru/", "myServiceImplService");
        Service service = Service.create(url, qName);
        myService ms = service.getPort(myService.class);

        System.out.println(Arrays.toString(ms.getArray(N)));

        DBConnector connector = new DBConnector();

        try {
            PreparedStatement preparedStatement = connector.getConnection().prepareStatement(INSERT);
            preparedStatement.setInt(1, N);
            preparedStatement.setString(2, Arrays.toString(ms.getArray(N)));
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connector.getConnection().close();
                System.exit(0);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }
}
