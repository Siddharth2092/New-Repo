package in.mubble.gradient;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.view.View;

public class TestView extends View{

	public TestView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		
		
		int[] colors = {Color.parseColor("#e9eff1"),
				Color.parseColor("#def1f8"),Color.parseColor("#cfdbe0"),Color.parseColor("#b2b2b2"),Color.parseColor("#abbdc4")
				,Color.parseColor("#a2c9d9"),Color.parseColor("#9bd4eb"),Color.parseColor("#bccfd6"),Color.parseColor("#cbcecf")
				,Color.parseColor("#c6d4d9"),Color.parseColor("#c1dbe6")};
		
		float[] positions = {0,0.15f,0.25f,0.35f,0.40f,0.65f,0.75f,0.80f,0.85f,0.90f,1};				
		Paint pp=new Paint();
		pp.setShader(new LinearGradient(0, 0,getWidth(),getHeight(), colors, positions, Shader.TileMode.CLAMP));
		canvas.drawPaint(pp);
		
	}
}
