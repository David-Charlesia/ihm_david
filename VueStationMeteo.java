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
    for (int i = 0; i<semaine.length; i++)
    {
      JButton bt = new JButton(semaine[i]);
      jp.add(bt);
    }

    return jp;
  }


  public JPanel getPanelNord()
  {
    JPanel jp=new JPanel();
    String[] label={"Place","Semaine","Température","Précipitation"};
    for(int i=0;i<label.length;i++)
    {
      jp.add(new JLabel(label[i]));
      jp.add(new JTextField(5));
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
      if(e.getActionCommand()=="Charger")
      {
        saisie2();
        valeur=new EnsValMeteo();
        valeur.charger(fichier);
        String ch=valeur.toString();
        System.out.println(ch);
        zone.setText(ch);
      }
      if(e.getActionCommand()=="Sauver")
      {
        valeur.sauver("test_bouton_sauver.txt");
      }
    }
  }
}
