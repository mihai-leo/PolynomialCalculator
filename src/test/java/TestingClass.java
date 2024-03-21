import org.example.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestingClass
{
    Polynomial input1 = new Polynomial("x^2-3+x^4");
    Polynomial input2 = new Polynomial("-x^2+3-6x");
    Operations op1= new Operations(input1);
    Operations op2= new Operations(input1,input2);

    @Test
    public void addition()
    {
        Polynomial result= new Polynomial("x^4-6x");
        op2.addition();
        assertEquals(op2.getResult().stringPolynomial(),result.stringPolynomial());
    }
    @Test
    public void subtraction()
    {
        Polynomial result= new Polynomial("x^4+2.0x^2+6x-6");
        op2.subtraction();
        assertEquals(op2.getResult().stringPolynomial(),result.stringPolynomial());
    }
    @Test
    public void multiplication()
    {
        Polynomial result= new Polynomial("-x^6-6x^5+2x^4-6x^3+6x^2+18x-9");
        op2.multiplication();
        assertEquals(op2.getResult().stringPolynomial(),result.stringPolynomial());
    }
    @Test
    public void division()
    {
        Polynomial result= new Polynomial("-x^2+6x-40");
        Polynomial rest= new Polynomial("-258x+117");
        op2.division();
        assertEquals(op2.getResult().stringPolynomial(),result.stringPolynomial());
        assertEquals(op2.getRest().stringPolynomial(),rest.stringPolynomial());
    }
    @Test
    public void derivation()
    {
        Polynomial result= new Polynomial("4x^3+2.0x");
        op1.derivation();
        assertEquals(op1.getResult().stringPolynomial(),result.stringPolynomial());
    }
    @Test
    public void integration()
    {
        Polynomial result= new Polynomial("0.2x^5+0.33333333333333x^3-3x");
        op1.integration();
        assertEquals(op1.getResult().stringPolynomial(),result.stringPolynomial());
    }
}
