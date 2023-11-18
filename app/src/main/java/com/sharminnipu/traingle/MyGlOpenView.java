package com.sharminnipu.traingle;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

public class MyGlOpenView extends GLSurfaceView {
    private final MyRender render;

    public MyGlOpenView(Context context) {
        super(context);

        initialRender();
        render=new MyRender();
        setRenderer(render);
    }

    public MyGlOpenView(Context context, AttributeSet attrs) {
        super(context, attrs);

        initialRender();
        render=new MyRender();
        setRenderer(render);
    }


    private void initialRender(){

        setEGLContextClientVersion(2);
        setPreserveEGLContextOnPause(true);

    }
}
