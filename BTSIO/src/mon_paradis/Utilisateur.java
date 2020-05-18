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
import javax.swing.JComboBox.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.SystemColor;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Window.Type;
import javax.swing.UIManager;
import javax.swing.JSeparator;
 
 
public class Utilisateur extends JFrame {

	private JPanel contentPane;
	private JTextField txtNom;
	private JTextField txtSearchingFor;
	private JTextField txtnom_utilisateur;
	private JTextField txtmot_de_passe;
	private JTextField txtSearch;
	private static JTable tableUtilisateur;
	private JTextField txtPrenom;
	private JTextField txtmail;
	private JComboBox<String> comboBoxRole,comboBoxSearch;
	private JTextField txtid_utilisateur;
	private String filterCriteria;

	Database db = new Database();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Utilisateur ame = new Utilisateur();
					ame.setVisible(true);
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
	public Utilisateur() {
		setForeground(new Color(240, 240, 240));
		setAlwaysOnTop(true);
		setTitle("Logiciel de Mon Paradis");
		setType(Type.POPUP);
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\aniel\\Documents\\BTS SIO 2020\\SCREENSHOT\\4.jpg"));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(300, 300, 1328, 700);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(175, 238, 238));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
				
//================================ DELETE ===========================================================			
		JButton btnDelete = new JButton("Effacer");
		btnDelete.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 16));
		btnDelete.setForeground(Color.RED);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String id_utilisateur = txtid_utilisateur.getText();
				String nom = txtNom.getText();
				String prenom = txtPrenom.getText();
				String nom_utilisateur = txtnom_utilisateur.getText();
				String mail = txtmail.getText();
				String mot_de_passe = txtmot_de_passe.getText();
				String role = comboBoxRole.getSelectedItem().toString();
				
				
if( nom.trim().length()==0 || prenom.trim().length()==0 || nom_utilisateur.trim().length()==0 || mail.trim().length()==0 || mot_de_passe.trim().length()==0 ){
		
					
					JOptionPane.showMessageDialog(null, "Remplissez les champs");

			}else {
				
				try {
					
					
					Connection con = db.db_connect();
					PreparedStatement stmt = con.prepareStatement("DELETE FROM utilisateur WHERE id_utilisateur=?");
				 	stmt.setString(1,id_utilisateur);
			 
					stmt.executeUpdate();
					JOptionPane.showMessageDialog(null,"Mise à jour effectué");
					showTable();
					
					
					
					
				}catch(Exception Up) {
					System.out.print(Up);
				}
				
			}
			
				 
				
			}
		});
		btnDelete.setBounds(143, 580, 132, 50);
		contentPane.add(btnDelete);
//================================ NEW ===========================================================		
		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 16));
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				

				String id_utilisateur = txtid_utilisateur.getText();
				String nom = txtNom.getText();
				String prenom = txtPrenom.getText();
				String nom_utilisateur = txtnom_utilisateur.getText();
				String mot_de_passe = txtmot_de_passe.getText();
				String mail = txtmail.getText();
				String role = comboBoxRole.getSelectedItem().toString();
				
				
