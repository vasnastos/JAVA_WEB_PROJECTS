package appackage;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

public class SystemShuffler implements Comparable<SystemShuffler>{
    private int system;
    private String number;
    private static Random ran=new Random(System.currentTimeMillis());
    public int getSystem()
    {
    	return this.system;
    }
    public String getNumber()
    {
    	return this.number;
    }
    public static SystemShuffler create()
    {
    	String n="";
    	int prod;
    	int s=ran.nextInt(15)+2;
    	for(int i=0;i<8;i++)
    	{
    		prod=ran.nextInt(s);
    		if(s>10 && prod>=10)
    		{
    			switch(prod)
    			{
    				case 10:
    					n+='A';
    					break;
    			    case 11:
    			    	n+='B';
    			    	break;
    			    case 12:
    			    	n+='C';
    			    	break;
    			    case 13:
    			    	n+='D';
    			    	break;
    			    case 14:
    			    	n+='E';
    			    	break;
    			    case 15:
    			    	n+='F';
    			    	break;
     			}
    			continue;
    		}
           n+=String.valueOf(prod);		
    	}
    	return new SystemShuffler(s,n);
    }
    public SystemShuffler(int s,String n)
    {
    	this.system=s;
    	this.number=n;
    }
    public int TO_DECIMAL()
    {
    	Queue <Character> digits = new PriorityQueue<Character>();
    	for(int i=0,t=this.number.length();i<t;i++)
    	{
    		digits.add(this.number.charAt(i));
    	}
    	int rval=this.number.length()-1;
    	int decimal=0;
    	while(!digits.isEmpty())
    	{
    		if(digits.element()>='A' && digits.element()<='F')
    		{
    			int num=1;
    			switch(digits.poll())
    			{
    			case 'A':
    				num=10;
    				break;
    			case 'B':
    				num=11;
    				break;
    			case 'C':
    				num=12;
    				break;
    			case 'D':
    				num=13;
    				break;
    			case 'E':
    				num=14;
    				break;
    			case 'F':
    				num=15;
    				break;
    		    }
    			decimal+=num*Math.pow(this.system,rval);
    			rval--;
    			continue;
    		}
    		decimal+=(digits.poll()-'0')*Math.pow(this.system,rval);
    		rval--;
    	}
    	return decimal;
    }
	@Override
	public int compareTo(SystemShuffler arg0) {
		return Integer.compare(this.TO_DECIMAL(),arg0.TO_DECIMAL());
	}
	@Override
	public String toString()
	{
	    return this.number+"------"+String.valueOf(this.system)+"------"+String.valueOf(this.TO_DECIMAL());	
	}
}
