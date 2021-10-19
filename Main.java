import javax.swing.*;
public class Main {
    protected static Menu menu;
    protected static Jeu jeu;
    protected static JFrame fenetre;	

    public static void main(String [] args) {
	fenetre = new JFrame("Fire Run!");
	fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	fenetre.setSize(700,360);
	fenetre.setResizable(false);
	fenetre.setLocationRelativeTo(null);
		
	fenetre.setContentPane(new Menu());
	fenetre.setVisible(true);
		
    }
}
