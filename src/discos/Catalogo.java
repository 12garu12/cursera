package discos;

import java.util.GregorianCalendar;
import java.util.Scanner;


/***
 * Simula el uso de un catalogo de discos.
 * En cada sesion puede construir su base de discos,
 * agregar discos, consultar el catalogo, iniciar
 * transmisiones y terminar transmisiones, registrando
 * todas las actividades en el catalogo.
 * @author Elisa Viso.
 * @version 2020
 */
public class Catalogo {
    //    Numero maximo de discos en cualquier catalogo.
    public final static int MAX_DISCOS = 50;
    public static final String[] MENU_CATALOGO = {// Opciones
            "Salir",                                   // 0
            "Agregar disco",                           // 1
            "Mostrar discos",                          // 2
            "Mostrar discos activos",                  // 3
            "Pedir transmision",                       // 4
            "Terminar transmision",                    // 5
            "Mostrar disco",                           // 6
            "Mostrar historico de un disco",           // 7
            "Mostrar historico de todos los discos"    // 8
    };

    /*  Constantes simbolicas de la clase   */
    /**
     * Accion para salir del menu.
     */
    public static final int SALIR = 0;
    /**
     * Accion para agregar un disco.
     */
    public static final int AGREGA_DISCO = 1;
    /**
     * Accion para mostrar el catalogo
     */
    public static final int MSTRA_DISCOS = 2;
    /**
     * Accion para mostrar discos activos.
     */
    public static final int MSTRA_DSCS_ACTVS = 3;
    /**
     * Accion para pedir una transmision.
     */
    public static final int PIDE_TRNSMSN = 4;
    /**
     * Accion para terminar una transmision.
     */
    public static final int TRMINA_TRNSMSN = 5;
    /**
     * Accion para mostrar un disco.
     */
    public static final int MSTRA_DISCO = 6;
    /**
     * Accion para mostrar historico de un disco.
     */
    public static final int MSTRA_HIST = 7;
    /**
     * Accion para mostrar historico de todos los discos.
     */
    public static final int MSTRA_HISTRS = 8;

    private Disco[] catalogo;  // catalogo de discos.
    private int numDiscos = 0; // numero de discos en el catalogo.
    private GregorianCalendar[][] fechas; // Un renglon por cada disco, una columna por transmision.
    // cada renglon tiene un numero de columnas dado por el disco en ese renglon.
    // El numero de fechas registradas esta dado por el atributo activas del disco.

    private GregorianCalendar[][][] historico; // Pareja de renglones por cada disco.
                                              /* PRIMERA DIMENSION va a estar dado por el tamaño del CATALOGO por lo que
                                              tendras que esperar a saber este tamaño, que se define en los constructores.
                                              SEGUNDA DIMENSION tiene tamaño 2, pero no se puede dar hasta que la primera
                                              esté definida.
                                              TERCERA DIMENSION va a ser dos veces el número de transmisisones
                                              simultaneas permitidas. Esta última dimension la
                                              definiras al construir el disco correspondiente.*/

    private int[] numHist;                   /* Da para cada disco el numero de transmisiones iniciadas y terminadas
                                                para ese disco.
                                             */

//    PRIMER CONSTRUCTOR

    /**
     * Construye un catalogo. Da el maximo numero de posibles
     * registros para discos y anota que no tiene ningun disco registrado.
     * Se construiran los arreglos correspondientes a historico y fechas
     * pero unicamente con su numero de renglones (o tablas). al registrar
     * cada disco, se contruiran los arreglos correspondientes a sus fechas
     * y su historico.
     */
    public Catalogo() {
        catalogo = new Disco[MAX_DISCOS];
        numDiscos = 0; // No se lleva ningun disco registrado
        /* El numero de posiciones disponibles para registrar transmisiones activas
         * en el arreglo fechas estara dado por el numero de transmisiones permitidas
         * del disco en esa posicion, que no se sabra hasta que se construya
         *  ese disco. El numero de lugares estara dado por el numero
         * de transmisiones activas del disco en esa posicion.
         * */
        fechas = new GregorianCalendar[catalogo.length][];
        historico = new GregorianCalendar[catalogo.length][2][];
        numHist = new int[catalogo.length]; //Como son objetos, los elementos numericos se inician en (0)
    }

//    SEGUNDO CONSTRUCTOR

