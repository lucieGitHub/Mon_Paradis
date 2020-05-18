package mon_paradis;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import net.proteanit.sql.DbUtils;

import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.AbstractButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import com.toedter.calendar.JDateChooser;
import javax.swing.UIManager;
import java.awt.Toolkit;

public class Reservation extends JFrame {

	private JPanel contentPane;
	private JTextField txtId;
	private JTextField txtnb_personnes,txtId_client,txtId_chambre,txtSearch;
	private JDateChooser dateA;
	private JDateChooser dateD;
	private JDateChooser dateReserv;
	protected String id_chambre;
	protected String id_client;
	private static JTable tableReser;
	
	private JComboBox<String> comboBoxSearch;
	private String filterCriteria;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Reservation frame = new Reservation();
					frame.setVisible(true);
					showTable();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	Database db = new Database();
	
	public void refreshTable() {
		try {
			Connection con;
			 con = (Connection) db.db_connect();
			PreparedStatement reserStmt = con.prepareStatement("Select id_reservation,date_reservation, date_arrivee,date_depart,nb_personnes,id_chambre,id_client  from reservation");
			ResultSet rs = reserStmt.executeQuery();
		
	reserStmt.close();
	rs.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * Create the frame.
	 */
	public Reservation() {
		setTitle("Logiciel de Mon Paradis");
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\aniel\\eclipse-workspace\\BTSIO\\img\\logo.jpg"));
		setBackground(new Color(255, 248, 220));
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 1317, 813);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(175, 238, 238));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnExit = new JButton("Deconnexion");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Pour quitter l'application
				System.exit(0);
			}
		});
		btnExit.setFont(new Font("Times New Roman", Font.BOLD, 16));	
//		btnExit.setBackground(SystemColor.menu);
		btnExit.setBounds(1148, 135, 131, 50);
		contentPane.add(btnExit);
			
	
		
		
//		1135, 140, 137, 44
		JSeparator separator = new JSeparator();
		separator.setBounds(296, 70, 704, 2);
		contentPane.add(separator);
		
		JLabel lblGestion = new JLabel("GESTION DE RESERVATION");
		lblGestion.setForeground(Color.BLACK);
		lblGestion.setFont(new Font("Georgia", Font.ITALIC, 29));
		lblGestion.setBounds(444, 22, 387, 37);
		contentPane.add(lblGestion);
		
		JLabel lblId = new JLabel("Ref");
		lblId.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblId.setBounds(46, 161, 83, 29);
		contentPane.add(lblId);
		
		JLabel lbldateReserv = new JLabel("Date Reservation");
		lbldateReserv.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lbldateReserv.setBounds(40, 222, 160, 29);
		contentPane.add(lbldateReserv);
		
		
		JLabel lbldateA = new JLabel("Date Arrivee");
		lbldateA.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lbldateA.setBounds(40, 279, 120, 25);
		contentPane.add(lbldateA);
		
		JLabel lbldateD = new JLabel("Date Depart");
		lbldateD.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lbldateD.setBounds(40, 331, 120, 29);
		contentPane.add(lbldateD);
		
		JLabel lblnb_personnes = new JLabel("Nombre Personnes:");
		lblnb_personnes.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblnb_personnes.setBounds(40, 378, 160, 37);
		contentPane.add(lblnb_personnes);
		

		JLabel lblId_chambre = new JLabel("Id Chambre");
		lblId_chambre.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblId_chambre.setBounds(40, 479, 131, 29);
		contentPane.add(lblId_chambre);
		
		JLabel lblId_client = new JLabel("Id Client:");
		lblId_client.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblId_client.setBounds(40, 431, 131, 27);
		contentPane.add(lblId_client);
		
		txtId = new JTextField();
		txtId.setEditable(false);
		txtId.setBounds(206, 165, 195, 27);
		contentPane.add(txtId);
		txtId.setColumns(10);
				
		JDateChooser dateReserv = new JDateChooser();
		dateReserv.setDateFormatString("MM-dd-yyyy");
		dateReserv.setBounds(206, 222, 195, 29);
		contentPane.add(dateReserv);
		
		JDateChooser dateA = new JDateChooser();
		dateA.setDateFormatString("MM-dd-yyyy");
		dateA.setBounds(206, 279, 195, 29);
		contentPane.add(dateA);
		
		JDateChooser dateD = new JDateChooser();
		dateD.setDateFormatString("MM-dd-yyyy");
		dateD.setBounds(206, 331, 195, 29);
		contentPane.add(dateD);
		
		txtnb_personnes = new JTextField();
		txtnb_personnes.setColumns(10);
		txtnb_personnes.setBounds(206, 382, 195, 27);
		contentPane.add(txtnb_personnes);
		
		txtId_chambre = new JTextField();
		txtId_chambre.setEditable(false);
		txtId_chambre.setColumns(10);
		txtId_chambre.setBounds(206, 483, 195, 29);
		contentPane.add(txtId_chambre);
		
		txtId_client = new JTextField();
		txtId_client.setEditable(false);
		txtId_client.setColumns(10);
		txtId_client.setBounds(206, 434, 195, 27);
		contentPane.add(txtId_client);
		

