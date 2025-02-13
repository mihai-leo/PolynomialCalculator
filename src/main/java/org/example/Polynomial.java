package org.example;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Polynomial {
    private TreeMap<Integer,Float> elements ;


    public Map<Integer, Float> getElements() {
        return elements;
    }
    public Polynomial()
    {
        this.elements = new TreeMap<>();
    }
    public Polynomial(int e,float c)
    {
        this.elements= new TreeMap<>();
        addElements(e,c);
    }

    public Polynomial(String string)
    {
        try {
        this.elements = new TreeMap<>();
        if (string.charAt(0)!='-')
        {
            string="+"+string;
        };
        string=string.replaceAll(" ","");
        String pattern="([-+]{1}[0-9]*\\.?[0-9]*)(x?)(\\^([0-9]+))?";
        Pattern pattern1=Pattern.compile(pattern);
        Matcher matcher=pattern1.matcher(string);
        int expo;
        float coef;
        while (matcher.find())
        {
            if(!matcher.group(2).equals("x"))
            {
                expo=0;
            }
            else if (matcher.group(4)==null)
            {
                expo=1;
            }
            else
            {
                expo=Integer.parseInt(matcher.group(4));
            }
            if(matcher.group(1).equals("-"))
            {
                coef=-1;
            }
            else if (matcher.group(1).equals("+"))
            {
                coef=1;
            }
            else
            {
                coef=Float.parseFloat(matcher.group(1));
            }
            addElements(expo,coef);

        }
        } catch (Exception e)
        {
            System.out.println("error1");
        }


    }

    public int getBiggestExponent()
    {
       return elements.keySet().stream().max(Comparator.comparing(Integer::intValue)).get();
    }
    public float getCoefficientOfBiggestExponent()
    {
        return elements.get(elements.keySet().stream().max(Comparator.comparing(Integer::intValue)).get());
    }
    public int addElements(int expo, float coef){
        if(!elements.containsKey(expo)&&coef==0)
        {
            return 4;
        }
        if(elements.containsKey(expo))
        {

            coef=coef+elements.get(expo);
            if(coef==0)
            {
                elements.remove(expo);
                return 3;
            }
            else
            {
                elements.put(expo,coef);
            }
            return 1;
        } else {
            elements.put(expo,coef);
            return 0;
        }
    }

    public String stringPolynomial()
    {
        StringBuilder strP= new StringBuilder();
        boolean isFirst = true;
        if(elements.isEmpty()||(elements.size()==1&&elements.containsKey(0)&&elements.get(0)==0))
            return "0";
        for(Map.Entry<Integer,Float> i:elements.descendingMap().entrySet())
        {
            float coef=i.getValue();
            int exp=i.getKey();
            if (coef!=0) {
                if (coef < 0) {
                    strP.append("-");
                } else if (!isFirst) {
                    strP.append("+");
                }
                isFirst = false;
                if ((coef != 1.0 && coef != -1.0 )||  exp == 0)
                    strP.append(Math.abs(coef));
                if (exp != 0)
                    strP.append("x");
                if (exp > 1) {
                    strP.append("^");
                    strP.append(exp);
                }
            }
        }
        if (strP.toString().isEmpty())
            return "0";
        else
            return strP.toString();
    }
}
