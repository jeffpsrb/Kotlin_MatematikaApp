package com.example.belajar_matematika

import android.graphics.Bitmap
import org.opencv.android.Utils
import org.opencv.core.Mat
import org.opencv.core.MatOfPoint
import org.opencv.core.Scalar
import org.opencv.core.Size
import org.opencv.imgproc.Imgproc

class ImageProcessor {

    fun preprocessImage(mat: Mat): Bitmap {
        var grayMat = Mat()
        if (mat.channels() == 3) {
            Imgproc.cvtColor(mat, grayMat, Imgproc.COLOR_BGR2GRAY)
        } else {
            grayMat = mat.clone()
        }

        //mengubah ukuran menjadi 28x28
        val resized = Mat()
        Imgproc.resize(grayMat, resized, Size(28.0, 28.0))

        //konversi kembali ke bitmap
        val output = Bitmap.createBitmap(28, 28, Bitmap.Config.ARGB_8888)
        Utils.matToBitmap(resized, output)

        return output
    }


    fun findContours(bitmap: Bitmap): MutableList<MatOfPoint> {
        val mat = Mat()
        Utils.bitmapToMat(bitmap, mat)

        // Mengonversi gambar ke grayscale
        val grey = Mat()
        Imgproc.cvtColor(mat, grey, Imgproc.COLOR_BGR2GRAY)

        // Menerapkan Gaussian Blur untuk mengurangi noise
        val blurred = Mat()
        Imgproc.GaussianBlur(grey, blurred, Size(5.0, 5.0), 0.0)

        // Menerapkan threshold untuk membuat gambar biner
        val thresh = Mat()
        Imgproc.threshold(blurred, thresh, 15.0, 255.0, Imgproc.THRESH_BINARY)

        // Mendeteksi kontur
        val contours = mutableListOf<MatOfPoint>()
        val hierarchy = Mat()
        Imgproc.findContours(thresh, contours, hierarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE)

        return contours
    }

    fun drawContours(bitmap: Bitmap, contours: List<MatOfPoint>): Bitmap {
        val mat = Mat()
        Utils.bitmapToMat(bitmap, mat)

        for (contour in contours) {
            val rect = Imgproc.boundingRect(contour)
            Imgproc.rectangle(mat, rect.tl(), rect.br(), Scalar(0.0, 255.0, 0.0), 2)
        }

        val output = Bitmap.createBitmap(bitmap.width, bitmap.height, Bitmap.Config.ARGB_8888)
        Utils.matToBitmap(mat, output)

        return output
    }
}