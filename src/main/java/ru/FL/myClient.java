package ru.FL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;

/**
 * Created by F4KEFLY on 15.07.2016.
 * Description: класс-клиент, осуществл€ет подключение к сервису и Ѕƒ, запускает нужный веб-метод и логирование.
 */
public class myClient
{
    private static final int N = 20;
    private static final String INSERT = "INSERT INTO ws_log.ws_table_log VALUES(?,?)";

    public static void main(String[] args) throws MalformedURLException
    {
        //по ссылке получаем автогенерируемые данные о сервисе
        URL url = new URL("http://localhost:8080/getArray?wsdl");

        // из тега definitions берем данные об исполн€емом классе
        QName qName = new QName("http://FL.ru/", "myServiceImplService");

        //переходим в тег service
        Service service = Service.create(url, qName);

        //получаем ссылку на наш удаленный объект
        myService ms = service.getPort(myService.class);

        System.out.println(Arrays.toString(ms.getArray(N)));

        DBConnector connector = new DBConnector();

        try {
            // готовим данные дл€ оправки в Ѕƒ-лог
            PreparedStatement preparedStatement = connector.getConnection().prepareStatement(INSERT);
            preparedStatement.setInt(1, N);
            preparedStatement.setString(2, Arrays.toString(ms.getArray(N)));
            // отправл€ем данные
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                // в случае успеха прерываем соединение и закрываем клиент
                connector.getConnection().close();
                System.out.println("log has been added to MySQL BD");
                System.exit(0);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }
}
