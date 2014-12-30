package com.model;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.controller.Controller;
import com.model.card.*;
import com.observer.*;
import com.view.ColorConstants;
import com.view.Window;

public class GameManager implements Observable{
	
/********************** Attributes **********************/
	private ArrayList<Observer> observers;
	
	private Map map;
	private ArrayList<Player> players;
	private int nbPlayer;
	
	
	//Ces attributs permettent au game manager de se situer dans la partie
	private int currentTurn;
	private int diceValue;
	
	private Player currentPlayer;
	private boolean placementPhase;
	private boolean placingUV;
	private boolean placingUVplus;
	private boolean placingCC;
	private boolean rollingDice;
	private boolean buying;
	private boolean devCardPlayed;
	private boolean movingLayaboutMate;
	private boolean canEndTurn;
	private Object lastObjectPlaced;
	
	
	
/*********************** Methods ***********************/
	
	// ---- Main ----
	public static void main(String[] args) {
		GameManager gm = new GameManager(2);
		Controller c = new Controller(gm);
		Window w = new Window(gm, c);
		
		gm.addObserver(w);
		
	}
	
	// ---- Contructor ----
	public GameManager(int nbPlayer){
		// Le gameManager implemente l'interface Observable
		// on pourra donc mettre a jour ses observer depuis la fonction updateOberserver
		observers = new ArrayList<Observer>();
		
		// Initialisation de la map et des joueurs
		map = new Map();
		players = new ArrayList<Player>();
		this.nbPlayer = nbPlayer;
		
		// On crée les joueurs, on leur ajoute chacun un nom et une couleur
		for(int i=0 ; i<nbPlayer ; i++){
			switch(i){
			case 0:
				players.add(new Player("Player "+ (i+1), new Color(ColorConstants.p1R, ColorConstants.p1G, ColorConstants.p1B)));
				break;
			case 1:
				players.add(new Player("Player "+ (i+1), new Color(ColorConstants.p2R, ColorConstants.p2G, ColorConstants.p2B)));
				break;
			case 2:
				players.add(new Player("Player "+ (i+1), new Color(ColorConstants.p3R, ColorConstants.p3G, ColorConstants.p3B)));
				break;
			case 3:
				players.add(new Player("Player "+ (i+1), new Color(ColorConstants.p4R, ColorConstants.p4G, ColorConstants.p4B)));
				break;
			default :
				players.add(new Player("Player "+ (i+1), Color.WHITE));
				break;
			}
			
			
			
		}
		
		// Initialisation des autres variables
		currentTurn = 0;
		currentPlayer = players.get(0);
		devCardPlayed = false;
		diceValue = 0;
		placementPhase = true;
		placingUV = false;
		placingUVplus = false;
		placingCC = false;
		rollingDice = false;
		movingLayaboutMate = false;
		canEndTurn = false;
		lastObjectPlaced = null;
		
	}
	
	// la fontion qui démarre la partie
	public void startGame(){
		// C'est le premier tour de jeu
		currentTurn = 1;
		
		// On tire aléatoirement l'ordre de passage
		int rand = (int)(Math.random()*nbPlayer);
		int order=0;
		for(int i=rand ; i<nbPlayer ; i++){
			players.get(i).setPassageOrder(order);
			order++;
		}
		for(int i=0 ; i<rand ; i++){
			players.get(i).setPassageOrder(order);
			order++;
		}
		currentPlayer = players.get(rand);
		
		UpdateObserver("the first player is "+ currentPlayer.getName()+ ", clic on a point to place an UV");
		placingUV = true;
	}
	