//				===========================VAlidation===========
if( nom.trim().length()==0 && prenom.trim().length()==0 && nom_utilisateur.trim().length()==0 && mot_de_passe.trim().length()==0 &&  mail.trim().length()==0){
		
					
					JOptionPane.showMessageDialog(null, "Remplissez les champs");

			}
			
				 else if(nom.trim().length()==0) {
					
					 JOptionPane.showMessageDialog(null,"Remplissez le champs de nom");
					
				}else if(prenom.trim().length()==0) {
				
					JOptionPane.showMessageDialog(null,"Remplissez le champs prenom");
					
				}else if(nom_utilisateur.trim().length()==0) {
					
					
					JOptionPane.showMessageDialog(null,"Remplissez le champs d'utilisateur");
					
				}else if(mot_de_passe.trim().length()==0) {
					
					
					JOptionPane.showMessageDialog(null,"Remplissez le champs de password");
					
				
				}else if(mail.trim().length()==0) {
					
					
					JOptionPane.showMessageDialog(null,"Remplissez le champs de email");
					
				
			
					
				
				}else {
					
					try {
						
						
						Connection con = db.db_connect();
						PreparedStatement stmt = con.prepareStatement("INSERT INTO  utilisateur (nom,prenom,role,nom_utilisateur,mot_de_passe,mail)VALUES(?,?,?,?,?,?)");
					 	stmt.setString(1,nom);
						stmt.setString(2, prenom);
						stmt.setString(3, role);
						stmt.setString(4, nom_utilisateur);
						stmt.setString(5, mot_de_passe);
						stmt.setString(6, mail);
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
		btnAjouter.setBounds(42, 495, 122, 50);
		contentPane.add(btnAjouter);
		
		JButton btnActualiser = new JButton("Actualiser");
		btnActualiser.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnActualiser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				showTable();
				txtid_utilisateur.setText("");
				
			}
		});
		
		
		btnActualiser.setBounds(916, 86, 122, 29);
		contentPane.add(btnActualiser);
		
		JLabel lblnom = new JLabel("Nom");
		lblnom.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblnom.setBounds(30, 137, 69, 20);
		contentPane.add(lblnom);
		
		txtNom = new JTextField();
		txtNom.setBounds(155, 134, 146, 26);
		contentPane.add(txtNom);
		txtNom.setColumns(10);
		
	
		
		JLabel lblPrenom = new JLabel("Prenom");
		lblPrenom.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblPrenom.setBounds(30, 193, 59, 20);
		contentPane.add(lblPrenom);
		
		JLabel lblMail = new JLabel("Email");
		lblMail.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblMail.setBounds(30, 296, 69, 20);
		contentPane.add(lblMail);
		
		JButton btnModifier = new JButton("Modifier");
		btnModifier.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 16));
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String id_utilisateur = txtid_utilisateur.getText();
				String nom = txtNom.getText();
				String prenom = txtPrenom.getText();
				String nom_utilisateur = txtnom_utilisateur.getText();
				String mail = txtmail.getText();
				String mot_de_passe = txtmot_de_passe.getText();
				String role = comboBoxRole.getSelectedItem().toString();
				
				
