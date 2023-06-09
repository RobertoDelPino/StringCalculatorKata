package test.kata;

import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class StringCalculatorShould {
    @Test
    public void be0IfNumbersAreEmpty(){
        int result = new StringCalculator().add("");
        assertThat(result).isEqualTo(0);
    }

    @Test
    public void beSameNumberIfLengthIs1(){
        int result = new StringCalculator().add("1");
        assertThat(result).isEqualTo(1);
    }

    @Test
    public void allowHandleUnknownAmountOfNumbers(){
        int result = new StringCalculator().add("5,5,4,3,1,2,5");
        assertThat(result).isEqualTo(25);
    }

    @Test
        public void allowHandleNewLinesBetweenNumbers(){
        int result = new StringCalculator().add("5\n5,4\n3,1,2,5");
        assertThat(result).isEqualTo(25);
    }

    @Test
    public void allowHandleDifferentDelimitersBetweenNumbers(){
        int result = new StringCalculator().add("//-\n5-5-4-3-1-2-5");
        assertThat(result).isEqualTo(25);
    }

    @Test(expected = IllegalArgumentException.class)
    public void allowOnlyPositiveNumbers(){
        new StringCalculator().add("//.\n5.5.4.-3.1.2.5");
    }

    @Test
    public void allowOnlyNumbersLessThan1000(){
        int result = new StringCalculator().add("2,1001");
        assertThat(result).isEqualTo(2);
    }

    @Test(expected = NumberFormatException.class)
    public void allowOnlyNumbers(){new StringCalculator().add("r");}

    @Test
    public void allowDelimitersOfAnyLengthWithAStrictFormat(){
        int result = new StringCalculator().add("//[***]\n1***2***3");
        assertThat(result).isEqualTo(6);
    }

    @Test
    public void allowMultipleDelimitersWithAStrictFormat(){
        int result = new StringCalculator().add("//[*][%]\n1*2%3");
        assertThat(result).isEqualTo(6);
    }

    @Test
    public void convertSplitStringToIntArray(){
        List<Integer> result = new StringCalculator().getNumbersWithDelimiter("3,4,5,5,6", ",");

        assertThat(result.get(0)).isEqualTo(3);
    }
}
