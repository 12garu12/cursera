package discos;

/***
 * Disco digital para su uso por una compañia de
 * "streaming". Se usa como ejemplo en algunos
 * aspectos introductorios a java.
 *
 * @author Elisa viso.
 * @version 1.0 01/03/17.
 * */

public interface ServiciosDisco {

    //METODOS DE CONSULTA O ACCESO

    /**
     * Proporsiona el numero de transmisiones activas.
     *
     * @return numero de transmisiones activas.
     */
    public int getActivas();

    /***
     * Proporsiona el anio que fue grabado el disco.
     *
     * @return fecha de grabacion.
     */
    public int getANHO();

    /***
     * Proporciona el nombre que tenga asociado el disco. Puede se el
     * musico si se trata de un CD o la pelicula si se trata de un DVD
     * o Blueray.
     *
     * @return nombre del musico si se trata de un CD o de una pelicual
     *      si se trata de un DVD o Blueray.
     */
    public String getNOMBRE();

    /***
     * Proporciona el numero de transmisiones permitidas.
     *
     * @return numero de transmisiones permitidas.
     */
    public int getPermitidas();

    /***
     * Proporciona el tipo del disco.
     *
     * @return el tipo de disco entre 1 y 3.
     **/
    public short getTIPO_DISCO();

    //METODOS MUTANTES O DE ACTUALIZACION

    /***
     * Modifica el valor de transmisiones activas.
     *
     * @param newActivas valor que se desea establecer.
     */
    public void setActivas(int newActivas);

    /***
     * Modifica el valor de transmisiones permitidas.
     *
     * @param permisos valor que se desea establecer.
     */
    public void setPermitidas(int permisos);

    //METODOS DE IMPLEMENTACION

    /***
     * Duplica a este disco, construyendo otro objeto con los mismos
     * valores, pero con identidad distinta.
     *
     * @return un nuevo disco identico al que se le pide.
     */
    public ServiciosDisco copiaDisco();

    /***
     * Otorga una transmision, contestando con la fecha y hora en que
     * la esta dando. si no la puede dar, responde negativamente.
     *
     * Actualiza el numero de transmisiones activas.
     *
     * @return un mensaje el cual dice si pudo o no otorgar la transmision.
     */
    public String daTransmision();


    /***
     * Muestra de forma estetica el contenido de este disco.
     *
     * @param encabezado para encabezar lo que se imprima.
     *
     * @return una cadena con la informacion y que contiene saltos de linea.
     */
    public String muestraDisco(String encabezado);

    /***
     * Actualiza el numero de transmisiones activas.
     *
     * @return si pudo (true) o no(false) terminar una transmision.
     */
    public boolean terminaTransmision();

    /***
     * Proporciona una cadena con los distintos campos ocupando un
     *  lugar definido.
     * @return la informacion del disco linealizada en forma de
     *      cadena, todos los discos con la misma informacion.
     */
    public String toString();
}
