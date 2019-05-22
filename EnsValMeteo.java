import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class EnsValMeteo extends java.util.ArrayList<ValMeteoJour>
{
  private String ville;
  private String numSemaine;

  EnsValMeteo(){}

  EnsValMeteo(String ville, String numSemaine)
  {
    this.ville=ville;
    this.numSemaine=numSemaine;
  }

  public void setVille(String ville)
  {
    this.ville=ville;
  }

  public void setNumSemaine(String numSemaine)
  {
    this.numSemaine=numSemaine;
  }

  public void charger(String nomF)
  {
    String jour;
  	float temp;
  	float prec;

    try
    {
      BufferedReader fichier = new BufferedReader(new FileReader(nomF));
      this.ville = fichier.readLine();
      this.numSemaine=fichier.readLine();
      String line=fichier.readLine();

      while (line!=null)
      {
        StringTokenizer ligne=new StringTokenizer(line,"\t");
        jour=ligne.nextToken();
        temp=Float.parseFloat(ligne.nextToken());
        prec=Float.parseFloat(ligne.nextToken());

        this.add(new ValMeteoJour(jour,temp,prec));

        line=fichier.readLine();
      }

      fichier.close();
    }catch(IOException e)
    {
      System.out.println(e);
    }
  }



  public void sauver(String nomF)
  {
    try
    {
      Iterator<ValMeteoJour> i=this.iterator();
      ValMeteoJour v;
      BufferedWriter f = new BufferedWriter(new FileWriter(nomF,true));

      f.write(this.ville);
      f.newLine();
      f.write(this.numSemaine);
      f.newLine();
      while(i.hasNext())
      {
        v=i.next();
        f.write(v.getJour()+"\t"+v.getTemp()+"\t"+v.getPrec());
        f.newLine();
      }
      f.close();
    }catch(IOException e)

    {
      System.out.println(e);
    }
  }




  public String toString()
  {
    String affichage="";

    affichage+="Ville : "+this.ville+"\n";
    affichage+="Semaine n°"+this.numSemaine+"\n";

    for(int i=0;i<this.size();i++)
    {
      affichage+=this.get(i).toString()+"\n";
    }

    return affichage;
  }

  public static void main(String[] args)
  {
    EnsValMeteo e = new EnsValMeteo();
    e.charger("testEnsValMeteo.txt");
    System.out.println(e.toString());
    e.remove(3);
    System.out.println(e.toString());

    e.add(new ValMeteoJour("Lundi",30.2F,80.3f));
    e.sauver("test_sauver_V2.txt");
  }

}
