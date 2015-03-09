package view;

import java.sql.SQLException;
import java.util.List;
import java.util.Random;

import com.example.virus.R;

import bdd.Comment;
import bdd.CommentsDataSource;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

public class DatabaseActivity extends ListActivity{
	
	private CommentsDataSource datasource;

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bdd);
		
		datasource = new CommentsDataSource(this);
		try {
			datasource.open();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		List<Comment> values = datasource.getAllComments();
		ArrayAdapter<Comment> adapter = new ArrayAdapter<Comment>(this, android.R.layout.simple_list_item_1, values);
		setListAdapter(adapter);
	}
	
	public void onClick(View view){
		@SuppressWarnings("unchecked")
		ArrayAdapter<Comment> adapter = (ArrayAdapter<Comment>) getListAdapter();
		Comment comment = null;
		if(getListAdapter().getCount() > 0){
				comment = (Comment) getListAdapter().getItem(0);
				datasource.deleteComment(comment);
				adapter.remove(comment);
		}
		adapter.notifyDataSetChanged();
	}
	
	@Override
	protected void onResume() {
		try {
			datasource.open();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		super.onResume();
	}
	
	@Override
	protected void onPause() {
		datasource.close();
		super.onPause();
	}
}
