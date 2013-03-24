package com.piit.lbp.form;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import android.graphics.PointF;
import android.graphics.Rect;
import android.media.FaceDetector;

import android.util.Log;
import android.view.View;
import android.widget.Toast;


public class FaceView extends View {
	private static final int NUM_FACES = 1; // max is 64
	private static final boolean DEBUG = true;

	private FaceDetector arrayFaces;
	private FaceDetector.Face getAllFaces[] = new FaceDetector.Face[NUM_FACES];
	private FaceDetector.Face getFace = null;
	
	private PointF eyesMidPts[] = new PointF[NUM_FACES];
	private float  eyesDistance[] = new float[NUM_FACES];
	
//	private final static float MAG_EYEDIST_FACE		= 3.0f;
//	private final static float MAG_EYEDIST_FACE1 	= 2.0f;
	private static final String TAG = null;
	

	private Bitmap scaledImage;
	
	private Paint tmpPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

	
	private int picWidth, picHeight;
	public static int NO_FACE;
	public static Bitmap croppedImage;

	
	public FaceView(Context context) {
		super(context);

		tmpPaint.setStyle(Paint.Style.STROKE);
		tmpPaint.setTextAlign(Paint.Align.CENTER);

		

		

		picWidth = LBPRequestMatch.sourceImage.getWidth();
		picHeight = LBPRequestMatch.sourceImage.getHeight();
		
		Log.d(TAG, "width"+picWidth+"Height"+picHeight);
		
		arrayFaces = new FaceDetector( picWidth, picHeight, NUM_FACES );
		arrayFaces.findFaces(LBPRequestMatch.sourceImage, getAllFaces);
		
		for (int i = 0; i < getAllFaces.length; i++)
		{
			getFace = getAllFaces[i];
			try {
				PointF eyesMP = new PointF();
				getFace.getMidPoint(eyesMP);
				eyesDistance[i] = getFace.eyesDistance();
				eyesMidPts[i] = eyesMP;
				
				if (DEBUG)
				{
					Log.i("Face",
						i +  " " + getFace.confidence() + " " + getFace.eyesDistance() + " "
						+ "Pose: ("+ getFace.pose(FaceDetector.Face.EULER_X) + ","
						+ getFace.pose(FaceDetector.Face.EULER_Y) + ","
						+ getFace.pose(FaceDetector.Face.EULER_Z) + ")"
						+ "Eyes Midpoint: ("+eyesMidPts[i].x + "," + eyesMidPts[i].y +")"
					);
				}
			}
			catch (Exception e)
			{
				if (DEBUG) Log.e("Face", i + " is null");
				if(i==0){
					NO_FACE=1;
				}
			}
			
			int x=(int) (eyesMidPts[i].x-eyesDistance[i]);
			int y = (int) (eyesMidPts[i].y-eyesDistance[i]);
			if(x<0)
				x=0;
			if(y<0)
				y=0;
						
			int width = (int) (2 * eyesDistance[i]);
			int height = (int) (2.5 * eyesDistance[i]);
			int newWidth=x+width;
			int newHeight= y+height;
			Log.d(TAG, width + " "+ height);
			if(newWidth>picWidth)
				width=picWidth-1;
			if(newHeight>picHeight)
				height=picHeight-1;
			try
			{
			scaledImage = Bitmap.createBitmap(LBPRequestMatch.sourceImage, x, y, width, height);
		
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		LBPRequestMatch.sourceImage.recycle();
		croppedImage = Bitmap.createScaledBitmap(scaledImage, 100, 100, true);
		scaledImage.recycle();
	}

	@Override
	protected void onDraw(Canvas canvas)
	{

		canvas.drawBitmap( croppedImage, null , new Rect(0,0,croppedImage.getWidth(),croppedImage.getHeight()),tmpPaint);

	}

}



