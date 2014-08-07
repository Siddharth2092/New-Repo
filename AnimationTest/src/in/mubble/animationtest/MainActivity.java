package in.mubble.animationtest;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

  private View animview;
  
  private RelativeLayout ussdView;
  private ImageView snapView;
  private ImageView arrowView;
  private int flag=1;
  
  private int ussd_x, ussd_y,ussd_ht, ussd_wd, ovrly_x, ovrly_y, ovrly_ht, overly_wd, line_wd,
  snap_x, snap_y,snap_ht, snap_wd, arrow_x, arrow_y, arrow_ht, arrow_wd, viewht, viewwd;
  
  private TextView terms_priv, enable_hint, ussd_content, ok_button, line;
  private Button enable_btn;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.enable_permission_fragment);
  
    animview = (View) findViewById(R.id.anim_view);
    
    DisplayMetrics metrics = this.getResources().getDisplayMetrics();
    int vw = metrics.widthPixels;
    int vh = metrics.heightPixels;
    viewwd = vw;
    vh     = (int)(vh/2);
    viewht = vh;
    
    ussdView    = new RelativeLayout(this);
    snapView    = new ImageView(this);
    arrowView   = new ImageView(this);
    
    
    setDimensions(   ussdView, vw, vw, 45, 40, 30, 10, 0, 0);
    setDimensions(  arrowView, vw, vh, 20, 20, 40, 10, 0, 0);
    setDimensions(   snapView, vw, vh, 50, 70, 25, 10, 0, 0);
    
    ussdView.setBackgroundColor(Color.BLACK);
    
    ussd_content = new TextView(this);
    ussd_content.setText("Last call charge for 00:00:05 sec from Main   Bal:0.030. Available Main Bal: Rs. 32.433, 10-09-2026, 42000 local secs for 28days recharge 201");
    ussd_content.setPadding(5, 0, 0, 0);
    ussd_content.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 13);
    ussd_content.setTextColor(Color.WHITE);
    ussd_content.setId(1);
    RelativeLayout.LayoutParams lay = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
    lay.addRule(RelativeLayout.ALIGN_PARENT_TOP);
    ussdView.addView(ussd_content, lay);
   
    line = new TextView(this);
    line.setBackgroundColor(Color.WHITE);
    line.setId(2);
    line_wd = (int)(.95*ussd_wd);
    line.setGravity(1);
    RelativeLayout.LayoutParams lay1 = new RelativeLayout.LayoutParams(line_wd, 1);
    lay1.addRule(RelativeLayout.BELOW, ussd_content.getId());
    ussdView.addView(line, lay1);
    
    ok_button = new TextView(this);
    ok_button.setText("OK");
    ok_button.setPadding(0, 5, 0, 0);
    ok_button.setTextColor(Color.WHITE);
    ok_button.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 13);
    ok_button.setGravity(Gravity.CENTER);
    RelativeLayout.LayoutParams lay2 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
    lay2.addRule(RelativeLayout.BELOW, line.getId());
    ussdView.addView(ok_button, lay2);
    
    arrowView.setImageResource(R.drawable.save);
    snapView.setImageResource(R.drawable.first_use_snap);
    
    ((ViewGroup) animview).addView(ussdView);
    ((ViewGroup) animview).addView(arrowView);
    ((ViewGroup) animview).addView(snapView);
    
    arrowView.setAlpha(0f);
    snapView.setAlpha(0f);
    
    terms_priv = (TextView)findViewById(R.id.terms_privacy);
    terms_priv.setAlpha(0f);
    
    enable_hint = (TextView)findViewById(R.id.enable_hint);
    enable_hint.setAlpha(0f);
    
    enable_btn = (Button)findViewById(R.id.enable_button);
    enable_btn.setAlpha(0f);
    
    this.enableAnimation();
   
  }
  private void setDimensions(View v, int vw, int vh, int pctW, int pctH, int pctML,
      int pctMT, int pctMR, int pctMB) {
      
      int height = calculate(pctH, vh);
      int width  = calculate(pctW, vw);
      int left   = calculate(pctML, vw);
      int top    = calculate(pctMT, vh);
      int right  = calculate(pctMR, vw);
      int bottom = calculate(pctMB, vh);
      
      if(flag == 2) {
        arrow_x = left;
        arrow_y = top;
        arrow_ht = height;
        Log.e("arrow height", ""+arrow_ht);
        arrow_wd = width;
        flag = flag+1;
      }
      
      else if(flag == 1) {
        ussd_x = left;
        ussd_y = top;
        flag = flag+1;
      }
      else if(flag == 3 ) {
        snap_x = left;
        snap_y = top;
        snap_ht = height;
        Log.e("snap X", ""+snap_x);
        
      }
  
      LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(width, height);
      lp.setMargins(left, top, right, bottom);
      
      v.setLayoutParams(lp);
  }
  
  private int calculate(int pct, int total) {
    if (pct < 0) {
      pct = pct * -1;  
      pct = (int)((total * pct)/100);
        return (pct * -1);
    }
    return (int)((total * pct)/100); 
}

  private void enableAnimation() {
    
    AnimatorSet animatorSet = new AnimatorSet();
    
    final AnimatorSet animSet = new AnimatorSet();
    
    // USSD stays on top and scales down 
    
    ObjectAnimator ussdscaledownx = ObjectAnimator.ofFloat(ussdView,"scaleX",0f);
    ussdscaledownx.setDuration(10);
    
    ObjectAnimator ussdscaledowny = ObjectAnimator.ofFloat(ussdView,"scaleY",0f);
    ussdscaledowny.setDuration(10);
    
    // USSD Scales Up Delay:1000
    ObjectAnimator ussdFade = ObjectAnimator.ofFloat(ussdView, "alpha",0f, 1f);
    ussdFade.setStartDelay(1000);
    ussdFade.setDuration(1000);
    
    ObjectAnimator ussdscaleupx = ObjectAnimator.ofFloat(ussdView,"scaleX",1.5f);
    ussdscaleupx.setDuration(1000);
    
    ObjectAnimator ussdscaleupy = ObjectAnimator.ofFloat(ussdView,"scaleY",1.5f);
    ussdscaleupy.setDuration(1000);
    
    // Save image translation Delay: 1000
    ObjectAnimator save_translatey = ObjectAnimator.ofFloat(arrowView, "translationY", -(.10f*viewht), (.02f*viewht));
    save_translatey.setStartDelay(2000);
    save_translatey.setDuration(1000);
    
    ObjectAnimator save_fade = ObjectAnimator.ofFloat(arrowView, "alpha",0f ,1f);
    save_fade.setDuration(1000);
    
    //Snap
    ObjectAnimator snapscaledownx = ObjectAnimator.ofFloat(snapView,"scaleX",0f);
    snapscaledownx.setStartDelay(3000);
    snapscaledownx.setDuration(10);
    
    ObjectAnimator snapscaledowny = ObjectAnimator.ofFloat(snapView,"scaleY",0f);
    snapscaledowny.setDuration(10);
    
    ObjectAnimator snapFade = ObjectAnimator.ofFloat(snapView, "alpha",0f ,1f);
    snapFade.setDuration(1000);
    
    ObjectAnimator snapupx = ObjectAnimator.ofFloat(snapView,"scaleX",1.5f);
    snapupx.setDuration(1000);
    
    ObjectAnimator snapupy = ObjectAnimator.ofFloat(snapView,"scaleY",1.5f);
    snapupy.setDuration(1000);
    
    //hints
    ObjectAnimator hint_appear = ObjectAnimator.ofFloat(enable_hint, "alpha",0f, 1f);
    hint_appear.setStartDelay(5370);
    hint_appear.setDuration(1);
    
    ObjectAnimator priv_appear = ObjectAnimator.ofFloat(terms_priv, "alpha",0f, 1f);
    priv_appear.setStartDelay(5);
    priv_appear.setDuration(1);
    
    ObjectAnimator enable_button = ObjectAnimator.ofFloat(enable_btn, "alpha",0f, 1f);
    enable_button.setStartDelay(5);
    enable_button.setDuration(1);
    
    
    animatorSet.play(ussdscaledownx).with(ussdscaledowny);
    animatorSet.play(ussdFade).with(ussdscaleupx).with(ussdscaleupy);
    animSet.play(save_translatey).with(save_fade);
    animSet.addListener(new AnimatorListenerAdapter() {
    @Override
    public void onAnimationEnd(Animator animation) {
      // TODO Auto-generated method stub
      super.onAnimationEnd(animation);
      animSet.start();
    }
    
    });
    
    
    animatorSet.play(snapscaledownx).with(snapscaledowny).with(snapFade).with(snapupx).with(snapupy);
    animatorSet.play(hint_appear).with(priv_appear).with(enable_button);
    animSet.start();
    
    animatorSet.start();
    
   
    
    }
  

}
