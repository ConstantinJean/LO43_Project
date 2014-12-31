package com.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.print.attribute.standard.JobMessageFromOperator;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import com.controller.Controller;
import com.model.GameManager;
import com.model.Player;
import com.model.Ressource;

public class PlayerTradePanel extends JPanel implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
/********************** Attributes **********************/
	private GameManager gm;
	
	private JButton exitButton;
	private JButton validateTradeButton;
	
	private Player currentPlayer;
	private Player traderPlayer;
	private int nbRCurPlayer;
	private int nbRTrPlayer;
	private Ressource rCurPlayer;
	private Ressource rTrPlayer;
	
/*********************** Methods ***********************/
	public PlayerTradePanel(GameManager gm){
		super();
		this.gm = gm;
		currentPlayer = null;
		traderPlayer = null;
		
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
		g.drawString("TRADING", getWidth()/6, 50);
		
		if(currentPlayer != gm.getCurrentPlayer()){
			currentPlayer = gm.getCurrentPlayer();
			traderPlayer = null;
			nbRCurPlayer=0;
			nbRTrPlayer=0;
			rCurPlayer=Ressource.NO_RESSOURCE;
			rTrPlayer=Ressource.NO_RESSOURCE;
		}
		
		ButtonGroup playerGroup = new ButtonGroup();
//		playerButton.setBounds(getWidth()/2 - 100, 100, 200, 200);
		int y = 30;
		for(Player pl : gm.getPlayers()){
			if(pl != gm.getCurrentPlayer()){
				JRadioButton newButton;
				if(traderPlayer == pl)
					newButton = new JRadioButton(pl.getName(), true);
				else
					newButton = new JRadioButton(pl.getName());
				newButton.addActionListener(this);
				newButton.setActionCommand(pl.getName());
				
				playerGroup.add(newButton);
				this.add(newButton);
				newButton.setBounds(getWidth()/2-80, y, 160, 20);
				y+=25;
			}
		}
		
		if(traderPlayer != null){
			g.setFont(new Font("arial", Font.BOLD, 15));
			
			// pour le current player
			g.setColor(gm.getCurrentPlayer().getColor());
			g.fillRect(50, getHeight()/3, 400, getHeight()/3-30);
			g.setColor(Color.BLACK);
			g.drawString(gm.getCurrentPlayer().getName() + " gives", 80, getHeight()/2);
			// nombre de ressources
			ButtonGroup nbRCurPlayerGroup = new ButtonGroup();
			y=getHeight()/3+5;
			for(int i=1 ; i<=5 ; i++){
				JRadioButton newButton;
				if(nbRCurPlayer == i)
					newButton = new JRadioButton(""+i, true);
				else
					newButton = new JRadioButton(""+i);
				newButton.addActionListener(this);
				newButton.setActionCommand("nbRCurPlayer:"+i);
				
				nbRCurPlayerGroup.add(newButton);
				this.add(newButton);
				newButton.setBounds(200, y, 50, (getHeight()-100)/16);
				y += (getHeight()-100)/16;
			}
			// type de ressources
			ButtonGroup rCurPlayerGroup = new ButtonGroup();
			y=getHeight()/3+5;
			for(Ressource re : Ressource.values()){
				if(re != Ressource.NO_RESSOURCE){
					JRadioButton newButton = null;
					if(re == rCurPlayer)
						newButton = new JRadioButton(""+re.toString(), true);
					else
						newButton = new JRadioButton(""+re.toString());
					newButton.addActionListener(this);
					newButton.setActionCommand("rCurPlayer:"+re.toString());
					
					rCurPlayerGroup.add(newButton);
					this.add(newButton);
					newButton.setBounds(270, y, 80, (getHeight()-100)/16);
					y += (getHeight()-100)/16;
				}
			}
			
			
			
			// pour le trader player
			g.setColor(traderPlayer.getColor());
			g.fillRect(50, 2*getHeight()/3, 400, getHeight()/3-30);
			g.setColor(Color.BLACK);
			g.drawString(traderPlayer.getName() + " gives", 80, 5*getHeight()/6);
			// nombre de ressources
			ButtonGroup nbRTrPlayerGroup = new ButtonGroup();
			y=2*getHeight()/3+5;
			for(int i=1 ; i<=5 ; i++){
				JRadioButton newButton;
				if(nbRTrPlayer == i)
					newButton = new JRadioButton(""+i, true);
				else
					newButton = new JRadioButton(""+i);
				newButton.addActionListener(this);
				newButton.setActionCommand("nbRTrPlayer:"+i);
				
				nbRTrPlayerGroup.add(newButton);
				this.add(newButton);
				newButton.setBounds(200, y, 50, (getHeight()-100)/16);
				y += (getHeight()-100)/16;
			}
			// type de ressources
			ButtonGroup rTrPlayerGroup = new ButtonGroup();
			y=2*getHeight()/3+5;
			for(Ressource re : Ressource.values()){
				if(re != Ressource.NO_RESSOURCE){
					JRadioButton newButton = null;
					if(re == rTrPlayer)
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
			validateTradeButton.setEnabled(nbRCurPlayer>0 && nbRTrPlayer>0
					&& rCurPlayer!=Ressource.NO_RESSOURCE && rTrPlayer!=Ressource.NO_RESSOURCE
					&& currentPlayer.getRessourceCards(rCurPlayer) >= nbRCurPlayer
					&& traderPlayer.getRessourceCards(rTrPlayer) >= nbRTrPlayer);
			
			
		}
		
		
		
		add(exitButton);
		exitButton.setBounds(getWidth()-120, 20, 100, 30);
		
	}

	
	
	
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().contains("nbRCurPlayer")){
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
			nbRTrPlayer = Integer.parseInt(String.valueOf(c));
		}
		else if(e.getActionCommand().contains("rTrPlayer")){
			String[] splitArray = null;
			splitArray = e.getActionCommand().split(":");
			for(Ressource r : Ressource.values()){
				if(splitArray[1].equals(r.toString())){
					rTrPlayer = r;
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
			for(int i=0 ; i<nbRTrPlayer ; i++){
				traderPlayer.spendRessourceCard(rTrPlayer);
			}
			JOptionPane.showMessageDialog(null, currentPlayer.getName()+" have given "
			+nbRCurPlayer+" "+rCurPlayer.toString()+" to "+traderPlayer.getName()+"\n"
			+"and "+traderPlayer.getName()+" have given "+nbRTrPlayer+" "
			+rTrPlayer.toString()+" to "+currentPlayer.getName());
		}
		else{
			for(Player pl : gm.getPlayers()){
				if(pl.getName().equals(e.getActionCommand())){
					traderPlayer = pl;
				}
			}
		}
		repaint();
	}
	
	
}
