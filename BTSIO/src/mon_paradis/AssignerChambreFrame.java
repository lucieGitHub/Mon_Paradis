//package mon_paradis;
//
//import java.awt.Color;
//import java.awt.EventQueue;
//import java.awt.Font;
//import java.awt.event.MouseAdapter;
//import java.awt.event.MouseEvent;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//import javax.swing.JFrame;
//import javax.swing.JPanel;
//import javax.swing.JScrollPane;
//import javax.swing.JSeparator;
//import javax.swing.JTable;
//import javax.swing.border.BevelBorder;
//import javax.swing.border.EmptyBorder;
//
//import net.proteanit.sql.DbUtils;
//
//import javax.swing.JLabel;
//import javax.swing.JOptionPane;
//import javax.swing.JTextField;
//import javax.swing.ListSelectionModel;
//import javax.swing.SwingConstants;
//import javax.swing.JComboBox;
//import javax.swing.JButton;
//import java.awt.event.ActionListener;
//import java.awt.event.ActionEvent;
//import java.awt.SystemColor;
//
//
//public class AssignerChambreFrame extends JFrame {
//
///**
//*
//*/
//private static final long serialVersionUID = 1L;
//private JPanel contentPane;
//private JTextField txtid_reservation;
//private JTextField txtid_client;
//private JTextField txtnom_client;
//private JTextField txtprenom_client;
//private JComboBox<String> roomAvailableList;
//private JButton btnAssignerCetteChambre;
//private JTable tblConcerner;
//private String id_reservation, id_client, cnom, cprenom, id_chambre;
//private JTextField txtid_chambre;
//private JTextField txtmail;
// 
///**
//* Launch the application.
//*/
//public static void main(String[] args) {
//EventQueue.invokeLater(new Runnable() {
//public void run() {
//try {
//AssignerChambreFrame frame = new AssignerChambreFrame("","","","");
//frame.setVisible(true);
//} catch (Exception e) {
//e.printStackTrace();
//}
//}
//});
//}
//
///**
//* Create the frame.
//*/
//public AssignerChambreFrame(String id_reservation,String id_cient,String cnom,String cprenom) {
//setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
//setBounds(100, 100, 865, 619);
//contentPane = new JPanel();
//contentPane.setBackground(new Color(255, 255, 0));
//contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//setContentPane(contentPane);
//contentPane.setLayout(null);
//
//// System.out.println(idReserved);
//
//JPanel panel = new JPanel();
//panel.setBackground(new Color(255, 255, 0));
//panel.setBounds(10, 11, 885, 569);
//contentPane.add(panel);
//panel.setLayout(null);
//
//txtid_reservation = new JTextField();
//txtid_reservation.setText(id_reservation);
//txtid_reservation.setBounds(134, 85, 136, 27);
//txtid_reservation.setFont(new Font("Tahoma", Font.BOLD, 11));
//panel.add(txtid_reservation);
//txtid_reservation.setColumns(10);
//
//txtid_client = new JTextField();
//txtid_client.setText(id_client);
//txtid_client.setFont(new Font("Tahoma", Font.BOLD, 11));
//txtid_client.setBounds(134, 123, 136, 27);
//panel.add(txtid_client);
//txtid_client.setColumns(10);
//
//txtnom_client = new JTextField();
//txtnom_client.setText(cnom);
//txtnom_client.setFont(new Font("Tahoma", Font.BOLD, 11));
//txtnom_client.setBounds(134, 163, 136, 27);
//panel.add(txtnom_client);
//txtnom_client.setColumns(10);
//
//txtprenom_client = new JTextField();
//txtprenom_client.setText(cprenom);
//txtprenom_client.setFont(new Font("Tahoma", Font.BOLD, 11));
//txtprenom_client.setBounds(134, 207, 194, 27);
//panel.add(txtprenom_client);
//txtprenom_client.setColumns(10);
//
//JLabel lblid_reservation = new JLabel("ID R\u00E9servation :");
//lblid_reservation.setBounds(10, 78, 136, 39);
//lblid_reservation.setFont(new Font("Arial", Font.BOLD, 14));
//panel.add(lblid_reservation);
//
//JLabel lblid_client = new JLabel("ID Client :");
//lblid_client.setBounds(10, 128, 112, 14);
//lblid_client.setFont(new Font("Arial", Font.BOLD, 14));
//panel.add(lblid_client);
//
//JLabel lblNom = new JLabel("Nom :");
//lblNom.setBounds(12, 166, 112, 14);
//lblNom.setFont(new Font("Arial", Font.BOLD, 14));
//panel.add(lblNom);
//
//JLabel lblPrenom = new JLabel("Pr\u00E9nom :");
//lblPrenom.setBounds(10, 207, 112, 14);
//lblPrenom.setFont(new Font("Arial", Font.BOLD, 14));
//panel.add(lblPrenom);
//Database db =new Database();
//
//
//
//roomAvailableList = new JComboBox<String>();
//roomAvailableList.setBounds(151, 387, 145, 27);
//try {
//roomAvailableList.removeAllItems();
//
//Connection con;
//con = db.db_connect();
//PreparedStatement stmt = con.prepareStatement("SELECT numero FROM CHAMBRE WHERE status='libre' ");
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////        LE NUMERO ICI REPRESENTE QUOI????        ////////////////////////////////////////////
//ResultSet rs = stmt.executeQuery();
//while(rs.next())
//       {
//           this.roomAvailableList.addItem(rs.getString("numero"));
//       }
//con.close();
//rs.close();
//}catch(Exception e){}
//panel.add(roomAvailableList);
//
//btnAssignerCetteChambre = new JButton("Assigner Cette Chambre");
//btnAssignerCetteChambre.setBackground(new Color(72,138,153));
//btnAssignerCetteChambre.addActionListener(new ActionListener() {
//public void actionPerformed(ActionEvent arg0) {
//String idReservation =txtid_reservation.getText();
//// String idClient =txtIdClient.getText();
//String room =roomAvailableList.getSelectedItem().toString();
//try {
//Connection con;
//con = db.db_connect();
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//PreparedStatement stmt = con.prepareStatement("SELECT * FROM CHAMBRE WHERE numero=?");
//////////////////////////////////////////////////////////////////////////////////////////////////////////////
//stmt.setString(1, room);
//ResultSet rs = stmt.executeQuery();
//if(rs.next()) {
//
//String add1 =rs.getString("id_chambre");
//id_chambre=add1;
//}
//PreparedStatement stmt2 = con.prepareStatement("INSERT INTO CONCERNER (id_chambre,id_reservation) VALUES(?,?)");
///////////////////////////////////////////////////////CONCERNER EST UNE AUTRE TABLE?  /////////////////////////////////////////
//
/////////////////////////////////////////////////////////////
//stmt2.setString(1, id_chambre);
//stmt2.setString(2, id_reservation);
//stmt2.executeUpdate();
//showTable();
//refreshRoomList();
//
//}catch(Exception e) {System.out.print(e);};
//System.out.print(id_chambre);
//
//}
//
//});
//btnAssignerCetteChambre.setBounds(134, 425, 179, 39);
//panel.add(btnAssignerCetteChambre);
//
//JScrollPane scrollPane = new JScrollPane();
//scrollPane.setBounds(399, 84, 414, 383);
//panel.add(scrollPane);
//scrollPane.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
//
//tblConcerner = new JTable() {
//private static final long serialVersionUID = 1L;
//public boolean isCellEditable(int row,int column){  
//                 return false;  
//      }
//};
//tblConcerner.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
//tblConcerner.setBackground(Color.LIGHT_GRAY);
//tblConcerner.setFont(new Font("Tahoma", Font.BOLD, 11));
//tblConcerner.setRowHeight(30);
//tblConcerner.addMouseListener(new MouseAdapter() {
//@Override
//public void mouseClicked(MouseEvent e) {
//try {
//// String idReservation =txtIdReservation.getText();
//int row=tblConcerner.getSelectedRow();
//String tableClick =(tblConcerner.getModel().getValueAt(row, 0).toString());
//
//Connection con;
//con = db.db_connect();
//PreparedStatement stmt = con.prepareStatement("SELECT concerner.id_chambre,concerner.id_reservation, numero FROM CONCERNER INNER JOIN CHAMBRE ON CONCERNER.ID_CHAMBRE=CHAMBRE.ID_CHAMBRE WHERE CONCERNER.id_chambre='"+tableClick+ "'");
//ResultSet rs = stmt.executeQuery();
//
//
//
//if(rs.next()) {
//
//String add1 =rs.getString("id_chambre");
//txtid_chambre.setText(add1);
//
//String add2 =rs.getString("numero");
//////////////////  NUMERO ////////
//txtNumero.setText(add2);
//
//
//}
//
//} catch(Exception ex) {
//JOptionPane.showMessageDialog(null, ex);
//}
//}
//});
//       
//
//tblConcerner.setAutoCreateRowSorter(true);
//scrollPane.setViewportView(tblConcerner);
//
//JSeparator separator_1 = new JSeparator();
//separator_1.setOrientation(SwingConstants.VERTICAL);
//separator_1.setBounds(371, 11, 6, 616);
//panel.add(separator_1);
//
//txtid_chambre = new JTextField();
//txtid_chambre.setFont(new Font("Tahoma", Font.BOLD, 11));
//txtid_chambre.setColumns(10);
//txtid_chambre.setBounds(134, 245, 136, 27);
//panel.add(txtid_chambre);
//
//JLabel lblIdChambre = new JLabel("ID Chambre :");
//lblIdChambre.setFont(new Font("Arial", Font.BOLD, 14));
//lblIdChambre.setBounds(10, 251, 112, 14);
//panel.add(lblIdChambre);
//
//JLabel lblNumro = new JLabel("Num\u00E9ro :");
//lblNumro.setFont(new Font("Arial", Font.BOLD, 14));
//lblNumro.setBounds(10, 288, 112, 14);
//panel.add(lblNumro);
//
//txtNumero = new JTextField();
//txtNumero.setFont(new Font("Tahoma", Font.BOLD, 11));
//txtNumero.setColumns(10);
//
//txtNumero.setBounds(134, 286, 136, 27);
//panel.add(txtNumero);
//
//JButton btnDelete = new JButton("Retirer");
//btnDelete.addActionListener(new ActionListener() {
//public void actionPerformed(ActionEvent arg0) {
//String num = txtNumero.getText();
//String id = txtid_chambre.getText();
//
//if ( id.trim().length() == 0 ||num.trim().length() == 0 ) {
//JOptionPane.showMessageDialog(null,"Veuillez d'abord sélectionner une chambre ou réservation");
//}else {
//Database db= new Database();
//
//
//try {
//Connection con;
//con = db.db_connect();
//PreparedStatement stmtUpdate = con.prepareStatement("UPDATE CHAMBRE SET Status='Libre'WHERE id_chambre=?");
//stmtUpdate.setString(1, id);
//stmtUpdate.executeUpdate();
//
//PreparedStatement stmt = con.prepareStatement("DELETE FROM `concerner` WHERE id_chambre=?");
//stmt.setString(1, id);
//stmt.executeUpdate();
//JOptionPane.showMessageDialog(null,"Suppression effectuée");
//showTable();
//
//
//} catch (ClassNotFoundException e) {
//
//JOptionPane.showMessageDialog(null, e);
//e.printStackTrace();
//} catch (SQLException e) {
//JOptionPane.showMessageDialog(null,e);
//e.printStackTrace();
//}
//
//showTable();}
//}
//});
//btnDelete.setBackground(new Color(72, 138, 153));
//btnDelete.setBounds(523, 495, 179, 39);
//panel.add(btnDelete);
//showTable();
//
//}
//
//public void showTable(){
//Database db =new Database();
//try {
//String idReservation =txtid_reservation.getText();
//Connection con;
//con = db.db_connect();
//PreparedStatement stmt = con.prepareStatement
//("SELECT id_chambre as 'ID Chambre', id_reservation as 'ID Reservation' FROM CONCERNER WHERE id_reservation=?");
//stmt.setString(1, idReservation);
//ResultSet rs = stmt.executeQuery();
//tblConcerner.setModel(DbUtils.resultSetToTableModel(rs));
//tblConcerner.getColumnModel().getColumn(0).setPreferredWidth(15);
//tblConcerner.getColumnModel().getColumn(1).setPreferredWidth(50);
//
//
//}catch(Exception e){
//JOptionPane.showMessageDialog(null, e);
//}
//}//===END SHOWTABLE()===//
//
//public void refreshRoomList() {
//try {
//roomAvailableList.removeAllItems();
//Database db =new Database();
//Connection con;
//con = db.db_connect();
//PreparedStatement stmt = con.prepareStatement("SELECT numero FROM CHAMBRE WHERE status='libre' ");
//ResultSet rs = stmt.executeQuery();
//while(rs.next())
//       {
//           this.roomAvailableList.addItem(rs.getString("numero"));
//       }
//con.close();
//rs.close();
//}catch(Exception e) {JOptionPane.showMessageDialog(null, e);}
//
//}
//public String getIdClient() {
//return id_client;
//}
//public void setIdClient(String idClient) {
//this.id_client = idClient;
//}
//public String getIdReservation() {
//return id_reservation;
//}
//public void setIdReservation(String idReserved) {
//this.id_reservation = idReserved;
//}
//public String getCNom() {
//return cnom;
//}
//public void setCNom(String cnom) {
//this.cnom = cnom;
//}
//public String getCPrenom() {
//return cprenom;
//}
//public void setCPrenom(String cprenom) {
//this.cprenom = cprenom;
//}
//}
