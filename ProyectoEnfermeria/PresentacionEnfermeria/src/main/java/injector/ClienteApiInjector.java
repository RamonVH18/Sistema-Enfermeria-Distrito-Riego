package injector;

import clienteApi.ClienteApiTemporal;
import contratos.IClienteApi;

/**
 *
 * @author Leonardo Flores Leyva
 */
public class ClienteApiInjector {
    public static IClienteApi toClienteApi(){
        return new ClienteApiTemporal();
    }
}