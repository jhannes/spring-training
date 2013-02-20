package com.exilesoft.exercise;

import java.util.Random;

public class RandomData {

	private static Random random = new Random();

	public static String randomWord() {
		return random("foo", "bar", "baz", "quux", "quuuuux", "gazzle", "hello", "world", "nice", "evening");
	}

	@SafeVarargs
	public static<T> T random(T... options) {
		return options[random(options.length)];
	}

	private static int random(int length) {
		return random.nextInt(length);
	}

	public static String randomUrl() {
		return "http://www." + randomWord() + ".com/" + randomWord();
	}

}
