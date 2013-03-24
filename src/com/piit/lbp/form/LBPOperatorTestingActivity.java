package com.piit.lbp.form;


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.util.Log;
import android.widget.TextView;


public class LBPOperatorTestingActivity  {
    private static final String TAG = null;


	/** Called when the activity is first created. */
    
    
    TextView tv ;


	private static int CumulativeHistogram[] = new int[256];


	private static int HistogramLUT[] = new int[256];


	private static int HistogramGrayscale[] = new int[256];
	
    static int bmpGrayscale[][] = new int[100][100];

    private static int width;
	private static int height;



	private static int count;
    public static int offset;
	private static int uniform[] = {0,1,2,3,4,6,7,8,12,14,15,16,24,28,30,31,32,48,56,60,62,63,64,96,112,120,124,126,127,128,129,131,135,143,159,191,192,193,195,199,207,223,224,225,227,231,239,240,241,243,247,248,249,251,252,253,254,255};
    static int Histogram[] = new int[2872];
	
	
   
    public static void toGrayscale (Bitmap bmpOriginal){
		width = bmpOriginal.getWidth();
		System.out.println(width);
		height = bmpOriginal.getHeight();
    	System.out.println(height);
    	
    	
    	int r,g,b,A;
	
    	for (int i =0;i<width;++i){
            for(int j=0;j<height;++j){
                    
                    r = Color.red(bmpOriginal.getPixel(i,j));
                    g = Color.green(bmpOriginal.getPixel(i,j));
                    b = Color.blue(bmpOriginal.getPixel(i,j));
                    A = (int)((r+g+b)/3);
                    bmpGrayscale[i][j] = A;
                    HistogramGrayscale[A]++;
            }
        }
    	
    	/*	
	    bmpGrayscale = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
	    Canvas c = new Canvas(bmpGrayscale);
	    Paint paint = new Paint();
	    ColorMatrix cm = new ColorMatrix();
	    cm.setSaturation(0);
	    ColorMatrixColorFilter f = new ColorMatrixColorFilter(cm);
	    paint.setColorFilter(f);
	    c.drawBitmap(bmpOriginal, 0, 0, paint);
	    */

    	Log.d(TAG, "Converted Image to GrayScale");	
    	bmpOriginal.recycle();

	    
    	   	
    }
    