//======================================================= DELETE ===========================================================================================//
		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id_reservation =txtId.getText();
				String date_reservation =((JTextField)dateReserv.getDateEditor().getUiComponent()).getText();
				String date_arrivee =((JTextField)dateA.getDateEditor().getUiComponent()).getText();
				String date_depart =((JTextField)dateD.getDateEditor().getUiComponent()).getText();
				String nb_personnes = txtnb_personnes.getText();				
			   
				
				String id_chambre = txtId_chambre.getText();
				String id_client = txtId_client.getText();

				
if(date_reservation.trim().length()==0 || date_arrivee.trim().length()==0 || date_depart.trim().length()==0 || nb_personnes.trim().length()==0 || id_chambre.trim().length()==0 || id_client.trim().length()==0 ) {
					
					JOptionPane.showMessageDialog(null, "Remplissez tous les champs ");
			} else 
				
			{
				try {
					Connection con;
					 con =(Connection) db.db_connect();
					PreparedStatement stmt = con.prepareStatement("DELETE FROM reservation WHERE id_reservation=?");
					stmt.setString(1, id_reservation);
					stmt.execute();
					showTable();
					
					JOptionPane.showMessageDialog(null,"Form cancel");
					
				}catch(Exception e1) {
				}
			}
		}
		});
		btnSupprimer.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnSupprimer.setBounds(247, 562, 131, 49);
		contentPane.add(btnSupprimer);
		
		
		///////////////////////======================================MODIFIER=========================/////////////////////////////////////////////////////
		JButton btnModifier = new JButton("Modifier");
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				String id_reservation =txtId.getText();
				String date_reservation =((JTextField)dateReserv.getDateEditor().getUiComponent()).getText();
				String date_arrivee =((JTextField)dateA.getDateEditor().getUiComponent()).getText();
				String date_depart =((JTextField)dateD.getDateEditor().getUiComponent()).getText();
				String nb_personnes = txtnb_personnes.getText();				
			   
				String id_chambre = txtId_chambre.getText();
				String id_client = txtId_client.getText();

				
if(date_reservation.trim().length()==0 || date_arrivee.trim().length()==0 || date_depart.trim().length()==0 || nb_personnes.trim().length()==0 || id_chambre.trim().length()==0 || id_client.trim().length()==0 ) {
		
					JOptionPane.showMessageDialog(null, "Empty txt fields");
				} else {
					
					try {
						Connection con;
						con = (Connection) db.db_connect();
						PreparedStatement stmt = con.prepareStatement("UPDATE reservation set date_reservation=?,date_arrivee=?,date_depart=?,nb_personnes=?,id_chambre=?,id_client=? where id_reservation=?");
						stmt.setString(1, date_reservation);
						stmt.setString(2, date_arrivee);
						stmt.setString(3, date_depart);
						stmt.setString(4, nb_personnes);
					   
						stmt.setString(5, id_chambre);
						stmt.setString(6, id_client);
						stmt.setString(7, id_reservation);
						stmt.execute();
						showTable();
						JOptionPane.showMessageDialog(null,"Mise à jour effectué");
						
 
					}catch(Exception e1) {
						System.out.print(e1);
						
					}
				} 
				
			}
		});
		btnModifier.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnModifier.setBounds(23, 562, 137, 49);
		contentPane.add(btnModifier);
		
		
	
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(444, 196, 835, 427);
		contentPane.add(scrollPane);