    /**
     * Cons truye un catalogo con espacio para un numero
     * definido de discos. Los espacios para cada disco contienen
     * una referencia nula. Se construiran los arreglos correspondientes
     * a historico y fechas pero unicamente con su numero
     * de renglones o tablas. Al registrar a cada disco, se completaran
     * los arreglos correspondientes a fechas e historicos
     *
     * @param numDscs Maximo numero de discos que puede tener el catalogo.
     */
    public Catalogo(int numDscs) {
        int maxDiscos = Disco.checaEntero(1, numDscs, MAX_DISCOS); // Se verifica que el argumento pasado al constructor este entre los rangos.
        catalogo = new Disco[maxDiscos]; // Construccion del catalogo con ese tamaño
        fechas = new GregorianCalendar[catalogo.length][];
        historico = new GregorianCalendar[catalogo.length][2][];
        numHist = new int[catalogo.length];
    }

//    TERCER CONSTRUCTOR

    /**
     * Construye un catalogo con espacio para un numero definido de discos.
     * El catgalogo se inicializa con el contenido del arreglo que recibes
     * como parametro. Inicializas el contador de discos activos relativo
     * al numero de discos ene el parametro. Para aquellos discos
     * que ya estan construidos construyes las columnas del arreglo fechas
     * y del arreglo historico. Si el numero de lugares solicitados es menor
     * que el tamaño del arreglo que recibes como parametro, para la inicilizacion
     * vas a utilizar este ultimo tamaño para los arreglos que corren paralelos.
     *
     * @param numDscs Maximo numero de posiciones.
     * @param nuevos  Arreglo inicial de discos para el catalogo.
     */
    public Catalogo(int numDscs, Disco[] nuevos) {
        int numNvos = nuevos == null ? 0 : nuevos.length;
        numDscs = numDscs < numNvos ? numNvos : numDscs;
        numDscs = Disco.checaEntero(1, numDscs, MAX_DISCOS);
        /* El numero de discos a copiar en el catalogo definitivo
         * es el minimo entre el tamaño final del arreglo
         * y el numero de discos nuevos.  |*/
        numNvos = numNvos > numDscs ? numDscs : numNvos;
        catalogo = new Disco[numDscs];
        numDiscos = 0;
        fechas = new GregorianCalendar[catalogo.length][];
        historico = new GregorianCalendar[catalogo.length][2][];
        numHist = new int[catalogo.length];
        /* Copias ahora los discos nuevos al catalogo en los primeros lugares del catalogo */
        for (int i = 0; i < numNvos; i++) {
            if (nuevos[i] == null) {
                continue;
            }
            catalogo[numDiscos] = nuevos[i];
            int numPrest = catalogo[numDiscos].getPermitidas(); //obtiene en numero de transmisiones permitidas de ese disco
            /* El numero posible de fechas */
            fechas[numDiscos] = new GregorianCalendar[numPrest];
            historico[numDiscos][0] = new GregorianCalendar[2 * numPrest];
            historico[numDiscos][1] = new GregorianCalendar[2 * numPrest];
            numDiscos++;

        }
    }

    // METODOS DE CONSULTA

    /**
     * Regresa el catalogo de discos incluyendo
     * aquellas posiciones que no tienen discos.
     *
     * @return Arreglo con el catalogo de discos.
     */
    public Disco[] getCatalogo() {
        return catalogo;
    }

    /**
     * Regresa el numero de discos registrados en el catalogo.
     *
     * @return El numero de discos registrados en el catalogo.
     */
    public int getNumDiscos() {
        return numDiscos;
    }

    /**
     * Regresa un arreglo de dos dimensiones donde en cada
     * dimension registra las fechas de inicio para cada
     * transmision de un disco dado.
     *
     * @return El arreglo con las fechas de prestamo para cada disco.
     */
    public GregorianCalendar[][] getFechas() {
        return fechas;
    }

