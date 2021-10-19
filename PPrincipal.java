import java.awt.*;
import javax.swing.*;
public class PPrincipal extends Personnage {
    protected Image imgp;
    protected ImageIcon icop;
    protected boolean saut,tir;
    protected static int vie=100000;
    protected static int absc1=0;

  
    	
	
    public PPrincipal(int x,int y) {
	super(x,y,48,25);
	this.icop = new ImageIcon("Image/persoMarcheDroite.png");
	this.imgp = icop.getImage();
	this.saut = false;
	this.tir = false;
    }

    


    @Override
    public Image deplacement(String s, int b){  //redefinition du deplacement specifique a notre personnage different des ennemis
    	// b est la frequence
    	String str;
    	ImageIcon  ico;
    	Image img;
    	    
    	if(!this.marche || Main.jeu.fond.xPos<=0) {// personnage arreter et a gauche de l'ecran
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
    		else{
		    str = "Image/" + s + "ArretGauche.png";
		}
    	    }
	    else {
		if(this.versDroite){
		    str = "Image/" + s + "MarcheDroite.png";
		}
    		else {
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


    //GEstion du saut du perso
    public Image saute(){
    	ImageIcon ico;
    	Image img;
    	String s;

	if(this.absc1 <= 200){ 
	    this.absc1+=1;
	    int w = 101*this.absc1*this.absc1;
	    int z = 202*this.absc1;
	    this.ord = 275+(w/10000)-(z/100); //saut du perso controle par une fonction parabollique

	    if(this.versDroite){
		s = "Image/persoMarcheDroite.png";
		// vu qu'on peut sauter vers la droite
       	    }else{
		s = "Image/persoMarcheGauche.png"; }// idem vers la gauche
	}
	else{
	    if(this.versDroite){
		s = "Image/persoArretDroite.png";
	    }
	    else{
		s = "Image/persoArretGauche.png";
	    }
	    this.saut = false;
	    this.absc1 =0 ;
        }
	ico = new ImageIcon(getClass().getResource(s));
	img = ico.getImage();
	return img;	
	
    }

    //Gestion de toutes les collisions possibles avec les ennemis et les objets et leurs consequences (perdre de la vie...)
    public void contact( Object ob){
	if( ob instanceof Objet){
	    Objet o= (Objet)ob;
	    if( (super.collisionAvant(o) && this.versDroite) || (super.collisionArrier(o) && !this.versDroite)){
		Main.jeu.fond.dx = 0;
		this.marche = false;
	    }
	    if(super.collisionDessous(o) && this.saut){
		// si le personnage saute le sol devient la hauteur de l'objet
		Main.jeu.fond.ySol = o.y;
	    }
	    else if(!super.collisionDessous(o)){
		Main.jeu.fond.ySol = 293;
		if(!this.saut){
		    this.ord = 275;
		}
	    }
	    if( super.collisionDessus(o) ){
		Main.jeu.fond.hauteurPlafond = o.y+o.hauteur;
	    }
	    else if (!super.collisionDessus(o) && !this.saut){
		Main.jeu.fond.hauteurPlafond = 0;
	    }else if( o.largeur == 40 && o.hauteur == 42 || super.collisionDessus(o) && o.largeur == 40 && o.hauteur == 42){
		Objet.piege(o);	
	    }
	}
	else if( ob instanceof Ennemi1){
	    Ennemi1 d= (Ennemi1)ob;
	    if( super.collisionAvant(d) || super.collisionArrier(d) || super.collisionDessus(d)){
		if(!d.b){
		    this.vie -= 1;
		}else if(d.b){
		    this.vie -= 3;
		}
		Jeu.Vie.setValue(this.vie);
		if(this.vie<=0){
		    this.marche = false;
		    this.estVivant = false;
		}
	    }
	}
    }
    
    //Lorsque le perso touche une vie
    public boolean contactN( Objet c){
	if(super.collisionAvant(c) || super.collisionArrier(c) || super.collisionDessous(c) || super.collisionDessus(c)) {
	    if(vie <=100000){
		vie+=8000;
		Jeu.Vie.setValue(vie);
		Main.jeu.score.score += 15;
	    }
	    return true;
	}
	return false;
    }


    //Affichage du perso
    public void affiche(Graphics g){
	if(this.saut){
	    g.drawImage(this.saute(),this.absc,this.ord,null);
	}else{
	    g.drawImage(this.deplacement("perso",30),this.absc,this.ord,null);
	}
    }
}
