
package com.pangli.emojicontext;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.text.Spannable;

/**
 * 2016/2/25
 * @author pangli
 */
public final class EmojiconHandler {

	private static Map<String, Integer> sEmojiconDrawableMap = new HashMap<String, Integer>();

	static {
		sEmojiconDrawableMap.put("@", R.drawable.credit_crown);
		sEmojiconDrawableMap.put("#", R.drawable.credit_diamond);
		sEmojiconDrawableMap.put("$", R.drawable.credit_ooopic);
		sEmojiconDrawableMap.put("%", R.drawable.credit_half_moo);
		sEmojiconDrawableMap.put("&", R.drawable.credit_star);
	}

	private static int getSoftbankCreditResource(char c) {
		if (sEmojiconDrawableMap.containsKey(String.valueOf(c))) {
			return sEmojiconDrawableMap.get(String.valueOf(c));
		}
		return 0;
	}

	/**
	 *
	 * @param context
	 * @param text
	 * @param emojiSize
	 * @param emojiAlignment
	 * @param textSize
	 * @param index
	 * @param length
	 */
	public static void addEmojicon(Context context, Spannable text, int emojiSize, int emojiAlignment, int textSize,
			int index, int length) {
		int textLength = text.length();
		int textLengthToProcessMax = textLength - index;
		int textLengthToProcess = length < 0 || length >= textLengthToProcessMax ? textLength : (length + index);
		EmojiconSpan[] oldSpans = text.getSpans(0, textLength, EmojiconSpan.class);
		for (int i = 0; i < oldSpans.length; i++) {
			text.removeSpan(oldSpans[i]);
		}
		for (int i = index; i < textLengthToProcess; i++) {
			int icon = 0;
			char c = text.charAt(i);
			icon = getSoftbankCreditResource(c);
			if (icon > 0) {
				text.setSpan(new EmojiconSpan(context, icon, emojiSize, emojiAlignment, textSize), i, i + 1,
						Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			}
		}
	}

}
