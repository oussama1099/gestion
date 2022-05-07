import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.*;

public class Login extends JFrame implements ActionListener{
	
	JFrame frame;
	JComboBox userType;
	JLabel lusername;
	JTextField username;
	JLabel lpassw;
	JPasswordField passw;
	JButton btn;
	String USERNAME;
	String PASSW;
	JCheckBox passw_afficher;

	public Login() {
		
		//frame
		frame = new JFrame();
		frame.setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 450);
		frame.setResizable(false);
		frame.setLayout(new BorderLayout());
		
		
		
		//form
		JPanel panForm = new JPanel();
		panForm.setLayout(null);
		panForm.setPreferredSize(new Dimension(400, 300));
		panForm.setBackground(Color.white);
		JLabel lusertype = new JLabel("Votre Role:");
		lusertype.setBounds(125, 100, 200, 30);
		userType = new JComboBox();
		userType.setBounds(200, 100, 125, 30);
		userType.addItem("Etudiant");
		userType.addItem("Professeur");
		userType.addItem("Administrateur");
		JLabel label2 = new JLabel("Se conncter");
		lusername = new JLabel();
		ImageIcon userIcon = new ImageIcon("rsrc\\user-icon.png");
		ImageIcon userIcon2 = new ImageIcon("rsrc\\user-icon2.png");
		lusername.setIcon(userIcon);
		lusername.setBounds(85, 150, 30, 30);
		username = new JTextField("Nom d'utilisateur");
		username.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
		USERNAME = "";
		username.addFocusListener(new FocusListener() {
			
			public void focusGained(FocusEvent e) {
				username.setText(USERNAME);
				lusername.setIcon(userIcon2);
				username.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(57, 113, 177)));
			}

			public void focusLost(FocusEvent e) {
				USERNAME = username.getText();
				if(USERNAME.isEmpty()) username.setText("Nom d'utilisateur");
				else username.setText(USERNAME);
				lusername.setIcon(userIcon);
				username.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
			}
		});
		username.setBounds(125, 150, 200, 30);
		lpassw = new JLabel();
		ImageIcon passwIcon = new ImageIcon("rsrc\\passw-icon.png");
		ImageIcon passwIcon2 = new ImageIcon("rsrc\\passw-icon2.png");
		lpassw.setIcon(passwIcon);
		lpassw.setBounds(85, 200, 30, 30);
		passw = new JPasswordField("***********");
		passw.setEchoChar('*');
		passw.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
		PASSW = "";
		passw.addFocusListener(new FocusListener() {
			
			public void focusGained(FocusEvent e) {
				passw.setText(PASSW);
				lpassw.setIcon(passwIcon2);
				passw.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(57, 113, 177)));
			}

			public void focusLost(FocusEvent e) {
				PASSW = passw.getText();
				if(PASSW.isEmpty()) passw.setText("***********");
				else passw.setText(PASSW);
				lpassw.setIcon(passwIcon);
				passw.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
			}
		});
		passw.setBounds(125,200,200, 30);
		btn = new JButton("Se connecter");
		btn.setBounds(85, 300, 240, 30);
		btn.setBackground(new Color(57, 113, 177));
		btn.setFont(new Font("Segeo UI", Font.PLAIN, 12));
		btn.setFocusable(false);
		btn.setForeground(Color.white);
		btn.addActionListener(this);
		passw_afficher = new JCheckBox("Afficher le mot de passe");
		passw_afficher.setBounds(85, 250, 175, 30);
		passw_afficher.setBackground(Color.white);
		passw_afficher.setFocusable(false);
		passw_afficher.addActionListener(this);
		JLabel Text = new JLabel("Se Connecter");
		Text.setFont(new Font("Segeo UI", Font.BOLD, 20));
		Text.setForeground(new Color(57, 113, 177));
		Text.setBounds(150, 25, 400, 30);
		panForm.add(Text);
		panForm.add(lusertype);
		panForm.add(userType);
		panForm.add(lusername);
		panForm.add(username);
		panForm.add(lpassw);
		panForm.add(passw);
		panForm.add(passw_afficher);
		panForm.add(btn);
		
		JLabel passw_forget = new JLabel("Mot de passe oublié?");
		passw_forget.setBounds(85, 350, 200, 30);
		panForm.add(passw_forget);
		
		
		
		//label1(Logo)
		JLabel label1 = new JLabel("Espace de gestion");
		ImageIcon icon1 = new ImageIcon("rsrc\\LOGO ENSAK 800 (1).png");
		label1.setIcon(icon1);
		label1.setIconTextGap(10);
		label1.setBounds(70, 80, 250, 200);
		label1.setVerticalTextPosition(JLabel.BOTTOM);
		label1.setHorizontalTextPosition(JLabel.CENTER);
		label1.setFont(new Font("Segeo UI", Font.ITALIC, 30));
		label1.setForeground(new Color(204,204,204));
		
		JLabel label5 = new JLabel("Created By AISSY Achraf & TEFFAL Oussama");
		label5.setFont(new Font("Segeo UI", Font.PLAIN, 10));
		label5.setBounds(90, 350, 250, 10);
		label5.setForeground(Color.white);
		
		
		
		//panel1(left)
		JPanel panel1 = new JPanel();
		panel1.setLayout(null);
		panel1.setBounds(0, 0, 400, 450);
		panel1.setBackground(new Color(0,0,0,200));
		JLabel label3 = new JLabel();
		ImageIcon icon3 = new ImageIcon("rsrc\\login_photo.jpg");
		label3.setBounds(0, 0, 400, 450);
		label3.setIcon(icon3);
		panel1.add(label1);
		panel1.add(label5);
		
		
		
		
		//panel3(Title: Se connecter)
		JPanel panel3 = new JPanel();
		panel3.setPreferredSize(new Dimension(400,100));
		panel3.setBackground(Color.black);
		panel3.setLayout(new GridBagLayout());
		
		
		
		
		
		//panel2
		JPanel panel2 = new JPanel();
		panel2.setLayout(new BorderLayout());
		panel2.setBounds(400, 0, 400, 450);
		panel2.setBackground(Color.white);
		//panel2.add(Text,BorderLayout.NORTH);
		panel2.add(panForm,BorderLayout.EAST);
		
		
		
		//add to frame and setVisible
		ImageIcon icon_frame = new ImageIcon("rsrc//LOGO ENSAK 800.png");
		frame.setIconImage(icon_frame.getImage());
		frame.setTitle("Espace de Gestion");
		frame.add(panel1);
		frame.add(panel2);
		frame.add(label3);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==btn) {
			String url = "jdbc:mysql://localhost:3306/jdbs";
			try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection connexion = DriverManager.getConnection(url, "root", "");
					USERNAME = username.getText();
					PASSW = passw.getText();
					Statement stat = connexion.createStatement();
					String sql = "select * from test where nom='"+USERNAME+"'and password='"+PASSW+"'";
					ResultSet rs = stat.executeQuery(sql);
					if(rs.next()) {
						frame.dispose();
						new Administration(null, null);
					}else {
						JOptionPane.showMessageDialog(null, "Nom d'utilisateur ou le mot de passe incorrecte(s)!");
						USERNAME = "";
						PASSW = "";
						username.setText("Nom d'utilisateur");
						passw.setText("***********");
					}
				
				connexion.close();
				
			} catch (ClassNotFoundException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			
			catch (SQLException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "Vous devez remplir tous les champs!");
			}
		}
		
		if(passw_afficher.isSelected()) {
			passw.setEchoChar((char)0);
		}else {
			passw.setEchoChar('*');
		}
		
	}

}
