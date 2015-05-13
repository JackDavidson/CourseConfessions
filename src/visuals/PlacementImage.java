package visuals;

import com.bitsplease.courseconfessions.R;

import activities.BaseScene;
//import activities.main.DTImageView;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
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
	private Bitmap bitmap;
	public PlacementImage(BaseScene context, int x, int y, int width, int height) 
	{
		context2 = context;
		linearLayout = new LinearLayout(context);
		linearLayout.setOrientation(LinearLayout.VERTICAL);
		bitmap = BitmapFactory.decodeResource(context.getResources(), R.raw.background);
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
	public EditText getEditText(){return null;}
	@Override
	public void attachToScene() {
		// TODO Auto-generated method stub
		context2.addContentView(linearLayout, vp);
		
	}

	@Override
	public void setX(int x) {
		// TODO Auto-generated method stub
		imageView.setX((context2.widthPx / 2) - x * context2.nativeToPxRatio);
	}

	@Override
	public void setY(int y) {
		// TODO Auto-generated method stub
		imageView.setY((context2.heightPx * 1 / 2) + y * context2.nativeToPxRatio);
	}

	@Override
	public void setWidth(int width) {
		// TODO Auto-generated method stub
		vp.width = (int) (width * context2.nativeToPxRatio);
	}

	@Override
	public void setHeight(int height) {
		// TODO Auto-generated method stub
		vp.height = (int) (height * context2.nativeToPxRatio);
	}

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

