package com.cxy.util;

/**
 * @author: ChenXianyong
 * @description: 算法
 * @date: 2019/12/4 15:01
 */
public class AlgorithmController {


  public static void main(String[] args) {
    int array[] = {0,1,2,3,4,5,6};
    int value = 11;


    int result = binarySearch(array, value);
    System.out.println(result);

    int searchEnd = binarySearchEnd(array, value);
    System.out.println(searchEnd);
  }

  /**
   * @author: ChenXianyong
   * @description: 第一个出现的位置
   * @date: 2019/12/4 15:10
   * @param: [array，由小到大排好序的, value 要查找的]
   * @return: int 位置，没有返回-1
   */
  private static int binarySearch(int array[], int value) {
    int i = 0;
    int length = array.length-1;
    while (i<length) {
      int mid = (i+length)>>1;
      if (array[mid] <value) {
        i = mid + 1;
      } else {
        length = mid;
      }
    }
    return array[i] == value ? i : -1;
  }

  /**
   * @author: ChenXianyong
   * @description: 最后一个出现的位置
   * @date: 2019/12/4 15:10
   * @param: [array，由小到大排好序的, value 要查找的]
   * @return: int 位置，没有返回-1
   */
  private static int binarySearchEnd(int array[], int value) {
    int i = 0;
    int length = array.length-1;
    while (i<length) {
      int mid = (i+length + 1)>>1;
      if (array[mid] <=value) {
        i = mid;
      } else {
        length = mid - 1;
      }
    }
    return array[i] == value ? i : -1;
  }
}
