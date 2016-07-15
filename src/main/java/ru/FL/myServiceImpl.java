package ru.FL;

import javax.jws.WebService;

/**
 * Created by F4KEFLY on 15.07.2016.
 * Description: реализация интерфейса.
 */
@WebService(endpointInterface = "ru.FL.myService")
public class myServiceImpl implements myService
{
    @Override
    public int[] getArray(int n)
    {
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = (int) (1 + Math.random() * 100);
        }
        return array;
    }
}