    /**
     * Regresa el historico de transmisiones
     * iniciadas y terminadas para cada disco. Hay una
     * pareja de renglones con fechas para cada disco,
     * por lo que el arreglo es de tres dimensiones.
     *
     * @return El historico de todos los discos.
     */
    public GregorianCalendar[][][] getHistorico() {
        return historico;
    }

    /**
     * Regresa el arreglo que contiene, para cada disco,
     * el numero de registros en el arreglo historico.
     *
     * @return El numero de registros en historico.
     */
    public int[] getNumHist() {
        return numHist;
    }

//    METODOS DE ACTUALIZACION

    /**
     * Copia el catalogo en el parametro si no es una referencia nula.
     * Si nuevos es una referencia nula "borramos todos los arreglos asociados.
     * Para cada disco que exite en el catalogo se inicializan los arreglos asociados
     * y se anota cuantos discos hay. No se copian las referencias nulas
     * (ni se cuaentan ) para que no haya huecos en el arreglo.
     *
     * @param nuevos El arreglo con el que se va inicializar el catalogo y toos los
     *               arreglos asociados.
     */
    public void setCatalogo(Disco[] nuevos) {
        int cuantos = nuevos == null ? 0 : nuevos.length;
        if (cuantos == 0) {
            catalogo = null;
            fechas = null;
            historico = null;
            numDiscos = 0;
            numHist = null;
            return;
        }
//       si la referencia no es null.
        catalogo = new Disco[cuantos];
        fechas = new GregorianCalendar[cuantos][];
        historico = new GregorianCalendar[cuantos][2][];
        numHist = new int[cuantos];
//       Se quieren copiar los discos que estan activos
//       El catalogo tiene todas sus referencias en null
        numDiscos = 0;
//        Ciclo for para copiar los elementos del arreglo disco
        for (int i = 0; i < cuantos; i++) {
            if (nuevos[i] == null) continue;
            catalogo[numDiscos] = nuevos[i];
            int numPerm = nuevos[i].getPermitidas();
            catalogo[numDiscos].setActivas(0);
            fechas[numDiscos] = new GregorianCalendar[numPerm];
            historico[numDiscos][0] = new GregorianCalendar[2 * numPerm];
            historico[numDiscos][1] = new GregorianCalendar[2 * numPerm];
            numDiscos++;
        }

    }

    /**
     * Agrega un disco al catalogo si es que hay lugar.
     *
     * @param nuevo El disco a agragar.
     * @return false si el disco es nulo o no hay lugar,
     * o verdadero si se pudo agregar
     */
    public boolean addCatalogo(Disco nuevo) {
        if (nuevo == null || numDiscos >= catalogo.length) {
            return false; // No agrego nada al catalogo
        }
//        llegas aqui nuevo existe y hay lugar
        catalogo[numDiscos] = nuevo;
        int numPerm = nuevo.getPermitidas();
        fechas[numDiscos] = new GregorianCalendar[numPerm];
        historico[numDiscos][0] = new GregorianCalendar[2 * numPerm];
        historico[numDiscos][1] = new GregorianCalendar[2 * numPerm];
        numDiscos++;
        return true;
    }

    /**
     * Registra la transmision del disco a una cierta hora.
     * Avisa si pudo o no dar la transmision.
     *
     * @param cualDisco Indica la posicion en el catalogo del disco.
     * @return verdadero o falso dependiendo si pudo o no dar la transmision.
     */
    public boolean daTransmision(int cualDisco) {
        if (cualDisco >= numDiscos || cualDisco < 0) return false;
        if (catalogo[cualDisco] == null) return false;
        int numDato = catalogo[cualDisco].getActivas();
        if (numDato >= catalogo[cualDisco].getPermitidas()) {
            System.out.println("El disco " + catalogo[cualDisco].getNOMBRE() +
                    "ya no tiene transmisiones disponibles");
            return false;
        }
        GregorianCalendar ahora = new GregorianCalendar();
        fechas[cualDisco][numDato] = ahora;
        System.out.println(catalogo[cualDisco].daTransmision(ahora));
        return true;
    }

