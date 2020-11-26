
public class currency {
	  private String id;
	  private String title;
	  private int am;
	  public currency(String i,String t,int a)
	  {
		  this.id=i;
		  this.title=t;
		  this.am=a;
	  }
	  public String get_Id() {return this.id;}
	  public String get_Title() {return this.title;}
	  public int get_Am() {return this.am;}
	}