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
import javax.swing.UIManager;
import java.awt.Toolkit;
import javax.swing.JSeparator;


public class Chambre extends JFrame {
//	txtImg,txtSuperficie,txtCapacite,txtDescription,txtPrix
	
	private JPanel contentPane;
	private JTextField txtid_chambre,txtImg,txtSuperficie,txtCapacite,txtDescription,txtPrix,txtEtat;
	private JTextField txtSearchingFor;
	private JTextField txtSearch;
	private static JTable tableChambre;
	private JComboBox<String> comboBoxType,comboBoxSearch;
	private String filterCriteria;

	Database db = new Database();

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Chambre frame = new Chambre();
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
	public Chambre() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\aniel\\Documents\\BTS SIO 2020\\SCREENSHOT\\4.jpg"));
		setTitle("Logiciel de Mon Paradis");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 300, 1328, 700);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(175, 238, 238));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JButton btnExit = new JButton("Deconnexion");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//quitter l'application
				System.exit(0);
			}
		});
		btnExit.setFont(new Font("Times New Roman", Font.BOLD, 13));	
		btnExit.setBackground(SystemColor.menu);
		btnExit.setBounds(1165, 105, 122, 29);
		contentPane.add(btnExit);
			
		
//================================ DELETE ===========================================================			
		JButton btnDelete = new JButton("Effacer");
		btnDelete.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnDelete.setForeground(Color.RED);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String id_chambre = txtid_chambre.getText();
				String image = txtImg.getText();
				String superficie = txtSuperficie.getText();
				String capacite = txtCapacite.getText();
				String description = txtDescription.getText();
				String prix = txtPrix.getText();
				String etat = txtEtat.getText();
				String type = comboBoxType.getSelectedItem().toString();
				
				
if( image.trim().length()==0 || superficie.trim().length()==0 || capacite.trim().length()==0 || description.trim().length()==0 || prix.trim().length()==0 || etat.trim().length()==0 ){
		
					
					JOptionPane.showMessageDialog(null, "Remplissez les champs");

			}else {
				
				try {
					
					
					Connection con = db.db_connect();
					PreparedStatement stmt = con.prepareStatement("DELETE FROM chambre WHERE id_chambre=?");
				 	stmt.setString(1,id_chambre);
			 
					stmt.executeUpdate();
					JOptionPane.showMessageDialog(null,"Mise à jour effectué");
					showTable();
					
					
				}catch(Exception Up) {
					System.out.print(Up);
				}		
			}	
			}
		});
		btnDelete.setBounds(133, 622, 132, 51);
		contentPane.add(btnDelete);
//=================================================== NEW ==================================================================================================//		
		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				

				String id_chambre = txtid_chambre.getText();
				String image = txtImg.getText();
				String superficie = txtSuperficie.getText();
				String capacite = txtCapacite.getText();
				String description = txtDescription.getText();
				String prix = txtPrix.getText();
				String type = comboBoxType.getSelectedItem().toString();
				String etat = txtEtat.getText();
				
				
//=================================================== VALIDATION ===========================================================================================//
if( image.trim().length()==0 && superficie.trim().length()==0 && capacite.trim().length()==0 && description.trim().length()==0 &&  prix.trim().length()==0 && etat.trim().length()==0){
		
					
					JOptionPane.showMessageDialog(null, "Remplissez les champs");

			}
			
				 else if(image.trim().length()==0) {
					
					 JOptionPane.showMessageDialog(null,"Remplissez le champs image");
					
				}else if(superficie.trim().length()==0) {
				
					JOptionPane.showMessageDialog(null,"Remplissez le champs superficie");
					
				}else if(capacite.trim().length()==0) {
					
					
					JOptionPane.showMessageDialog(null,"Remplissez le champs capacite");
					
				}else if(description.trim().length()==0) {
					
					
					JOptionPane.showMessageDialog(null,"Remplissez le champs de description");
					
				
				}else if(prix.trim().length()==0) {
					
					
					JOptionPane.showMessageDialog(null,"Remplissez le champs de prix");
					
				}else if(etat.trim().length()==0) {
					
					
					JOptionPane.showMessageDialog(null,"Remplissez le champs de etat");
					
				
			
					
				
				}else {
					
					try {
						
						
						Connection con = db.db_connect();
						PreparedStatement stmt = con.prepareStatement("INSERT INTO  chambre (img,superficie,capacite,description,prix,type,etat)VALUES(?,?,?,?,?,?,?)");
					 	stmt.setString(1,image);
						stmt.setString(2, superficie);
						stmt.setString(3, capacite);
						stmt.setString(4, description);
						stmt.setString(5, prix);
						stmt.setString(6, type);
						stmt.setString(7, etat);
						stmt.executeUpdate();
						JOptionPane.showMessageDialog(null,"Mise à jour effectué");
						showTable();
						
					}catch(Exception Up) {
						System.out.print(Up);
					}
				}
			}
		});


