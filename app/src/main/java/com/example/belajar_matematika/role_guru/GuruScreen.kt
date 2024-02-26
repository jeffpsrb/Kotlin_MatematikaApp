package com.example.belajar_matematika.role_guru

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.belajar_matematika.ui.theme.GuruColor
import com.example.belajar_matematika.ui.theme.SecondaryColor
import com.example.belajar_matematika.ui.theme.SiswaColor


//code ini adalah code untuk membuat tampilan dari fitur Role Guru
//Fitur untuk membuat soal dan generate jawaban

@Composable
fun RoleGuruScreen(
    modifier: Modifier,
    calculatorViewModel: CalculatorViewModel?
) {
    //memanggil CalculatorState yang berfungsi merecord segala perubahan di input atau di output
    var calculatorState = calculatorViewModel?.calculatorState

    ConstraintLayout(
        modifier = modifier
            .fillMaxSize()
            .padding(start = 30.dp, end = 30.dp)
    ) {
        //variabel yang akan menjadi id dari ConstraintLayaout untuk mengatur letak dari element UI
        val (inputText, outputText, spacer1, buttonC, buttonOpenBracket, buttonCloseBracket, buttonDevide) = createRefs()
        val guidelineTop = createGuidelineFromTop(0.1f)

        //createHorizontalChain() : membuat elemen UI berjejer secara horizontal dengan parameter id elemen yang ingin dikenakan createHorizontalChain
        createHorizontalChain(
            buttonC,
            buttonOpenBracket,
            buttonCloseBracket,
            buttonDevide,
            chainStyle = ChainStyle.SpreadInside
        )
        
        Text(
            text = calculatorState?.input ?: "0",
            modifier = modifier
                .constrainAs(inputText) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(guidelineTop)

                }
                .padding(end = 20.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.End,
            fontWeight = FontWeight.Black,
            fontSize = 20.sp

        )
        Text(
            text = calculatorState?.result ?: "0",
            fontWeight = FontWeight.Black,
            fontSize = 20.sp,
            textAlign = TextAlign.End,
            modifier = modifier
                .padding(end = 20.dp, top = 16.dp)
                .fillMaxWidth()
                .constrainAs(outputText) {
                    start.linkTo(parent.start)
                    top.linkTo(inputText.bottom)
                    end.linkTo(parent.end)
                }
        )

        Spacer(
            modifier = modifier
                .height(20.dp)
                .fillMaxWidth()
                .constrainAs(spacer1) {
                    top.linkTo(outputText.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(
                        parent.end
                    )
                }
        )
        CalculatorButton(
            symbols = "C",
            colorBackground = GuruColor,
            colorFont = Color.White,
            modifier = modifier
                .width(60.dp)
                .constrainAs(buttonC) {
                    start.linkTo(parent.start)
                    top.linkTo(spacer1.bottom)
                }
        ) {
            calculatorViewModel?.onAction(CalculatorAction.ClearInput)
        }

        CalculatorButton(
            symbols = "(",
            colorBackground = SecondaryColor,
            colorFont = SiswaColor,
            modifier = modifier
                .width(60.dp)
                .constrainAs(buttonOpenBracket) {
                    top.linkTo(buttonC.top)
                    bottom.linkTo(buttonC.bottom)
                }
        ) {
            calculatorViewModel?.onAction(CalculatorAction.InputChange("("))
        }

        CalculatorButton(
            symbols = ")",
            colorBackground = SecondaryColor,
            colorFont = SiswaColor,
            modifier = modifier
                .width(60.dp)
                .constrainAs(buttonCloseBracket) {
                    top.linkTo(buttonC.top)
                    bottom.linkTo(buttonC.bottom)
                }
        ) {
            calculatorViewModel?.onAction(CalculatorAction.InputChange(")"))
        }

        CalculatorButton(
            symbols = "÷",
            colorBackground = SecondaryColor,
            colorFont = SiswaColor,
            modifier = modifier
                .width(60.dp)
                .constrainAs(buttonDevide) {
                    top.linkTo(buttonC.top)
                    bottom.linkTo(buttonC.bottom)
                    end.linkTo(parent.end)
                }
        ) {
            calculatorViewModel?.onAction(CalculatorAction.InputChange("/"))
        }


    }
}


@Preview(showBackground = true)
@Composable
fun RoleGuruScreenPrev() {
    RoleGuruScreen(modifier = Modifier, calculatorViewModel = CalculatorViewModel())

}