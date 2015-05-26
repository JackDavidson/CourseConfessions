package visuals;

import android.widget.EditText;

public interface Placeable {
	abstract void attachToScene();
	abstract void setX(int x);
	abstract void setY(int y);
	abstract void setWidth(int width);
	abstract void setHeight(int height);
	abstract EditText getEditText();
}
