package org.example;

import java.util.Map;

public class TwoInputs {
    private Polynomial firstPolynomial= new Polynomial();
    private Polynomial secondPolynomial= new Polynomial();
    private Polynomial result=new Polynomial();
    private Polynomial rest=new Polynomial();

    public Polynomial getResult() {
        return result;
    }
    public Polynomial getRest(){return rest;}

    public void setResult(Polynomial result) {
        this.result = result;
    }

    public TwoInputs(Polynomial input1, Polynomial input2)
    {
        this.firstPolynomial=input1;
        this.secondPolynomial=input2;
    }
    public void addition()
    {
        this.result=new Polynomial();
        for(Map.Entry<Integer,Float> i:firstPolynomial.getElements().entrySet())
        {
            this.result.addElements(i.getKey(),i.getValue());
        }
        for(Map.Entry<Integer,Float> i:secondPolynomial.getElements().entrySet())
        {
            float coef=i.getValue();
            int expo=i.getKey();
            if(!result.getElements().containsKey(expo))
            {
                result.getElements().put(expo,coef);
            }
            else
            {
                result.getElements().put(expo,coef+result.getElements().get(expo));
            }

        }
    }
    public void subtraction()
    {
        this.result=new Polynomial();
        for(Map.Entry<Integer,Float> i:firstPolynomial.getElements().entrySet())
        {
            this.result.addElements(i.getKey(),i.getValue());
        }
        for(Map.Entry<Integer,Float> i:secondPolynomial.getElements().entrySet())
        {
            float coef=i.getValue();
            int expo=i.getKey();
            if(!result.getElements().containsKey(expo))
            {
                result.getElements().put(expo,0-coef);
            }
            else
            {
                result.getElements().put(expo,result.getElements().get(expo)-coef);
            }

        }
    }
    public void multiplication()
    {
        this.result=new Polynomial();
        for(Map.Entry<Integer,Float> i:firstPolynomial.getElements().entrySet())
        {
            for(Map.Entry<Integer,Float> j:secondPolynomial.getElements().entrySet())
            {
                this.result.addElements(i.getKey()+j.getKey(),i.getValue()*j.getValue());
            }

        }

    }
    public void division()
    {
        this.result=new Polynomial();
        this.rest=new Polynomial(firstPolynomial.stringPolynomial());
        while (!rest.getElements().isEmpty()&&rest.getBiggestExponent()>= secondPolynomial.getBiggestExponent())
        {
            int e=rest.getBiggestExponent()-secondPolynomial.getBiggestExponent();
            float c=rest.getCoefficientOfBiggestExponent()/secondPolynomial.getCoefficientOfBiggestExponent();
            result.addElements(e,c);
            System.out.println(e+" "+c+" "+rest.getBiggestExponent()+" "+secondPolynomial.getBiggestExponent());
            TwoInputs op1 = new TwoInputs(new Polynomial(e,c),secondPolynomial);
            op1.multiplication();
            System.out.println(op1.getResult().stringPolynomial());
            System.out.println(rest.stringPolynomial());
            TwoInputs op2= new TwoInputs(rest,op1.getResult());
            op2.subtraction();
            rest=new Polynomial(op2.getResult().stringPolynomial());
            System.out.println(rest.stringPolynomial());
        }
    }
//4x^3-7x^2-11x+5
}
