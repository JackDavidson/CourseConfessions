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
	//places it via top left corner of the text box. someone check this to make sure?
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
	//return the EditText field of the PlacementEditText object
	public EditText getEditText() {
		return text;
	}
	//attach the placementEditText to scene.
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
	//set the x
	public void setX(int x) {
		text.setX(x * context2.nativeToPxRatio);
	}

	@Override
	//set the y
	public void setY(int y) {
		text.setY(y * context2.nativeToPxRatio);
	}

	@Override
	//set the width
	public void setWidth(int width) {
		lp.width = (int) (width * context2.nativeToPxRatio);
	}

	@Override
	//set the height
	public void setHeight(int height) {
		lp.height = (int) (height * context2.nativeToPxRatio);
	}

}