    /**
     * Regresa una cadena con el catalogo de discos. Cada disco
     * muestra su contenido en forma compacta.
     *
     * @param encabezado El titulo del listado.
     * @return La cadena con un disco por renglon y la posicion que ocupa.
     */
    public String muestraCatalogo(String encabezado) {
        if (catalogo == null) {
            return encabezado + "\nNo existe el catalogo de discos";
        }
        if (numDiscos <= 0) {
            return encabezado + "\nNo hay discos en el catalogo";
        }
        String cadena = encabezado == null ? "" : encabezado + "\n";
        for (int i = 0; i < numDiscos; i++) {
            cadena += "[" + i + "] "; // posicion que ocupa el disco
            if (catalogo[i] == null) {
                cadena += "no hay disco en esta posicion\n";
                continue;
            }
            cadena += "(" + catalogo[i].daTipo(catalogo[i].getTIPO_DISCO()) + ") "
                    + catalogo[i].getNOMBRE() + "\n"
                    + "\t Num. de transmisiones permitidas: "
                    + catalogo[i].getPermitidas() + "\n"
                    + "\t Num. de transmisiones activas: "
                    + catalogo[i].getActivas() + "\n"
                    + "\n";
        } // for
        return cadena;
    } // fin de mstraCatalogo

    /**
     * Muestra en una lista aquellos discos que tienen
     * transmisiones activas. Identifica al disco por
     * la posicion que ocupa y, para cada disco, identifica
     * a las transmisiones activas por la columna que ocupan
     * en el renglon correspondiente al disco en el arreglo
     * fechas.
     *
     * @param encabezado Para que el listado tenga un
     *                   titulo.
     * @return una lista de los discos que tienen transmisiones
     * activas con las transmisiones identificadas por
     * la posicion que ocupa cada una.
     */
    public String mstraActivos(String encabezado) {
        if (catalogo == null || numDiscos <= 0) {
            return encabezado + "\nNo hay discos en el catalogo";
        }
        String cadena = encabezado == null ? "" : encabezado + "\n";
        for (int i = 0; i < numDiscos; i++) {
            if (catalogo[i] == null) continue;
            int numActivas = catalogo[i].getActivas();
            if (numActivas <= 0) continue;
            cadena += "catalogo [" + i + "]: " + catalogo[i].getNOMBRE() + "\n";
            for (int j = 0; j < numActivas; j++) {
//                la  j da la posicion de fecha en el disco posicion i
                cadena += "[" + j + "] " + daCalendario(fechas[i][j]) + "\n";
            } // fin de fo j
            cadena += "\n";
        } // fin del for i
        return cadena;
    } // fin de mstraActvos

    /**
     * Convierte una fecha de GregorianCalendar en una cadena con la
     * fecha y la hora representada en el parametro.
     *
     * @param fecha un GregorianCalendar que se desea "descifrar".
     * @return una cadena que corresponde al parametro.
     */
    public static String daCalendario(GregorianCalendar fecha) {
        if (fecha == null) return "fecha invalida";
        String laFecha = Disco.daFecha(fecha) + (fecha.get(fecha.HOUR) != 1 ? " a las " :
                "a la ") + Disco.daHora(fecha);
        return laFecha;
    }

    /**
     * Muestra las transmisiones activas de un disco determinado.
     *
     * @param cualDisco posicion del disco elegido en el catalogo.
     * @return cadena con una lista de las transmisiones activas.
     */
    public String mstraActivas(int cualDisco) {
        if (catalogo == null || cualDisco < 0 || cualDisco >= catalogo.length || catalogo[cualDisco] == null) { // Verificar que en el catalogo exista la posicion dada.
            System.out.println("Este disco no existe en el catalogo");
            return null;
        }
        int cuantas = catalogo[cualDisco].getActivas(); // posicion del disco en el catalogo con la posicion del argumento dado para obtener las transmisiones activas.
        if (cuantas <= 0) {
            System.out.println("Este disco no tiene transmisiones activas");
            return null;
        }
        String cadena = catalogo[cualDisco].muestraDisco("Transmisiones activas: ") + "\n";
        for (int i = 0; i < cuantas; i++) {
            if (fechas[cualDisco][i] != null) {
                cadena += "[" + i + "]\t" + daCalendario(fechas[cualDisco][i]) + "\n";
            } else cadena += "fecha no registrada\n";
        }
        return cadena;
    }

