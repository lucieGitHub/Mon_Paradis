package mon_paradis;


import java.sql.SQLException;
import java.awt.Toolkit;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.jdbc.Statement;
import com.toedter.calendar.JDateChooser;

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
import javax.swing.UIManager;
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

public class AssignerChambre {

	
	
	private JFrame frame;
	private JTextField txtid_reservation;
	private JTextField txtid_client;
	public String priviledge;
	public String myname;
	private JButton btnExit;
	private JLabel txtid_ref;
	private JTextField txtnom;
	private JTextField txtprenom;
	private JComboBox<String> comboStatus;
	
	private String id_Client;
	private String id_Reserved;
    private String cnom;
	private String cprenom;
	
	private JComboBox<String> comboPlan;
	private JLabel txtnb_personnes;
	private Object dateD;
	private JDateChooser dateR;
	private JDateChooser dateA;
	
	Database db = new Database();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AssignerChambre window = new AssignerChambre();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AssignerChambre() {
		
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
				///////////////////////////////////////////////////////////////////////
				frame = new JFrame();
				frame.setBackground(new Color(240, 240, 240));
				frame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\aniel\\Desktop\\image1.jpg"));
				frame.getContentPane().setBackground(new Color(192, 192, 192));
				frame.setBounds(100, 100, 898, 461);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.getContentPane().setLayout(null);
				
				JLabel lblUser = new JLabel("Id_reservation");
				lblUser.setFont(new Font("Georgia", Font.ITALIC, 18));
				lblUser.setForeground(UIManager.getColor("menuText"));
				lblUser.setBounds(45, 71, 131, 23);
				frame.getContentPane().add(lblUser);
				
				JLabel lblPassword = new JLabel("Id_client");
				lblPassword.setFont(new Font("Georgia", Font.ITALIC, 20));
				lblPassword.setForeground(UIManager.getColor("menuPressedItemB"));
				lblPassword.setBounds(45, 120, 131, 23);
				frame.getContentPane().add(lblPassword);
				
				txtid_reservation = new JTextField();
			
				txtid_reservation.setBounds(203, 74, 134, 23);
				frame.getContentPane().add(txtid_reservation);
				txtid_reservation.setColumns(10);
				
				txtid_client = new JTextField();
				txtid_client.setColumns(10);
				txtid_client.setBounds(203, 123, 134, 23);
				frame.getContentPane().add(txtid_client);
				
				
//				BUTTON DELETE     /////
				JButton btnDelete = new JButton("Supprimer");
				btnDelete.setToolTipText("Supprimer");
				btnDelete.setBackground(new Color(72,138,153));
				btnDelete.setForeground(Color.WHITE);
				btnDelete.setBorder(null);
				btnDelete.setFont(new Font("Tahoma", Font.BOLD, 14));
//				btnDelete.setIcon(new ImageIcon(RoomPanel.class.getResource("/loginSystem/img/delete.png")));
				btnDelete.addActionListener(new ActionListener() {
				
				

				public void actionPerformed(ActionEvent arg0) {
				String idReservation = txtid_ref.getText();
				String dateReserv = ((JTextField)dateR.getDateEditor().getUiComponent()).getText();
				String dateArrive =((JTextField)dateA.getDateEditor().getUiComponent()).getText();
				String dateDepar =((JTextField)((JDateChooser) dateD).getDateEditor().getUiComponent()).getText();
				String nb_personnes =txtnb_personnes.getText();
//				String child =txtChild.getText();
				String plan =comboPlan.getSelectedItem().toString();
				String status =comboStatus.getSelectedItem().toString();

				if ( idReservation.trim().length() == 0 ||dateReserv.trim().length() == 0 || dateArrive.trim().length() == 0||
						dateDepar.trim().length() == 0|| nb_personnes.trim().length() == 0||
				 plan.trim().length()==0|| status.trim().length()==0 ) {
				JOptionPane.showMessageDialog(null,"Veuillez d'abord sélectionner une réservation ");
				return;
				}else {
				Database db= new Database();
				String idRes = txtid_ref.getText();

				try {
				Connection con;
				con =db.db_connect();

				PreparedStatement findIdClient=con.prepareStatement("Select id_client FROM reservation WHERE id_reservation=?");
				findIdClient.setString(1, idRes);
				ResultSet rs = findIdClient.executeQuery();
				if(rs.next()) {
				String id=rs.getString("id_client");
				// System.out.print(id);

				PreparedStatement stmt = con.prepareStatement("DELETE FROM RESERVATION WHERE id_reservation=?");
				stmt.setString(1, idRes);
				stmt.executeUpdate();
				  refresh();

				PreparedStatement stmt2 = con.prepareStatement("DELETE FROM client WHERE id_client=?");
				stmt2.setString(1, id);
				stmt2.executeUpdate();
				JOptionPane.showMessageDialog(null,"Suppression effectuée");
				refresh();

				}


				 
				} catch (ClassNotFoundException e) {

				JOptionPane.showMessageDialog(null,e );
				e.printStackTrace();
				} catch (SQLException e) {
				JOptionPane.showMessageDialog(null,"Operation impossible car ce client a déja une/des chambre(s) de réserver");
				System.out.println(e);
				e.printStackTrace();
				}
				showTable();}
				}

				private void showTable() {
					// TODO Auto-generated method stub
					
				}
				});

				btnDelete.setBounds(198, 478, 149, 30);
				add(btnDelete);

				
				
		/////		ASSIGNER UNE CHAMBRE    /////
				   
				JButton btnAssigner = new JButton("Voir/Assigner Chambre");
				btnAssigner.setForeground(new Color(255, 255, 255));
				btnAssigner.setBackground(new Color(72,138,153));
				btnAssigner.setFont(new Font("Tahoma", Font.BOLD, 12));
				btnAssigner.addActionListener(new ActionListener() {
				
		

				public void actionPerformed(ActionEvent e) {
				String idRes = txtid_ref.getText();
				String status = comboStatus.getSelectedItem().toString();
				// System.out.print(status);
		
				if (idRes.trim().length()== 0) {
				JOptionPane.showMessageDialog(null,"Cliquez sur une réservation d'abord SVP");
				}
				if(status.equals("Non Confirmé")){
				JOptionPane.showMessageDialog(null,"Veuillez d'abord confirmer la réservation SVP");
				}else
		
					
				try {
		
		
				Connection con = db.db_connect();
				PreparedStatement stmt = con.prepareStatement("SELECT client.id_client,reservation.id_reservation,nom,prenom FROM reservation INNER JOIN client on reservation.id_client=client.id_client WHERE  id_reservation=? ");
				stmt.setString(1, idRes);
				ResultSet rs = stmt.executeQuery();
		
				if(rs.next()) {
				String idc =rs.getString(1);
				String idr =rs.getString(2);
				String nom =rs.getString(3);
				String prenom =rs.getString(4);
		
				id_Client = rs.getString(1);
				id_Reserved = rs.getString(2);
				cnom = rs.getString(3);
				cprenom = rs.getString(4);
		
				AssignerChambre assign = new AssignerChambre();
		//		(id_Reserved,id_Client,cnom, cprenom);
				assign.setVisible(true);
				       
				}
				else
				JOptionPane.showMessageDialog(null, "Nom d'utilisateur ou mot de passe non valide");
				con.close();
				}catch(Exception e1){System.out.print(e1);}
				}
				});
				btnAssigner.setBounds(379, 79, 213, 35);
				add(btnAssigner);
				
				//////////////////////////////////////////////////////////////////////////////
				
			}

	protected void setVisible(boolean b) {
		// TODO Auto-generated method stub
		
	}

	protected void refresh() {
		// TODO Auto-generated method stub
		
	}

	private void add(JButton btnAssigner) {
		// TODO Auto-generated method stub
		
	}
		}
