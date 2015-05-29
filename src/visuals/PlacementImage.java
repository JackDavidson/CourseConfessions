package visuals;

import java.lang.ref.WeakReference;

import activities.BaseScene;
//import activities.main.DTImageView;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;
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
	private WeakReference<BaseScene> context2;
	private LinearLayout.LayoutParams vp;
	private LinearLayout linearLayout;

	// places an image relative to the image's left corner, not the center.
	// someone might need to confirm that
	public PlacementImage(BaseScene context, int id, int x, int y, int width,
			int height) {

		context2 = new WeakReference<BaseScene>(context);
		BaseScene contextForUse = context2.get();
		linearLayout = new LinearLayout(contextForUse);
		linearLayout.setOrientation(LinearLayout.VERTICAL);
		imageView = new DTImageView(contextForUse);
		imageView.setX(x * contextForUse.nativeToPxRatio);
		imageView.setY(y * contextForUse.nativeToPxRatio);
		vp = new LinearLayout.LayoutParams(
				(int) (width * contextForUse.nativeToPxRatio),
				(int) (height * contextForUse.nativeToPxRatio));

		try {
			Bitmap bitmap = BitmapFactory.decodeResource(
					contextForUse.getResources(), id);
			// vp = new LinearLayout.LayoutParams(width, height);

			imageView.imageBitmap = Bitmap.createScaledBitmap(bitmap,
					(int) (width * contextForUse.nativeToPxRatio),
					(int) (height * contextForUse.nativeToPxRatio), true);
			// create lots of objects here and stash them somewhere
		} catch (OutOfMemoryError E) {
			Log.e("Placement Image create", "OOM!!!!!!!!!!");
			BitmapFactory.Options opts = new BitmapFactory.Options();
			opts.inSampleSize = 8;
			imageView.imageBitmap = BitmapFactory.decodeResource(
					contextForUse.getResources(), id, opts);
			try {
				Bitmap newBitmap = Bitmap.createScaledBitmap(
						imageView.imageBitmap,
						(int) (width * contextForUse.nativeToPxRatio),
						(int) (height * contextForUse.nativeToPxRatio), true);
				imageView.imageBitmap = newBitmap;
			} catch (OutOfMemoryError E2) {
				Log.e("Placement Image create", "second time, out of memory");
			}
		}

		linearLayout.setClipChildren(false);
		linearLayout.addView(imageView);
	}

	// to fill the interface requirements
	public EditText getEditText() {
		return null;
	}

	@Override
	// attach the image to the scene
	public void attachToScene() {
		// TODO Auto-generated method stub
		BaseScene contextForUse = context2.get();
		if (contextForUse != null)
			contextForUse.addContentView(linearLayout, vp);

	}

	@Override
	// set the value of x
	public void setX(int x) {
		// TODO Auto-generated method stub
		BaseScene contextForUse = context2.get();
		imageView.setX(x * contextForUse.nativeToPxRatio);
	}

	@Override
	// set the value of y
	public void setY(int y) {
		// TODO Auto-generated method stub
		BaseScene contextForUse = context2.get();
		imageView.setY(y * contextForUse.nativeToPxRatio);
	}

	@Override
	// set the width
	public void setWidth(int width) {
		// TODO Auto-generated method stub
		BaseScene contextForUse = context2.get();
		vp.width = (int) (width * contextForUse.nativeToPxRatio);
	}

	@Override
	// set the height
	public void setHeight(int height) {
		// TODO Auto-generated method stub
		BaseScene contextForUse = context2.get();
		vp.height = (int) (height * contextForUse.nativeToPxRatio);
	}

	// helper class for the bitmapping, and placement of the image.
	public class DTImageView extends View {

		public Bitmap imageBitmap;

		public DTImageView(Context context) {
			super(context);
		}

		protected void onDraw(Canvas canvas) {
			super.onDraw(canvas);
			if (imageBitmap != null)
				canvas.drawBitmap(imageBitmap, 0, 0, null);
		}
	}
}
