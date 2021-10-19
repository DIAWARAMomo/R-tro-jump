import java.awt.*;
import javax.swing.ImageIcon;
public class Personnage {
    protected int taille,largeur,absc,ord,compteur;
    protected boolean marche,versDroite,estVivant;
  

    public Personnage(int x,int y,int t,int l){
	this.taille=t;
	this.largeur=l;
	this.marche= false;
	this.versDroite=true;
	this.estVivant=true;
	this.absc=x;
	this.ord=y;
    }
    
    	
    public Image deplacement(String s, int b){
	// b est la frequence
	String str;
	ImageIcon  ico;
	Image img;
	    
	if(!this.marche){
	    if(this.versDroite){
		str="Image/"+ s +"ArretDroite.png";
	    }
	    else{
		str="Image/"+ s +"ArretGauche.png";
	    }
	}
	else{
	    this.compteur+=1;
	    if(compteur/b==0){
		if(this.versDroite){
		    str = "Image/" + s + "ArretDroite.png";
		}
		else {
		    str = "Image/" + s + "ArretGauche.png";
		}
	    }
	    else {
		if(this.versDroite){
		    str = "Image/" + s + "MarcheDroite.png";
		}
		else{
		    str = "Image/" + s + "MarcheGauche.png";
		}
		if(this.compteur ==2 * b){
		    this.compteur = 0;
		}
	    }
	}
	ico = new ImageIcon(getClass().getResource(str));
	img = ico.getImage();
	return img;
    }

    //MAJ de la position du perso en fonction de l'image du fond car c'est le fond qui se deplace et non le perso
    public void move(){
	if(Main.jeu.fond.xPos>=0 ){
	    this.absc = this.absc - Main.jeu.fond.dx;
	}
    }
	
    public boolean collisionAvant(Objet o) {

	if(this.absc + this.largeur <o.x || this.absc + this.largeur > o.x || this.ord + this.taille <= o.y || this.ord >= o.y + o.hauteur){
	    return false;
	}
	else {
	    return true;
	}   
    }



    public boolean collisionAvant(Personnage p) {
	if(this.absc + this.largeur <p.absc || this.absc + this.largeur > p.absc || this.ord + this.taille <= p.ord || this.ord >= p.ord + p.taille){
	    return false;
	}
	else {
	    return true;
	}
    }


    public boolean collisionAvant(Tir tir){

	if(this.absc + this.largeur < tir.x || this.absc + this.largeur > tir.x){
	    return false;
	}
	return true;
    }

    
    public boolean collisionDessous(Objet o) {
	if(this.absc + this.largeur < o.x+5 || this.absc > o.largeur+o.x-5 || this.ord + this.taille < o.y || this.ord +this.taille> o.y) {
	    return false;
	}
	else {
	    return true;
	}
    }

    public boolean collisionDessous(Personnage p) {
	if(this.absc + this.largeur < p.absc+5 || this.absc > p.largeur+p.absc-5 || this.ord + this.taille < p.ord || this.ord +this.taille> p.ord) {
	    return false;
	}
	else {
	    return true;
	}
    }


    public boolean collisionDessus(Objet o) {
	if(this.absc+this.largeur < o.x-15 || this.absc >o.x+o.largeur-15 || this.ord < o.hauteur + o.y || this.ord > o.y + o.hauteur+5){
	    return false;
	}
	else {
	    return true;
	}
    }

    public boolean collisionDessus(Personnage p) {
	if(this.absc+this.largeur < p.absc-5 || this.absc >p.absc+p.largeur-15 || this.ord < p.taille + p.ord || this.ord > p.ord + p.taille+5){
	    return false;
	}
	else {
	    return true;
	}
    }



    public boolean collisionArrier(Objet o) {
	if(this.absc >o.x+o.largeur || this.absc + this.largeur < o.x+o.largeur-5 || this.ord + this.taille <= o.y || this.ord >= o.y + o.hauteur){
	    return false;
	}
	else {
	    return true;
	}   
    }


    public boolean collisionArrier(Personnage p) {
	if(this.absc >p.absc+p.largeur || this.absc + this.largeur < p.absc+p.largeur-5 || this.ord + this.taille <= p.ord || this.ord >= p.ord + p.taille){
	    return false;
	}
	else {
	    return true;
	}   
    }



    public boolean collisionArriere(Tir tir){
	if(this.absc < tir.x + tir.largeur || this.absc > tir.x+tir.largeur){
	    return false;
	}
	return true;
    }

    
    public boolean proche(Object ob){
	if (ob instanceof Objet){
	    Objet o= (Objet)ob;
	    return (( this.absc >= o.x-5 && this.absc<= o.x+o.largeur+5) || (this.absc+this.largeur>=o.x-5 && this.largeur<=o.x+o.largeur+5));
	}
	if(ob instanceof Personnage){
	    Personnage p = (Personnage)ob;
	    return(( this.absc >= p.absc-5 && this.absc<= p.absc+p.largeur+5) || (this.absc+this.largeur>=p.absc-5 && this.largeur<=p.absc+p.largeur+5));
	}
	return false;
    }
}    
