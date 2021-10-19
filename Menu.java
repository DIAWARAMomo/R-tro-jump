import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class Menu extends JPanel{

    protected static JButton jouer = new JButton("Jouer");
    protected static JButton quitter= new JButton("Quitter");
    protected static JButton rejouer= new JButton("Rejouer");
    protected JLabel label,label2,label3;


    public Menu(){
	if(Main.jeu.etat == Main.jeu.etat.MENU){
	

	    this.setBackground(Color.white);
	    this.label = new JLabel("MENU");
	    
	    this.jouer.addActionListener(new ActionListener(){

		    public void actionPerformed(ActionEvent clic){
			if(clic.getSource() == jouer){	


			    Main.fenetre.dispose();
			    Main.fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			    Main.fenetre.setSize(700,360);
			    Main.fenetre.setResizable(false);
			    Main.fenetre.setLocationRelativeTo(null);
			    Main.jeu = new Jeu();
			    Main.jeu.etat = Main.jeu.etat.JEU;
			    Main.fenetre.setContentPane(Main.jeu);
			    Main.fenetre.repaint();
			    Main.fenetre.revalidate();
			    Main.fenetre.setVisible(true);
			}		
		    }
		});

	    this.quitter.addActionListener(new ActionListener(){
		    public void actionPerformed(ActionEvent clic){
			if(clic.getSource() == quitter){	
			    System.exit(0);
			}		
		    }
		});
	
	    this.add(this.label);
	    this.add(this.jouer);
	    this.add(this.quitter);
	}
	else if(Main.jeu.etat == Main.jeu.etat.PERDU){
	

	    this.setBackground(Color.white);
	    this.label = new JLabel("VOUS AVEZ PERDU");
	    this.label2 = new JLabel("Votre score est : "+Main.jeu.score.score);
	    this.label3 = new JLabel("Votre highscore est : "+Main.jeu.score.max());
	    this.rejouer.addActionListener(new ActionListener(){
		    public void actionPerformed(ActionEvent clic){
			if(clic.getSource() == rejouer){

			    Main.jeu.etat = Main.jeu.etat.MENU;
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
		});
		

	    this.quitter.addActionListener(new ActionListener(){
		    public void actionPerformed(ActionEvent clic){
			if(clic.getSource() == quitter){	
			    System.exit(0);
			}		
		    }
		});

	    this.add(this.label);
	    this.add(this.label2);
	    this.add(this.label3);
	    this.add(this.rejouer);
	    this.add(this.quitter);
	}
	else if(Main.jeu.etat == Main.jeu.etat.GAGNE){
	  
	    this.setBackground(Color.white);
 		
	    this.label = new JLabel("VOUS AVEZ GAGNÉ");
	    this.label2 = new JLabel("Votre score est : "+Main.jeu.score.score);
	    this.label3 = new JLabel("Votre highscore est : "+Main.jeu.score.max());
	    
	    this.rejouer.addActionListener(new ActionListener(){
		    
		    public void actionPerformed(ActionEvent clic){
			
			if(clic.getSource() == rejouer){	
			    Main.jeu.etat = Main.jeu.etat.MENU;
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
		});

	    this.quitter.addActionListener(new ActionListener(){
		    
		    public void actionPerformed(ActionEvent clic){
			if(clic.getSource() == quitter){	
			    System.exit(0);
			}		
		    }
		});

	    this.add(this.label);
	    this.add(this.label2);
	    this.add(this.label3);
	    this.add(this.rejouer);
	    this.add(this.quitter);
	}

    }


}