	// Cette fonction sera appelé par le controller a chaque fois que l'action en cours sera terminé
	// Elle permet au game manager de savoir quelle sera l'action suivant.
	public void nextAction(){
		
		if(placementPhase){
			if(placingUV){
				// si on est dans la phase de placement et qu'on vient de placer une UV :
				placingUV = false;
				currentPlayer.setVictoryPoint(currentPlayer.getVictoryPoint()+1);
				// si on est dans le deuxième tour de table alors le joueur gagne des ressources
				if(currentTurn>nbPlayer){
					map.giveInitialRessources((UV)lastObjectPlaced);
				}
				// la prochaine action sera de placer un CC
				placingCC = true;
				UpdateObserver("now clic on a path to place a CC");
			}
			else if(placingCC){
				// si on était en phase de placement et qu'on vient de placer un CC :
				placingCC = false;
				// si la phase de placement continue :
				if(currentTurn<nbPlayer*2){
					// si on est dans le premier tour de table, on passe au joueur suivant
					if(currentTurn < nbPlayer){
						nextPlayer();
					}
					// sinon on revient au joueur précédant
					else if(currentTurn > nbPlayer){
						precedentPlayer();
					}
					currentTurn++;
					
					// Ce joueur pourra placer une UV
					placingUV = true;
					UpdateObserver(currentPlayer.getName() + ", clic on a point to place an UV");
				}
				// si on vient de terminer la phase de placement :
				else{
					placementPhase = false;
					// on affiche le panel de transition et on passe au tour suivant
					UpdateObserver("trans");
					currentTurn ++;
				}
			}
		}
		// si on est pas dans la phase de placement :
		else{
			// si on vient de lancer les dés
			if(rollingDice){
				rollingDice = false;
				// on calcul le lancé
				int d1 = (int)(Math.random()*6+1), d2 = (int)(Math.random()*6+1);
				diceValue = d1+d2;
				// si on a obtenu 7, la map produit des ressources
				if(diceValue != 7){
					map.produceRessources(diceValue);
					UpdateObserver("Dice value : "+diceValue+" ("+d1+"+"+d2+")");
					canEndTurn = true;
				}
				// sinon tous les joueurs perdent la moitié de leu cartes
				// et le current player déplace le binome glandeur
				else{
					loseHalfCards();
					movingLayaboutMate = true;
					UpdateObserver("Dice value : "+diceValue+" ("+d1+"+"+d2+"), everybody have lost the half of there cards, click on an hexagon to move the layaboute mate");
				}
			}
			// si le joueur vient de placer un CC
			else if(placingCC){
				placingCC = false;
				canEndTurn = true;
				UpdateObserver("");
			}
			// si le joueur vient de placer une UV
			else if(placingUV){
				placingUV = false;
				canEndTurn = true;
				UpdateObserver("");
			}
			// si le joueur vient de placer une UV**
			else if (placingUVplus){
				placingUVplus = false;
				canEndTurn = true;
				UpdateObserver("");
			}
			// si le joueur vient de déplacer le binome glandeur
			else if(movingLayaboutMate){
				movingLayaboutMate = false;
				//on vole un joueur
				canEndTurn = true;
				steal();
				UpdateObserver("");
			}
			// si on vient de commencer le tour d'un joueur :
			else{
				canEndTurn = false;
				// ce joueur peux lancer les dés
				rollingDice = true;
				UpdateObserver(currentPlayer.getName() + ", click to roll dice");
			}
		}
	}
	
	// c'est la methode qui est appelé quand on appuis sur le bouton "next turn"
	public void nextTurn(){
		canEndTurn = false;
		nextPlayer();
		UpdateObserver("trans");
	}
	
	// c'est la methode qui est appelé quand on appuis sur le bouton "buy"
	public void buy(){
		buying = true;
		canEndTurn = false;
		UpdateObserver("shop");
	}
	
	// la methode qui est appelé quand on ferme la fenetre de shop
	public void shopClosed(){
		buying = false;
		canEndTurn = true;
		UpdateObserver("");
	}
	
	// la méthode pour acheter un CC
	public void buyCC(){
		buying = false;
		placingCC = true;
		UpdateObserver("click on a path to place a CC");
		
		currentPlayer.spendRessourceCard(Ressource.BEER);
		currentPlayer.spendRessourceCard(Ressource.FOOD);
	}
	
	public void buyUV(){
		buying = false;
		placingUV = true;
		UpdateObserver("click on a point to place an UV");
		
		currentPlayer.spendRessourceCard(Ressource.BEER);
		currentPlayer.spendRessourceCard(Ressource.FOOD);
		currentPlayer.spendRessourceCard(Ressource.COURS);
		currentPlayer.spendRessourceCard(Ressource.COFFEE);

		currentPlayer.setVictoryPoint(currentPlayer.getVictoryPoint()+1);
	}
	
	public void buyUVplus(){
		buying = false;
		placingUVplus = true;
		UpdateObserver("click on an UV to turn it into UV**");
		
		currentPlayer.spendRessourceCard(Ressource.COURS);
		currentPlayer.spendRessourceCard(Ressource.COURS);
		currentPlayer.spendRessourceCard(Ressource.SLEEP);
		currentPlayer.spendRessourceCard(Ressource.SLEEP);
		currentPlayer.spendRessourceCard(Ressource.SLEEP);
		
		currentPlayer.setVictoryPoint(currentPlayer.getVictoryPoint()+1);
	}
	
	public void buyDevCard(){
		buying = false;
		canEndTurn = true;
		currentPlayer.spendRessourceCard(Ressource.COURS);
		currentPlayer.spendRessourceCard(Ressource.COFFEE);
		currentPlayer.spendRessourceCard(Ressource.SLEEP);
		
		int rand = (int)(Math.random()*25)+1;
		if(rand <=5 ){
			currentPlayer.getDevelopmentCards().add(new VictoryPointCard());
			UpdateObserver("You drew a card of victory point");
		}
		else if(rand <=7){
			currentPlayer.getDevelopmentCards().add(new BuildingCCCard());
			UpdateObserver("You drew a card of buiding two CC");
		}
		else if(rand <= 9){
			currentPlayer.getDevelopmentCards().add(new DiscoveryCard());
			UpdateObserver("You drew a card Discovery");
		}
		else if(rand <= 11){
			currentPlayer.getDevelopmentCards().add(new MonopolyCard());
			UpdateObserver("You drew a card of Monopoly");
		}
		else{
			currentPlayer.getDevelopmentCards().add(new ElderCard());
			UpdateObserver("You drew a Elder");
		}
	}
	
