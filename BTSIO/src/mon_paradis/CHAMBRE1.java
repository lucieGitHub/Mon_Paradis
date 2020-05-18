//
//package mon_paradis;
//
//import java.awt.BorderLayout;
//import java.awt.EventQueue;
//
//
//
//import javax.swing.JFrame;
//import javax.swing.JPanel;
//import javax.swing.border.EmptyBorder;
//import java.sql.Connection;
// 
//
//import net.proteanit.sql.DbUtils;
//
//import javax.swing.JLabel;
//import javax.swing.JOptionPane;
//import javax.swing.DefaultComboBoxModel;
//import javax.swing.JButton;
//import javax.swing.JComboBox;
//
//import java.awt.Color;
//import java.awt.event.ActionListener;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.awt.event.ActionEvent;
//import javax.swing.JTextField;
//import javax.swing.JTable;
//import javax.swing.JTextPane;
//import javax.swing.JScrollPane;
//import java.awt.event.MouseEvent;
//import java.awt.event.MouseAdapter;
//import javax.swing.JComboBox.*;
//import java.awt.event.KeyAdapter;
//import java.awt.event.KeyEvent;
//import java.awt.SystemColor;
//import java.awt.Font;
//
//
//public class Chambre extends JFrame {
////	txtImg,txtSuperficie,txtCapacite,txtDescription,txtPrix
//	
//	private JPanel contentPane;
//	private JTextField txtid_chambre,txtImg,txtSuperficie,txtCapacite,txtDescription,txtPrix;
//	private JTextField txtIdChambre,txtid_categorie,txtimg,txtsup,txtcap,txtdes;
//
//	
//	private JTextField txtSearchingFor;
//	private JTextField txtSearch;
//	private static JTable tableChambre;
//	private JComboBox<String> comboBoxType,comboBoxSearch;
//	private String filterCriteria;
//
//	Database db = new Database();
//	private JTextField txtEtat;
//	
//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Chambre frame = new Chambre();
//					frame.setVisible(true);
//					showTable();
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//
//			private void showTable() {
//				// TODO Auto-generated method stub
//				
//			}
//		});
//	}
//
//	/**
//	 * Create the frame.
//	 */
//	public Chambre() {
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setBounds(300, 300, 1328, 700);
//		contentPane = new JPanel();
//		contentPane.setBackground(new Color(240, 248, 255));
//		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//		setContentPane(contentPane);
//		contentPane.setLayout(null);
////================================ DELETE ===========================================================			
//		JButton btnDelete = new JButton("Effacer");
//		btnDelete.setForeground(Color.RED);
//		btnDelete.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//				
//				String id_chambre = txtid_chambre.getText();
//				String id_categorie	= txtid_categorie.getText();
//				String image = txtImg.getText();
//				String superficie = txtSuperficie.getText();
//				String capacite = txtCapacite.getText();
//				String description = txtDescription.getText();
//				String prix = txtPrix.getText();
//				String value = comboBoxType.getSelectedItem().toString();
//				
//				
//				if( id_chambre.trim().length()==0 || id_categorie.trim().length()==0 || image.trim().length()==0 || superficie.trim().length()==0 || capacite.trim().length()==0 || description.trim().length()==0 || prix.trim().length()==0 ){
//		
//					
//					JOptionPane.showMessageDialog(null, "Remplissez les champs");
//
//			}else {
//				
//				try {
//					
//					
//					Connection con = db.db_connect();
//					PreparedStatement stmtCategorie = con.prepareStatement("DELETE FROM categorie WHERE id_categorie=?" );
//					stmtCategorie.setString(1,id_categorie);
//			 
//					stmtCategorie.executeUpdate();
//					JOptionPane.showMessageDialog(null,"Mise à jour effectué");
//					showTable();
//					
//					
//					
//					
//				}catch(Exception Up) {
//					System.out.print(Up);
//				}
//				
//			}
//			
//				 
//				
//			}
//		});
//		btnDelete.setBounds(245, 509, 122, 29);
//		contentPane.add(btnDelete);
////================================ NEW ===========================================================		
//		JButton btnAjouter = new JButton("Ajouter");
//		btnAjouter.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				
//				String id_chambre = txtid_chambre.getText();
//				String id_categorie  = txtid_categorie.getText();
//				String image = txtImg.getText();
//				String superficie = txtSuperficie.getText();
//				String capacite = txtCapacite.getText();
//				String description = txtDescription.getText();
//				String prix = txtPrix.getText();
//				String value = comboBoxType.getSelectedItem().toString();
//				
//				
////				===========================VAlidation===========
//				if( id_chambre.trim().length()==0 || id_categorie.trim().length()==0 || image.trim().length()==0 || superficie.trim().length()==0 || capacite.trim().length()==0 || description.trim().length()==0 || prix.trim().length()==0 ){
//		
//					
//					JOptionPane.showMessageDialog(null, "Remplissez les champs");
//
//			}
//			
//				 else if(image.trim().length()==0) {
//					
//					 JOptionPane.showMessageDialog(null,"Remplissez le champs de image");
//					
//				}else if(superficie.trim().length()==0) {
//				
//					JOptionPane.showMessageDialog(null,"Remplissez le champs de superficie");
//					
//				}else if(capacite.trim().length()==0) {
//					
//					
//					JOptionPane.showMessageDialog(null,"Remplissez le champs de capacite");
//					
//				
//				}else if(description.trim().length()==0) {
//					
//					
//					JOptionPane.showMessageDialog(null,"Remplissez le champs de description");
//					
//				
//				}else if(prix.trim().length()==0) {
//					
//					
//					JOptionPane.showMessageDialog(null,"Remplissez le champs de prix");
//					
//				
//				 
//					
//				
//				}else {
//					
//					try {
////=========================================Connexion pour la table Categorie============================================================================						
//			
//						
//						Connection con = db.db_connect();
//						
//                        
//                        PreparedStatement stmtCategorie = con.prepareStatement("INSERT INTO categorie (type,prix) values(?,?)",Statement.RETURN_GENERATED_KEYS);      
//                       
//                        stmtCategorie.setString(2,value);
//                        stmtCategorie.setString(1,prix);
//                        stmtCategorie.executeUpdate();
//                        ResultSet rs =stmtCategorie.getGeneratedKeys();
//                        
//                        if(rs.next()) {
//                        	
//                            id_categorie=rs.getString(1);
//
//                        
//                        
//                        
////==================================================Connexion pour la table Chambre=======================================================================================================================================================================                        
//
//			
//						Connection con = db.db_connect();
//			             PreparedStatement stmtChambre = con.prepareStatement("INSERT INTO chambre (id_chambre,img,superficie,capacite,description,id_categorie)VALUES(?,?,?,?,?,?) ");
//						stmtChambre.setString(1,id_chambre);
//						stmtChambre.setString(2,image);
//						stmtChambre.setString(3, superficie);
//						stmtChambre.setString(4, capacite);
//						stmtChambre.setString(5, description);
//						stmtChambre.executeUpdate();
//                        
//						JOptionPane.showMessageDialog(null,"Mise à jour effectué");
//						showTable();
//						
//					}catch(Exception Up) {
//						System.out.print(Up);
//					}
//				}
//				
//				
//			}
//		});
//		
////=============================REFRESH=================================================		
//		
//		JButton btnActualiser = new JButton("Actualiser");
//		btnActualiser.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) { 
//				txtid_chambre.setText("");
//				txtid_categorie.setText("");
//				txtImg.setText("");
//				txtSuperficie.setText("");
//				txtCapacite.setText("");
//				txtDescription.setText("");
//				txtPrix.setText("");
//			}
//				showTable();
//			
//			}
//		});
//		btnAjouter.setBounds(30, 581, 122, 29);
//		contentPane.add(btnAjouter);
//		
//		
//		btnActualiser.setBounds(1036, 32, 122, 29);
//		contentPane.add(btnActualiser);
////==============================================================END OF REFRESH=================================================================================	
//		
//		
//		
//		JLabel lblImg = new JLabel("Image");
//		lblImg.setFont(new Font("Georgia", Font.ITALIC, 17));
//		lblImg.setBounds(51, 101, 69, 29);
//		contentPane.add(lblImg);
//		
//		txtImg = new JTextField();
//		txtImg.setBounds(166, 99, 146, 29);
//		contentPane.add(txtImg);
//		txtImg.setColumns(10);
//		
//	
//		
//		JLabel lblSuperficie = new JLabel("Superficie");
//		lblSuperficie.setFont(new Font("Georgia", Font.ITALIC, 17));
//		lblSuperficie.setBounds(51, 154, 90, 25);
//		contentPane.add(lblSuperficie);
//		
//		JLabel lblPrix = new JLabel("Prix");
//		lblPrix.setFont(new Font("Georgia", Font.ITALIC, 17));
//		lblPrix.setBounds(51, 258, 69, 29);
//		contentPane.add(lblPrix);
//		
////=============================MODIFIER=================================================		
//				
//		JButton btnModifier = new JButton("Modif");
//		btnModifier.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				
//				String id_chambre = txtid_chambre.getText();
//				String image = txtImg.getText();
//				String superficie = txtSuperficie.getText();
//				String capacite = txtCapacite.getText();
//				String description = txtDescription.getText();
//				String id_categorie  = txtidCategorie.getText();
//				String prix = txtPrix.getText();
//				String value = comboBoxType.getSelectedItem().toString();
////==============================================================================================================================================================				
//				
////=======================================VALIDATION============================================================================================================
//				if(	id_chambre.trim().length()==0 || id_categorie.trim().length()==0 || image.trim().length()==0 || superficie.trim().length()==0 || capacite.trim().length()==0 || description.trim().length()==0 || prix.trim().length()==0 ){
//		
//					
//	
//						JOptionPane.showMessageDialog(null, "Remplissez les champs");
//					
//					}
//					
//					 else if(image.trim().length()==0) {
//						
//						 JOptionPane.showMessageDialog(null,"Remplissez le champs de image");
//						
//					}else if(superficie.trim().length()==0) {
//					
//						JOptionPane.showMessageDialog(null,"Remplissez le champs de superficie");
//						
//					}else if(capacite.trim().length()==0) {
//						
//						
//						JOptionPane.showMessageDialog(null,"Remplissez le champs de capacite");
//						
//					
//					}else if(description.trim().length()==0) {
//						
//						
//						JOptionPane.showMessageDialog(null,"Remplissez le champs de description");
//						
//					
//					}else if(prix.trim().length()==0) {
//						
//						
//						JOptionPane.showMessageDialog(null,"Remplissez le champs de prix");
//
//						
//					}else {
//						
//						try {
////=========================================Connexion pour la table Categorie============================================================================
//							Connection con = db.db_connect();
//							
//	                        
//	                        PreparedStatement stmtCategorie = con.prepareStatement("UPDATE categorie prix=?,type=? where id_categorie=?",Statement.RETURN_GENERATED_KEYS);      
//	                       
//	                        stmtCategorie.setString(2,value);
//	                        stmtCategorie.setString(1,prix);
//	                        stmtCategorie.executeUpdate();
//	                        ResultSet rs =stmtCategorie.getGeneratedKeys();
//	                        
//	                        if(rs.next()) {
//	                        	
//	                             id_categorie= rs.getString(1);
//	                             
////=========================================Connexion pour la table Chambre============================================================================            						
//							Connection con = db.db_connect();
//							PreparedStatement stmtChambre  = con.prepareStatement("update chambre set  id_categorie='%d',id_chambre='%d',img='%s', superficie='%s',capacite='%s', description='%s', prix='%s' where id_chambre='%d'");
//							stmtChambre.setString(1,id_categorie);
//							stmtChambre.setString(2,id_chambre);
//							stmtChambre.setString(3,image);
//							stmtChambre.setString(4, superficie);
//							stmtChambre.setString(5, capacite);
//							stmtChambre.setString(6, description);
//							stmtChambre.setString(8, value);
//							stmtChambre.setString(9, prix);
//							stmtChambre.executeUpdate();
//							JOptionPane.showMessageDialog(null,"Mise à jour effectué");
//							showTable();
//							
//							
//							
//							
//						}catch(Exception Up) {
//							System.out.print(Up);
//						}
//					}
//					
//					
//				}
//			});
//		
//		btnModifier.setBounds(30, 509, 122, 29);
//		contentPane.add(btnModifier);
//		
//		JLabel lblEnregistrer = new JLabel("Enregistrez-Vous!!");
//		lblEnregistrer.setFont(new Font("Georgia", Font.ITALIC, 15));
//		lblEnregistrer.setBounds(67, 17, 152, 20);
//		contentPane.add(lblEnregistrer);
//		
//		
//		JLabel lblSearch = new JLabel("Recherche");
//		lblSearch.setFont(new Font("Georgia", Font.ITALIC, 16));
//		lblSearch.setBounds(567, 34, 81, 20);
//		contentPane.add(lblSearch);
//		
//		txtCapacite= new JTextField();
//		txtCapacite.setColumns(10);
//		txtCapacite.setBounds(166, 316, 146, 29);
//		contentPane.add(txtCapacite);
//		
//		JLabel lblCapacite = new JLabel("Capacite");
//		lblCapacite.setFont(new Font("Georgia", Font.ITALIC, 17));
//		lblCapacite.setBounds(49, 318, 81, 27);
//		contentPane.add(lblCapacite);
//		
//		JLabel lblDescription = new JLabel("Description");
//		lblDescription.setFont(new Font("Georgia", Font.ITALIC, 17));
//		lblDescription.setBounds(51, 422, 101, 25);
//		contentPane.add(lblDescription);
//		
//		txtDescription = new JTextField();
//		txtDescription.setColumns(10);
//		txtDescription.setBounds(166, 422, 146, 29);
//		contentPane.add(txtDescription);
//		
//		//=============================RECHERCHE=========================================================		
//	 	this.comboBoxSearch =new JComboBox<String>();
//		comboBoxSearch.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//				
//			
//			Object selected =comboBoxSearch.getSelectedItem();
//			if(selected.toString().equals("Image"))
//				filterCriteria= "img";
//			
//			if(selected.toString().equals("Superficie"))
//				filterCriteria= "superficie";
//			
//			if(selected.toString().equals("Capacite"))
//				filterCriteria= "capacite";
//			
//			if(selected.toString().equals("Description"))
//				filterCriteria= "description";
//
//			if(selected.toString().equals("Prix"))
//				filterCriteria= "prix";
//			
//			if(selected.toString().equals("Privilege"))
//				filterCriteria= "type";
//			System.out.print(filterCriteria);
//	
//				
//			}
//		});
//		
//		comboBoxSearch.setModel(new DefaultComboBoxModel<String>(new String[]{" ","Image","Superficie","Capacite","Description","Type de Chambre","Prix","Privilege"}));
//		comboBoxSearch.setBounds(880, 31, 122, 26);
//		contentPane.add(comboBoxSearch);
//
//txtSearch = new JTextField();
//txtSearch.addKeyListener(new KeyAdapter() {
//	@Override
//	public void keyReleased(KeyEvent arg0) {
//	 
//	
//		try {
//			
//			String searchObject=txtSearch.getText();
//			Connection con;
//			con = db.db_connect();
//			PreparedStatement stmt = con.prepareStatement("SELECT  id_chambre as '#', img as 'Images' , type as 'Type de Chambre'"
//					+ " , prix as 'Prix', superficie as 'Superficie',capacite as 'Capacite',description as 'Description'  id_categorie as 'Id' FROM chambre inner join categorie ON chambre.id_categorie = categorie.id_categorie "+filterCriteria+" LIKE ? ");
//			stmt.setString(1,  "%" +searchObject + "%");
//	
//
//			ResultSet rs = stmt.executeQuery();
//			
//			
//			
//			
//			tableChambre.setModel(DbUtils.resultSetToTableModel(rs));//link database data to table
//			
//			
//			
//		}catch(Exception e1) {
//			
//			System.out.print(e1);
//			
////			JOptionPane.showMessageDialog(null, "Veuillez d'abord seletionner les critères");
//								
//		}
//	}
//});
////txtSearch.setText("searching for...");
//txtSearch.setBounds(682, 31, 126, 26);
//contentPane.add(txtSearch);
//
//		JScrollPane scrollPane = new JScrollPane();
//		
//		tableChambre = new JTable() {
//			
//			public boolean isCellEditable(int row,int column) {
//				return false;
//			}//cela permet de restrincte la partie d'editable....
//		};
//		
//		
//		
//		
//		tableChambre.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent arg0) {
//				
//			try {  
//				
//				int row = tableChambre.getSelectedRow();// nous permet de stocker des valeurs dans la variable row...
//				
////				System.out.print(row);
//				
//				String Click = (tableChambre.getModel().getValueAt(row, 0).toString());
//				
////				System.out.print(Click);
//				
//				
//
//				Connection con;
//				con= db.db_connect();
//				PreparedStatement stmt = con.prepareStatement(
//						"SELECT  FROM chambre inner join categorie ON chambre.id_categorie = categorie.id_categorie WHERE id_chambre=?'+Click+' ");
//				ResultSet rs = stmt.executeQuery();
//					if(rs.next()) {
//					
//					String data1 = rs.getString("id_chambre");
//					String data2 = rs.getString("img");
//					String data3 = rs.getString("superficie");
//					String data4 = rs.getString("capacite");
//					String data5 = rs.getString("description");
//					String data6 = rs.getString("type");
//					String data7 = rs.getString("prix");
//					
//					
//
//					
//					
//					System.out.println(data1);
//					System.out.println(data2);
//					System.out.println(data3);
//					System.out.println(data4);
//					System.out.println(data5);
//					System.out.println(data6);
//					System.out.println(data7);
//					System.out.println("_______________________________________________________________________________");
//					
//					
//					txtid_chambre.setText(data1);
//					txtImg.setText(data2);
//					txtSuperficie.setText(data3);
//					txtCapacite.setText(data4);
//					txtDescription.setText(data5);
//					txtPrix.setText(data7);
//		 
//					comboBoxType.setSelectedItem(data6);
//					
//					
//					
//				}
//				
//			}catch(Exception en) {
//				
//				System.out.print(en);
//				JOptionPane.showMessageDialog(null, en);
//				
//			}	
//				
//		
//			}// pour permettre de faire des actions sur ma table avec la souris
//		});
//		
//		
//		
//		
//		scrollPane.setBounds(553, 86, 703, 436);
//		contentPane.add(scrollPane);
//		
//		
//		
//		
//		
//		scrollPane.setViewportView(tableChambre);
//		tableChambre.setBackground(Color.WHITE);
//		
//		
//		
//		txtSuperficie = new JTextField();
//		txtSuperficie.setBounds(166, 150, 146, 29);
//		contentPane.add(txtSuperficie);
//		txtSuperficie.setColumns(10);
//		
//		txtPrix = new JTextField();
//		txtPrix.setColumns(10);
//		txtPrix.setBounds(166, 258, 146, 29);
//		contentPane.add(txtPrix);
//		
//		comboBoxType = new JComboBox();
//		comboBoxType.addItem("Chambre simple");
//		comboBoxType.addItem("Chambre double");
//		comboBoxType.addItem("Chambre familliale");
//		comboBoxType.addItem("Chambre triple");
//		comboBoxType.setBounds(166, 205, 146, 29);
//		comboBoxType.setEditable(false);
//		contentPane.add(comboBoxType);
//		
//		JLabel lblId = new JLabel("#");
//		lblId.setFont(new Font("Georgia", Font.ITALIC, 17));
//		lblId.setBounds(51, 48, 59, 25);
//		contentPane.add(lblId);
//		
//		txtid_chambre = new JTextField();
//		txtid_chambre.setEditable(false);
//		txtid_chambre.setColumns(10);
//		txtid_chambre.setBounds(166, 47, 146, 29);
//		contentPane.add(txtid_chambre);
//		
//		JLabel lblType = new JLabel("Type");
//		lblType.setFont(new Font("Georgia", Font.ITALIC, 17));
//		lblType.setBounds(51, 205, 69, 24);
//		contentPane.add(lblType);
//		
//		JButton button = new JButton("Deconnexion");
//		button.setBackground(SystemColor.menu);
//		button.setBounds(1180, 30, 122, 29);
//		contentPane.add(button);
//		
//		JLabel lblEtat = new JLabel("Etat");
//		lblEtat.setFont(new Font("Georgia", Font.ITALIC, 17));
//		lblEtat.setBounds(51, 376, 101, 25);
//		contentPane.add(lblEtat);
//		
//		txtEtat = new JTextField();
//		txtEtat.setColumns(10);
//		txtEtat.setBounds(166, 372, 146, 29);
//		contentPane.add(txtEtat);
//
//	}
//		protected static void showTable() {
//			// TODO Auto-generated method stub
//			
//		
//	public static void showTable() {
//		
//		Database db = new Database();
//		
//		try {
//			
//			Connection con;
//			con= db.db_connect();
//			PreparedStatement stmtChambre = con.prepareStatement("SELECT id_chambre AS '#',img AS 'Image', superficie AS 'Superficie', capacite AS 'capacite', description AS 'Description', type AS 'Type', prix AS 'Prix' FROM chambre");
//			ResultSet rs = stmtChambre.executeQuery();
//			tableChambre.setModel(DbUtils.resultSetToTableModel(rs));
//			
//		}catch(Exception e) {
//			
//			System.out.print(e);
//		
//		}
//	
//		
//	
//	}
//		}
//}