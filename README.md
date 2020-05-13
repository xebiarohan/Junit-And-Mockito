# JUnit And Mockito

Latest version of JUnit is 5.

Dependencies required
```java
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter-engine</artifactId>
            <version>5.1.0</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter-params</artifactId>
            <version>5.5.2</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.mockito</groupId>
            <artifactId>mockito-junit-jupiter</artifactId>
            <version>2.23.0</version>
            <scope>test</scope>
        </dependency>

```

### Difference between JUnit4 and JUnit5

#### Exception expected
In JUnit4 we write code with @Test to expect an exception in test case. but in JUnit5
we have assertThrow method which takes Exception with lambda function.

JUnit4
```java
@Test(expected = NullPointerException.class)
public void checkException() {
    int[] numbers = null;
    Arrays.sort(numbers);
}
```

JUnit5
```java
    @Test
    public void testExceptionScenaario() {
        assertThrows(NullPointerException.class,() -> {
            int[] numbers = null;
            Arrays.sort(numbers);
        });
    }
```    
#### Timeout
In JUnit4 we write timeout time with @Test annotation but in JUnit5 we have assertTimeout method

JUnit4
```java
@Test(timeout="1000")
public void checkPerformance() {
    int[] array = {34,56,1,44};
    for(int i=0;i<1000000;i++) {
        array[0] = i;
        Arrays.sort(array);
    }
}
```

JUnit5
```java
    @Test
    public void testPerformance() {
        assertTimeout(Duration.ofMillis(100), () -> {
            int[] array = {34,5,1,36};
            for(int i=0;i<1000000;i++) {
                array[0] = i;
                Arrays.sort(array);
            }
        });

    }
```  

#### Mock class annotations

JUnit4
In JUnit4 we require @RunWith annotation
example 
```java
@RunWith(MockitoJUnitRunner.class)
```

In JUnit5 we have @@ExtendWith annotation
example
```java
@ExtendWith(MockitoExtension.class)
```

#### Multiple Runners
In JUnit4 we can have only 1 test runner and for others we have to use rules, In JUnit5 we can have multiple runners.

#### Single Jar
JUnit4 is a bog single Jar file. even if we want to use a single functionality we have to import whole library.In JUnit5
we have multiple small libraries which we can import as per our needs.

#### New Java version 
JUnit4 is features upto Java7 they don't have features of Java8 and newer versions. Where as JUnit5 have all these features like
lambda functions.

#### Annotation changes :
Some annotation in JUnit4 is now changed in JUnit5 like :
1. @Before changed to @BeforeEach
2. @After changed to @AfterEach
3. @BeforeClass changed to  @BeforeAll
4. @AfterClass changed to @AfterAll
5. @Ignore changed to @Disabled

#### Chaining assertions :
we can now chain multiple assertions in one test case like :

```java
@Test
public void shouldAssertAllTheGroup() {
    List<Integer> list = Arrays.asList("alpha", "beta", "gamma");
    Assertions.assertAll("Some assertion message",
        () -> Assertions.assertEquals(list.get(0), "alpha"),
        () -> Assertions.assertEquals(list.get(1), "beta"),
        () -> Assertions.assertEquals(list.get(2), "gamma"));
}
```

#### PowerMocks
The only current issue with JUnit5 is that as per today there is no a concrete support for Power mocks. Where as in JUnit4 PowerMocks
works perfectly.


### Parameterised tests.
These tests are used when we have similar kind of multiple test cases with the only difference of 
test inputs like
```java
    @Test
    public void string1IsNotNull() {
       assertNotNull("Hello");
    }

    @Test
    public void string2IsNotNull() {
         assertNotNull("world");
    }
```

So we can write it as :

```java
    @ParameterizedTest
    @ValueSource(strings = {"Hello", "World"})
    void shouldPassNonNullMessageAsMethodParameter(String message) {
        assertNotNull(message);
    }
```


