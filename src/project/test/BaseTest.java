package project.test;/*
 * Denna klass innehåller kod som är gemensam för alla JUnit-testfall till inlämningsuppgiftens olika delar.
 * För att dessa ska fungera måste du ladda ner denna fil och lägga den i samma katalog som koden som ska testas.
 */

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.lang.reflect.*;
import java.util.*;
import java.util.stream.*;

// TODO: Ta bort allt kommenterat när allt är flyttat
public abstract class BaseTest {

	protected void setIn(String format, Object... args) {
		String input = String.format(format, args);
		System.setIn(new ByteArrayInputStream(input.getBytes()));
	}

	protected final void assertEqualsIgnoreCase(String expected, Object actual) {
		assertTrue(actual instanceof String, actual + " is not a String");
		assertEquals(expected.toLowerCase(), ((String) actual).toLowerCase(), String.format(
				"Var \"%s\", men borde varit \"%s\" (utan hänsyn till stora och små bokstäver)", actual, expected));
	}

	protected final void assertEqualsIgnoreCase(String expected, Object actual, String msg) {
		assertTrue(actual instanceof String, actual + " is not a String");
		assertEquals(expected.toLowerCase(), ((String) actual).toLowerCase(),
				String.format(msg + ",var \"%s\", men borde varit \"%s\" (utan hänsyn till stora och små bokstäver)",
						actual, expected));
	}

	protected void assertBasicStructureOfSingleMethodAssignmentMainClass(ClassUnderTest cut, MethodUnderTest mut) {
		// Klassen
		assertTrue(cut.exists(), "Hittar inte klassen %s. Är namnet korrekt i TestData.java?".formatted(cut.name()));

		assertEquals(0, cut.getClassVariables().count(),
				"Det borde inte behövas några statiska variabler för denna uppgift");
		assertEquals(0, cut.getClassMethods().count(),
				"Det borde inte behövas några statiska metoder för denna uppgift");

		for (Class<?> c : new Class<?>[] { String.class, char.class, byte.class, short.class, int.class, long.class,
				float.class, double.class }) {
			assertEquals(0, cut.getInstanceFields(c).count(),
					"Det borde inte finnas något behov av någon instansvariabel av typen %s i denna uppgift"
							.formatted(c));
		}

		// Metoden under test
		assertTrue(mut.exists(), "Hittar ingen metod med namnet %s() i klassen %s. Är namnen korrekta i TestData.java?"
				.formatted(mut.name(), cut.name()));
	}

	protected void assertListExists(ClassUnderTest cut, String listName) {
		// Kastar undantag om fältet inte finns
		FieldUnderTest list = cut.getField(listName);

		// Går tyvärr inte att kontrollera att listan faktiskt innehåller det vi vill
		// att den ska innehålla, så vi nöjer oss med att kontrollera att det är en
		// lista
		assertTrue(list.isAssignableTo(List.class),
				"Kunde inte hitta någon lista med namnet %s i klassen %s".formatted(listName, cut.name()));
	}

	// Går tyvärr inte att kontrollera att listan faktiskt innehåller det vi vill,
	// så vi nöjer oss med att kontrollera att det är en lista
	@SuppressWarnings("unchecked")
	protected <T> List<T> getList(Object sut, String name) {
		ClassUnderTest cut = new ClassUnderTest(sut.getClass());
		FieldUnderTest field = cut.getField(name);
		assertTrue(field.isAssignableTo(List.class));
		return (List<T>) field.getValue(sut);
	}

	// TODO: ordna metoderna i klasserna nedan i en mer logisk ordning, nu ligger de
	// i den ordning de skapades
	private static record NoSuchClass(String unknownClassName) {

	}

	public static abstract class SoftwareUnderTest<T> {

		private final String name;
		private final T sut;

		public SoftwareUnderTest(String name, T sut) {
			this.name = name;
			this.sut = sut;
		}

		public String name() {
			return name;
		}

		public boolean exists() {
			return sut != null;
		}

		public T sut() {
			if (sut == null)
				throw new IllegalStateException(
						"Kunde inte hitta en %s med namnet \"%s\". Kontrollera namnet i TestData.java"
								.formatted(sutTypeName(), name));
			return sut;
		}

		protected abstract String sutTypeName();

		protected final static boolean isPublic(Member m) {
			return Modifier.isPublic(m.getModifiers());
		}

		protected final static boolean isPrivate(Member m) {
			return Modifier.isPrivate(m.getModifiers());
		}

		protected final static boolean isStatic(Member m) {
			return Modifier.isStatic(m.getModifiers());
		}

