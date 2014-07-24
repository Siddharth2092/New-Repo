package in.mubble.animationtest;


import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

  private View animview;
  
  private ImageView ussdView;
  private ImageView snapView;
  private ImageView arrowView;
  private ImageView overlayView;
  private int flag=1;
  
  private int ussd_x, ussd_y,ussd_ht, ussd_wd, ovrly_x, ovrly_y, ovrly_ht, overly_wd,
              snap_x, snap_y,snap_ht, snap_wd, arrow_x, arrow_y, arrow_ht, arrow_wd,viewht, viewwd;
  
  private TextView terms_priv, enable_hint;
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
    Log.e("viewwd", ""+viewwd);
    ussdView    = new ImageView(this);
    snapView    = new ImageView(this);
    arrowView   = new ImageView(this);
    overlayView = new ImageView(this);
    
    
    setDimensions(  arrowView, vw, vh, 25, 30, 47,  12, 0, 0);
    setDimensions(overlayView, vw, vh, 25, 30, 47, -30, 0, 0);
    setDimensions(   ussdView, vw, vw, 40, 40,  5, -35, 0, 0);
    setDimensions(   snapView, vw, vh, 50, 70, 35, -15, 0, 0);
    
    ussdView.setImageResource(R.drawable.first_use_ussd);
    snapView.setImageResource(R.drawable.first_use_snap);
    arrowView.setImageResource(R.drawable.first_use_arrow);
    overlayView.setBackgroundColor(getResources().getColor(R.color.overlay_grey));
    
    ((ViewGroup) animview).addView(arrowView);
    ((ViewGroup) animview).addView(overlayView);
    ((ViewGroup) animview).addView(ussdView);
    ((ViewGroup) animview).addView(snapView);
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
      
      if(flag == 1) {
        arrow_x = left;
        arrow_y = top;
        arrow_ht = height;
        arrow_wd = width;
        flag = flag+1;
      }
      
      else if(flag == 2) {
        ovrly_x = left;
        ovrly_y = top;
        ovrly_ht = height;
        flag = flag+1;
      }
      
      else if(flag == 3) {
        ussd_x = left;
        ussd_y = top;
        flag = flag+1;
      }
      else if(flag == 4 ) {
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
    
    float box = viewht - (.25f * viewht);
    float arrowlength = arrow_wd - (.32f * arrow_wd); 
    
    AnimatorSet animatorSet = new AnimatorSet();
    
    // USSD comes from bottom to mid screen 
    ObjectAnimator scaleupx = ObjectAnimator.ofFloat(ussdView,"scaleX",1.8f);
    scaleupx.setDuration(400);
    
    ObjectAnimator scaleupy = ObjectAnimator.ofFloat(ussdView,"scaleY",1.8f);
    scaleupy.setDuration(400);
    
    ObjectAnimator ussdTranslate = ObjectAnimator.ofFloat(ussdView,"translationY", viewht, (viewht/2));
    ussdTranslate.setDuration(400);
    
    ObjectAnimator ussdFade = ObjectAnimator.ofFloat(ussdView, "alpha",0f, 1f);
    ussdFade.setDuration(400);
    
    ObjectAnimator ussdTranslatex = ObjectAnimator.ofFloat(ussdView, "translationX", viewwd, (viewwd/4)); 
    ussdTranslatex.setDuration(400);
    
    // USSD goes from mid screen to top Delay: 1000
    ObjectAnimator scaledownx = ObjectAnimator.ofFloat(ussdView, "scaleX", 1f); 
    scaledownx.setStartDelay(1000);
    scaledownx.setDuration(100);
    
    ObjectAnimator scaledowny = ObjectAnimator.ofFloat(ussdView, "scaleY", 1f); 
    scaledowny.setDuration(100);
    
    ObjectAnimator ussdtranslate_topx = ObjectAnimator.ofFloat(ussdView, "translationX", (viewwd/4), 0); 
    ussdtranslate_topx.setDuration(100);
    
    ObjectAnimator ussdtranslate_topy = ObjectAnimator.ofFloat(ussdView, "translationY", (viewht/2), 0); 
    ussdtranslate_topy.setDuration(100);
    
    
    
    // snapview Delay 1100 + Manual Delay: 100 => Total: 1200
    
    ObjectAnimator snapTranslateX = ObjectAnimator.ofFloat(snapView, "translationX", -snap_x,0);
    snapTranslateX.setStartDelay(1200);
    snapTranslateX.setDuration(300);
    
    ObjectAnimator snapTranslateY = ObjectAnimator.ofFloat(snapView, "translationY",box ,0);
    snapTranslateY.setDuration(300);
    
    ObjectAnimator snapFade = ObjectAnimator.ofFloat(snapView, "alpha",0f ,1f);
    snapFade.setDuration(300);
    
    // Overlay  Delay 1600
    ObjectAnimator overlayx = ObjectAnimator.ofFloat(overlayView, "translationX",0 ,arrowlength);
    overlayx.setStartDelay(1600);
    overlayx.setDuration(300);
    
    // scaling along XY Delay 1900
    ObjectAnimator rotationxy = ObjectAnimator.ofFloat(overlayView, "rotation",45);
    rotationxy.setStartDelay(1800);
    rotationxy.setDuration(70);
    
    ObjectAnimator overlayxy = ObjectAnimator.ofFloat(overlayView, "translationX",arrowlength ,(.68f *arrow_wd));
    overlayxy.setDuration(70);
   
    ObjectAnimator overlayy = ObjectAnimator.ofFloat(overlayView, "translationY",0 ,(.20f * ovrly_ht));
    overlayy.setDuration(70);
    
    // scaling along Y Delay 1970
    ObjectAnimator rotationxyx = ObjectAnimator.ofFloat(overlayView, "rotation",90);
    rotationxyx.setStartDelay(1770);
    rotationxyx.setDuration(300);
    
    ObjectAnimator overlayy1 = ObjectAnimator.ofFloat(overlayView, "translationY",(.20f * ovrly_ht) ,(ovrly_ht));
    overlayy1.setDuration(400);
    
    ObjectAnimator hint_appear = ObjectAnimator.ofFloat(enable_hint, "alpha",0f, 1f);
    hint_appear.setStartDelay(2370);
    hint_appear.setDuration(1);
    
    ObjectAnimator priv_appear = ObjectAnimator.ofFloat(terms_priv, "alpha",0f, 1f);
    priv_appear.setStartDelay(5);
    priv_appear.setDuration(1);
    
    ObjectAnimator enable_button = ObjectAnimator.ofFloat(enable_btn, "alpha",0f, 1f);
    enable_button.setStartDelay(5);
    enable_button.setDuration(1);
    
    
    animatorSet.play(scaleupx).with(scaleupy).with(ussdTranslate).with(ussdTranslatex).with(ussdFade);
    animatorSet.play(scaledownx).with(scaledowny).with(ussdtranslate_topx).with(ussdtranslate_topy);
    animatorSet.play(snapTranslateX).with(snapTranslateY).with(snapFade);
    animatorSet.play(overlayx);  
    animatorSet.play(rotationxy).before(overlayxy).with(overlayy);
    animatorSet.play(rotationxyx).with(overlayy1);
    animatorSet.play(hint_appear).with(priv_appear).with(enable_button);
    animatorSet.start();
    
    }

}
