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
        val (
            inputText,
            outputText,
            spacer1,
            buttonC,
            buttonOpenBracket,
            buttonCloseBracket,
            buttonDevide,
            spacer2,
            button7,
            button8,
            button9,
            buttonMultiply,
            spacer6,
        ) = createRefs()

        // createRefs(): hanya dapat menampung 17 id sehingga jika lebih dari 17 maka harus membuat createRefs() baru
        val (
            spacer3,
            button4,
            button5,
            button6,
            buttonMin,
            spacer4,
            button1,
            button2,
            button3,
            buttonPlus,
            spacer5,
            button0,
            buttonDot,
            buttonSquere,
            buttonGenerate,
            buttonDelete,
        ) = createRefs()
        val guidelineTop = createGuidelineFromTop(0.1f)

        //createHorizontalChain() : membuat elemen UI berjejer secara horizontal dengan parameter id elemen yang ingin dikenakan createHorizontalChain
        createHorizontalChain(
            buttonC,
            buttonOpenBracket,
            buttonCloseBracket,
            buttonDevide,
            chainStyle = ChainStyle.SpreadInside
        )
        createHorizontalChain(
            button7,
            button8,
            button9,
            buttonMultiply,
            chainStyle = ChainStyle.SpreadInside
        )
        createHorizontalChain(
            button4,
            button5,
            button6,
            buttonMin,
            chainStyle = ChainStyle.SpreadInside
        )
        createHorizontalChain(
            button1,
            button2,
            button3,
            buttonPlus,
            chainStyle = ChainStyle.SpreadInside
        )
        createHorizontalChain(
            button0,
            buttonDot,
            buttonSquere,
            chainStyle = ChainStyle.SpreadInside
        )
        createHorizontalChain(
            buttonDelete,
            buttonGenerate,
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
            colorBackground = SiswaColor,
            colorFont = Color.White,
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

        Spacer(
            modifier = modifier
                .height(20.dp)
                .fillMaxWidth()
                .constrainAs(spacer2) {
                    top.linkTo(buttonC.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )

        CalculatorButton(
            symbols = "7",
            colorBackground = SecondaryColor,
            colorFont = SiswaColor,
            modifier = modifier
                .width(60.dp)
                .constrainAs(button7) {
                    top.linkTo(spacer2.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        ) {
            calculatorViewModel?.onAction(CalculatorAction.InputChange("7"))
        }

        CalculatorButton(
            symbols = "8",
            colorBackground = SecondaryColor,
            colorFont = SiswaColor,
            modifier = modifier
                .width(60.dp)
                .constrainAs(button8) {
                    top.linkTo(button7.top)
                    bottom.linkTo(button7.bottom)
                }
        ) {
            calculatorViewModel?.onAction(CalculatorAction.InputChange("8"))
        }

        CalculatorButton(
            symbols = "9",
            colorBackground = SecondaryColor,
            colorFont = SiswaColor,
            modifier = modifier
                .width(60.dp)
                .constrainAs(button9) {
                    top.linkTo(button7.top)
                    bottom.linkTo(button7.bottom)
                }
        ) {
            calculatorViewModel?.onAction(CalculatorAction.InputChange("9"))
        }

        CalculatorButton(
            symbols = "X",
            colorBackground = SiswaColor,
            colorFont = Color.White,
            modifier = modifier
                .width(60.dp)
                .constrainAs(buttonMultiply) {
                    top.linkTo(button7.top)
                    bottom.linkTo(button7.bottom)
                    end.linkTo(parent.end)
                }
        ) {
            calculatorViewModel?.onAction(CalculatorAction.InputChange("*"))
        }

        Spacer(
            modifier = modifier
                .height(20.dp)
                .fillMaxWidth()
                .constrainAs(spacer3) {
                    top.linkTo(button7.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )

        CalculatorButton(
            symbols = "4",
            colorBackground = SecondaryColor,
            colorFont = SiswaColor,
            modifier = modifier
                .width(60.dp)
                .constrainAs(button4) {
                    top.linkTo(spacer3.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        ) {
            calculatorViewModel?.onAction(CalculatorAction.InputChange("4"))
        }

        CalculatorButton(
            symbols = "5",
            colorBackground = SecondaryColor,
            colorFont = SiswaColor,
            modifier = modifier
                .width(60.dp)
                .constrainAs(button5) {
                    top.linkTo(button4.top)
                    bottom.linkTo(button4.bottom)

                }
        ) {
            calculatorViewModel?.onAction(CalculatorAction.InputChange("5"))
        }

        CalculatorButton(
            symbols = "6",
            colorBackground = SecondaryColor,
            colorFont = SiswaColor,
            modifier = modifier
                .width(60.dp)
                .constrainAs(button6) {
                    top.linkTo(button4.top)
                    bottom.linkTo(button4.bottom)

                }
        ) {
            calculatorViewModel?.onAction(CalculatorAction.InputChange("6"))
        }

        CalculatorButton(
            symbols = "-",
            colorBackground = SiswaColor,
            colorFont = Color.White,
            modifier = modifier
                .width(60.dp)
                .constrainAs(buttonMin) {
                    top.linkTo(button4.top)
                    bottom.linkTo(button4.bottom)

                }
        ) {
            calculatorViewModel?.onAction(CalculatorAction.InputChange("-"))
        }

        Spacer(
            modifier = modifier
                .height(20.dp)
                .fillMaxWidth()
                .constrainAs(spacer4) {
                    top.linkTo(button4.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )

        CalculatorButton(
            symbols = "1",
            colorBackground = SecondaryColor,
            colorFont = SiswaColor,
            modifier = modifier
                .width(60.dp)
                .constrainAs(button1) {
                    top.linkTo(spacer4.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)

                }
        ) {
            calculatorViewModel?.onAction(CalculatorAction.InputChange("1"))
        }
        CalculatorButton(
            symbols = "2",
            colorBackground = SecondaryColor,
            colorFont = SiswaColor,
            modifier = modifier
                .width(60.dp)
                .constrainAs(button2) {
                    top.linkTo(button1.top)
                    bottom.linkTo(button1.bottom)

                }
        ) {
            calculatorViewModel?.onAction(CalculatorAction.InputChange("2"))
        }
        CalculatorButton(
            symbols = "3",
            colorBackground = SecondaryColor,
            colorFont = SiswaColor,
            modifier = modifier
                .width(60.dp)
                .constrainAs(button3) {
                    top.linkTo(button1.top)
                    bottom.linkTo(button1.bottom)

                }
        ) {
            calculatorViewModel?.onAction(CalculatorAction.InputChange("3"))
        }
        CalculatorButton(
            symbols = "+",
            colorBackground = SiswaColor,
            colorFont = Color.White,
            modifier = modifier
                .width(60.dp)
                .constrainAs(buttonPlus) {
                    top.linkTo(button1.top)
                    bottom.linkTo(button1.bottom)
                    end.linkTo(parent.end)

                }
        ) {
            calculatorViewModel?.onAction(CalculatorAction.InputChange("+"))
        }

        Spacer(
            modifier = modifier
                .height(20.dp)
                .fillMaxWidth()
                .constrainAs(spacer5) {
                    top.linkTo(button1.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )
        CalculatorButton(
            symbols = "0",
            colorBackground = SecondaryColor,
            colorFont = SiswaColor,
            modifier = modifier
                .width(120.dp)
                .constrainAs(button0) {
                    top.linkTo(spacer5.bottom)
                    start.linkTo(parent.start)

                }
        ) {
            calculatorViewModel?.onAction(CalculatorAction.InputChange("0"))
        }
        CalculatorButton(
            symbols = ".",
            colorBackground = SecondaryColor,
            colorFont = SiswaColor,
            modifier = modifier
                .width(60.dp)
                .constrainAs(buttonDot) {
                    top.linkTo(button0.top)
                    bottom.linkTo(button0.bottom)

                }
        ) {
            calculatorViewModel?.onAction(CalculatorAction.InputChange("."))
        }
        CalculatorButton(
            symbols = "^",
            colorBackground = SiswaColor,
            colorFont = Color.White,
            modifier = modifier
                .width(60.dp)
                .constrainAs(buttonSquere) {
                    top.linkTo(button0.top)
                    bottom.linkTo(button0.bottom)

                }
        ) {
            calculatorViewModel?.onAction(CalculatorAction.InputChange("^"))
        }
        Spacer(
            modifier = modifier
                .height(20.dp)
                .fillMaxWidth()
                .constrainAs(spacer6) {
                    top.linkTo(button0.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )

        CalculatorButton(
            symbols = "<-",
            colorBackground = GuruColor,
            colorFont = Color.White,
            modifier = modifier
                .width(120.dp)
                .constrainAs(buttonDelete) {
                    top.linkTo(spacer6.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)

                }
        ) {
            calculatorViewModel?.onAction(CalculatorAction.DeleteInput)
        }
        CalculatorButton(
            symbols = "generate",
            colorBackground = SiswaColor,
            colorFont = Color.White,
            modifier = modifier
                .width(160.dp)
                .constrainAs(buttonGenerate) {
                    top.linkTo(buttonDelete.top)
                    bottom.linkTo(buttonDelete.bottom)
                    end.linkTo(parent.end)

                }
        ) {
            calculatorViewModel?.onAction(CalculatorAction.Evaluate)
        }


    }
}


@Preview(showBackground = true)
@Composable
fun RoleGuruScreenPrev() {
    RoleGuruScreen(modifier = Modifier, calculatorViewModel = CalculatorViewModel())

}