package tictactoe
var MultiList = mutableListOf(mutableListOf<Char>('_','_','_'),mutableListOf<Char>('_','_','_'),mutableListOf<Char>('_','_','_'))
fun printDashes(){
  println("---------")
}

fun printGrid(){
  printDashes()
  for (i in 0..2){
    print("|")
    for (c in 0..2){
      if (MultiList[i][c] != '_')
      print(" ${MultiList[i][c]}")
      else print("  ")
    }
    println(" |")
  }
  printDashes()
}


fun create2DGrid(input: String){
  for (i in 0..2){
    for (c in 0..2){
      MultiList[i][c] = input[3*i+c]
    }
  }
}

fun isImpossible(): Boolean{
  var numberX = 0
  var numberO = 0
  var impossible = false
  for (i in 0..2){
    for (c in 0..2){
      if (MultiList[i][c] == 'X') numberX++
      if (MultiList[i][c] == 'O') numberO++
    }
  }

  if ((numberX - numberO >= 2) || (numberO - numberX >= 2)) impossible = true

  return impossible
}

fun isEnded():Boolean {
  var winX = false
  var winO = false
  var endGame = true
  if (isImpossible()){
    println("Impossible")
    endGame = true
  }
  else {
    for (i in 0..2) {
      if (MultiList[i].contains('_')) endGame = false //check if the grid if full
      //check the rows
      if ((MultiList[i][0] == MultiList[i][1]) && (MultiList[i][1] == MultiList[i][2])) {
        if (MultiList[i][0] == 'X') winX = true
        else if (MultiList[i][0] == 'O') winO = true
      }
      //check colon
      if ((MultiList[0][i] == MultiList[1][i]) && (MultiList[1][i] == MultiList[2][i])) {
        if (MultiList[0][i] == 'X') winX = true
        else if (MultiList[0][i] == 'O') winO = true
      }


    }
    //check diagonals
    if ((MultiList[0][0] == MultiList[1][1]) && (MultiList[1][1] == MultiList[2][2])) {
      if (MultiList[0][0] == 'X') winX = true
      else if (MultiList[0][0] == 'O') winO = true
    }
    if ((MultiList[2][0] == MultiList[1][1]) && (MultiList[1][1] == MultiList[0][2])) {
      if (MultiList[2][0] == 'X') winX = true
      else if (MultiList[2][0] == 'O') winO = true
    }

    if (endGame && !winX && !winO) {
      println("Draw")
    }
    /*if (!endGame && !winX && !winO) {
      println("Game not finished")
    }*/
    if (winX && winO) {
      println("Impossible")
      endGame = true
    }
    if (winX && !winO) {
      println("X wins")
      endGame = true
    }
    if (!winX && winO) {
      println("O wins")
      endGame = true
    }

  }

  return endGame

}

fun makeYourMove(player:Char){
  var playerMove = true
  while (playerMove) {
    var input = readln().split(" ")
    val coordinates: Pair<Int, Int>
    try {
      coordinates = Pair(input[0].toInt() - 1, input[1].toInt() - 1)
      if ((coordinates.first > 2) || (coordinates.second > 2)) println("Coordinates should be from 1 to 3!")
      else if (MultiList[coordinates.first][coordinates.second] != '_') println("This cell is occupied! Choose another one!")
      else if (MultiList[coordinates.first][coordinates.second] == '_') {
        MultiList[coordinates.first][coordinates.second] = player
        printGrid()
        playerMove = false
      }
    } catch (e: NumberFormatException) {
      println("You should enter numbers!")
    }
  }




}



fun main() {
  // val symbols = readln()
  //create2DGrid(symbols)
  printGrid()
  //AnalyzeTheState()
  var xMove = true
  var oMove = false

  while (!isEnded()) {
    if (xMove) {
      makeYourMove('X')
      xMove = false
      oMove = true
    } else if (oMove) {
      makeYourMove('O')
      xMove = true
      oMove = false
    }
  }




   /* while (xMove) {
      var input = readln().split(" ")
      val coordinates: Pair<Int, Int>
      try {
        coordinates = Pair(input[0].toInt() - 1, input[1].toInt() - 1)
        if ((coordinates.first > 2) || (coordinates.second > 2)) println("Coordinates should be from 1 to 3!")
        else if (MultiList[coordinates.first][coordinates.second] != '_') println("This cell is occupied! Choose another one!")
        else if (MultiList[coordinates.first][coordinates.second] == '_') {
          MultiList[coordinates.first][coordinates.second] = 'X'
          printGrid()
          xMove = false
        }

      } catch (e: NumberFormatException) {
        println("You should enter numbers!")
      }
    }*/

}