//============================================================= REFRESH ====================================================================================//		
		btnAjouter.setBounds(240, 550, 132, 50);
		contentPane.add(btnAjouter);
		
		JButton btnActualiser = new JButton("Actualiser");
		btnActualiser.setFont(new Font("Times New Roman", Font.BOLD, 13));
		btnActualiser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				showTable();
				txtid_chambre.setText("");
				
			}
		});
		
		
		btnActualiser.setBounds(884, 103, 122, 29);
		contentPane.add(btnActualiser);
		
		JLabel lblImg = new JLabel("Image");
		lblImg.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblImg.setBounds(51, 185, 69, 29);
		contentPane.add(lblImg);
		
		txtImg = new JTextField();
		txtImg.setEditable(false);
		txtImg.setBounds(166, 187, 146, 29);
		contentPane.add(txtImg);
		txtImg.setColumns(10);
		
	
		
		JLabel lblSuperficie = new JLabel("Superficie");
		lblSuperficie.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblSuperficie.setBounds(51, 231, 90, 25);
		contentPane.add(lblSuperficie);
		
		JLabel lblPrix = new JLabel("Prix");
		lblPrix.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblPrix.setBounds(51, 370, 69, 29);
		contentPane.add(lblPrix);
		
		JButton btnModifier = new JButton("Modifier");
		btnModifier.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String id_chambre = txtid_chambre.getText();
				String image = txtImg.getText();
				String superficie = txtSuperficie.getText();
				String capacite = txtCapacite.getText();
				String description = txtDescription.getText();
				String prix = txtPrix.getText();
				String etat = txtEtat.getText();
				String type = comboBoxType.getSelectedItem().toString();
				
				
