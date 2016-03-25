package com.xsx.weather.widget;

import android.animation.TypeEvaluator;
import android.graphics.Point;
import android.graphics.PointF;

/**
 * Created by XSX on 2016/3/9.
 */
public class BezierEvaluator implements TypeEvaluator<PointF> {

    private PointF[] points;

    public BezierEvaluator(PointF... points) {
        if (points.length > 4) {
            throw new IllegalArgumentException("只能有4个点");
        }
        this.points = points;

    }

    @Override
    public PointF evaluate(float fraction, PointF startValue, PointF endValue) {
        // B(t) = P0 * (1-t)^3 + 3 * P1 * t * (1-t)^2 + 3 * P2 * t^2 * (1-t) + P3 * t^3
        float t = fraction;
        float n = 1f - t;
        PointF p0 = points[0];
        PointF p1 = points[1];
        PointF p2 = points[2];
        PointF p3 = points[3];
        float x = (float) (p0.x * Math.pow(n, 3) + 3 * p1.x * t * Math.pow(n, 2) + 3 * p2.x * Math.pow(t, 2) * n + p3.x * Math.pow(t, 3));
        float y = (float) (p0.y * Math.pow(n, 3) + 3 * p1.y * t * Math.pow(n, 2) + 3 * p2.y * Math.pow(t, 2) * n + p3.y * Math.pow(t, 3));
        return new PointF(x,y);
    }
}
