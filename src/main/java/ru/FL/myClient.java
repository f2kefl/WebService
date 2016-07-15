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
 * Description: �����-������, ������������ ����������� � ������� � ��, ��������� ������ ���-����� � �����������.
 */
public class myClient
{
    private static final int N = 20;
    private static final String INSERT = "INSERT INTO ws_log.ws_table_log VALUES(?,?)";

    public static void main(String[] args) throws MalformedURLException
    {
        //�� ������ �������� ���������������� ������ � �������
        URL url = new URL("http://localhost:8080/getArray?wsdl");

        // �� ���� definitions ����� ������ �� ����������� ������
        QName qName = new QName("http://FL.ru/", "myServiceImplService");

        //��������� � ��� service
        Service service = Service.create(url, qName);

        //�������� ������ �� ��� ��������� ������
        myService ms = service.getPort(myService.class);

        System.out.println(Arrays.toString(ms.getArray(N)));

        DBConnector connector = new DBConnector();

        try {
            // ������� ������ ��� ������� � ��-���
            PreparedStatement preparedStatement = connector.getConnection().prepareStatement(INSERT);
            preparedStatement.setInt(1, N);
            preparedStatement.setString(2, Arrays.toString(ms.getArray(N)));
            // ���������� ������
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                // � ������ ������ ��������� ���������� � ��������� ������
                connector.getConnection().close();
                System.out.println("log has been added to MySQL BD");
                System.exit(0);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }
}
