package discos;

import java.net.Socket;
import java.sql.DriverPropertyInfo;

public class Usuario {

    public static void main(String[] args) {

//        String saludo = "Bienvenido al sistema de striming";
//        System.out.println(saludo);
//
////        Disco elMio = new Disco();
////        elMio.setPermitidas(15);
//        Disco elMio = new Disco((short) 2, "Ahora los ves, ahora no", 2014, 10);
//        elMio.setPermitidas(15);
//
//        Disco elTuyo = new Disco((short) 2, "Descifrando Enigma", 2015, 30);
//
//        String mio = elMio.muestraDisco("Se trata del disco elMio");
//        System.out.println(mio);
//
//        System.out.println("elMio usando toString ():");
//        System.out.println("*" + elMio + "*");
//
//        elTuyo.setActivas(elTuyo.getActivas() + 5);
//        String tuyo = "El contenido de elTuyo con toString() : \n" + elTuyo;
//        System.out.println(tuyo);
//
//        System.out.println();
//        elTuyo.setPermitidas(50);
//        elTuyo.setActivas(49);
//        System.out.println("objeto.elTuyo: \n"
//                + elTuyo.daTransmision());
//        System.out.println();
//        System.out.println("objeto.elTuyo: \n"
//                + elTuyo.daTransmision());
//        System.out.println();
//        System.out.println("objeto elTuyo:\n"
//                + (elTuyo.terminaTransmision()
//                ?"Termino en 'elTuyo'"
//                : "No habia nada que terminar"));
//        System.out.println();
//        System.out.println("objeto elTuyo:\n" + elTuyo.daTransmision());
//        System.out.println();
//        Disco elNuestro = (Disco)(elMio.copiaDisco());
//        System.out.println(elMio.daTransmision());
//        System.out.println("Disco 'elMio':\n" + elMio);
//        System.out.println();
//        System.out.println("Disco 'elNuestro':\n" + elNuestro);
//        System.out.println();
//        System.out.println("Hasta luego");

        System.out.println("Empezamos con el programa");
        Disco firstDisc = new Disco((short) 3, "Avengers", 2012, 5);

        String primerDisco = firstDisc.muestraDisco("Es el primer disco");
        System.out.println(primerDisco);

        System.out.println("elMio usando toString ():");
        System.out.println("*" + firstDisc + "*");

        firstDisc.setActivas(4);
        System.out.println("*" + firstDisc + "*");
        System.out.println(firstDisc.daTransmision());
        System.out.println("Quedan transmisiones: " + firstDisc.terminaTransmision());

        Disco copyFirstDisc = (Disco) firstDisc.copiaDisco();
        System.out.println(copyFirstDisc);
        System.out.println(copyFirstDisc.muestraDisco("Copia del disco"));
        System.out.println("Hasta luego!");
    }
}
