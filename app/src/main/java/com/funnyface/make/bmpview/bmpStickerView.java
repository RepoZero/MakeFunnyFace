package com.funnyface.make.bmpview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ImageView;

import com.funnyface.make.bmpmodel.bmpStickerPropertyModel;
import com.funnyface.make.facefunny.R;

public class bmpStickerView extends ImageView {
    private static final String TAG = "StickerView";
    private static final float BITMAP_SCALE = 0.7f;
    private final float gpepointerLimitDis = 20f;
    private final float gpepointerZoomCoeff = 0.09f;
    private final long stickerId;
    private Bitmap bmpgpedeleteBitmap;
    private Bitmap bmpgpeflipVBitmap;
    private Bitmap bmpgpetopBitmap;
    private Bitmap bmpgperesizeBitmap;
    private Bitmap bmpgpemBitmap;
    private Rect bmpgpedst_delete;
    private Rect bmpgpedst_resize;
    private Rect bmpgpedst_flipV;
    private Rect bmpgpedst_top;
    private int bmpgpebmpgpedeleteBitmapWidth;
    private int bmpgpedeleteBitmapHeight;
    private int bmpgperesizeBitmapWidth;
    private int bmpgperesizeBitmapHeight;
    private int bmpgpeflipVBitmapWidth;
    private int bmpgpeflipVBitmapHeight;
    private int bmpgpetopBitmapWidth;
    private int bmpgpetopBitmapHeight;
    private Paint gpelocalPaint;
    private int gpemScreenwidth, gpemScreenHeight;
    private PointF gpemid = new PointF();
    private OperationListener operationListener;
    private float bmpgpelastRotateDegree;
    private boolean bmpgpeisPointerDown = false;
    private float gpelastLength;
    private boolean bmpgpeisInResize = false;
    private Matrix bmpgpematrix = new Matrix();
    private boolean bmpgpeisInSide;
    private float lastX, lastY;
    private boolean isInEdit = true;
    private float MIN_SCALE = 0.5f;
    private float MAX_SCALE = 1.2f;
    private double halfDiagonalLength;
    private float oringinWidth = 0;
    private float gpeoldDis;
    private DisplayMetrics dm;
    private boolean flag=false;
    private boolean isHorizonMirror = false;

