package in.mubble.fragmenttest;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class MainActivity extends FragmentActivity implements FragmentList.OnItemClickListener{

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);  
    
    FragmentList fl = new FragmentList();
    FragmentManager fm = getFragmentManager();
    FragmentTransaction tr = fm.beginTransaction();
    
    tr.add(R.id.FrameLayout1, fl);   
    tr.commit();
    
   }

  @Override
  public void onItemSelected(int pos) {
    
    FragmentDetail frag      = new FragmentDetail(pos);
    FragmentManager fr       = getFragmentManager();
    FragmentTransaction tran = fr.beginTransaction();
    
    tran.addToBackStack(null);
    tran.replace(R.id.FrameLayout1, frag);   
    tran.commit();
    
  }
  
  @Override
  public void onBackPressed() {
    
    int i = getFragmentManager().getBackStackEntryCount();
    if (i < 1) {      
      super.onBackPressed();
    }
    else
    getFragmentManager().popBackStack();
    
  }
   
}
