package f5;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class AssignmentFivePointThreeTest {

    /*
     * De första två testen testar absolutvärdesmetoden med ett positivt och ett
     * negativt värde. Bägge dessa test bör gå igenom om du inte ändrat något i
     * metoden absoluteValue eftersom den metoden är korrekt implementerad i den
     * givna koden.
     *
     * Testen är uppsatta på olika sätt för att visa olika varianter, men de
     * fungerar på samma sätt. assertEquals första argument är det förväntade
     * värdet, det andra vad metoden som testas returnerar.
     */

    @Test
    void theAbsoluteValueOfAPositiveNumberIsTheNumberItself() {
        int expected = 3;
        int actual = AssignmentFivePointThree.absoluteValue(3);

        assertEquals(expected, actual);
    }

    @Test
    void theAbsoluteValueOfANegativeNumberIsTheNumberNegated() {
        assertEquals(7, AssignmentFivePointThree.absoluteValue(-7));
    }

    /*
     * De följande testfallen testar metoden isLeapYear för de fyra fallen för ett
     * skottår: Ett skottår måste vara jämt delbart med fyra, men inte med hundra,
     * om det inte samtidigt är jämt delbart med fyra hundra
     *
     * Implementationen av isLeapYear som finns i ilearn implementerar bara det
     * första av dessa fall, så minst ett av testen nedan kommer att misslyckas
     * innan metoden rättats. Det är inte ett krav att skriva klart metoden. Uppgift
     * 5.3:s syfte är att testa på att använda JUnit-testfall. Men, vill du få ut så
     * mycket som möjligt av uppgiften så försök skriva klart metoden, och kör
     * testfallen efter varje ändring du gjort så att du kan se hur JUnit-testen kan
     * användas.
     */

    @Test
    void aYearDivisibleByFourIsALeapYear() {
        assertTrue(AssignmentFivePointThree.isLeapYear(1956));
    }

    @Test
    void aYearNotDivisibleByFourIsNotALeapYear() {
        assertFalse(AssignmentFivePointThree.isLeapYear(1977));
    }

    @Test
    void aYearDivisibleByAHundredIsNotALeapYear() {
        assertFalse(AssignmentFivePointThree.isLeapYear(2100));
    }

    @Test
    void aYearDivisibleByFourHundredIsALeapYear() {
        assertTrue(AssignmentFivePointThree.isLeapYear(2000));
    }

    /*
     * De sista två testen visar ett sätt att köra samma test med olika värden.
     * Flera av dessa kommer att misslyckas innan isLeapYear-metoden är korrigerad.
     */

    @ParameterizedTest
    @ValueSource(ints = { 1996, 2000, 2004 })
    void validLeapYears(int year) {
        assertTrue(AssignmentFivePointThree.isLeapYear(year));
    }

    @ParameterizedTest
    @ValueSource(ints = { 1899, 1900, 1902 })
    void notValidLeapYears(int year) {
        assertFalse(AssignmentFivePointThree.isLeapYear(year));
    }

}