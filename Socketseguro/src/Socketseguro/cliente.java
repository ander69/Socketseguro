package Socketseguro;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.UnknownHostException;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public class cliente {

	public static void main(String[] args) {
		String host="192.168.222.207";
		int puerto=6000;
		
		
		SSLSocketFactory sfact=(SSLSocketFactory) SSLSocketFactory.getDefault();
		try {
			
			SSLSocket Cliente=(SSLSocket) sfact.createSocket(host, puerto);
			System.out.println("Programa cliente iniciado...");
			
			//Creo flujode salida al servidor
			DataOutputStream flujoSalida=new DataOutputStream(Cliente.getOutputStream());
			
			flujoSalida.writeInt(5);
			
			flujoSalida.writeInt(5);
			
			
			//Creo flujo de entrada al servidor
			DataInputStream flujoEntrada=new DataInputStream(Cliente.getInputStream());
			//El servidor me envía un mensaje
			System.out.println("Recibiendo del servidor: \n\t"+flujoEntrada.readInt());
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
