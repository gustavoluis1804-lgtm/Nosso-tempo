package br.com.gustavo.nossotempo;

import android.Manifest; import android.app.*; import android.content.*; import android.graphics.Color;
import android.graphics.drawable.GradientDrawable; import android.net.Uri; import android.os.*; import android.provider.Settings;
import android.view.*; import android.widget.*;

public class MainActivity extends Activity {
    private TextView status;
    @Override protected void onCreate(Bundle b){super.onCreate(b);LinearLayout root=new LinearLayout(this);root.setOrientation(LinearLayout.VERTICAL);root.setGravity(Gravity.CENTER);root.setPadding(dp(24),dp(28),dp(24),dp(28));root.setBackgroundColor(Color.rgb(19,9,20));
        TextView heart=label("♥",52,Color.rgb(255,79,135));TextView title=label("NOSSO TEMPO",29,Color.WHITE);title.setTypeface(null,1);
        root.addView(heart);root.addView(title);root.addView(label("Desde 12/09/2026 às 16:43\n\nAtive as permissões para deixar o contador flutuando e receber os avisos.",16,Color.rgb(230,193,210)));status=label("",14,Color.WHITE);status.setPadding(0,dp(16),0,dp(12));root.addView(status);
        root.addView(button("1. PERMITIR JANELA FLUTUANTE",v->overlay()));root.addView(button("2. PERMITIR AVISOS",v->notifications()));root.addView(button("3. PERMITIR TELA INTEIRA",v->fullScreen()));root.addView(button("INICIAR CONTADOR",v->startCounter()));root.addView(button("PARAR CONTADOR",v->stopService(new Intent(this,FloatingCounterService.class))));setContentView(root);AnniversaryScheduler.scheduleAll(this);}
    private void overlay(){if(!Settings.canDrawOverlays(this))startActivity(new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,Uri.parse("package:"+getPackageName())));else refresh();}
    private void notifications(){if(Build.VERSION.SDK_INT>=33)requestPermissions(new String[]{Manifest.permission.POST_NOTIFICATIONS},40);else refresh();}
    private void fullScreen(){if(Build.VERSION.SDK_INT>=34)startActivity(new Intent(Settings.ACTION_MANAGE_APP_USE_FULL_SCREEN_INTENT,Uri.parse("package:"+getPackageName())));else exactAlarm();}
    private void exactAlarm(){if(Build.VERSION.SDK_INT>=31){AlarmManager a=getSystemService(AlarmManager.class);if(!a.canScheduleExactAlarms())startActivity(new Intent(Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM,Uri.parse("package:"+getPackageName())));}}
    private void startCounter(){if(!Settings.canDrawOverlays(this)){overlay();return;}exactAlarm();startForegroundService(new Intent(this,FloatingCounterService.class));AnniversaryScheduler.scheduleAll(this);refresh();}
    private Button button(String s,View.OnClickListener l){Button x=new Button(this);x.setText(s);x.setTextColor(Color.WHITE);x.setTextSize(13);x.setTypeface(null,1);GradientDrawable g=new GradientDrawable();g.setColor(Color.rgb(190,43,95));g.setCornerRadius(dp(16));x.setBackground(g);x.setOnClickListener(l);LinearLayout.LayoutParams p=new LinearLayout.LayoutParams(-1,dp(55));p.setMargins(0,dp(5),0,dp(5));x.setLayoutParams(p);return x;}
    private TextView label(String s,int z,int c){TextView v=new TextView(this);v.setText(s);v.setTextSize(z);v.setTextColor(c);v.setGravity(Gravity.CENTER);return v;}private int dp(int n){return Math.round(n*getResources().getDisplayMetrics().density);}private void refresh(){status.setText(Settings.canDrawOverlays(this)?"✓ Janela flutuante autorizada":"○ Autorize a janela flutuante");}@Override protected void onResume(){super.onResume();refresh();AnniversaryScheduler.scheduleAll(this);}
}
