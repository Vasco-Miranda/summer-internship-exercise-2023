package com.premiumminds.internship.snail;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

/**
 * Created by aamado on 05-05-2023.
 */
class SnailShellPattern implements ISnailShellPattern {

  /**
   * Method to get snailshell pattern
   * 
   * @param matrix matrix of numbers to go through
   * @return order array of values thar represent a snail shell pattern
   */
  public Future<int[]> getSnailShell(int[][] matrix) {
    // Se a matriz estiver vazia, devolve um array vazio
    if (matrix.length == 0 || matrix[0].length == 0) {
      return CompletableFuture.completedFuture(new int[0]);
    }

    // Lança uma exceção no caso da matriz dada não ser quadrada
    if (matrix.length != matrix[0].length) {
      throw new RuntimeException("Matrix não é quadrada");
    }

    Integer[] resultRaw = getSnailShellAux(matrix).toArray(new Integer[0]);
    int[] result = toInt(resultRaw);
    return CompletableFuture.completedFuture(result);
    
  }

  private List<Integer> getSnailShellAux(int[][] matrix) {
    // if (matrix.length == 0) {
    //   return new ArrayList<>();
    // } else 
    if (matrix.length == 1) {
      List<Integer> ret = new ArrayList<>();
      ret.add(matrix[0][0]);
      return ret;
    }
    List<Integer> result = new ArrayList<>();

    // Adiciona as primeiras linhas e colunas da parte de fora da matriz
    result.addAll(getLine(matrix, Direction.LEFT));
    result.addAll(getLine(matrix, Direction.DOWN));
    result.addAll(getLine(matrix, Direction.RIGHT));
    result.addAll(getLine(matrix, Direction.UP));

    // Adiciona recursivamente as linhas e colunas do centro da matriz
    int[][] center = getCenter(matrix);
    if (center.length == 0) {
      return result;
    }
    result.addAll(getSnailShellAux(center));
    return result;
  }

  private Collection<Integer> getLine(int[][] matrix, Direction dir) {
    List<Integer> ret = new ArrayList<>();
    int n = matrix.length;

    switch (dir) {
      case LEFT:
        for (int i = 0; i < n; i++) {
          ret.add(matrix[0][i]);
        }
        break;
      
      case DOWN:
        for (int i = 1; i < n; i++) {
          ret.add(matrix[i][n - 1]);
        }
        break;
      
      case RIGHT:
        for (int i = n - 2; i >= 0; i--) {
          ret.add(matrix[n - 1][i]);
        }
        break;
      
      default:
        for (int i = n - 2; i > 0; i--) {
          ret.add(matrix[i][0]);
        }
        break;
    }
    return ret;
  }

  private int[][] getCenter(int[][] matrix) {
    if (matrix.length == 2) {
      return new int[0][0];
    } else if (matrix.length == 3) {
      int[][] ret = new int[1][1];
      ret[0][0] = matrix[1][1];
      return ret;
    }
    int n = matrix.length - 2;
    int[][] ret = new int[n][n];

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        ret[i][j] = matrix[i + 1][j + 1];
      }
    }
    return ret;
  }

  private int[] toInt(Integer[] xs) {
    int[] ret = new int[xs.length];
    int i = 0;

    for (Integer integer : xs) {
      ret[i] = integer;
      i++;
    }
    return ret;
  }

  private enum Direction {
    UP, DOWN, LEFT, RIGHT;
  }
}
