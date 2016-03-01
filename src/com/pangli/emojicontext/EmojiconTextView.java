package com.pangli.emojicontext;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.DynamicDrawableSpan;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * 2016/2/25
 * @author pangli
 * 注：@—皇冠，#—钻石，￥—太阳，%—月亮，&—星星
 */
public class EmojiconTextView extends TextView {
	private int mCreditSize;
	private int mCreditAlignment;
	private int mCreditTextSize;
	private int mTextStart = 0;
	private int mTextLength = -1;

	public EmojiconTextView(Context context) {
		super(context);
		init(null);
	}

	public EmojiconTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(attrs);
	}

	public EmojiconTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(attrs);
	}

	private void init(AttributeSet attrs) {
		mCreditTextSize = (int) getTextSize();
		if (attrs == null) {
			mCreditSize = (int) getTextSize();
		} else {
			TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.Credit);
			mCreditSize = (int) a.getDimension(R.styleable.Credit_creditSize, getTextSize());
			mCreditAlignment = a.getInt(R.styleable.Credit_creditAlignment, DynamicDrawableSpan.ALIGN_BASELINE);
			mTextStart = a.getInteger(R.styleable.Credit_creditTextStart, 0);
			mTextLength = a.getInteger(R.styleable.Credit_creditTextLength, -1);
			a.recycle();
		}
		setText(getText());
	}

	@Override
	public void setText(CharSequence text, BufferType type) {
		if (!TextUtils.isEmpty(text)) {
			SpannableStringBuilder builder = new SpannableStringBuilder(text);
			EmojiconHandler.addEmojicon(getContext(), builder, mCreditSize, mCreditAlignment, mCreditTextSize,
					mTextStart, mTextLength);
			text = builder;
		}
		super.setText(text, type);
	}

	/**
	 * 设置图标大小
	 */
	public void setEmojiconSize(int pixels) {
		mCreditSize = pixels;
		super.setText(getText());
	}

}
