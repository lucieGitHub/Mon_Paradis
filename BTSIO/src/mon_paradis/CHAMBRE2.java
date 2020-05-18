package mon_paradis;


	import java.awt.BorderLayout;
	import java.awt.EventQueue;

	import javax.swing.JFrame;
	import javax.swing.JPanel;
	import javax.swing.border.EmptyBorder;

	import com.mysql.jdbc.Statement;

	import net.proteanit.sql.DbUtils;

	import javax.swing.JScrollBar;
	import javax.swing.JScrollPane;
	import javax.swing.JTable;
	import javax.swing.JLabel;
	import javax.swing.JOptionPane;
	import javax.swing.JButton;
	import javax.swing.JComboBox;
	import javax.swing.JTextField;
	import javax.swing.JTextPane;
	import javax.swing.SwingConstants;
	import javax.swing.DefaultComboBoxModel;
	import javax.swing.ImageIcon;
	import java.awt.Color;
	import java.awt.Font;
	import java.awt.Window;
	import java.awt.event.ActionListener;
	import java.awt.event.KeyAdapter;
	import java.awt.event.KeyEvent;
	import java.awt.event.MouseAdapter;
	import java.awt.event.MouseEvent;
	import java.sql.Connection;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.awt.event.ActionEvent;

	public class CHAMBRE2 extends JFrame {


		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private JPanel contentPane;
		private JTextField txtimg,txtsup;
		private JTextField txtSearchChambre;
		private static JTable tableChambre;
		private JTextField txtcap;
		private JComboBox<String> comboBoxType,comboBoxChambre;
		private JTextField txtIdChambre;
		private JTextField txtidCategorie;
		private String filterCriteria;
	    private JTextField txtPrix;
	    Database db = new Database();
	    private JTextField txtdes;
		
		
		
		
		
//		  Launch the application.
		 
		public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						CHAMBRE2 frame = new CHAMBRE2();
						frame.setVisible(true);
						showTable();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}

		
