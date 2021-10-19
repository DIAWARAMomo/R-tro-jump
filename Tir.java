import javax.swing.*;
import java.awt.*;
public class Tir{
    protected int x,y,hauteur,largeur;
    protected ImageIcon tir,tir2;


    public Tir(int x, int y){
	this.x = x;
	this.y = y;
	this.hauteur = 20;
	this.largeur = 23;
	this.tir = new ImageIcon(getClass().getResource("Image/tir.png"));
	this.tir2 = new ImageIcon(getClass().getResource("Image/tir2.png"));
    }

    //gere l'abscisse du tir
    public int estTire(){
	if(Main.jeu.perso.versDroite){
	    return this.x += 2;
	}if(!Main.jeu.perso.versDroite){
	    return this.x -=2;
	}
	return this.x;
    }

    //Affichage du tir
    public void affiche(Graphics g){
	int pos = Main.jeu.perso.ord;
	if(Main.jeu.perso.versDroite){
	    g.drawImage(this.tir.getImage(),this.estTire(),pos+25,null);
	}else{
	    g.drawImage(this.tir2.getImage(),this.estTire(),pos+25,null);
	}
    }

    //Gestion de la collision du tir avec les ennemis et les objets
    public void collisionObjet(Object ob){
	if(ob instanceof Ennemi1){
	    Ennemi1 e=(Ennemi1)ob;
	    if(e.estVivant){
		if(e.vie <= 0 && !e.b){
		    e.estVivant = false;
		    Main.jeu.score.score +=30; 
		}else if (e.vie <= 0 && e.b){
		    e.estVivant = false;
		    Main.jeu.score.score += 50; 
		}else{
		    e.vie -= 1;
		}	
	    }
	}
    }


    //Gestion de toutes les collisions possibles du tir
    public boolean collisionArriere(Objet o){
	return(this.x > o.x - this.largeur && this.x < o.x);
    }

    
    public boolean collisionArriere(Personnage p){
	return(this.x > p.absc - this.largeur && this.x < p.absc);
    }

    
    public boolean collisionAvant(Objet o){
	return(this.x > o.x && this.x< o.x + this.largeur);
    }
    

    public boolean collisionAvant(Personnage p){
	return(this.x > p.absc && this.x< p.absc + this.largeur);
    }


    public boolean collisionEcran(){
	return (this.x + this.largeur > 800 || this.x + this.largeur < 0);
    }

}
