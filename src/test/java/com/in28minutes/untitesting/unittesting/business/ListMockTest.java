package com.in28minutes.untitesting.unittesting.business;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.array;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ListMockTest {

    List<String> mock = mock(List.class);

    @Test
    public void size_basic() {
        when(mock.size()).thenReturn(5);
        assertThat(5).isEqualTo(mock.size());
    }

    @Test
    public void size_returnDifferentValues() {
        when(mock.size()).thenReturn(5).thenReturn(10);
        assertThat(5).isEqualTo(mock.size());
        assertThat(10).isEqualTo(mock.size());
    }

    @Test
    public void size_returnWithParameters() {
        when(mock.get(0)).thenReturn("in 28 minutes.");
        assertThat(mock.get(0)).isEqualTo("in 28 minutes.");
        assertThat(mock.get(1)).isEqualTo(null);
    }

    @Test
    public void size_returnWithGenericParameters() {
        when(mock.get(anyInt())).thenReturn("in 28 minutes.");
        assertThat(mock.get(0)).isEqualTo("in 28 minutes.");
        assertThat(mock.get(1)).isEqualTo("in 28 minutes.");
    }

    @Test
    public void verificationBasics() {
        String value1 = mock.get(0);
        String value2 = mock.get(1);

        verify(mock).get(0);
//        verify(mock).get(anyInt()); // By default, this checks that it was only called once. So if it was called twice, as in lines 47, 48, the test will fail.
        verify(mock, times(2)).get(anyInt());
        verify(mock, atLeast(1)).get(anyInt());
        verify(mock, atLeastOnce()).get(anyInt());
        verify(mock, atMost(2)).get(anyInt());
        verify(mock, never()).get(2); // Tests that .get(2) is never called.
    }

    @Test
    public void argumentCapturing() {
        mock.add("SomeString");

        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(mock).add(captor.capture());

        assertThat("SomeString").isEqualTo(captor.getValue());
    }

    @Test
    public void multipleArgumentCapturing() {
        mock.add("SomeString1");
        mock.add("SomeString2");

        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);

        verify(mock, times(2)).add(captor.capture());

        List<String> allValues = captor.getAllValues();

        assertThat("SomeString1").isEqualTo(allValues.get(0));
        assertThat("SomeString2").isEqualTo(allValues.get(1));
    }

    @Test
    public void mocking() {
        ArrayList arrayListMock = mock(ArrayList.class);

        System.out.println(arrayListMock.get(0)); // null
        System.out.println(arrayListMock.size()); // 0

        arrayListMock.add("Test");
        arrayListMock.add("Test2");
        System.out.println(arrayListMock.size()); // Will still be 0 because this is a mock ArrayList, not a real one; Not retaining any values.

        when(arrayListMock.size()).thenReturn(5);
        System.out.println(arrayListMock.size()); // 5
    }

    @Test
    public void spying() {
        // A spy, by default, retains behavior (code) of the original class. You can stub (override (when statements)) and verify specific behavior (methods) on a Spy.
        ArrayList arrayListSpy = spy(ArrayList.class); // Original behavior of the ArrayList is retained because it is a spy, not a mock.

//        System.out.println(arrayListSpy.get(0)); // This will throw an index out of bounds exception because you will be trying to do .get(0) on a list that has nothing in it yet.
        System.out.println(arrayListSpy.size()); // 0

        arrayListSpy.add("Test0");
        System.out.println(arrayListSpy.get(0)); // "Test0"
        System.out.println(arrayListSpy.size()); // 1

        arrayListSpy.add("Test");
        arrayListSpy.add("Test2");
        System.out.println(arrayListSpy.size()); // 3

        when(arrayListSpy.size()).thenReturn(5); // Now, we are overriding the classes retained behavior. No matter how much we add, it will always return 5 for .size()
        System.out.println(arrayListSpy.size()); // 5
        arrayListSpy.add("Test3");
        arrayListSpy.add("Test4");
        System.out.println(arrayListSpy.size()); // 5
        System.out.println(arrayListSpy.get(3)); // Will still return the correct value, but it will always return a size 5

//        Why use Spy instead of a Mock?
//          -> Sometimes you want to use the original dependency and you don't want to use a mock dependency, but also want to find out what is happening with it.
    }

}