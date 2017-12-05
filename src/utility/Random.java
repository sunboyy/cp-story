package utility;

import java.util.List;

import javafx.util.Pair;

public class Random {

	public static <T> Pair<T,Double> weightedRandomInList(List<Pair<T,Double>> list) {
		double sum = 0;
		for (Pair<T,Double> i: list) {
			sum += i.getValue();
		}
		double rand = sum * Math.random();
		for (Pair<T,Double> i: list) {
			if (rand <= i.getValue()) {
				return new Pair<T, Double>(i.getKey(), rand);
			}
			rand -= i.getValue();
		}
		return null;
	}
	
}
