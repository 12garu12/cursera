package discos;

import javax.swing.*;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class Disco implements ServiciosDisco {

    // Atributos de Clase
    private static final int MAX_PERMITIDAS = 50;
    public static final short CD = 1;
    public static final short DVD = 2;
    public static final short BR = 3;
    private static final int PRIMER_ANHO = 1900;
    private static final int ULT_ANHO = 2020;
    public static int LUG_TD = 1,
            LUG_NOMBRE = 40,
            LUG_ANHO = 4,
            LUG_PERMITIDAS = 4,
            LUG_ACTIVAS = 4;
    public final static String ESPACIOS = "          " + "                        ";
    public static final String N_DIAS = "         "
            + "domingo  "
            + "lunes    "
            + "martes   "
            + "miercoles"
            + "jueves   "
            + "viernes  "
            + "sabado   ";
    public static final int TAM_DIA = 9;
    // Atributos de objetos
    private final int ANHO;
    private final String NOMBRE;
    private final short TIPO_DISCO;
    // Variables de objeto
    private int activas;
    private int permitidas;


    // Constructores
    // Constructor sin parametros que asigna valores a los atributos por entrada de teclado
    public Disco() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Que deseas grabar: (1) CD, (2) DVD, (3) BlueRay");
        System.out.print("Elige tipo de disco (1,2,3)," +
                " terminando con un [Enter]:--> ");
        this.TIPO_DISCO = (short) checaEntero(CD, sc.nextShort(), BR);
        sc.nextLine(); // cuando en una entrada por teclado consume la entrada hasta que encuentra un enter. con esto se limpia la entrada y desechas todo lo tecleado despues de un short incluyendo el enter.
        System.out.print("Ahora dame el nombre " + (this.TIPO_DISCO == CD ? "el nombre del cantante"
                : (this.TIPO_DISCO == DVD ? "de la pelicula" : "el nombre de la serie")) + "--> ");
        this.NOMBRE = checaCadena(sc.nextLine());
        System.out.print("Ahora toca el año en que fue grabado " + " (entre " + PRIMER_ANHO + " y " + ULT_ANHO +
                ") terminando con un [Enter]--> ");
        this.ANHO = checaEntero(PRIMER_ANHO, sc.nextInt(), ULT_ANHO);
        sc.nextLine();
        System.out.print("Dame ahora el numero de transmisiones permitidas \n(entre 1 y " + MAX_PERMITIDAS + ")"
                + " terminando con un [Enter]--> ");
        this.permitidas = checaEntero(1, sc.nextInt(), MAX_PERMITIDAS);
        sc.nextLine();
        System.out.println("Gracias");
//        sc.close();
    }

    // Constructor con 3 parametros
    public Disco(short tipo, String nombre, int fecha) {
        this.TIPO_DISCO = (short) checaEntero(CD, tipo, BR);
        this.NOMBRE = checaCadena(nombre).trim();
        this.ANHO = checaEntero(PRIMER_ANHO, fecha, ULT_ANHO);
    }

    // Constructor con 4 parametros
    public Disco(short tipo, String nombre, int fecha, int permitidas) {
        this.TIPO_DISCO = (short) checaEntero(CD, tipo, BR);
        this.NOMBRE = checaCadena(nombre).trim();
        this.ANHO = checaEntero(PRIMER_ANHO, fecha, ULT_ANHO);
        this.permitidas = checaEntero(0, permitidas, MAX_PERMITIDAS);
    }


    //METODOS DE CONSULTA O ACCESO

    /***
     * Proporsiona el numero de transmisiones activas.
     *
     * @return numero de transmisiones activas.
     */
    public int getActivas() {
        return activas;
    }

    /***
     * Proporsiona el año en que fue grabado el disco.
     *
     * @return fecha de grabacion.
     */
    public int getANHO() {
        return ANHO;
    }

    /***
     * Proporciona el nombre que tenga asociado el disco. Puede se el
     * musico si se trata de un CD o la pelicula si se trata de un DVD
     * o Blueray.
     *
     * @return nombre del musico si se trata de un CD o de una pelicula
     *      si se trata de un DVD y de la serie si se trata de un Bluray.
     */
    public String getNOMBRE() {
        return NOMBRE;
    }

    /***
     * Proporciona el numero de transmisiones permitidas.
     *
     * @return numero de transmisiones permitidas.
     */
    public int getPermitidas() {
        return permitidas;
    }

    /***
     * Proporciona el tipo del disco.
     *
     * @return el tipo de disco entre 1 y 3.
     **/
    public short getTIPO_DISCO() {
        return TIPO_DISCO;
    }


    //METODOS MUTANTES O DE ACTUALIZACION

    /***
     * Modifica el valor de transmisiones activas.
     *
     * @param newActivas valor que se desea establecer.
     */
    public void setActivas(int newActivas) {
        this.activas = checaEntero(1, newActivas, permitidas);
    }

    /***
     * Modifica el valor de transmisiones permitidas.
     *
     * @param permisos valor que se desea establecer.
     */
    public void setPermitidas(int permisos) {
        this.permitidas = checaEntero(1, permisos, MAX_PERMITIDAS);
    }


    //METODOS DE IMPLEMENTACION

    /***
     * Duplica a este disco, construyendo otro objeto con los mismos
     * valores, pero con identidad distinta.
     *
     * @return un nuevo disco identico al que se le pide, pero con cero transmisiones activas.
     */
    public ServiciosDisco copiaDisco() {
        return new Disco(this.TIPO_DISCO, this.NOMBRE, this.ANHO, this.permitidas);
    }

    /***
     * Otorga una transmision, contestando con la fecha y hora en que
     * la esta dando. si no la puede dar, responde negativamente.
     * Actualiza el numero de transmisiones activas.
     *
     * @return Un mensaje ciciendo la hora y fecha en caso de haber
     * tenido transmisiones disponibles o, de lo contrario, qu eno pudo otorgar la transmision.
     */
    public String daTransmision() {
        GregorianCalendar miCal = new GregorianCalendar(); /* Al crearse un objeto usando este encabezado se llenan
        automaticamente los campos para fecha y hora y esta clase hereda de la clase calendar muchas variables estaticas
        que van a indicar en donde se guarda el Año, Dia, Dia de la semana, Mes , Hora, Minutos, segundos y milisegundos
        tiene mucha informacion adicional que por el momento no se va ha utilizar.  */
        boolean siHay = activas < permitidas; //Empiezas preguntando si hay transmisiones disponibles.
        activas += siHay ? 1 : 0; // si las hay sumas 1 a la variable activas.
        return siHay ? "Transmision a las " + daHora(miCal) + "del" + daFecha(miCal)
                : "No hay transmisiones disponibles";
    }

    /***
     * Otorga una transmision, contestando con la fecha y hora en que
     * la esta dando. si no la puede dar, responde negativamente.
     * Actualiza el numero de transmisiones activas.
     *
     * @param miCal fecha y hora en la que se esta pidiendo la transmision.
     * @return Un mensaje ciciendo la hora y fecha en caso de haber
     * tenido transmisiones disponibles o, de lo contrario, qu eno pudo otorgar la transmision.
     */
    public String daTransmision(GregorianCalendar miCal) {
        boolean siHay = activas < permitidas; //Empiezas preguntando si hay transmisiones disponibles.
        activas += siHay ? 1 : 0; // si las hay sumas 1 a la variable activas.
        return siHay ? "Transmision a las " + daHora(miCal) + "del " + daFecha(miCal)
                : "No hay transmisiones disponibles";
    }

    // Metodo para la hora con la clase GregorianCalendar de java.util.
    public static String daHora(GregorianCalendar cal) { // Se le pasa como argumento un objeto de la clase GregorianCalendar
        int hora = cal.get(cal.HOUR);
        int minutos = cal.get(cal.MINUTE);
        int segundos = cal.get(cal.SECOND);
        String ampm = cal.get(cal.AM_PM) == cal.AM ? "AM " : "PM ";
        hora = (hora % 12 == 0) && (cal.get(cal.AM_PM) == cal.PM) ? 12 : hora;
        return "" + hora + ":" + (minutos < 10 ? 0 + minutos : minutos) +
                ":" + (segundos < 10 ? 0 + segundos : segundos) +
                " " + ampm + " ";
    }

    public static String daFecha(GregorianCalendar cal) {
        int dia = cal.get(cal.DAY_OF_MONTH);
        int mes = cal.get(cal.MONTH) + 1;
        int anho = cal.get(cal.YEAR);
        int dayWeek = cal.get(cal.DAY_OF_WEEK);
        String diaSemana = nombreDia(cal.get(cal.DAY_OF_WEEK));
        return "" + diaSemana + " " + dia + "/" + mes + "/" + anho;
    }

    public static String nombreDia(int numDia) {
        int desde = numDia * TAM_DIA;
        int hasta = desde + TAM_DIA;
        return N_DIAS.substring(desde, hasta).trim();
    }

    /***
     * Muestra de forma estetica el contenido de este disco.
     *
     * @param encabezado para encabezar lo que se imprima.
     *
     * @return una cadena con la informacion y que contiene saltos de linea.
     */
    public String muestraDisco(String encabezado) {
        String salida = checaCadena(encabezado)
                + "\n===================================================\n";
        salida += "Tipo de disco: " + (TIPO_DISCO == CD ? "CD" : TIPO_DISCO == DVD ? "DVD" : "Blueray") + "\n";
        salida += "Nombre de " + (TIPO_DISCO == CD ? "el cantante" : TIPO_DISCO == DVD ? "la pelicula"
                : "la serie") + ": " + NOMBRE.trim() + "\n";
        salida += "Transmisiones permitidas: " + permitidas + "\n";
        salida += "Transmisiones activas: " + activas + "\n";
        return salida;
    }

    /***
     * Actualiza el numero de transmisiones activas, decrementa el numero de transmisiones activas si es que las hay.
     *
     * @return si pudo (true) o no(false) terminar una transmision, si pudo o no terminar la transmision. Podria ho
     * haberla terminado si no hay transmision que terminar.
     */
    public boolean terminaTransmision() {
        boolean hayActivas = this.activas > 0;
        activas -= hayActivas ? 1 : 0;
        return hayActivas;
    }

    /***
     * Proporciona una cadena con los distintos campos ocupando un
     *  lugar definido.
     * @return la informacion del disco linealizada en forma de
     *      cadena, todos los discos con la misma informacion en las mismas posiciones.
     */
    public String toString() {
        String salida = editaNum(TIPO_DISCO, LUG_TD) + " "
                + editaCad(NOMBRE, LUG_NOMBRE)
                + editaNum(ANHO, LUG_ANHO)
                + editaNum(permitidas, LUG_PERMITIDAS)
                + editaNum(activas, LUG_ACTIVAS);
        return salida;
    }

    //Metodos auxiliares

    /***
     * Tiene las expresiones condiciones si un argumento entero esta en rango
     *
     * @param limiteInferior
     * @param valorArgumento
     * @param limeteSuperior
     * @return
     */
    public static final int checaEntero(int limiteInferior, int valorArgumento, int limeteSuperior) {
        return (valorArgumento < limiteInferior) ? limiteInferior :
                ((valorArgumento > limeteSuperior) ? limeteSuperior : valorArgumento);
    }

    /***
     *
     * @param cadena
     * @return
     */
    public static String checaCadena(String cadena) {
        return cadena == null || cadena.length() == 0 ? "No identificado" : cadena;
    }


    private short checaTipo(short tipo, short limI, short limS) {
        return tipo < limI ? limI : (tipo > limS ? limS : tipo);
    }


    private int checaFecha(int fecha, int primerAnho, int ultimoAnho) {
        return fecha < primerAnho ? primerAnho : (fecha > ultimoAnho ? ultimoAnho : fecha);
    }

    /***
     * Acomoda un numero para que tenga cierto numero dee espacios a la izquierda.
     *
     * @param valor
     * @param lugares a ocupar
     * @return
     */
    // Metodo de clase para editar un numero a String.
    public static String editaNum(int valor, int lugares) {
        String conEspacios = ESPACIOS + valor; // Agregamos espacios por la izquierda.
        int longitudConEspacios = conEspacios.length();
        String cadena = conEspacios.substring(longitudConEspacios - lugares);
        return cadena; // Tomamos lo que incluye al numero original que se encuntra al final de la cadena.
    }

    /***
     * Acomoda una Cadena para que tenga cierto numerode espacios a la izquierda.
     * @param sValor
     * @param lugares lugares a ocupar.
     * @return
     */

    // Metodo de clase para editar un String
    public static String editaCad(String sValor, int lugares) {
        String conEspacios = sValor + ESPACIOS;
        String subCadena = conEspacios.substring(0, lugares);
        return subCadena;
    }

    public static void main(String[] args) {
        GregorianCalendar obj = new GregorianCalendar();
        System.out.println("Hora: " + daHora(obj) + "\nFecha: " + daFecha(obj));

        Disco elMio = new Disco(DVD, "Ahora los ves, ahora  no", 2013, 2);
        System.out.println(elMio.muestraDisco("elMio"));
        System.out.println(elMio);
        System.out.println(elMio.daTransmision());
        System.out.println(elMio.daTransmision());
        System.out.println(elMio.daTransmision());
        System.out.println(obj.JANUARY);
    }

    public String daTipo(int tipo) {
        switch (tipo) {
            case CD:
                return "Cancion    ";
            case DVD:
                return "Pelicula   ";
            case BR:
                return "Serie de TV";
            default:
                return "Desconocido";
        }
    }
}
