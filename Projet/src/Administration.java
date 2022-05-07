
import java.awt.*;
import javax.swing.*;

public class Administration {
		
	JFrame frame;

	
	
	public Administration(String nom_Asministrateur, String Cd_Administrateur) {
		frame = new JFrame("Espace de Gestion");
		frame.setSize(1000, 700);
		
		
		//bar des onglets
		JTabbedPane onglets = new JTabbedPane(SwingConstants.TOP);
		onglets.setBackground(Color.white);
		onglets.setFocusable(false);
		
		//_________________________________________________________________________________________________
		
		//onglet des réservations
		
		OngletReservation ReservationOnglet = new OngletReservation("Administrateur");
		
		onglets.addTab("Réservation des salles", ReservationOnglet.reservationOnglet);
		//__________________________________________________________________________________________________
		
		//onglet des événements
		JPanel eventOnglet = new JPanel();
		JLabel titreOnglet2 = new JLabel("Événements");
		eventOnglet.add(titreOnglet2);
		eventOnglet.setPreferredSize(new Dimension(1000, 700));
		eventOnglet.setBackground(Color.white);
		onglets.addTab("Événements", eventOnglet);
		
		
		
		frame.add(onglets);
		frame.setLocationRelativeTo(null);
		ImageIcon icon_frame = new ImageIcon("rsrc//LOGO ENSAK 800.png");
		frame.setIconImage(icon_frame.getImage());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
