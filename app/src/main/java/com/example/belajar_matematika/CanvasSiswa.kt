package com.example.belajar_matematika


import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class Line(
    val start: Offset,
    val end: Offset,
    val color: Color = Color.White,
    val strokeWidth: Dp = 10.dp
)



@Composable
fun CanvasSiswa() {
    val lines = remember {
        mutableStateListOf<Line>()
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.primary)),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Gambar Angka",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
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
                    containerColor = colorResource(id = R.color.secondary),
                    contentColor = Color.White
                )

            ) {
                Text(text = "Clear")
            }

            Button(
                onClick = {

                },
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier
                    .padding(start = 130.dp)
                    .width(98.dp)
                    .height(47.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.secondary),
                    contentColor = Color.White
                )

            ) {
                Text(text = "Submit")
            }
        }

    }
}




@Preview(showBackground = true)
@Composable
fun CanvasSiswaPrev() {
    CanvasSiswa()
}