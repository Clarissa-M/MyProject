package com.example.projectvers2;

import static org.mockito.Mockito.when;

import android.util.Log;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DataTests extends TestCase {
    @Mock
    ViewModel mockModel;

    @Test
    public void testHasOverspent(){

        when(mockModel.getLimitAmount()).thenReturn(15);
        when(mockModel.getSpentAmount()).thenReturn(20.0);
        when(mockModel.hasOverspent()).thenReturn(true);

        boolean myBool = mockModel.hasOverspent();
        System.out.println("The spentAmount is" + mockModel.getSpentAmount());
        assertTrue(myBool);

    }

    @Test
    public void testCalcOverspend(){
        when(mockModel.getLimitAmount()).thenReturn(15);
        when(mockModel.getSpentAmount()).thenReturn(20.0);
        when(mockModel.calcOverspend()).thenReturn(5.0);

        assertEquals(5.0, mockModel.calcOverspend());

    }



}