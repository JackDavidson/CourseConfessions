package visuals;

import activities.BaseScene;
import android.graphics.Color;
import android.text.InputFilter;
import android.text.InputType;
import android.text.Spanned;
import android.widget.EditText;
import android.widget.RelativeLayout;

public class PlacementEditText implements Placeable {

	/**
	 * this should accept values assuming height of 1280, and width of whatever
	 * width will be.
	 * 
	 * @param context
	 * @param x
	 *            - assume a with of BaseScene.width
	 * @param y
	 *            - assume a height of 1280
	 * @param width
	 * @param height
	 */
	private RelativeLayout.LayoutParams lp;
	private EditText text;
	private BaseScene context2;

	public PlacementEditText(BaseScene context, int x, int y, int width,
			int height, String hint) {
		context2 = context;
		lp = new RelativeLayout.LayoutParams(
				(int) (width * context.nativeToPxRatio),
				(int) (height * context.nativeToPxRatio));
		text = new EditText(context);
		text.setTextColor(Color.rgb(12, 26, 38));
		text.setHint(hint);
		setX(x);
		setY(y);
		if (text.getHint().equals("Password")) {
			text.setInputType(InputType.TYPE_CLASS_TEXT
					| InputType.TYPE_TEXT_VARIATION_PASSWORD);
		}

	}

	@Override
	public EditText getEditText() {
		return text;
	}

	public void attachToScene() {
		// TODO Auto-generated method stub
		if (text.getHint().equals("Username")) {
			System.out.println(text.getHint());
			InputFilter filter = new InputFilter() {
				@Override
				public CharSequence filter(CharSequence source, int start,
						int end, Spanned dest, int dstart, int dend) {
					for (int i = start; i < end; i++)
						if (!Character.isLetter(source.charAt(i)))
							return "";

					return null;
				}
			};
			text.setFilters(new InputFilter[] { filter });
		}

		context2.addContentView(text, lp);
	}

	@Override
	public void setX(int x) {
		// TODO Auto-generated method stub
		text.setX(x * context2.nativeToPxRatio);
	}

	@Override
	public void setY(int y) {
		// TODO Auto-generated method stub
		text.setY(y * context2.nativeToPxRatio);
	}

	@Override
	public void setWidth(int width) {
		// TODO Auto-generated method stub
		lp.width = (int) (width * context2.nativeToPxRatio);
	}

	@Override
	public void setHeight(int height) {
		// TODO Auto-generated method stub
		lp.height = (int) (height * context2.nativeToPxRatio);
	}

}
