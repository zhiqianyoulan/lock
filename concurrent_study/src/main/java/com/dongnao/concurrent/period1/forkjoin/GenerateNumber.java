package com.dongnao.concurrent.period1.forkjoin;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class GenerateNumber {

	public static void main(String[] args) throws IOException {

		Random random = new Random();

		try (PrintWriter out = new PrintWriter(new File("d:/test/data1.txt"));) {
			for (int i = 0; i < 1_000_000; i++) {

				out.println(random.nextInt());

				if (i % 10000 == 0)
					out.flush();
			}
		}
	}

}
