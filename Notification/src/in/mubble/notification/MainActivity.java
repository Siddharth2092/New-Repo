package in.mubble.notification;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.Button;


public class MainActivity extends Activity {
  
  private static int count = 0;
  
  
  private Bitmap img;
  private NotificationManager nm;
  private static final int notificationId = 1234;
  private NotificationCompat.Builder builder;
  private String group = "Whats_up";
   
  private NotificationCompat.InboxStyle inboxStyle;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    
    Drawable draw = getResources().getDrawable(R.drawable.notification_icon);
    img           = ((BitmapDrawable)draw).getBitmap();
    Button btn    = (Button)findViewById(R.id.button1);
    
    btn.setOnClickListener(new View.OnClickListener() {      
      @Override
      public void onClick(View v) {        
        ViewNotification();
        count++;
        finish();
        }
    });
    
  }  
    private void ViewNotification() {
          
    Intent intent = new Intent (this, NewActivity.class);
    
    PendingIntent pi = PendingIntent.getActivity(this, 0 , intent, 0); 
    builder = new NotificationCompat.Builder(this);
    inboxStyle = new NotificationCompat.InboxStyle();
    builder.setSmallIcon(R.drawable.notification_icon);
    builder.setContentTitle(""+ (count+1) + " New Notification" );
 //   builder.setContentText("This is the content text");
   // builder.setContentInfo("Information");
    builder.setLargeIcon(img);
    builder.setAutoCancel(true);
    
    builder.setStyle(inboxStyle);
    addLine(count);
    //inboxStyle.addLine("Displayed Line Number: " + (count+1));
    inboxStyle.setBigContentTitle("" + (count+1) + " New Message");
    inboxStyle.setSummaryText("SIM 1");
    builder.setGroup(group);
    builder.setGroupSummary(true);
    builder.setContentIntent(pi);
   
    //builder.setStyle(new NotificationCompat.BigTextStyle().bigText(msg));
    //inboxStyle.addLine("Displayed Line Number: " + (count+1));
    
    nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
    nm.notify(notificationId, builder.build());
    
  }
    
   private void addLine(int count) {
    for (int i = 0 ; i <= count ; i++){
      inboxStyle.addLine("Displayed Line Number: " + (i+1));     
    }
    
    }
  
}
