package GVB.generateur;

import GVB.personnage.bot.Voleur;
import iut.Game;
import iut.GameItem;

import java.awt.*;
import java.util.Random;

/**
 * Cette classe permet de générer les voleurs qui aide le boss
 * @author Lucas Guillaume
 */
public class GenerateurVoleur extends iut.GameItem{
    private long timeToCreate = 2000;
    private double angle;
    private int posY;
    private String sens;//= "policeGauche";
    private int compteurVoleur = 0;

    public GenerateurVoleur(Game g) {
        super(g, "", -1, -1);
        this.sens = "voleurGauche";
    }

    /**
     * Ce deuxième constructeur s'occupe de recréé le voleur lorsqu'il change d'étage
     * @param V le boss
     * @param Y sa position en Y
     * @param X sa position en X
     * @param a sa direction (droite ou gauche)
     * @param w et sa vitesse
     */
    public GenerateurVoleur(Voleur V, int Y, int X, double a, double w, String sens) {
        super(V.getGame(), V.getItemType(), X, Y);
        this.sens = sens;
        Voleur newV = new Voleur(getGame(), X, Y, a, w, sens);
        getGame().addItem(newV);
    }

    public int getCompteurVoleur() {
        return compteurVoleur;
    }

    public long getTimeToCreate() {
        return timeToCreate;
    }

    public int getPosY() {
        return posY;
    }

    public String getSens() {
        return sens;
    }

    @Override
    public void draw(Graphics g) throws Exception {
    }

    @Override
    public boolean isCollide(GameItem gameItem) {
        return false;
    }

    @Override
    public void collideEffect(GameItem gameItem) {
    }

    @Override
    public String getItemType() {
        return "GenerateurVoleur";
    }

    /**
     * Permet d'ajouter de plus en plus de voleur lorsque le temps passe
     * @param l temps qui passe en milliseconde
     */
    @Override
    public void evolve(long l) {
        this.timeToCreate -= l;
        if (this.timeToCreate <= 0){
            this.generer();
            this.compteurVoleur++;
            this.timeToCreate = 10000 - compteurVoleur*1000;
            if (this.timeToCreate < 0){
                this.timeToCreate = 1000;
            }
        }
    }

    /**
     * Permet de générer les voleurs aléatoirement sur 3 étages
     */
    public void generer() {
        Random random = new Random();
        int t1 = random.nextInt(2)+1;

        switch (t1) {
            case 1:
                this.angle = 0;
                break;
            case 2:
                this.angle = 180;
                break;
            default:
                this.angle = 0;
        }
        int t2 = random.nextInt(3)+1;
        switch (t2) {
            case 1:
                this.posY = 440;
                break;
            case 2:
                this.posY = 260;
                break;
            case 3:
                this.posY = 80;
                break;
            default:
                this.posY = 440;
        }
        //permet de gérer le sprite en fonction du sens d'apparition
        if(this.angle == 180.0){
            this.sens = "voleurGauche";
        }
        else{
            this.sens = "voleurDroite";
        }
        //Ajoute le voleur au jeu
        Voleur voleur = new Voleur(getGame(), getGame().getWidth()/2, this.posY, this.angle, 0.1, this.sens);
        getGame().addItem(voleur);
    }
}



