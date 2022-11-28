package racingcar.interfaces.ui.controller.console

object RacingCarOutputConsole {

    private const val RESULT_COMMENT = "처리 결과"
    private const val LITERAL_POSITION = "-"

    fun print(string: String) {
        print(string)
    }

    fun consolePrintPrepareWin() {
        println("우승자 발표")
        println("두구두구두구----")
        println()
    }

    fun consolePrintNewLine() {
        println()
    }

    fun printPosition() {
        print(LITERAL_POSITION)
    }

    fun printWinners(winnerNames: List<String>) {
        println("${winnerNames}가 최종 우승했습니다 !!!")
    }

    fun printlnResult() {
        println(RESULT_COMMENT)
    }
}
