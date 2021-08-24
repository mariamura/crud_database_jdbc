import DButils.DBUtil;
import view.ConsoleStarter;

public class StartProgram {
	public static void main(String[] args) throws Exception {
		DBUtil.createTable("developer");
	}
}
