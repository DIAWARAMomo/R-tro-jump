import java.util.*;
import javax.swing.*;
import java.awt.*;
public class Controleur{

    protected Jeu jeu;
 

    public Controleur(Jeu jeu){
	this.jeu = jeu;
    }


    public void afficheControleur(Graphics g){
	if(this.jeu.etat == this.jeu.etat.JEU){
	    
	    //Affiche le perso
	    this.jeu.perso.affiche(g);

	    if(this.jeu.perso.estVivant){	
	
		//On parcours la liste d'objet pour gerer la collision entre le perso et les objets, le perso et les ennemis, et les ennemis avec les objets
		for ( int i = 0; i<this.jeu.listObjet.size();i++){

		    if(this.jeu.perso.proche(this.jeu.listObjet.get(i))){ 
			this.jeu.perso.contact(this.jeu.listObjet.get(i));
		    }

		    for( int j=0;j<this.jeu.listEnnemi.size();j++){

			if(this.jeu.listEnnemi.get(j).proche(this.jeu.listObjet.get(i))){
			    this.jeu.listEnnemi.get(j).contact(this.jeu.listObjet.get(i));
			}

			if(this.jeu.perso.proche(this.jeu.listEnnemi.get(j))){
			    this.jeu.perso.contact(this.jeu.listEnnemi.get(j));
			}

			if(!this.jeu.listEnnemi.get(j).estVivant){
			    this.jeu.listEnnemi.remove(this.jeu.listEnnemi.get(j));
			}
		    }
		    this.jeu.listObjet.get(i).move();
		}
		
		//On gere la collision du perso avec les vies
		for( int j=0;j<this.jeu.listNourritures.size();j++){

		    if(this.jeu.perso.proche(this.jeu.listNourritures.get(j))){

			if(this.jeu.perso.contactN(this.jeu.listNourritures.get(j))){
			    this.jeu.listNourritures.remove(this.jeu.listNourritures.get(j));	
			}
		    }
		    this.jeu.listNourritures.get(j).move();
		}


		//On met a jour la position des ennemis dans le jeu	
		for ( int i = 0; i<this.jeu.listEnnemi.size();i++){

		    if(this.jeu.c%2==0){
			this.jeu.listEnnemi.get(i).bouge();
		    }
		    this.jeu.listEnnemi.get(i).move();
		}
		

		//Lorsque le perso tire cad lorsque le joueur presse la touche espace
		if(this.jeu.estTir == true){
		    int a = 0;
		    while(a<this.jeu.listeTir.size()){

			//gestion de l'affichage du tir
			this.jeu.tir = this.jeu.listeTir.get(a);
			this.jeu.tir.affiche(g);

			if(this.jeu.listeTir.size() > 0){
			    //on parcours la liste des ennemis pour savoir lequels d'entre eux a ete touche par le tir
			    for(int l = 0;l<this.jeu.listEnnemi.size() && (this.jeu.listEnnemi.get(l).absc < this.jeu.perso.absc +1000 || this.jeu.listEnnemi.get(l).absc > this.jeu.perso.absc -1000) ;l++){
				//on parcours la liste de tir pour savoir quel tir a touche un ennemi pour le supprimer de la liste
				for(int i = 0;i<this.jeu.listeTir.size();i++){

				    if(this.jeu.listeTir.size() >0 && this.jeu.listeTir.get(i).collisionArriere(this.jeu.listEnnemi.get(l)) ){

					this.jeu.listeTir.get(i).collisionObjet(this.jeu.listEnnemi.get(l));
					this.jeu.listeTir.remove(this.jeu.listeTir.get(i));

				    }else if( this.jeu.listeTir.size() >0 && this.jeu.listeTir.get(i).collisionAvant(this.jeu.listEnnemi.get(l)) ){

					this.jeu.listeTir.get(i).collisionObjet(this.jeu.listEnnemi.get(l));
					this.jeu.listeTir.remove(this.jeu.listeTir.get(i));

				    }
				    //le break sert à sortir de la boucle car il est possible qu'un tir soit supprimer 2 fois 
				    break;
				}
			    }//On parcours la liste d'objet pour savoir quel objet est touche par un tir
			    for(int j = 0; j<this.jeu.listObjet.size() && (this.jeu.listObjet.get(j).x < this.jeu.perso.absc +1000 || this.jeu.listObjet.get(j).x > this.jeu.perso.absc -1000);j++){
				//On parcours la liste de tir pour savoir quel tir a touche un objet
				for(int i = 0; i < this.jeu.listeTir.size();i++){

				    if(this.jeu.listeTir.size() >0 && this.jeu.listeTir.get(i).collisionAvant(this.jeu.listObjet.get(j)) ){

					this.jeu.listeTir.remove(this.jeu.listeTir.get(i));

				    }else if (this.jeu.listeTir.size() >0 && this.jeu.listeTir.get(i).collisionArriere(this.jeu.listObjet.get(j)) ){

					this.jeu.listeTir.remove(this.jeu.listeTir.get(i));

				    }	
				    break;
				}
			    }
			    //Si le tir n'a atteint aucun objet ni ennemi mais qu'il sort du champ de vision du jeu alors il est supprim
			    if(this.jeu.listeTir.size() >0 && this.jeu.listeTir.get(a).collisionEcran()){

				this.jeu.listeTir.remove(this.jeu.listeTir.get(a));

			    }
			    break;
			}
			a +=1;
		    }
		}

	    }
	}
	
    }
}
