package app;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

public class ServerActions{
	public ServerActions() {
	}
	
	public String serverActions(String option) throws UnknownHostException {
		
		if(option.equalsIgnoreCase("remoteIpconfig")) {
			return getIp();
		}else if(option.equalsIgnoreCase("interface")) {
			return getInterface();
		}else if(option.equalsIgnoreCase("whatTimeIsIt")) {
			return getDate();
		}else {  
			return option; 
		}
		
	}
	
	public String getIp() throws UnknownHostException {
		return InetAddress.getLocalHost().getHostAddress();
	}
	
	public String getInterface() {
		String msg="";
		try {
			Enumeration<NetworkInterface> interfaces= NetworkInterface.getNetworkInterfaces();
			while(interfaces.hasMoreElements()) {
				NetworkInterface netN = interfaces.nextElement();
				if(netN.isUp() && netN.getHardwareAddress()!=null) {
					msg = netN.getName();
					break;
				}
			}
		}catch(SocketException e) {
			e.printStackTrace();
		}
		return msg;
	}
	
	public String getDate() {
		DateFormat format = new SimpleDateFormat("HH:mm:ss");
		Date date = new Date();
		return format.format(date);
	}
	
}