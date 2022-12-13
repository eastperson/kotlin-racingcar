package racingcar.interfaces.ui.controller

import racingcar.application.race.CarData
import racingcar.application.race.Race
import racingcar.application.race.RoundCarData
import racingcar.application.race.WinCarData
import racingcar.domain.car.ParticipatingCars
import racingcar.interfaces.ui.controller.console.RacingCarInputConsole
import racingcar.interfaces.ui.controller.console.RacingCarOutputConsole

class RacingCarController(
    private val race: Race
) {

    fun startRace() {
        val input = RacingCarInputConsole.input()
        val participatingCars = race.getParticipatingCars(carNames = input.carNames)

        initRace(participatingCars = participatingCars)
        processRace(participatingCars = participatingCars, attemptCount = input.attemptCount)
        winRace(participatingCars = participatingCars)
    }

    private fun initRace(participatingCars: ParticipatingCars) {
        val initRoundData = race.currentCarPositions(participatingCars = participatingCars)
        RacingCarOutputConsole.printlnResult()
        initRoundData.currentData().printCurrent()
        RacingCarOutputConsole.printNewLine()
    }


    private fun List<Pair<String, Int>>.currentData(): RoundCarData {
        return RoundCarData(
            this.map { (name, position) ->
                CarData(name = name, position = position)
            }
        )
    }

    private fun processRace(participatingCars: ParticipatingCars, attemptCount: Int) {
        repeat(attemptCount) {
            participatingCars.move()
            val currentRoundData = race.currentCarPositions(participatingCars = participatingCars)
            currentRoundData.currentData().printCurrent()
            RacingCarOutputConsole.printNewLine()
        }
        RacingCarOutputConsole.printNewLine()
    }

    private fun RoundCarData.printCurrent() {
        this.carData.forEach {
            RacingCarOutputConsole.printName(it.name)
            repeat(it.position) {
                RacingCarOutputConsole.printPosition()
            }
            RacingCarOutputConsole.printNewLine()
        }
    }

    private fun winRace(participatingCars: ParticipatingCars) {
        val winners = race.win(participatingCars)
        RacingCarOutputConsole.printPrepareWin()

        val winnerNames = mutableListOf<String>()
        winners.announceWinners().carData.map { winnerNames.add(it.name) }
        RacingCarOutputConsole.printWinners(winnerNames = winnerNames)
    }

    private fun List<Pair<String, Int>>.announceWinners(): WinCarData {
        return WinCarData(
            this.map {
                CarData(name = it.first, position = it.second)
            }
        )
    }
}
