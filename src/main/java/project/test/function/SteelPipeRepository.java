package project.test.function;

import project.repositories.SuperRepository;

public interface SteelPipeRepository extends SuperRepository<SteelPipe> {
	
	public SteelPipe findById(Long id);

}
