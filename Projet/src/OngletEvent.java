import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
import java.time.LocalTime;
import java.util.Calendar;

import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;

import com.toedter.calendar.JDateChooser;

import net.proteanit.sql.DbUtils;

public class OngletEvent  extends JPanel implements ActionListener{
	
	JTextField titleEvent;
	String IdEvent;
	JTextField N_Salle;
	JTextField N_Heure;
	JTextArea DiscEvent;
	JSpinner.DateEditor de;
	JSpinner.DateEditor de1;
	JSpinner jSpinner1;
	JSpinner jSpinner2;
	JDateChooser datePanel;
	JPanel eventOnglet;
	JButton AjoutBtn;
	JButton DeleteBtn;
	JButton UpdateBtn;
	JTable tabEvent;
	JTable tabEvent2;
	JDateChooser datePanel2;
	String UserType;
	String UserID;
	String USERNAME;
	JLabel lTAB1;
	JLabel lTAB2;
	String idReservation;
	
	JTextField BareRech;
	JButton BtnRech; //**
	JButton BtnAnnulRech; //**
	String Rech; //***

	public OngletEvent(String UserType,String UserID) {
		
		this.UserType=UserType;
		this.UserID=UserID;
		
		this.lTAB1 = new JLabel();
		this.lTAB2 = new JLabel();
		
		tabEvent = new JTable();
		tabEvent2 = new JTable();
		Select();
		JLabel lBienvune = new JLabel("Bienvenue, "+USERNAME);
		lBienvune.setFont(new Font("Segeo UI", Font.BOLD, 18));
		lBienvune.setForeground(Color.decode("#C6B89B"));
		lBienvune.setBounds(55, 50, 300, 30);
		
		JLabel Text = new JLabel("Planing des Evènements");
		Text.setFont(new Font("Segeo UI", Font.BOLD, 24));
		Text.setForeground(new Color(57, 113, 177));
		Text.setBounds(55, 105, 600, 30);
		
		eventOnglet= new JPanel();
		eventOnglet.setPreferredSize(new Dimension(1000, 700));
		eventOnglet.setBackground(Color.white);
		eventOnglet.setLayout(new BorderLayout());
		
			//partie à gauche (Ajout, suppression, modification des réseravation)
		
				JPanel leftPan1 = new JPanel();
				leftPan1.setLayout(null);
				leftPan1.setPreferredSize(new Dimension(400, 700));
				leftPan1.setBackground(Color.white);
				
				JLabel lidReservataire=new JLabel("Titre D'évènement");
				lidReservataire.setBounds(60, 152, 200, 30);
				titleEvent = new JTextField();
				titleEvent.setBounds(60, 180, 250, 30);
				if(UserType=="etudiant" || UserType=="professeur") {
					titleEvent.setText(UserID);
					titleEvent.setEditable(false);
				}
				
				JLabel lDiscEvent = new JLabel("Description");
				DiscEvent = new JTextArea();
				Border border = BorderFactory.createLineBorder(Color.GRAY);
				DiscEvent.setBorder(BorderFactory.createCompoundBorder(border, 
				BorderFactory.createEmptyBorder(10, 10, 10, 10)));
				lDiscEvent.setBounds(60, 222, 200, 30);
				DiscEvent.setBounds(60, 250, 250, 60);
				
				JLabel NSalle=new JLabel("Numéro de salle");
				NSalle.setBounds(60, 312, 200, 30);
				N_Salle = new JTextField();
				N_Salle.setBounds(60, 340, 250, 30);
//				N_Salle.addKeyListener(new KeyAdapter() {    //*********A Ameliorer***********************
//					
//					public void keyReleased(KeyEvent arg0) {
//						String url = "jdbc:mysql://localhost:3306/projet_java";
//						
//						try {
//							
//							Class.forName("com.mysql.jdbc.Driver");
//							Connection connexion = DriverManager.getConnection(url,"root","");
//							String sql = "select N_salle, date, HeureD,HeureF from confirmation where N_salle =  '"+N_Salle.getText().toString()+"'";
//							Statement stat = connexion.createStatement();
//							ResultSet rs = stat.executeQuery(sql);
//							if(rs.next())
//								tabEvent.setModel(DbUtils.resultSetToTableModel(rs));
//							stat.close();
//							connexion.close();
//							
//						} catch (SQLException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						} catch (ClassNotFoundException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
//						
//					}
//					
//					
//				}
//				);
				
				JLabel lDateReseravtion=new JLabel("Date de Réservation");
				lDateReseravtion.setBounds(60, 382, 200, 30);
				datePanel = new JDateChooser();
				datePanel.setBounds(60, 410, 250, 30);
				
				JLabel HeureD = new JLabel("Heure début");
				HeureD.setBounds(60, 452, 200, 30);
				Date date = new Date(0,0,0);
				SpinnerDateModel sm = new SpinnerDateModel(date, null, null, Calendar.HOUR);
				jSpinner1 = new javax.swing.JSpinner(sm);
				de = new JSpinner.DateEditor(jSpinner1, "HH:mm:ss");
				jSpinner1.setEditor(de);
				jSpinner1.setBounds(60, 480, 120, 30);
				leftPan1.add(HeureD);
				leftPan1.add(jSpinner1);
				
				
				JLabel HeureF = new JLabel("Heure de fin");
				HeureF.setBounds(190, 452, 200, 30);
				Date date1 = new Date(0,0,0);
				SpinnerDateModel sm1 = new SpinnerDateModel(date1, null, null, Calendar.HOUR_OF_DAY);
				jSpinner2 = new javax.swing.JSpinner(sm1);
				de1 = new JSpinner.DateEditor(jSpinner2,"HH:mm:ss");
				jSpinner2.setEditor(de1);
				jSpinner2.setBounds(190, 480, 120, 30);
				leftPan1.add(HeureF);
				leftPan1.add(jSpinner2);
				
				
				AjoutBtn  = new JButton("Ajouter");
				AjoutBtn.setBounds(38, 530, 100, 30);
				DeleteBtn = new JButton("Supprimer");
				DeleteBtn.setBounds(148, 530, 100,30);
				UpdateBtn = new JButton("Modifier");
				UpdateBtn.setBounds(256, 530, 100,30);
				
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
				
				leftPan1.add(lBienvune);
				JSeparator sp = new JSeparator(JSeparator.HORIZONTAL);
				sp.setBounds(20, 92, 360, 30);
				sp.setForeground(Color.decode("#C6B89B"));
				leftPan1.add(sp);
				leftPan1.add(lidReservataire);
				leftPan1.add(NSalle);
				leftPan1.add(lDateReseravtion);
				leftPan1.add(titleEvent);
				leftPan1.add(N_Salle);
				leftPan1.add(datePanel);
				leftPan1.add(Text);
				leftPan1.add(AjoutBtn);
				leftPan1.add(DeleteBtn);
				leftPan1.add(UpdateBtn);
				leftPan1.add(lDiscEvent);
				leftPan1.add(DiscEvent);
	
				
			//partie à droite (Affichage des tableaux)
				
				
				lTAB1.setFont(new Font("Segeo UI", Font.BOLD, 24));
				lTAB1.setForeground(Color.white);
				
				
				lTAB2.setFont(new Font("Segeo UI", Font.BOLD, 24));
				lTAB2.setForeground(Color.white);
				
				/////////////////////////////////////////////////////////////*****************
				Rech = new String();
				BareRech = new JTextField("Rechercher une salle");
				BareRech.addFocusListener(new FocusListener() {
					public void focusGained(FocusEvent e) {
						BareRech.setText(Rech);
					}

					public void focusLost(FocusEvent e) {
						Rech = BareRech.getText();
						if(Rech.isEmpty()) BareRech.setText("Rechercher une salle");
						else BareRech.setText(Rech);
					}
				});
				BareRech.setBounds(25, 565, 200, 30);						
				BtnRech = new JButton();							
				BtnRech.setIcon(new ImageIcon("rsrc\\icons-chercher.png")); 
				BtnRech.setText("Rechercher");								
				BtnRech.setBounds(235, 565, 150, 30); 						
				BtnRech.setBackground(Color.white); 						
				BtnRech.setFont(new Font("Segeo UI", Font.PLAIN, 12)); 		
				BtnRech.setFocusable(false); 								
				BtnRech.setForeground(new Color(57, 113, 177));				
				BtnAnnulRech = new JButton();							
				BtnAnnulRech.setIcon(new ImageIcon("rsrc\\icons-effacer-la-recherche.png")); 
				BtnAnnulRech.setText("Annuler la Recherche");
				BtnAnnulRech.setBounds(395, 565, 180, 30);						
				BtnAnnulRech.setBackground(Color.white); 						
				BtnAnnulRech.setFont(new Font("Segeo UI", Font.PLAIN, 12)); 		
				BtnAnnulRech.setFocusable(false); 								
				BtnAnnulRech.setForeground(new Color(57, 113, 177));			
				BtnRech.addActionListener(this);								
				BtnAnnulRech.addActionListener(this);						
				
				
				////////////////////////////////////////////////////////////////////*************
				
				
				JPanel rightPan1 = new JPanel();
				JScrollPane tabscroll= new JScrollPane();
				
				
				tabscroll.setBounds(25, 355, 550, 200);
				rightPan1.add(lTAB1,BorderLayout.CENTER);
				rightPan1.add(tabscroll,BorderLayout.CENTER);
				tabscroll.setViewportView(tabEvent);
				rightPan1.setLayout(null);
				rightPan1.setBackground(new Color(57, 113, 177));
				tabEvent.getTableHeader().setBackground(Color.decode("#C6B89B"));
				tabEvent2.getTableHeader().setBackground(Color.decode("#C6B89B"));
				
				tabEvent.setDefaultRenderer(Object.class, new DefaultTableCellRenderer()
				{
				    @Override
				    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
				    {
				        final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				        c.setBackground(row % 2 == 0 ? Color.decode("#e8e3d8") : Color.WHITE);
				        return c;
				    }
				});
				
				tabEvent2.setDefaultRenderer(Object.class, new DefaultTableCellRenderer()
				{
				    @Override
				    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
				    {
				        final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				        c.setBackground(row % 2 == 0 ? Color.decode("#e8e3d8") : Color.WHITE);
				        return c;
				    }
				});
				
				if(UserType=="Administrateur") {
					tabEvent.addMouseListener(new MouseAdapter() {
					@Override					
					 public void mouseClicked (MouseEvent arg0){
						 
						 int ligne =tabEvent.getSelectedRow();
						 
						 String nsalle = tabEvent.getModel().getValueAt(ligne,1).toString();
						 String datec = tabEvent.getModel().getValueAt(ligne,2).toString();
						 String heured = tabEvent.getModel().getValueAt(ligne,3).toString();
						 String heuref = tabEvent.getModel().getValueAt(ligne,4).toString();
						 idReservation = new String();
						 idReservation = tabEvent.getModel().getValueAt(ligne,0).toString();

						 try {
							N_Salle.setText(nsalle);
							jSpinner1.setValue(new SimpleDateFormat("HH:mm:ss").parse(heured));
							jSpinner2.setValue(new SimpleDateFormat("HH:mm:ss").parse(heuref)); 
							java.util.Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse(datec);
							datePanel.setDate(date2);
							
							
			

						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						 
					 }});}
				
					   
				tabEvent2.addMouseListener(new MouseAdapter() {
					
					 public void mouseClicked (MouseEvent arg0){
						
					
						 int ligne2=tabEvent2.getSelectedRow();
						 
						 String TitreEvent = tabEvent2.getModel().getValueAt(ligne2,1).toString();
						 String Description = tabEvent2.getModel().getValueAt(ligne2,2).toString();
						 IdEvent = tabEvent2.getModel().getValueAt(ligne2,0).toString();
						 
						 
							
							titleEvent.setText(TitreEvent);
							DiscEvent.setText(Description);

					}});
					                                          
		
				
				
				JScrollPane tabscroll2= new JScrollPane();
				tabscroll2.setBounds(25, 85, 550, 200);
				
				rightPan1.add(lTAB2,BorderLayout.CENTER);
				rightPan1.add(tabscroll2,BorderLayout.CENTER);
				
				rightPan1.add(BareRech,BorderLayout.CENTER);  //*
				rightPan1.add(BtnRech,BorderLayout.CENTER); //*
				rightPan1.add(BtnAnnulRech,BorderLayout.CENTER); //*
				
				
				tabscroll2.setViewportView(tabEvent2);
				rightPan1.setLayout(new BorderLayout());
				rightPan1.setBackground(new Color(57, 113, 177));
				//rightPan1.setBounds(400, 0,600, 700);
				//leftPan1.setBounds(0, 0, 400, 700);
				eventOnglet.add(rightPan1,BorderLayout.CENTER);
				eventOnglet.add(leftPan1,BorderLayout.WEST);
				
	}

	///////////////////////////////////////////////////////////////////*******************
	
	private void Recherch() {
		String url = "jdbc:mysql://localhost:3306/projet_java";
		String sql = "select id_reservation,N_salle,date, HeureD,HeureF from confirmation where N_salle = '"+BareRech.getText()+"'";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connect = DriverManager.getConnection(url,"root","");
			Statement s = connect.createStatement();
			ResultSet rs = s.executeQuery(sql);
			tabEvent.setModel(DbUtils.resultSetToTableModel(rs));
			tabEvent.getColumnModel().getColumn(0).setHeaderValue("N° Réservation");
			tabEvent.getColumnModel().getColumn(1).setHeaderValue("Salle");
			tabEvent.getColumnModel().getColumn(2).setHeaderValue("Date Réservation");
			tabEvent.getColumnModel().getColumn(3).setHeaderValue("De");
			tabEvent.getColumnModel().getColumn(4).setHeaderValue("Jusqu'à");
			s.close();
			connect.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	///////////////////////////////////////////////////////////////////************************
	
	
	public void Select() {
		String sql0 = "select nom from "+UserType+" where UserID ='"+UserID+"'";
		String url = "jdbc:mysql://localhost:3306/projet_java";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connect = DriverManager.getConnection(url,"root","");
			Statement s = connect.createStatement();
			ResultSet rs = s.executeQuery(sql0);
			rs.next();
			USERNAME = rs.getString(1);
			s.close();
			connect.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//requete SQL
		
		String sql = "select IdEvent,idReservation,titre, Description from event where idReservation=any(select id_reservation from confirmation where date>=DATE(NOW()))";
		String sql2 ="select id_reservation,N_salle, date, HeureD,HeureF from confirmation";
		String sqlE="select id_reservation,N_salle,date, HeureD,HeureF from confirmation";
		String sqlE2="select id_reservation,Nsalle, date, HeureD,HeureF from reservation where id_reservataire='"+UserID+"'";
		
		
		
		//selection des données session admin
			lTAB1.setBounds(120, 50, 500, 30);
			lTAB1.setText("Les évènements");
			lTAB2.setText("Les réservations");
			lTAB2.setBounds(150, 315, 300, 30);
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connect = DriverManager.getConnection(url,"root","");
			Statement s = connect.createStatement();
			ResultSet resultat=s.executeQuery(sqlE);
			tabEvent.setModel(DbUtils.resultSetToTableModel(resultat));
			tabEvent.getColumnModel().getColumn(0).setHeaderValue("N° Réservation");
			tabEvent.getColumnModel().getColumn(1).setHeaderValue("Salle");
			tabEvent.getColumnModel().getColumn(2).setHeaderValue("Date Réservation");
			tabEvent.getColumnModel().getColumn(3).setHeaderValue("De");
			tabEvent.getColumnModel().getColumn(4).setHeaderValue("Jusqu'à");
			
			ResultSet resultat2=s.executeQuery(sql);
			tabEvent2.setModel(DbUtils.resultSetToTableModel(resultat2));
         
			tabEvent2.getColumnModel().getColumn(0).setHeaderValue("Id évènement");
			tabEvent2.getColumnModel().getColumn(1).setHeaderValue("N°Réservation");
			tabEvent2.getColumnModel().getColumn(2).setHeaderValue("Titre");
			tabEvent2.getColumnModel().getColumn(3).setHeaderValue("Description");
			s.close();
			s.close();
			connect.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String url = "jdbc:mysql://localhost:3306/projet_java";
		
		
		//////////////////////////////////*****************************
		if(e.getSource()==BtnRech) {
			Recherch();
		}
		
		if(e.getSource()==BtnAnnulRech) {
			Rech = "Rechercher une salle";
			BareRech.setText(Rech);
			Select();
		}
		//////////////////////////////////*****************************
		
		// BUTTON AJOUTER
		if(e.getSource()==AjoutBtn) {
			
			Date date = new Date(datePanel.getDate().getTime());
			String heured = de.getFormat().format(jSpinner1.getValue());
			String heuref = de1.getFormat().format(jSpinner2.getValue());
			LocalTime h1 = LocalTime.parse(heured);
			LocalTime h2 = LocalTime.parse(heuref);
			
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection connect = DriverManager.getConnection(url,"root","");
				Statement s = connect.createStatement();
			
                String sql =" insert into event (titre, description, IdReservation,idAdministrateur) values ('" +titleEvent.getText()+"','"+DiscEvent.getText()+"',"+Integer.parseInt(idReservation)+",'"+UserID+"')";
                
				s.executeUpdate(sql);
				
				titleEvent.setText("");
				DiscEvent.setText("");
				N_Salle.setText("");
				datePanel.setDate(null);
				
				s.close();
				connect.close();
				Select();
				}catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			
		}}
			
		
		if(e.getSource()==DeleteBtn) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection connect2 = DriverManager.getConnection(url,"root","");
				Statement s2 = connect2.createStatement();
				
				String Sql = "delete from event where IdEvent="+IdEvent;
				JOptionPane.showMessageDialog(null, Sql);
				s2.executeUpdate(Sql);
				
				titleEvent.setText("");
				DiscEvent.setText("");
				
				
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
//		if(e.getSource()==UpdateBtn) {
//			
//			try {
//				Class.forName("com.mysql.jdbc.Driver");
//				Connection connect3 = DriverManager.getConnection(url,"root","");
//				Statement s3 = connect3.createStatement();
//				if(UserType=="Administrateur") {
//					
//					// admin session update
//
//				//String sql4="update confirmation set N_salle='"+N_Salle.getText()+"',date='"+date+"',HeureD='"+de.getFormat().format(jSpinner1.getValue())+"',HeureF='"+de1.getFormat().format(jSpinner2.getValue())+"' where id_reservation ="+tabEvent2.getModel().getValueAt(tabEvent2.getSelectedRow(),0).toString(); 
//				//s3.executeUpdate(sql4);
//					titleEvent.setText("");
//				N_Salle.setText("");
//				datePanel.setDate(null);
//				}else {
//					//reservataire update
//					
//				//String sql4="update reservation set Nsalle='"+N_Salle.getText()+"',date='"+date+"',HeureD='"+de.getFormat().format(jSpinner1.getValue())+"',HeureF='"+de1.getFormat().format(jSpinner2.getValue())+"' where id_reservation ="+tabEvent2.getModel().getValueAt(tabEvent2.getSelectedRow(),0).toString(); 
//				
//				String sql2="select * from confirmation where N_salle='"+N_Salle.getText()+"' and date='"+date+"' and (('"+heured+"' > HeureD and '"+heured+"' < HeureF or '"+heuref+"' > HeureD and '"+heuref+"' < HeureF) or ('"+heured+"' = HeureD and '"+heuref+"' = HeureF) or ('"+heured+"' = HeureD and '"+heuref+"' > HeureF) or ('"+heured+"' < HeureD and '"+heuref+"' = HeureF)) " ;
//				//JOptionPane.showMessageDialog(null, sql4);
//				//ResultSet res =s3.executeQuery(sql2);
//
////				if(res.next()) {
////				JOptionPane.showMessageDialog(null, " la modification est impossible,veuillez changer le créneau choisi");
////				}
////				else
////				   s3.executeUpdate(sql4);
//				N_Salle.setText("");
//				datePanel.setDate(null);
//				
//			}
//				
//				
//			
//				s3.close();
//				connect3.close();
//				Select();
//				
//			} catch (ClassNotFoundException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			} catch (SQLException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//			
//		}
		
	}
	
	

}
