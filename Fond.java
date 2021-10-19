import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


public class Fond{
	
    protected ImageIcon iconeFond;
    protected Image imgFond;
    protected Image imgFond2;
    protected int xFond1;
    protected int xFond2;
    protected int dx;
    protected int xPos;
    protected int ySol; //hauteur sol
    protected int hauteurPlafond; //hauteur du plafond courant
       


    public Fond() {
	this.xFond1 = -50;
	this.xFond2 = 750;
	this.dx = 0;
	this.xPos = -1;
	this.ySol = 293;
	this.hauteurPlafond = 0;
	iconeFond = new ImageIcon(getClass().getResource("Image/fond.png"));
	this.imgFond = this.iconeFond.getImage();
	this.imgFond2 = this.iconeFond.getImage();
	
    }
	

    //Affichage du fond
    public void affiche(Graphics g){
	g.drawImage(this.imgFond,this.xFond1,0,null);
	g.drawImage(this.imgFond2, this.xFond2,0,null);
    }


    //MAJ de la position de l'image du fond
    public void movingFond() {
	if(this.xPos >= 0 ) {
	    this.xPos = this.xPos + this.dx;		
	    this.xFond1 = this.xFond1 - this.dx;
	    this.xFond2 = this.xFond2 - this.dx;
	}
	if(this.xFond1 == -800){
	    this.xFond1 = 800;
	}
	else if (this.xFond2 == -800){
	    this.xFond2 = 800;
	}
	else if (this.xFond1 == 800){
	    this.xFond1 = -800;
	}
	else if (this.xFond2 == 800){
	    this.xFond2 = -800;
	}
    }

}
