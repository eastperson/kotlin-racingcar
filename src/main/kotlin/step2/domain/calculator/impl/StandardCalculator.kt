package step2.domain.calculator.impl

import step2.domain.calculator.Calculator
import step2.domain.calculator.model.InputOperationCommand
import step2.domain.operation.enums.Operator
import step2.domain.operation.model.BinaryInputCommand

class StandardCalculator : Calculator<Int> {

    override fun calculate(inputOperationCommandList: List<InputOperationCommand>): Int {
        return sequentialOperate(inputOperationCommandList)
    }

    private fun sequentialOperate(inputOperationCommandList: List<InputOperationCommand>): Int {
        var answer = 0
        var temp: InputOperationCommand? = null

        inputOperationCommandList.windowed(size = 2, step = 1, partialWindows = true) { window: List<InputOperationCommand> ->
            var first = window.first()
            temp?.let { first = it }

            if (first.nextOperator == Operator.ILLEGAL_STATE) run {
                answer = first.number
                return@windowed
            }

            val second = window.last()
            val inputCommand = BinaryInputCommand(first.number, second.number)

            val result = first.nextOperator.toCommand().operate(inputCommand)
            temp = InputOperationCommand(number = result, second.nextOperator)
        }
        return answer
    }
}
