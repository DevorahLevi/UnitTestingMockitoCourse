package com.in28minutes.untitesting.unittesting.business;

import com.in28minutes.untitesting.unittesting.data.SomeDataService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SomeDataServiceMockTest {

    @Mock
    SomeDataService dataServiceMock = mock(SomeDataService.class);

    @InjectMocks
    SomeBusinessImpl business = new SomeBusinessImpl();

    @Test
    public void calculateSumUsingDataService_basic() {

        when(dataServiceMock.retrieveAllData()).thenReturn(new int[]{1, 2, 3});

        int result = business.calculateSumUsingDataService();
        int expected = 6;
        assertThat(expected).isEqualTo(result);
    }

    @Test
    public void calculateSum_empty() {

        when(dataServiceMock.retrieveAllData()).thenReturn(new int[]{});

        int result = business.calculateSumUsingDataService();
        int expected = 0;
        assertThat(expected).isEqualTo(result);
    }

    @Test
    public void calculateSum_oneValue() {

        when(dataServiceMock.retrieveAllData()).thenReturn(new int[]{5});

        int result = business.calculateSumUsingDataService();
        int expected = 5;
        assertThat(expected).isEqualTo(result);
    }
}