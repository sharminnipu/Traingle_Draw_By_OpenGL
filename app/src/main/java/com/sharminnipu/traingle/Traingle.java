package com.sharminnipu.traingle;

import android.opengl.GLES20;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public class Traingle {

    private int mProgram;

    private final String vertexShaderCode =
            "attribute vec4 vPosition;" +
                    "void main() {" +
                    "  gl_Position = vPosition;" +
                    "}";

    private final String fragmentShaderCode =
            "precision mediump float;" +
                    "uniform vec4 vColor;" +
                    "void main() {" +
                    "  gl_FragColor = vColor;" +
                    "}";




    private FloatBuffer vertexBuffer;
    static final int COORDS_PER_VERTEX=3;
    static float traingleCoords[]={
            0.0f,0.5f,0.0f,//top
            0.5f,0.0f,0.0f,//bottom right
            -0.5f,0.0f,0.0f//bottom left
    };
    float traingleColor[]={1.0f,0.0f,0.0f,1.0f};

    public Traingle(){
        ByteBuffer bb=ByteBuffer.allocateDirect(traingleCoords.length *4);
        bb.order(ByteOrder.nativeOrder());
        vertexBuffer=bb.asFloatBuffer();
        vertexBuffer.put(traingleCoords);
        vertexBuffer.position(0);

        int vertexShader=MyRender.loadShader(GLES20.GL_VERTEX_SHADER,vertexShaderCode);
        int fragmentShader=MyRender.loadShader(GLES20.GL_FRAGMENT_SHADER,fragmentShaderCode);

        mProgram=GLES20.glCreateProgram();
        GLES20.glAttachShader(mProgram,vertexShader);
        GLES20.glAttachShader(mProgram,fragmentShader);
        GLES20.glLinkProgram(mProgram);

    }
    private int positionHandle;
    private int colorHandle;

    private final int vertexCount=traingleCoords.length/COORDS_PER_VERTEX;
    private final int vertexStride=COORDS_PER_VERTEX *4;

    public void draw(){
        GLES20.glUseProgram(mProgram);
        GLES20.glEnableVertexAttribArray(positionHandle);

        GLES20.glVertexAttribPointer(positionHandle,COORDS_PER_VERTEX,GLES20.GL_FLOAT,false,vertexStride,vertexBuffer);

        colorHandle=GLES20.glGetUniformLocation(mProgram,"vColor");
        GLES20.glUniform4fv(colorHandle,1,traingleColor,0);
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES,0,vertexCount);
        GLES20.glDisableVertexAttribArray(positionHandle);

    }

}
