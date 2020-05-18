package mon_paradis;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import javax.swing.UIManager;
import javax.swing.JTextField;
import java.awt.Toolkit;

public class Dashboard extends JFrame {

	private JPanel contentPane;
	public  String priviledge;
	public  String monnom;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Dashboard frame = new Dashboard("role","nom");
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Dashboard(String priviledge,String monnom) {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\wamp64\\www\\testsa1\\img\\4.jpg"));
		setBackground(Color.BLACK);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 200, 657, 443);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(192, 192, 192));
		contentPane.setForeground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblGestion = new JLabel("Interface Administrateur ");
		lblGestion.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 28));
		lblGestion.setBounds(173, 11, 293, 27);
		contentPane.add(lblGestion);
		
		JLabel lblNewLabel = new JLabel("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\r\n");
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel.setBounds(152, 33, 325, 5);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel(" \"Mon Paradis\"");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 28));
		lblNewLabel_1.setBounds(222, 49, 189, 27);
		contentPane.add(lblNewLabel_1);
		
		JButton btnReservation = new JButton("Reservation");
		btnReservation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {	
								
					Reservation res = new Reservation();    
					res.showTable();
					res.setVisible(true);
				
			}
		});
		btnReservation.setBounds(400,90,120,70);
		btnReservation.setFont(new Font("Times New Roman", Font.ITALIC,17));
		
		
		JButton btnClient = new JButton("Client");
		btnClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				Client cli = new Client();
				cli.showTable();
				cli.setVisible(true);
			}
		});
		btnClient.setBounds(120,90,120,70);
		btnClient.setFont(new Font("Times New Roman", Font.ITALIC,17));
		
		
		JButton btnUtilisateur = new JButton("Utilisateur");
		btnUtilisateur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Utilisateur uti = new Utilisateur();
				uti.showTable();
				uti.setVisible(true);
				
			}
		});
		btnUtilisateur.setBounds(120,250,120,70);
		btnUtilisateur.setFont(new Font("Times New Roman", Font.ITALIC,17));
		
		
		JButton btnChambre = new JButton("Chambre");
		btnChambre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Chambre cha = new Chambre();
				cha.showTable();
				cha.setVisible(true);
			}
		});
		btnChambre.setBounds(400,250,120,70);
		btnChambre.setFont(new Font("Times New Roman", Font.ITALIC,17));
		
		System.out.println(priviledge);
		System.out.println(monnom);
		
		try { if(priviledge.contentEquals("administrateur")) {
			contentPane.add(btnReservation);
			contentPane.add(btnClient);
			contentPane.add(btnUtilisateur);
			contentPane.add(btnChambre);
		
			
			
		}else if(priviledge.contentEquals("gestionnaire")) {
			contentPane.add(btnReservation);
			contentPane.add(btnClient);
			contentPane.add(btnChambre);
			
			
		}else {
			
			JOptionPane.showMessageDialog(null, "Il y a une erreur avec votre compte!!! S'il vous Plait Contactez votre Administrateur");
		}
			
		}catch(Exception e) {
			System.out.println(e);
		}
		
	}
}

