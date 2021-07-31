package utilidades;

import modelos.Usuario;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    //los spinner de la app
    public static void spinnerGeneral(String nombreSpinner) throws InterruptedException {
        System.out.print(nombreSpinner + " ");
        for (int i = 0; i < 3; i++) {
            System.out.print(".");
            TimeUnit.SECONDS.sleep(1);
        }
        saltoLinea(2);
    }

    public static void spinnerRealizandoCambio() throws InterruptedException {
        System.out.print("Realizando cambio ");
        for (int i = 0; i < 1; i++) {
            System.out.print(".");
            TimeUnit.SECONDS.sleep(1);
        }
        saltoLinea(2);
    }

    public static void spinnerCargando() throws InterruptedException {
        System.out.print("Cargando ");
        for (int i = 0; i < 2; i++) {
            System.out.print(".");
            TimeUnit.SECONDS.sleep(1);
        }
        saltoLinea(2);
    }

    public static void spinnerMenuPrincipal() throws InterruptedException {
        System.out.print("Regresando al menú ");
        for (int i = 0; i < 3; i++) {
            System.out.print(".");
            TimeUnit.SECONDS.sleep(1);
        }
        saltoLinea(2);
    }

    public static void spinnerCargandoVenta() throws InterruptedException {
        System.out.print("Añadiendo artículo en venta");
        for (int i = 0; i < 3; i++) {
            System.out.print(".");
            TimeUnit.SECONDS.sleep(1);
        }
        saltoLinea(2);
    }

    public static void spinnerAgregandoArticulo() throws InterruptedException {
        System.out.print("Creando producto");
        for (int i = 0; i < 3; i++) {
            System.out.print(".");
            TimeUnit.SECONDS.sleep(1);
        }
        saltoLinea(2);
    }

    public static void spinnerSolicitudArticulo() throws InterruptedException {
        System.out.println("Solicitando artículo");
        System.out.print("Procesando ");
        for (int i = 0; i < 3; i++) {
            System.out.print(".");
            TimeUnit.SECONDS.sleep(1);
        }
        saltoLinea(2);
    }

    public static void spinnerQuitandoArticulo() throws InterruptedException {
        System.out.print("Quitando producto ");
        for (int i = 0; i < 3; i++) {
            System.out.print(".");
            TimeUnit.SECONDS.sleep(1);
        }
        saltoLinea(2);
    }

    public static void pulseParaContinuarConsola() {
        Scanner s = new Scanner(System.in);
        System.out.println("Pulse una tecla + Enter para continuar ......");
        s.nextLine();
    }

    public static void saltoLinea(int numSaltos) {

        for (int i = 0; i < numSaltos; ++i) System.out.println();

    }

    public static String valorInvitado(String invitado) {
        int pos1 = 0, pos2 = 0;
        for (int i = 0; i < invitado.length(); i++) {
            if (invitado.charAt(i) == ':'){//clave
                if (pos1 == 0) pos1 = i;
            }

            if (invitado.charAt(i) == '.'){//valor
                if (pos2 == 0) pos2 = i;
            }
        }
        return invitado.substring(pos1 + 1, pos2);
    }

    private static String cadenaCaseInsensitive(String term) {
        StringBuffer strbf = new StringBuffer();
        Matcher match = Pattern.compile("([a-z])([a-z]*)", Pattern.CASE_INSENSITIVE).matcher(term);
        while(match.find())
        {
            match.appendReplacement(strbf, match.group(1).toUpperCase() + match.group(2).toLowerCase());
        }
        return String.valueOf(match.appendTail(strbf));
    }

    public static String getNombreCompleto(Usuario user) {
        String term = user.getNombre() +  " " + user.getApellido();
        return Utils.cadenaCaseInsensitive(term);
    }
}
