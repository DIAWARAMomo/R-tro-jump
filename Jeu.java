import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class Jeu extends JPanel{
    protected LinkedList<Objet> listObjet;
    protected LinkedList<Ennemi1> listEnnemi;
    protected LinkedList<Tir> listeTir;
    protected LinkedList<Objet.Nourritures> listNourritures;
    protected PPrincipal perso;
    protected int c = 0;
    protected static enum ETAT{
	MENU,
	JEU,
	PERDU,
	GAGNE
    };
    protected Controleur controleur;
    protected static ETAT etat = ETAT.MENU;
    protected static Score score;   
    protected Font police;
    protected Menu menu;
    protected static JProgressBar Vie;

    protected static boolean estTir;     
    protected Fond fond;
    protected Tir tir ;

    public Jeu(){
	this.etat = ETAT.JEU;
	this.perso = new PPrincipal(300,275);
	this.fond = new Fond();
	estTir = false;
	this.score = new Score();
	this.setFocusable(true);
	this.requestFocusInWindow();
	this.addKeyListener(new Action(this));
	this.police = new Font("Arial",Font.BOLD, 15);
	this.controleur = new Controleur(this);
	this.tir = new Tir(this.perso.ord,300);
	this.listObjet = new LinkedList<Objet>();
	this.listEnnemi = new LinkedList<Ennemi1>();
	this.listeTir = new LinkedList<Tir>();
	this.listNourritures = new LinkedList<Objet.Nourritures>();
	Objet.obstacle(listObjet);
	Ennemi1.ennemis(listEnnemi);
	Objet.Nourritures.gagneVie(listNourritures);

	Vie = new JProgressBar(0,100000);
	Vie.setPreferredSize(new Dimension(200,30));
	Vie.setBackground(Color.red);
	Vie.setForeground(Color.green);
	Vie.setValue(100000);
	this.add(Vie);

	JButton label = new JButton("Rejouer");
	label.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent clic){
		    if(clic.getSource() == label){	
			Main.jeu.score.liste.add(Main.jeu.score);
			replay();
		    }		
		}
	    });
	this.add(label);

	//met a jour le jeu chaque 3 millisecondes
	javax.swing.Timer t = new javax.swing.Timer(3, new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    repaint();
		    c+=1;
		}
	    });
	t.start();
    }


    //Fonction permettant de rejouer au jeu
    public void replay(){
	Main.jeu = new Jeu();
	Main.jeu.etat = etat.JEU;
	Main.fenetre.dispose();
	Main.fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	Main.fenetre.setSize(700,360);
	Main.fenetre.setResizable(false);
	Main.fenetre.setLocationRelativeTo(null);
	Main.fenetre.setContentPane(Main.jeu);
	Main.fenetre.repaint();
	Main.fenetre.revalidate();
	Main.fenetre.setVisible(true);
    }



    public void paintComponent(Graphics g) {
	if(etat == etat.JEU){

	    //affichage de l'image de fond
	    this.fond.affiche(g);
    	    
            //Affichage de tous les elements du controleur
	    controleur.afficheControleur(g);
	
            //Mise a jour de la position de l'image du fond
	    this.fond.movingFond();	

	    //mise a jour de l'affiche des objets 	
	    for (int i = 0; i<listObjet.size();i++){
		listObjet.get(i).afficheObjet(g);
	    }
	    
	    //mise a jour de l'affichage des boites mysteres de vie
	    for (int i = 0; i<listNourritures.size();i++){
		listNourritures.get(i).afficheNourriture(g);
	    }

	    //mise a jour de l'affichage des ennemis
	    for (int i = 0; i<listEnnemi.size();i++){
		listEnnemi.get(i).afficheEnnemi(g);
	    }

	    //Affichage du score et du nombre d'ennemis restants
	    g.setFont(this.police);
	    g.drawString("Score : "+this.score.score,5,15);
	    g.drawString("Il reste : "+this.listEnnemi.size()+" ennemis",510,20);
	    Font t= new Font("Arial", Font.BOLD,50);
	    g.setFont(t);
	}
	this.score.highScore();
	//On gere l'affiche de lorsque le joueur perd la partie
	if(this.Partie1()){
	    g.drawString("GAME OVER ", 100,160);
	    g.drawString("Score " + this.score.score, 50,100);
	    this.score.liste.add(this.score);
	    this.etat = etat.PERDU;
	    Main.fenetre.dispose();
	    Main.fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    Main.fenetre.setSize(700,360);
	    Main.fenetre.setResizable(false);
	    Main.fenetre.setLocationRelativeTo(null);
	    Main.fenetre.setContentPane(new Menu());
	    Main.fenetre.repaint();
	    Main.fenetre.revalidate();
	    Main.fenetre.setVisible(true);
	}
	//on gere l'affichage de lorsque le joueur tue tous les ennemis du jeu	
	else if(this.Partie1()){
	    g.drawString("Score " + this.score.score, 50,100);
	    this.score.liste.add(this.score);
	    this.etat = etat.GAGNE;
	    Main.fenetre.dispose();
	    Main.fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    Main.fenetre.setSize(700,360);
	    Main.fenetre.setResizable(false);
	    Main.fenetre.setLocationRelativeTo(null);
	    Main.fenetre.setContentPane(new Menu());
	    Main.fenetre.repaint();
	    Main.fenetre.revalidate();
	    Main.fenetre.setVisible(true);
	}
	
    }



    public boolean Partie1(){
	return (this.perso.vie<=0);
    }

    public boolean AGagne(){
	return (listEnnemi.size() == 0);
    }

}
