import java.awt.Color;

import javax.swing.*;

public class reservataire {
	
	
	JFrame frame;
	
	public reservataire(String UserType,String UserID) {
		
		frame = new JFrame("Espace de Gestion");
		frame.setSize(1000, 700);
		
		//bar des onglets
				JTabbedPane onglets = new JTabbedPane(SwingConstants.TOP);
				onglets.setBackground(Color.white);
				onglets.setFocusable(false);
				
		//onglet des réservations
				
				OngletReservation ReservationOnglet = new OngletReservation(UserType,UserID);
				onglets.addTab("Réservation des salles", ReservationOnglet.reservationOnglet);
		//_____________________________________________________________________________
			
		
				frame.add(onglets);
				frame.setLocationRelativeTo(null);
				ImageIcon icon_frame = new ImageIcon("rsrc//LOGO ENSAK 800.png");
				frame.setIconImage(icon_frame.getImage());
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
		
		
	}

}
