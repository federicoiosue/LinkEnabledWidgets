package it.feio.android.linkifytest;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.method.LinkMovementMethod;
import android.text.method.MovementMethod;
import android.view.View;
import android.widget.Toast;

//Here the Activity is implementing the TextLinkClickListener the one we have created
//the Clicks over the Links are forwarded over here from the LinkEnabledTextView

public class TextViewActivity extends Activity implements TextLinkClickListener {
	private LinkEnabledTextView textview;
	private LinkEnabledEditText edittext;
	private SharedPreferences prefs;
	private TextViewActivity mContext;

	protected void onCreate(Bundle savedInstance) {
		super.onCreate(savedInstance);
		setContentView(R.layout.activity_main);
		mContext = this;
		prefs = PreferenceManager.getDefaultSharedPreferences(this);

		String text = "This is a #test of regular expressions with http://example.com/ links as used in @twitter for performing various operations based on the links this handles multiple links like http://this_is_fun.com/ and #Awesomess and @Cool";

		// textview = new LinkEnabledTextView(this, null);
		textview = (LinkEnabledTextView) findViewById(R.id.textview);
		textview.setOnTextLinkClickListener(this);
		textview.gatherLinksForText(text);

		edittext = (LinkEnabledEditText) findViewById(R.id.edittext);
		edittext.setText(prefs.getString("edittextstring", ""));
		edittext.gatherLinksForText();
		edittext.setOnTextLinkClickListener(this);

		MovementMethod m = textview.getMovementMethod();
		if ((m == null) || !(m instanceof LinkMovementMethod)) {
			if (textview.getLinksClickable()) {
				textview.setMovementMethod(LinkMovementMethod.getInstance());
			}
		}

	}

	public void onTextLinkClick(View textView, String clickedString) {
		Toast.makeText(mContext, "Hyperlink clicked is :: " + clickedString, Toast.LENGTH_SHORT).show();
	}

	@Override
	protected void onPause() {
		prefs.edit().putString("edittextstring", edittext.getText().toString())
				.commit();
		super.onPause();
	}

}