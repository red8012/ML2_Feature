import java.util.ArrayList;

public class TechnicalModule<T> {
	final String NAME;
	DataSet D;

	public TechnicalModule(String name) {
		NAME = name;
	}

	public ArrayList<T> getEmptyArrayList() {
		return new ArrayList<T>();
	}

	T calculate(int row) throws Exception{
		if (D == null) throw new Exception("DataSet D is null");
		return null;
	}
}
