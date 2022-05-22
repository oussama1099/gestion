
import java.awt.*;
import javax.swing.*;

public class Administration {
		
	JFrame frame;

	
	
	public Administration( String Cd_Administrateur) {
		frame = new JFrame("Espace de Gestion");
		frame.setSize(1000, 700);
		
		
		//bar des onglets
		JTabbedPane onglets = new JTabbedPane(SwingConstants.TOP);
		onglets.setBackground(Color.white);
		onglets.setFocusable(false);
		
		//_________________________________________________________________________________________________
		
		//onglet des reservations
		
		OngletReservation ReservationOnglet = new OngletReservation("Administrateur",Cd_Administrateur);
		onglets.addTab("Réservation des salles", ReservationOnglet.reservationOnglet);
		//__________________________________________________________________________________________________
		
		//onglet des  evenements
		OngletEvent EventOnglet = new OngletEvent("Administrateur",Cd_Administrateur);
		onglets.addTab("Evènements", EventOnglet.eventOnglet);
		
		
		
		frame.add(onglets);
		frame.setLocationRelativeTo(null);
		ImageIcon icon_frame = new ImageIcon("rsrc//LOGO ENSAK 800.png");
		frame.setIconImage(icon_frame.getImage());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
