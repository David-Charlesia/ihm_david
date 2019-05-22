public class ValMeteoJour
{
	private String jour;
	private float temp;
	private float prec;

	public ValMeteoJour(){}

	public ValMeteoJour(String jour, float temp, float prec)
	{
		this.jour = jour;
		this.temp = temp;
		this.prec = prec;
	}


	public String getJour()
	{
		return jour;
	}

	public void setJour(String jour)
	{
		this.jour = jour;
	}

	public float getTemp()
	{
		return temp;
	}

	public void setTemp(float temp)
	{
		this.temp = temp;
	}

	public float getPrec()
	{
		return prec;
	}

	public void setPrec(float prec)
	{
		this.prec = prec;
	}


	public String toString()
	{
		return "[jour=" + jour + ", prec=" + prec + ", temp=" + temp+ "]";
	}
}
