import jeu.audio.Audio;
import java.awt.event.*;
import javax.swing.*;
public class Action implements KeyListener{
    
    protected Jeu jeu;

    public Action(Jeu f){
	this.jeu=f;
    }
	
    @Override
    public void keyPressed(KeyEvent e) {
	if(jeu.etat == jeu.etat.JEU){
	    if(e.getKeyCode() == KeyEvent.VK_RIGHT) {//le perso avance a droite
		if(jeu.fond.xPos == -1) {
		    jeu.fond.xPos = 0;
		    jeu.fond.xFond1 = -50;
		    jeu.fond.xFond2 = 750;
		}
		jeu.perso.marche = true;
		jeu.perso.versDroite = true;
		jeu.fond.dx = 1 ;
		jeu.repaint();
	    } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {//le perso avance a gauche	
		jeu.perso.marche = true;
		jeu.perso.versDroite = false;
		jeu.fond.dx = -1;
		jeu.repaint();
	    }
	    if(e.getKeyCode() == KeyEvent.VK_UP){//perso saute
		jeu.perso.saut = true;
		Audio.playSound("/Audio/saut.wav");
		jeu.repaint();		
	    }
	    if(e.getKeyCode() == KeyEvent.VK_SPACE && !this.jeu.perso.saut){//le perso ne peut pas tiré en sautant
		Audio.playSound("/Audio/tir.wav");
		jeu.perso.tir = true;
		jeu.estTir = true;
		Tir tir = new Tir(Main.jeu.perso.absc,300);
		jeu.listeTir.add(tir);
		jeu.repaint();
	    }
	}
    }

    //Lorsque l'on relache une touche
    @Override
    public void keyReleased(KeyEvent e) {
	jeu.perso.marche = false;
	jeu.fond.dx = 0;
	jeu.perso.tir = false;
    }

    
    @Override
    public void keyTyped(KeyEvent e){
    }


}