### Stub testing
Stubs are the dummy implementation of our classes.
 For example we have 2 services
 
 TodoBusinessService
 TodoService
 
 and we are calling todoService in todoBusinessService. For writing test cases of 
 todoBusinessService. we can either mock the todoService or can use stub to give a 
 dummy implementation like :
 
 ```java
 public interface TodoService {
     public List<String> retrieveTodos(String user);
 }
 ```
and Stub 

```java
public class TodoServiceStub implements TodoService {
    @Override
    public List<String> retrieveTodos(String user) {
        return Arrays.asList("Learn Spring MVC","Learn Spring","Learn JUnit");
    }
}
```

test case 
```java
    @Test
    public void testRetreiveTodosRelatedToSpring() {
        TodoService todoServiceStub = new TodoServiceStub();
        TodoBusinessImpl todoBusiness = new TodoBusinessImpl(todoServiceStub);
        assertEquals(2, todoBusiness.retreiveTodosRelatedToSpring("Dummy").size());
        assertTrue(true);
    }
```    

So the problem with Stub is :                                          

Dynamic data : We have to create different stubs each time we need different dynamic data testing

Extra code : There can be more than 1 method in an interface. So to test 1 service method we have to
implement others also in stub.


### Mock testing
In mock testing, the dependencies are replaced with objects that simulate the behaviour of the real ones.

test case :
```java
   @Test
    public void testRetreiveTodosRelatedToSpring() {
        TodoService todoService = mock(TodoService.class);

        List<String> strings = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn JUnit");

        when(todoService.retrieveTodos("Dummy")).thenReturn(strings);

        TodoBusinessImpl todoBusiness = new TodoBusinessImpl(todoService);
        assertEquals(2, todoBusiness.retreiveTodosRelatedToSpring("Dummy").size());
    }
```    

### Returning multiple values.
We can return multiple values in mock statement like :

```java
    @Test
    public void multipleReturnValue() {
        List list = mock(List.class);
        when(list.size()).thenReturn(2).thenReturn(3);
        assertEquals(2,list.size());
        assertEquals(3,list.size());
    }
```

### Argument Matcher
When we want to test any method for general input like :

```java
    @Test
    public void listGetTest() {
        List list = mock(List.class);
        //Argument Matcher
        when(list.get(anyInt())).thenReturn("Alpha");
        assertEquals("Alpha",list.get(2));
        assertEquals("Alpha",list.get(10));
    }
```

We have different types of argument Matchers

anyInt()
anyDouble()
anyObject()
anyString()
anyBoolean()
anyChar() and many more..

they all part of Matchers.class


### BDD (Behavioural driven development)
Its a method in which we develop our code based on scenarios.
It can be divided into 3 parts
Given  -  when  -  then

Given some value to the function
when something got executed on that value
Then some output it will return

example :

Given a customer comes and buy a candy from s shop
when we give the candy and take the money
then our money increases and candy decreases

BDD test case :

```java
    @Test
    public void testRetreiveTodosRelatedToSpring() {
        //Given
        TodoService todoService = mock(TodoService.class);
        List<String> strings = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn JUnit");
        given(todoService.retrieveTodos("Dummy")).willReturn(strings);
        TodoBusinessImpl todoBusiness = new TodoBusinessImpl(todoService);

        // when
        int todoCount = todoBusiness.retreiveTodosRelatedToSpring("Dummy").size();

        // then
        assertEquals(2, todoCount);

    }
```    

### Checking method calls in a test case
We can check if any other service call is happening in our test code and how
many times it is happening.

It is used to test the functions which are not returning any value. Are of void types.

like : 
```java
    public void deleteTodoNotRelatedToSprinng(String user) {
        List<String> todos = todoService.retrieveTodos(user);
        todos.stream().filter(x -> !x.contains("Spring")).forEach(x ->
                todoService.deleteTodo(x)
        );
    }
```

Now here we can verify when this todoService deleteTodo method gets called and 
with which parameter and how many times

