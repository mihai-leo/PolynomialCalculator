package org.example;

import java.util.Map;

public class TwoInputs {
    private Polynomial firstPolynomial= new Polynomial();
    private Polynomial secondPolynomial= new Polynomial();
    private Polynomial result=new Polynomial();

    public Polynomial getResult() {
        return result;
    }

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

}
