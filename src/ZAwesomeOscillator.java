public class ZAwesomeOscillator extends TechnicalModule<Float> {
	public ZAwesomeOscillator() {
		super("ao");
	}

	@Override
	public Float calculate(int row) {
		float maM5, maM34;
		try {
			maM5 = D.getFloChecked("maM5", row);
			maM34 = D.getFloChecked("maM34", row);
		} catch (NullPointerException e) {
			return null;
		}
		return maM5 - maM34;
	}
}
