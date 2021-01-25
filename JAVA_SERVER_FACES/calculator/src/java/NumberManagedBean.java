/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.model.SelectItem;

/**
 *
 * @author nasto
 */
@Named(value = "numberManagedBean")
@RequestScoped
public class NumberManagedBean {

         private double n1;
    private double n2;
    private String op;
    private String result;
    private static HashMap <String,SelectItem> operands=new HashMap<String,SelectItem>();
    private static List <SelectItem> items=new ArrayList<SelectItem>();
    static
    {
        operands.put("1",new SelectItem("+","+"));
        operands.put("2",new SelectItem("-","-"));
        operands.put("3",new SelectItem("*","*"));
        operands.put("4",new SelectItem("/","/"));
        items.add(new SelectItem("+","+"));
        items.add(new SelectItem("-","-"));
        items.add(new SelectItem("*","*"));
        items.add(new SelectItem("/","/"));
    }
    public NumberManagedBean() {
    }
    public void setN1(double n)
    {
        this.n1=n;
    }
    public double getN1()
    {
        return this.n1;
    }
    public List <SelectItem> getItems()
    {
        return items;
    }
    public void setOp(String n)
    {
        this.op=n;
    }
    public String getOp()
    {
        return this.op;
    }
    public void setN2(double n)
    {
      this.n2=n;
    }
    public double getN2()
    {
        return this.n2;
    }
    public String clear()
    {
        this.n1=0.0;
        this.n2=0.0;
        return "page1";
    }
    public String  getResult()
    {
        return this.result;
    }
    public String calculate()
    {
        double res=0.0;
        if(this.op.equals("-"))
        {
            res=this.n1-this.n2;
        }
        else if(this.op.equals("+"))
        res=this.n1+this.n2;
        else if(this.op.equals("*"))
        res=this.n1*this.n2;
        else
        {
            if(this.n2==0)
            {
                this.result="NAN-Unacceptable Token";
                return "page2";
            }
            res=this.n1/this.n2;
        }
        this.result=String.format("%.3f", res);
        return "page2";
    }
 }
