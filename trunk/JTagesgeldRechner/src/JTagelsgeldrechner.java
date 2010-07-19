import javax.swing.SwingUtilities;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JComboBox;
import java.awt.Color;

/**
 * 
 */

/**
 * Das JTagesgeldrechner Programm zur Berechnung der Tagesgeldzinsen.
 * Copyright (C) [2010]  [Oliver Türpe]
 * <br>
 * This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation; either version 3 of the License, or (at your option) any later version.
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with this program; if not, see <http://www.gnu.org/licenses/>.
 * <br>
 * Elektronisch bin ich unter oliver&#064;tuerpe.info zu erreichen.
 * @version 0.1
 * @author Oliver Türpe
 */
public class JTagelsgeldrechner extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JButton Daten_updaten = null;
	private JLabel LGeldanlage = null;
	private JTextField Geldanlage = null;
	private JMenuBar Menuebar = null;
	private JMenu jMenu = null;
	private JMenu Info = null;
	private JMenuItem About = null;
	private JMenuItem Hilfe = null;
	private JLabel Datum_waehlen = null;
	private JComboBox Tag = null;
	private JComboBox Monat = null;
	private JComboBox Jahr = null;
	private JLabel geld_erklaerung = null;
	private JLabel Eurosymbol = null;
	private JButton Zinsertrag_errechnen = null;
	private static double anlage; //Der Anlagewert
	private JLabel erklaerungen = null;
	private JLabel erster = null;
	private JLabel zweiter = null;
	private JLabel dritter = null;
	private JLabel Platz_eins = null;
	private JLabel Platz_zwei = null;
	private JLabel Platz_drei = null;
	
	/**
	 * This method initializes Daten_updaten	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getDaten_updaten() {
		if (Daten_updaten == null) {
			Daten_updaten = new JButton();
			Daten_updaten.setBounds(new Rectangle(755, 15, 116, 60));
			Daten_updaten.setText("<HTML><center>Tagesgeld<br>Daten updaten</center></HTML>");
		}
		return Daten_updaten;
	}

	/**
	 * This method initializes Geldanlage	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getGeldanlage() {
		if (Geldanlage == null) {
			Geldanlage = new JTextField();
			Geldanlage.setBounds(new Rectangle(241, 15, 103, 21));
		}
		return Geldanlage;
	}

	/**
	 * This method initializes Menuebar	
	 * 	
	 * @return javax.swing.JMenuBar	
	 */
	private JMenuBar getMenuebar() {
		if (Menuebar == null) {
			Menuebar = new JMenuBar();
			Menuebar.setBackground(Color.black);
			Menuebar.add(getJMenu());
			Menuebar.add(getInfo());
		}
		return Menuebar;
	}

	/**
	 * This method initializes jMenu	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getJMenu() {
		if (jMenu == null) {
			jMenu = new JMenu();
			jMenu.setBackground(Color.black);
			jMenu.setText("zurücksetzen");
		}
		return jMenu;
	}

	/**
	 * This method initializes Info	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getInfo() {
		if (Info == null) {
			Info = new JMenu();
			Info.setBackground(Color.black);
			Info.setText("Infos");
			Info.add(getAbout());
			Info.add(getHilfe());
		}
		return Info;
	}

	/**
	 * This method initializes About	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getAbout() {
		if (About == null) {
			About = new JMenuItem();
			About.setText("About");
			About.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("about aufgerufen");
					String about = "";
					try{
						File aboutdatei = new File("data" + File.separator + "about");
						FileReader fr = new FileReader(aboutdatei);
						BufferedReader einlesen = new BufferedReader(fr);
						String zeile = " ";
						while (zeile != null){
							try {
								zeile = einlesen.readLine();
							} catch (IOException e2) {
								e2.printStackTrace();
							}
							about = about + zeile + "\n";
						}
					}catch (FileNotFoundException e3){
						System.out.println("data/about nicht gefunden!");
					}
						about = about.substring(0, about.length()-6);
						JOptionPane.showMessageDialog(jContentPane, about, "About/Info", 1);
				}	
			});
		}
		return About;
	}

	/**
	 * This method initializes Hilfe	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getHilfe() {
		if (Hilfe == null) {
			Hilfe = new JMenuItem();
			Hilfe.setText("Hilfe");
		}
		return Hilfe;
	}

	private Object makeObj(final String item) {
		return new Object(){
			public String toString(){
				return item;
			}
		};
	}
	
	/**
	 * This method initializes Tag	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getTag() {
		if (Tag == null) {
			Tag = new JComboBox();
			Tag.setBounds(new Rectangle(17, 70, 80, 35));
			Tag.addItem(makeObj("Tag"));
			for (int i = 1; i< 32; i++){ //alle Tage hinzufügen
				Tag.addItem(makeObj("" + i));
			}
		}
		return Tag;
	}

	/**
	 * This method initializes Monat	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getMonat() {
		if (Monat == null) {
			Monat = new JComboBox();
			Monat.addItem(makeObj("Monat"));
			Monat.addItem(makeObj("Januar"));
			Monat.addItem(makeObj("Februar"));
			Monat.addItem(makeObj("März"));
			Monat.addItem(makeObj("April"));
			Monat.addItem(makeObj("Mai"));
			Monat.addItem(makeObj("Juni"));
			Monat.addItem(makeObj("Juli"));
			Monat.addItem(makeObj("August"));
			Monat.addItem(makeObj("September"));
			Monat.addItem(makeObj("Oktober"));
			Monat.addItem(makeObj("November"));
			Monat.addItem(makeObj("Dezember"));
			Monat.setBounds(new Rectangle(105, 70, 100, 35));
		}
		return Monat;
	}

	/**
	 * This method initializes Jahr	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getJahr() {
		Calendar jahrangabe = Calendar.getInstance();
		jahrangabe.setTime(new Date()); //Heute
		if (Jahr == null) {
			Jahr = new JComboBox();
			Jahr.addItem(makeObj("Jahr"));
			for (int i = jahrangabe.get(Calendar.YEAR);i<=jahrangabe.get(Calendar.YEAR) + 10;i++){ //Wir fügen alle möglichen Geburtstjahre ein
				Jahr.addItem(makeObj("" + i));
			}
			Jahr.setBounds(new Rectangle(212, 70, 109, 35));
		}
		return Jahr;
	}

	/**
	 * This method initializes Zinsertrag_errechnen	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getZinsertrag_errechnen() {
		if (Zinsertrag_errechnen == null) {
			Zinsertrag_errechnen = new JButton();
			Zinsertrag_errechnen.setBounds(new Rectangle(755, 75, 116, 60));
			Zinsertrag_errechnen.setText("<HTML><center>Zinsertrag<br>errechnen</center></HTML>");
			Zinsertrag_errechnen.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("Berechnen gedrückt");
					if(Eingabekorreckt()){
						
					}
				}
			});
		}
		return Zinsertrag_errechnen;
	}
	
	private boolean Eingabekorreckt(){
		if (Datumkorreckt() && Anlagekorreckt()){
			return true;
		}
		return false;
	}
	
	private boolean Anlagekorreckt(){
		try{
			anlage = Double.parseDouble(Geldanlage.getText().replace(',', '.'));
			System.out.println("Zinsertrag: " + anlage + "€");
			return true;
		}catch(NumberFormatException nf){
			System.out.println("Eingabe falsch");
			JOptionPane.showMessageDialog(jContentPane, "Sie haben einen ungültigen Betrag angegeben.", "Fehler", 1);
		}
		return false;
	}
	
	private boolean Datumkorreckt(){
		if ((Tag.getSelectedIndex() == 0) || (Monat.getSelectedIndex() == 0) || (Jahr.getSelectedIndex() == 0)){
			System.out.println("Datum inkorreckt");
			return false;
		}
		System.out.println("Datum korreckt");
		return true;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JTagelsgeldrechner thisClass = new JTagelsgeldrechner();
				thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				thisClass.setVisible(true);
			}
		});
	}

	/**
	 * This is the default constructor
	 */
	public JTagelsgeldrechner() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(883, 215);
		this.setJMenuBar(getMenuebar());
		this.setContentPane(getJContentPane());
		this.setTitle("JTagesgeldrechner");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			Platz_drei = new JLabel();
			Platz_drei.setForeground(Color.red);
			//Platz_drei.setVisible(false);
			Platz_drei.setBounds(new Rectangle(400, 120, 180, 25));
			Platz_drei.setText("JLabel");
			Platz_zwei = new JLabel();
			Platz_zwei.setForeground(Color.yellow);
			//Platz_zwei.setVisible(false);
			Platz_zwei.setBounds(new Rectangle(400, 95, 180, 25));
			Platz_zwei.setText("JLabel");
			Platz_eins = new JLabel();
			//Platz_eins.setVisible(false);
			Platz_eins.setForeground(Color.green);
			Platz_eins.setBounds(new Rectangle(400, 70, 180, 25));
			Platz_eins.setText("JLabel");
			dritter = new JLabel();
			dritter.setBounds(new Rectangle(365, 120, 25, 25));
			dritter.setText("3.");
			dritter.setForeground(Color.red);
			//dritter.setVisible(false);
			zweiter = new JLabel();
			zweiter.setBounds(new Rectangle(365, 95, 25, 25));
			zweiter.setText("2.");
			zweiter.setForeground(Color.yellow);
			//zweiter.setVisible(false);
			erster = new JLabel();
			erster.setBounds(new Rectangle(365, 70, 25, 25));
			erster.setForeground(Color.green);
			erster.setText("1.");
			//erster.setVisible(false);
			erklaerungen = new JLabel();
			erklaerungen.setForeground(Color.white);
			erklaerungen.setBounds(new Rectangle(488, 13, 234, 34));
			erklaerungen.setText("Es werden nur die Top 3 vorgestellt:");
			Eurosymbol = new JLabel();
			Eurosymbol.setForeground(Color.white);
			Eurosymbol.setBounds(new Rectangle(343, 12, 14, 27));
			Eurosymbol.setText("€");
			geld_erklaerung = new JLabel();
			geld_erklaerung.setForeground(Color.white);
			geld_erklaerung.setBounds(new Rectangle(362, 15, 81, 22));
			geld_erklaerung.setText("(z.B.: 12,78)");
			Datum_waehlen = new JLabel();
			Datum_waehlen.setForeground(Color.white);
			Datum_waehlen.setBounds(new Rectangle(16, 44, 327, 26));
			Datum_waehlen.setText("Bis zu welchem Tag soll das Geld angelegt werden?");
			LGeldanlage = new JLabel();
			LGeldanlage.setForeground(Color.white);
			LGeldanlage.setBounds(new Rectangle(16, 14, 218, 20));
			LGeldanlage.setText("Wieviel Geld soll angelegt werden?");
			jContentPane = new JPanel();
			jContentPane.setBackground(Color.black);
			jContentPane.setLayout(null);
			jContentPane.add(getDaten_updaten(), null);
			jContentPane.add(LGeldanlage, null);
			jContentPane.add(getGeldanlage(), null);
			jContentPane.add(Datum_waehlen, null);
			jContentPane.add(getTag(), null);
			jContentPane.add(getMonat(), null);
			jContentPane.add(getJahr(), null);
			jContentPane.add(geld_erklaerung, null);
			jContentPane.add(Eurosymbol, null);
			jContentPane.add(getZinsertrag_errechnen(), null);
			jContentPane.add(erklaerungen, null);
			jContentPane.add(erster, null);
			jContentPane.add(zweiter, null);
			jContentPane.add(dritter, null);
			jContentPane.add(Platz_eins, null);
			jContentPane.add(Platz_zwei, null);
			jContentPane.add(Platz_drei, null);
		}
		return jContentPane;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
