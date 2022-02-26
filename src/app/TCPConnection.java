package app;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPConnection extends Thread{

    public final static int port = 6000;
    private Socket socket;
    private BufferedReader br;
    private BufferedWriter bw;
    private ServerActions sv;
	private ServerSocket server;
    
    @Override
    public void run() {
        try {
            server = new ServerSocket(port);
            sv = new ServerActions();
            while(true){
            	System.out.println("Esperando un cliente");
                socket = server.accept();
                System.out.println("Cliente conectado");
                br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                String msg = br.readLine();
                String svAnswer="";
                svAnswer= sv.serverActions(msg);
                if(msg!=svAnswer) {
                	bw.write(svAnswer+"\n");
                    bw.flush();
                }else {
                	 msg = br.readLine();
                	 bw.write(msg+"\n");
                     bw.flush();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}