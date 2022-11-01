package step2.domain.operation.enums

import step2.domain.operation.command.AdditionOperationCommand
import step2.domain.operation.command.DivisionOperationCommand
import step2.domain.operation.command.MultiplicationOperationCommand
import step2.domain.operation.command.OperationCommand
import step2.domain.operation.command.SubtractionOperationCommand

enum class Operator(
    private val operator: String
) {
    ADDITION("+"), SUBTRACTION("-"), MULTIPLICATION("*"), DIVISION("/");

    companion object {
        fun operatorOf(operatorString: String): Operator {
            for (value in values()) {
                if (operatorString == value.operator) {
                    return value
                }
            }
            throw IllegalArgumentException("존재하지 않는 연산자입니다.")
        }
    }

    fun toCommand(): OperationCommand {
        return when (this) {
            ADDITION -> AdditionOperationCommand()
            SUBTRACTION -> SubtractionOperationCommand()
            MULTIPLICATION -> MultiplicationOperationCommand()
            DIVISION -> DivisionOperationCommand()
        }
    }
}
