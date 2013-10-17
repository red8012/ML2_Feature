import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;

public class ZStandardDeviation extends TechnicalModule<Float> {
	int length;

	public ZStandardDeviation(int length) {
		super("standardDeviation"+String.valueOf(length));
		this.length = length;
	}

	@Override
	public Float calculate(int row) {
		double[] dou = new double[length * 4];
		int x=0;
		try {
			for (int i = row - length + 1; i <= row; i++){
				dou[x++]=D.getFloChecked("open", i);
				dou[x++]=D.getFloChecked("high", i);
				dou[x++]=D.getFloChecked("low", i);
				dou[x++]=D.getFloChecked("close", i);
			}
		} catch (NullPointerException e) {
			return null;
		}
		StandardDeviation std=new StandardDeviation(false);
		return new Float(std.evaluate(dou));
	}
}
