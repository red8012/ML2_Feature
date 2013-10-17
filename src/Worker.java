import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Worker implements Runnable {
	String securityCode;
	DataSet d = new DataSet();

	public Worker(int securityCode) {
		this.securityCode = String.valueOf(securityCode);
	}

	@Override
	public void run() {
		try {
			readInputData();
			addTechnicalIndicator(new ZStandardDeviation(5));
			addTechnicalIndicator(new ZStandardDeviation(20));
			for (int i = 0; i < d.getLength(); i++) {
				System.out.println(
//						d.getStr("date", i) + "\t" +
//						d.getFlo("volume", i) + "\t" +
						d.getFlo("open", i) + "\t" +
								d.getFlo("high", i) + "\t" +
								d.getFlo("low", i) + "\t" +
								d.getFlo("close", i) + "\t"
//								d.getFlo("standardDeviation5", i) + "\t" +
//								d.getFlo("standardDeviation20", i)
				);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	void readInputData() throws Exception {
		BufferedReader reader = new BufferedReader(new FileReader("input/" + securityCode + ".csv"));
		String line = reader.readLine();
		d.addNewColumn("date", new ArrayList<String>());
		d.addNewColumn("volume", new ArrayList<Float>());
		d.addNewColumn("open", new ArrayList<Float>());
		d.addNewColumn("high", new ArrayList<Float>());
		d.addNewColumn("low", new ArrayList<Float>());
		d.addNewColumn("close", new ArrayList<Float>());

		while ((line = reader.readLine()) != null) {
			String[] sp = line.split(",");
			d.add("date", sp[0]);
			d.add("volume", new Float(sp[1]));
			d.add("open", new Float(sp[2]));
			d.add("high", new Float(sp[3]));
			d.add("low", new Float(sp[4]));
			d.add("close", new Float(sp[5]));
		}
		reader.close();
	}

	void addTechnicalIndicator(TechnicalModule t) throws Exception {
		t.D = d;
		d.addNewColumn(t.NAME, t.getEmptyArrayList());
		for (int i = 0; i < d.getLength(); i++)
			d.add(t.NAME, t.calculate(i));
	}
}