```java
    @Test
    public void testDeleteTodosNotRelatedToSpring() {
        //Given
        TodoService todoService = mock(TodoService.class);

        List<String> strings = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn JUnit");

        given(todoService.retrieveTodos("Dummy")).willReturn(strings);
        TodoBusinessImpl todoBusiness = new TodoBusinessImpl(todoService);

        // when
        todoBusiness.deleteTodoNotRelatedToSprinng("Dummy");

        // Just wanter to test is it calling or not
        verify(todoService).deleteTodo("Learn JUnit");

        // exactly wanted to test how many times it is calling
        verify(todoService, times(1)).deleteTodo("Learn JUnit");

        // minimum 1 time it should get called
        verify(todoService, atLeastOnce()).deleteTodo("Learn JUnit");

        // Atleast 5 times
        verify(todoService, atLeast(5)).deleteTodo(anyString());
        
        // Maximium how many times
        verify(todoService, atMost(6)).deleteTodo(anyString());
        
        //  should never be called 
        verify(todoService,never()).deleteTodo("Learn Spring MVC");

    }

```

### Hamcrest Matcher
This matcher help in increasing the readability of test cases

```java
        <dependency>
            <groupId>org.hamcrest</groupId>
            <artifactId>hamcrest-library</artifactId>
            <version>2.2</version>
            <scope>test</scope>
        </dependency>

```

like :

```java
    @Test
    public void test() {
        List<Integer> list = Arrays.asList(1,2,3,4);
        // List
        assertThat(list,hasSize(4));
        assertThat(list,hasItems(1,2,3,4));
        assertThat(list,everyItem(greaterThan(0)));
        assertThat(list,everyItem(lessThan(10)));

        // String
        assertThat("", isEmptyString());
        assertThat(null,isEmptyOrNullString());

        //Array
        Integer[] array = {1,2,3};
        assertThat(array,arrayWithSize(3));
        assertThat(array,arrayContaining(1,2,3));
        assertThat(array,arrayContainingInAnyOrder(1,3,2));
    }
```

### Mockito Annotation Based Test cases
In mockito annotation based test cases we mock mocks and inject mocks the classes.
InjectMock the class for which we are writing test case and Mock the classes which 
are used in main test class.

example
```java
@ExtendWith(MockitoExtension.class)
public class TodoBusinessImplInjectMockTest {

    @Mock
    private TodoService todoService;

    @InjectMocks
    private TodoBusinessImpl todoBusiness;

    @Test
    public void testRetreiveTodosRelatedToSpring() {
        List<String> strings = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn JUnit");
        when(todoService.retrieveTodos("Dummy")).thenReturn(strings);
        assertEquals(2, todoBusiness.retreiveTodosRelatedToSpring("Dummy").size());
    }
}
```

### Spy
A Spy gets all the logic from the class. So it is upto us to use some part as the
exact functionality of the class and some part we can mock.

example
```java
    @Test
    public void test() {
        ArrayList listSpy = spy(ArrayList.class);
        assertEquals(0,listSpy.size());

        listSpy.add("alpha");
        assertEquals(1,listSpy.size());

        when(listSpy.size()).thenReturn(5);
        assertEquals(5,listSpy.size());
    }
```

In 3rd line we are adding an element in the list, which is the exact functionality
of the List class, we are not mocking it.

In 5th line we are mocking the size method of list.

So using Spy we can combine both types.

But its not recommended to use Spy, always try to use Mock. Spy was for the legacy code.


#### Note : Mockito cannot mock final, static and private methods. For that we have power mock.

#### Power Mock
Power mock is used to mock static methods, private methods.
Power mock is not compatible with JUnit5 So we will discuss it will JUnit4.

pom.xml :

```java
<dependency>
    <groupId>org.powermock</groupId>
    <artifactId>powermock-api-mockito</artifactId>
    <version>1.7.4</version>
    <scope>test</scope>
</dependency>
<dependency>
    <groupId>org.powermock</groupId>
    <artifactId>powermock-module-junit4</artifactId>
    <version>1.6.4</version>
    <scope>test</scope>
</dependency>
```

