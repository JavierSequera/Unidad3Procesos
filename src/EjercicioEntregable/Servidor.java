package EjercicioEntregable;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Servidor {
    public static void main(String[] args) {
        boolean encontrado = false;

        try {
            // 1 - Crear socket de tipo servidor y le indicamos el puerto
            ServerSocket servidor = new ServerSocket(49200);

            // 2 - Queda a la espera de peticiones y las acepta cuando las recibe
            System.out.println("Servidor se encuentra a la escucha...");
            Socket peticion = servidor.accept();

            // 3 - Abrir flujos de lectura y escritura de datos
            InputStream is = peticion.getInputStream();
            OutputStream os = peticion.getOutputStream();

            // 4 - Intercambiar datos con el cliente
            // Leer mensaje enviado por el cliente e imprimirlo por consola
            InputStreamReader isr = new InputStreamReader(is, "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String nombreFichero = br.readLine();


            // Enviarle mensaje al cliente
            System.out.println("Servidor envía al cliente un mensaje");
            OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
            BufferedWriter bw = new BufferedWriter(osw);
            bw.write(lectura(nombreFichero));
            bw.newLine();
            bw.flush();

            // 5 - Cerrar flujos de lectura y escritura
            br.close();
            isr.close();
            is.close();
            bw.close();
            osw.close();
            os.close();

            // 6 - Cerra la conexión
            System.out.println("Cierre de conexión del servidor");
            peticion.close();
            servidor.close();

        } catch (IOException e) {
            System.err.println("Ha habido algún error en la creación del Socket Servidor");
            e.printStackTrace();
        }
    }

    private static String lectura(String fichero) {
        String lectura = "";
        File archivo;
        FileReader fr = null;
        BufferedReader br = null;
        Scanner s = null;

        try {

            archivo = new File(fichero);
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
            s = new Scanner(br);

            while (s.hasNextLine()) {
                lectura += s.nextLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return lectura;
    }
}


