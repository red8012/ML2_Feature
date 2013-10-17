import java.util.ArrayList;
import java.util.HashMap;

public class DataSet {
	HashMap<String, ArrayList> map = new HashMap<String, ArrayList>();

	void addNewColumn(String columnName, ArrayList emptyList) {
		map.put(columnName, emptyList);
	}

	void set(String column, int index, String value) {
		map.get(column).set(index, value);
	}

	void set(String column, int index, Integer value) {
		map.get(column).set(index, value);
	}

	void set(String column, int index, Float value) {
		map.get(column).set(index, value);
	}

	void add(String column, Object value) {
		map.get(column).add(value);
	}

//	void add(String column, String value){
//		map.get(column).add(value);
//	}
//
//	void add(String column, Integer value){
//		map.get(column).add(value);
//	}
//
//	void add(String column, Float value){
//		map.get(column).add(value);
//	}

	String getStr(String column, int index) {
		return ((ArrayList<String>) map.get(column)).get(index);
	}

	Integer getInt(String column, int index) {
		return ((ArrayList<Integer>) map.get(column)).get(index);
	}

	Float getFlo(String column, int index) {
		return ((ArrayList<Float>) map.get(column)).get(index);
	}

	float getFloChecked(String column, int index) throws NullPointerException {
		ArrayList a = map.get(column);
		if (a == null) throw new NullPointerException("ArrayList is null");
		if (a.isEmpty()) throw new NullPointerException("ArrayList is empty");
		ArrayList<Float> af = a;
		Float f;
		try {
			f= af.get(index);
		} catch (Exception e) {
			throw new NullPointerException("Array out of bound");
		}
		if (f == null) throw new NullPointerException("Item is null");
		if (f == Float.NaN) throw new NullPointerException("Item is NaN");
		return f;
	}

	ArrayList get(String column) {
		return map.get(column);
	}

	int getLength() {
		return map.get("date").size();
	}
}