    public bmpStickerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        stickerId = 0;
        init();
    }

    public bmpStickerView(Context context) {
        super(context);
        stickerId = 0;
        init();
    }

    public bmpStickerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        stickerId = 0;
        init();
    }

    private void init() {

        bmpgpedst_delete = new Rect();
        bmpgpedst_resize = new Rect();
        bmpgpedst_flipV = new Rect();
        bmpgpedst_top = new Rect();
        gpelocalPaint = new Paint();
        gpelocalPaint.setColor(getResources().getColor(R.color.red_e73a3d));
        gpelocalPaint.setAntiAlias(true);
        gpelocalPaint.setDither(true);
        gpelocalPaint.setStyle(Paint.Style.STROKE);
        gpelocalPaint.setStrokeWidth(2.0f);
        dm = getResources().getDisplayMetrics();
        gpemScreenwidth = dm.widthPixels;
        gpemScreenHeight = dm.heightPixels;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (bmpgpemBitmap != null) {


            float[] bmparrayOfFloat = new float[9];
            bmpgpematrix.getValues(bmparrayOfFloat);
            float f1 = 0.0F * bmparrayOfFloat[0] + 0.0F * bmparrayOfFloat[1] + bmparrayOfFloat[2];
            float f2 = 0.0F * bmparrayOfFloat[3] + 0.0F * bmparrayOfFloat[4] + bmparrayOfFloat[5];
            float f3 = bmparrayOfFloat[0] * this.bmpgpemBitmap.getWidth() + 0.0F * bmparrayOfFloat[1] + bmparrayOfFloat[2];
            float f4 = bmparrayOfFloat[3] * this.bmpgpemBitmap.getWidth() + 0.0F * bmparrayOfFloat[4] + bmparrayOfFloat[5];
            float f5 = 0.0F * bmparrayOfFloat[0] + bmparrayOfFloat[1] * this.bmpgpemBitmap.getHeight() + bmparrayOfFloat[2];
            float f6 = 0.0F * bmparrayOfFloat[3] + bmparrayOfFloat[4] * this.bmpgpemBitmap.getHeight() + bmparrayOfFloat[5];
            float f7 = bmparrayOfFloat[0] * this.bmpgpemBitmap.getWidth() + bmparrayOfFloat[1] * this.bmpgpemBitmap.getHeight() + bmparrayOfFloat[2];
            float f8 = bmparrayOfFloat[3] * this.bmpgpemBitmap.getWidth() + bmparrayOfFloat[4] * this.bmpgpemBitmap.getHeight() + bmparrayOfFloat[5];

            canvas.save();
            canvas.drawBitmap(bmpgpemBitmap, bmpgpematrix, null);
            bmpgpedst_delete.left = (int) (f3 - bmpgpebmpgpedeleteBitmapWidth / 2);
            bmpgpedst_delete.right = (int) (f3 + bmpgpebmpgpedeleteBitmapWidth / 2);
            bmpgpedst_delete.top = (int) (f4 - bmpgpedeleteBitmapHeight / 2);
            bmpgpedst_delete.bottom = (int) (f4 + bmpgpedeleteBitmapHeight / 2);
            bmpgpedst_resize.left = (int) (f7 - bmpgperesizeBitmapWidth / 2);
            bmpgpedst_resize.right = (int) (f7 + bmpgperesizeBitmapWidth / 2);
            bmpgpedst_resize.top = (int) (f8 - bmpgperesizeBitmapHeight / 2);
            bmpgpedst_resize.bottom = (int) (f8 + bmpgperesizeBitmapHeight / 2);
            bmpgpedst_top.left = (int) (f1 - bmpgpeflipVBitmapWidth / 2);
            bmpgpedst_top.right = (int) (f1 + bmpgpeflipVBitmapWidth / 2);
            bmpgpedst_top.top = (int) (f2 - bmpgpeflipVBitmapHeight / 2);
            bmpgpedst_top.bottom = (int) (f2 + bmpgpeflipVBitmapHeight / 2);
            bmpgpedst_flipV.left = (int) (f5 - bmpgpetopBitmapWidth / 2);
            bmpgpedst_flipV.right = (int) (f5 + bmpgpetopBitmapWidth / 2);
            bmpgpedst_flipV.top = (int) (f6 - bmpgpetopBitmapHeight / 2);
            bmpgpedst_flipV.bottom = (int) (f6 + bmpgpetopBitmapHeight / 2);
            if (isInEdit) {

                canvas.drawLine(f1, f2, f3, f4, gpelocalPaint);
                canvas.drawLine(f3, f4, f7, f8, gpelocalPaint);
                canvas.drawLine(f5, f6, f7, f8, gpelocalPaint);
                canvas.drawLine(f5, f6, f1, f2, gpelocalPaint);

                canvas.drawBitmap(bmpgpedeleteBitmap, null, bmpgpedst_delete, null);
                canvas.drawBitmap(bmpgperesizeBitmap, null, bmpgpedst_resize, null);
                canvas.drawBitmap(bmpgpeflipVBitmap, null, bmpgpedst_flipV, null);
                canvas.drawBitmap(bmpgpetopBitmap, null, bmpgpedst_top, null);
            }

            canvas.restore();
        }
    }

    @Override
    public void setImageResource(int resId) {
        setBitmap(BitmapFactory.decodeResource(getResources(), resId));
    }


    public void setBitmap(Bitmap bitmap) {
        bmpgpematrix.reset();
        bmpgpemBitmap = bitmap;
        bmpsetDiagonalLength();
        initBitmaps();
        int w = bmpgpemBitmap.getWidth();
        int h = bmpgpemBitmap.getHeight();
        oringinWidth = w;
        float initScale = (MIN_SCALE + MAX_SCALE) / 2;
        bmpgpematrix.postScale(initScale, initScale, w / 2, h / 2);
        bmpgpematrix.postTranslate(gpemScreenwidth / 2 - w / 2, (gpemScreenwidth) / 2 - h / 2);
        invalidate();
    }


    private void bmpsetDiagonalLength() {
        halfDiagonalLength = Math.hypot(bmpgpemBitmap.getWidth(), bmpgpemBitmap.getHeight()) / 2;
    }

    private void initBitmaps() {
        if (bmpgpemBitmap.getWidth() >= bmpgpemBitmap.getHeight()) {
            float minWidth = gpemScreenwidth / 8;
            if (bmpgpemBitmap.getWidth() < minWidth) {
                MIN_SCALE = 1f;
            } else {
                MIN_SCALE = 1.0f * minWidth / bmpgpemBitmap.getWidth();
            }

            if (bmpgpemBitmap.getWidth() > gpemScreenwidth) {
                MAX_SCALE = 1;
            } else {
                MAX_SCALE = 1.0f * gpemScreenwidth / bmpgpemBitmap.getWidth();
            }
        } else {
            float minHeight = gpemScreenwidth / 8;
            if (bmpgpemBitmap.getHeight() < minHeight) {
                MIN_SCALE = 1f;
            } else {
                MIN_SCALE = 1.0f * minHeight / bmpgpemBitmap.getHeight();
            }

            if (bmpgpemBitmap.getHeight() > gpemScreenwidth) {
                MAX_SCALE = 1;
            } else {
                MAX_SCALE = 1.0f * gpemScreenwidth / bmpgpemBitmap.getHeight();
            }
        }

        bmpgpetopBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.icon_top_enable);
        bmpgpedeleteBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.icon_delete);
        bmpgpeflipVBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.icon_flip);
        bmpgperesizeBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.icon_resize);

        bmpgpebmpgpedeleteBitmapWidth = (int) (bmpgpedeleteBitmap.getWidth() * BITMAP_SCALE);
        bmpgpedeleteBitmapHeight = (int) (bmpgpedeleteBitmap.getHeight() * BITMAP_SCALE);

        bmpgperesizeBitmapWidth = (int) (bmpgperesizeBitmap.getWidth() * BITMAP_SCALE);
        bmpgperesizeBitmapHeight = (int) (bmpgperesizeBitmap.getHeight() * BITMAP_SCALE);

        bmpgpeflipVBitmapWidth = (int) (bmpgpeflipVBitmap.getWidth() * BITMAP_SCALE);
        bmpgpeflipVBitmapHeight = (int) (bmpgpeflipVBitmap.getHeight() * BITMAP_SCALE);

        bmpgpetopBitmapWidth = (int) (bmpgpetopBitmap.getWidth() * BITMAP_SCALE);
        bmpgpetopBitmapHeight = (int) (bmpgpetopBitmap.getHeight() * BITMAP_SCALE);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
            int action = MotionEventCompat.getActionMasked(event);
            boolean handled = true;
        if(!flag)
        {
            switch (action) {
                case MotionEvent.ACTION_DOWN:
                    if (isInButton(event, bmpgpedst_delete)) {
                        if (operationListener != null) {
                            operationListener.onDeleteClick();
                        }
                    } else if (bmpgpeisInResize(event)) {
                        bmpgpeisInResize = true;
                        bmpgpelastRotateDegree = rotationToStartPoint(event);
                        gpemidPointToStartPoint(event);
                        gpelastLength = diagonalLength(event);
                    } else if (isInButton(event, bmpgpedst_flipV)) {
                        PointF localPointF = new PointF();
                        gpemidDiagonalPoint(localPointF);
                        bmpgpematrix.postScale(-1.0F, 1.0F, localPointF.x, localPointF.y);
                        isHorizonMirror = !isHorizonMirror;
                        invalidate();
                    } else if (isInButton(event, bmpgpedst_top)) {
                        bringToFront();
                        if (operationListener != null) {
                            operationListener.onTop(this);
                        }
                    } else if (isInBitmap(event)) {
                        bmpgpeisInSide = true;
                        lastX = event.getX(0);
                        lastY = event.getY(0);
                    } else {
                        handled = false;
                    }
                    break;
                case MotionEvent.ACTION_POINTER_DOWN:
                    if (spacing(event) > gpepointerLimitDis) {
                        gpeoldDis = spacing(event);
                        bmpgpeisPointerDown = true;
                        gpemidPointToStartPoint(event);
                    } else {
                        bmpgpeisPointerDown = false;
                    }
                    bmpgpeisInSide = false;
                    bmpgpeisInResize = false;
                    break;
                case MotionEvent.ACTION_MOVE:
                    if (bmpgpeisPointerDown) {
                        float scale;
                        float disNew = spacing(event);
                        if (disNew == 0 || disNew < gpepointerLimitDis) {
                            scale = 1;
                        } else {
                            scale = disNew / gpeoldDis;
                            scale = (scale - 1) * gpepointerZoomCoeff + 1;
                        }
                        float scaleTemp = (scale * Math.abs(bmpgpedst_flipV.left - bmpgpedst_resize.left)) / oringinWidth;
                        if (((scaleTemp <= MIN_SCALE)) && scale < 1 ||
                                (scaleTemp >= MAX_SCALE) && scale > 1) {
                            scale = 1;
                        } else {
                            gpelastLength = diagonalLength(event);
                        }
                        bmpgpematrix.postScale(scale, scale, gpemid.x, gpemid.y);
                        invalidate();
                    } else if (bmpgpeisInResize) {

                        bmpgpematrix.postRotate((rotationToStartPoint(event) - bmpgpelastRotateDegree) * 2, gpemid.x, gpemid.y);
                        bmpgpelastRotateDegree = rotationToStartPoint(event);

                        float scale = diagonalLength(event) / gpelastLength;

                        if (((diagonalLength(event) / halfDiagonalLength <= MIN_SCALE)) && scale < 1 ||
                                (diagonalLength(event) / halfDiagonalLength >= MAX_SCALE) && scale > 1) {
                            scale = 1;
                            if (!bmpgpeisInResize(event)) {
                                bmpgpeisInResize = false;
                            }
                        } else {
                            gpelastLength = diagonalLength(event);
                        }
                        bmpgpematrix.postScale(scale, scale, gpemid.x, gpemid.y);

                        invalidate();
                    } else if (bmpgpeisInSide) {
                        float x = event.getX(0);
                        float y = event.getY(0);
                        bmpgpematrix.postTranslate(x - lastX, y - lastY);
                        lastX = x;
                        lastY = y;
                        invalidate();
                    }
                    break;
                case MotionEvent.ACTION_CANCEL:
                case MotionEvent.ACTION_UP:
                    bmpgpeisInResize = false;
                    bmpgpeisInSide = false;
                    bmpgpeisPointerDown = false;
                    break;

            }
            if (handled && operationListener != null) {
                operationListener.onEdit(this);
            }
        }
        return handled;

    }


    public bmpStickerPropertyModel calculate(bmpStickerPropertyModel model) {
        float[] v = new float[9];
        bmpgpematrix.getValues(v);
        float tx = v[Matrix.MTRANS_X];
        float ty = v[Matrix.MTRANS_Y];
        Log.d(TAG, "tx : " + tx + " ty : " + ty);
        float scalex = v[Matrix.MSCALE_X];
        float skewy = v[Matrix.MSKEW_Y];
        float bmprScale = (float) Math.sqrt(scalex * scalex + skewy * skewy);
        Log.d(TAG, "bmprScale : " + bmprScale);
        float rAngle = Math.round(Math.atan2(v[Matrix.MSKEW_X], v[Matrix.MSCALE_X]) * (180 / Math.PI));
        Log.d(TAG, "rAngle : " + rAngle);

        PointF localPointF = new PointF();
        gpemidDiagonalPoint(localPointF);

        Log.d(TAG, " width  : " + (bmpgpemBitmap.getWidth() * bmprScale) + " height " + (bmpgpemBitmap.getHeight() * bmprScale));

        float minX = localPointF.x;
        float minY = localPointF.y;

        Log.d(TAG, "gpemidX : " + minX + " gpemidY : " + minY);
        model.setbmpdegree((float) Math.toRadians(rAngle));
        float bmpprecentWidth = (bmpgpemBitmap.getWidth() * bmprScale) / gpemScreenwidth;
        model.setbmpgpescaling(bmpprecentWidth);
        model.setbmpxLocation(minX / gpemScreenwidth);
        model.setbmpyLocation(minY / gpemScreenwidth);
        model.setStickerId(stickerId);
        if (isHorizonMirror) {
            model.seterhorizonMirror(1);
        } else {
            model.seterhorizonMirror(2);
        }
        return model;
    }


    private boolean isInBitmap(MotionEvent event) {
        float[] bmpbmparrayOfFloat1 = new float[9];
        this.bmpgpematrix.getValues(bmpbmparrayOfFloat1);
        float f1 = 0.0F * bmpbmparrayOfFloat1[0] + 0.0F * bmpbmparrayOfFloat1[1] + bmpbmparrayOfFloat1[2];
        float f2 = 0.0F * bmpbmparrayOfFloat1[3] + 0.0F * bmpbmparrayOfFloat1[4] + bmpbmparrayOfFloat1[5];
        float f3 = bmpbmparrayOfFloat1[0] * this.bmpgpemBitmap.getWidth() + 0.0F * bmpbmparrayOfFloat1[1] + bmpbmparrayOfFloat1[2];
        float f4 = bmpbmparrayOfFloat1[3] * this.bmpgpemBitmap.getWidth() + 0.0F * bmpbmparrayOfFloat1[4] + bmpbmparrayOfFloat1[5];
        float f5 = 0.0F * bmpbmparrayOfFloat1[0] + bmpbmparrayOfFloat1[1] * this.bmpgpemBitmap.getHeight() + bmpbmparrayOfFloat1[2];
        float f6 = 0.0F * bmpbmparrayOfFloat1[3] + bmpbmparrayOfFloat1[4] * this.bmpgpemBitmap.getHeight() + bmpbmparrayOfFloat1[5];
        float f7 = bmpbmparrayOfFloat1[0] * this.bmpgpemBitmap.getWidth() + bmpbmparrayOfFloat1[1] * this.bmpgpemBitmap.getHeight() + bmpbmparrayOfFloat1[2];
        float f8 = bmpbmparrayOfFloat1[3] * this.bmpgpemBitmap.getWidth() + bmpbmparrayOfFloat1[4] * this.bmpgpemBitmap.getHeight() + bmpbmparrayOfFloat1[5];

        float[] bmpbmparrayOfFloat2 = new float[4];
        float[] bmpbmparrayOfFloat3 = new float[4];
        bmpbmparrayOfFloat2[0] = f1;
        bmpbmparrayOfFloat2[1] = f3;
        bmpbmparrayOfFloat2[2] = f7;
        bmpbmparrayOfFloat2[3] = f5;
        bmpbmparrayOfFloat3[0] = f2;
        bmpbmparrayOfFloat3[1] = f4;
        bmpbmparrayOfFloat3[2] = f8;
        bmpbmparrayOfFloat3[3] = f6;
        return pointInRect(bmpbmparrayOfFloat2, bmpbmparrayOfFloat3, event.getX(0), event.getY(0));
    }


    private boolean pointInRect(float[] xRange, float[] yRange, float x, float y) {
        double a1 = Math.hypot(xRange[0] - xRange[1], yRange[0] - yRange[1]);
        double a2 = Math.hypot(xRange[1] - xRange[2], yRange[1] - yRange[2]);
        double a3 = Math.hypot(xRange[3] - xRange[2], yRange[3] - yRange[2]);
        double a4 = Math.hypot(xRange[0] - xRange[3], yRange[0] - yRange[3]);
        double b1 = Math.hypot(x - xRange[0], y - yRange[0]);
        double b2 = Math.hypot(x - xRange[1], y - yRange[1]);
        double b3 = Math.hypot(x - xRange[2], y - yRange[2]);
        double b4 = Math.hypot(x - xRange[3], y - yRange[3]);

        double u1 = (a1 + b1 + b2) / 2;
        double u2 = (a2 + b2 + b3) / 2;
        double u3 = (a3 + b3 + b4) / 2;
        double u4 = (a4 + b4 + b1) / 2;

        double s = a1 * a2;
        double ss = Math.sqrt(u1 * (u1 - a1) * (u1 - b1) * (u1 - b2))
                + Math.sqrt(u2 * (u2 - a2) * (u2 - b2) * (u2 - b3))
                + Math.sqrt(u3 * (u3 - a3) * (u3 - b3) * (u3 - b4))
                + Math.sqrt(u4 * (u4 - a4) * (u4 - b4) * (u4 - b1));
        return Math.abs(s - ss) < 0.5;


    }


    private boolean isInButton(MotionEvent event, Rect rect) {
        int left = rect.left;
        int right = rect.right;
        int top = rect.top;
        int bottom = rect.bottom;
        return event.getX(0) >= left && event.getX(0) <= right && event.getY(0) >= top && event.getY(0) <= bottom;
    }


    private boolean bmpgpeisInResize(MotionEvent event) {
        int left = -20 + this.bmpgpedst_resize.left;
        int top = -20 + this.bmpgpedst_resize.top;
        int right = 20 + this.bmpgpedst_resize.right;
        int bottom = 20 + this.bmpgpedst_resize.bottom;
        return event.getX(0) >= left && event.getX(0) <= right && event.getY(0) >= top && event.getY(0) <= bottom;
    }


    private void gpemidPointToStartPoint(MotionEvent event) {
        float[] bmparrayOfFloat = new float[9];
        bmpgpematrix.getValues(bmparrayOfFloat);
        float f1 = 0.0f * bmparrayOfFloat[0] + 0.0f * bmparrayOfFloat[1] + bmparrayOfFloat[2];
        float f2 = 0.0f * bmparrayOfFloat[3] + 0.0f * bmparrayOfFloat[4] + bmparrayOfFloat[5];
        float f3 = f1 + event.getX(0);
        float f4 = f2 + event.getY(0);
        gpemid.set(f3 / 2, f4 / 2);
    }


    private void gpemidDiagonalPoint(PointF paramPointF) {
        float[] bmparrayOfFloat = new float[9];
        this.bmpgpematrix.getValues(bmparrayOfFloat);
        float f1 = 0.0F * bmparrayOfFloat[0] + 0.0F * bmparrayOfFloat[1] + bmparrayOfFloat[2];
        float f2 = 0.0F * bmparrayOfFloat[3] + 0.0F * bmparrayOfFloat[4] + bmparrayOfFloat[5];
        float f3 = bmparrayOfFloat[0] * this.bmpgpemBitmap.getWidth() + bmparrayOfFloat[1] * this.bmpgpemBitmap.getHeight() + bmparrayOfFloat[2];
        float f4 = bmparrayOfFloat[3] * this.bmpgpemBitmap.getWidth() + bmparrayOfFloat[4] * this.bmpgpemBitmap.getHeight() + bmparrayOfFloat[5];
        float f5 = f1 + f3;
        float f6 = f2 + f4;
        paramPointF.set(f5 / 2.0F, f6 / 2.0F);
    }


    private float rotationToStartPoint(MotionEvent event) {

        float[] bmparrayOfFloat = new float[9];
        bmpgpematrix.getValues(bmparrayOfFloat);
        float x = 0.0f * bmparrayOfFloat[0] + 0.0f * bmparrayOfFloat[1] + bmparrayOfFloat[2];
        float y = 0.0f * bmparrayOfFloat[3] + 0.0f * bmparrayOfFloat[4] + bmparrayOfFloat[5];
        double arc = Math.atan2(event.getY(0) - y, event.getX(0) - x);
        return (float) Math.toDegrees(arc);
    }


    private float diagonalLength(MotionEvent event) {
        float diagonalLength = (float) Math.hypot(event.getX(0) - gpemid.x, event.getY(0) - gpemid.y);
        return diagonalLength;
    }


    private float spacing(MotionEvent event) {
        if (event.getPointerCount() == 2) {
            float x = event.getX(0) - event.getX(1);
            float y = event.getY(0) - event.getY(1);
            return (float) Math.sqrt(x * x + y * y);
        } else {
            return 0;
        }
    }

    public void setOperationListener(OperationListener operationListener) {
        this.operationListener = operationListener;
    }

    public void setInEdit(boolean isInEdit) {
        this.isInEdit = isInEdit;
        invalidate();
    }
     public void setenable(boolean flag) {
        this.flag=flag;
        invalidate();
    }

    public interface OperationListener {
        void onDeleteClick();

        void onEdit(bmpStickerView stickerView);

        void onTop(bmpStickerView stickerView);
    }
}