//		//=============================================================================== RECHERCHE ===================================================================
//		comboBoxSearch.setModel(new DefaultComboBoxModel<String>(new String[]{" ","Nom","Prénom","Courriel","Nom de l'Utilisateur","Privilege"}));
//		comboBoxSearch.setBounds(882, 87, 122, 26);
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
//			con = (Connection) db.db_connect();
//			PreparedStatement stmt = con.prepareStatement("SELECT  id_reservation as '#', date_reservation as 'Date Reservation' , date_arrivee as 'Date Arrivee',"
//					+ "date_depart as 'date Depart', ns_personnes as 'Nombre Personnes', id_chambre as 'Id Chambre' FROM reservation WHERE "+filterCriteria+" LIKE ? ");
//			stmt.setString(1,  "%" +searchObject + "%");
//			
//
//			ResultSet rs = stmt.executeQuery();
//			
//			
//			
//			
//			tableReser.setModel(DbUtils.resultSetToTableModel(rs));//link database data to table
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
//txtSearch.setBounds(684, 86, 126, 26);
//contentPane.add(txtSearch);
//
//		
//		
		
		//////////////////////====================POUR CONFIGURER LA TABLE============================================//////////////////////////////////
		tableReser = new JTable();
		tableReser.setBackground(UIManager.getColor("InternalFrame.activeTitleGradient"));
		
		
		tableReser.addMouseListener(new MouseAdapter() {
		
			public void mouseClicked(MouseEvent e) {
				
				try {
					int row = tableReser.getSelectedRow();
					System.out.print(row); //////getSelectedRow() donne l`indice de chaque ranger
					
					String Clicktable = (tableReser.getModel().getValueAt(row, 0).toString());
//					System.out.print(Clicktable);
					
					
					Connection con;
					con = (Connection) db.db_connect();
					PreparedStatement callInfo = con.prepareStatement("SELECT * FROM reservation WHERE id_reservation='"+Clicktable+"'"); //////faire la concatenation avec le nom du variable clicktable
					ResultSet rs = callInfo.executeQuery();
					if(rs.next()) {
						
						String data1 = rs.getString("id_reservation");
						String data2 = rs.getString("date_reservation");
						String data3 = rs.getString("date_arrivee");
						String data4 = rs.getString("date_depart");
						String data5 = rs.getString("nb_personnes");
						String data6 = rs.getString("id_chambre");
						String data7 = rs.getString("id_client");
						

//						System.out.print(data2);
//			
						txtId.setText(data1);
						((JTextField)dateReserv.getDateEditor().getUiComponent()).setText(data2);
						((JTextField)dateA.getDateEditor().getUiComponent()).setText(data3);
						((JTextField)dateD.getDateEditor().getUiComponent()).setText(data4);
						txtnb_personnes.setText(data5);
						txtId_chambre.setText(data6);
						txtId_client.setText(data7);
		
					}
				}catch(Exception e1) {
					System.out.print(e1);
				}
				
			}
			
		});
		scrollPane.setViewportView(tableReser);
	
//========================================================== ACTUALISER ===================================================================================//
//		JButton btn = new JButton("Actualiser");
//		btn.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//				
//
//				showTable();
//				((JTextField)dateReserv.getDateEditor().getUiComponent()).setText(" ");
//				((JTextField)dateA.getDateEditor().getUiComponent()).setText(" ");
//				((JTextField)dateD.getDateEditor().getUiComponent()).setText(" ");
//				txtnb_personnes.setText(" ");
//				txtId_chambre.setText(" ");
//				txtId_client.setText(" ");
//				
//			}
//		});
//		btn.setBackground(UIManager.getColor("InternalFrame.activeTitleGradient"));
//		btn.setFont(new Font("Times New Roman", Font.BOLD, 16));
//		btn.setBounds(783, 141, 177, 29);
//		contentPane.add(btn);
//==========================================================FIN D'ACTUALISER ===============================================================================//		
		JButton btnNouveau = new JButton("Nouveau");
		btnNouveau.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try
				{
					reserverForm window = new reserverForm();
					window.frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}

			}
		});
		btnNouveau.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnNouveau.setBounds(135, 641, 131, 49);
		contentPane.add(btnNouveau);
		
	}
	
	
	
//================================== La fonction showTable ================================================================================================//
	
	public static void showTable() {
		
		Database db = new Database();
		
		try {
			Connection con;
			con = (Connection) db.db_connect();
			PreparedStatement userStmt = con.prepareStatement("SELECT id_reservation AS '#',date_reservation as 'DateReserv', date_arrivee as 'Arrivee', date_depart as 'Depart', nb_personnes as 'Nb_personnes',id_client as 'Id_Client', id_chambre as 'Id_Chambre' FROM reservation ");	

	    //Execution du query se fait ici//
		ResultSet rs = userStmt.executeQuery();
		tableReser.setModel(DbUtils.resultSetToTableModel(rs));
		
		}catch (Exception e) {
			System.out.print(e);
		}
}
}
