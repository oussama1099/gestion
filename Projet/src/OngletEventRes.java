import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;

import net.proteanit.sql.DbUtils;

public class OngletEventRes implements ActionListener {
	
	JTextField titleEvent;
	String IdEvent;
	JPanel eventOnglet;
	JTable tabEvent;
	JLabel lTAB1;
	JTextField BareRech;
	JButton BtnRech;
	JButton BtnAnnulRech;
	String Rech;
	
	public OngletEventRes() {
		
		this.lTAB1 = new JLabel();
		lTAB1.setFont(new Font("Segeo UI", Font.BOLD, 24));
		tabEvent = new JTable();
		Select();
		JLabel Text = new JLabel("Planning des évènements");
		Text.setFont(new Font("Segeo UI", Font.BOLD, 24));
		Text.setForeground(Color.white);
		Text.setBounds(350, 50, 300, 30);
		eventOnglet= new JPanel();
		
		
		Rech = new String("");
		BareRech = new JTextField("Recherche par titre");
		BareRech.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				BareRech.setText(Rech);
			}

			public void focusLost(FocusEvent e) {
				Rech = BareRech.getText();
				if(Rech.isEmpty()) BareRech.setText("Recherche par titre");
				else BareRech.setText(Rech);
			}
		});
		BareRech.setBounds(150, 565, 350, 30);						
		BtnRech = new JButton();							
		BtnRech.setIcon(new ImageIcon("rsrc\\icons-chercher.png")); 
		BtnRech.setText("Rechercher");								
		BtnRech.setBounds(510, 565, 150, 30); 						
		BtnRech.setBackground(Color.white); 						
		BtnRech.setFont(new Font("Segeo UI", Font.PLAIN, 12)); 		
		BtnRech.setFocusable(false); 								
		BtnRech.setForeground(new Color(57, 113, 177));				
		BtnAnnulRech = new JButton();							
		BtnAnnulRech.setIcon(new ImageIcon("rsrc\\icons-effacer-la-recherche.png")); 
		BtnAnnulRech.setText("Annuler la Recherche");
		BtnAnnulRech.setBounds(670, 565, 180, 30);						
		BtnAnnulRech.setBackground(Color.white); 						
		BtnAnnulRech.setFont(new Font("Segeo UI", Font.PLAIN, 12)); 		
		BtnAnnulRech.setFocusable(false); 								
		BtnAnnulRech.setForeground(new Color(57, 113, 177));			
		BtnRech.addActionListener(this);								
		BtnAnnulRech.addActionListener(this);	
		
		JScrollPane tabscroll= new JScrollPane();
		tabscroll.setBounds(150, 100, 700, 450);
		eventOnglet.add(Text,BorderLayout.CENTER);
		tabEvent.getTableHeader().setBackground(Color.decode("#C6B89B"));
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
		
		
		
		
		eventOnglet.add(BareRech,BorderLayout.CENTER);  //*
		eventOnglet.add(BtnRech,BorderLayout.CENTER); //*
		eventOnglet.add(BtnAnnulRech,BorderLayout.CENTER); //*
		tabscroll.setViewportView(tabEvent);
		eventOnglet.add(tabscroll);
		eventOnglet.setPreferredSize(new Dimension(1000, 700));
		eventOnglet.setBackground(new Color(57, 113, 177));
		eventOnglet.setLayout(null);
		
	}
	
	private void Recherch() {
		String url = "jdbc:mysql://localhost:3306/projet_java";
		String sql = "select titre, description  from event where titre like '"+BareRech.getText()+"%'";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connect = DriverManager.getConnection(url,"root","");
			Statement s = connect.createStatement();
			ResultSet rs = s.executeQuery(sql);
			tabEvent.setModel(DbUtils.resultSetToTableModel(rs));
			tabEvent.getColumnModel().getColumn(0).setHeaderValue("Titre");
			tabEvent.getColumnModel().getColumn(1).setHeaderValue("Description");
			s.close();
			connect.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void Select() {
		String sql = "select titre, Description from event where idReservation=any(select id_reservation from confirmation where date>=DATE(NOW()))";
		String url = "jdbc:mysql://localhost:3306/projet_java";
		//selection des données session admin
			lTAB1.setBounds(400, 50, 200, 30);
			lTAB1.setText("Les évènements");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connect = DriverManager.getConnection(url,"root","");
			Statement s = connect.createStatement();
			ResultSet resultat=s.executeQuery(sql);
			tabEvent.setModel(DbUtils.resultSetToTableModel(resultat));
			tabEvent.getColumnModel().getColumn(0).setHeaderValue("Titre");
			tabEvent.getColumnModel().getColumn(1).setHeaderValue("Description");
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
		
		if(e.getSource()==BtnRech) {
			Recherch();
		}
		
		if(e.getSource()==BtnAnnulRech) {
			Select();
		}
	}

}
