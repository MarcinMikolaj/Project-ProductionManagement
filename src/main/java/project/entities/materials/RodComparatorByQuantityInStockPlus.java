package project.entities.materials;

import java.util.Comparator;

public class RodComparatorByQuantityInStockPlus implements Comparator<Rod> {

	@Override
	public int compare(Rod o1, Rod o2) {
		if(o1.getQuantityInStock() < o2.getQuantityInStock()) return 1;
		if(o1.getQuantityInStock() > o2.getQuantityInStock()) return -1;
		return 0;
	}

}
