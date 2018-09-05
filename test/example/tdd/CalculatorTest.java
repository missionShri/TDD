package example.tdd;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

//TODO: https://technologyconversations.com/2014/09/30/test-driven-development-tdd/
//TODO: https://technologyconversations.com/2013/11/14/behavior-driven-development-bdd-value-through-collaboration-part-1-introduction/
public class CalculatorTest {

    //TODO : Test naming convention -> https://www.google.com/search?q=test+naming+convention&oq=test+naming+con&aqs=chrome.0.0j69i57j0l4.2540j1j7&sourceid=chrome&ie=UTF-8
    //TODO : Choice of testing framework: https://www.google.com/search?q=junit4+vs+junit5+vs+testng&oq=junit4+vs+junit5+vs+testn&aqs=chrome.1.69i57j33.9586j1j7&sourceid=chrome&ie=UTF-8
    //TODO : https://technologyconversations.com/2014/04/08/tests-as-documentation/

    //TODO: Data-providers case
    @Test(expected = RuntimeException.class)
    public final void whenNoInputIsUsedThenExceptionIsThrown()
    {
        StringCalculator.add(null);
    }

    @Test
    public final void whenEmptyIsUsedThenZeroIsReturned()
    {
        Assert.assertEquals(0,StringCalculator.add(" "));
    }

    //    @Test(expected = RuntimeException.class)
    //    public final void whenMoreThanTwoInputsAreUsedThenExceptionIsThrown()
    //    {
    //        StringCalculator.add("1,2,3");
    //    }

    @Test(expected = RuntimeException.class)
    public final void whenNonNumberInpuIsUsedThenExceptionIsThrown()
    {
        StringCalculator.add("1,a");
    }

    @Test
    public final void whenTwoInputsAreUsedThenTheirSumIsReturned()
    {
        Assert.assertEquals(1+2, StringCalculator.add("1,2"));
    }

    @Test
    public final void whenOneInputAreUsedThenItIsReturnedAsIs()
    {
        Assert.assertEquals(4, StringCalculator.add("4"));
    }

    @Test(expected = NumberFormatException.class)
    public final void whenMoreThanTwoInputsAreUsedThenExceptionIsThrown()
    {
        StringCalculator.add("3+6+15+18+46+33");
    }

    @Test
    public final void whenMoreThanTwoInputsWithWhiteSpacesAreUsedThenTheirSumIsReturned()
    {
        Assert.assertEquals(3+6+15+18+46+33, StringCalculator.add("3, 6,15 , 18 ,46,33"));
    }

    @Test
    public final void whenMoreThanTwoInputsAreUsedThenTheirSumIsReturned()
    {
        Assert.assertEquals(3+6+15+18+46+33, StringCalculator.add("3,6,15,18,46,33"));
    }

    @Test
    public final void whenNewLinesAndCommaAsDelimiterThenTheirSumIsReturned()
    {
        Assert.assertEquals(3+6+15+18+46+33, StringCalculator.add("3,6,15\n18,46\n33"));
    }

    @Test
    public final void whenDelimiterThenTheirSumIsReturned()
    {
        Assert.assertEquals(3+6+15, StringCalculator.add("//;\n3;6;15"));
    }

    @Test
    public final void whenCustomSameDelimitersThenTheirSumIsReturned()
    {
        Assert.assertEquals(3+6+15, StringCalculator.add("//;;;\n3;;;6;;;15"));
    }

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public final void whenNegativeInputsThenExceptionIsThrownIdentifyingEach()
    {
        //Given
        expectedEx.expect(RuntimeException.class);
        expectedEx.expectMessage("Negative values are not allowed.Your input included the following");

        //When
        StringCalculator.add("-3,-6,15,-18,46,33");

        /*try {
            StringCalculator.add("-3,-6,15,-18,46,33");
        } catch (RuntimeException e) {
            final String message = "Negative values are not allowed.Your input included the following";
            Assert.assertEquals(message,e.getMessage());
        }*/
    }

    @Test
    public final void whenInputsGreaterThanThousandThenSumIgnoringThemIsReturned()
    {
        Assert.assertEquals(3+6+1000+18+33, StringCalculator.add("3,6,1000, 1500,18,4600,33"));
    }

//    @Test
//    public final void whenMultipleSingleCharDelimitersThenTheirSumIsReturned()
//    {
//        Assert.assertEquals(3+6+15, StringCalculator.add("//[+][;]\n3;6+15"));
//    }
//
//    @Test
//    public final void whenMultipleVariableLengthDelimitersThenTheirSumIsReturned()
//    {
//        Assert.assertEquals(3+6+15, StringCalculator.add("//[--][;]\n3;6--15"));
//    }
}
