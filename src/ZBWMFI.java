public class ZBWMFI extends TechnicalModule<Float> {
	public ZBWMFI() {
		super("bwmfi");
	}

	@Override
	public Float calculate(int row) {
		try {
			return (D.getFloChecked("high", row) - D.getFloChecked("low", row)) / D.getFloChecked("volume", row);
		} catch (NullPointerException e) {
			return null;
		}
	}
}
