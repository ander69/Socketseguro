package Socketseguro;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;


public class ServidorSSL {

	public static void main(String[] args)throws IOException {
		int puerto=6000;
		SSLServerSocketFactory sfact=(SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
		SSLServerSocket servidorSSL=(SSLServerSocket) sfact.createServerSocket(puerto);
		SSLSocket clienteConectado=null;
		
		DataInputStream flujoEntrada=null;
		DataOutputStream flujoSalida=null;
		
		for(int i=1;i<5;i++){
			System.out.println("Esperando al cliente "+i);
			clienteConectado=(SSLSocket) servidorSSL.accept();
			flujoEntrada=new DataInputStream(clienteConectado.getInputStream());
			//El cliente me envía un mensaje
			System.out.println("Recibiendo del cliente: "+i+"\n\t"+flujoEntrada.readUTF());
			flujoSalida=new DataOutputStream(clienteConectado.getOutputStream());
			//Envio un saludo al cliente
			flujoSalida.writeUTF("Saludos al cliente del Servidor");
		}
		//Cerrar streams y sockets
		flujoEntrada.close();
		flujoSalida.close();
		clienteConectado.close();
		servidorSSL.close();
	}
}