		protected final static boolean isFinal(Member m) {
			return Modifier.isFinal(m.getModifiers());
		}

		protected final static boolean isConstant(Field f) {
			return isStatic(f) && isFinal(f) && f.getName().equals(f.getName().toUpperCase());
		}

		protected final static boolean isConstant(Field f, Class<?>... okTypes) {
			boolean okType = false;
			for (Class<?> type : okTypes) {
				okType = okType || f.getType() == type;
			}
			return okType && isConstant(f);
		}

		@Override
		public String toString() {
			return name();
		}

	}

	public static class ClassUnderTest extends SoftwareUnderTest<Class<?>> {

		public ClassUnderTest(String name) {
			this(name, load(name));
		}

		public ClassUnderTest(Class<?> cut) {
			this(cut.getName(), cut);
		}

		private ClassUnderTest(String name, Class<?> cut) {
			super(name, cut);
		}

		private static Class<?> load(String className) {
			try {
				return ClassLoader.getSystemClassLoader().loadClass(className);
			} catch (ClassNotFoundException e) {
				return NoSuchClass.class;
			}

		}

		@Override
		protected String sutTypeName() {
			return "klass";
		}

		public Stream<FieldUnderTest> getFields() {
			return Arrays.stream(sut().getDeclaredFields()).map(f -> new FieldUnderTest(f));
		}

		public Stream<FieldUnderTest> getInstanceFields() {
			return getFields().filter(f -> f.isNonStatic());
		}

		public Stream<FieldUnderTest> getInstanceFields(Class<?> type) {
			return getFields().filter(f -> f.isNonStatic() && f.hasType(type));
		}

		// TODO: Borde finnas en för alla fält, inte bara instans
		public Stream<FieldUnderTest> getInstanceFieldsAssignableTo(Class<?> type) {
			return getFields().filter(f -> type.isAssignableFrom(f.sut().getType()));
		}

		public Stream<FieldUnderTest> getFinalAndNonFinalInstanceVariables() {
			return getFields().filter(f -> f.isNonStatic());
		}

		public Stream<FieldUnderTest> getNonFinalInstanceVariables() {
			return getFields().filter(f -> f.isNonStatic() && f.isNonFinal());
		}

		public Stream<FieldUnderTest> getClassVariables() {
			return getFields().filter(f -> f.isStatic() && f.isNonFinal());
		}

		public Stream<FieldUnderTest> getConstants() {
			return getFields().filter(f -> f.isStatic() && f.isFinal());
		}

		public Stream<FieldUnderTest> getStaticFields() {
			return getFields().filter(f -> f.isStatic());
		}

		public Stream<ConstructorUnderTest> getConstructors() {
			return Arrays.stream(sut().getDeclaredConstructors()).map(c -> new ConstructorUnderTest(c));
		}

		public Stream<ConstructorUnderTest> getPublicConstructors() {
			return getConstructors().filter(c -> c.isPublic());
		}

		public Stream<MethodUnderTest> getMethods() {
			return Arrays.stream(sut().getDeclaredMethods()).map(m -> new MethodUnderTest(m.getName(), m));
		}

		public Stream<MethodUnderTest> getInstanceMethods() {
			return getMethods().filter(m -> m.isNonStatic());
		}

		public Stream<MethodUnderTest> getClassMethods() {
			return getMethods().filter(m -> m.isStatic());
		}

		public Stream<MethodUnderTest> getPublicMethods() {
			return getMethods().filter(m -> m.isPublic());
		}

		public FieldUnderTest getField(String name) {
			try {
				return new FieldUnderTest(sut().getDeclaredField(name));
			} catch (NoSuchFieldException | SecurityException e) {
				throw new IllegalStateException("Hittar inget fält med namnet %s i %s".formatted(name, name()));
			}
		}

		public FieldUnderTest getOnlyFieldOfType(Class<?> type) {
			var fields = getFields().filter(f -> f.hasType(type)).toList();
			if (fields.size() == 0)
				throw new IllegalStateException("Hittar inget fält med typen %s i  %s".formatted(type, name()));
			if (fields.size() > 1)
				throw new IllegalStateException(
						"Det finns flera fält av typen %s i %s, testprogrammet förväntade sig bara ett".formatted(type,
								name()));
			return fields.get(0);
		}

		public FieldUnderTest getOnlyFieldAssignableFromType(Class<?> type) {
			var fields = getInstanceFieldsAssignableTo(type).toList();
			if (fields.size() == 0)
				throw new IllegalStateException("Inget fält av typen %s eller liknande i %s".formatted(type, name()));
			if (fields.size() > 1)
				throw new IllegalStateException(
						"Flera fält av typen %s eller liknande i %s, testprogrammet förväntade sig bara ett"
								.formatted(type, name()));
			return fields.get(0);
		}

