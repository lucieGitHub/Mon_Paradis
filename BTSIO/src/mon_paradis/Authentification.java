package mon_paradis;

import java.awt.EventQueue;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import mon_paradis.Database;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.UIManager;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JTextPane;
import java.awt.Toolkit;

public class Authentification {

	
	
	private JFrame frmLogicielDeMon;
	private JTextField txtUsername;
	private JTextField txtPassword;
	public String priviledge;
	public String myname;
	private JButton btnExit;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Authentification window = new Authentification();
					window.frmLogicielDeMon.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Authentification() {
		
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		///////////////////////////////////////////////////////////////////////
		frmLogicielDeMon = new JFrame();
		frmLogicielDeMon.setResizable(false);
		frmLogicielDeMon.setBackground(new Color(240, 240, 240));
		frmLogicielDeMon.getContentPane().setBackground(new Color(192, 192, 192));
		frmLogicielDeMon.setBounds(100, 100, 714, 516);
		frmLogicielDeMon.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frmLogicielDeMon.getContentPane().setLayout(null);
		
		JLabel lblUser = new JLabel("NOM DE L'UTILISATEUR");
		lblUser.setFont(new Font("Georgia", Font.ITALIC, 20));
		lblUser.setForeground(Color.BLACK);
		lblUser.setBounds(217, 131, 242, 23);
		frmLogicielDeMon.getContentPane().add(lblUser);
		
		JLabel lblPassword = new JLabel("MOT DE PASSE");
		lblPassword.setFont(new Font("Georgia", Font.ITALIC, 20));
		lblPassword.setForeground(Color.BLACK);
		lblPassword.setBounds(254, 226, 144, 28);
		frmLogicielDeMon.getContentPane().add(lblPassword);
		
		txtUsername = new JTextField();
	
		txtUsername.setBounds(232, 176, 207, 28);
		frmLogicielDeMon.getContentPane().add(txtUsername);
		txtUsername.setColumns(10);
		
		txtPassword = new JTextField();
		txtPassword.setColumns(10);
		txtPassword.setBounds(232, 270, 207, 28);
		frmLogicielDeMon.getContentPane().add(txtPassword);
		
		JButton btnLogin = new JButton("CONNEXION");
		btnLogin.setFont(new Font("Times New Roman", Font.ITALIC, 17));
		btnLogin.setBackground(UIManager.getColor("ToolBar.dockingForeground"));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String username = txtUsername.getText();
				String password = txtPassword.getText();
//				
//				System.out.print(username);
//				System.out.print(password);
				
				JLabel alertUser = new JLabel("");
				alertUser.setBounds(214, 65, 100, 14);
				frmLogicielDeMon.getContentPane().add(alertUser);
				
				JLabel alertPassword = new JLabel("");
				alertPassword.setBounds(204, 148, 100, 14);
				frmLogicielDeMon.getContentPane().add(alertPassword);
				
				////////////////Erase alert Message/////////////////
				txtUsername.addKeyListener(new KeyAdapter() {
					@Override
					public void keyReleased(KeyEvent e) {
						alertUser.setText("");
					}
				});
				txtPassword.addKeyListener(new KeyAdapter() {
					@Override
					public void keyReleased(KeyEvent e) {
						alertPassword.setText("");
					}
				});
				
				
				////////////////Validation/////////////////
				
				if(username.trim().length()==0 && password.trim().length()==0) {
					
					alertUser.setText("Username is empty");
					alertPassword.setText("Password is empty");
					JOptionPane.showMessageDialog(null,"BOTH FIELDS ARE NULL");
					
				} else if(username.trim().length()==0) {
					
					alertUser.setText("Username is empty");
				} else if(password.trim().length()==0) {
						
					alertPassword.setText("Password is empty");	
					
				}else {
					Database db = new Database();
					
					try{
						
						Connection con =db.db_connect();
						PreparedStatement stmt = con.prepareStatement(" SELECT * FROM user WHERE username=? AND password=? ");
						stmt.setString(1,username);
						stmt.setString(2,password);
						ResultSet rs = stmt.executeQuery(); 
						if (rs.next()) {
							
//							A REVOIR
							String role = rs.getString(4);
							priviledge = role;
 
							String prenom =rs.getString(3);
							JOptionPane.showMessageDialog(null, "Bienvenue "+ prenom);
							myname = prenom;
							
								 frmLogicielDeMon.dispose();
								 Dashboard welcome = new Dashboard(priviledge, myname);
								 welcome.setVisible(true);
								 				
							}else {
							
							
							JOptionPane.showMessageDialog(null,"Identifiant ou mot de passe incorrect");
						}
		
						
					}catch(Exception error) {
						System.out.print(error);
						
					}
								
				}		
			}
		});
		btnLogin.setBounds(354, 365, 133, 60);
		frmLogicielDeMon.getContentPane().add(btnLogin);
		
		  btnExit = new JButton("QUITTER");
		  btnExit.setFont(new Font("Times New Roman", Font.ITALIC, 17));
		  btnExit.setBackground(new Color(240, 240, 240));
		  btnExit.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent e) { //quitter l'application
		  		System.exit(0);
		  	}
		  });
		
		
		btnExit.setBackground(UIManager.getColor("ToolBar.dockingForeground"));
		btnExit.setBounds(199, 365, 130, 60);
		frmLogicielDeMon.getContentPane().add(btnExit);
		
		JLabel lblNewLabel_1 = new JLabel("H\u00D4TEL MON PARADIS");
		lblNewLabel_1.setForeground(new Color(255, 255, 0));
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 27));
		lblNewLabel_1.setBounds(199, 24, 305, 47);
		frmLogicielDeMon.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\aniel\\eclipse-workspace\\BTSIO\\img\\login.jpg"));
		lblNewLabel.setBounds(0, 0, 708, 487);
		frmLogicielDeMon.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setBounds(109, 82, 46, 14);
		frmLogicielDeMon.getContentPane().add(lblNewLabel_2);
		

		//////////////////////////////////////////////////////////////////////////////
		
	}
}
