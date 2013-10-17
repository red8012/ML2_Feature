public class ZBollingerBandPercentage extends TechnicalModule<Float> {
	final static int CLOSE = 0, MID = 1;
	final int endPointType;

	public ZBollingerBandPercentage(int endPointType) {
		super("boll" + (endPointType == CLOSE ? "C" : "M"));
		this.endPointType = endPointType;
	}

	@Override
	public Float calculate(int row) {
		try {
			double mean = D.getFloChecked("maA20", row),
					std = D.getFloChecked("std20", row),
					end;
			if (endPointType == MID)
				end = (D.getFloChecked("high", row) + D.getFloChecked("low", row)) / 2;
			else end = D.getFloChecked("close", row);

			return new Float((end - mean + 2 * std) / std / 4);
		} catch (NullPointerException e) {
			return null;
		}
	}
}
