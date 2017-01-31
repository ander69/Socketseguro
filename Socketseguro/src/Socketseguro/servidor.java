package Socketseguro;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;

public class servidor {
	public static void main(String[] args) throws IOException {
		int puerto=6000;
		int num1,num2,suma;
		SSLServerSocketFactory sfact=(SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
		SSLServerSocket servidorSSL=(SSLServerSocket) sfact.createServerSocket(puerto);
		SSLSocket clienteConectado=null;
		
		DataInputStream flujoEntrada=null;
		DataOutputStream flujoSalida=null;
			System.out.println("Esperando al cliente");
			clienteConectado=(SSLSocket) servidorSSL.accept();
			flujoEntrada=new DataInputStream(clienteConectado.getInputStream());
			//El cliente envía el mensaje
			num1=flujoEntrada.readInt();
			num2=flujoEntrada.readInt();
			suma=num1+num2;
			
			flujoSalida=new DataOutputStream(clienteConectado.getOutputStream());
			//Envio un saludo al cliente
			flujoSalida.writeInt(suma);
		
		//Cerrar streams y sockets
		flujoEntrada.close();
		flujoSalida.close();
		clienteConectado.close();
		servidorSSL.close();
		
	}

}
