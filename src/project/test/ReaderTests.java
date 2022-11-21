package project.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.*;
import java.time.Duration;
import java.util.Locale;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;


// TODO: skriv om testfallen så att det räcker med att en av inläsningsmetoderna finns för att kunna börja testa
public class AssignmentSixPointThreeInputClassTest extends IOBaseTest {

    private static final Duration TIMEOUT = Duration.ofMillis(100);
    private static final String TIMEOUT_MSG_TEMPLATE = "Det tog för lång tid att %s. "
            + "Detta kan bero på flera saker, men de vanligaste är att man läser från fel ström, "
            + "eller på att en extra inläsning görs någonstans. "
            + "Oavsett vilket så får detta effekten att anropet verkar \"hänga\" medan inläsningen väntar på indata som aldrig kommer";

    private static Object TestData;
    public static final ClassUnderTest SCANNER_WRAPPER_CLASS = new ClassUnderTest(TestData.SCANNER_ADAPTER_CLASS_NAME);
    public static final MethodUnderTest READ_TEXT_METHOD = SCANNER_WRAPPER_CLASS
            .getMethodOrNullObject(TestData.READ_TEXT_METHOD_NAME, String.class);
    public static final MethodUnderTest READ_INTEGER_METHOD = SCANNER_WRAPPER_CLASS
            .getMethodOrNullObject(TestData.READ_INTEGER_METHOD_NAME, String.class);
    public static final MethodUnderTest READ_DECIMAL_METHOD = SCANNER_WRAPPER_CLASS
            .getMethodOrNullObject(TestData.READ_DECIMAL_METHOD_NAME, String.class);

//
// 	@Test
// 	public static void checkInputClassAndMethods() {
// 		long count = SCANNER_WRAPPER_CLASS.getPublicMethods().count();
//
// 		assertTrue(count <= 3, String.format(
// 				"det fanns fler publika metoder i klassen %s än förväntat (%d/%d). De enda publika metoderna ska vara de för inläsning.",
// 				SCANNER_WRAPPER_CLASS.name(), count, 3));
// 	}
//
// 	@Test
// 	public void checkNameOfClassForObviousProblems() {
// 		assertFalse(SCANNER_WRAPPER_CLASS.name().equals("Scanner"),
// 				"Scanner är inte ett bra namn på klassen, det går inte att skilja från java.util.Scanner");
// 		assertFalse(SCANNER_WRAPPER_CLASS.name().equals("Input"),
// 				"Input är inte ett bra namn på klassen, det är för allmänt i sig självt");
// 		assertFalse(SCANNER_WRAPPER_CLASS.name().contains("Class"),
// 				"Klassnamn ska inte innehålla ordet Class, det är onödigt och klottrar ner namnet");
// 		assertFalse(SCANNER_WRAPPER_CLASS.name().contains("Dog"),
// 				"Klassnamn ska inte innehålla ordet Dog, det är inte en klass som har något med hundar att göra");
// 		// Detta test kan misslyckas om namnet i singular slutar på s.
// 		// Har du ett sådant namn hör av dig till handledningsforumet
// 		// så uppdaterar vi testet.
// 		assertFalse(SCANNER_WRAPPER_CLASS.name().endsWith("s"),
// 				"Klassnamn är normalt i singular, om klassen inte representerar en samling av objekt vilket inte är fallet här. Även i fallet med samlingar är det vanlig att namnet på klassen är i singular, och istället visar att det är en samling genom att heta något som innehåller Collection, List, edyl.");
// 	}
//
// 	private void checkNameOfMethodForObviousProblems(MethodUnderTest m) {
// 		assertFalse(m.name().contains("Method"),
// 				"Metodnamn ska inte innehålla ordet Method, det är helt onödigt och klottrar ner namnet");
// 	}
//
// 	@Test
// 	public void checkNameOfMethodsForObviousProblems() {
// 		checkNameOfMethodForObviousProblems(READ_TEXT_METHOD);
// 		checkNameOfMethodForObviousProblems(READ_INTEGER_METHOD);
// 		checkNameOfMethodForObviousProblems(READ_DECIMAL_METHOD);
// 	}
//
// 	@Test
// 	public void classHasTwoPublicConstructors() {
// 		assertEquals(2, SCANNER_WRAPPER_CLASS.getPublicConstructors().count());
// 	}
//
// 	@Test
// 	public void classHasConstructorWithNoParameters() {
// 		assertDoesNotThrow(() -> SCANNER_WRAPPER_CLASS.getConstructor());
// 	}
//
// 	@Test
// 	public void classHasConstructorWithOneParameters() {
// 		assertDoesNotThrow(() -> SCANNER_WRAPPER_CLASS.getConstructor(InputStream.class));
// 	}
//
// 	@Test
// 	public void noUnnecessaryStaticFields() {
// 		assertTrue(SCANNER_WRAPPER_CLASS.getClassVariables().count() <= 1,
// 				"Det borde inte finnas något behov av mer än max en statisk variabel i inläsningsklassen. (Konstanter är en annan sak, där kan det vara bra med någon till.)");
// 	}
//
// 	@Test
// 	public void noStaticMethods() {
// 		assertEquals(0, SCANNER_WRAPPER_CLASS.getClassMethods().count(),
// 				"Det borde inte finnas något behov av några statiska metoder i inläsningsklassen.");
// 	}
//
// 	private Object getNewAdapter(InputStream in) {
// 		var adapter = assertTimeoutPreemptively(TIMEOUT, () -> {
// 			try {
// 				return SCANNER_WRAPPER_CLASS.getConstructor(InputStream.class).newInstance(in);
// 			} catch (RuntimeException e) {
// 				throw e.getCause().getClass() == IllegalStateException.class ? (IllegalStateException) e.getCause() : e;
// 			}
// 		}, String.format(TIMEOUT_MSG_TEMPLATE, "skapa en instans av inläsningsklassen"));
// 		return adapter;
// 	}
//
// 	private Object getNewAdapter(String input) {
// 		return getNewAdapter(new ByteArrayInputStream((input + "\n").getBytes()));
// 	}
//
// 	private void assertPromptWas(String expected) {
// 		out().assertStartsWith(expected, "%s ?>");
// 		out().trim().assertEndsWith("?>", "%s ?>");
// 	}
//
// 	private Object callInputMethod(MethodUnderTest m, Object adapter, String prompt) {
// 		var input = assertTimeoutPreemptively(TIMEOUT, () -> {
// 			return m.invoke(adapter, prompt);
// 		}, String.format(TIMEOUT_MSG_TEMPLATE, "anropa " + m));
// 		return input;
// 	}
//
// 	private Object callInputMethod(MethodUnderTest m, String input, String prompt) {
// 		return callInputMethod(m, getNewAdapter(input), prompt);
// 	}
//
// 	@Test
// 	public void testMethodToReadInt() {
// 		var result = callInputMethod(READ_INTEGER_METHOD, "123", "prompt integer");
// 		assertPromptWas("prompt integer");
// 		assertEquals(123, result);
// 	}
//
// 	@ParameterizedTest
// 	@CsvSource({ "en,GB", "sv,SE" }) // Testar både engelska och svenska landsinställningar
// 	public void testMethodReadDouble(String language, String country) {
// 		Locale defaultLocale = Locale.getDefault();
//
// 		try {
// 			Locale.setDefault(new Locale(language, country));
// 			// String.format för att landsinställningarna ska användas
// 			var result = callInputMethod(READ_DECIMAL_METHOD, String.format("%f", 1.23), "prompt decimal");
// 			assertPromptWas("prompt decimal");
// 			assertEquals(1.23, result);
// 		} finally {
// 			Locale.setDefault(defaultLocale);
// 		}
// 	}
//
// 	@Test
// 	public void testMethodToReadText() {
// 		var result = callInputMethod(READ_TEXT_METHOD, "input text", "prompt text");
// 		assertPromptWas("prompt text");
// 		assertEqualsIgnoreCase("input text", result);
// 	}
//
// 	@Test
// 	public void readingIntegerClearsBuffer() {
// 		Object adapter = getNewAdapter("1\ntext");
// 		Object result = callInputMethod(READ_INTEGER_METHOD, adapter, "prompt");
// 		assertEquals(1, result);
// 		result = callInputMethod(READ_TEXT_METHOD, adapter, "prompt");
// 		assertEqualsIgnoreCase("text", result);
// 	}
//
// 	@Test
// 	public void readingDecimalClearsBuffer() {
// 		Object adapter = getNewAdapter(String.format("%.1f%ntext", 1.2));
// 		Object result = callInputMethod(READ_DECIMAL_METHOD, adapter, "prompt");
// 		assertEquals(1.2, result);
// 		result = callInputMethod(READ_TEXT_METHOD, adapter, "prompt");
// 		assertEqualsIgnoreCase("text", result);
// 	}
//
// 	@Test
// 	public void readingDifferentThingsDoesNotCauseProblemsWithBuffering() {
// 		Object adapter = getNewAdapter(String.format("%s%n%d%n%f%n%d%n%s%n%f%n", "first", 2, 3.0, 4, "fifth", 6.0));
// 		Object result;
// 		result = callInputMethod(READ_TEXT_METHOD, adapter, "prompt");
// 		assertEqualsIgnoreCase("first", result);
// 		result = callInputMethod(READ_INTEGER_METHOD, adapter, "prompt");
// 		assertEquals(2, result);
// 		result = callInputMethod(READ_DECIMAL_METHOD, adapter, "prompt");
// 		assertEquals(3.0, result);
// 		result = callInputMethod(READ_INTEGER_METHOD, adapter, "prompt");
// 		assertEquals(4, result);
// 		result = callInputMethod(READ_TEXT_METHOD, adapter, "prompt");
// 		assertEqualsIgnoreCase("fifth", result);
// 		result = callInputMethod(READ_DECIMAL_METHOD, adapter, "prompt");
// 		assertEquals(6.0, result);
// 	}
//
// 	@Test
// 	public void creatingMultipleAdaptersThrowsException() {
// 		InputStream in = new ByteArrayInputStream(new byte[] {});
// 		assertDoesNotThrow(() -> {
// 			getNewAdapter(in);
// 		}, "Det första försöket att skapa ett objekt av klassen borde lyckats");
// 		assertThrows(IllegalStateException.class, () -> {
// 			getNewAdapter(in);
// 		}, "Det andra försöket att skapa ett objekt av klassen borde misslyckats");
// 	}
//
// 	@ParameterizedTest(name = "InputStream nr {0} upprepas")
// 	@ValueSource(ints = { 1, 2, 5, 10 })
// 	public void creatingMultipleAdaptersThrowsException(int i) {
// 		final InputStream[] repeated = { null };
// 		for (int n = 1; n <= i * 3; n++) {
// 			InputStream in = new ByteArrayInputStream("".getBytes());
// 			getNewAdapter(in);
// 			if (n == i)
// 				repeated[0] = in;
// 		}
//
// 		assertThrows(IllegalStateException.class, () -> {
// 			getNewAdapter(repeated[0]);
// 		});
// 	}
//
// 	@Test
// 	public void readingFromMultipleStreamsInParallellGivesCorrectInput() {
// 		Object first = getNewAdapter(String.format("1\n2\n3\n"));
// 		Object second = getNewAdapter(String.format("A\nB\nC\n"));
//
// 		final String MSG = "Fel värde inläst";
//
// 		assertEquals(1, callInputMethod(READ_INTEGER_METHOD, first, "prompt"), MSG);
// 		assertEqualsIgnoreCase("A", callInputMethod(READ_TEXT_METHOD, second, "prompt"), MSG);
// 		assertEquals(2, callInputMethod(READ_INTEGER_METHOD, first, "prompt"), MSG);
// 		assertEqualsIgnoreCase("B", callInputMethod(READ_TEXT_METHOD, second, "prompt"), MSG);
// 		assertEquals(3, callInputMethod(READ_INTEGER_METHOD, first, "prompt"), MSG);
// 		assertEqualsIgnoreCase("C", callInputMethod(READ_TEXT_METHOD, second, "prompt"), MSG);
//
// 	}
//

}