    /**
     * Termina una transmision activa y la coloca en el historico de transmisones para ese disco.
     *
     * @param cualDisco La posicion del disco cuya transmision se desea terminar.
     * @param cons      la consola a traves de la cual se hace la comunicacion.
     * @return si pudo (true) o no (false) terminar la transmision.
     */
    public boolean terminaTrans(int cualDisco, Scanner cons) {
        if (cualDisco < 0 || cualDisco >= numDiscos || catalogo[cualDisco] == null) { // Verificacion de la posicion del disco
            System.out.println("El disco " + cualDisco + " no existe");
            return false;
        }
        if (cons == null) { // Verificacion de la consola
            System.out.println("No es una consola valida");
            return false;
        }
        System.out.println("Para el disco\n" +
                catalogo[cualDisco].muestraDisco("Disco numero [" + cualDisco + "]"));
        System.out.println("Tenemos las siguientes transmisiones activas: ");
        String cadena = mstraActivas(cualDisco);
        if (cadena == null) {
            System.out.println("Este disco no tiene transmisiones activas.");
            return false;
        }
        System.out.println(cadena);
        int numActivas = catalogo[cualDisco].getActivas(); // Transmisiones del disco activas
        int cualTrans = pideNum(cons, "Elige el numero de transmision a terminar", 0, numActivas - 1);
        if (cualTrans == -1) { // El numero dado esta fuera de rango.
            System.out.println("Esta transmision no existe");
            return false;
        }
        GregorianCalendar inicio = fechas[cualDisco][cualTrans];
        GregorianCalendar fechaFin = new GregorianCalendar();
        int donde = numHist[cualDisco];
        historico[cualDisco][0][donde] = inicio;
        historico[cualDisco][1][donde] = fechaFin;
        numHist[cualDisco]++;
        System.out.println("Transmision terminada: " + daCalendario(fechaFin));
        if (eliminaCelda(fechas[cualDisco], cualTrans))
            catalogo[cualDisco].terminaTransmision();
        else return false;
        return true;
    }

    /**
     * Muestra el historico de transmisiones que solo incluye
     * las iniciadas que ya fueron terminadas de un disco dado.
     *
     * @param cual Elige al disco que va a mostrar el historico.
     * @return cadena con el historico bien presentado.
     */
    public String mstraHist(int cual) {
        if (cual < 0 || cual >= numDiscos || catalogo[cual] == null) return "Este disco no existe.";
        String cadena = "Historico del disco\t" + catalogo[cual].getNOMBRE() + "\n";
        if (numHist[cual] == 0) {
            cadena += "Este disco no tiene un historico.\n";
            return cadena;
        }
        for (int i = 0; i < numHist[cual]; i++) {
            cadena += "[" + i + "] \tTransmision iniciada: \t" + daCalendario(historico[cual][0][i])
                    + "\n\tTransmisison terminada: \t"
                    + daCalendario(historico[cual][1][i]) + "\n\n";
        }
        return cadena;
    }

    /**
     * Muestra el historico de todos los discos que lo tienen.
     *
     * @return cadena con el historico presentado en un formato.
     */
    public String mstraHistrs() {
        String cadena = "Hisrorco en los discos que lo tienen: \n" +
                "=====================================\n";
        for (int cual = 0; cual < numDiscos; cual++) {
            if (catalogo[cual] != null)
                cadena += mstraHist(cual) + "\n";
        }
        return cadena;
    }

