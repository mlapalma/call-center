package com.mlapalma.util;

import java.util.Random;

public class RandomMilisecondsGenerator {

	public static int getRandomNumber(int min, int max) {

		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}

		Random random = new Random();
		return random.nextInt((max - min) + 1) + min;
	}

}