//================================================================ VALIDATION ==============================================================================//
if( image.trim().length()==0 || superficie.trim().length()==0 || capacite.trim().length()==0 || description.trim().length()==0 || prix.trim().length()==0 || etat.trim().length()==0 ){
		
					
	
						JOptionPane.showMessageDialog(null, "Remplissez les champs");
					
					}
					
					 else if(image.trim().length()==0) {
						
						 JOptionPane.showMessageDialog(null,"Remplissez le champs de image");
						
					}else if(superficie.trim().length()==0) {
					
						JOptionPane.showMessageDialog(null,"Remplissez le champs de superficie");
						
					}else if(capacite.trim().length()==0) {
						
						
						JOptionPane.showMessageDialog(null,"Remplissez le champs de capacite");
						
					
					}else if(description.trim().length()==0) {
						
						
						JOptionPane.showMessageDialog(null,"Remplissez le champs de description");
						
					
					}else if(prix.trim().length()==0) {
						
						
						JOptionPane.showMessageDialog(null,"Remplissez le champs de prix");
						
					
					}else if(etat.trim().length()==0) {
						
						
						JOptionPane.showMessageDialog(null,"Remplissez le champs de etat");
											
					}else {
						
						try {
				
							Connection con = db.db_connect();
							PreparedStatement stmt = con.prepareStatement
							("Update chambre set img=?,superficie=?, capacite=?,description=?, type=?,prix=?, etat=? WHERE id_chambre=?");
									
						 	stmt.setString(1, image);
							stmt.setString(2, superficie);
							stmt.setString(3, capacite);
							stmt.setString(4, description);
							stmt.setString(5, type);
							stmt.setString(6, prix);
							stmt.setString(7, etat);
							stmt.setString(8, id_chambre);
							stmt.executeUpdate();
							JOptionPane.showMessageDialog(null,"Mise à jour effectué");
							showTable();
							
							
							
							
						}catch(Exception Up) {
							System.out.print(Up);
						}
					}
					
					
				}
			});
		
		btnModifier.setBounds(30, 550, 132, 50);
		contentPane.add(btnModifier);
		
		JLabel lblEnregistrer = new JLabel("Enregistrez-Vous!!");
		lblEnregistrer.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblEnregistrer.setBounds(64, 75, 162, 29);
		contentPane.add(lblEnregistrer);
		
		
		JLabel lblSearch = new JLabel("Recherche");
		lblSearch.setFont(new Font("Georgia", Font.BOLD | Font.ITALIC, 16));
		lblSearch.setBounds(453, 105, 101, 20);
		contentPane.add(lblSearch);
		
		txtCapacite= new JTextField();
		txtCapacite.setColumns(10);
		txtCapacite.setBounds(166, 278, 146, 29);
		contentPane.add(txtCapacite);
		
		JLabel lblCapacite = new JLabel("Capacite");
		lblCapacite.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblCapacite.setBounds(51, 277, 81, 27);
		contentPane.add(lblCapacite);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblDescription.setBounds(51, 326, 111, 25);
		contentPane.add(lblDescription);
		
		txtDescription = new JTextField();
		txtDescription.setColumns(10);
		txtDescription.setBounds(166, 326, 146, 29);
		contentPane.add(txtDescription);
		
		//=============================SEARCH=========================================================		
	 	this.comboBoxSearch =new JComboBox<String>();
		comboBoxSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			
			Object selected =comboBoxSearch.getSelectedItem();
			if(selected.toString().equals("Image"))
				filterCriteria= "img";
			
			if(selected.toString().equals("Superficie"))
				filterCriteria= "superficie";
			
			if(selected.toString().equals("Capacite"))
				filterCriteria= "capacite";
			
			if(selected.toString().equals("Description"))
				filterCriteria= "description";

			if(selected.toString().equals("Prix"))
				filterCriteria= "prix";
			
			if(selected.toString().equals("Privilege"))
				filterCriteria= "type";
			System.out.print(filterCriteria);
			
			if(selected.toString().equals("Etat"))
				filterCriteria= "etat";
			System.out.print(filterCriteria);
	
				
			}
		});
		
		comboBoxSearch.setModel(new DefaultComboBoxModel<String>(new String[]{" ","Image","Superficie","Capacite","Description","Prix","Privilege","Etat"}));
		comboBoxSearch.setBounds(725, 104, 122, 26);
		contentPane.add(comboBoxSearch);

