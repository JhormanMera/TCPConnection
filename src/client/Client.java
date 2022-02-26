package client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

	private static String ip="127.0.0.1";
	private static int port=6000;
	private static Socket socket;
	private static BufferedWriter bwriter;
	private static BufferedReader breader;
	private static Scanner scanner;

	public static void main(String[] args) {
		scanner = new Scanner(System.in);
		while(true) {
			String line= scanner.nextLine();
			clientMessage(line);
		}
	}

	private static void clientMessage(String line) {
		try {
			socket = new Socket(ip,port);
			System.out.println("Conectado");
			bwriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			breader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			bwriter.write(line+"\n");
			bwriter.flush();
			clientActions(line);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void clientActions(String option) throws IOException {
		if(option.equalsIgnoreCase("RTT")) {
			long st =System.nanoTime();
			byte[] message1024 = new byte[1024];
			String send1024 = new String(message1024);
			bwriter.write(send1024+"\n");
			bwriter.flush();
			//Recibir mensaje
			String recibido = breader.readLine();
			System.out.println("Mensaje recibido: "+recibido);
			long sendingTime=System.nanoTime()-st;
			System.out.println("Tardó: "+sendingTime+" Nanosegundos");
		}else if(option.equalsIgnoreCase("speed")) {
			long st =System.nanoTime();
			byte[] message8192 = new byte[8192];
			String send8192 = new String(message8192);
			bwriter.write(send8192+"\n");
			bwriter.flush();

			//Recibir mensaje
			String recibido = breader.readLine();
			System.out.println("Mensaje recibido: "+recibido);
			long sendingTime=System.nanoTime()-st;
			sendingTime=sendingTime/1000000;
			System.out.println("Tardó: "+sendingTime+" Segundos");
			long kilobytes = (8/sendingTime);
			System.out.println("Velocidad de transferencia: "+kilobytes+" KB/S");			
		}else {
			String recibido = breader.readLine();
			System.out.println("Mensaje recibido: "+recibido);
		}
	}

}