    public static void featureVectorExtraction(Bitmap bmpOriginal){
    	
    	toGrayscale(bmpOriginal);
    	HistogramEqualization();
    	
    	    	
    	for(int i=1;i<width-1;i++)
	    	for(int j=1;j<height-1;j++)
	    	{
	    		count=0;
	    		if (bmpGrayscale[i-1][j-1] > bmpGrayscale[i][j])
	    			count=count+1;
	    		if (bmpGrayscale[i-1][j] > bmpGrayscale[i][j])
	    			count=count+2;
	    		if (bmpGrayscale[i-1][j+1] > bmpGrayscale[i][j])
	    			count=count+4;
	    		if (bmpGrayscale[i][j-1] > bmpGrayscale[i][j])
	    			count=count+8;
	    		if (bmpGrayscale[i][j+1] > bmpGrayscale[i][j])
	    			count=count+16;
	    		if (bmpGrayscale[i+1][j-1] > bmpGrayscale[i][j])
	    			count=count+32;
	    		if (bmpGrayscale[i+1][j] > bmpGrayscale[i][j])
	    			count=count+64;
	    		if (bmpGrayscale[i+1][j+1] > bmpGrayscale[i][j])
	    			count=count+128;
	    		
	    		Histogram[count]++;
	    		
	    	}
	    offset=256;
	    
    	Log.d(TAG, "Histogram(8,1) Generated for 1 Level Image");	
	    
	    
	    UniformOperator81(0,height/2,0,width/2);
	    UniformOperator81(0,height/2,width/2,width);
	    UniformOperator81(height/2,height,0,width/2);
	    UniformOperator81(height/2,height,width/2,width);
	    
    	Log.d(TAG, "Histogram(8,1) Generated for 2 level Image");	
    	
	    UniformOperator81(0,height/4,0,width/4);
	    UniformOperator81(0,height/4,width/4,width/2);
	    UniformOperator81(0,height/4,width/2,3*width/4);
	    UniformOperator81(0,height/4,3*width/4,width);

	    UniformOperator81(height/4,height/2,0,width/4);
	    UniformOperator81(height/4,height/2,width/4,width/2);
	    UniformOperator81(height/4,height/2,width/2,3*width/4);
	    UniformOperator81(height/4,height/2,3*width/4,width);
	    
	    UniformOperator81(height/2,3*height/4,0,width/4);
	    UniformOperator81(height/2,3*height/4,width/4,width/2);
	    UniformOperator81(height/2,3*height/4,width/2,3*width/4);
	    UniformOperator81(height/2,3*height/4,3*width/4,width);
	    
	    UniformOperator81(3*height/4,height,0,width/4);
	    UniformOperator81(3*height/4,height,width/4,width/2);
	    UniformOperator81(3*height/4,height,width/2,3*width/4);
	    UniformOperator81(3*height/4,height,3*width/4,width);
	    
		
	    Log.d(TAG, "Histogram(8,1) Generated for 3 level Image");	

    	for(int i=2;i<width-2;i++)
	    	for(int j=2;j<height-2;j++)
	    	{
	    		count=0;
	    		if (bmpGrayscale[i-2][j-2] > bmpGrayscale[i][j])
	    			count=count+1;
	    		if (bmpGrayscale[i-2][j] > bmpGrayscale[i][j])
	    			count=count+2;
	    		if (bmpGrayscale[i-2][j+2] > bmpGrayscale[i][j])
	    			count=count+4;
	    		if (bmpGrayscale[i][j-2] > bmpGrayscale[i][j])
	    			count=count+8;
	    		if (bmpGrayscale[i][j+2] > bmpGrayscale[i][j])
	    			count=count+16;
	    		if (bmpGrayscale[i+2][j-2] > bmpGrayscale[i][j])
	    			count=count+32;
	    		if (bmpGrayscale[i+2][j] > bmpGrayscale[i][j])
	    			count=count+64;
	    		if (bmpGrayscale[i+2][j+2] > bmpGrayscale[i][j])
	    			count=count+128;
	    		
	    		Histogram[offset+count]++;
	    		
	    	}
	    offset=offset+256;
	    
    	Log.d(TAG, "Histogram(8,2) Generated for 1 Level Image");	

	    
	    UniformOperator82(0,height/2,0,width/2);
	    UniformOperator82(0,height/2,width/2,width);
	    UniformOperator82(height/2,height,0,width/2);
	    UniformOperator82(height/2,height,width/2,width);
	    
    	Log.d(TAG, "Histogram(8,2) Generated for 2 level Image");	
    	
	    UniformOperator82(0,height/4,0,width/4);
	    UniformOperator82(0,height/4,width/4,width/2);
	    UniformOperator82(0,height/4,width/2,3*width/4);
	    UniformOperator82(0,height/4,3*width/4,width);

	    UniformOperator82(height/4,height/2,0,width/4);
	    UniformOperator82(height/4,height/2,width/4,width/2);
	    UniformOperator82(height/4,height/2,width/2,3*width/4);
	    UniformOperator82(height/4,height/2,3*width/4,width);
	    
	    UniformOperator82(height/2,3*height/4,0,width/4);
	    UniformOperator82(height/2,3*height/4,width/4,width/2);
	    UniformOperator82(height/2,3*height/4,width/2,3*width/4);
	    UniformOperator82(height/2,3*height/4,3*width/4,width);
	    
	    UniformOperator82(3*height/4,height,0,width/4);
	    UniformOperator82(3*height/4,height,width/4,width/2);
	    UniformOperator82(3*height/4,height,width/2,3*width/4);
	    UniformOperator82(3*height/4,height,3*width/4,width);
    	
	    Log.d(TAG, "Histogram(8,2) Generated for 3 level Image");
    	Log.d(TAG, ""+offset);

    }
    public static void HistogramReset(){
    	for(int i=0;i<Histogram.length;i++)
    	{
    		Histogram[i]=0;
    	}
    	Log.d(TAG, "Histogram Resetted");	

    }
    public static void UniformOperator81 (int start1,int end1,int start2,int end2) 
    {
	    int count;
    	for(int i=start2+2;i<end2-2;i++)
	    	for(int j=start1+2;j<end1-2;j++) 
	    	{
	    		count=0;
	    		if (bmpGrayscale[i-1][j-1] > bmpGrayscale[i][j])
	    			count=count+1;
	    		if (bmpGrayscale[i-1][j] > bmpGrayscale[i][j])
	    			count=count+2;
	    		if (bmpGrayscale[i-1][j+1] > bmpGrayscale[i][j])
	    			count=count+4;
	    		if (bmpGrayscale[i][j-1] > bmpGrayscale[i][j])
	    			count=count+8;
	    		if (bmpGrayscale[i][j+1] > bmpGrayscale[i][j])
	    			count=count+16;
	    		if (bmpGrayscale[i+1][j-1] > bmpGrayscale[i][j])
	    			count=count+32;
	    		if (bmpGrayscale[i+1][j] > bmpGrayscale[i][j])
	    			count=count+64;
	    		if (bmpGrayscale[i+1][j+1] > bmpGrayscale[i][j])
	    			count=count+128;
	    		

	    		for(int k=0;k<uniform.length;k++)
	    			if(uniform[k]==count)
	    			{
	    				Histogram[k+offset+1]++;
	    				break;
	   
	    			}
	    			else
	    				Histogram[offset]++;

	
	    	}
	    offset=offset+59;
    }
    public static void UniformOperator82 (int start1,int end1,int start2,int end2) 
    {
	    int count;
    	for(int i=start2+2;i<end2-2;i++)
	    	for(int j=start1+2;j<end1-2;j++) 
	    	{
	    		count=0;
	    		if (bmpGrayscale[i-2][j-2] > bmpGrayscale[i][j])
	    			count=count+1;
	    		if (bmpGrayscale[i-2][j] > bmpGrayscale[i][j])
	    			count=count+2;
	    		if (bmpGrayscale[i-2][j+2] > bmpGrayscale[i][j])
	    			count=count+4;
	    		if (bmpGrayscale[i][j-2] > bmpGrayscale[i][j])
	    			count=count+8;
	    		if (bmpGrayscale[i][j+2] > bmpGrayscale[i][j])
	    			count=count+16;
	    		if (bmpGrayscale[i+2][j-2] > bmpGrayscale[i][j])
	    			count=count+32;
	    		if (bmpGrayscale[i+2][j] > bmpGrayscale[i][j])
	    			count=count+64;
	    		if (bmpGrayscale[i+2][j+2] > bmpGrayscale[i][j])
	    			count=count+128;
	    		

	    		for(int k=0;k<uniform.length;k++)
	    			if(uniform[k]==count)
	    			{
	    				Histogram[k+offset+1]++;
	    				break;
	   
	    			}
	    			else
	    				Histogram[offset]++;

	
	    	}
	    offset=offset+59;
    }
    
