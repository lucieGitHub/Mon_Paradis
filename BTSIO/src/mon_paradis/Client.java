package mon_paradis;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.sql.Connection;
import net.proteanit.sql.DbUtils;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.UIManager;
import java.awt.Toolkit;
import javax.swing.JSeparator;
 

public class Client extends JFrame {

	private JPanel contentPane;
	
	private JTextField txtSearchingFor;
		private JTextField txtSearch;
	private static JTable tableClient;
	private JTextField txtid_client;
	private JTextField txtNom;
	private JTextField txtPrenom;
	private JTextField txtAdresse;
	private JTextField txtMail;
	private JComboBox<String> comboBoxPays,comboBoxSearch;
	private String filterCriteria;
	private JButton btnQuitter;
	
	Database db = new Database();
//	private JTextField textField;

	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Client frame = new Client();
					frame.setVisible(true);
					showTable();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Client() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\aniel\\Documents\\BTS SIO 2020\\SCREENSHOT\\4.jpg"));
		setTitle("Logiciel de Mon Paradis");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 300, 1328, 618);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(175, 238, 238));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnQuitter = new JButton("D\u00E9connexion");
		btnQuitter.setFont(new Font("Calibri Light", Font.BOLD | Font.ITALIC, 14));
		 btnQuitter.setBackground(new Color(240, 240, 240));
		 btnQuitter.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent e) { //quitter l'application
		  		System.exit(0);
		  	}
		  });
		 btnQuitter.setBounds(309, 528, 122, 51);
		contentPane.add(btnQuitter);
//================================ EFFACER ===========================================================			
		JButton btnDelete = new JButton("Effacer");
		btnDelete.setFont(new Font("Calibri Light", Font.BOLD | Font.ITALIC, 15));
		btnDelete.setForeground(Color.RED);
		btnDelete.addActionListener(new ActionListener() {
			

			public void actionPerformed(ActionEvent arg0) {
				
				String id_client = txtid_client.getText();
				String nom = txtNom.getText();
				String prenom = txtPrenom.getText();
				String adresse = txtAdresse.getText();
				String mail = txtMail.getText();
				String pays = comboBoxPays.getSelectedItem().toString();
				
				
if( nom.trim().length()==0 || prenom.trim().length()==0 || adresse.trim().length()==0 ||  mail.trim().length()==0 ){
		
					
					JOptionPane.showMessageDialog(null, "Remplissez les champs");

			}else {
				
				try {
					
					
					Connection con = db.db_connect();
					PreparedStatement stmt = con.prepareStatement("DELETE FROM CLIENT WHERE id_client=?");
				 	stmt.setString(1,id_client);
			 
					stmt.executeUpdate();
					JOptionPane.showMessageDialog(null,"Mise à jour effectué");
					showTable();
				
				}catch(Exception Up) {
					System.out.print(Up);
				}
				
			}
			}
		});
		btnDelete.setBounds(309, 454, 122, 51);
		contentPane.add(btnDelete);
//================================ NEW ===========================================================		
		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.setFont(new Font("Calibri Light", Font.BOLD | Font.ITALIC, 15));
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String id_client = txtid_client.getText();
				String nom = txtNom.getText();
				String prenom = txtPrenom.getText();
				String adresse = txtAdresse.getText();
				String mail = txtMail.getText();
				String pays = comboBoxPays.getSelectedItem().toString();
				
//				===========================VAlidation===========
if( nom.trim().length()==0 && prenom.trim().length()==0 &&  adresse.trim().length()==0 &&  mail.trim().length()==0 ){
		
					
					JOptionPane.showMessageDialog(null, "Remplissez les champs");

			}
			
				 else if(nom.trim().length()==0) {
					
					 JOptionPane.showMessageDialog(null,"Remplissez le champs de nom");
					
				}else if(prenom.trim().length()==0) {
				
					JOptionPane.showMessageDialog(null,"Remplissez le champs de prenom");
					
				}else if(adresse.trim().length()==0) {
					
					JOptionPane.showMessageDialog(null,"Remplissez le champs de adresse");	
				
				}else if(mail.trim().length()==0) {
					
					
					JOptionPane.showMessageDialog(null,"Remplissez le champs de mail");
					
		
				}else {
					
					try {
						
						
						Connection con = db.db_connect();
						PreparedStatement stmt = con.prepareStatement("INSERT INTO  client (nom,prenom,adresse,mail,pays)VALUES(?,?,?,?,?)");
						stmt.setString(1, nom);
						stmt.setString(2, prenom );
						stmt.setString(3, adresse);
						stmt.setString(4, mail);
						stmt.setString(5, pays);
						
						stmt.executeUpdate();
						JOptionPane.showMessageDialog(null,"Mise à jour effectué");
						showTable();
						
						
						
						
					}catch(Exception Up) {
						System.out.print(Up);
					}
				}
								
			}
		});
		
