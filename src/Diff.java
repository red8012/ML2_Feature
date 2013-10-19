public class Diff extends TechnicalModule<Float> {
	final String target;
	final int offset;

	public Diff(String target, int offset) {
		super("D" + target + String.valueOf(offset));
		this.target = target;
		this.offset = offset;
	}

	@Override
	public Float calculate(int row) {
		try {
			return D.getFloChecked(target, row) - D.getFloChecked(target, row - offset);
		} catch (NullPointerException e) {
			return null;
		}
	}
}