    public static void HistogramEqualization()
    {
    	float temp=0,temp1=0;
    	int pixel;
    	CumulativeHistogram[0]=HistogramGrayscale[0];
    	for(int i=1;i<256;i++){
    			CumulativeHistogram[i] = CumulativeHistogram[i-1]+HistogramGrayscale[i];
    			
    		}
    	
    	System.out.println("Cumulutaive Density Function Calculated");
    	for(int i=1;i<256;i++){
			
    		temp = CumulativeHistogram[i]*256/(width*height);
    		temp1 = temp-(int) temp;
    		if(temp1>0.5)
    			HistogramLUT[i] =(int)temp+1;
    		else
    			HistogramLUT[i] =(int)temp+1;
			
		}
    	System.out.println("Lookup Table Generated");
    	for(int i=0;i<width;i++)
    		for (int j=0;j<height;j++){
    			pixel=bmpGrayscale[i][j];
    			//Set new pixel value
    			bmpGrayscale[i][j]=HistogramLUT[pixel];
    		}
    	System.out.println("bmpGrayscale Equalized");		
    			
    		
    }
    
  /*  
    public static double chiSquareClassifier(int Hist1[], int Hist2[])
    {
    	System.out.println(Hist1.length);
    	System.out.println(Hist2[49]);
    	
    	double chiSquare = 0.0;
    	
        long temp,temp1;
        double temp2;
     	
         for(int i=0;i<offset;i++)
 		{
     		

     		if(Hist2[i] == 0 && Hist1[i] == 0)
 				continue;
 			else
 			{
 			temp = Hist2[i]-Hist1[i];
 			temp = temp*temp;
 			temp1 = Hist2[i]+Hist1[i];
 			temp2= temp/temp1;
 			chiSquare = chiSquare + temp2;
 			}

		}

    	return chiSquare;
    	    
    }*/
}