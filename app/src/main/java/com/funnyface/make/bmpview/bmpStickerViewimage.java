package com.funnyface.make.bmpview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.widget.ImageView;

import com.funnyface.make.bmpmodel.bmpStickerPropertyModel;
import com.funnyface.make.facefunny.R;

public class bmpStickerViewimage extends ImageView {
    private static final String TAG = "StickerView";
    private static final float BITMAP_SCALE = 0.7f;
    private final float pointerLimitDis = 20f;
    private final float pointerZoomCoeff = 0.09f;
    private final long stickerId;
    int bmpproA = 255;
    int bmpproB;
    int bmpproC;
    ColorMatrixColorFilter gpefiltr;
    ColorMatrix gpenewMatrix;
    Paint gpepaint = new Paint();
    private Bitmap bmpgpeflipVBitmap;
    private Bitmap bmpgpetopBitmap;
    private Bitmap bmpgperesizeBitmap;
    private Bitmap bmpgpemBitmap;
    private Rect bmpgpedst_delete;
    private Rect bmpgpedst_resize;
    private Rect bmpgpedst_flipV;
    private Rect bmpgpedst_top;
    private int gpedeleteBitmapWidth;
    private int gpedeleteBitmapHeight;
    private int bmpbmpgperesizeBitmapWidth;
    private int bmpbmpgperesizeBitmapHeight;
    private int bmpbmpgpeflipVBitmapWidth;
    private int bmpbmpgpeflipVBitmapHeight;
    private int bmpbmpgpetopBitmapWidth;
    private int bmpbmpgpetopBitmapHeight;
    private Paint bmpgpelocalPaint;
    private int gpemScreenwidth, gpemScreenHeight;
    private PointF mid = new PointF();
    private OperationListener operationListener;
    private float lastRotateDegree;
    private boolean bmpisPointerDown = false;
    private float gpelastLength;
    private boolean isInResize = false;
    private Matrix bmpmatrix = new Matrix();
    private boolean bmpgpeisInSide;
    private float bmplastX, lastY;
    private boolean isInEdit = true;
    private float MIN_SCALE = 0.5f;
    private float MAX_SCALE = 1.2f;
    private double halfDiagonalLength;
    private float oringinWidth = 0;
    private float bmpgpeoldDis;
    private DisplayMetrics dm;
    private boolean bmpisHorizonMirror = false;
    boolean flag=false;
Bitmap orignalbitmap;
    public bmpStickerViewimage(Context context, AttributeSet attrs) {
        super(context, attrs);
        stickerId = 0;
        init();
    }

    public bmpStickerViewimage(Context context) {
        super(context);
        stickerId = 0;
        init();
    }

    public bmpStickerViewimage(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        stickerId = 0;
        init();
    }

