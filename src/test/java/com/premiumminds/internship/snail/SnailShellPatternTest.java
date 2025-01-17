package com.premiumminds.internship.snail;

import static org.junit.Assert.*;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Created by aamado on 05-05-2023.
 */
@RunWith(JUnit4.class)
public class SnailShellPatternTest {

  /**
   * The corresponding implementations to test.
   *
   * If you want, you can make others :)
   *
   */
  public SnailShellPatternTest() {
  };

  // Função auxiliar para comparar arrays, pois o assertEquals estava a comparar endereços de memória no VSCode
  private boolean arrayEquals(int[] expected, int[] result) {
    if (expected.length != result.length) {
      return false;
    }

    boolean ret = true;
    for (int i = 0; i < result.length; i++) {
      if (expected[i] != result[i]) {
        ret = false;
      }
    }
    return ret;
  }

  @Test
  public void ScreenLockinPatternTestFirst3Length2Test()
      throws InterruptedException, ExecutionException, TimeoutException {
    int[][] matrix = { { 1, 2, 3 }, { 8, 9, 4 }, { 7, 6, 5 } };
    Future<int[]> count = new SnailShellPattern().getSnailShell(matrix);
    int[] result = count.get(10, TimeUnit.SECONDS);
    int[] expected = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
    //assertEquals(result, expected);
    boolean equals = arrayEquals(expected, result);
    assertTrue(equals);
  }

  @Test
  public void SnailShellPatternEmptyTest()
      throws InterruptedException, ExecutionException, TimeoutException {
    int[][] matrix = { { } };
    Future<int[]> count = new SnailShellPattern().getSnailShell(matrix);
    int[] result = count.get(10, TimeUnit.SECONDS);
    int[] expected = {};
    //assertEquals(result, expected);
    boolean equals = arrayEquals(expected, result);
    assertTrue(equals);
  }

  @Test
  public void SnailShellPattern2By2SquareTest()
      throws InterruptedException, ExecutionException, TimeoutException {
    int[][] matrix = { { 1, 2 }, { 4, 3 } };
    Future<int[]> count = new SnailShellPattern().getSnailShell(matrix);
    int[] result = count.get(10, TimeUnit.SECONDS);
    int[] expected = { 1, 2, 3, 4 };
    //assertEquals(result, expected);
    boolean equals = arrayEquals(expected, result);
    assertTrue(equals);
  }

  @Test
  public void SnailShellPattern5By5SquareTest()
      throws InterruptedException, ExecutionException, TimeoutException {
    int[][] matrix = { { 1, 2, 3, 4, 5 }, { 16, 17, 18, 19, 6 }, { 15, 24, 25, 20, 7 }, { 14, 23, 22, 21, 8 }, { 13, 12, 11, 10, 9 } };
    Future<int[]> count = new SnailShellPattern().getSnailShell(matrix);
    int[] result = count.get(10, TimeUnit.SECONDS);
    int[] expected = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25 };
    //assertEquals(result, expected);
    boolean equals = arrayEquals(expected, result);
    assertTrue(equals);
  }

  @Test
  public void SnailShellPatternSingletonSquareTest()
      throws InterruptedException, ExecutionException, TimeoutException {
    int[][] matrix = { { 1 } };
    Future<int[]> count = new SnailShellPattern().getSnailShell(matrix);
    int[] result = count.get(10, TimeUnit.SECONDS);
    int[] expected = { 1 };
    //assertEquals(result, expected);
    boolean equals = arrayEquals(expected, result);
    assertTrue(equals);
  }
  
}