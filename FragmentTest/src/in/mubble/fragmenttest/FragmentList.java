package in.mubble.fragmenttest;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class FragmentList extends Fragment {

  OnItemClickListener mCallBack;
  int pos;
  public interface OnItemClickListener {
    
    public void onItemSelected(int pos);
    
  }
  
  @Override
  public void onAttach(Activity activity) {    
    super.onAttach(activity);
    Log.i("onAttach", "done");
    try {
      
      mCallBack = (OnItemClickListener)activity;
    } catch (ClassCastException e){
      throw new ClassCastException (activity.toString()
          + " must implement OnItemClickListener");      
    }
  }
  
 @Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
    Bundle savedInstanceState) {
  Log.i("onCreateView", "done");   
  View view      = inflater.inflate(R.layout.list_fragment, container, false);  
  ListView lv    = (ListView)view.findViewById(R.id.list);  
  String[] array = getResources().getStringArray(R.array.listing); 
  
  lv.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, array));   
  
  lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
        long id) {
      
      pos = position;     
      sendpos(pos);
    }
  });
  Log.i("onCreateView", "exit");
  return view;
}
 
 protected void sendpos(int pos) {
   Log.e("fr_test", "item clicked, sending event to activity");
   mCallBack.onItemSelected(pos);
}
 
}

