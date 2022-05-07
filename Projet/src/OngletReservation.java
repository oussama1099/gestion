import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.*;

import com.toedter.calendar.JDateChooser;

import net.proteanit.sql.DbUtils;

public class OngletReservation extends JPanel implements ActionListener {
	
	JTextField IdReservataire;
	JTextField N_Salle;
	JTextField N_Heure;
	
	JSpinner.DateEditor de;
	JSpinner.DateEditor de1;
	
	JSpinner jSpinner1;
	JSpinner jSpinner2;
	JDateChooser datePanel;
	JPanel reservationOnglet;
	JButton AjoutBtn;
	JButton DeleteBtn;
	JButton UpdateBtn;
	JTable tabReservation;
	JTable tabReservation2;
	JDateChooser datePanel2;
	String UserType;
	String UserID;

	public OngletReservation(String UserType,String UserID) {
		
		this.UserType=UserType;
		this.UserID=UserID;
		
		
//		if(UserType=="Administrateur")
		JLabel Text = new JLabel("Réseravtion des salles");
		Text.setFont(new Font("Segeo UI", Font.BOLD, 20));
		Text.setForeground(new Color(57, 113, 177));
		Text.setBounds(70, 25, 600, 30);
		
		reservationOnglet = new JPanel();
		reservationOnglet.setPreferredSize(new Dimension(1000, 700));
		reservationOnglet.setBackground(Color.white);
		reservationOnglet.setLayout(null);
		
			//partie à gauche (Ajout, suppression, modification des réseravation)
		
				JPanel leftPan1 = new JPanel();
				leftPan1.setLayout(null);
				leftPan1.setPreferredSize(new Dimension(400, 700));
				leftPan1.setBackground(Color.white);
				
				JLabel lidReservataire=new JLabel("ID réservataire");
				lidReservataire.setBounds(80, 72, 200, 30);
				IdReservataire = new JTextField();
				IdReservataire.setBounds(80, 100, 200, 30);
			
				
				JLabel NSalle=new JLabel("Numéro de salle");
				NSalle.setBounds(80, 142, 200, 30);
				N_Salle = new JTextField();
				N_Salle.setBounds(80, 170, 200, 30);
				
				JLabel lDateReseravtion=new JLabel("Date de Réservation");
				lDateReseravtion.setBounds(80, 212, 200, 30);
				datePanel = new JDateChooser();
				datePanel.setBounds(80, 240, 200, 30);
				
				JLabel HeureD = new JLabel("Heure début");
				HeureD.setBounds(80, 282, 200, 30);
				Date date = new Date(0,0,0);
				SpinnerDateModel sm = new SpinnerDateModel(date, null, null, Calendar.HOUR);
				jSpinner1 = new javax.swing.JSpinner(sm);
				de = new JSpinner.DateEditor(jSpinner1, "HH:mm:ss");
				jSpinner1.setEditor(de);
				jSpinner1.setBounds(80, 310, 200, 30);
				leftPan1.add(HeureD);
				leftPan1.add(jSpinner1);
				
				
				JLabel HeureF = new JLabel("Heure de fin");
				HeureF.setBounds(80, 352, 200, 30);
				Date date1 = new Date(0,0,0);
				SpinnerDateModel sm1 = new SpinnerDateModel(date1, null, null, Calendar.HOUR_OF_DAY);
				jSpinner2 = new javax.swing.JSpinner(sm1);
				de1 = new JSpinner.DateEditor(jSpinner2,"HH:mm:ss");
				jSpinner2.setEditor(de1);
				jSpinner2.setBounds(80, 380, 200, 30);
				leftPan1.add(HeureF);
				leftPan1.add(jSpinner2);
				
				
				AjoutBtn  = new JButton("Ajouter");
				AjoutBtn.setBounds(38, 450, 100, 30);
				DeleteBtn = new JButton("Supprimer");
				DeleteBtn.setBounds(148, 450, 100,30);
				UpdateBtn = new JButton("Modifier");
				UpdateBtn.setBounds(256, 450, 100,30);
				
				AjoutBtn.setBackground(new Color(57, 113, 177));
				AjoutBtn.setFont(new Font("Segeo UI", Font.PLAIN, 12));
				AjoutBtn.setFocusable(false);
				AjoutBtn.setForeground(Color.white);
				AjoutBtn.addActionListener(this);
			
				DeleteBtn.setBackground(new Color(57, 113, 177));
				DeleteBtn.setFont(new Font("Segeo UI", Font.PLAIN, 12));
				DeleteBtn.setFocusable(false);
				DeleteBtn.setForeground(Color.white);
				DeleteBtn.addActionListener(this);
				
				UpdateBtn.setBackground(new Color(57, 113, 177));
				UpdateBtn.setFont(new Font("Segeo UI", Font.PLAIN, 12));
				UpdateBtn.setFocusable(false);
				UpdateBtn.setForeground(Color.white);
				UpdateBtn.addActionListener(this);
				
				leftPan1.add(lidReservataire);
				leftPan1.add(NSalle);
				leftPan1.add(lDateReseravtion);
				leftPan1.add(IdReservataire);
				leftPan1.add(N_Salle);
				leftPan1.add(datePanel);
				leftPan1.add(Text);
				leftPan1.add(AjoutBtn);
				leftPan1.add(DeleteBtn);
				leftPan1.add(UpdateBtn);
	
				
			//partie à droite (Affichage des tableaux)
				JPanel rightPan1 = new JPanel();
				JScrollPane tabscroll= new JScrollPane();
				tabReservation = new JTable();
				tabReservation2 = new JTable();
				tabscroll.setBounds(45, 60, 500, 200);
				rightPan1.add(tabscroll);
				tabscroll.setViewportView(tabReservation);
				rightPan1.setLayout(null);
				rightPan1.setPreferredSize(new Dimension(600, 700));
				rightPan1.setBackground(new Color(57, 113, 177));
				if(UserType=="Administrateur") {
				tabReservation.addMouseListener(new MouseAdapter() {
					@Override					
					 public void mouseClicked (MouseEvent arg0){
						 
						 int ligne =tabReservation.getSelectedRow();
						 
						 String nsalle = tabReservation.getModel().getValueAt(ligne,1).toString();
						 String datec = tabReservation.getModel().getValueAt(ligne,2).toString();
						 String heured = tabReservation.getModel().getValueAt(ligne,3).toString();
						 String heuref = tabReservation.getModel().getValueAt(ligne,4).toString();
						 String id_Res= tabReservation.getModel().getValueAt(ligne,5).toString();
						
						 
						
						 

						 try {							
							N_Salle.setText(nsalle);
							jSpinner1.setValue(new SimpleDateFormat("HH:mm:ss").parse(heured));
							jSpinner2.setValue(new SimpleDateFormat("HH:mm:ss").parse(heuref)); 
							java.util.Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse(datec);
							datePanel.setDate(date2);
							IdReservataire.setText(id_Res);
							
					
						
							 

						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						 
					 }});}
				
					   
				tabReservation2.addMouseListener(new MouseAdapter() {
					
					 public void mouseClicked (MouseEvent arg0){
						
					
						 int ligne2=tabReservation2.getSelectedRow();
						 
						 String n_salle = tabReservation2.getModel().getValueAt(ligne2,1).toString();
						 String dateb = tabReservation2.getModel().getValueAt(ligne2,2).toString();
						 String heureD = tabReservation2.getModel().getValueAt(ligne2,3).toString();
						 String heureF = tabReservation2.getModel().getValueAt(ligne2,4).toString();
						 
							
							try {
								
							
								N_Salle.setText(n_salle);
								jSpinner1.setValue(new SimpleDateFormat("HH:mm:ss").parse(heureD));
								jSpinner2.setValue(new SimpleDateFormat("HH:mm:ss").parse(heureF)); 
								java.util.Date date3 = new SimpleDateFormat("yyyy-MM-dd").parse(dateb);
								datePanel.setDate(date3);
								
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						

					}});
					                                          
		
				
				
				JScrollPane tabscroll2= new JScrollPane();
				tabscroll2.setBounds(45, 350, 500, 200);
				rightPan1.add(tabscroll2);
				tabscroll2.setViewportView(tabReservation2);
				rightPan1.setLayout(null);
				rightPan1.setPreferredSize(new Dimension(600, 700));
				rightPan1.setBackground(new Color(57, 113, 177));
				
				
				rightPan1.setBounds(400, 0,600, 700);
				leftPan1.setBounds(0, 0, 400, 700);
				reservationOnglet.add(rightPan1);
				reservationOnglet.add(leftPan1);
				Select();
		
		
		
	}

	
	

	public void Select() {
		//requete SQL
		String userid="";
		String sql = "select id_reservation,Nsalle, date, HeureD,HeureF,id_reservataire from reservation ";
		String sql2 ="select id_reservation,N_salle, date, HeureD,HeureF from confirmation";
		String sqlE="select N_salle,date, HeureD,HeureF from confirmation";
		String sqlE2="select id_reservation,Nsalle, date, HeureD,HeureF from reservation where id_reservataire='"+UserID+"'";
		
		String url = "jdbc:mysql://localhost:3306/projet_java";
		
		//selection des données session admin
		if(UserType=="Administrateur") {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connect = DriverManager.getConnection(url,"root","");
			Statement s = connect.createStatement();
			ResultSet resultat=s.executeQuery(sql);
			tabReservation.setModel(DbUtils.resultSetToTableModel(resultat));
			ResultSet resultat2=s.executeQuery(sql2);
			tabReservation2.setModel(DbUtils.resultSetToTableModel(resultat2));
         

			s.close();
			connect.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// selection des données session reservataire

	}else {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connect = DriverManager.getConnection(url,"root","");
			Statement s = connect.createStatement();
			ResultSet resultat=s.executeQuery(sqlE);
			tabReservation.setModel(DbUtils.resultSetToTableModel(resultat));
			ResultSet resultat2=s.executeQuery(sqlE2);
			 
			tabReservation2.setModel(DbUtils.resultSetToTableModel(resultat2));
			s.close();
			connect.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String url = "jdbc:mysql://localhost:3306/projet_java";
		Date date = new Date(datePanel.getDate().getTime());
		
		// BUTTON AJOUTER
		if(e.getSource()==AjoutBtn) {
			
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection connect = DriverManager.getConnection(url,"root","");
				Statement s = connect.createStatement();
				if(UserType=="Administrateur") {
					// session admin
                String sql =" insert into confirmation (N_salle, date, HeureD,HeureF,id_reservataire,code_adm) values ('" +N_Salle.getText()+"','"+date+"','"+de.getFormat().format(jSpinner1.getValue())+"','"+de1.getFormat().format(jSpinner2.getValue())+"','"+IdReservataire.getText()+"','"+UserID+"')";
				String sql2="delete from reservation where id_reservation ="+tabReservation.getModel().getValueAt(tabReservation.getSelectedRow(),0).toString();
				s.executeUpdate(sql);
				s.executeUpdate(sql2);
				}else {
					// session Reservataire
					String sql ="insert into reservation (Nsalle, date, HeureD,HeureF,id_reservataire) values ('" +N_Salle.getText()+"','"+date+"','"+de.getFormat().format(jSpinner1.getValue())+"','"+de1.getFormat().format(jSpinner2.getValue())+"','"+UserID+"')";
					String sql2="select * from confirmation where N_salle='"+N_Salle.getText()+"' and date='"+date+"' and (TIMEDIFF(HeureD,'"+de.getFormat().format(jSpinner1.getValue())+"')<=\"00:00:00\" or TIMEDIFF(HeureF,'"+de1.getFormat().format(jSpinner2.getValue())+"')>=\"00:00:00\")" ;
					JOptionPane.showMessageDialog(null, sql);
					ResultSet res =s.executeQuery(sql2);
					

					if(res.next()) {
						JOptionPane.showMessageDialog(null, " la salle est deja réservé,veuillez changer le créneau choisi");
					}
					else
					   s.executeUpdate(sql);
				}
				
				s.close();
				connect.close();
				Select();
				}catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
			
		
		if(e.getSource()==DeleteBtn) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection connect2 = DriverManager.getConnection(url,"root","");
				Statement s2 = connect2.createStatement();
				if(UserType=="Administrateur") {
					// admin session delete
				String sql3="delete from confirmation where id_reservation ="+tabReservation2.getModel().getValueAt(tabReservation2.getSelectedRow(),0).toString();
				s2.executeUpdate(sql3);
				}else {
					
					// reservataire delete
					String sql3="delete from reservation where id_reservation ="+tabReservation2.getModel().getValueAt(tabReservation2.getSelectedRow(),0).toString();
					s2.executeUpdate(sql3);
	               

					
				}
				s2.close();
				connect2.close();
				Select();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();

			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
		if(e.getSource()==UpdateBtn) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection connect3 = DriverManager.getConnection(url,"root","");
				Statement s3 = connect3.createStatement();
				if(UserType=="Administrateur") {
					
					// admin session update

				String sql4="update confirmation set N_salle='"+N_Salle.getText()+"',date='"+date+"',HeureD='"+de.getFormat().format(jSpinner1.getValue())+"',HeureF='"+de1.getFormat().format(jSpinner2.getValue())+"' where id_reservation ="+tabReservation2.getModel().getValueAt(tabReservation2.getSelectedRow(),0).toString(); 
				s3.executeUpdate(sql4);
				}else {
					//reservataire update
					
				String sql4="update reservation set Nsalle='"+N_Salle.getText()+"',date='"+date+"',HeureD='"+de.getFormat().format(jSpinner1.getValue())+"',HeureF='"+de1.getFormat().format(jSpinner2.getValue())+"' where id_reservation ="+tabReservation2.getModel().getValueAt(tabReservation2.getSelectedRow(),0).toString(); 
				
				String sql2="select * from confirmation where N_salle='"+N_Salle.getText()+"' and date='"+date+"' and (TIMEDIFF(HeureD,'"+de.getFormat().format(jSpinner1.getValue())+"')<=\"00:00:00\" or TIMEDIFF(HeureF,'"+de1.getFormat().format(jSpinner2.getValue())+"')>=\"00:00:00\")" ;
				JOptionPane.showMessageDialog(null, sql4);
//				ResultSet res =s3.executeQuery(sql2);
//
//				if(res.next()) {
//				JOptionPane.showMessageDialog(null, " la modification est impossible,veuillez changer le créneau choisi");
				//}
			//	else
				   s3.executeUpdate(sql4);
			//}
				
				
			
				s3.close();
				connect3.close();
				Select();
				
			}} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
			
			
		}
		
		
	}
	
	

}