    /**
     * Elimina la celda solicitada, si existe, en el arreglo correspondiente. Despues
     * de llamar  a este metodo, se tienen que hacer los decrementos correspondientes a
     * los arreglos que se esten usando.
     *
     * @param arreglo De una dimension del que se va a eliminar la celda.
     * @param cual    el indice del elemento que se  va a eliminar.
     * @return si pudo (true) o no (false) hacer la eliminacion.
     */
    public static boolean eliminaCelda(Object[] arreglo, int cual) {
        if (arreglo == null) {
            System.out.println("El arreglo no existe.");
            return false;
        }
//        se verifica si la posicion del arreglo es valida
        if (cual < 0 || cual >= arreglo.length) {
            System.out.println("la posicion a eliminar no existe");
            return false;
        }
        for (int i = cual + 1; i < arreglo.length && arreglo[i] != null; i++) {
            arreglo[i - 1] = arreglo[i]; // i vale al menos 1
            arreglo[i] = null;
        }
        return true;
    }

    /**
     * Se comunica con un ususario y le solicita un entero que
     * este en ciertos rangos. Le da un mensaje al usuario
     * indicando lo que debe proporcionar y los limites que
     * debe observar.
     *
     * @param cons   Un Scanner a traves del que se comunica la clase con el usuario.
     * @param msg    El mensaje con el que pide el dato este metodo.
     * @param minVal menor valor aceptado.
     * @param maxVal mayor valor aceptado.
     * @return Un entero si es valido o -1 si el valor dado no es valido.
     */
    public static int pideNum(Scanner cons, String msg, int minVal, int maxVal) {
        int num = -1; // Si no lee nada, tiene valor incorrecto
        System.out.println(msg + "(entre  " + minVal + " y " + maxVal + ") termninando con [Enter]: --> ");
        num = cons.nextInt();
        /*cons.nextLine();*/
        if (num < minVal || num > maxVal) {
            num = -1;
        }
        return num;
    }

