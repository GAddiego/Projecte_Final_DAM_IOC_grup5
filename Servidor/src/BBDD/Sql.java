
package BBDD;

import objectes.Configuracio;


/**
 *
 * @author aleix
 */
public interface Sql {
    Configuracio conf = new Configuracio();

    static String connexio = conf.getDada("bbdd.url");
    static String user = conf.getDada("bbdd.usuari");
    static String pasw = conf.getDada("bbdd.contrasenya");
}

