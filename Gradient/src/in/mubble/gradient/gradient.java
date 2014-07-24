package in.mubble.gradient;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.PaintDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.view.View;
import android.widget.LinearLayout;

@SuppressLint("NewApi")
public class gradient extends View {

	public gradient(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onFinishInflate() {
		// TODO Auto-generated method stub
		super.onFinishInflate();
final LinearLayout layout=(LinearLayout)findViewById(R.id.LinearLayout1);
		
		ShapeDrawable.ShaderFactory sf=new ShapeDrawable.ShaderFactory() {
			
			@Override
			public Shader resize(int width, int height) {
				// TODO Auto-generated method stub
				LinearGradient lg=new LinearGradient(0,layout.getHeight(),layout.getWidth(),0, new int[] {Color.parseColor("#e9eff1"),
					Color.parseColor("#def1f8"),Color.parseColor("#cfdbe0"),Color.parseColor("#b2b2b2"),Color.parseColor("#abbdc4")
					,Color.parseColor("#a2c9d9"),Color.parseColor("#9bd4eb"),Color.parseColor("#bccfd6"),Color.parseColor("#cbcecf")
					,Color.parseColor("#c6d4d9"),Color.parseColor("#c1dbe6")},
						new float[] {0,0.15f,0.25f,0.35f,0.40f,0.65f,0.75f,0.80f,0.85f,0.90f,1}, Shader.TileMode.CLAMP); 
						
				return lg;
			}
		};
		PaintDrawable p=new PaintDrawable();
		p.setShape(new RectShape());
		p.setShaderFactory(sf);
		
		
		int sdk = android.os.Build.VERSION.SDK_INT;
		if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
		    layout.setBackgroundDrawable((Drawable)p);
		} else {
		    layout.setBackground((Drawable)p);
		}
		
		
	
	}
}
