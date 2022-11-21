package racingcar.assemble

import racingcar.application.port.input.InputPort
import racingcar.application.port.output.OutputPort
import racingcar.application.race.event.publisher.EventPublisher
import racingcar.application.race.Race
import racingcar.application.race.factory.CarFactory
import racingcar.domain.car.engine.implement.RandomMovingEngine
import racingcar.interfaces.ui.input.RacingCarInputAdapter
import racingcar.interfaces.ui.output.RacingCarOutputAdapter

object ObjectAssemble {
    fun start() = racingCarInputAdapter().input()

    private fun racingCarInputAdapter() = RacingCarInputAdapter(inputPort = inputPort())
    private fun inputPort() = InputPort(race = race())

    private fun race() = Race(carFactory = carFactory(), eventPublisher = eventPublisher())
    private fun eventPublisher() = EventPublisher(outputPort = outputPort())
    private fun carFactory() = CarFactory(movingEngine = movingEngine())
    private fun movingEngine() = RandomMovingEngine()

    private fun outputPort() = OutputPort(racingCarOutputAdapter = racingCarOutputAdapter())
    private fun racingCarOutputAdapter() = RacingCarOutputAdapter()
}
