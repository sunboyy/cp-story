package utility;

import java.util.ArrayList;
import java.util.List;

import javafx.util.Pair;

public class Random {

	public static int randInt(int n) {
		return (int) Math.floor(Math.random()*n);
	}
	
	public static <T> Pair<T,Double> weightedRandomInList(List<Pair<T,Double>> list) throws ListEmptyException, NegativeWeightedRandomException {
		if (list.size() == 0) throw new ListEmptyException();
		double sum = 0;
		for (Pair<T,Double> i: list) {
			if (i.getValue() < 0) throw new NegativeWeightedRandomException();
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
	
	public static <T> List<T> multipleRandomInList(List<Pair<T,Double>> list) throws NegativeWeightedRandomException {
		List<T> out = new ArrayList<>();
		for (Pair<T,Double> i: list) {
			double rand = Math.random();
			if (rand <= i.getValue()) {
				out.add(i.getKey());
			}
		}
		return out;
	}
	
}
