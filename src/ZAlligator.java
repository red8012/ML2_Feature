import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;

public class ZAlligator extends TechnicalModule {
	final static int A5 = 0, A8 = 1, A13 = 2;
	final static int gatorShort = 3, gatorLong = 4, gatorStdev = 5;
	final static String[] NAME = {"5", "8", "13", "Short", "Long", "Stdev"};
	final int type;

	public ZAlligator(int type) {
		super("alligator" + NAME[type]);
		this.type = type;
	}

	@Override
	public Float calculate(int row) {
		try {
			if (type == A5) return D.getFloChecked("maM5", row - 3);
			else if (type == A8) return D.getFloChecked("maM8", row - 5);
			else if (type == A13) return D.getFloChecked("maM13", row - 8);
			else if (type == gatorShort)
				return D.getFloChecked("alligator5", row) - D.getFloChecked("alligator8", row);
			else if (type == gatorLong)
				return D.getFloChecked("alligator8", row) - D.getFloChecked("alligator13", row);
			else if (type == gatorStdev) {
				double[] dou = new double[3];
				dou[0] = D.getFloChecked("alligator5", row);
				dou[1] = D.getFloChecked("alligator8", row);
				dou[2] = D.getFloChecked("alligator13", row);
				StandardDeviation std = new StandardDeviation(false);
				return new Float(std.evaluate(dou));
			}
		} catch (NullPointerException e) {
			return null;
		}
		return null;
	}
}
