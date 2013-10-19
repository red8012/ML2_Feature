public class ZFractal extends TechnicalModule<Float> {
	static final int UP = 0, PERFECT_UP = 1, DOWN = 2, PERFECT_DOWN = 3;
	final static String NAME_POSTFIX[] = {"U", "PU", "D", "PD"};
	final int type;

	public ZFractal(int type) {
		super("fractal" + NAME_POSTFIX[type]);
		this.type = type;
	}

	@Override
	public Float calculate(int row) {
		Float h[] = new Float[5],
				l[] = new Float[5];
		try {
			for (int i = 0; i < 5; i++) {
				h[i] = D.getFloChecked("high", row - i);
				l[i] = D.getFloChecked("low", row - i);
			}
		} catch (NullPointerException e) {
			return null;
		}
		switch (type) {
			case UP:
				if (h[2] > h[0] && h[2] > h[1] && h[2] > h[3] && h[2] > h[4]) return 1f;
				else return 0f;
			case DOWN:
				if (l[2] < l[0] && l[2] < l[1] && l[2] < l[3] && l[2] < l[4]) return 1f;
				else return 0f;
			case PERFECT_UP:
				if (h[2] > h[0] && h[2] > h[1] && h[2] > h[3] && h[2] > h[4] &&
						h[1] > h[0] && h[3] > h[4]) return 1f;
				else return 0f;
			case PERFECT_DOWN:
				if (l[2] < l[0] && l[2] < l[1] && l[2] < l[3] && l[2] < l[4] &&
						l[1] < l[0] && l[3] < l[4]) return 1f;
				else return 0f;
		}
		return null;
	}
}
