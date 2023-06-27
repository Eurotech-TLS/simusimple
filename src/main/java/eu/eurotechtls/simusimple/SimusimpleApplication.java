package eu.eurotechtls.simusimple;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication
public class SimusimpleApplication extends JFrame implements ActionListener {
    private JButton putButton, putButton2;
	private JTextField textField;
	String url = "http://37.59.24.247:8080/apis/salida";
        
    public SimusimpleApplication() {
        setTitle("Aplicación POST");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Crear el campo de texto
		textField = new JTextField(20);

        // Crear el botón y agregar el ActionListener
        putButton = new JButton(" SALIDA");
        putButton.addActionListener(this);


        putButton2 = new JButton(" LLEGADA");
        putButton2.addActionListener(this);

        // Crear el panel y agregar el botón
        JPanel panel = new JPanel();
		panel.add(textField);
        panel.add(putButton);
        panel.add(putButton2);

        // Agregar el panel al marco
        getContentPane().add(panel);

        pack();
        setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == putButton) {
            // Lógica para realizar la llamada PUT
            realizarPut();
        }
		if (e.getSource() == putButton2) {
            // Lógica para realizar la llamada PUT
            realizarPut2();
        }
    }

    private void realizarPut() {
        // Código para realizar la llamada POST
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd%20HH:mm:ss");
        String date = dateFormat.format(new Date());

       String postData = "?id="+ textField.getText() + "&time=" + date + "";

		
		System.out.println(date); 

		try {
            // Crear objeto URL
            URL apiUrl = new URI(url + postData).toURL();

            // Abrir la conexión HTTP
            HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();

            // Establecer el método de solicitud como PUT
            connection.setRequestMethod("PUT");

            // Habilitar el envío y recepción de datos
            connection.setDoOutput(true);

            // Escribir los datos en el cuerpo de la solicitud
            DataOutputStream dataOutputStream = new DataOutputStream(connection.getOutputStream());
			System.out.println("A enviar: " + dataOutputStream);
            dataOutputStream.writeBytes(postData);
            dataOutputStream.flush();
            dataOutputStream.close();

            // Obtener la respuesta del servidor
            int responseCode = connection.getResponseCode();

            BufferedReader bufferedReader;
            if (responseCode == HttpURLConnection.HTTP_OK) {
                bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            } else {
                bufferedReader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            }

            // Leer la respuesta del servidor
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = bufferedReader.readLine()) != null) {
                response.append(inputLine);
            }
            bufferedReader.close();

            // Imprimir la respuesta del servidor
            System.out.println("Respuesta del servidor: " + response.toString());

        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }

    }

	    private void realizarPut2() {
        // Código para realizar la llamada POST
        // Código para realizar la llamada POST
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd%20HH:mm:ss");
        String date = dateFormat.format(new Date());

        String postData = "?id="+ textField.getText() + "&time=" + date + "";
		
		try {
            // Crear objeto URL
            URL apiUrl = new URI(url + postData).toURL();

            // Abrir la conexión HTTP
            HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();

            // Establecer el método de solicitud como PUT
            connection.setRequestMethod("PUT");

            // Habilitar el envío y recepción de datos
            connection.setDoOutput(true);

            // Escribir los datos en el cuerpo de la solicitud
            DataOutputStream dataOutputStream = new DataOutputStream(connection.getOutputStream());
			System.out.println("A enviar: " + dataOutputStream);
            dataOutputStream.writeBytes(postData);
            dataOutputStream.flush();
            dataOutputStream.close();

            // Obtener la respuesta del servidor
            int responseCode = connection.getResponseCode();

            BufferedReader bufferedReader;
            if (responseCode == HttpURLConnection.HTTP_OK) {
                bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            } else {
                bufferedReader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            }

            // Leer la respuesta del servidor
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = bufferedReader.readLine()) != null) {
                response.append(inputLine);
            }
            bufferedReader.close();

            // Imprimir la respuesta del servidor
            System.out.println("Respuesta del servidor: " + response.toString());

        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        SimusimpleApplication mainFrame = new SimusimpleApplication();
        mainFrame.setVisible(true);
    }
}
