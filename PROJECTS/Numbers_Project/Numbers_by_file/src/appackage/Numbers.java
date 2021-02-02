package appackage;
import java.util.*;

public class Numbers {
    private static List <List <Double>> nums;
    private static Numbers n=null;
    private Numbers()
    {
    	nums=new ArrayList<List<Double>>();
    }
    public static Numbers getInstance()
    {
    	if(Numbers.n==null)
    	{
    		Numbers.n=new Numbers();
    	}
    	return Numbers.n;
    }
    public List <List <Double>> getList()
    {
    	return Numbers.nums;
    }
    public boolean isEmpty()
    {
    	return nums.isEmpty();
    }
    public double getSum(int j)
    {
    	double s=0.0;
    	for(Double x:nums.get(j))
    	{
    		s+=x;
    	}
    	return s/nums.get(j).size();
    }
    public void clear()
    {
    	Numbers.nums.clear();
    }
    public void add(List <Double> nm)
    {
    	Numbers.nums.add(nm);
    }
}
