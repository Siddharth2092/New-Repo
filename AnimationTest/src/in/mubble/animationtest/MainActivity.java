package in.mubble.animationtest;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
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
  private ImageView overlayView;
  private int flag=1;
  private RelativeLayout.LayoutParams rl;
  
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
    overlayView = new ImageView(this);
    
    setDimensions(  arrowView, vw, vh, 25, 30, 55,  12, 0, 0);
    setDimensions(overlayView, vw, vh, 25, 30, 55, -30, 0, 0);
    setDimensions(   ussdView, vw, vw, 45, 40,  5, -35, 0, 0);
    setDimensions(   snapView, vw, vh, 50, 70, 40, -15, 0, 0);
    
    ussdView.setBackgroundColor(Color.BLACK);
    
    ussd_content = new TextView(this);
    ussd_content.setText("Last call charge for 00:00:05 sec from Main   Bal:0.030. Available Main Bal: Rs. 32.433, 10-09-2026, 42000 local secs for 28days recharge 201");
    ussd_content.setPadding(5, 0, 0, 0);
    ussd_content.setTextSize(12);
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
    ok_button.setTextSize(12);
    ok_button.setGravity(Gravity.CENTER);
    RelativeLayout.LayoutParams lay2 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
    lay2.addRule(RelativeLayout.BELOW, line.getId());
    ussdView.addView(ok_button, lay2);
    
    
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
  
  @Override
  protected void onResume() {
    // TODO Auto-generated method stub
    super.onResume();
    this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    
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
        ussd_wd = width;
        rl = new RelativeLayout.LayoutParams(width, height);
      }
      else if(flag == 4 ) {
        snap_x = left;
        snap_y = top;
        snap_ht = height;
        
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
        
     final AnimatorSet animatorSet = new AnimatorSet();
     final AnimatorSet next = new AnimatorSet();
    // USSD comes from bottom to mid screen 
    ObjectAnimator scaleupx = ObjectAnimator.ofFloat(ussdView,"scaleX",1.7f);
    scaleupx.setDuration(400);
    
    ObjectAnimator scaleupy = ObjectAnimator.ofFloat(ussdView,"scaleY",1.7f);
    scaleupy.setDuration(400);
    
    ObjectAnimator ussdTranslate = ObjectAnimator.ofFloat(ussdView,"translationY", viewht, (viewht/2));
    ussdTranslate.setDuration(400);
    
    ObjectAnimator ussdFade = ObjectAnimator.ofFloat(ussdView, "alpha",0f, 1f);
    ussdFade.setDuration(400);
    
    ObjectAnimator ussdTranslatex = ObjectAnimator.ofFloat(ussdView, "translationX", viewwd, (0.23f * viewwd)); 
    ussdTranslatex.setDuration(400);
    
    // USSD goes from mid screen to top Delay: 1000
    ObjectAnimator scaledownx = ObjectAnimator.ofFloat(ussdView, "scaleX", 1f); 
    scaledownx.setStartDelay(1000);
    scaledownx.setDuration(300);
    
    ObjectAnimator scaledowny = ObjectAnimator.ofFloat(ussdView, "scaleY", 1f); 
    scaledowny.setDuration(300);
    
    ObjectAnimator ussdtranslate_topx = ObjectAnimator.ofFloat(ussdView, "translationX", (0.23f * viewwd), 0); 
    ussdtranslate_topx.setDuration(300);
    
    ObjectAnimator ussdtranslate_topy = ObjectAnimator.ofFloat(ussdView, "translationY", (viewht/2), 0); 
    ussdtranslate_topy.setDuration(300);
    
    // Snapview delay: 1300
    ObjectAnimator snapTranslateX = ObjectAnimator.ofFloat(snapView, "translationX", -snap_x,0);
    snapTranslateX.setStartDelay(1200);
    snapTranslateX.setDuration(300);
    
    ObjectAnimator snapTranslateY = ObjectAnimator.ofFloat(snapView, "translationY",box ,0);
    snapTranslateY.setDuration(300);
    
    ObjectAnimator snapFade = ObjectAnimator.ofFloat(snapView, "alpha",0f ,1f);
    snapFade.setDuration(300);
    
 // Overlay  Delay 1600
    ObjectAnimator overlayx = ObjectAnimator.ofFloat(overlayView, "translationX",0 ,(.65f * arrow_wd));
    overlayx.setStartDelay(1700);
    overlayx.setDuration(300);
    
    // scaling along XY Delay 2000
    ObjectAnimator rotationxy = ObjectAnimator.ofFloat(overlayView, "rotation",45);
    rotationxy.setStartDelay(1900);
    rotationxy.setDuration(70);
    
    ObjectAnimator overlayxy = ObjectAnimator.ofFloat(overlayView, "translationX",(.65f * arrow_wd) ,(.65f *arrow_wd));
    overlayxy.setDuration(70);
   
    ObjectAnimator overlayy = ObjectAnimator.ofFloat(overlayView, "translationY",0 ,(.15f * ovrly_ht));
    overlayy.setDuration(70);
    
    // scaling along Y Delay 2070
    ObjectAnimator rotationxyx = ObjectAnimator.ofFloat(overlayView, "rotation",90);
    rotationxyx.setStartDelay(1870);
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
    
    ObjectAnimator extra_delay1 = ObjectAnimator.ofFloat(ussdView, "alpha",1f, 1f);
    extra_delay1.setStartDelay(2475);
    extra_delay1.setDuration(3000);
    
    animatorSet.play(scaleupx).with(scaleupy).with(ussdTranslate).with(ussdTranslatex).with(ussdFade);
    animatorSet.play(scaledownx).with(scaledowny).with(ussdtranslate_topx).with(ussdtranslate_topy);
    animatorSet.play(snapTranslateX).with(snapTranslateY).with(snapFade);
    animatorSet.play(overlayx).with(rotationxy).before(overlayxy).with(overlayy);
    animatorSet.play(rotationxyx).with(overlayy1);
    animatorSet.play(hint_appear).with(priv_appear).with(enable_button);
    animatorSet.play(extra_delay1);
    animatorSet.addListener(new AnimatorListenerAdapter() {
    
    @Override
    public void onAnimationEnd(Animator animation) {
    
     
      
      ObjectAnimator ovrly_animx = ObjectAnimator.ofFloat(overlayView, "translationX", (.68f * arrow_wd), 0);
      ovrly_animx.setDuration(1);
      ObjectAnimator ovrly_animy = ObjectAnimator.ofFloat(overlayView, "translationY", ovrly_ht, 0);
      ovrly_animy.setDuration(1);
      ObjectAnimator rotation_anim = ObjectAnimator.ofFloat(overlayView, "rotation", 90);
      rotation_anim.setDuration(1);
      
      next.play(ovrly_animx).with(ovrly_animy).with(rotation_anim);
      next.start();
      animatorSet.start();
      
      }
    
    @Override
      public void onAnimationStart(Animator animation) {
        // TODO Auto-generated method stub
        super.onAnimationStart(animation);
        snapView.setAlpha(0f);
      }
    
    });

    animatorSet.start();
    }

}
