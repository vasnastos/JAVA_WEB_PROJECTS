package preperation_1;

public class product implements Comparable<product>{
  private String name;
  private String id;
  private double price;
  public product(String n,String i,double p)
  {
	  this.name=n;
	  this.id=i;
	  this.price=p;
  }
  public final String getName()
  {
	  return this.name;
  }
  public final String getId()
  {
	  return this.id;
  }
  public final double getPrice()
  {
	  return this.price;
  }
@Override
public int compareTo(product arg0) {
	return this.id.compareTo(arg0.id);
}
@Override
public String toString()
{
	return this.name+"-"+this.id+"-"+String.valueOf(this.price);
}
}
