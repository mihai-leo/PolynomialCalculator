package org.example;

import java.util.Map;

public class OneInput {
    private Polynomial inputPolynomial= new Polynomial();

    private Polynomial result=new Polynomial();
    public void setResult(Polynomial result) {
        this.result = result;
    }
    public Polynomial getResult() {
        return result;
    }
    public OneInput(Polynomial inputPolynomial)
    {
        this.inputPolynomial=inputPolynomial;
    }
    public void derivation()
    {
        this.result=new Polynomial();
        for(Map.Entry<Integer,Float> i:inputPolynomial.getElements().entrySet())
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
        for(Map.Entry<Integer,Float> i:inputPolynomial.getElements().entrySet())
        {
                result.addElements(i.getKey()+1,i.getValue()/(i.getKey()+1));
        }
    }
}