    /**
     * Inicia la comunicacion ya sea con el dueño del catalogo o con un cliente. Mediante
     * esta linea de comunicacion se agregan discos, se piden transmisiones y se terminantransmisiones.
     */
    public void conectaCatlgo() {
        Scanner cons = new Scanner(System.in);
        int opcion = 0;   // Para recibir la opcion del usuario
        Disco discoNvo; // Para construir discos nuevos
        Disco elDisco;
        //Saludar al usuario
        System.out.println("Bienvenido al catalogo de discos ");
        do {
            System.out.println("Menu de opciones de trabajo: \n" +
                    "============================");
            for (int i = 0; i < MENU_CATALOGO.length; i++) {
                System.out.println((i < 10 ? " " : "") + "[" + i + "] " + MENU_CATALOGO[i]);

            }
            System.out.println("Elige una opcion (terminando con [Enter]): [0-" +
                    (MENU_CATALOGO.length - 1) + "] --> : ");
//            Pedirle al usuario que elija una opcion
            opcion = pideNum(cons, "", 0, MENU_CATALOGO.length - 1);
            int cualDisco; // Para elegir disco
            int sigDato; // Posicion de la siguiente transmision a registrar en ese disco.
            //Dependiendo de la opcion a elegir el bloque que corresponda
            switch (opcion) {
                case SALIR:
                    System.out.println("Termina la sesion\n¡Hasta Pronto!");
                    break;
                case AGREGA_DISCO:
                    discoNvo = new Disco();
                    if (addCatalogo(discoNvo))
                        System.out.println(discoNvo.getNOMBRE() + " agregado.\n");
                    else
                        System.out.println("Ya no hay lugara para " + discoNvo.getNOMBRE());
                    break;
                case MSTRA_DISCOS:
                    if (catalogo == null || numDiscos <= 0) {
                        System.out.println("No hay discos registrados en el catalogo\n");
                        break;
                    }
                    System.out.println("\nDiscos en el catalogo.");
                    for (int i = 0; i < numDiscos; i++) {
                        System.out.println(catalogo[i].muestraDisco("Disco[" + i + "]:"));
                    }
                    break;

                case MSTRA_DSCS_ACTVS:
                    if (catalogo == null || numDiscos <= 0) {
                        System.out.println("No hay discos registrados en el catalogo.");
                        break;
                    }
                    System.out.println(mstraActivos("Discos Activos\n" +
                            "=============="));
                    break;
                case PIDE_TRNSMSN:
                    System.out.println(muestraCatalogo("\nDiscos disponibles en el catalogo"));
                    cualDisco = pideNum(cons, "Elige el numero del disco, terminando con un [Enter]--> : ", 0, numDiscos - 1);
                    if (cualDisco == -1) {
                        System.out.println("El disco elegido no existe.");
                        break;
                    }
                    sigDato = catalogo[cualDisco].getActivas();
                    if (daTransmision(cualDisco)) {
                        System.out.println("Disco [" + cualDisco + "]: " +
                                catalogo[cualDisco].getNOMBRE().trim() +
                                " transmitiendose  empezando " +
                                daCalendario(fechas[cualDisco][sigDato]) + "\n");
                    }
                    break;
                case TRMINA_TRNSMSN:
//                    primero se localiza la transmision
                    System.out.println("Elige el disco cuya transmision deseas terminar");
                    System.out.println(mstraActivos("Discos con transmisiones activas."));
                    cualDisco = pideNum(cons, "Elige el numero de disco, ", 0, numDiscos - 1);
                    if (cualDisco == -1) {
                        System.out.println("El disco " + cualDisco + " no existe.");
                        break;
                    }
                    if (catalogo[cualDisco].getActivas() <= 0) {
                        System.out.println("El disco " + cualDisco + " no tiene transmisiones activas.");
                        break;
                    }
//                    El disco correcto. Falta dar de baja la transmision.
                    if (terminaTrans(cualDisco, cons))
                        System.out.println("Transmision terminada");
                    else
                        System.out.println("No se elimino la eliminacion.");
                    break;
                case MSTRA_DISCO:
                    System.out.println(muestraCatalogo("Discos disponibles en el catalogo"));
                    cualDisco = pideNum(cons, "Elige el numero de disco, ", 0, numDiscos - 1);
                    if (cualDisco == -1) {
                        System.out.println("El disco elegido no existe.");
                        break;
                    }
                    elDisco = catalogo[cualDisco];
                    if (elDisco == null) {
                        System.out.println("Ese disco no exite.");
                        break;
                    }
                    System.out.println(elDisco.muestraDisco("[" + cualDisco + "] Disco "));
                    break;
                case MSTRA_HIST:
                    System.out.println(muestraCatalogo("Discos disponibles en el catalogo"));
                    cualDisco = pideNum(cons, "Elige el numero de disco, ", 0, numDiscos - 1);
                    if (cualDisco == -1) {
                        System.out.println("El disco elegido no existe.");
                        break;
                    }
                    elDisco = catalogo[cualDisco];
                    if (elDisco == null) {
                        System.out.println("Ese disco no exite.");
                        break;
                    }
//                    El disco es valido
                    if (numHist[cualDisco] == 0) {
                        System.out.println("El disco [" + cualDisco + "] no tine historia.");
                        break;
                    }
                    System.out.println(mstraHist(cualDisco));
                    break;
                case MSTRA_HISTRS:
                    System.out.println(mstraHistrs());
                    break;
                default:
                    System.out.println("Opcion no implementada.");
            }
        } while (opcion != 0);

    }

    public static void main(String[] args) {
        Catalogo elMio = new Catalogo(10, new Disco[]{
                new Disco(Disco.DVD, "Ahora los ves, ahora no", 1999, 5),
                new Disco(Disco.BR, "Billions", 1015, 4),
                new Disco(Disco.BR, "Outlander", 2016, 3),
                new Disco(Disco.CD, "Frank Sinatra", 1992, 2),
                new Disco(Disco.DVD, "Avengers", 2012, 4)
        }); // Construccion del catalogo

        elMio.conectaCatlgo();


    }

}