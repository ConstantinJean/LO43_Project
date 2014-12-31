package com.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import com.model.GameManager;
import com.model.Player;
import com.model.Ressource;
import com.model.card.RessourceCard;
import com.model.port.GeneralPort;
import com.model.port.Port;
import com.model.port.SpecifiedPort;

public class TradePanel extends JPanel implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
/********************** Attributes **********************/
	private GameManager gm;
	
	private JButton exitButton;
	private JButton validateTradeButton;
	
	private Player currentPlayer;
	private int nbRCurPlayer;
	private Ressource rCurPlayer;
	
	private Player selectedPlayer;
	private boolean selectedBank;
	private Port selectedPort;
	
	private int nbROther;
	private Ressource rOther;
	
	
	
	
	
/*********************** Methods ***********************/
	public TradePanel(GameManager gm){
		super();
		this.gm = gm;
		
		currentPlayer = null;
		selectedPlayer = null;
		selectedBank = false;
		selectedPort = null;
		
		setLayout(null);
		
		exitButton = new JButton("Exit");
		exitButton.addActionListener(this);
		exitButton.setActionCommand("EXIT_PLAYER_TRADE");
		
		validateTradeButton = new JButton("Validate Trade");
		validateTradeButton.addActionListener(this);
		validateTradeButton.setActionCommand("VALIDATE_TRADE");
		validateTradeButton.setEnabled(false);
	}
	
	public void paintComponent(Graphics g){
		removeAll();
		
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		g.setColor(Color.BLACK);
		g.setFont(new Font("arial", Font.BOLD, 20));
		g.drawString("TRADING", getWidth()/11, 50);
		
		if(currentPlayer != gm.getCurrentPlayer()){
			currentPlayer = gm.getCurrentPlayer();
			nbRCurPlayer=0;
			rCurPlayer=Ressource.NO_RESSOURCE;
			
			selectedPlayer = null;
			selectedBank = false;
			selectedPort = null;
			
			nbROther=0;
			rOther=Ressource.NO_RESSOURCE;
		}
		
		int y = 30;
		JRadioButton newButton;
		// ajout des joueurs
		for(Player pl : gm.getPlayers()){
			if(pl != gm.getCurrentPlayer()){
				if(selectedPlayer == pl)
					newButton = new JRadioButton(pl.getName(), true);
				else
					newButton = new JRadioButton(pl.getName());
				newButton.addActionListener(this);
				newButton.setActionCommand("player:"+pl.getName());
				
				this.add(newButton);
				newButton.setBounds(getWidth()/2-180, y, 160, 20);
				y+=25;
			}
		}
		// ajout de la banque
		if(selectedBank)
			newButton = new JRadioButton("Bank", true);
		else
			newButton = new JRadioButton("Bank");
		newButton.addActionListener(this);
		newButton.setActionCommand("bank");
		this.add(newButton);
		newButton.setBounds(getWidth()/2, 30, 160, 20);
		// ajout des ports
		if(currentPlayer.getPorts().size()>0){
			y=55;
			// séparation des ports généraux et spécifiques
			Port generalPort = null;
			ArrayList<SpecifiedPort> specifiedPorts = new ArrayList<SpecifiedPort>();
			for(Port po : currentPlayer.getPorts()){
				if(po.getClass() == GeneralPort.class){
					if(generalPort == null){
						generalPort = po;
					}
				}
				else{
					specifiedPorts.add((SpecifiedPort)po);
				}
			}
			
			
			if(generalPort != null){
				if(selectedPort != null
						&& selectedPort.getClass() == GeneralPort.class)
					newButton = new JRadioButton("General port", true);
				else
					newButton = new JRadioButton("General port");
				newButton.addActionListener(this);
				newButton.setActionCommand("port:generalPort");
				this.add(newButton);
				newButton.setBounds(getWidth()/2, y, 160, 20);
				y+=25;
			}
			if(specifiedPorts.size()>0){
				for(SpecifiedPort po : specifiedPorts){
					if(selectedPort == po)
						newButton = new JRadioButton("Port : "+po.getRessourceType().toString(), true);
					else
						newButton = new JRadioButton("Port : "+po.getRessourceType().toString());
					newButton.addActionListener(this);
					newButton.setActionCommand("port:"+po.getRessourceType().toString());
					this.add(newButton);
					newButton.setBounds(getWidth()/2, y, 160, 20);
					y+=25;
				}
			}
		}
		
		// affichage des 2 rectangles de choix des montants du trade
		
		if(selectedPlayer!=null || selectedBank || selectedPort!=null){
			g.setFont(new Font("arial", Font.BOLD, 15));
			
			// pour le current player
			g.setColor(gm.getCurrentPlayer().getColor());
			g.fillRect(50, getHeight()/3, 400, getHeight()/3-30);
			g.setColor(Color.BLACK);
			g.drawString(gm.getCurrentPlayer().getName() + " gives", 80, getHeight()/2);
			// nombre de ressources
			y=getHeight()/3+5;
			for(int i=1 ; i<=5 ; i++){
				if(nbRCurPlayer == i)
					newButton = new JRadioButton(""+i, true);
				else
					newButton = new JRadioButton(""+i);
				newButton.addActionListener(this);
				newButton.setActionCommand("nbRCurPlayer:"+i);
				
				if(selectedBank || selectedPort!=null){
					newButton.setEnabled(false);
				}
				else{
					newButton.setEnabled(true);
				}
				
				this.add(newButton);
				newButton.setBounds(200, y, 50, (getHeight()-100)/16);
				y += (getHeight()-100)/16;
			}
			// type de ressources
			ButtonGroup rCurPlayerGroup = new ButtonGroup();
			y=getHeight()/3+5;
			for(Ressource re : Ressource.values()){
				if(re != Ressource.NO_RESSOURCE){
					newButton = null;
					if(re == rCurPlayer)
						newButton = new JRadioButton(""+re.toString(), true);
					else
						newButton = new JRadioButton(""+re.toString());
					newButton.addActionListener(this);
					newButton.setActionCommand("rCurPlayer:"+re.toString());
					
					if(selectedPort!=null && selectedPort.getClass()==SpecifiedPort.class){
						newButton.setEnabled(false);
					}
					else{
						newButton.setEnabled(true);
					}
					
					rCurPlayerGroup.add(newButton);
					this.add(newButton);
					newButton.setBounds(270, y, 80, (getHeight()-100)/16);
					y += (getHeight()-100)/16;
				}
			}
			
			
			
			// pour l'autre
			if(selectedPlayer != null)
				g.setColor(selectedPlayer.getColor());
			else
				g.setColor(Color.GRAY);
			g.fillRect(50, 2*getHeight()/3, 400, getHeight()/3-30);
			g.setColor(Color.BLACK);
			
			if(selectedPlayer != null){
				g.drawString(selectedPlayer.getName() + " gives", 80, 5*getHeight()/6);
			}
			else if(selectedBank){
				g.drawString("Bank gives", 80, 5*getHeight()/6);
			}
			else{
				g.drawString("Port gives", 80, 5*getHeight()/6);
			}
			
			// nombre de ressources
			y=2*getHeight()/3+5;
			for(int i=1 ; i<=5 ; i++){
				if(nbROther == i)
					newButton = new JRadioButton(""+i, true);
				else
					newButton = new JRadioButton(""+i);
				newButton.addActionListener(this);
				newButton.setActionCommand("nbRTrPlayer:"+i);
				
				if(selectedBank || selectedPort!=null){
					newButton.setEnabled(false);
				}
				else{
					newButton.setEnabled(true);
				}
				
				this.add(newButton);
				newButton.setBounds(200, y, 50, (getHeight()-100)/16);
				y += (getHeight()-100)/16;
			}
			// type de ressources
			ButtonGroup rTrPlayerGroup = new ButtonGroup();
			y=2*getHeight()/3+5;
			for(Ressource re : Ressource.values()){
				if(re != Ressource.NO_RESSOURCE){
					newButton = null;
					if(re == rOther)
						newButton = new JRadioButton(""+re.toString(), true);
					else
						newButton = new JRadioButton(""+re.toString());
					newButton.addActionListener(this);
					newButton.setActionCommand("rTrPlayer:"+re.toString());
					
					rTrPlayerGroup.add(newButton);
					this.add(newButton);
					newButton.setBounds(270, y, 80, (getHeight()-100)/16);
					y += (getHeight()-100)/16;
				}
			}
			
			add(validateTradeButton);
			validateTradeButton.setBounds(500, 2*getHeight()/3 - 40, 150, 40);
			if(nbRCurPlayer>0 && nbROther>0
				&& rCurPlayer!=Ressource.NO_RESSOURCE && rOther!=Ressource.NO_RESSOURCE
				&& currentPlayer.getRessourceCards(rCurPlayer) >= nbRCurPlayer
				&&
				( selectedPlayer != null && selectedPlayer.getRessourceCards(rOther) >= nbROther
						||
				selectedBank
						||
				selectedPort != null )
				){
				validateTradeButton.setEnabled(true);
			}
			else{
				validateTradeButton.setEnabled(false);
			}
		}
		
		
		
		add(exitButton);
		exitButton.setBounds(getWidth()-120, 20, 100, 30);
		
	}

	
	
	
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().contains("player")){
			String[] splitArray = null;
			splitArray = e.getActionCommand().split(":");
			for(Player pl : gm.getPlayers()){
				if(pl.getName().equals(splitArray[1])){
					selectedPlayer = pl;
					selectedBank = false;
					selectedPort = null;
				}
			}
		}
		else if(e.getActionCommand().contains("bank")){
			selectedPlayer = null;
			selectedBank = true;
			selectedPort = null;
			
			nbRCurPlayer = 4;
			nbROther = 1;
		}
		if(e.getActionCommand().contains("port")){
			selectedPlayer = null;
			selectedBank = false;
			String[] splitArray = null;
			splitArray = e.getActionCommand().split(":");
			if(splitArray[1].equals("generalPort")){
				for(Port po : currentPlayer.getPorts()){
					if(po.getClass() == GeneralPort.class){
						selectedPort = po;
						nbRCurPlayer = 3;
						nbROther = 1;
					}		
				}
			}
			else{
				for(Port po : currentPlayer.getPorts()){
					if(po.getClass() == SpecifiedPort.class
							&& ((SpecifiedPort)po).getRessourceType().toString().equals(splitArray[1])){
						selectedPort = po;
						nbRCurPlayer = 2;
						nbROther = 1;
						rCurPlayer = ((SpecifiedPort)po).getRessourceType();
					}
				}
			}
			
		}
		else if(e.getActionCommand().contains("nbRCurPlayer")){
			char c = e.getActionCommand().charAt(e.getActionCommand().length()-1);
			nbRCurPlayer = Integer.parseInt(String.valueOf(c));
		}
		else if(e.getActionCommand().contains("rCurPlayer")){
			String[] splitArray = null;
			splitArray = e.getActionCommand().split(":");
			for(Ressource r : Ressource.values()){
				if(splitArray[1].equals(r.toString())){
					rCurPlayer = r;
				}
			}
		}
		else if(e.getActionCommand().contains("nbRTrPlayer")){
			char c = e.getActionCommand().charAt(e.getActionCommand().length()-1);
			nbROther = Integer.parseInt(String.valueOf(c));
		}
		else if(e.getActionCommand().contains("rTrPlayer")){
			String[] splitArray = null;
			splitArray = e.getActionCommand().split(":");
			for(Ressource r : Ressource.values()){
				if(splitArray[1].equals(r.toString())){
					rOther = r;
				}
			}
		}
		else if (e.getActionCommand().equals("EXIT_PLAYER_TRADE")){
			gm.UpdateObserver("");
		}
		else if (e.getActionCommand().equals("VALIDATE_TRADE")){
			for(int i=0 ; i<nbRCurPlayer ; i++){
				currentPlayer.spendRessourceCard(rCurPlayer);
			}
			for(int i=0 ; i<nbROther ; i++){
				currentPlayer.getRessourceCards().add(new RessourceCard(rOther));
			}
			if(selectedPlayer!=null){
				for(int i=0 ; i<nbROther ; i++){
					selectedPlayer.spendRessourceCard(rOther);
				}
				for(int i=0 ; i<nbRCurPlayer ; i++){
					selectedPlayer.getRessourceCards().add(new RessourceCard(rCurPlayer));
				}
			}
			String otherTrader = "";
			if(selectedPlayer != null)
				otherTrader = selectedPlayer.getName();
			else if(selectedBank)
				otherTrader = "the bank";
			else
				otherTrader = "the port";
			
			JOptionPane.showMessageDialog(null, currentPlayer.getName()+" have given "
			+nbRCurPlayer+" "+rCurPlayer.toString()+" to "+otherTrader+"\n"
			+"and "+otherTrader+" have given "+nbROther+" "
			+rOther.toString()+" to "+currentPlayer.getName());
		}
		
		repaint();
	}
	
	
}
