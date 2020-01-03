package com.dongnao.concurrent.period1.forkjoin;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class ArrayMergerSortTask extends RecursiveAction {

	// implementation details follow:
	static final int THRESHOLD = 1000;

	final int[] array;
	final int lo, hi;

	ArrayMergerSortTask(int[] array, int lo, int hi) {
		this.array = array;
		this.lo = lo;
		this.hi = hi;
	}

	ArrayMergerSortTask(int[] array) {
		this(array, 0, array.length);
	}

	protected void compute() {
		if (hi - lo < THRESHOLD)		//小于1000，就排序
			sortSequentially(lo, hi);
		else {
			int mid = (lo + hi) >>> 1;		//大于1000，拆分
			invokeAll(new ArrayMergerSortTask(array, lo, mid),
					new ArrayMergerSortTask(array, mid, hi));
			merge(lo, mid, hi);
		}
	}

	void sortSequentially(int lo, int hi) {
		Arrays.sort(array, lo, hi);		//利用JDK自带的排序进行
	}

	void merge(int lo, int mid, int hi) {
		int[] buf = Arrays.copyOfRange(array, lo, mid);
		for (int i = 0, j = lo, k = mid; i < buf.length; j++)
			array[j] = (k == hi || buf[i] < array[k]) ? buf[i++] : array[k++];
	}

	public static void main(String[] args) throws Exception {
		// 这里以一个长度为2千的数组做示例
		int length = 2_000;
		int[] array = new int[length];
		// 填充数值
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			array[i] = random.nextInt();
			System.out.println(array[i]);
		}

		// 利用forkjoinpool来完成多线程快速归并排序
		ArrayMergerSortTask stask = new ArrayMergerSortTask(array);
		ForkJoinPool pool = new ForkJoinPool();
		pool.submit(stask);
		// 等待任务完成
		stask.get();

		System.out.println("----------排序后的结果:");
		for (int d : array) {
			System.out.println(d);
		}
	}
}
