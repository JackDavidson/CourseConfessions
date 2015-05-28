package visuals;

import activities.BaseScene;
//import activities.main.DTImageView;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

public class PlacementImage implements Placeable {

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
	private DTImageView imageView;
	private BaseScene context2;
	private LinearLayout.LayoutParams vp;
	private LinearLayout linearLayout;
	//places an image relative to the image's left corner, not the center. someone might need to confirm that
	public PlacementImage(BaseScene context, int id, int x, int y, int width, int height) 
	{
		context2 = context;
		linearLayout = new LinearLayout(context);
		linearLayout.setOrientation(LinearLayout.VERTICAL);
		Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), id);
		imageView = new DTImageView(context);
		vp = new LinearLayout.LayoutParams((int)(width*context.nativeToPxRatio), 
			(int)(height*context.nativeToPxRatio));
		//vp = new LinearLayout.LayoutParams(width, height);
		imageView.setX(x* context.nativeToPxRatio);
		imageView.imageBitmap = Bitmap.createScaledBitmap(bitmap,
				(int)(width*context.nativeToPxRatio),(int)(height*context.nativeToPxRatio),true);
		imageView.setY(y * context.nativeToPxRatio);
		linearLayout.setClipChildren(false);
		linearLayout.addView(imageView);
	}
	//to fill the interface requirements
	public EditText getEditText(){return null;}
	@Override
	//attach the image to the scene
	public void attachToScene() {
		// TODO Auto-generated method stub
		context2.addContentView(linearLayout, vp);
		
	}

	@Override
	//set the value of x
	public void setX(int x) {
		// TODO Auto-generated method stub
		imageView.setX(x * context2.nativeToPxRatio);
	}

	@Override
	//set the value of y
	public void setY(int y) {
		// TODO Auto-generated method stub
		imageView.setY(y * context2.nativeToPxRatio);
	}

	@Override
	//set the width
	public void setWidth(int width) {
		// TODO Auto-generated method stub
		vp.width = (int) (width * context2.nativeToPxRatio);
	}

	@Override
	//set the height
	public void setHeight(int height) {
		// TODO Auto-generated method stub
		vp.height = (int) (height * context2.nativeToPxRatio);
	}
	//helper class for the bitmapping, and placement of the image.
	public class DTImageView extends View {

	    public Bitmap imageBitmap;

	    public DTImageView(Context context) { 
	        super(context); 
	    } 

	    protected void onDraw(Canvas canvas) {
	       super.onDraw(canvas);
	       if(imageBitmap != null)
	         canvas.drawBitmap(imageBitmap, 0, 0, null); 
	    }
	}
}

