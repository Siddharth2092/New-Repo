package in.mubble.fragmenttest;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FragmentDetail extends Fragment {
  
  private int pos;
  
  public FragmentDetail(int pos) {
        
    this.pos = pos;
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    
    View v         = inflater.inflate ( R.layout.detail_frag, container, false );
    String[] array = getResources().getStringArray(R.array.listing);
    TextView tv    = (TextView)v.findViewById(R.id.textView1 );
    tv.setText (array[pos]);
    
    return v;
  }  
}
