package project.test.function;

import project.repositories.SuperRepository;
import java.util.List;

public interface MaterialProviderRepository extends SuperRepository<MaterialProvider> {
	
	public MaterialProvider findByCompanyName(String companyName);
	public MaterialProvider findByNip(String nip);
	public List<MaterialProvider> findAll();
	public MaterialProvider removeByNip(String nip);
	public boolean existByNip(String nip);

}
