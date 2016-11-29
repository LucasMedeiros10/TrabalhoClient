package br.univel.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

import br.univel.view.Loading;

/**
 * Socket para envio de requisições e recebimento de respotas do 
 * server
 * 
 * @author LucasMedeiros
 *
 */

public class ClientSocket {

	public Object enviarRequisicao(Object objeto){
		
		Socket socket = null;
		OutputStream output = null;
		InputStream input = null;
		Object retorno = null;
		
		Loading loading = new Loading();
		Thread threadCarregar = new Thread(loading);
		threadCarregar.start();		
		
		try {
			socket = new Socket("localhost", 1000);
			output = socket.getOutputStream();
			ObjectOutputStream objOutput = new ObjectOutputStream(output);
			objOutput.writeObject(objeto);

			// Notifica que a escrita de informações terminou
			objOutput.flush();
			input = socket.getInputStream();
			ObjectInputStream objInput = new ObjectInputStream(input);
			retorno = objInput.readObject();
			
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (input != null) {
					input.close();
				}
				if (output != null) {
					output.close();
				}
				if (socket != null) {
					socket.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		loading.setExecutando(false);	
		
		return retorno;		
	}
}
