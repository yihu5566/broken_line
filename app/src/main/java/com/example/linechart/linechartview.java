package com.example.linechart;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class linechartview extends View {

	private Paint paint;
	private Paint textpaint;
	private Paint hiddenpaint;
	private Canvas canvas;
	private int measuredWidth;
	private int measuredHeight;
	private int width;
	private int height;
	private int left;
	private int bottom;
	// �������
	private List<Map<String, Float>> mListPoint;

	// Զ������
	private int centrex;
	private int centrey;
	// x��y�᳤��
	private int xlong;
	private int ylong;
	// ������������������
	private int ynumber = 4;
	private String[] xnumber = new String[] { "1", "2", "3", "4" };
	// ���Ӧ�ø�����������������᳤����ȷ��
	private int jianjux;
	private int jianjuy;
	// ��ͼ�ü��
	private int drawx;
	// �Ƿ���ʾ���߱��
	private Boolean isGone = true;

	// ����ֵ��Ӧ��ͼ���е�λ��
	private float maSxy;
	private float scale;
	private float comparingrule;

	public void setMaSxy(float maSxy) {
		this.maSxy = maSxy;
	}

	// ((mListPoint.get(0).get("key_y")/ynumber.length)/ynumber.length)*ylong;
	public void setIsGone(Boolean isGone) {
		this.isGone = isGone;
	}

	public void setXnumber(String[] xnumber) {
		if (xnumber == null || xnumber.length == 0) {
			return;
		}
		this.xnumber = xnumber;
	}

	public void setYnumber(int ynumber) {
		if (ynumber == 0) {
			return;
		}
		this.ynumber = ynumber;
	}

	public void setmListPoint(List<Map<String, Float>> mListPoint) {
		this.mListPoint = mListPoint;
	}

	public linechartview(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	public linechartview(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public linechartview(Context context) {
		super(context);
		init();
	}

	private void init() {
		paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		textpaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		// ���߱�
		hiddenpaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		canvas = new Canvas();
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);

		measuredWidth = getMeasuredWidth();
		measuredHeight = getMeasuredHeight();

		left = getLeft();
		bottom = getBottom();

		width = getWidth();
		height = getHeight();

		initDate();

	}

	// ��ֵ
	private void initDate() {

		centrex = 50;
		centrey = height - 50;
		xlong = width - 80;
		ylong = height - 80;

		jianjux = xlong / xnumber.length;
		jianjuy = ylong / ynumber;
		drawx = xlong / xnumber.length;
		//������
		if (maSxy!=0) {
			
			comparingrule = ylong/maSxy; 
			
		}
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		if (maSxy != 0) {
			
			scale = maSxy/(float)ynumber;

		}
		// canvas.drawText("ceshi", centrex + jianjux, centrey - 50, textpaint);

		// ������x��y
		canvas.drawLine(centrex, centrey, centrex + xlong, centrey, paint);
		canvas.drawLine(centrex, centrey, centrex, centrey - ylong, paint);

		// ������x����
		for (int i = 0; i < xnumber.length; i++) {
			canvas.drawText(xnumber[i] + "", centrex + jianjux - 10,
					centrey + 20, textpaint);
			if (isGone) {
				// ������
				Path path = new Path();
				path.moveTo(centrex + jianjux, centrey - ylong);
				path.lineTo(centrex + jianjux, centrey);
				PathEffect effects = new DashPathEffect(new float[] { 1, 2, 4,
						8 }, 1);
				hiddenpaint.setPathEffect(effects);
				hiddenpaint.setStyle(Style.STROKE);
				canvas.drawPath(path, hiddenpaint);
			}

			// canvas.drawLine(getX() + jianjux, getY(), getX() + jianjux,
			// getY() - 200, hiddenpaint);
			jianjux = jianjux + xlong / xnumber.length;
		}
		// ������y�ϵ���
		for (int i = 0; i < ynumber; i++) {
			if (maSxy != 0) {
				canvas.drawText(scale + "", centrex - 30, centrey - jianjuy + 5,
						textpaint);
				scale=scale+maSxy/(float)ynumber;
				
			}else{
			canvas.drawText(jianjuy + "", centrex - 30, centrey - jianjuy + 5,
					textpaint);
			
			}
			if (isGone) {
				// ������
				Path path = new Path();
				path.moveTo(centrex, centrey - jianjuy);
				path.lineTo(centrex + xlong, centrey - jianjuy);
				PathEffect effects = new DashPathEffect(new float[] { 1, 2, 4,
						8 }, 1);
				hiddenpaint.setPathEffect(effects);
				hiddenpaint.setStyle(Style.STROKE);
				canvas.drawPath(path, hiddenpaint);
			}
			// canvas.drawLine(getX(), getY() - jianjuy, getX() + 200,getY() -
			// jianjuy, hiddenpaint);
			jianjuy = jianjuy + ylong / ynumber;
		}
		// ��������

		// canvas.save();
		// ������
		// �����ļ���

		paint.setStrokeWidth(2);
		paint.setColor(Color.RED);
		if (mListPoint != null && mListPoint.size() > 0) {
			for (int i = 1; i < mListPoint.size(); i++) {

				// �����۵�ԭ��

				canvas.drawCircle(centrex, (centrey
						- mListPoint.get(0).get("key_y")*comparingrule), 3, paint);

				canvas.drawCircle(centrex + drawx * i, (centrey
						- mListPoint.get(i).get("key_y")*comparingrule), 3, paint);

			
				// ��������
				canvas.drawLine(centrex + drawx * (i - 1), 
						(centrey- mListPoint.get(i - 1).get("key_y")*comparingrule),
						centrex + drawx* i, 
						(centrey- mListPoint.get(i).get("key_y")*comparingrule), paint);
				Log.i("view", comparingrule + "ssss");

			}

		}

		// canvas.restore();

	}

}
