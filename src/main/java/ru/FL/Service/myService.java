package ru.FL.Service;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 * Created by F4KEFLY on 15.07.2016.
 * Description: интерфейс для веб сервиса
 */

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface myService
{
    @WebMethod
    int[] getArray(int n);
}
