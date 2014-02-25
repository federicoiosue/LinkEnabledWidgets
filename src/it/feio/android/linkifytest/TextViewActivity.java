package it.feio.android.linkifytest;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.text.method.MovementMethod;
import android.view.View;
import android.widget.TextView;

//Here the Activity is implementing the TextLinkClickListener the one we have created
//the Clicks over the Links are forwarded over here from the LinkEnabledTextView

public class TextViewActivity extends Activity implements TextLinkClickListener {
	private LinkEnabledTextView textview;
	private LinkEnabledEditText edittext;

	protected void onCreate(Bundle savedInstance) {
		super.onCreate(savedInstance);
		setContentView(R.layout.activity_main);

		String text = "This is a #test of regular expressions with http://example.com/ links as used in @twitter for performing various operations based on the links this handles multiple links like http://this_is_fun.com/ and #Awesomess and @Cool";

//		textview = new LinkEnabledTextView(this, null);
		textview = (LinkEnabledTextView) findViewById(R.id.textview);
		textview.setOnTextLinkClickListener(this);
		textview.gatherLinksForText(text);
		
		edittext = (LinkEnabledEditText) findViewById(R.id.edittext);
		
		
		MovementMethod m = textview.getMovementMethod();
		if ((m == null) || !(m instanceof LinkMovementMethod)) {
			if (textview.getLinksClickable()) {
				textview.setMovementMethod(LinkMovementMethod.getInstance());
			}
		}

	}

	public void onTextLinkClick(View textView, String clickedString) {
		android.util.Log.v("Hyperlink clicked is :: " + clickedString,
				"Hyperlink clicked is :: " + clickedString);
	}
}