import model.Developer;
import repository.jdbcImpl.DeveloperRepositoryImpl;

public class StartProgram {
	public static void main(String[] args) throws Exception {
		DeveloperRepositoryImpl developerRepository = new DeveloperRepositoryImpl();
		System.out.println(developerRepository.save(new Developer("Karl", "Jones")));

	}
}
