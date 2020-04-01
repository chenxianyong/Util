package com.cxy.util;

/**
 * @author: ChenXianyong
 * @description: 算法
 * @date: 2019/12/4 15:01
 */
public class AlgorithmUtils {


  public static void main(String[] args) {
//    //二分查找法
//    int array[] = {0,1,2,3,4,5,6};
//    int value = 11;
//
//
//    int result = binarySearch(array, value);
//    System.out.println(result);
//
//    int searchEnd = binarySearchEnd(array, value);
//    System.out.println(searchEnd);

//    //快速排序算法测试
//    int array[] = {1,5,8,9,6,3,4,5,9,11,56,2131,58,15,51832,18,3,21,8,365};
//    System.out.println(System.currentTimeMillis() + "排序前：" + Arrays.toString(array));
//    quickSort(array, 0, array.length - 1);
//    System.out.println(System.currentTimeMillis() + "排序后：" + Arrays.toString(array));

//    //堆排序测试
//    int array[] = {1,5,8,9,6,3,4,5,9,11,56,2131,58,15,51832,18,3,21,8,365};
//    System.out.println(System.currentTimeMillis() + "排序前：" + Arrays.toString(array));
//    headSort(array);
//    System.out.println(System.currentTimeMillis() + "排序后：" + Arrays.toString(array));

//    //归并算法测试
//    int array[] = {1,5,8,9,6,3,4,5,11,56,2131,58,15,51832,18,3,21,8,365};
//    int tmp[] = new int[array.length];
//    System.out.println("归并前：" + Arrays.toString(array));
//    mergeSort(array, 0, array.length - 1, tmp);
//    System.out.println("归并后：" + Arrays.toString(array));

    int [] arr = {1,3,4,5,2,5,1,8,9,15,58,5146,541,65,5156,5};
    //常规方式
    System.out.println(find(arr,5146));
    //递归实现
    System.out.println(recursiveFind(arr,0,arr.length-1,5146));


  }

  /** ================================================================================
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

  /** ================================================================================
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

  /** ================================================================================
   * @author: ChenXianyong
   * @description: 快速排序算法
   * @date: 2019/12/5 14:35
   * @param: [array，要排序的数组； left：数组第一个指针, right：数组最后一个指针]
   */
  private static void quickSort(int array[], int left, int right) {
    int pivot = 0;
    if (left <right) {
      pivot = partition(array, left, right);
      quickSort(array, left, pivot - 1);
      quickSort(array, pivot + 1, right);
    }
  }

  /**
   * @author: ChenXianyong
   * @description: 快速排序算法
   * @date: 2019/12/5 15:06
   * @param: [array，要排序的数组； left：数组第一个指针, right：数组最后一个指针]
   * @return: int
   */
  private static int partition(int array[], int left, int right) {
    int key = array[left];
    while (left < right) {
      while (left < right && array[right] >= key) {
        right--;
      }
      array[left] = array[right];
      while (left < right && array[left] <= key) {
        left++;
      }
      array[right] = array[left];
    }
    array[left] = key;
    return left;
  }

  /** ================================================================================
   * @author: ChenXianyong
   * @description: 堆排序算法
   * @date: 2019/12/5 15:16
   * @param: [arr，要排序的数组]
   */
  private static void headSort(int arr[]) {
    int size = arr.length - 1;
    for (int i = 0; i < size; i++) {
      buildHeap(arr, size - i);
      swap(arr, 0, size - i);
    }
  }

  /**
   * @author: ChenXianyong
   * @description: 堆排序算法
   * @date: 2019/12/5 15:16
   * @param: [arr，要排序的数组]
   */
  private static void buildHeap(int arr[], int lastIndex) {
    for (int i = (lastIndex - 1) / 2; i >= 0; i--) {
      int k = i;
      while (2 * k + 1 <= lastIndex) {
        int biggerIndex = 2 * k + 1;

        if (biggerIndex < lastIndex) {
          if (arr[biggerIndex] < arr[biggerIndex + 1]) {
            biggerIndex++;
          }
        }

        if (arr[k] < arr[biggerIndex]) {
          swap(arr, k, biggerIndex);
          k = biggerIndex;
        } else {
          break;
        }
      }
    }
  }

  /**
   * @author: ChenXianyong
   * @description: 堆排序算法
   * @date: 2019/12/5 15:16
   * @param: [arr，要排序的数组]
   */
  private static void swap(int arr[], int i, int j) {
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }

  /** ================================================================================================
   * @author: ChenXianyong
   * @description: 归并排序
   * @date: 2019/12/5 16:46
   * @param: [arr, low, high, tmp]
   */
  private static void mergeSort(int arr[], int low, int high, int tmp[]) {
    if (low < high) {
      int mid = (low + high) / 2;
      mergeSort(arr, low, mid, tmp);
      mergeSort(arr, mid + 1, high, tmp);
      merge(arr, low, mid, high, tmp);
    }
  }

  /**
   * @author: ChenXianyong
   * @description: 归并排序
   * @date: 2019/12/5 16:46
   * @param: [arr, low, high, tmp]
   */
  private static void merge(int arr[], int low, int mid, int high, int tmp[]) {
    int i = 0;
    int j = low;
    int k = mid+1;
    while (j <= mid && k <= high) {
      if (arr[j] < arr[k]) {
        tmp[i++] = arr[j++];
      } else {
        tmp[i++] = arr[k++];
      }
    }

    while (j <= mid) {
      tmp[i++] = arr[j++];
    }

    while (k <= high) {
      tmp[i++] = arr[k++];
    }

    for (int t = 0; t < i; t++) {
      arr[low + t] = tmp[t];
    }





  }


  /*
   * 二分查找，返回索引
   */
  private static int find(int [] arr,int searchKey){
    int lowerBound = 0;
    int upperBound = arr.length -1;
    int curIn;
    while(lowerBound <= upperBound){
      curIn = (lowerBound + upperBound) / 2;
      if(arr[curIn] == searchKey){
        return curIn;
      }else{
        if(arr[curIn] < searchKey){
          lowerBound = curIn + 1;
        }else{
          upperBound = curIn - 1;
        }
      }
    }
    return -1;
  }
  /*
   * 二分查找，返回索引，递归实现
   */
  private static int recursiveFind(int[] arr,int start,int end,int searchKey){
    if (start <= end) {
      int middle =  (start+end)/2;
      if (searchKey == arr[middle]) {
        return middle;
      } else if (searchKey < arr[middle]) {
        return recursiveFind(arr, start, middle - 1, searchKey);
      } else {
        return recursiveFind(arr, middle + 1, end, searchKey);
      }
    } else {
      return -1;
    }
  }


}