//				===========================VAlidation===========
if( nom.trim().length()==0 && prenom.trim().length()==0 && nom_utilisateur.trim().length()==0 && mot_de_passe.trim().length()==0  && mail.trim().length()==0){
		
					
					JOptionPane.showMessageDialog(null, "Remplissez les champs");

			}
			
				 else if(nom.trim().length()==0) {
					
					 JOptionPane.showMessageDialog(null,"Remplissez le champs de nom");
					
				}else if(prenom.trim().length()==0) {
				
					JOptionPane.showMessageDialog(null,"Remplissez le champs de prenom");
					
								
				}else if(nom_utilisateur.trim().length()==0) {
					
					
					JOptionPane.showMessageDialog(null,"Remplissez le champs d'utilisateur");
						
					
				
				}else if(mot_de_passe.trim().length()==0) {
					
					
					JOptionPane.showMessageDialog(null,"Remplissez le champs de mot de passe");
					
					
				}else if(mail.trim().length()==0) {
					
					
					JOptionPane.showMessageDialog(null,"Remplissez le champs de email");
					
				
				 
					
				
				}else {
					
					try {
						
						
						Connection con = db.db_connect();
						PreparedStatement stmt = con.prepareStatement("Update utilisateur set nom=?,prenom=?, role=?,nom_utilisateur=?, mot_de_passe=?,mail=? WHERE id_utilisateur=?");
					 	stmt.setString(1, nom);
						stmt.setString(2, prenom);
						stmt.setString(3, role);
						stmt.setString(4, nom_utilisateur);
						stmt.setString(5, mot_de_passe);
						stmt.setString(6, mail);
						stmt.setString(7, id_utilisateur);
						stmt.executeUpdate();
						JOptionPane.showMessageDialog(null,"Votre table a été modifiée");
						showTable();
						
						
						
						
					}catch(Exception Mo) {
						System.out.print(Mo);
					}
				}
				
				
				
			}
		});
		btnModifier.setBounds(252, 495, 122, 50);
		contentPane.add(btnModifier);
		
		JLabel lblEnregistrer = new JLabel("GESTION UTILISATEUR");
		lblEnregistrer.setFont(new Font("Georgia", Font.ITALIC, 27));
		lblEnregistrer.setBounds(486, 0, 324, 50);
		contentPane.add(lblEnregistrer);
		
		
		JLabel lblSearch = new JLabel("Recherche");
		lblSearch.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblSearch.setBounds(475, 90, 69, 20);
		contentPane.add(lblSearch);
		
		txtnom_utilisateur = new JTextField();
		txtnom_utilisateur.setColumns(10);
		txtnom_utilisateur.setBounds(155, 344, 146, 26);
		contentPane.add(txtnom_utilisateur);
		
		JLabel lblUsername = new JLabel("Nom utilisateur");
		lblUsername.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblUsername.setBounds(30, 347, 122, 20);
		contentPane.add(lblUsername);
		
		JLabel lblPass = new JLabel("Mot de Passe");
		lblPass.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblPass.setBounds(30, 400, 92, 20);
		contentPane.add(lblPass);
		
		txtmot_de_passe = new JTextField();
		txtmot_de_passe.setColumns(10);
		txtmot_de_passe.setBounds(155, 400, 146, 26);
		contentPane.add(txtmot_de_passe);
		
		//=============================SEARCH=========================================================		
	 	this.comboBoxSearch =new JComboBox<String>();
		comboBoxSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			
			Object selected =comboBoxSearch.getSelectedItem();
			if(selected.toString().equals("Nom"))
				filterCriteria= "nom";
			
			if(selected.toString().equals("Prénom"))
				filterCriteria= "prenom";
			
			if(selected.toString().equals("Courriel"))
				filterCriteria= "mail";
			
			if(selected.toString().equals("Nom de l'Utilisateur"))
				filterCriteria= "nom_utilisateur";
			
			if(selected.toString().equals("Privilege"))
				filterCriteria= "role";
			System.out.print(filterCriteria);
	
				
			}
		});
		
		comboBoxSearch.setModel(new DefaultComboBoxModel<String>(new String[]{" ","Nom","Prénom","Courriel","Nom de l'Utilisateur","Privilege"}));
		comboBoxSearch.setBounds(749, 89, 122, 26);
		contentPane.add(comboBoxSearch);

