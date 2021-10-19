import java.util.LinkedList;
public class Score{
    protected int score;
    protected LinkedList<Score> liste = new LinkedList<Score>();

    public Score(){
	this.score=0;
    }

    public void highScore(){
	this.score = max();
    }

    //Permet de connaitre le highscore parmis toutes les parties jouees
    public int max(){
	for (int i = 0; i<liste.size();i++){
	    if(liste.get(i).score > this.score){
		return liste.get(i).score;
	    }
	}
	return this.score;
    }

}
