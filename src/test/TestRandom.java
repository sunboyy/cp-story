package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import javafx.util.Pair;
import utility.ListEmptyException;
import utility.NegativeWeightedRandomException;
import utility.Random;

public class TestRandom {

	@Test
	public void testWeightedRandomInList() {
		List<Pair<String, Double>> list = new ArrayList<>();
		list.add(new Pair<String, Double>("A", 1.2));
		list.add(new Pair<String, Double>("B", 0.3));
		list.add(new Pair<String, Double>("C", 1.0));
		list.add(new Pair<String, Double>("D", 0.6));
		
		for (int i=0;i<1000;i++) {
			Pair<String, Double> p;
			try {
				p = Random.weightedRandomInList(list);
				assertNotNull(p);
				if (p.getKey().equals("A")) {
					assertTrue(p.getValue() < 1.2);
				}
				if (p.getKey().equals("B")) {
					assertTrue(p.getValue() < 0.3);
				}
				if (p.getKey().equals("C")) {
					assertTrue(p.getValue() < 1.0);
				}
				if (p.getKey().equals("D")) {
					assertTrue(p.getValue() < 0.6);
				}
			} catch (ListEmptyException e) {
				e.printStackTrace();
			} catch (NegativeWeightedRandomException e) {}
			
		}
	}

}
