package in.mubble.customlistview;

import java.util.ArrayList;
import java.util.List;
import in.mubble.customlistview.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


public class MainActivity extends Activity {
  
  List< Rowitem > rowitems;
  private int pos;
  private int[] image = { R.drawable.nokia,R.drawable.samsung1, R.drawable.sony,R.drawable.rel,
                          R.drawable.bb,R.drawable.apple, R.drawable.lg,R.drawable.moto };  
  private  String[] myarray, array1;
  @Override
  
  protected void onCreate(Bundle savedInstanceState) {
		
    super.onCreate ( savedInstanceState );
	setContentView ( R.layout.activity_main );	
	myarray  = getResources().getStringArray( R.array.Manufacturers );
	array1   = getResources().getStringArray(R.array.text);
	rowitems = new ArrayList<Rowitem>();
		
	for( int i = 0; i < myarray.length ; i++ ) {
	  
	  Rowitem item = new Rowitem ( myarray [ i ], image[i] );
	  rowitems.add( item );
	}
	ListView lv          = ( ListView ) findViewById ( R.id.listView1 );
	CustomArrayAdapter c = new CustomArrayAdapter( this,R.layout.list_item, rowitems );	
	
	lv.setAdapter ( c );		
	lv.setOnItemClickListener ( new AdapterView.OnItemClickListener() {

	  @Override
	  public void onItemClick ( AdapterView<?> parent, View view,
			int position, long id ) {
				// TODO Auto-generated method stub	    
	    pos = position;	    	    
	    functionDialog();
		}
		});					    	   
	}
  protected void functionDialog() {
    // TODO Auto-generated method stub
    final AlertDialog.Builder builder = new AlertDialog.Builder ( MainActivity.this );
    LayoutInflater inflater = MainActivity.this.getLayoutInflater();        
    View v = inflater.inflate ( R.layout.dialog_pop, null );
    builder.setView ( v );
    TextView txt2 = ( TextView  ) v.findViewById ( R.id.textView2 );
    TextView txt1 = ( TextView  ) v.findViewById ( R.id.textView1 );
    ImageView img = ( ImageView ) v.findViewById ( R.id.img );
    
    img.setImageResource ( image[pos] );
    txt1.setText ( myarray[pos] );
    txt2.setText ( array1[pos] );
       
    builder.setPositiveButton ( "Exit", new DialogInterface.OnClickListener() {  
    
      @Override
      public void onClick(DialogInterface dialog, int which) {
        // TODO Auto-generated method stub
        dialog.cancel();               
      }
    });
    
    builder.setCancelable ( false );
    
    final AlertDialog dialog = builder.create();
    dialog.show();
    
    Handler h    = new Handler();
    Runnable run = new Runnable() {
      
      @Override
      public void run() {
        // TODO Auto-generated method stub
       dialog.dismiss(); 
      }
    };   
    h.postDelayed ( run, 5000 );        
  }	
}
