package project.entities.materials;

import java.util.Comparator;

public class PipeComparatorByQuantityInStockPlus implements Comparator<Pipe> {

	@Override
	public int compare(Pipe o1, Pipe o2) {
		if(o1.getQuantityInStock() < o2.getQuantityInStock()) return 1;
		if(o1.getQuantityInStock() > o2.getQuantityInStock()) return -1;
		return 0;
	}

}
