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
			batchAddIndicator(
					new ZStandardDeviation(5),
					new ZStandardDeviation(20),

					new ZMovingAverage(ZMovingAverage.HIGH, 5),
					new ZMovingAverage(ZMovingAverage.LOW, 5),
					new ZMovingAverage(ZMovingAverage.MID, 5),
					new ZMovingAverage(ZMovingAverage.ALL, 5),

					new ZMovingAverage(ZMovingAverage.HIGH, 20),
					new ZMovingAverage(ZMovingAverage.LOW, 20),
					new ZMovingAverage(ZMovingAverage.MID, 20),
					new ZMovingAverage(ZMovingAverage.ALL, 20),

					new ZMovingAverage(ZMovingAverage.ALL, 3),
					new ZMovingAverage(ZMovingAverage.MID, 8),
					new ZMovingAverage(ZMovingAverage.MID, 13),
					new ZMovingAverage(ZMovingAverage.MID, 34),

					new ZAlligator(ZAlligator.A5),
					new ZAlligator(ZAlligator.A8),
					new ZAlligator(ZAlligator.A13),
					new ZAlligator(ZAlligator.gatorShort),
					new ZAlligator(ZAlligator.gatorLong),
					new ZAlligator(ZAlligator.gatorStdev),
					new ZAwesomeOscillator(),
					new ZAcceleration(),
					new ZBWMFI(),

					new Diff("alligatorShort", 1),
					new Diff("alligatorLong", 1),
					new Diff("alligatorStdev", 1),
					new Diff("bwmfi", 1),
					new Diff("volume", 1),

					new ZBollingerBandPercentage(ZBollingerBandPercentage.MID),
					new ZBollingerBandPercentage(ZBollingerBandPercentage.CLOSE),

					new ZFractal(ZFractal.UP),
					new ZFractal(ZFractal.PERFECT_UP),
					new ZFractal(ZFractal.DOWN),
					new ZFractal(ZFractal.PERFECT_DOWN));

			for (int i = 0; i < d.getLength(); i++) {
				try {
					System.out.println(
						//						d.getStr("date", i) + "\t" +
						//						d.getFlo("volume", i) + "\t" +
						d.getFlo("open", i) + "\t" +
								d.getFlo("high", i) + "\t" +
								d.getFlo("low", i) + "\t" +
								d.getFlo("close", i) + "\t" +
								d.getFlo("volume", i) + "\t" +
								d.getFlo("Dbwmfi1", i) + "\t"+
								d.getFlo("Dvolume1", i) + "\t"
//								d.getFlo("alligator5", i) + "\t" +
//								d.getFlo("alligator8", i) + "\t" +
//								d.getFlo("alligator13", i) + "\t"+
//								d.getFlo("alligatorShort", i) + "\t"+
//								d.getFlo("alligatorLong", i) + "\t"+
//								d.getFlo("alligatorStdev", i) + "\t"+
//								d.getFlo("DalligatorShort1", i) + "\t"+
//								d.getFlo("DalligatorLong1", i) + "\t"+
//								d.getFlo("DalligatorStdev1", i) + "\t"

//									String.valueOf(d.getFlo("maA20", i) + 2.0 * d.getFlo("std20", i)) + "\t"+
//									String.valueOf(d.getFlo("maA20", i) - 2.0 * d.getFlo("std20", i)) + "\t"+
//									d.getFlo("bollM", i)
//							d.getFlo("fractalU", i) +"\t"+
//							d.getFlo("fractalPU", i) +"\t"+
//							d.getFlo("fractalD", i) +"\t"+
//									d.getFlo("ao", i) + "\t"+
//								d.getFlo("ac", i) + "\t"
					);
				} catch (Exception e) {
				}
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

	void batchAddIndicator(TechnicalModule... t) throws Exception {
		for (TechnicalModule module : t)
			addTechnicalIndicator(module);
	}

	void addTechnicalIndicator(TechnicalModule t) throws Exception {
		t.D = d;
		d.addNewColumn(t.NAME, t.getEmptyArrayList());
		for (int i = 0; i < d.getLength(); i++)
			d.add(t.NAME, t.calculate(i));
	}
}
