package com.example.belajar_matematika

import android.content.Context
import android.graphics.Bitmap
import com.example.belajar_matematika.ml.ModelBest

import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import java.nio.ByteBuffer
import java.nio.ByteOrder

class DigitClassifier (private val context: Context) {
    private val inputSize = 28

    private fun convertBitmapToByteBuffer(bitmap: Bitmap): ByteBuffer {
        val byteBuffer = ByteBuffer.allocateDirect(inputSize * inputSize * 4)
        byteBuffer.order(ByteOrder.nativeOrder())

        val pixels = IntArray(inputSize * inputSize)
        bitmap.getPixels(pixels, 0, inputSize, 0, 0, inputSize, inputSize)
        for (pixel in pixels) {
            val normalized = (pixel and 0xFF).toFloat() / 255.0f
            byteBuffer.putFloat(normalized)
        }

        return byteBuffer
    }

    fun performInference(bitmap: Bitmap): Int {
        val model = ModelBest.newInstance(context)

        val byteBuffer = convertBitmapToByteBuffer(bitmap)

        val inputFeature0 = TensorBuffer.createFixedSize(intArrayOf(1, inputSize, inputSize, 1), org.tensorflow.lite.DataType.FLOAT32)
        inputFeature0.loadBuffer(byteBuffer)

        val output = model.process(inputFeature0)
        val outputFeature0 = output.outputFeature0AsTensorBuffer

        model.close()

        val outputArray = outputFeature0.floatArray
        return outputArray.indices.maxByOrNull { outputArray[it] } ?: -1
    }
}