package Socketseguro;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.UnknownHostException;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;


public class ClienteSSL {

	public static void main(String[] args) {
		String host="Localhost";
		int puerto=6000;
		
		System.out.println("Programa cliente iniciado...");
		SSLSocketFactory sfact=(SSLSocketFactory) SSLSocketFactory.getDefault();
		try {
			SSLSocket Cliente=(SSLSocket) sfact.createSocket(host, puerto);
			//Creo flujode salida al servidor
			DataOutputStream flujoSalida=new DataOutputStream(Cliente.getOutputStream());
			
			//Envío un saludo al servidor
			flujoSalida.writeUTF("Saludos al servidor desde el cliente");
			//Creo flujo de entrada al servidor
			DataInputStream flujoEntrada=new DataInputStream(Cliente.getInputStream());
			//El servidor me envía un mensaje
			System.out.println("Recibiendo del servidor: \n\t"+flujoEntrada.readUTF());
			//Cerrar streams y sockets
			flujoSalida.close();
			flujoEntrada.close();
			Cliente.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
