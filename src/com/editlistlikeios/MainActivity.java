package com.editlistlikeios;

import java.util.ArrayList;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

 public class MainActivity extends ListActivity implements OnClickListener {
	static private String termsName1[]={"terms 1","terms 2","terms 3","terms 4","terms 5","terms 6"};
	ArrayList<String>termsName=new ArrayList<String>();
	static int EditListFlag= 4;
	static MylibraryAdapter library;
	static Animation show;
	EditText addTerms;
	ImageButton cross;
	
	public Button edit_list_btn;
	static InputMethodManager inputMgr;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		
		super.onCreate(savedInstanceState);                 
		
		/*hide title bar*/           
    	requestWindowFeature(Window.FEATURE_NO_TITLE);
    	/*set orientation portrait mode*/
    	setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		
    	setContentView(R.layout.activity_main);
		 
		inputMgr =(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
	    
	    edit_list_btn=(Button)findViewById(R.id.btn_my_library_edit);edit_list_btn.setOnClickListener(this);
		show=AnimationUtils.loadAnimation(this, R.anim.fade);
		
		 for (int i = 0; i < termsName1.length; i++) {
			termsName.add(termsName1[i]);
		 }
		termsName.add("Add Terms");
	    library=new MylibraryAdapter(this, termsName);
		setListAdapter(library);                         
    }
	
	//adapter class bind the row layout into a list.
	private static class MylibraryAdapter extends ArrayAdapter<String> {
		private Context mContext;
		private ArrayList<String> termsName =new ArrayList<String>();
		public MylibraryAdapter(Context context, ArrayList<String> termsName2) 
		{                     
			super(context, android.R.layout.simple_list_item_1, termsName2);
			this.mContext = context;
			this.termsName = termsName2;
		}                                         
    
	  @Override                      
	  public View getView(final int pos, View view, final ViewGroup parent) {
			final ViewHolder holder;
			if (view == null) {
				LayoutInflater inflater = LayoutInflater.from(mContext);
				view = inflater.inflate(R.layout.edit_list_row, parent,
						false);
				holder = new ViewHolder();
				holder.TermsName = (TextView) view              
						.findViewById(R.id.tv_album_name_of_album);
				holder.red=(ImageView)view.findViewById(R.id.iv_mylibrary_red);
				holder.save=(Button)view.findViewById(R.id.btn_my_library_save);
				holder.addterm=(FrameLayout)view.findViewById(R.id.fl_my_library_add);
				holder.red_vertical=(ImageView)view.findViewById(R.id.iv_mylibrary_red_vertical);
				holder.green=(ImageView)view.findViewById(R.id.iv_mylibrary_green_vertical);
				holder.red_button=(FrameLayout)view.findViewById(R.id.fl_deleteButton);
				holder.Terms=(EditText)view.findViewById(R.id.et_my_library_addTerms);     
				
				view.setTag(holder);
			 } else {
				holder = (ViewHolder) view.getTag();
			 }
			holder.TermsName.setText(termsName.get(pos));
			holder.TermsName.setTag(termsName.get(pos));
			if(EditListFlag==1)
			 {
				
			   if(termsName.get(pos).equals("Add Terms")){
				   
				    holder.red_button.setVisibility(4);
				    holder.red.setVisibility(4);
				    holder.red_button.setAnimation(null);
					holder.red_vertical.setVisibility(4);
					holder.green.setVisibility(1);
				 }
			      else {
					 //Log.v("fdf", "value");
					 holder.red_button.setVisibility(4);
					 holder.red_button.setAnimation(null);
					 holder.red_vertical.setVisibility(4);
					 holder.red.setVisibility(1);
					 holder.green.setVisibility(4);
					 holder.red.startAnimation(show);	
				     }                                      
				     holder.TermsName.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v){
						 String str=(String) holder.TermsName.getTag();
						   System.out.println("term  "+str);
						 
					   }                                  
				    });
			  }else if(EditListFlag==2){
				  
			  }else 
			  {
				holder.red.setVisibility(4);
			  }
			 holder.green.setOnClickListener(new OnClickListener(){
			 @Override
				public void onClick(View v){
					holder.red_button.setVisibility(4);
				    holder.red.setVisibility(4);
					holder.red_vertical.setVisibility(4);
					holder.green.setVisibility(4);
					holder.TermsName.setVisibility(4);
					holder.addterm.setVisibility(1);
					holder.save.setVisibility(1);
					holder.Terms.requestFocus();
					holder.Terms.setCursorVisible(true);
				    inputMgr.toggleSoftInput(0, 0);
				}
			  });
			 
              holder.save.setOnClickListener(new OnClickListener() {
			   @Override
			    public void onClick(View v) {
					inputMgr.hideSoftInputFromWindow(((Activity) getContext()).getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
				    holder.red.setVisibility(4);
				    holder.red_button.setAnimation(null);
					holder.red_vertical.setVisibility(4);
					holder.green.setVisibility(1);
					holder.TermsName.setVisibility(1);
					holder.addterm.setVisibility(4);
					holder.save.setVisibility(4);
					String getTerm=holder.Terms.getText().toString();
					System.out.println("getTerm"+getTerm);
					if(getTerm.equals("")){
						
					}else {
						
						int i=termsName.size();
						System.out.println("size"+i);
                        termsName.add(i-1, getTerm);
                       
						holder.Terms.setText("");                             
						library.notifyDataSetChanged();
						holder.red_button.setVisibility(4);
					}
				}
			});
			holder.red.setOnClickListener(new OnClickListener(){
				
			 @Override
			 public void onClick(View v){
			      holder.red.setVisibility(4);
				  holder.red_button.setVisibility(1);  
				  holder.red_vertical.setVisibility(1);
				  holder.red_button.startAnimation(show);
				 
				}
			});
			
			 holder.red_button.setOnClickListener(new OnClickListener(){ 
		       @Override
			 public void onClick(View v){      
		    	 holder.red_button.setVisibility(4);
		    	 holder.green.setVisibility(4);
		    	 holder.red.setVisibility(4);
		    	 holder.red_vertical.setVisibility(4);
					 String str=(String) holder.TermsName.getTag();
					 System.out.println("str"+str);
					 termsName.remove(str);
					 int i=termsName.size();       
					 System.out.println("size"+i);             
                     library.notifyDataSetChanged();
					 System.out.println(""+termsName);
					
				}    
			});
			holder.red_vertical.setOnClickListener(new OnClickListener(){
				
		    @Override
			 public void onClick(View v) {
					  holder.red_button.setVisibility(4);
					  holder.red_vertical.setVisibility(4);
					  holder.red.setVisibility(1);
		  	    }
			});
		 return view;
		}
	    
	    static class ViewHolder {
			TextView TermsName;
			ImageView red,red_vertical,green;
			FrameLayout red_button,addterm;
			Button save;
			EditText Terms;
		}
	}    
	 
	@Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
          if(keyCode == KeyEvent.KEYCODE_BACK) {
        	 finish();
          return false;
         }
        return super.onKeyDown(keyCode, event);
    }
	
	@Override
	public void onClick(View v){
		switch (v.getId()) {
		case R.id.btn_my_library_edit:
			String getButtonString=edit_list_btn.getText().toString();
			
			if(getButtonString.equals("Edit")){
				EditListFlag=1; 
				edit_list_btn.setText("Done");
			}else{
				EditListFlag=2; 
				edit_list_btn.setText("Edit");
			}
			
			                                     
			library=new MylibraryAdapter(MainActivity.this, termsName);
			 setListAdapter(library);
			break;                     
		
		
		default:
			break;
		}
	}
}
