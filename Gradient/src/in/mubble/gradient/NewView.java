package in.mubble.gradient;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

public class NewView extends View{

  private Resources res; 
  private int [] colors;
  private float [] pos; 
  private Paint p;
  
  public NewView(Context context) {
    super(context);
		// TODO Auto-generated constructor stub
		init();						
  }
	
  public NewView(Context context, AttributeSet attrs) {
		// TODO Auto-generated constructor stub
    super(context,attrs);
		init();
  }
	
  public NewView(Context context, AttributeSet attrs, int defStyle) {
		// TODO Auto-generated constructor stub
	super(context,attrs,defStyle);
		init();		
	
  }
  
  private void init() {
    // TODO Auto-generated method stub
    res    = getResources();
    colors = new int[8];
    pos    = new float[8];
    
    int [] arr        = { res.getColor(R.color.mu_grey_1), res.getColor(R.color.mu_blue_1),
                          res.getColor(R.color.mu_grey_3), res.getColor(R.color.mu_grey_4),
                          res.getColor(R.color.mu_grey_3), res.getColor(R.color.mu_blue_4),
                          res.getColor(R.color.mu_grey_3), res.getColor(R.color.mu_blue_3),
                        };
    
    float [] position = {0,0.10f,0.25f,0.30f,0.33f,0.60f,0.80f,1};      
        
    for ( int i=0; i< arr.length; i++) {
      
      colors [i] = arr [i];
      pos [i]    = position [i];      
    }             
    p = new Paint();
  
  }

  @Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
			
		p.setShader(new LinearGradient(0,getHeight(),getWidth(), 0, colors, pos, Shader.TileMode.CLAMP));	    						
		canvas.rotate(20, 0, getHeight());
		canvas.drawPaint(p);		
	}
}
