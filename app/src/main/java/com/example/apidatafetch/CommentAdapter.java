package com.example.apidatafetch;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class CommentAdapter extends BaseAdapter {

    final String TAG = "CommentAdapter";
    Context context;
    List<Comment> commentList;

    public CommentAdapter(Context context,List<Comment> commentList){
        this.context = context;
        this.commentList = commentList;
    }


    @Override
    public int getCount() {
        return commentList.size();
    }

    @Override
    public Object getItem(int position) {
        return commentList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        TextView textViewPostId;
        TextView textViewId;
        TextView textViewName;
        TextView textViewEmail;
        TextView textVieBody;

        if(convertView == null)
            convertView = LayoutInflater.from(context).inflate(R.layout.comment_item,parent,false);

        Comment currentComment = (Comment) getItem(position);

        textViewId = convertView.findViewById(R.id.tect_id);
        textViewName = convertView.findViewById(R.id.text_name);
        textViewEmail = convertView.findViewById(R.id.text_email);
        textVieBody = convertView.findViewById(R.id.text_body);


        textViewId.setText(currentComment.getId());
        textViewName.setText(currentComment.getName());
        textViewEmail.setText(currentComment.getEmail());
        textVieBody.setText(currentComment.getBody());

        return convertView;

    }
}
