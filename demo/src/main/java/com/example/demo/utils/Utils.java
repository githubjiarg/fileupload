package com.example.demo.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Utils {

    public static void main(String[] args) {
        getRowNumber();
    }

    /**
     *  获取指定列数据
     */
    private static void getRowNumber(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入一个整数 n（1 <= n < 1024）：");
        // 字符串数字元素数量
        int input = scanner.nextInt();
        System.out.print("请输入一个整数 k （n起始行的后k行）：");
        // n行中后k行下标计算
        int stepIndex = 0;
        int stepNum = scanner.nextInt();
        List<Integer> bodyList = new ArrayList<Integer>();
        List<Integer> headList = new ArrayList<Integer>();
        List<Integer> numberList = new ArrayList<Integer>();
        // 元素数组信息
        for ( int k = 1; k <= input; k++ ) {
            bodyList.add(k);
            headList.add(k);
        }
        int headIndex = 0;
        while ( true ) {
            if ( headIndex == headList.size() ) {
                break;
            }
            numberList.clear();
            numberList.add(headList.get(headIndex));
            bodyList = gitCircleList(headList.get(headIndex),input);
            // 获取n数字起始的行下标
            if ( headList.get(headIndex) == input ) {
                stepIndex = 1;
                if ( stepIndex == stepNum ) {
                    System.out.println("N值起始行K行后输入:");
                    printArray(bodyList);
                }
            }
            // 输出数组
             printArray(bodyList);
            while ( true ) {

                // 遍历行中每一个元素
                for ( int i = 1; i < bodyList.size(); i++) {
                    int colNum = bodyList.get(i);
                    while ( true ) {
                        colNum = addNum(colNum,input,1);
                        if (!numberList.contains(colNum)) {
                            break;
                        }
                    }
                    numberList.add(colNum);
                }
                //  元素数量满足时输出
                if ( numberList.size() == input ) {
                    stepIndex++;
                    bodyList = new ArrayList<>(numberList);
                    // 输出数组
                    printArray(numberList);
                    // 判断是否到达n起始k行后的数据
                    if ( stepIndex == stepNum ) {
                        System.out.println("N值起始行K行后输入:");
                        printArray(bodyList);
                    }
                    numberList.clear();
                    numberList.add(bodyList.get(0));
                }
                // 当第二元素等于第一个元素时，第一元素加1
                if ( addNum(bodyList.get(1),input,1) == headList.get(headIndex)) {
                    headIndex = headIndex + 1;
                    break;
                }
            }
        }
    }

    /**
     *  输出数组信息
     * @param list
     * @return
     */
    private static void printArray(List<Integer> list){
        String str = list.toString().replaceAll(","," ");
        str = str.substring(str.indexOf("[") + 1,str.indexOf("]"));
        System.out.println("输出：" + str);
    }

    /**
     *  货物循环集合
     * @return
     */
    private static List<Integer> gitCircleList(int start, int length){
        List <Integer> list = new ArrayList<>();
        list.add(start);
        while ( true ) {
            start = addNum(start,length,1);
            list.add(start);
            if ( list.size() == length ) {
                break;
            }
        }
        return list;
    }

    /**
     *  获取数字自增后的值
     * @param num
     * @param max
     * @return
     */
    private static int addNum(int num,int max,int min){
        if ( num == max ) {
            return min;
        }
        return num + 1;
    }


}