	// ---- methodes privées ----
	
	// tous les joueurs perdent la moitié de leurs cartes si ils en possède plus de 7
	private void loseHalfCards(){
		for(Player pl : players){
			if(pl.getRessourceCards().size() > 7){
				int nbLostCard = pl.getRessourceCards().size()/2;
				
				for(int i=0 ; i<nbLostCard ; i++){
					int rand = (int)(Math.random()*pl.getRessourceCards().size());
					pl.getRessourceCards().remove(rand);
				}
			}
		}
	}
	
	private void steal(){
		// initailisation de la liste des cibles
		ArrayList<String> targetNames = new ArrayList<String>();
		// on parcourt les UV adjacentes au binome glandeur
		for(Point po : map.getLayaboutMate().getPos().getPoints()){
			if(po.getUV() != null){
				// si le propriétaire de l'UV n'est pas l'attaquant ou déja compté, on l'ajoute a la liste
				Player owner = po.getUV().getPlayer();
				if(owner != currentPlayer && !targetNames.contains(owner.getName())){
					String name = owner.getName();
					targetNames.add(name);
				}
			}
		}
		String targetName = (String)JOptionPane.showInputDialog(null
				, "chose your target"
				, "target"
				, JOptionPane.PLAIN_MESSAGE
				, null
				, targetNames.toArray()
				, "");
		
		if (targetName == null){
			// si le joueur n'a choisi personne, on recommence
			UpdateObserver("you have to choose a target");
			steal();
		}
		else{
			// on trouve le joueur a partir de son nom
			Player target = null;
			for(Player pl : players){
				if(pl.getName() == targetName){
					target = pl;
				}
			}
			
			// le joueur vole une ressource à la cible si il en possède une
			int nbCard = target.getRessourceCards().size();
			if(nbCard>0){
				int rand = (int)(Math.random()*nbCard);
				RessourceCard cardStolen = target.getRessourceCards().get(rand);
				target.getRessourceCards().remove(rand);
				currentPlayer.getRessourceCards().add(cardStolen);
				UpdateObserver("you stole a card "+cardStolen.getType());
			}
			else{
				UpdateObserver("the target doesn't have ressource card");
			}
		}
		
		
	}
	
	//le current player passe au joueur suivant
	private void nextPlayer(){
		if(currentPlayer == players.get(nbPlayer-1)){
			currentPlayer = players.get(0);
		}
		else{
			currentPlayer = players.get(players.indexOf(currentPlayer)+1);
		}
	}
	
	//le current player passe au joueur précedant
	private void precedentPlayer(){
		if(players.indexOf(currentPlayer)==0){
			currentPlayer = players.get(nbPlayer-1);
		}
		else{
			currentPlayer = players.get(players.indexOf(currentPlayer)-1);
		}
	}
	
	// ---- getters and setters ----
	public Map getMap(){
		return map;
	}
	public ArrayList<Player> getPlayers(){
		return players;
	}
	public int getNbPlayer(){
		return nbPlayer;
	}
	public Player getCurrentPlayer(){
		return currentPlayer;
	}
	public int getCurrentTurn() {
		return currentTurn;
	}
	public boolean isPlacementPhase() {
		return placementPhase;
	}
	public boolean isPlacingUV() {
		return placingUV;
	}
	public boolean isPlacingUVplus() {
		return placingUVplus;
	}
	public boolean isPlacingCC() {
		return placingCC;
	}
	public boolean isRollingDice(){
		return rollingDice;
	}
	public boolean isMovingLayaboutMate(){
		return movingLayaboutMate;
	}
	public boolean isBuying(){
		return buying;
	}
	public boolean canEndTurn(){
		return canEndTurn;
	}
	public Object getLastObjectPlaced() {
		return lastObjectPlaced;
	}
	public void setLastObjectPlaced(Object lastObjectPlaced) {
		this.lastObjectPlaced = lastObjectPlaced;
	}

	
	
	// ---- fonctions de l'interface observable ----
	public void addObserver(Observer obs) {
		observers.add(obs);
	}
	public void removeObservers() {
		observers = new ArrayList<Observer>();
	}
	public void UpdateObserver(String msg) {
		for(Observer obs : observers){
			obs.update(msg);
		}
	}

}
