package mon_paradis;

import java.awt.EventQueue;

import javax.swing.JFrame;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import com.toedter.calendar.JDateChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.UIManager;

public class reserverForm extends Reservation{

	JFrame frame;
	private JTextField txtnb_personnes;
	private JTextField txtNom;
	private JTextField txtPrenom;
	private JTextField txtEmail;
	private JComboBox<String> comboPays;

	private JTextField txtId_chambre;
	private JTextField txtId_client;
	protected String date_reservation;
	private JTextField txtAdresse;
	private JTextField txtRefReserv;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					reserverForm window = new reserverForm();
					window.frame.setVisible(true);
				} catch (Exception e) {
					
					e.printStackTrace();
				}
			}
		});
	}
	
	Database db = new Database();

	/**
	 * Create the application.
	 */
	public reserverForm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(UIManager.getColor("CheckBox.background"));
		frame.setBounds(100, 100, 971, 690);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JDateChooser dateReserv = new JDateChooser();
		dateReserv.setDateFormatString("MM-dd-yyyy");
		dateReserv.setBounds(228, 176, 176, 35);
		frame.getContentPane().add(dateReserv);
		
		
		JDateChooser dateA = new JDateChooser();
		dateA.setDateFormatString("MM-dd-yyyy");
		dateA.setBounds(228, 241, 176, 35);
		frame.getContentPane().add(dateA);
		
		JDateChooser dateD = new JDateChooser();
		dateD.setDateFormatString("MM-dd-yyyy");
		dateD.setBounds(228, 311, 176, 35);
		frame.getContentPane().add(dateD);
		
		JLabel lbldate_Reserv = new JLabel("Date Reservation");
		lbldate_Reserv.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lbldate_Reserv.setBounds(54, 176, 156, 35);
		frame.getContentPane().add(lbldate_Reserv);
		
		JLabel lblArrivee = new JLabel("Date Arrivee");
		lblArrivee.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblArrivee.setBounds(54, 248, 115, 28);
		frame.getContentPane().add(lblArrivee);
		
		JLabel lblDepart = new JLabel("Date Depart");
		lblDepart.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblDepart.setBounds(54, 318, 105, 28);
		frame.getContentPane().add(lblDepart);
		
		JLabel lblPersonnes = new JLabel("Nombre personnes");
		lblPersonnes.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblPersonnes.setBounds(54, 392, 156, 28);
		frame.getContentPane().add(lblPersonnes);
		
		JLabel lblIdchambre = new JLabel("Id Chambre");
		lblIdchambre.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblIdchambre.setBounds(54, 464, 96, 28);
		frame.getContentPane().add(lblIdchambre);
		

		JLabel lblIdclient = new JLabel("Ref Client");
		lblIdclient.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblIdclient.setBounds(565, 117, 96, 28);
		frame.getContentPane().add(lblIdclient);
		
		JLabel lblNom = new JLabel("Nom");
		lblNom.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNom.setBounds(565, 177, 62, 28);
		frame.getContentPane().add(lblNom);
		
		JLabel lblPrenom = new JLabel("Prenom");
		lblPrenom.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPrenom.setBounds(565, 248, 86, 28);
		frame.getContentPane().add(lblPrenom);
		
		JLabel lblAdresse = new JLabel("Adresse");
		lblAdresse.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAdresse.setBounds(565, 318, 86, 28);
		frame.getContentPane().add(lblAdresse);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblEmail.setBounds(565, 386, 62, 28);
		frame.getContentPane().add(lblEmail);
		
		JLabel lblPays = new JLabel("Pays");
		lblPays.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPays.setBounds(565, 464, 74, 28);
		frame.getContentPane().add(lblPays);
		
		txtnb_personnes = new JTextField();
		txtnb_personnes.setBounds(228, 379, 176, 35);
		frame.getContentPane().add(txtnb_personnes);
		txtnb_personnes.setColumns(10);
		
		txtNom = new JTextField();
		txtNom.setColumns(10);
		txtNom.setBounds(694, 176, 176, 35);
		frame.getContentPane().add(txtNom);
		
		txtPrenom = new JTextField();
		txtPrenom.setColumns(10);
		txtPrenom.setBounds(694, 241, 176, 35);
		frame.getContentPane().add(txtPrenom);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(694, 391, 176, 35);
		frame.getContentPane().add(txtEmail);

		
		txtId_chambre = new JTextField();
		txtId_chambre.setEditable(false);
		txtId_chambre.setColumns(10);
		txtId_chambre.setBounds(228, 463, 176, 35);
		frame.getContentPane().add(txtId_chambre);
		
		comboPays = new JComboBox();
		comboPays.setModel(new DefaultComboBoxModel<String>(new String[] {" ","Afghanistan", "Afrique du Sud", "Albanie",
				"Alg\u00E9rie", "Allemagne", "Andorre", "Angola", "Antigua-et-Barbuda","Arabie Saoudite", "Argentine",
				"Arm\u00E9nie", "Australie", "Autriche", "Azerba\u00EFdjan", "Bahamas", "Bahre\u00EFn", "Bangladesh", 
				"Barbade", "Belau", "Belgique", "Belize", "B\u00E9nin", "Bhoutan", "Bi\u00E9lorussie", "Birmanie", 
				"Bolivie", "Bosnie-Herz\u00E9govine", "Botswana", "Br\u00E9sil", "Brunei", "Bulgarie", "Burkina", "Burundi", 
				"Cambodge", "Cameroun", "Canada", "Cap-Vert", "Chili", "Chine", "Chypre", "Colombie", "Comores", "Congo", 
				"Cook", "Cor\u00E9e du Nord", "Cor\u00E9e du Sud", "Costa Rica", "C\u00F4te d'Ivoire", "Croatie", "Cuba", 
				"Danemark", "Djibouti", "Dominique", "\u00C9gypte", "\u00C9mirats arabes unis", "\u00C9quateur", "\u00C9rythr\u00E9e",
				"Espagne", "Estonie", "\u00C9tats-Unis", "\u00C9thiopie", "Fidji", "Finlande", "France", "Gabon", "Gambie",
				"G\u00E9orgie", "Ghana", "Gr\u00E8ce", "Grenade", "Guatemala", "Guin\u00E9e", "Guin\u00E9e-Bissao", "Guin\u00E9e \u00E9quatoriale",
				"Guyana", "Ha\u00EFti", "Honduras", "Hongrie", "Inde", "Indon\u00E9sie", "Iran", "Iraq", "Irlande", "Islande", "Isra\u00EBl",
				"Italie", "Jama\u00EFque", "Japon", "Jordanie", "Kazakhstan", "Kenya", "Kirghizistan", "Kiribati", "Kowe\u00EFt", "Laos", 
				"Lesotho", "Lettonie", "Liban", "Liberia", "Libye", "Liechtenstein", "Lituanie", "Luxembourg", "Mac\u00E9doine", "Madagascar", "Malaisie",
				"Malawi", "Maldives", "Mali", "Malte", "Maroc", "Marshall", "Maurice", "Mauritanie", "Mexique", "Micron\u00E9sie", "Moldavie",
				"Monaco", "Mongolie", "Mozambique", "Namibie", "Nauru", "N\u00E9pal", "Nicaragua", "Niger", "Nigeria", "Niue", 
				"Norv\u00E8ge", "Nouvelle-Z\u00E9lande", "Oman", "Ouganda", "Ouzb\u00E9kistan", "Pakistan", "Panama", "Papouasie - Nouvelle Guin\u00E9e",
				"Paraguay", "Pays-Bas", "P\u00E9rou", "Philippines", "Pologne", "Portugal", "Qatar", "R\u00E9publique Centrafricaine",
				"R\u00E9publique Dominicaine", "R\u00E9publique tch\u00E8que","Rodrigues", "Roumanie", "Royaume-Uni", "Russie", "Rwanda", 
				"Saint-Christophe-et-Ni\u00E9v\u00E8s", "Sainte-Lucie", "Saint-Marin", "Saint-Si\u00E8ge, ou leVatican", "Saint-Vincent-et-les Grenadines"
				, "Salomon", "Salvador", "Samoa occidentales", "Sao Tom\u00E9-et-Principe", "S\u00E9n\u00E9gal", "Seychelles", "Sierra Leone", 
				"Singapour", "Slovaquie", "Slov\u00E9nie", "Somalie", "Soudan", "Sri Lanka", "Su\u00E8de", "Suisse", "Suriname",
				"Swaziland", "Syrie", "Tadjikistan", "Tanzanie", "Tchad", "Tha\u00EFlande", "Togo", "Tonga", "Trinit\u00E9-et-Tobago",
				"Tunisie", "Turkm\u00E9nistan", "Turquie", "Tuvalu", "Ukraine", "Uruguay", "Vanuatu", "Venezuela", "Vi\u00EAt Nam",
				"Y\u00E9men", "Yougoslavie", "Za\u00EFre", "Zambie", "Zimbabwe"}));
		comboPays.setBounds(694, 463, 176, 35);
		frame.getContentPane().add(comboPays);
		
		txtId_client = new JTextField();
		txtId_client.setColumns(10);
		txtId_client.setBounds(694, 110, 176, 35);
		frame.getContentPane().add(txtId_client);
		
		
