package br.com.geostore.activities;

import java.util.Arrays;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ListaProd extends Activity{
	//faz parte do método populate
	protected static final int CONTEXTMENU_DELETEITEM = 0;
	private static final String CATEGORIA = "logcat";
	
private String[] Produtos;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_lvdemo);
        Produtos = getResources().getStringArray(R.array.produtos);
        Arrays.sort(Produtos);
        
		ListView list = (ListView)findViewById(R.id.list);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.listitem, Produtos);
		list.setAdapter(adapter);
		registerForContextMenu(list);
    }       
    
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
    		ContextMenuInfo menuInfo) {
    	if (v.getId()==R.id.list) {
    	    AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;
    		menu.setHeaderTitle(Produtos[info.position]);
    		String[] menuItems = getResources().getStringArray(R.array.menu); 
    		for (int i = 0; i<menuItems.length; i++) {
    			menu.add(Menu.NONE, i, i, menuItems[i]);
			}
    	}
    }
    
  //método que popula lista    
    //public void onPopulateContextMenu(ContextMenu conMenu, View arg1, Object arg2) {
    //    conMenu.setHeaderTitle("ContextMenu");
    //    conMenu.add(0, CONTEXTMENU_DELETEITEM, 0, "Delete this favorite!");
    //    /* Add as many context-menu-options as you want to. */
    //}
    
    @Override
    public boolean onContextItemSelected(MenuItem item) {
	    AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
	    int menuItemIndex = item.getItemId();
	    //parte adicionada
	    switch (menuItemIndex) {
	    case 0:
	      Log.i(CATEGORIA,"Gerar Voucher");
	      final Intent it1 = new Intent(ListaProd.this, Voucher.class);
          startActivity(it1);
	      return true;
	    case 1:
	    	Log.i(CATEGORIA,"Traçar Rota");
	    	final Intent it2 = new Intent(ListaProd.this, Rota.class);
	        startActivity(it2);
	      return true;
	    case 2:
	    	Log.i(CATEGORIA,"Discar Loja");
	    	//final Intent it3 = new Intent(ListaProd.this, DiscarLoja.class);
	    	
	    	String fone = "88064406";
			Uri foneloja = Uri.parse("tel:" + fone);
			Intent discar = new Intent(Intent.ACTION_CALL,foneloja);
			startActivity(discar);	    	
	    	
	      return true;
	    default:
	      return super.onContextItemSelected(item);
	    }
	    //fim da parte adicionada
	    
	    //parte original
	    /*
	    Log.i(CATEGORIA,"menuItemIndex= "+ menuItemIndex);
	    
	    String[] menuItems = getResources().getStringArray(R.array.menu);
		String menuItemName = menuItems[menuItemIndex];
		
		Log.i(CATEGORIA,"itemName= "+ menuItemName);
		
	    String listItemName = Produtos[info.position];
	    
	    Log.i(CATEGORIA,"listItemName= "+ listItemName);
	    
	    TextView text = (TextView)findViewById(R.id.footer);
	    text.setText(String.format("Selected %s for item %s", menuItemName, listItemName));
	    return true;
	    */   	
    }
}
