public class ZAcceleration extends TechnicalModule {
	public ZAcceleration() {
		super("ac");
	}

	@Override
	public Float calculate(int row) {
		float ao, maao;
		try {
			maao = ao = D.getFloChecked("ao", row);
			for (int i = 1; i < 5; i++)
				maao += D.getFloChecked("ao", row - i);
			maao /= 5;
		} catch (NullPointerException e) {
			return null;
		}
		return ao - maao;
	}
}
