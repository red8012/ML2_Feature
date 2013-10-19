import org.apache.commons.math3.stat.descriptive.moment.Mean;

public class ZMovingAverage extends TechnicalModule<Float> {
	final static int HIGH = 0, LOW = 1, MID = 2, ALL = 3;
	final static String NAME_POSTFIX[] = {"H", "L", "M", "A"};
	final static int[] LENGTH = {1, 1, 2, 4};
	final int period, type;

	public ZMovingAverage(int type, int period) {
		super("ma" + NAME_POSTFIX[type] + String.valueOf(period));
		this.period = period;
		this.type = type;
	}

	@Override
	public Float calculate(int row) {
		double[] dou = new double[period * LENGTH[type]];
		int x = 0;
		try {
			for (int i = row - period + 1; i <= row; i++) {
				if (type == ALL) dou[x++] = D.getFloChecked("open", i);
				if (type != LOW) dou[x++] = D.getFloChecked("high", i);
				if (type != HIGH) dou[x++] = D.getFloChecked("low", i);
				if (type == ALL) dou[x++] = D.getFloChecked("close", i);
			}
		} catch (NullPointerException e) {
			return null;
		}
		Mean mean = new Mean();
		return new Float(mean.evaluate(dou));
	}
}
