package Chat_Java;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Cliente extends JFrame implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private JTextField mensaje = new JTextField();
	private JScrollPane scrollpane;
	private JTextArea textarea;
	private JButton boton = new JButton("Enviar");
	private JButton desconectar = new JButton("Salir");
	private Socket cliente;
	private DataOutputStream flujo_salida;
	
	
	public Cliente(String nombre)
	{
		super(" Cliente: " + nombre);
		getContentPane().setLayout(null);
		setSize(550, 400);
		setLocationRelativeTo(null);
		mensaje.setBounds(10, 10, 400, 30);
		getContentPane().add(mensaje);
		textarea = new JTextArea();
		scrollpane = new JScrollPane(textarea);
		scrollpane.setBounds(10, 50, 400, 300);
		getContentPane().add(scrollpane);
		boton.setBounds(420, 10, 100, 30);
		getContentPane().add(boton);
		desconectar.setBounds(420, 50, 100, 30);
		getContentPane().add(desconectar);
		textarea.setEditable(false);
		boton.addActionListener(this);
		getRootPane().setDefaultButton(boton);
		desconectar.addActionListener(this);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setVisible(true);
			
		//ABRIR EL SOCKET
		try
		{			
			String host = "localhost";
			int puerto = 4448; // PUERTO REMOTO AL QUE CONECTAR
			cliente = new Socket(host, puerto);	
			flujo_salida = new DataOutputStream(cliente.getOutputStream());
		} 
		catch (UnknownHostException e)
		{
			System.out.println(e.getMessage());
		} 
		catch (IOException e)
		{
			System.out.println(e.getMessage());
		} 
	}
	
	public static void main(String[] args)
	{
		String nombre = JOptionPane.showInputDialog("Introduce tu nombre o nick:");
		new Cliente(nombre).setVisible(true);
	}

	
	@Override
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource().equals(boton))
		{		
			try
			{			
				String texto = mensaje.getText().toString();
				flujo_salida.writeUTF(texto);
				mensaje.setText("");
				
			} catch (IOException e)
			{
				System.out.println("Servidor Apagado");
				System.out.println(e.getMessage());
			}				
		}
		else
		{
			try
			{
				cliente.close();
				System.exit(0);
			} catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
	}	
}
