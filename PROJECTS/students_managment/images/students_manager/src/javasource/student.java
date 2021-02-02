package javasource;

public class student {
  private String name;
  private String lastname;
  private String id;
  private String e_mail;
  private int semester;
  public student()
  {
	  this.lastname="";
	  this.name="";
	  this.id="";
	  this.semester=1;
	  this.e_mail="";
  }
  public student(String n,String ln,String i,String e,int s)
  {
	  this.name=n;
	  this.lastname=ln;
	  this.id=i;
	  this.e_mail=e;
	  this.semester=s;
  }
  public String getName()
  {
	  return this.name;
  }
  public String getId()
  {
	  return this.id;
  }
  public String getLastname()
  {
	  return this.lastname;
  }
  public String getEmail()
  {
	  return this.e_mail;
  }
  public int getSemester()
  {
	  return this.semester;
  }
  public String to_str()
  {
	  return this.name+"--"+this.lastname+"--"+this.id+"--"+this.e_mail+"--"+String.valueOf(this.semester);
  }
}
