package appackage;
import java.util.*;

public class numbers {
   public static List <SystemShuffler> nums=new ArrayList<SystemShuffler>();
   public static String chrono="";
   public static int spreding[];
   public static void add_number(SystemShuffler s) {numbers.nums.add(s);}
   public static void clear() {numbers.clear();}
   public static void reset()
   {
	   numbers.nums.clear();
	   if(spreding.length!=0)
	   for(int i=2;i<17;i++)
	   {
		   numbers.spreding[i]=0;
	   }
   }
   public static void random_fill()
   {
	   numbers.reset();
	   long time=System.currentTimeMillis();
	   Random r=new Random(System.currentTimeMillis());
	   final int size=r.nextInt(500);
	   for(int i=0;i<size;i++)
	   {
		   numbers.nums.add(SystemShuffler.create());
	   }
	   long timefinilize=System.currentTimeMillis();
	   numbers.chrono=String.valueOf(timefinilize-time);
   }
   public static String print_table()
   {
	   String htmlview="";
	   for(SystemShuffler s:numbers.nums)
	   {
		   htmlview+="<tr><td>"+String.valueOf(s.getSystem())+"</td><td>"+String.valueOf(s.getNumber())+"</td></tr>";
	   }
	   return htmlview;
   }
   public static long summary()
   {
	   long sum=0;
	   final int size=numbers.nums.size();
	   for(int i=0;i<size;i++)
	   {
		   sum+=numbers.nums.get(i).TO_DECIMAL();
	   }
	   return sum;
   }
   public static String get_spred()
   {
	   numbers.spreding=new int[17];
	   numbers.spreding[0]=-10;
	   numbers.spreding[1]=-10;
	   for(SystemShuffler s:numbers.nums)
	   {
		   numbers.spreding[s.getSystem()]++;
	   }
	   String table="<table border=\"3\" style=\"width:40%; background-color:white; color:purple; font-size:21px; font-weight:color;\"><tr style=\"background-color:green; color:black;\"><th>SYSTEM</th><th>SPREDING</th><th>SPREDING PERCENTANCE</th></tr>";
	   double per=-1;
	   for(int i=2;i<spreding.length;i++)
	   {
		   per=((double)spreding[i]/numbers.nums.size())*100.0;
		   table+="<tr><td>"+String.valueOf(i)+"</td><td>"+String.valueOf(spreding[i])+"</td><td>"+String.format("%.3f%%",per)+"</td></tr>";
	   }
	   table+="</table>";
	   return table;
   }
   public static String print()
   {
	  String message="<div style=\"text-align:center; border:2px solid red; width:50%; font-size:16px; height:auto; text-align:center; overflow:hidden;\">";
	  message+="<h2>Information About Shuffling</h2><hr><ul>";
	  message+="<li>Numbers Created:"+String.valueOf(numbers.nums.size())+"</li>";
	  message+="<li>Summary:"+String.valueOf(numbers.summary())+"</li>";
	  message+="<li>Time exceded:"+numbers.chrono+" ms</li>";
	  message+="<h4>SPEDING</h4>"+numbers.get_spred()+"</div>";
	  return message;   
   }
}