txtSearch = new JTextField();
txtSearch.addKeyListener(new KeyAdapter() {
	@Override
	public void keyReleased(KeyEvent arg0) {
	 
	
		try {
			
			String searchObject=txtSearch.getText();
			Connection con;
			con = db.db_connect();
			PreparedStatement stmt = con.prepareStatement("SELECT  id_utilisateur as '#', nom as 'Nom' , prenom as 'Prénom',role as 'Role'"
					+ " , nom_utilisateur as 'Nom Utilisateur', mail as 'Mail' FROM utilisateur WHERE "+filterCriteria+" LIKE ? ");
			stmt.setString(1,  "%" +searchObject + "%");
			

			ResultSet rs = stmt.executeQuery();
			
			
			
			
			tableUtilisateur.setModel(DbUtils.resultSetToTableModel(rs));//link database data to table
			
			
			
		}catch(Exception e1) {
			
			System.out.print(e1);
			
//			JOptionPane.showMessageDialog(null, "Veuillez d'abord seletionner les critères");
								
		}
	}
});
//txtSearch.setText("searching for...");
txtSearch.setBounds(574, 88, 126, 26);
contentPane.add(txtSearch);

		JScrollPane scrollPane = new JScrollPane();
		
		tableUtilisateur = new JTable() {
			
			public boolean isCellEditable(int row,int column) {
				return false;
			}//cela permet de restrincte la partie d'editable....
		};
		
		
		
		
		tableUtilisateur.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
			try {  
				
				int row = tableUtilisateur.getSelectedRow();// nous permet de stocker des valeurs dans la variable row...
				
//				System.out.print(row);
				
				String Click = (tableUtilisateur.getModel().getValueAt(row, 0).toString());
				
//				System.out.print(Click);
				
				

				Connection con;
				con= db.db_connect();
				PreparedStatement stmt = con.prepareStatement("SELECT * FROM utilisateur WHERE id_utilisateur='"+Click+"'");
				ResultSet rs = stmt.executeQuery();
				if(rs.next()) {
					
					String data1 = rs.getString("id_utilisateur");
					String data2 = rs.getString("nom");
					String data3 = rs.getString("prenom");
					String data4 = rs.getString("role");
					String data5 = rs.getString("nom_utilisateur");
					String data6 = rs.getString("mot_de_passe");
					String data7 = rs.getString("mail");
					
					
					System.out.println(data1);
					System.out.println(data2);
					System.out.println(data3);
					System.out.println(data4);
					System.out.println(data5);
					System.out.println(data6);
					System.out.println(data7);
					System.out.println("_______________________________________________________________________________");
					
					txtid_utilisateur.setText(data1);
					txtNom.setText(data2);
					txtPrenom.setText(data3);
					comboBoxRole.setSelectedItem(data4);
					txtnom_utilisateur.setText(data5);
					txtmail.setText(data7);
					txtmot_de_passe.setText(data6);
				}
			}catch(Exception en) {
				
				System.out.print(en);
				JOptionPane.showMessageDialog(null, en);
				
			}	
				
			}// pour permettre de faire des actions sur ma table avec la souris
		});
		
		
		scrollPane.setBounds(452, 126, 834, 440);
		contentPane.add(scrollPane);
	
		scrollPane.setViewportView(tableUtilisateur);
		tableUtilisateur.setBackground(Color.WHITE);
			
		txtPrenom = new JTextField();
		txtPrenom.setBounds(155, 187, 146, 26);
		contentPane.add(txtPrenom);
		txtPrenom.setColumns(10);
		
		txtmail = new JTextField();
		txtmail.setColumns(10);
		txtmail.setBounds(155, 293, 146, 26);
		contentPane.add(txtmail);
		
		comboBoxRole = new JComboBox();
		comboBoxRole.addItem("administrateur");
		comboBoxRole.addItem("gestionnaire");
		comboBoxRole.setBounds(155, 238, 146, 26);
		comboBoxRole.setEditable(false);
		contentPane.add(comboBoxRole);
		
		JLabel lblId = new JLabel("Ref");
		lblId.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblId.setBounds(30, 90, 59, 20);
		contentPane.add(lblId);
		
		txtid_utilisateur = new JTextField();
		txtid_utilisateur.setEditable(false);
		txtid_utilisateur.setColumns(10);
		txtid_utilisateur.setBounds(155, 87, 146, 26);
		contentPane.add(txtid_utilisateur);
		
		JLabel lblRole = new JLabel("Role");
		lblRole.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblRole.setBounds(30, 241, 69, 20);
		contentPane.add(lblRole);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(363, 45, 597, 8);
		contentPane.add(separator);
		
		JButton button = new JButton("D\u00E9connexion");
		button.setFont(new Font("Times New Roman", Font.BOLD, 16));
		button.setBounds(1152, 67, 134, 43);
		contentPane.add(button);

	}
	
	public static void showTable() {
		
		Database db = new Database();
		
		try {
			
			Connection con;
			con= db.db_connect();
			PreparedStatement userStmt = con.prepareStatement("SELECT id_utilisateur AS '#',nom AS 'Nom', prenom AS 'Prenom', role AS 'Role',nom_utilisateur  AS 'Nom Utilisateur', mail AS 'Mail' FROM utilisateur");
			ResultSet rs = userStmt.executeQuery();
			tableUtilisateur.setModel(DbUtils.resultSetToTableModel(rs));
			
		}catch(Exception e) {
			
			System.out.print(e);
		
		}
	
		
	
	}
}