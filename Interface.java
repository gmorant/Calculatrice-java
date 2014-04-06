
import java.awt.*;
import java.awt.event.*; 
import javax.swing.*;

public class Interface extends JFrame {
	private JPanel conteneur = new JPanel();
	String tableaucalcul[]  = {"1","2","3","4","5","6","7","8","9","0",".","=","C","+","-","*","/"};
	JButton tableaudebouton[] = new JButton[17];

	private JLabel ecran = new JLabel();
	private double premierchiffre ,deuxiemechiffre;

	private boolean clicOperateur = false, update = false;
	private String operateur = " ";

	// méthode permettant l'initialisation des différents élements de l'interface //

	private void initialiserInterface(){
		ecran = new JLabel("0");
		ecran.setForeground(Color.GRAY);
		ecran.setHorizontalAlignment(JLabel.RIGHT);
		ecran.setPreferredSize(new Dimension(220, 20));
		JPanel operateur = new JPanel(); 
		operateur.setPreferredSize(new Dimension(175,180));
		JPanel cadreChiffres = new JPanel();
		cadreChiffres.setPreferredSize(new Dimension(165, 185));
		JPanel cadreEcran = new JPanel();
		cadreEcran.setPreferredSize(new Dimension(330,30));
		cadreEcran.setBorder(BorderFactory.createLineBorder(Color.BLACK));//methode qui permet de mettre un cadre noir autour

		//boucle permettant d'afficher les differents boutons 

		for(int i = 0;i<=16;i++)
		{
			tableaudebouton[i] = new JButton(tableaucalcul[i]);
			tableaudebouton[i].setPreferredSize(new Dimension(50, 41));

			if(i == 11)
			{
				tableaudebouton[i].addActionListener(new Egalite());
				cadreChiffres.add(tableaudebouton[i]);
			}
			else if (i== 12)
			{
				tableaudebouton[i].setForeground(Color.darkGray);
				tableaudebouton[i].addActionListener(new Annulation());
				tableaudebouton[i].setPreferredSize(new Dimension(50, 61));
				operateur.add(tableaudebouton[i]);

			}
			else if( i == 13)
			{
				tableaudebouton[i].setForeground(Color.RED);
				tableaudebouton[i].addActionListener(new Addition());
				tableaudebouton[i].setPreferredSize(new Dimension(50, 61));
				operateur.add(tableaudebouton[i]);
			}
			else if(i == 14)
			{
				tableaudebouton[i].setForeground(Color.RED);
				tableaudebouton[i].addActionListener(new Soustraction());
				tableaudebouton[i].setPreferredSize(new Dimension(50, 61));
				operateur.add(tableaudebouton[i]);
			}	
			else if (i == 15 ){	
				tableaudebouton[i].setForeground(Color.RED);
				tableaudebouton[i].addActionListener(new Multiplication());
				tableaudebouton[i].setPreferredSize(new Dimension(50, 61));
				operateur.add(tableaudebouton[i]);
			}
			else if (i == 16) {
				tableaudebouton[i].setForeground(Color.RED);
				tableaudebouton[i].addActionListener(new Division());
				tableaudebouton[i].setPreferredSize(new Dimension(50,61));
				operateur.add(tableaudebouton[i]);
			}
			else
			{	
				cadreChiffres.add(tableaudebouton[i]);
				tableaudebouton[i].addActionListener(new AfficherChiffre());
			}
		}

		cadreEcran.add(ecran);
		conteneur.add(cadreEcran);
		conteneur.add(cadreChiffres);
		conteneur.add(operateur);
	}

	public Interface(){
		this.setSize(360,260);
		this.setResizable(false);
		this.setTitle("Calculatrice");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setContentPane(conteneur);
		this.setVisible(true);
		this.setLocationRelativeTo(null);//permet de positionner la fenètre au milieu de l'ecran
		initialiserInterface();
	}

//procedures permettant les calculs 
	private void calcul(){
		deuxiemechiffre = Double.valueOf(ecran.getText()).doubleValue();
			
		if(operateur == "+"){
			
			premierchiffre = premierchiffre + deuxiemechiffre ;
			ecran.setText(String.valueOf(premierchiffre));
		}
		if(operateur == "-" ){
			premierchiffre = premierchiffre - deuxiemechiffre ;
			ecran.setText(String.valueOf(premierchiffre));
		}          
		if(operateur == "*" ){
			premierchiffre = premierchiffre * deuxiemechiffre ;
					
			ecran.setText(String.valueOf(premierchiffre));
		}     
		if(operateur == "/" ){

			//permet de gerer la division par 0
		
				premierchiffre = premierchiffre /deuxiemechiffre ;
						
				ecran.setText(String.valueOf(premierchiffre));
			
				
				if (deuxiemechiffre == 0 || premierchiffre == 0 )
				{
					ecran.setText("Division par Zero Impossible");
				}

		}
		
		
	

	}



	//classe permettant les actions sur les boutons  

	public class AfficherChiffre implements ActionListener {
		public void actionPerformed(ActionEvent e){

			String str = ((JButton)e.getSource()).getText();
			if(update){
				update = false;
			}
			else{
				if(!ecran.getText().equals("Ecrivez un Chiffre"))
					str = ecran.getText() + str;
			}
			ecran.setText(str);
		}
	}


	public class Egalite implements ActionListener {
		public void actionPerformed(ActionEvent arg0){
			calcul();
			update = true;
			clicOperateur = false;
		}
	}


	public class Addition implements ActionListener {
		public void actionPerformed(ActionEvent arg0){
			if(clicOperateur){
				calcul();
				ecran.setText(String.valueOf(premierchiffre));
			}
			else{
				premierchiffre = Double.valueOf(ecran.getText()).doubleValue();
				clicOperateur = true;
			}
			operateur = "+";
			update = true;
		}
	}


	public class Soustraction implements ActionListener {
		public void actionPerformed(ActionEvent arg0){
			if(clicOperateur){
				calcul();
				ecran.setText(String.valueOf(premierchiffre));
			}
			else{
				premierchiffre = Double.valueOf(ecran.getText()).doubleValue();
				clicOperateur = true;
			}
			operateur = "-";
			update = true;
		}
	}


	public class Multiplication implements ActionListener {
		public void actionPerformed(ActionEvent arg0){
			if(clicOperateur){
				calcul();
				ecran.setText(String.valueOf(premierchiffre));
			}
			else{
				premierchiffre = Double.valueOf(ecran.getText()).doubleValue();
				clicOperateur = true;
			}
			operateur = "*";
			update = true;
		}
	}


	public class Division implements ActionListener {
		public void actionPerformed(ActionEvent arg0){
			if(clicOperateur){
				calcul();
				ecran.setText(String.valueOf(premierchiffre));
			}
			else{
				premierchiffre = Double.valueOf(ecran.getText()).doubleValue();
				clicOperateur = true;
			}
			operateur = "/";
			update = true;
		}
	}

	public class Annulation implements ActionListener {
		public void actionPerformed(ActionEvent arg0){
			clicOperateur = false;
			update = true;
			premierchiffre = 0;
			operateur = "";
			ecran.setText("0");
		}
	}  		
}