txtSearch = new JTextField();
txtSearch.addKeyListener(new KeyAdapter() {
	@Override
	public void keyReleased(KeyEvent arg0) {
	 
	
		try {
			
			String searchObject=txtSearch.getText();
			Connection con;
			con = db.db_connect();
			PreparedStatement stmt = con.prepareStatement("SELECT  id_chambre as '#', img as 'Image' , superficie as 'Superficie'"
					+ " , capacite as 'Capacite', description as 'Description', type as 'Type', prix as 'Prix' , etat as 'Etat' FROM chambre WHERE "+filterCriteria+" LIKE ? ");
			stmt.setString(1,  "%" +searchObject + "%");
			

			ResultSet rs = stmt.executeQuery();
			
			
			
			
			tableChambre.setModel(DbUtils.resultSetToTableModel(rs));//link database data to table
			
			
			
		}catch(Exception e1) {
			
			System.out.print(e1);
			
//			JOptionPane.showMessageDialog(null, "Veuillez d'abord seletionner les critères");
								
		}
	}
});
//txtSearch.setText("searching for...");
txtSearch.setBounds(564, 104, 126, 26);
contentPane.add(txtSearch);

		JScrollPane scrollPane = new JScrollPane();
		
		tableChambre = new JTable() {
			
			public boolean isCellEditable(int row,int column) {
				return false;
			}//cela permet de restrincte la partie d'editable....
		};
		
		
		
		
		tableChambre.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
			try {  
				
				int row = tableChambre.getSelectedRow();// nous permet de stocker des valeurs dans la variable row...
				
//				System.out.print(row);
				
				String Click = (tableChambre.getModel().getValueAt(row, 0).toString());
				
//				System.out.print(Click);
				
				

				Connection con;
				con= db.db_connect();
				PreparedStatement stmt = con.prepareStatement("SELECT * FROM chambre WHERE id_chambre='"+Click+"'");
				ResultSet rs = stmt.executeQuery();
				if(rs.next()) {
					
					String data1 = rs.getString("id_chambre");
					String data2 = rs.getString("img");
					String data3 = rs.getString("superficie");
					String data4 = rs.getString("capacite");
					String data5 = rs.getString("description");
					String data6 = rs.getString("type");
					String data7 = rs.getString("prix");
					String data8 = rs.getString("etat");
					
					

					
					
					System.out.println(data1);
					System.out.println(data2);
					System.out.println(data3);
					System.out.println(data4);
					System.out.println(data5);
					System.out.println(data6);
					System.out.println(data7);
					System.out.println(data8);
					System.out.println("_______________________________________________________________________________");
					
					
					txtid_chambre.setText(data1);
					txtImg.setText(data2);
					txtSuperficie.setText(data3);
					txtCapacite.setText(data4);
					txtDescription.setText(data5);
					comboBoxType.setSelectedItem(data6);
					txtPrix.setText(data7);
					txtEtat.setText(data8);
		 				
				}
				
			}catch(Exception en) {
				
				System.out.print(en);
				JOptionPane.showMessageDialog(null, en);
				
			}	
				
		
			}// pour permettre de faire des actions sur ma table avec la souris
		});
		
		
		
		
		scrollPane.setBounds(439, 146, 848, 464);
		contentPane.add(scrollPane);
		
		
		
		
		
		scrollPane.setViewportView(tableChambre);
		tableChambre.setBackground(Color.WHITE);
		
		
		
		txtSuperficie = new JTextField();
		txtSuperficie.setBounds(166, 231, 146, 29);
		contentPane.add(txtSuperficie);
		txtSuperficie.setColumns(10);
		
		txtPrix = new JTextField();
		txtPrix.setColumns(10);
		txtPrix.setBounds(166, 372, 146, 29);
		contentPane.add(txtPrix);
		
		comboBoxType = new JComboBox();
		comboBoxType.addItem("chambre simple");
		comboBoxType.addItem("chambre double");
		comboBoxType.addItem("chambre familliale");
		comboBoxType.addItem("chambre triple");
		comboBoxType.addItem("Chambre Simple");
		comboBoxType.addItem("Chambre Double");
		comboBoxType.addItem("Chambre Familiale");
		comboBoxType.addItem("Chambre Triple");
		comboBoxType.setBounds(166, 418, 146, 29);
		comboBoxType.setEditable(false);
		contentPane.add(comboBoxType);
		
		JLabel lblId = new JLabel("Ref");
		lblId.setFont(new Font("Times New Roman",Font.BOLD, 17));
		lblId.setBounds(51, 134, 59, 25);
		contentPane.add(lblId);
		
		txtid_chambre = new JTextField();
		txtid_chambre.setEditable(false);
		txtid_chambre.setColumns(10);
		txtid_chambre.setBounds(166, 140, 146, 29);
		contentPane.add(txtid_chambre);
		
		JLabel lblType = new JLabel("Type");
		lblType.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblType.setBounds(51, 418, 69, 24);
		contentPane.add(lblType);
		
	
		
		JLabel lblEtat = new JLabel("Etat");
		lblEtat.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblEtat.setBounds(51, 466, 101, 25);
		contentPane.add(lblEtat);
		
		txtEtat = new JTextField();
		txtEtat.setColumns(10);
		txtEtat.setBounds(166, 466, 146, 29);
		contentPane.add(txtEtat);
		
		JLabel lblGestionChambres = new JLabel("GESTION CHAMBRES");
		lblGestionChambres.setFont(new Font("Georgia", Font.ITALIC, 27));
		lblGestionChambres.setBounds(514, 11, 311, 46);
		contentPane.add(lblGestionChambres);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(327, 56, 640, 20);
		contentPane.add(separator);

	}

public static void showTable() {
		
		Database db = new Database();
		
		try {
			
			Connection con;
			con= db.db_connect();
			PreparedStatement chambreStmt = con.prepareStatement("SELECT id_chambre,img,superficie ,capacite ,description ,prix,type, etat FROM chambre");
			ResultSet rs = chambreStmt.executeQuery();
			tableChambre.setModel(DbUtils.resultSetToTableModel(rs));
			
		}catch(Exception e) {
			
			System.out.print(e);
		
		}
	
		
	
	}
}