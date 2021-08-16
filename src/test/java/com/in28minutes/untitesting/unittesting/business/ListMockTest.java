package com.in28minutes.untitesting.unittesting.business;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
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
        String value2 = mock.get(0);

        verify(mock).get(0);
        verify(mock).get(anyInt());
//        verify(mock, times(2)).get(anyInt());
//        verify(mock, atLeast(1)).get(anyInt());
//        verify(mock, atLeastOnce()).get(anyInt());
//        verify(mock, atMost(2)).get(anyInt());
//        verify(mock, never()).get(2);
    }

}