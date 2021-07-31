package utilidades;

import comunicaciones.Email;
import modelos.Usuario;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import javax.servlet.ServletContext;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FicherosConverter {

    public static String obtenerPropertie(String clave) {
        Properties props = new Properties();
        InputStream inputStream = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("/config/config.properties");
        try {
            props.load(inputStream);
            return props.getProperty(clave);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String obtenerPropertieEmail(String clave) {
        Properties props = new Properties();
        try {
            InputStream inputStream = Thread.currentThread().getContextClassLoader()
                    .getResourceAsStream("/config/email_config.properties");
            props.load(inputStream);
            return props.getProperty(clave);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String obtenerPropertieDataBase(String clave) {
        Properties props = new Properties();
        try {
            InputStream inputStream = Thread.currentThread().getContextClassLoader()
                    .getResourceAsStream("/config/database.properties");
            props.load(inputStream);
            return props.getProperty(clave);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean escribirSesionUsuario(String correo) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
        String hora = sdf.format(Calendar.getInstance().getTime());
        Properties props = new Properties();
        String ruta = obtenerPropertie("rutaSesionUsuario");
        if (ruta != null) {
            try {
                props.load(new FileReader(ruta));
                props.setProperty(correo, hora);
                props.store(new FileWriter(ruta), "Ultima Sesion de Usuarios");
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static String obtenerPropertieSesion(String clave) {
        Properties props = new Properties();
        String ruta = obtenerPropertie("rutaSesionUsuario");
        if (ruta != null) {
            try {
                props.load(new FileReader(ruta));
                return props.getProperty(clave);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }


    public static String leerFichero(String ruta) {
        String linea = "";
        try {
            BufferedReader bf = new BufferedReader(new FileReader(ruta));
            linea = bf.readLine();
            bf.close();
            return linea;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String fecha() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy ; hh:mm:ss");
        return sdf.format(Calendar.getInstance().getTime());
    }

    /**
     * @param tipo es clave que se le da al guardar el registro
     * @param user obtendremos el correo del usuario
     *             Generado con propertie
     */
    public static void registrarLog(String tipo, Usuario user) {
        //Esta es la ruta donde creara el archivo.
        String ruta = obtenerPropertie("rutaLog");
        boolean logActivado = Boolean.parseBoolean(obtenerPropertie("opcionLog"));
        if (ruta != null && logActivado) {
            File f = new File(ruta);
            try {
                //Con el parametro true no se sobreescriben los archivos
                BufferedWriter bw = new BufferedWriter(new FileWriter(f, true));
                bw.write(tipo + "; " + user.getCorreo() + "; " + fecha() + "\n");
                bw.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //generado con propertie
    public static boolean generarExcel(ArrayList<Usuario> listaUsuarios, String correo) {
        //Crear libro de trabajo en blanco
        Workbook workbook = new HSSFWorkbook();
        //Crea hoja nueva
        Sheet sheet = workbook.createSheet("Hoja de datos");
        //Por cada línea se crea un arreglo de objetos (Object[])
        HashMap<String, Object[]> datos = new HashMap<>();
        datos.put("1", new Object[]{"Id", "Nombre", "Correo", "Contraseña", "Token"});
        int cont = 2;
        int id = 0;
        for (Usuario u : listaUsuarios) {
            datos.put(String.valueOf(cont++), new Object[]{++id, u.getNombre(), u.getCorreo(), u.getContrasenia(), "1223"});
        }

        //Iterar sobre datos para escribir en la hoja
        Set keyset = datos.keySet(); //obtener las claves
        int numeroRenglon = 0;
        for (Object key : keyset) { //iterar en cada clave y obtener su valor
            Row row = sheet.createRow(numeroRenglon++);
            Object[] arregloObjetos = datos.get(key); // almaceno los valores
            int numeroCelda = 0;
            for (Object obj : arregloObjetos) { // itero los valores y voy agrendo en el excel
                Cell cell = row.createCell(numeroCelda++);
                if (obj instanceof String) {
                    cell.setCellValue((String) obj);
                } else if (obj instanceof Integer) {
                    cell.setCellValue((Integer) obj);
                }
            }
        }
        try {
            String ruta = obtenerPropertie("rutaFicheroExcel");
            if (ruta != null) {
                File f = new File(ruta);
                if (f.exists()) f.delete(); // si existe lo genero de nuevo con nuevo datos
                //Se genera el documento
                FileOutputStream out = new FileOutputStream(f);
                workbook.write(out);
                out.close();
                //envio el email con el excel generado
                String asunto = "Adjunto excel de lista de usuarios generado.";
                if (Email.seEnviaElEmailAdjunto(correo, asunto, ruta)) return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    //generado con propertie
    public static void mostrarAllProperties(String rutaProp) {
        Properties props = new Properties();
        try {
            String ruta = obtenerPropertie(rutaProp);
            if (ruta != null) {
                props.load(new FileReader(ruta));
                //obtienes todas las claves
                Enumeration<Object> claves = props.keys();
                if (claves.hasMoreElements()) {
                    while (claves.hasMoreElements()) {
                        Object clave = claves.nextElement();
                        System.out.println(clave.toString() + ": " + props.get(clave).toString());
                    }
                    Utils.saltoLinea(2);
                } else {
                    System.out.println("Ningun dato registrado.\n");
                }
            }

        } catch (IOException e) {
            System.out.println("Ruta o fichero desconocido.\n");
        }
    }
}
