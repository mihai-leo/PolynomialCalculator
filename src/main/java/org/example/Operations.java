package org.example;

import java.util.Map;

public class Operations {
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

    public Operations(Polynomial input1, Polynomial input2)
    {
        this.firstPolynomial=input1;
        this.secondPolynomial=input2;
    }
    public Operations(Polynomial input)
    {
        this.firstPolynomial=input;
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
        for (Map.Entry<Integer, Float> i : firstPolynomial.getElements().entrySet()) {
            for (Map.Entry<Integer, Float> j : secondPolynomial.getElements().entrySet()) {
                this.result.addElements(i.getKey() + j.getKey(), i.getValue() * j.getValue());
            }
        }

    }
    public void division()
    {
        this.result=new Polynomial();
        this.rest=new Polynomial(firstPolynomial.stringPolynomial());
        try {
            while (!rest.getElements().isEmpty() && rest.getBiggestExponent() >= secondPolynomial.getBiggestExponent()) {
                int e = rest.getBiggestExponent() - secondPolynomial.getBiggestExponent();
                float c = rest.getCoefficientOfBiggestExponent() / secondPolynomial.getCoefficientOfBiggestExponent();
                result.addElements(e, c);
                Operations op1 = new Operations(new Polynomial(e, c), secondPolynomial);
                op1.multiplication();
                Operations op2 = new Operations(rest, op1.getResult());
                op2.subtraction();
                rest = new Polynomial(op2.getResult().stringPolynomial());
            }
        }
        catch (Exception NoSuchElementException)
        {
            throw NoSuchElementException;
        }
    }
    public void derivation()
    {
        this.result=new Polynomial();
        for(Map.Entry<Integer,Float> i:firstPolynomial.getElements().entrySet())
        {
            if(i.getKey()!=0)
            {
                result.addElements(i.getKey()-1,i.getKey()*i.getValue());
            }
        }
    }
    public void integration()
    {
        this.result=new Polynomial();
        for(Map.Entry<Integer,Float> i:firstPolynomial.getElements().entrySet())
        {
            result.addElements(i.getKey()+1,i.getValue()/(i.getKey()+1));
        }
    }
}