    private void init() {

        bmpgpedst_delete = new Rect();
        bmpgpedst_resize = new Rect();
        bmpgpedst_flipV = new Rect();
        bmpgpedst_top = new Rect();
        bmpgpelocalPaint = new Paint();
        bmpgpelocalPaint.setColor(getResources().getColor(R.color.red_e73a3d));
        bmpgpelocalPaint.setAntiAlias(true);
        bmpgpelocalPaint.setDither(true);
        bmpgpelocalPaint.setStyle(Paint.Style.STROKE);
        bmpgpelocalPaint.setStrokeWidth(2.0f);
        dm = getResources().getDisplayMetrics();
        gpemScreenwidth = dm.widthPixels;
        gpemScreenHeight = dm.heightPixels;
        gpenewMatrix = new ColorMatrix();
        gpefiltr = new ColorMatrixColorFilter(gpenewMatrix);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        if (bmpgpemBitmap != null) {

            gpenewMatrix.set(new float[]{
                    1, 0, 0, 0, bmpproB,
                    0, 1, 0, 0, bmpproB,
                    0, 0, 1, 0, bmpproB,
                    0, 0, 0, 1, 0});

            float input = bmpproC / (float) 100;
            float gpebmpscale = input + 1f;
            float contrast = 128.0F + -128.0F * gpebmpscale + bmpproB;

            gpenewMatrix.set(new float[]{
                    gpebmpscale, 0, 0, 0, contrast,
                    0, gpebmpscale, 0, 0, contrast,
                    0, 0, gpebmpscale, 0, contrast,
                    0, 0, 0, 1, 0});

            gpefiltr = new ColorMatrixColorFilter(gpenewMatrix);
            this.gpepaint.setColorFilter(this.gpefiltr);
            this.gpepaint.setAlpha(bmpproA);


            float[] bmparrayOfFloat = new float[9];
            bmpmatrix.getValues(bmparrayOfFloat);
            float f1 = 0.0F * bmparrayOfFloat[0] + 0.0F * bmparrayOfFloat[1] + bmparrayOfFloat[2];
            float f2 = 0.0F * bmparrayOfFloat[3] + 0.0F * bmparrayOfFloat[4] + bmparrayOfFloat[5];
            float f3 = bmparrayOfFloat[0] * this.bmpgpemBitmap.getWidth() + 0.0F * bmparrayOfFloat[1] + bmparrayOfFloat[2];
            float f4 = bmparrayOfFloat[3] * this.bmpgpemBitmap.getWidth() + 0.0F * bmparrayOfFloat[4] + bmparrayOfFloat[5];
            float f5 = 0.0F * bmparrayOfFloat[0] + bmparrayOfFloat[1] * this.bmpgpemBitmap.getHeight() + bmparrayOfFloat[2];
            float f6 = 0.0F * bmparrayOfFloat[3] + bmparrayOfFloat[4] * this.bmpgpemBitmap.getHeight() + bmparrayOfFloat[5];
            float f7 = bmparrayOfFloat[0] * this.bmpgpemBitmap.getWidth() + bmparrayOfFloat[1] * this.bmpgpemBitmap.getHeight() + bmparrayOfFloat[2];
            float f8 = bmparrayOfFloat[3] * this.bmpgpemBitmap.getWidth() + bmparrayOfFloat[4] * this.bmpgpemBitmap.getHeight() + bmparrayOfFloat[5];

            canvas.save();
            canvas.drawBitmap(bmpgpemBitmap, bmpmatrix, this.gpepaint);
            bmpgpedst_delete.left = (int) (f3 - gpedeleteBitmapWidth / 2);
            bmpgpedst_delete.right = (int) (f3 + gpedeleteBitmapWidth / 2);
            bmpgpedst_delete.top = (int) (f4 - gpedeleteBitmapHeight / 2);
            bmpgpedst_delete.bottom = (int) (f4 + gpedeleteBitmapHeight / 2);
            bmpgpedst_resize.left = (int) (f7 - bmpbmpgperesizeBitmapWidth / 2);
            bmpgpedst_resize.right = (int) (f7 + bmpbmpgperesizeBitmapWidth / 2);
            bmpgpedst_resize.top = (int) (f8 - bmpbmpgperesizeBitmapHeight / 2);
            bmpgpedst_resize.bottom = (int) (f8 + bmpbmpgperesizeBitmapHeight / 2);
            bmpgpedst_top.left = (int) (f1 - bmpbmpgpeflipVBitmapWidth / 2);
            bmpgpedst_top.right = (int) (f1 + bmpbmpgpeflipVBitmapWidth / 2);
            bmpgpedst_top.top = (int) (f2 - bmpbmpgpeflipVBitmapHeight / 2);
            bmpgpedst_top.bottom = (int) (f2 + bmpbmpgpeflipVBitmapHeight / 2);
            bmpgpedst_flipV.left = (int) (f5 - bmpbmpgpetopBitmapWidth / 2);
            bmpgpedst_flipV.right = (int) (f5 + bmpbmpgpetopBitmapWidth / 2);
            bmpgpedst_flipV.top = (int) (f6 - bmpbmpgpetopBitmapHeight / 2);
            bmpgpedst_flipV.bottom = (int) (f6 + bmpbmpgpetopBitmapHeight / 2);
            if (isInEdit) {

                canvas.drawLine(f1, f2, f3, f4, bmpgpelocalPaint);
                canvas.drawLine(f3, f4, f7, f8, bmpgpelocalPaint);
                canvas.drawLine(f5, f6, f7, f8, bmpgpelocalPaint);
                canvas.drawLine(f5, f6, f1, f2, bmpgpelocalPaint);

                canvas.drawBitmap(bmpgperesizeBitmap, null, bmpgpedst_resize, null);
                canvas.drawBitmap(bmpgpeflipVBitmap, null, bmpgpedst_flipV, null);
                canvas.drawBitmap(bmpgpetopBitmap, null, bmpgpedst_top, null);
            }
            canvas.restore();
        }
    }
    public void setImageResource(Bitmap resId) {
        setBitmap(resId);
    }

