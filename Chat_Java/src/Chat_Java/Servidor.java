package Chat_Java;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;


public class Servidor extends JFrame implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private JScrollPane scrollpane;
	static JTextArea textarea;
	private JButton boton = new JButton("Apagar Servidor");
	private final JScrollPane scrollPane = new JScrollPane((Component) null);
	private static JLabel lblPuerto = new JLabel("PUERTO:");
	private static ServerSocket servidor;
	private static Socket cliente;
	
	public Servidor() 
	{
		super("SERVIDOR");
		getContentPane().setLayout(null);
		setSize(571, 415);
		setLocationRelativeTo(null);
		scrollPane.setBounds(402, 89, 143, 261);
		getContentPane().add(scrollPane);
		textarea = new JTextArea();
		scrollpane = new JScrollPane(textarea);
		scrollpane.setBounds(10, 10, 382, 340);
		getContentPane().add(scrollpane);
		boton.setBounds(402, 10, 143, 30);
		getContentPane().add(boton);
		textarea.setEditable(false);
		boton.addActionListener(this);
		this.getRootPane().setDefaultButton(boton);
		JLabel lblNewLabel = new JLabel("USUARIOS CONECTADOS");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(402, 70, 143, 14);
		getContentPane().add(lblNewLabel);
		lblPuerto.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblPuerto.setForeground(Color.RED);
		lblPuerto.setHorizontalAlignment(SwingConstants.CENTER);
		lblPuerto.setBounds(402, 51, 143, 14);
		getContentPane().add(lblPuerto);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setVisible(true);
		setResizable(false);


	}

	public static void main(String[] args)
	{

		new Servidor();

		//-----CREACIÓN DEL SERVER---------

		int puerto = 4448; // PUERTO DE ESCUCHA
		lblPuerto.setText("PUERTO: " + puerto);

		try
		{
			servidor = new ServerSocket(puerto);
			cliente = servidor.accept(); // ESPERANDO A UN CLIENTE
			
			while(true)
			{		
				DataInputStream flujo_salida = new DataInputStream(cliente.getInputStream());
				String texto = flujo_salida.readUTF();
				textarea.append(texto + "\n");
			}
						
		} catch(IOException e)
		{	
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{		
		System.exit(0);
	}
}
