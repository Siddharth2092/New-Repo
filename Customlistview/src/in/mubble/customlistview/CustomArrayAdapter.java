package in.mubble.customlistview;

import java.util.List;
import java.util.Random;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class CustomArrayAdapter extends ArrayAdapter<Rowitem>{

  int length;
  Context context;
  Random rand = new Random();
  public CustomArrayAdapter ( Context context, int resource, List<Rowitem> items ) {
    super ( context, resource, items );
	this.context = context;		
	length = items.size();
	}
	 
  private class ViewHolder{  
    ImageView imgvw;
    TextView txtvw;
  }
  
  @Override
  public View getView ( int position, View convertView, ViewGroup parent ) {
		
    ViewHolder holder = null;
    Rowitem rowitem   = getItem ( position );    
    LayoutInflater li = ( LayoutInflater ) context.getSystemService ( Activity.LAYOUT_INFLATER_SERVICE );
    if ( convertView == null ) {
      convertView  = li.inflate ( R.layout.list_item, null );
      holder       = new ViewHolder();
      holder.imgvw = ( ImageView ) convertView.findViewById (R.id.icon );
      holder.txtvw = ( TextView ) convertView.findViewById ( R.id.textView1 );
      convertView.setBackgroundColor ( getColor() );                       
      convertView.setTag ( holder );
    }
    else holder = ( ViewHolder ) convertView.getTag();    
    holder.imgvw.setImageResource ( rowitem.getimageId () );
    holder.txtvw.setText ( rowitem.getname () );
	return convertView;
  }

  private int getColor() {
                
    int r = rand.nextInt ( 255 );
    int g = rand.nextInt ( 255 );
    int b = rand.nextInt ( 255 );    
    int randcolor = Color.rgb ( r, g, b );
    return randcolor;    
    }
}