//		  Create the frame.
		 
		public CHAMBRE2() {
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(300, 300, 1328, 700);
			contentPane = new JPanel();
			contentPane.setBackground(new Color(220, 220, 220));
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);
			

			
			
			
	//================================ EFFACER ===========================================================			
			JButton btnDelete = new JButton("Effacer");
			btnDelete.setForeground(Color.RED);
			btnDelete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					String id_chambre 	= txtIdChambre.getText();
					String id_categorie	= txtidCategorie.getText();
					String images 	    = txtimg.getText();
					String superficie 	= txtsup.getText();
					String capacite 	= txtcap.getText();
					String description	= txtdes.getText();
					String prix 		= txtPrix.getText();
					String value	    = comboBoxType.getSelectedItem().toString();
					
					
					if( id_chambre.trim().length()==0 || id_categorie.trim().length()==0 || images.trim().length()==0 || superficie.trim().length()==0 || capacite.trim().length()==0 ||description.trim().length()==0 || prix.trim().length()==0 ||   value.trim().length()==0){
						
						
						JOptionPane.showMessageDialog(null," Remplissez les champs");

				}else {
					
					try {
						
						
						Connection con = db.db_connect();
						
						
						PreparedStatement stmt = con.prepareStatement("DELETE FROM categorie WHERE id_categorie=?" );
					 	stmt.setString(1,id_categorie);
				 
						stmt.executeUpdate();
						JOptionPane.showMessageDialog(null,"sucess");
						showTable();
						
						
						
						PreparedStatement stmt1 = con.prepareStatement("DELETE FROM chambre inner join categorie ON chambre.id_categorie = categorie.id_categorie WHERE id_chambre=?" );
					 	stmt.setString(1,id_chambre);
				 
						stmt.executeUpdate();
						JOptionPane.showMessageDialog(null,"Vos champs a été effacé");
						showTable();
						
						
						
					}catch(Exception D) {
						System.out.print(D);
					}
					
				}
				
					 
					
				}
			});
			
			
			btnDelete.setBounds(903, 544, 122, 29);
			contentPane.add(btnDelete);
	//================================ AJOUTER ===========================================================		
			JButton btnAjouter = new JButton("Ajouter");
			btnAjouter.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					String id_chambre    = txtIdChambre.getText();
					String id_categorie  = txtidCategorie.getText();
					String images 	    = txtimg.getText();
					String superficie	= txtsup.getText();
					String capacite 	= txtcap.getText();
					String description	= txtdes.getText();
					String prix	        = txtPrix.getText();
					String value 		= comboBoxType.getSelectedItem().toString();
					
					
	//================================Validation pour le bouton ajouter========================================================
	if( id_chambre.trim().length()==0 && id_categorie.trim().length()==0 && images.trim().length()==0 && superficie.trim().length()==0 && capacite.trim().length()==0 && description.trim().length()==0 && prix.trim().length()==0 &&  value.trim().length()==0 ){
			
						
						JOptionPane.showMessageDialog(null, "Remplissez les champs");

				}
				
					 else if(images.trim().length()==0) {
						
						 JOptionPane.showMessageDialog(null,"Remplissez le champs d'images");
						
					}else if(superficie.trim().length()==0) {
					
						JOptionPane.showMessageDialog(null,"Remplissez le champs de superficie");
						
					}else if(capacite.trim().length()==0) {
						
						
						JOptionPane.showMessageDialog(null,"Remplissez le champs de capacite");
						
						
						
		         }else if(description.trim().length()==0) {
						
						
						JOptionPane.showMessageDialog(null,"Remplissez le champs de description");
						
						
					
					}else if(prix.trim().length()==0) {
						
						
						JOptionPane.showMessageDialog(null,"Remplissez le champs de prix");
						
					
		 
						
					
					 
						
					
					}else {
						
						try {
							
	//=========================================Connexion pour la table Categorie============================================================================						
				
							
							Connection con = db.db_connect();
							
	                        
	                        PreparedStatement stmtCategorie = con.prepareStatement("INSERT INTO categorie (type,prix) values(?,?)",Statement.RETURN_GENERATED_KEYS);      
	                       
	                        stmtCategorie.setString(2,value);
	                        stmtCategorie.setString(1,prix);
	                        stmtCategorie.executeUpdate();
	                        ResultSet rs =stmtCategorie.getGeneratedKeys();
	                        
	                        if(rs.next()) {
	                        	
	                            id_categorie=rs.getString(1);

	                        
	                        
	                        
	//==================================================Connexion pour la table Chambre=======================================================================================================================================================================                        
							
	                        PreparedStatement stmtChambre = con.prepareStatement("INSERT INTO chambre (id_chambre,img,superficie,capacite,description,id_categorie)VALUES(?,?,?,?,?,?) ");
							stmtChambre.setString(1,id_chambre);
							stmtChambre.setString(2,images);
							stmtChambre.setString(3,superficie);
							stmtChambre.setString(4,capacite);
							stmtChambre.setString(5,description);
							stmtChambre.setString(5,id_categorie);
							stmtChambre.executeUpdate();
	                        }
	                        
							JOptionPane.showMessageDialog(null,"Vos données ont été bien envoyées");
							showTable();
							
						
						}catch(Exception Aj) {
							System.out.print(Aj);
						}
					}
					
					
					
					
					
				}
				
			});
			
			
			btnAjouter.setBounds(567, 544, 122, 29);
			contentPane.add(btnAjouter);
			
			
	//====================================REFRESH=================================================		
			
			JButton btnActualiser = new JButton("Actualiser");
			btnActualiser.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) { 
					txtIdChambre.setText("");
					txtidCategorie.setText("");
					txtimg.setText("");
					txtsup.setText("");
					txtcap.setText("");
					txtdes.setText("");
					txtPrix.setText("");
					showTable();
				}
			});
			
			
			btnActualiser.setBounds(722, 544, 122, 29);
			contentPane.add(btnActualiser);
	//===========================================================================================		
			
			
			JLabel lblimage = new JLabel("Images");
			lblimage.setBounds(20, 101, 137, 20);
			contentPane.add(lblimage);
			
			txtimg = new JTextField();
			txtimg.setBounds(197, 98, 146, 26);
			contentPane.add(txtimg);
			txtimg.setColumns(10);
			
		
			
			JLabel lblcapacite = new JLabel("Capacitie");
			lblcapacite.setBounds(21, 192, 69, 20);
			contentPane.add(lblcapacite);

	//====================================MODIFIER================================================
			
			JButton btnModifier = new JButton("Modifier");
			btnModifier.addActionListener(new ActionListener() {
				private String id_categorie;

				public void actionPerformed(ActionEvent e) {
				
					String id_chambre	= txtIdChambre.getText();
					String images	    = txtimg.getText();
					String superficie 	= txtsup.getText();
				    String capacite	    = txtcap.getText();
					String description	= txtdes.getText();
					String id_categorie  = txtidCategorie.getText();
					String prix 		= txtPrix.getText();
					String value		= comboBoxType.getSelectedItem().toString();
	//===========================================================================================
			
					

	//====================================Validation=============================================
				if(	id_chambre.trim().length()==0 && id_categorie.trim().length()==0 && images.trim().length()==0 && superficie.trim().length()==0 && capacite.trim().length()==0 && description.trim().length()==0 && prix.trim().length()==0 &&  value.trim().length()==0 ){
				
				
				JOptionPane.showMessageDialog(null, "Remplissez les champs");

		}
		
			 else if(images.trim().length()==0) {
				
				 JOptionPane.showMessageDialog(null,"Remplissez le champs d'images");
				
			}else if(superficie.trim().length()==0) {
			
				JOptionPane.showMessageDialog(null,"Remplissez le champs de superficie");
				
			}else if(capacite.trim().length()==0) {
				
				
				JOptionPane.showMessageDialog(null,"Remplissez le champs de capacite");
				
				
				
	     }else if(description.trim().length()==0) {
				
				
				JOptionPane.showMessageDialog(null,"Remplissez le champs de description");
				
				
			
			}else if(prix.trim().length()==0) {
				
				
				JOptionPane.showMessageDialog(null,"Remplissez le champs de prix");
				
			

				
					
					}else {
						
						try {
							
	//=========================================Connexion pour la table Categorie============================================================================						
				
							
	//=========================================Connexion pour la table Categorie============================================================================						
				
							
							Connection con = db.db_connect();
							
	                        
	                        PreparedStatement stmtCategorie = con.prepareStatement("UPDATE categorie prix=?,type=? where id_categorie=?",Statement.RETURN_GENERATED_KEYS);      
	                       
	                        stmtCategorie.setString(2,value);
	                        stmtCategorie.setString(1,prix);
	                        stmtCategorie.executeUpdate();
	                        ResultSet rs =stmtCategorie.getGeneratedKeys();
	                        
	                        if(rs.next()) {
	                        	
	                             id_categorie= rs.getString(1);

	                        
	                        
	                        
	//==================================================Connection pour la table Chambre=======================================================================================================================================================================                        
							
	                        PreparedStatement stmtChambre = con.prepareStatement("UPDATE chambre id_categorie=?,id_chambre=?,images=?, superficie=?,capacite=?,description=? WHERE id_chambre=?" );
							stmtChambre.setString(1,id_categorie);
							stmtChambre.setString(2,id_chambre);
							stmtChambre.setString(3,images);
							stmtChambre.setString(4,superficie);
							stmtChambre.setString(5,capacite);
							stmtChambre.setString(6,description);
							stmtChambre.executeUpdate();
	                        }
	                        
							JOptionPane.showMessageDialog(null,"Vos données ont été bien envoyées");
							showTable();
					
						
							
							
						}catch(Exception Mo) {
							System.out.print(Mo);
						}
					}
					
					
					
				}
			});
			btnModifier.setBounds(1062, 544, 122, 29);
			contentPane.add(btnModifier);
	//======================================================================================================
			
			
			
			
			JLabel lblEnregistrer = new JLabel("Enregistrez-Vous!!");
			lblEnregistrer.setBounds(53, 16, 152, 20);
			contentPane.add(lblEnregistrer);
			
			
			JLabel lblRecherche = new JLabel("Recherche");
			lblRecherche.setBounds(666, 34, 96, 20);
			contentPane.add(lblRecherche);
			
			JLabel lblType = new JLabel("Type");
			lblType.setBounds(21, 331, 81, 20);
			contentPane.add(lblType);
			
			JLabel lblprix = new JLabel("Prix");
			lblprix.setBounds(21, 379, 69, 20);
			contentPane.add(lblprix);
			
	//=====================================RECHERCHE=========================================================		
		 	this.comboBoxChambre =new JComboBox<String>();
			comboBoxChambre.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
				
				Object selected =comboBoxChambre.getSelectedItem();
				if(selected.toString().equals("Nom de la Chambre"))
					filterCriteria= "nomChambre";
				
				if(selected.toString().equals("Numéro de Chambre"))
					filterCriteria= "numChambre";
				
				if(selected.toString().equals("Type de Chambre"))
					filterCriteria= "typeChambre";
				
				
				if(selected.toString().equals("Prix"))
					filterCriteria= "Prix";
				
				if(selected.toString().equals("Superficie"))
					filterCriteria= "superficie";
				System.out.print(filterCriteria);
		
					
				}
			});
			
			comboBoxChambre.setModel(new DefaultComboBoxModel<String>(new String[]{" ","Images","Capacite","Type de Chambre ","Superficie","Prix"}));
			comboBoxChambre.setBounds(965, 31, 122, 26);
			contentPane.add(comboBoxChambre);

	txtSearchChambre = new JTextField();
	txtSearchChambre.addKeyListener(new KeyAdapter() {
		@Override
		public void keyReleased(KeyEvent arg0) {
		 
		
			try {
				Database db = new Database();
				String searchObject=txtSearchChambre.getText();
				Connection con;
				con = db.db_connect();
				PreparedStatement stmt = con.prepareStatement(
						"SELECT  id_chambre as '#', img as 'Images' , type as 'Type de Chambre'"
						+ " , prix as 'Prix', superficie as 'Superficie',capacite as 'Capacite',description as 'Description'  id_categorie as 'Id' FROM chambre inner join categorie ON chambre.id_categorie = categorie.id_categorie "+filterCriteria+" LIKE ? ");
				stmt.setString(1,  "%" +searchObject + "%");

				ResultSet rs = stmt.executeQuery();
				
				
				
				
				tableChambre.setModel(DbUtils.resultSetToTableModel(rs)); //link database data to table
				
				
				
			}catch(Exception s) {
				
				System.out.print(s);
				
				JOptionPane.showMessageDialog(null, "Veuillez d'abord seletionner les critères");
									
			}
		}
	});

	txtSearchChambre.setBounds(777, 28, 126, 26);
	contentPane.add(txtSearchChambre);




	//===========================================================================================================



			
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
					
					int row = tableChambre.getSelectedRow(); //nous permet de stocker des valeurs dans la variable row...
					
					System.out.print(row);
					
					String Click = (tableChambre.getModel().getValueAt(row, 0).toString());
					
					System.out.print(Click);
					
					

					Connection con;
					con= db.db_connect();
					PreparedStatement stmt = con.prepareStatement(
							"SELECT  FROM chambre inner join categorie ON chambre.id_categorie = categorie.id_categorie WHERE id_chambre=?'+Click+' ");
					ResultSet rs = stmt.executeQuery();
					if(rs.next()) {
						
						
						
						String data1 = rs.getString("id_chambre");
						String data2 = rs.getString("img");
						String data3 = rs.getString("superficie");
						String data4 = rs.getString("capacite");
						String data5 = rs.getString("description");
						String data6 = rs.getString("prix");
						String data7 = rs.getString("type");
						System.out.print(data7);
	 
						
						txtIdChambre.setText(data1);
						txtimg.setText(data2);
						txtsup.setText(data3);
						txtcap.setText(data4);
						txtdes.setText(data5);
						txtPrix.setText(data6);
						comboBoxType.setSelectedItem(data7);
						
						 
						
						
						
					}
					
					
					
					
				}catch(Exception en) {
					
					System.out.print(en);
					JOptionPane.showMessageDialog(null, en);
					
				}	
					
					
					
				
					
				}// pour permettre de faire des actions sur ma table avec la souris
			});
			
			
			
			
			scrollPane.setBounds(553, 86, 703, 436);
			contentPane.add(scrollPane);
			
			
			
			
			
			scrollPane.setViewportView(tableChambre);
			tableChambre.setBackground(Color.WHITE);
			
			
			
			
			
			
			
			

			
			txtcap = new JTextField();
			txtcap.setBounds(197, 189, 146, 26);
			contentPane.add(txtcap);
			txtcap.setColumns(10);
			
			JLabel lblnumchambre = new JLabel("Ref");
			lblnumchambre.setBounds(21, 52, 69, 20);
			contentPane.add(lblnumchambre);
			
			txtIdChambre = new JTextField();
			txtIdChambre.setEditable(false);
			txtIdChambre.setColumns(10);
			txtIdChambre.setBounds(197, 49, 146, 26);
			contentPane.add(txtIdChambre);
			comboBoxType = new JComboBox();
			comboBoxType.addItem(" Chambre Simple");
			comboBoxType.addItem("Chambre Double");
			comboBoxType.addItem("Chambre Triple");
			comboBoxType.addItem("Chambre Familiale");
			comboBoxType.setBounds(197, 328, 146, 26);
			contentPane.add(comboBoxType);
			
			JLabel lblsuperficie = new JLabel("Superficie");
			lblsuperficie.setBounds(21, 150, 146, 20);
			contentPane.add(lblsuperficie);
			
			txtsup = new JTextField();
			txtsup.setColumns(10);
			txtsup.setBounds(197, 147, 146, 26);
			contentPane.add(txtsup);
			
			txtPrix = new JTextField();
			txtPrix.setColumns(10);
			txtPrix.setBounds(197, 376, 146, 26);
			contentPane.add(txtPrix);
			
			JLabel lblidCategorie = new JLabel("idCategorie");
			lblidCategorie.setBounds(20, 278, 137, 20);
			contentPane.add(lblidCategorie);
			
			txtidCategorie = new JTextField();
			txtidCategorie.setEditable(false);
			txtidCategorie.setColumns(10);
			txtidCategorie.setBounds(197, 275, 146, 26);
			contentPane.add(txtidCategorie);
			
			JLabel lblDescription = new JLabel("Decription");
			lblDescription.setBounds(21, 241, 69, 14);
			contentPane.add(lblDescription);
			
			txtdes = new JTextField();
			txtdes.setBounds(197, 238, 146, 20);
			contentPane.add(txtdes);
			txtdes.setColumns(10);
			
	 

		
			
		}
		
		
		
		
		
		
		
		
		
		public static void showTable() {
			
			Database db = new Database();
			
			try {
				
				Connection con;
				con= db.db_connect();
				PreparedStatement chambreStmt = con.prepareStatement("SELECT id_chambre,img,superficie,capacite,description, chambre.id_categorie,type,prix FROM chambre inner join categorie ON chambre.id_categorie = categorie.id_categorie");
				ResultSet rs = chambreStmt.executeQuery();
				tableChambre.setModel(DbUtils.resultSetToTableModel(rs));
				
			}catch(Exception e) {
				
				System.out.print(e);
			
			}
		
			
		
		}
	}
