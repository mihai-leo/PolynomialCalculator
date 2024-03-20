import org.example.Operations;
import org.example.Polynomial;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestingClass
{
    Polynomial input1 = new Polynomial("x^2-3");
    Polynomial input2 = new Polynomial("-x^2+3");
    Operations op1= new Operations(input1);
    Operations op2= new Operations(input1,input2);

    @Test
    public void addition()
    {
        Polynomial result= new Polynomial("0");
        op2.addition();
        assertEquals(op2.getResult().stringPolynomial(),result.stringPolynomial());
    }
    @Test
    public void subtraction()
    {
        Polynomial result= new Polynomial("2x^2-6");
        op2.subtraction();
        assertEquals(op2.getResult().stringPolynomial(),result.stringPolynomial());
    }
    @Test
    public void multiplication()
    {
        Polynomial result= new Polynomial("-x^4+6x^2-9");
        op2.multiplication();
        assertEquals(op2.getResult().stringPolynomial(),result.stringPolynomial());
    }
    @Test
    public void division()
    {
        Polynomial result= new Polynomial("-1");
        Polynomial rest= new Polynomial("0");
        op2.division();
        assertEquals(op2.getResult().stringPolynomial(),result.stringPolynomial());
        assertEquals(op2.getRest().stringPolynomial(),rest.stringPolynomial());
    }
    @Test
    public void derivation()
    {
        Polynomial result= new Polynomial("2x");
        op1.derivation();
        assertEquals(op1.getResult().stringPolynomial(),result.stringPolynomial());
    }
    @Test
    public void integration()
    {
        Polynomial result= new Polynomial("0.33333333333333x^3-3x");
        op1.integration();
        assertEquals(op1.getResult().stringPolynomial(),result.stringPolynomial());
    }
}