///////////////////////////==============================================NEW==========================================/==///////////////////////////////////////////////		
		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
///////////////////////////////La Partie Reservation///////////////////////////////////////////////////				
				String date_reservation =((JTextField)dateReserv.getDateEditor().getUiComponent()).getText();
				String date_arrivee =((JTextField)dateA.getDateEditor().getUiComponent()).getText();
				String date_depart =((JTextField)dateD.getDateEditor().getUiComponent()).getText();
				String nb_personnes = txtnb_personnes.getText();
				String id_chambre = txtId_chambre.getText();
				
///////////////////////////////La Partie Client///////////////////////////////////////////////////	
				String id_client = txtId_client.getText();
				String nom = txtNom.getText();
				String prenom = txtPrenom.getText();
				String adresse =txtAdresse.getText();
				String mail = txtEmail.getText();
				String pays =comboPays.getSelectedItem().toString();

				
				
				if( nom.trim().length()==0 || prenom.trim().length()==0 || adresse.trim().length()==0 || mail.trim().length()==0 || pays.trim().length()==0 ) {
					
					JOptionPane.showMessageDialog(null, "Remplissez tous les champs ");
					
					}			
			
				
				else {
					
					try {
						Connection con;
						 con =(Connection) db.db_connect();
//						 String id_client= null;
						PreparedStatement stmt = con.prepareStatement("INSERT INTO client ( nom, prenom, adresse, mail, pays) VALUES(?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
						
						stmt.setString(1, nom);
						stmt.setString(2, prenom);
						stmt.setString(3, adresse);
						stmt.setString(4, mail);
						stmt.setString(5, pays);
						stmt.executeUpdate();
						Client.showTable();
						
						ResultSet rs= stmt.getGeneratedKeys();
						if(rs.next()) {
							id_client=rs.getString(1);
							System.out.println(id_client);
						}				
						PreparedStatement stm = con.prepareStatement("INSERT INTO reservation (date_reservation,date_arrivee, date_depart, nb_personnes, id_chambre, id_client) VALUES(?,?,?,?,?,?)");
						
						stm.setString(1, date_reservation);
						stm.setString(2, date_arrivee);
						stm.setString(3, date_depart);
						stm.setString(4, nb_personnes);
						stm.setString(5, id_chambre);
						stm.setString(6, id_client);
						stmt.executeUpdate();
						Reservation.showTable();
						JOptionPane.showMessageDialog(null,"Utilisateur Enregistrer");
						
					}catch(Exception e1) {
						System.out.print(e1);
						
					}
					
					}
				}
					
		
		});
		btnAjouter.setFont(new Font("Times New Roman", Font.BOLD, 17));
		btnAjouter.setBounds(441, 555, 137, 57);
		frame.getContentPane().add(btnAjouter);
		
		JLabel lblFormulaireDeReservation = new JLabel("Formulaire de  Reservation");
		lblFormulaireDeReservation.setForeground(Color.BLUE);
		lblFormulaireDeReservation.setFont(new Font("Georgia", Font.ITALIC, 28));
		lblFormulaireDeReservation.setBounds(342, 11, 359, 35);
		frame.getContentPane().add(lblFormulaireDeReservation);
		
		txtAdresse = new JTextField();
		txtAdresse.setFont(new Font("Times New Roman", Font.BOLD, 16));
		txtAdresse.setColumns(10);
		txtAdresse.setBounds(694, 311, 176, 35);
		frame.getContentPane().add(txtAdresse);
		
		JLabel lblRefReservation = new JLabel("Ref Reservation");
		lblRefReservation.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblRefReservation.setBounds(54, 117, 137, 28);
		frame.getContentPane().add(lblRefReservation);
		
		txtRefReserv = new JTextField();
		txtRefReserv.setColumns(10);
		txtRefReserv.setBounds(228, 117, 176, 28);
		frame.getContentPane().add(txtRefReserv);
		
	
	}
		
	public void setVisible(boolean b) {
		// TODO Auto-generated method stub
		
	}
	}
