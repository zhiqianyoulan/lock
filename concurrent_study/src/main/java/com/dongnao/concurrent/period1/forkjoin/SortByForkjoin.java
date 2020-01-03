package com.dongnao.concurrent.period1.forkjoin;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class SortByForkjoin {

	public static void main(String[] args) throws Exception {

		// forkjoin线程池
		ForkJoinPool pool = new ForkJoinPool();

		// 1 从文件中读取内存容量可处理的数据量 // 拆分为多个部分排序的文件
		int size = 100_000;		//每100_000 条数据，放到一个文件中
		int[] array = new int[size];
		BufferedReader reader = new BufferedReader(new FileReader("d:/test/data1.txt"));
		String line = null;
		int i = 0;
		int partition = 0;
		// 拆分的部分文件名list，后面归并部分文件需要用到它
		List<String> partFiles = new ArrayList<>();

		//每size条数据，排序后，放到一个文件
		while ((line = reader.readLine()) != null) {
			array[i++] = Integer.parseInt(line);

			if (i == size) {
				// 重置i为0
				i = 0;
				// 排序输出到部分文件
				String filename = "d:/test/data1-part-" + partition++ + ".txt";
				//对当前partition的数据进行排序
				doPartitionSort(pool, filename, array, 0, size);
				partFiles.add(filename);
			}
		}

		reader.close();

		// 未达到size的部分数据，排序后放一个文件
		if (i > 0) {
			// 排序输出到部分文件
			String filename = "d:/test/data1-part-" + partition++ + ".txt";
			//对当前partition的数据进行排序
			doPartitionSort(pool, filename, array, 0, i);
			partFiles.add(filename);
		}

		// 2 归并排序后的partition
		if (partition > 1) {
			// 归并排序
			MergerFileSortTask mtask = new MergerFileSortTask(partFiles, "d:/test/data1-sort");
			pool.submit(mtask);
			mtask.get();

		} else {
			// 将唯一的一个部分文件重命名为最终结果文件名
		}

		pool.shutdown();
	}

	static void doPartitionSort(ForkJoinPool pool, String filename, int[] array, int start, int end) throws Exception {
		ArrayMergerSortTask task = new ArrayMergerSortTask(array, start, end);
		pool.submit(task);
		task.get();

		// 输出到文件
		try (PrintWriter pw = new PrintWriter(filename);) {
			for (int i = start; i < end; i++) {
				pw.println(array[i]);
			}
		}
	}
}
