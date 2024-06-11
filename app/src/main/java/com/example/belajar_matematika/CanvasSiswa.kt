package com.example.belajar_matematika


import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.widget.Toast
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.belajar_matematika.ui.theme.GuruColor
import com.example.belajar_matematika.ui.theme.SecondaryColor
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.ByteArrayOutputStream

data class Line(
    val start: Offset,
    val end: Offset,
    val color: Color = Color.White,
    val strokeWidth: Dp = 20.dp
)


@Composable
fun CanvasSiswa(
    identitas: String,
    token: String,
    navController: NavController
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val lines = remember {
        mutableStateListOf<Line>()
    }

    var isDataSent by remember { mutableStateOf(false) }


    Column(
        modifier = Modifier
            .fillMaxSize(),

    ) {
        Column (
            verticalArrangement = Arrangement.Top
        ) {
            Button(
                onClick = {
                    navController.navigate(Screen.Siswa.route)
                },
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier
                    .padding(start = 16.dp, top = 16.dp)
                    .width(85.dp)
                    .height(47.dp),

                colors = ButtonDefaults.buttonColors(
                    containerColor = SecondaryColor,
                    contentColor = Color.White
                )


            ) {
                Text(text = "Back")
            }

        }
        Spacer(modifier = Modifier.height(60.dp))
        Text(
            text = "Gambar Angka",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier
                .padding(bottom = 12.dp)
                .align(Alignment.CenterHorizontally)
        )
        Row(
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {

            Canvas(
                modifier = Modifier
                    .width(316.dp)
                    .height(314.dp)
                    .pointerInput(true) {
                        detectDragGestures { change, dragAmount ->
                            change.consume()


                            val line = Line(
                                start = change.position - dragAmount,
                                end = change.position

                            )



                            lines.add(line)

                        }
                    }
                    .background(color = Color.Black)
                    .clipToBounds()
            ){
                lines.forEach {
                    line -> drawLine(
                        color = line.color,
                        start = line.start,
                        end = line.end,
                        strokeWidth = line.strokeWidth.toPx(),
                        cap = StrokeCap.Round
                    )
                }
            }
        }
        Row(
            modifier = Modifier.padding(top = 12.dp)
        ) {

            Button(
                onClick = {
                    lines.clear()
                },
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier
                    .padding(start = 39.dp)
                    .width(85.dp)
                    .height(47.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = SecondaryColor,
                    contentColor = Color.White
                )

            ) {
                Text(text = "Clear")
            }

            Button(
                onClick = {
                    if (!isDataSent) {
                        val byteArray = convertCanvasToJpg(lines)
                        if(byteArray != null) {
                            val imageMedia = "image/jpeg".toMediaTypeOrNull()
                            val imageRequestBody = byteArray.toRequestBody(imageMedia)
                            val identitasRequestBody = identitas.toRequestBody("text/plain".toMediaTypeOrNull())
                            val imagePart = MultipartBody.Part.createFormData("image", "gambar_${identitas}_${token}.jpg", imageRequestBody)
                            scope.launch {
                                submit(imagePart, identitasRequestBody, context)
                            }
                            isDataSent = true

                        } else {
                            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(context, "Data has been sent already", Toast.LENGTH_SHORT).show()
                    }

                },
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier
                    .padding(start = 130.dp)
                    .width(98.dp)
                    .height(47.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = GuruColor,
                    contentColor = Color.White
                )

            ) {
                Text(text = "Submit")
            }
        }



    }
}

suspend fun submit(image: MultipartBody.Part, identitas: RequestBody, context: Context) {
    try {
        val response = ApiClient.apiService.uploadData(image, identitas)
        if (response.isSuccessful) {
            Toast.makeText(context, "Data berhasil dikirim", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Gagal mengirim data", Toast.LENGTH_SHORT).show()
        }
    } catch (e: Exception) {
        Toast.makeText(context, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
    }
}



fun convertCanvasToJpg(lines: List<Line>): ByteArray {
    // Membuat gambar bitmap dari canvas
    val bitmap = Bitmap.createBitmap(948, 942, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bitmap)
    lines.forEach { line ->
        canvas.drawLine(
            line.start.x,
            line.start.y,
            line.end.x,
            line.end.y,
            android.graphics.Paint().apply {
                color = line.color.toArgb()
                strokeWidth = line.strokeWidth.value
                strokeCap = android.graphics.Paint.Cap.ROUND
            }
        )
    }


    // Mengonversi gambar bitmap menjadi format JPG
    val outputStream = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
    return outputStream.toByteArray()

}



fun saveImageToGallery(context: Context, byteArray: ByteArray, fileName: String) {
    val contentValues = ContentValues().apply {
        put(MediaStore.Images.Media.DISPLAY_NAME, fileName)
        put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            put(MediaStore.Images.Media.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
            put(MediaStore.Images.Media.IS_PENDING, 1)
        }
    }

    val resolver = context.contentResolver
    val uri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)

    uri?.let {
        resolver.openOutputStream(uri).use { outputStream ->
            outputStream?.write(byteArray)
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            contentValues.clear()
            contentValues.put(MediaStore.Images.Media.IS_PENDING, 0)
            resolver.update(uri, contentValues, null, null)
        }

        Toast.makeText(context, "Gambar disimpan di galeri", Toast.LENGTH_SHORT).show()
    } ?: Toast.makeText(context, "Gagal menyimpan gambar", Toast.LENGTH_SHORT).show()
}


@Preview(showBackground = true)
@Composable
fun CanvasSiswaPrev() {
    CanvasSiswa(identitas = "jeff", token = "tqyu5", navController = rememberNavController())
}