    public void setBitmap(Bitmap bitmap) {
        bmpmatrix.reset();
        orignalbitmap=bitmap;
        bmpgpemBitmap = bitmap;
        bmpsetDiagonalLength();
        bmpinitBitmaps();
        int w = bmpgpemBitmap.getWidth();
        int h = bmpgpemBitmap.getHeight();
        oringinWidth = w;
        float bmpinitScale = (MIN_SCALE + MAX_SCALE) / 2;
        bmpmatrix.postScale(bmpinitScale, bmpinitScale, w / 2, h / 2);
        bmpmatrix.postTranslate(gpemScreenwidth / 2 - w / 2, (gpemScreenwidth) / 2 - h / 2);
        invalidate();
    }
    private void bmpsetDiagonalLength() {
        halfDiagonalLength = Math.hypot(bmpgpemBitmap.getWidth(), bmpgpemBitmap.getHeight()) / 2;
    }
    private void bmpinitBitmaps() {
        if (bmpgpemBitmap.getWidth() >= bmpgpemBitmap.getHeight())
        {
            float minWidth = gpemScreenwidth / 8;
            if (bmpgpemBitmap.getWidth() < minWidth) {
                try {
                    MIN_SCALE = 1f;
                } catch (Exception e) {
                    e.printStackTrace();
                }
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

        bmpgpeflipVBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.icon_flip);
        bmpgperesizeBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.icon_resize);


        bmpbmpgperesizeBitmapWidth = (int) (bmpgperesizeBitmap.getWidth() * BITMAP_SCALE);
        bmpbmpgperesizeBitmapHeight = (int) (bmpgperesizeBitmap.getHeight() * BITMAP_SCALE);

        bmpbmpgpeflipVBitmapWidth = (int) (bmpgpeflipVBitmap.getWidth() * BITMAP_SCALE);
        bmpbmpgpeflipVBitmapHeight = (int) (bmpgpeflipVBitmap.getHeight() * BITMAP_SCALE);

        bmpbmpgpetopBitmapWidth = (int) (bmpgpetopBitmap.getWidth() * BITMAP_SCALE);
        bmpbmpgpetopBitmapHeight = (int) (bmpgpetopBitmap.getHeight() * BITMAP_SCALE);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = MotionEventCompat.getActionMasked(event);
        boolean handled = true;
        if(!flag) {
            switch (action) {
                case MotionEvent.ACTION_DOWN:
                    if (isInButton(event, bmpgpedst_delete)) {
                        if (operationListener != null) {
                            operationListener.onDeleteClick();
                        }
                    } else if (isInResize(event)) {
                        isInResize = true;
                        lastRotateDegree = rotationToStartPoint(event);
                        midPointToStartPoint(event);
                        gpelastLength = diagonalLength(event);
                    } else if (isInButton(event, bmpgpedst_flipV)) {
                        PointF bmplocalPointF = new PointF();
                        bmpmidDiagonalPoint(bmplocalPointF);
                        bmpmatrix.postScale(-1.0F, 1.0F, bmplocalPointF.x, bmplocalPointF.y);
                        bmpisHorizonMirror = !bmpisHorizonMirror;
                        invalidate();
                    } else if (isInButton(event, bmpgpedst_top)) {
                        bringToFront();
                        if (operationListener != null) {
                            operationListener.onTop(this);
                        }
                    } else if (isInBitmap(event)) {
                        bmpgpeisInSide = true;
                        bmplastX = event.getX(0);
                        lastY = event.getY(0);
                    } else {
                        handled = false;
                    }
                    break;
                case MotionEvent.ACTION_POINTER_DOWN:
                    if (spacing(event) > pointerLimitDis) {
                        bmpgpeoldDis = spacing(event);
                        bmpisPointerDown = true;
                        midPointToStartPoint(event);
                    } else {
                        bmpisPointerDown = false;
                    }
                    bmpgpeisInSide = false;
                    isInResize = false;
                    break;
                case MotionEvent.ACTION_MOVE:
                    if (bmpisPointerDown) {
                        float bmpscale;
                        float disNew = spacing(event);
                        if (disNew == 0 || disNew < pointerLimitDis) {
                            bmpscale = 1;
                        } else {
                            bmpscale = disNew / bmpgpeoldDis;
                            bmpscale = (bmpscale - 1) * pointerZoomCoeff + 1;
                        }
                        float bmpscaleTemp = (bmpscale * Math.abs(bmpgpedst_flipV.left - bmpgpedst_resize.left)) / oringinWidth;
                        if (((bmpscaleTemp <= MIN_SCALE)) && bmpscale < 1 ||
                                (bmpscaleTemp >= MAX_SCALE) && bmpscale > 1) {
                            bmpscale = 1;
                        } else {
                            gpelastLength = diagonalLength(event);
                        }
                        bmpmatrix.postScale(bmpscale, bmpscale, mid.x, mid.y);
                        invalidate();
                    } else if (isInResize) {

                        bmpmatrix.postRotate((rotationToStartPoint(event) - lastRotateDegree) * 2, mid.x, mid.y);
                        lastRotateDegree = rotationToStartPoint(event);

                        float bmpscale = diagonalLength(event) / gpelastLength;

                        if (((diagonalLength(event) / halfDiagonalLength <= MIN_SCALE)) && bmpscale < 1 ||
                                (diagonalLength(event) / halfDiagonalLength >= MAX_SCALE) && bmpscale > 1) {
                            bmpscale = 1;
                            if (!isInResize(event)) {
                                isInResize = false;
                            }
                        } else {
                            gpelastLength = diagonalLength(event);
                        }
                        bmpmatrix.postScale(bmpscale, bmpscale, mid.x, mid.y);

                        invalidate();
                    } else if (bmpgpeisInSide) {
                        float x = event.getX(0);
                        float y = event.getY(0);
                        bmpmatrix.postTranslate(x - bmplastX, y - lastY);
                        bmplastX = x;
                        lastY = y;
                        invalidate();
                    }
                    break;
                case MotionEvent.ACTION_CANCEL:
                case MotionEvent.ACTION_UP:
                    isInResize = false;
                    bmpgpeisInSide = false;
                    bmpisPointerDown = false;
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
        bmpmatrix.getValues(v);
        float tx = v[Matrix.MTRANS_X];
        float ty = v[Matrix.MTRANS_Y];
        float bmpbmpscalex = v[Matrix.MSCALE_X];
        float skewy = v[Matrix.MSKEW_Y];
        float bmprScale = (float) Math.sqrt(bmpbmpscalex * bmpbmpscalex + skewy * skewy);
        float rAngle = Math.round(Math.atan2(v[Matrix.MSKEW_X], v[Matrix.MSCALE_X]) * (180 / Math.PI));

        PointF bmplocalPointF = new PointF();
        bmpmidDiagonalPoint(bmplocalPointF);


        float bmpminX = bmplocalPointF.x;
        float minY = bmplocalPointF.y;

        model.setbmpdegree((float) Math.toRadians(rAngle));
        float precentWidth = (bmpgpemBitmap.getWidth() * bmprScale) / gpemScreenwidth;
        model.setbmpgpescaling(precentWidth);
        model.setbmpxLocation(bmpminX / gpemScreenwidth);
        model.setbmpyLocation(minY / gpemScreenwidth);
        model.setStickerId(stickerId);
        if (bmpisHorizonMirror) {
            model.seterhorizonMirror(1);
        } else {
            model.seterhorizonMirror(2);
        }
        return model;
    }
    private boolean isInBitmap(MotionEvent event) {
        float[] bmpbmparrayOfFloat1 = new float[9];
        this.bmpmatrix.getValues(bmpbmparrayOfFloat1);
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
    private boolean isInResize(MotionEvent event) {
        int left = -20 + this.bmpgpedst_resize.left;
        int top = -20 + this.bmpgpedst_resize.top;
        int right = 20 + this.bmpgpedst_resize.right;
        int bottom = 20 + this.bmpgpedst_resize.bottom;
        return event.getX(0) >= left && event.getX(0) <= right && event.getY(0) >= top && event.getY(0) <= bottom;
    }
    private void midPointToStartPoint(MotionEvent event) {
        float[] bmparrayOfFloat = new float[9];
        bmpmatrix.getValues(bmparrayOfFloat);
        float f1 = 0.0f * bmparrayOfFloat[0] + 0.0f * bmparrayOfFloat[1] + bmparrayOfFloat[2];
        float f2 = 0.0f * bmparrayOfFloat[3] + 0.0f * bmparrayOfFloat[4] + bmparrayOfFloat[5];
        float f3 = f1 + event.getX(0);
        float f4 = f2 + event.getY(0);
        mid.set(f3 / 2, f4 / 2);
    }


    private void bmpmidDiagonalPoint(PointF paramPointF) {
        float[] bmparrayOfFloat = new float[9];
        this.bmpmatrix.getValues(bmparrayOfFloat);
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
        bmpmatrix.getValues(bmparrayOfFloat);
        float x = 0.0f * bmparrayOfFloat[0] + 0.0f * bmparrayOfFloat[1] + bmparrayOfFloat[2];
        float y = 0.0f * bmparrayOfFloat[3] + 0.0f * bmparrayOfFloat[4] + bmparrayOfFloat[5];
        double arc = Math.atan2(event.getY(0) - y, event.getX(0) - x);
        return (float) Math.toDegrees(arc);
    }


    private float diagonalLength(MotionEvent event) {
        float diagonalLength = (float) Math.hypot(event.getX(0) - mid.x, event.getY(0) - mid.y);
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

    public void setOpacity(int paramInt) {
        bmpproA = paramInt;
        invalidate();
    }

    public void setBrightProgress(int paramInt) {
        bmpproB = paramInt;
        invalidate();
    }

    public void setContrastProgress(int paramInt) {
//        bmpproC = paramInt;

        Bitmap localBitmap = Bitmap.createBitmap(orignalbitmap.getWidth(), orignalbitmap.getHeight(), orignalbitmap.getConfig());
        Canvas localCanvas = new Canvas(localBitmap);
        Paint localPaint = new Paint();
        localPaint.setAntiAlias(true);
        localPaint.setMaskFilter(new BlurMaskFilter((float) paramInt, BlurMaskFilter.Blur.NORMAL));
        Path localPath = new Path();
        localPath.moveTo((float) paramInt, (float) paramInt);
        localPath.lineTo((float) (localCanvas.getWidth() - paramInt), (float) paramInt);
        localPath.lineTo((float) (localCanvas.getWidth() - paramInt), (float) (localCanvas.getHeight() - paramInt));
        localPath.lineTo((float) paramInt, (float) (localCanvas.getHeight() - paramInt));
        localPath.lineTo((float) paramInt, (float) paramInt);
        localPath.close();
        localCanvas.drawPath(localPath, localPaint);
        localPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        localCanvas.drawBitmap(orignalbitmap, 0.0f, 0.0f, localPaint);
        bmpgpemBitmap=localBitmap;
        invalidate();
    }
    public void setenable(boolean flag) {
        this.flag=flag;
        invalidate();
    }
    public interface OperationListener {
        void onDeleteClick();

        void onEdit(bmpStickerViewimage stickerView);

        void onTop(bmpStickerViewimage stickerView);
    }

}