##### Mocking static methods
Using power mock we can easily mock static methods.

We can return a mocked value if some static method is called or can verify that static method
is called or not.

example :

```java
public int methodCallingAStaticMethod() {
        //privateMethodUnderTest calls static method SomeClass.staticMethod
        List<Integer> stats = dependency.retrieveAllStats();
        long sum = 0;
        for (int stat : stats)
            sum += stat;
        return UtilityClass.staticMethod(sum);
    }
```

for this method we will write test case. Here we are calling UtilityClass.staticMethod(sum) static method.

So the test case will be

```java
@RunWith(PowerMockRunner.class)
@PrepareForTest(UtilityClass.class)
class SystemUnderTest {
    
    @Mock
    Dependency dependency;
    
    @InjectMocks
    SystemUnderTest systemUnderTest;

    @Test
    public void test() {
        List<Integer> stats = Arrays.asList(1,2,3);
        when(dependency.retrieveAllStats()).thenReturn(stats);

        PowerMockito.mockStatic(UtilityClass.class);
        when(UtilityClass.staticMethod(6)).thenReturn(100);

        int result = systemUnderTest.methodCallingAStaticMethod();

        assertEquals(100,result);

        PowerMockito.verifyStatic(UtilityClass.class);
        UtilityClass.staticMethod(6);
    }

}
```
So we can divide this test case in 3 parts

1st : We have to prepare the static class for the test case. Here are we doing it using @PrepareForTest() annotation

2nd : Then we have to mock the static class using powerMockito. here we are doing in (line number 3) in test method.
And can mock the return value from static method (line number 4)

3rd : To verify that a static method is called or not we can first use verifyStatic() to specify the class and
then we can write the method which we want to verify (line no 7 and 8).


##### Invoking Private methods

Private method can be tested using powerMock . But its not a good practice to test private test cases.

private method :
```java
 private long privateMethodUnderTest() {
        List<Integer> stats = dependency.retrieveAllStats();
        long sum = 0;
        for (int stat : stats)
            sum += stat;
        return sum;
    }
```

We can test a private method using Whitebox

```java
@RunWith(PowerMockRunner.class)
public class InvokingPrivateMethodTest {

    @Mock
    Dependency dependency;

    @InjectMocks
    SystemUnderTest systemUnderTest;

    @Test   
    public void test() throws Exception {
        List<Integer> stats = Arrays.asList(1, 2, 3);
        when(dependency.retrieveAllStats()).thenReturn(stats);

        long result = Whitebox.invokeMethod(systemUnderTest, "privateMethodUnderTest");
        assertEquals(6, result);
    }
}

```

In Whitebox invokeMethod we specify the class name as the first argument and method name as the
second argument.

##### Mocking a constructor

Constructor testing can be done using powerMock in two steps

First we need to prepare the class using @@PrepareForTest(), in this annotation we will pass the class which
will call the constructor.

Like if class A is calling ArrayList then here we will do @PrepareForTest(A.class)

Second we will override the constructor

method :

```java
    public int methodUsingAnArrayListConstructor() {
        ArrayList list = new ArrayList();
        return list.size();
    }
```

mocking :
```java
@RunWith(PowerMockRunner.class)
@PrepareForTest(SystemUnderTest.class)
public class InvokingConstructorMethodTest {

    @InjectMocks
    SystemUnderTest systemUnderTest;

    @Mock
    ArrayList mockList;

    @Test
    public void test() throws Exception {
        when(mockList.size()).thenReturn(5);

        PowerMockito.whenNew(ArrayList.class).withAnyArguments().thenReturn(mockList);

        int size = systemUnderTest.methodUsingAnArrayListConstructor();
        assertEquals(1, size);
    }
}
```

Note: PowerMock is not recommended in latest projects. Its not a good  coding practice.
There is no need to test static methods, private methods. They get covered in public methods.

