package ec.edu.epn.redes.cs.tcp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringTokenizer;

public class TCPServer2 {
	public static final int PORT = 9090;
	  public static void main(String[] args) throws IOException {
	    // Establece el puerto en el que escucha peticiones
	    ServerSocket socketServidor = null;
	    try {
	      socketServidor = new ServerSocket(PORT);
	    } catch (IOException e) {
	      System.out.println("No puede escuchar en el puerto: " + PORT);
	      System.exit(-1);
	    }

	    Socket socketCliente = null;
	    BufferedReader entrada = null;
	    PrintWriter salida = null;

	    System.out.println("Escuchando: " + socketServidor);
	    try {
	      // Se bloquea hasta que recibe alguna petición de un cliente
	      // abriendo un socket para el cliente
	      socketCliente = socketServidor.accept();
	     
	      // Establece canal de entrada
	      entrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
	      // Establece canal de salida
	      salida = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socketCliente.getOutputStream())),true);
	      
	      // Hace eco de lo que le proporciona el cliente, hasta que recibe "Adios"
	      while (true) {  
	    String str = entrada.readLine();
		System.out.println("ClientePerris dice:" + str);
		salida.println(Suma(str));
		if (str.equals("Adios")) break;
	      }
	    } catch (IOException e) {
	      System.out.println("IOException: " + e.getMessage());
	    }  
	    salida.close();
	    entrada.close();
	    socketCliente.close();
	    socketServidor.close();
	    
	  }
	  
	  private static String Suma(String operate){
		  StringTokenizer tokens=new StringTokenizer(operate, "+");
		  String n1=tokens.nextToken();
		  String n2=tokens.nextToken();
		  
		  int a= Integer.parseInt(n1);
		  int b= Integer.parseInt(n2);
		  
		  return (operate +"="+ (a+b));
	  }
	

}
