import java.awt.*;
import java.util.*;
import javax.swing.*;

public class Ennemi1 extends Personnage{
	
    protected Image imgEnnemi;
    protected ImageIcon icoEnnemi;
	
    protected int dxEnnemi1,vie; // 1 quand il se deplace vers la droite et -1 vers la gauche
    protected boolean b;

    public Ennemi1(int x,int y,boolean b) {
	super(x,y,27,30); // position du champignon et sa taille
	super.versDroite = true; // il est initialement tourne vers la droite et peut marcher
	super.marche = true;
	this.dxEnnemi1 = 1;
	this.b = b;
	if(!b){
	    this.vie = 5;
	    this.icoEnnemi = new ImageIcon(getClass().getResource("Image/ennemiArretDroite.png"));
	    this.imgEnnemi = this.icoEnnemi.getImage();
	}else if(b){
	    this.vie = 10;
	    this.icoEnnemi = new ImageIcon(getClass().getResource("Image/ennemi2ArretGauche.png"));
	    this.imgEnnemi = this.icoEnnemi.getImage();
	}
    }
	
	
    //mets a jour l'emplacement des ennemis 
    public void bouge() {
	if(super.versDroite){
	    this.dxEnnemi1 = 1;
	}else {
	    this.dxEnnemi1 = -1;
	}
	super.absc += this.dxEnnemi1; // met a jour l'abscisse des ennemis afin de les deplacer
    }
	

    public void contact(Object ob) { // test collison avant quand l'ennemi est vers la droite et arriere quand vers la gauche
	if(ob instanceof Objet){
	    Objet o = (Objet)ob;
	    
	    if(super.collisionAvant(o) && this.versDroite) {
		super.versDroite = false;
		this.dxEnnemi1 = -1; //repart dans l'autre sens car notre ennemi est toujours en mouvement donc -1 le remettra vers la gauche
	    }
	    else if (super.collisionArrier(o) && !this.versDroite) {
		super.versDroite = true;
		this.dxEnnemi1 = 1;
	    }
	}
	if(ob instanceof PPrincipal){
	    PPrincipal p=(PPrincipal)ob;

	    if(super.collisionAvant(p) && estVivant){
		dxEnnemi1=-1;
	    }
	    else if (super.collisionArrier(p) && estVivant){
		dxEnnemi1=-1;
	    }
	}
    }

    //Affichage des ennemis
    public void afficheEnnemi(Graphics g){
	if(!this.b){

	    if(this.estVivant){
		g.drawImage(this.deplacement("ennemi",45),this.absc,this.ord,null);
	    }
	    else{
		g.drawImage(null,this.absc,this.ord + 20,null);
	    }	
	}
	else if(this.b){

	    if(this.estVivant){
		g.drawImage(this.deplacement("ennemi2",45),this.absc,this.ord,null);
	    }
	    else{
		g.drawImage(null,this.absc,this.ord + 20,null);
	    }	
	}
    }

    //Gestion de la collision avec le tir cad que lorsque l'ennemi va etre touche par un tir il perdra une vie
    public boolean collisionTir(Object ob){
	if(ob instanceof Tir){
	    Tir t=(Tir)ob;

	    if(super.collisionAvant(t) && estVivant){

		if(vie <= 0){
		    estVivant = false;
		}
		else{
		    vie -= 1;
		}	
		return true;
	    }
	    else if (super.collisionArriere(t) && estVivant){

		if(vie <= 0){
		    estVivant = false;
		}
		else{
		    vie -= 1;
		}
		return true;
	    }
	}
	return false;
    }


    //genere aleatoirement les ennemis
    public static void ennemis(LinkedList<Ennemi1> e ){
	Ennemi1 e1 = new Ennemi1(900,300,false);
	Ennemi1 e2 = new Ennemi1(8000,277,true);
	e.add(e1);
	e.add(e2);
	int i=0;
	int y=0;

	while(i <= 60){

	    i = e.size()-1;
	    y = e.get(i).absc;
	    int d = (int)(Math.random() * (950-600)) + 600;
	    int d2 = (int)(Math.random() * (1000-700)) + 700;

	    if( d>=750){
		e.add(new Ennemi1(y+d,300,false));
	    }
	    if( d2>=800){
		e.add(new Ennemi1(y+d2,277,true));
	    }
	}
    }
	
    
}

