import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.io.*;
import java.awt.event.*;

public class VueStationMeteo extends JFrame
{
  private JButton charger;
  private JButton sauver;
  private JButton[] jours;
  private String fichier;
  private JTextArea zone;
  private EnsValMeteo valeur;
  private JTextField JPlace;
  private JTextField JSemaine;
  private JTextField JTemperature;
  private JTextField JPrecipitation;
  private Object[] chiffres=new Object[52];
  JComboBox<Object> compteur;

  public JPanel getPanelSud()
  {
    JPanel jp=new JPanel();
    charger=new JButton("Charger");
    sauver=new JButton("Sauver");

    BoutonListener blis=new BoutonListener();
    charger.addActionListener(blis);
    sauver.addActionListener(blis);

    jp.add(charger);
    jp.add(sauver);
    return jp;
  }


  public JPanel getPanelCentre()
  {
    JPanel jp=new JPanel();
    jp.setLayout(new GridLayout(1,1));


    this.zone=new JTextArea();
    this.zone.setEditable(true);
    this.zone.setLocation(0,0);
    this.zone.setLineWrap(true);

    this.valeur = new EnsValMeteo();
    this.valeur.charger(this.fichier);
    String affichage=this.valeur.toString();


    this.zone.append(affichage);
    //JScrollPane scroll=new JScrollPane(zone);
    //scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    //scroll.setPreferredSize(new Dimension(130,130));
    jp.add(this.zone);
    //jp.add(scroll);

    return jp;
  }


  public JPanel getPanelWest(JButton[] jours)
  {
    JPanel jp = new JPanel();
    jp.setLayout(new GridLayout(7,1));
    String[] semaine =  {"Lundi","Mardi","Mercredi","Jeudi","Vendredi","Samedi","Dimanche"};
    BoutonListener blis=new BoutonListener();

    for (int i = 0; i<semaine.length; i++)
    {
      JButton bt = new JButton(semaine[i]);
      bt.addActionListener(blis);
      jp.add(bt);
    }

    return jp;
  }


  public JPanel getPanelNord()
  {
    System.out.println("Nord");
    JPanel jp=new JPanel();
    String[] label={"Place","Semaine","Température","Précipitation"};

    String tx="";
    for(int i=0;i<label.length;i++)
    {
      tx=label[i];
      switch(tx)
      {
        case "Place":
          jp.add(new JLabel(label[i]));
          JPlace=new JTextField(5);
          jp.add(JPlace);
          break;

        case "Semaine":
          for(int j=1;j<53;j++)
          {
            chiffres[j-1]=new String(""+j);
          }

          compteur=new JComboBox<Object>(chiffres);
          jp.add(compteur);

          break;

        case "Température":
          jp.add(new JLabel(label[i]));
          JTemperature=new JTextField(5);
          jp.add(JTemperature);
          break;

        case "Précipitation":
          jp.add(new JLabel(label[i]));
          JPrecipitation=new JTextField(5);
          jp.add(JPrecipitation);
          break;
      }
    }
    return jp;
  }

  public void saisie2()
  {

    JFrame frame=new JFrame("");
    JFileChooser dialogue=new JFileChooser(new File("."));
    int returnval=dialogue.showOpenDialog(this);
    if(returnval==JFileChooser.APPROVE_OPTION)
    {
      File file=dialogue.getSelectedFile();
      this.fichier=file.getPath();
    }

    //System.exit(0);

  }

  public void initialise()
  {
    saisie2();
	  this.setLayout(new BorderLayout());
	  this.add(this.getPanelNord(),BorderLayout.NORTH);
	  this.add(this.getPanelCentre(), BorderLayout.CENTER);
	  this.add(this.getPanelWest(jours), BorderLayout.WEST);
	  this.add(this.getPanelSud(),BorderLayout.SOUTH);
  }

  public VueStationMeteo(String titre,int x,int y,int w,int h)
  {
    super(titre);
    this.initialise();
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.setBounds(x,y,w,h);
    this.setVisible(true);
  }

  public static void main(String[] args)
  {
    VueStationMeteo Fenetre = new VueStationMeteo("Station Meteo",200,400,600,400);


  }


  class BoutonListener implements ActionListener
  {
    public void actionPerformed(ActionEvent e)
    {
      String commande=e.getActionCommand();
      ValMeteoJour vmj;
      if(commande=="Charger")
      {
        saisie2();
        valeur=new EnsValMeteo();
        valeur.charger(fichier);
        String ch=valeur.toString();
        zone.setText(ch);
      }
      if(commande=="Sauver")
      {
        valeur.sauver("test_bouton_sauver.txt");
      }

      switch(commande)
      {
        case "Lundi":
          valeur=new EnsValMeteo(JPlace.getText(),compteur.getSelectedItem().toString());

          vmj=new ValMeteoJour("Lundi",Float.parseFloat(JTemperature.getText()),Float.parseFloat(JPrecipitation.getText()));
          valeur.add(vmj);
          zone.setText(valeur.toString());
          break;

        case "Mardi":
          valeur=new EnsValMeteo(JPlace.getText(),compteur.getSelectedItem().toString());

          vmj=new ValMeteoJour("Mardi",Float.parseFloat(JTemperature.getText()),Float.parseFloat(JPrecipitation.getText()));
          valeur.add(vmj);
          zone.setText(valeur.toString());
          break;

        case "Mercredi":
          valeur=new EnsValMeteo(JPlace.getText(),compteur.getSelectedItem().toString());

          vmj=new ValMeteoJour("Mercredi",Float.parseFloat(JTemperature.getText()),Float.parseFloat(JPrecipitation.getText()));
          valeur.add(vmj);
          zone.setText(valeur.toString());
          break;

        case "Jeudi":
          valeur=new EnsValMeteo(JPlace.getText(),compteur.getSelectedItem().toString());

          vmj=new ValMeteoJour("Jeudi",Float.parseFloat(JTemperature.getText()),Float.parseFloat(JPrecipitation.getText()));
          valeur.add(vmj);
          zone.setText(valeur.toString());
          break;

        case "Vendredi":
          valeur=new EnsValMeteo(JPlace.getText(),compteur.getSelectedItem().toString());

          vmj=new ValMeteoJour("Vendredi",Float.parseFloat(JTemperature.getText()),Float.parseFloat(JPrecipitation.getText()));
          valeur.add(vmj);
          zone.setText(valeur.toString());
          break;

        case "Samedi":
          valeur=new EnsValMeteo(JPlace.getText(),compteur.getSelectedItem().toString());

          vmj=new ValMeteoJour("Samedi",Float.parseFloat(JTemperature.getText()),Float.parseFloat(JPrecipitation.getText()));
          valeur.add(vmj);
          zone.setText(valeur.toString());
          break;

        case "Dimanche":
          valeur=new EnsValMeteo(JPlace.getText(),compteur.getSelectedItem().toString());

          vmj=new ValMeteoJour("Dimanche",Float.parseFloat(JTemperature.getText()),Float.parseFloat(JPrecipitation.getText()));
          valeur.add(vmj);
          zone.setText(valeur.toString());
          break;
      }


    }
  }
}
