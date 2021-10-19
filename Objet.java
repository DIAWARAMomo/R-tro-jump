import java.util.*;
import java.awt.*;
import javax.swing.*;
public class Objet {
    protected int largeur,hauteur,x,y;
    protected Image imgObjet;
    protected ImageIcon icoObjet;
    protected Tir tir;
    
    public Objet(int x,int y,int largeur, int hauteur) {
	this.x = x;
	this.y = y;
	this.largeur = largeur;
	this.hauteur = hauteur;
	this.icoObjet = new ImageIcon(getClass().getResource(quelleImage(largeur,hauteur)));
	this.imgObjet = this.icoObjet.getImage();
    }
    
    //Affecte une image à un objet en fonction de ses dimensions
    public String quelleImage(int i, int j){
	String s ="";
	if(i == 43 && j == 65){
	    s = "Image/tuyauRouge.png";
	}else if(i == 182 && j == 24){
	    s = "Image/bloc2.png";
	}else if(i == 40 && j == 42){
	    s = "Image/pic.png";
	}
	return s;
    }
	
    //mets a jour la position de l'bjet
    public void move() {
	if(Main.jeu.fond.xPos>=0 ) {
	    this.x = this.x - Main.jeu.fond.dx;
	}
    }
   
    //Gestion de l'apparatition des pieges
    public static void piege(Objet o){
	o.y =290;
	Main.jeu.perso.vie -=10;
	Jeu.Vie.setValue(Main.jeu.perso.vie);
    }

    //cette fonction sert a generer les obstacles et les pièges aleatoirement 
    protected static void obstacle(LinkedList<Objet> l){
	Objet obj1_1 = new Objet(800,270,43,65);
	l.add(obj1_1);
	int i=0;
	int y=0;
	while(i <= 100){
	    i=l.size()-1;
	    y=l.get(i).x;
	    int d=(int)(Math.random() * (650-600)) + 600;
	    if( d>=624){
		l.add(new Objet(y+d,270,43,65));
		l.add(new Objet(y+d+200,400,40,42));
	    }
	    else{
		l.add(new Objet(y+d+200,230,182,24));
		l.add(new Objet(y+d+400,400,40,42));
	    }
	}
    }

    //Gestion de la collision avec le tir
    public boolean collisionArriere(Tir tir){
	if(this.x < tir.x + tir.largeur || this.x > tir.x+tir.largeur){
	    return false;
	}
	return true;
    }


    //Affichage des objets
    public void afficheObjet(Graphics g){
	g.drawImage(this.imgObjet,this.x,this.y,null);
    }


    public static class Nourritures extends Objet{
	protected Image imgNourriture;
	protected ImageIcon icoNourriture;
	public Nourritures(int x, int y){
	    super(x,y,27,30);
	    this.icoNourriture = new ImageIcon(getClass().getResource("Image/nourriture.jpg"));
	    this.imgNourriture = this.icoNourriture.getImage();
	}

	//MAJ de la position de la vie
	public void move() {
	    if(Main.jeu.fond.xPos>=0 ) {
		this.x = this.x - Main.jeu.fond.dx;
	    }
	}


	public Image getImgNourriture(){
	    return this.imgNourriture;
	}


	public void afficheNourriture(Graphics g){
	    g.drawImage(this.imgNourriture,this.x,this.y,null);
	}

	//genere les vies 
	protected static void gagneVie(LinkedList<Nourritures> n){
	    Nourritures n1 = new Nourritures(3500,180);
	    n.add(n1);
	    for(int i=0; i<10;i++){
		n.add(new Nourritures(n.get(i).x+2000,180));
	    }
	}
    }
}

