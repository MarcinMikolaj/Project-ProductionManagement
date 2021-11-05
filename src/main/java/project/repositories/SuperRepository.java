package project.repositories;

public interface SuperRepository<G> {
	
	public void save(G object);
	public void update(G object);
	public void createUpdateOrDeleteQuery(String query);

}