//=============================REFRESH=================================================		
		btnAjouter.setBounds(48, 454, 122, 51);
		contentPane.add(btnAjouter);
		
		JButton btnActualiser = new JButton("Actualiser");
		btnActualiser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				showTable();
				txtid_client.setText("");
				
			}
		});
		
		
		btnActualiser.setBounds(1045, 113, 122, 29);
		contentPane.add(btnActualiser);
		
		JLabel lblnom = new JLabel("Nom");
		lblnom.setFont(new Font("Georgia", Font.ITALIC, 17));
		lblnom.setBounds(57, 194, 69, 27);
		contentPane.add(lblnom);
		
		JLabel lblPrenom = new JLabel("Prenom");
		lblPrenom.setFont(new Font("Georgia", Font.ITALIC, 17));
		lblPrenom.setBounds(57, 238, 69, 27);
		contentPane.add(lblPrenom);
		
		JLabel lblAdresse = new JLabel("Adresse");
		lblAdresse.setFont(new Font("Georgia", Font.ITALIC, 17));
		lblAdresse.setBounds(57, 288, 62, 22);
		contentPane.add(lblAdresse);
		
		JLabel lblMail = new JLabel("Email");
		lblMail.setFont(new Font("Georgia", Font.ITALIC, 17));
		lblMail.setBounds(57, 338, 69, 29);
		contentPane.add(lblMail);
		
		JButton btnModifier = new JButton("Modifier");
		btnModifier.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 16));
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String id_client = txtid_client.getText();
				String nom = txtNom.getText();
				String prenom = txtPrenom.getText();
				String adresse = txtAdresse.getText();
				String mail = txtMail.getText();
				String pays = comboBoxPays.getSelectedItem().toString();
				
				