		public ConstructorUnderTest getConstructor(Class<?>... parameterTypes) {
			try {
				return new ConstructorUnderTest(sut().getConstructor(parameterTypes));
			} catch (NoSuchMethodException | SecurityException e) {
				throw new IllegalStateException("Det finns ingen konstruktor med parametrarna %s i %s"
						.formatted(paramsAsString(parameterTypes), name()));
			}
		}

		public MethodUnderTest getMethod(String methodName, Class<?>... parameterTypes) {
			String fullMethodName = fullMethodName(methodName, parameterTypes);
			try {
				return new MethodUnderTest(fullMethodName, sut().getDeclaredMethod(methodName, parameterTypes));
			} catch (NoSuchMethodException | SecurityException e) {
				return new MethodUnderTest("Ej hittad metod (%s)".formatted(fullMethodName), null);
			}
		}

		public MethodUnderTest getMethodOrNullObject(String methodName, Class<?>... parameterTypes) {
			String fullMethodName = fullMethodName(methodName, parameterTypes);
			try {
				return getMethod(methodName, parameterTypes);
			} catch (IllegalStateException e) {
				return new MethodUnderTest(fullMethodName, null);
			}
		}

		private String fullMethodName(String methodName, Class<?>... parameterTypes) {
			return "%s.%s(%s)".formatted(name(), methodName, paramsAsString(parameterTypes));
		}

		private String paramsAsString(Class<?>... parameterTypes) {
			return Arrays.stream(parameterTypes).map(p -> p.getName()).collect(Collectors.joining(", "));
		}

	}

	public static abstract class MemberUnderTest<T extends Member> extends SoftwareUnderTest<T> {
		public MemberUnderTest(String name, T sut) {
			super(name, sut);
		}

		public final boolean isPublic() {
			return isPublic(sut());
		}

		public final boolean isPrivate() {
			return isPrivate(sut());
		}

		public final boolean isStatic() {
			return isStatic(sut());
		}

		public final boolean isNonStatic() {
			return !isStatic();
		}

		public final boolean isFinal() {
			return isFinal(sut());
		}

		public final boolean isNonFinal() {
			return !isFinal();
		}

	}

	public static class FieldUnderTest extends MemberUnderTest<Field> {

		public FieldUnderTest(Field fut) {
			super(fut.getName(), fut);
			fut.setAccessible(true);
		}

		@Override
		protected String sutTypeName() {
			return "fält";
		}

		public boolean hasType(Class<?> type) {
			return sut().getType() == type;
		}

		public boolean isAssignableTo(Class<?> type) {
			return type.isAssignableFrom(sut().getType());
		}

		public Object getValue(Object obj) {
			try {
				return sut().get(obj);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				throw new IllegalArgumentException(
						"Unable to access the field %s on the object %s".formatted(sut(), obj), e);
			}
		}

	}

	public static class ConstructorUnderTest extends MemberUnderTest<Constructor<?>> {

		public ConstructorUnderTest(Constructor<?> cut) {
			super(cut.getName(), cut);
		}

		public ConstructorUnderTest(String fullname) {
			super(fullname, null);
		}

		@Override
		protected String sutTypeName() {
			return "konstruktor";
		}

		public Object newInstance(Object... args) {
			try {
				return sut().newInstance(args);
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e) {
				if (e.getClass() == InvocationTargetException.class)
					throw new RuntimeException("Fel vid instansiering av klassen %s med argumenten %s: %s"
							.formatted(name(), Arrays.toString(args), e.getCause()), e.getCause());

				throw new RuntimeException("Fel vid instansiering av klassen %s med argumenten %s: %s".formatted(name(),
						Arrays.toString(args), e), e);
			}
		}

	}

	public static class MethodUnderTest extends MemberUnderTest<Method> {

		private MethodUnderTest(String name, Method mut) {
			super(name, mut);
			if (mut != null)
				mut.setAccessible(true);
		}

		@Override
		protected String sutTypeName() {
			return "metod";
		}

		public int getParameterCount() {
			return sut().getParameterCount();
		}

		public Class<?> getReturnType() {
			return sut().getReturnType();
		}

		public Object invoke(Object obj, Object... args) {
			try {
				return sut().invoke(obj, args);
			} catch (IllegalAccessException | InvocationTargetException e) {
				throw new RuntimeException("Fel vid anrop på metoden " + name(), e);
			}
		}

	}

}
