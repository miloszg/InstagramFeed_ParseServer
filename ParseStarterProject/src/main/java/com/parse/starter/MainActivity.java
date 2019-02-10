/*
 * Copyright (c) 2015-present, Parse, LLC.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */
package com.parse.starter;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Switch;

import com.parse.GetCallback;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseAnonymousUtils;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;


public class MainActivity extends AppCompatActivity {


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

      ParseObject tweet = new ParseObject("Tweet");
      tweet.put("username","Apollo");
      tweet.put("tweet","Where the bitchez @????");

      tweet.saveInBackground(new SaveCallback() {
          @Override
          public void done(ParseException e) {
              if(e==null){
                  Log.i("Saving","Complete");
              } else {
                  e.printStackTrace();
              }
          }
      });

      ParseQuery<ParseObject> tweet_query=ParseQuery.getQuery("Tweet");
      tweet_query.getInBackground("V3ekuftW8d", new GetCallback<ParseObject>() {
          @Override
          public void done(ParseObject parseObject, ParseException e) {
              if(e==null & parseObject!=null){
                  Log.i("username",parseObject.getString("username"));
                  Log.i("tweet",parseObject.getString("tweet"));

                  parseObject.put("tweet","I humbly apologize for the previous tweet. The goal of my content is to entertain others");
                  parseObject.saveInBackground();
              } else {
                  e.printStackTrace();
              }
          }
      });



    ParseAnalytics.trackAppOpenedInBackground(getIntent());
  }

}