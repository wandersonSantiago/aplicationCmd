package com.cmd.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.cmd.model.Comando;

@Service
public class ComandoService {

	private static final Charset UTF_8 = Charset.forName("UTF-8");
	private static final Charset ISO = Charset.forName("ISO-8859-1");
	
	public Comando executar(Comando comando) {		
		
		  try {
			  comando.setData(new Date());
	            ProcessBuilder builder = new ProcessBuilder("cmd", "/c",
	                String.join("& ", comando.getExecutar()));

	            builder.redirectErrorStream(true);

	            Process p = builder.start();

	            BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
	            String line;

	            while (true) {
	                line = r.readLine();
	                if (line == null) {
	                    break;
	                }
	               String texto = new String(line.getBytes(ISO),UTF_8);
	                System.out.println(texto);
	                comando.addRetorno(texto);
	            }
	        } catch(Exception e) {
	        	 System.out.println(e);
	        	 comando.addRetorno(e.getStackTrace().toString());
	        }
		return comando;		  
		
	}
}
