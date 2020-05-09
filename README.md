# JUnit And Mockito

Latest version of Junit is 5.

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
            <artifactId>mockito-all</artifactId>
            <version>1.10.19</version>
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

### Parameterised tests.
Thes tests are used when we have similar kind of multiple test cases with the only difference of 
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


### Stubs
Stubs are the dummy implementation of our classes.
 For example we have 2 services
 
 TodoBusinessService
 TodoService
 
 and we are calling todoService in todoBusinessService. For writting test cases of 
 todoBusinessService. we can either mock the todoService or can use stub to give a 
 dummy implementation like :
 
 ```java
 TodoService
 
 public interface TodoService {
     public List<String> retrieveTodos(String user);
 }
 ```
and Stub 

```java
public class TodoServiceStub implements TodoService {
    @Override
    public List<String> retrieveTodos(String user) {
        return Arrays.asList("Learn Spring MVC","Learn Spring","Learn Junit");
    }
}
```

So the problem with Stub is :                                          

Dynamic data : We have to create different stubs each time we need different dynamic data testing

Extra code : There can be more than 1 method in an interface. So to test 1 service method we have to
implement others also in stub.