//				===========================VAlidation===========
if( nom.trim().length()==0 && prenom.trim().length()==0 && adresse.trim().length()==0 && mail.trim().length()==0 ){
		
					
					JOptionPane.showMessageDialog(null, "Remplissez les champs");

			}
			
				 else if(nom.trim().length()==0) {
					
					 JOptionPane.showMessageDialog(null,"Remplissez le champs de nom");
					
				}else if(prenom.trim().length()==0) {
				
					JOptionPane.showMessageDialog(null,"Remplissez le champs de prenom");
					
								
				}else if(adresse.trim().length()==0) {
					
					
					JOptionPane.showMessageDialog(null,"Remplissez le champs de adresse");
						
					
				}else if(mail.trim().length()==0) {
					
					
					JOptionPane.showMessageDialog(null,"Remplissez le champs de email");
						
				
				}else {
					
					try {
						
						
						Connection con = db.db_connect();
						PreparedStatement stmt = con.prepareStatement("Update client set nom=?,prenom=?, adresse=?, mail=?, pays=? WHERE id_client=?");
					 	stmt.setString(1, nom);
						stmt.setString(2, prenom);
						stmt.setString(3, adresse);
						stmt.setString(4, mail);
						stmt.setString(5, pays);
						stmt.setString(6, id_client);
						stmt.executeUpdate();
						JOptionPane.showMessageDialog(null,"Votre table a été modifiée");
						showTable();
						
				
					}catch(Exception Mo) {
						System.out.print(Mo);
					}
				}
					
			}
		});
		btnModifier.setBounds(48, 526, 122, 50);
		contentPane.add(btnModifier);
		
		JLabel lblEnregistrer = new JLabel("Enregistrez-Vous!!");
		lblEnregistrer.setFont(new Font("Georgia", Font.BOLD | Font.ITALIC, 17));
		lblEnregistrer.setBounds(67, 74, 227, 39);
		contentPane.add(lblEnregistrer);
		
		
		JLabel lblSearch = new JLabel("Recherche");
		lblSearch.setBounds(568, 117, 69, 20);
		contentPane.add(lblSearch);
		
		
		//=============================SEARCH=========================================================		
	 	this.comboBoxSearch =new JComboBox<String>();
		comboBoxSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			
			Object selected =comboBoxSearch.getSelectedItem();
			if(selected.toString().equals("Nom"))
				filterCriteria= "nom";
			
			if(selected.toString().equals("Prénom"))
				filterCriteria= "prenom";
			
			if(selected.toString().equals("Adresse"))
				filterCriteria= "adresse";
			
			if(selected.toString().equals("Courriel"))
				filterCriteria= "mail";
			
			if(selected.toString().equals("Privilege"))
				filterCriteria= "pays";
			System.out.print(filterCriteria);
	
				
			}
		});
		
		comboBoxSearch.setModel(new DefaultComboBoxModel<String>(new String[]{" ","Nom","Prénom","Adresse","Courriel","Privilege"}));
		comboBoxSearch.setBounds(858, 114, 122, 26);
		contentPane.add(comboBoxSearch);

	txtSearch = new JTextField();
	txtSearch.addKeyListener(new KeyAdapter() {
		@Override
		public void keyReleased(KeyEvent arg0) {
	 
	
		try {
			
			String searchObject=txtSearch.getText();
			Connection con;
			con = db.db_connect();
			PreparedStatement stmt = con.prepareStatement("SELECT  id_client as '#', nom as 'Nom', prenom, as 'Prénom', adresse as 'Adresse' , mail as 'Mail' , pays as 'Pays' "
					+ " FROM client WHERE "+filterCriteria+" LIKE ? ");
			stmt.setString(1,  "%" +searchObject + "%");
			

			ResultSet rs = stmt.executeQuery();
			
			
			
			
			tableClient.setModel(DbUtils.resultSetToTableModel(rs));//link database data to table
			
			
			
		}catch(Exception e1) {
			
			System.out.print(e1);
			
//			JOptionPane.showMessageDialog(null, "Veuillez d'abord seletionner les critères");
								
		}
	}
});
txtSearch.setText("Recherche sur...");
txtSearch.setBounds(666, 111, 126, 26);
contentPane.add(txtSearch);

		JScrollPane scrollPane = new JScrollPane();
		
		tableClient = new JTable() {
			
			public boolean isCellEditable(int row,int column) {
				return false;
			}//cela permet de restrincte la partie d'editable....
		};
		
		
			tableClient.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				
			try {  
				
				int row = tableClient.getSelectedRow();// nous permet de stocker des valeurs dans la variable row...
				
//				System.out.print(row);
				
				String Click = (tableClient.getModel().getValueAt(row, 0).toString());
				
//				System.out.print(Click);
				
				

				Connection con;
				con= db.db_connect();
				PreparedStatement stmt = con.prepareStatement("SELECT * FROM client WHERE id_client='"+Click+"'");
				ResultSet rs = stmt.executeQuery();
				if(rs.next()) {
					
					String data1 = rs.getString("id_client");
					String data2 = rs.getString("nom");
					String data3 = rs.getString("prenom");	
					String data4 = rs.getString("adresse");	
					String data5 = rs.getString("mail");
					String data6 = rs.getString("pays");
					

					System.out.println(data1);
					System.out.println(data2);
					System.out.println(data3);
					System.out.println(data4);
					System.out.println(data5);
					System.out.println(data6);
				
					System.out.println("_______________________________________________________________________________");
					
					txtid_client.setText(data1);
					txtNom.setText(data2);
					txtPrenom.setText(data3);
					txtAdresse.setText(data4);
					txtMail.setText(data5);
		    		comboBoxPays.setSelectedItem(data6);
					
				}		
				
			}catch(Exception en) {
				
				System.out.print(en);
				JOptionPane.showMessageDialog(null, en);
				
			}	
	
				
			}// pour permettre de faire des actions sur ma table avec la souris
		});
		
		
		scrollPane.setBounds(554, 167, 703, 436);
		contentPane.add(scrollPane);
		
		
		scrollPane.setViewportView(tableClient);
		tableClient.setBackground(Color.WHITE);
		

		txtid_client = new JTextField();
		txtid_client.setEditable(false);
		txtid_client.setColumns(10);
		txtid_client.setBounds(148, 143, 146, 26);
		contentPane.add(txtid_client);
		
		txtNom = new JTextField();
		txtNom.setBounds(148, 192, 146, 29);
		contentPane.add(txtNom);
		txtNom.setColumns(10);
		
	
		txtPrenom = new JTextField();
		txtPrenom.setBounds(148, 238, 146, 29);
		contentPane.add(txtPrenom);
		txtPrenom.setColumns(10);
		
		txtAdresse = new JTextField();
		txtAdresse.setColumns(10);
		contentPane.add(txtAdresse);
		txtAdresse.setBounds(148, 287, 146, 29);
		
		

		txtMail = new JTextField();
		txtMail.setColumns(10);
		contentPane.add(txtMail);
		txtMail.setBounds(148, 340, 146, 29);
		
		
		
		comboBoxPays = new JComboBox();
		this.comboBoxPays = new JComboBox<String>();
		comboBoxPays.setModel(new DefaultComboBoxModel<String>(new String[] {" ","Afghanistan", "Afrique du Sud", "Albanie",
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
				"R\u00E9publique Dominicaine", "R\u00E9publique tch\u00E8que", "Roumanie", "Royaume-Uni", "Russie", "Rwanda", 
				"Saint-Christophe-et-Ni\u00E9v\u00E8s", "Sainte-Lucie", "Saint-Marin", "Saint-Si\u00E8ge, ou leVatican", "Saint-Vincent-et-les Grenadines"
				, "Salomon", "Salvador", "Samoa occidentales", "Sao Tom\u00E9-et-Principe", "S\u00E9n\u00E9gal", "Seychelles", "Sierra Leone", 
				"Singapour", "Slovaquie", "Slov\u00E9nie", "Somalie", "Soudan", "Sri Lanka", "Su\u00E8de", "Suisse", "Suriname",
				"Swaziland", "Syrie", "Tadjikistan", "Tanzanie", "Tchad", "Tha\u00EFlande", "Togo", "Tonga", "Trinit\u00E9-et-Tobago",
				"Tunisie", "Turkm\u00E9nistan", "Turquie", "Tuvalu", "Ukraine", "Uruguay", "Vanuatu", "Venezuela", "Vi\u00EAt Nam",
				"Y\u00E9men", "Yougoslavie", "Za\u00EFre", "Zambie", "Zimbabwe"}));
		comboBoxPays.setBounds(148, 391, 146, 30);
		getContentPane().add(comboBoxPays);
		contentPane.add(comboBoxPays);

		
		JLabel lblId = new JLabel("Id");
		lblId.setFont(new Font("Georgia", Font.ITALIC, 17));
		lblId.setBounds(57, 144, 38, 20);
		contentPane.add(lblId);
		
		
		JLabel lblPays = new JLabel("Pays");
		lblPays.setFont(new Font("Georgia", Font.ITALIC, 17));
		lblPays.setBounds(57, 394, 69, 20);
		contentPane.add(lblPays);
		
		JLabel lblGestionClient = new JLabel("GESTION CLIENT");
		lblGestionClient.setFont(new Font("Georgia", Font.ITALIC, 27));
		lblGestionClient.setBounds(507, 11, 285, 44);
		contentPane.add(lblGestionClient);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(337, 57, 542, 9);
		contentPane.add(separator);
				
	}
	
	
	public static void showTable() {
		
		Database db = new Database();
		
		try {
			
			Connection con;
			con= db.db_connect();
			PreparedStatement userStmt = con.prepareStatement("SELECT id_client AS '#',nom AS 'Nom', prenom AS 'Prenom',adresse AS 'Adresse', mail AS 'Mail',pays AS 'Pays' FROM client");
			ResultSet rs = userStmt.executeQuery();
			tableClient.setModel(DbUtils.resultSetToTableModel(rs));
			
		}catch(Exception e) {
			
			System.out.print(e);
		
		}
	
		
	
	}
}