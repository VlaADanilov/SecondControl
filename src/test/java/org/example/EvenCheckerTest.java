package org.example;

import org.junit.Assert;
import org.junit.Test;

public class EvenCheckerTest{

    @Test
    public void checkOne(){
        byte[] arr = new byte[]{1,2,3,4,5,6,7,8,9,10}; //1, 10, 11, 100, 101, 110, 111, 1000, 1001, 1010
        // всего 17 единиц
        Assert.assertEquals(1, Main.counter(arr));
    }
    @Test
    public void checkTwo(){
        byte[] arr = new byte[]{12,64,3,2,1}; // 1100, 1_000_000, 11, 10, 1
        // всего 7 единиц
        Assert.assertEquals(1, Main.counter(arr));
    }
    @Test
    public void checkThree(){
        byte[] arr = new byte[] {0,0,0,0,0,0,0,0};
        Assert.assertEquals(0, Main.counter(arr));
    }
    @Test
    public void checkFour(){
        byte[] arr = new byte[] {1,1,1,1,1,1,1,1,1,1}; //10 единиц
        Assert.assertEquals(0, Main.counter(arr));
    }

    @Test
    public void checkFive(){
        byte[] arr = new byte[] {1,1,1,1,1,1,1,1,1,1,1}; //11 единиц
        Assert.assertEquals(1, Main.counter(arr));
    }
}
