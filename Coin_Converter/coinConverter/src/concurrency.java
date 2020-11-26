

public class concurrency {
   private String coin;
   private String description;
   private int conc;
   public concurrency(String c,String d,int cn)
   {
	   this.coin=c;
	   this.description=d;
	   this.conc=cn;
   }
   public String getDescription() {return this.description;}
   public String getCoin() {return this.coin;}
   public int getConcurrency() {return this.conc;}
}
