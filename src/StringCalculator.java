package example.tdd.src;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class StringCalculator {

    public static final String DELIMITER_PREFIX = "//";
    private static final String COMMA_DELIMITER = ",";
    private static final String NEWLINE_DELIMITER = "\n";

    //TODO: https://stackoverflow.com/questions/2236599/final-keyword-in-method-parameters#
    //TODO : Log-&-Rethrow as an anti-pattern: https://community.oracle.com/docs/DOC-983543
    public static  long add(final String userInput) throws NumberFormatException {
        long sum = 0;
        String[] inputs;
        if (userInput == null) {
            throw new RuntimeException("Please provide a valid input");
        }

        if (userInput.startsWith(DELIMITER_PREFIX)) {
            final String[] delimiterAndInputString = userInput.split("\n", 2);
            final String delimiter = delimiterAndInputString[0]
                .substring(delimiterAndInputString[0].indexOf(DELIMITER_PREFIX) + (DELIMITER_PREFIX.length()));
            inputs = delimiterAndInputString[1].split(delimiter);
        } else
        {
            //TODO : Cool trick for regex-s
            inputs = userInput.split(COMMA_DELIMITER+"|"+NEWLINE_DELIMITER);
        }


        //        if(inputs.length>2)
        //        {
        //            throw new RuntimeException("Please provide at the most 2 inputs separated by a comma");
        //        }
        //        else
        //        {
        //WOW ! This was great !! A good number of validation errros were discovered by testing
        List<Integer> negativeInputValues = new ArrayList<>();
        for (String input: inputs) {
            input = input.trim();
            if(StringUtils.isNoneBlank(input))
            {
                int inputValue = Integer.parseInt(input);
                if(inputValue<0)
                {
                    negativeInputValues.add(inputValue);
                }
                if(inputValue<0 || inputValue>1000)
                {
                    continue;
                }
                sum += inputValue;
            }
        }

        if(negativeInputValues.size()>0)
        {
            throw new RuntimeException("Negative values are not allowed.Your input included the following "+negativeInputValues);
        }

        return sum;
    }
}
