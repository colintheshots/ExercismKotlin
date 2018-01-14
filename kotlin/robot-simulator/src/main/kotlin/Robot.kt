import Orientation.*

class Robot(var gridPosition: GridPosition = GridPosition(0, 0),
            var orientation: Orientation = NORTH) {
    fun turnLeft() {
        orientation = when (orientation) {
            NORTH -> WEST
            EAST -> NORTH
            SOUTH -> EAST
            WEST -> SOUTH
        }
    }
    fun turnRight() {
        orientation = when (orientation) {
            NORTH -> EAST
            EAST -> SOUTH
            SOUTH -> WEST
            WEST -> NORTH
        }
    }
    fun advance() {
        gridPosition = when (orientation) {
            NORTH -> gridPosition.copy(y = gridPosition.y + 1)
            EAST -> gridPosition.copy(x = gridPosition.x + 1)
            SOUTH -> gridPosition.copy(y = gridPosition.y - 1)
            WEST -> gridPosition.copy(x = gridPosition.x - 1)
        }
    }
    fun simulate(commands: String) {
        commands.forEach { command ->
            when (command) {
                'L' -> turnLeft()
                'R' -> turnRight()
                'A' -> advance()
            }
        }
    }
}