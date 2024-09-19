object TrisKotlin {
    private val tabella: List<MutableList<String>> = listOf(
        mutableListOf("-", "-", "-"),
        mutableListOf("-", "-", "-"),
        mutableListOf("-", "-", "-"),
    )

    @JvmStatic
    fun main(args: Array<String>) {
        printWelcomeMessage()

        var turno: String = "x"
        stampaTabella()

        println()

        while (!piena() && vincitore() == null) {
            gioca(turno)
            stampaTabella()

            turno = if (turno == "x") {
                "o"
            } else {
                "x"
            }
        }

        val haVinto = vincitore()

        if (haVinto == null) {
            println("Pareggio!")
        } else {
            println("Ha vinto $haVinto!")
        }
    }

    /**
     * Prints a styled welcome message to the console.
     */
    private fun printWelcomeMessage() {
        println("*****************************************")
        println("*                                       *")
        println("*      Welcome to Tic-Tac-Toe!          *")
        println("*     Created by Giorgia Giorgi         *")
        println("*   https://github.com/giorgia-giorgi   *")
        println("*                                       *")
        println("*****************************************")
        println()
    }

    /**
     * Returns whether the table is full or not.
     */
    private fun piena(): Boolean {
        for (riga in tabella) {
            for (segno in riga) {
                if (segno == "-") {
                    return false
                }
            }
        }
        return true
    }

    /**
     * Returns the winner. Null if not present yet.
     */
    private fun vincitore(): String? {
        for (riga in tabella) {
            var diverso = false
            val primo = riga[0]

            for (segno in riga) {
                if (primo == "-" || primo != segno) {
                    diverso = true
                    break
                }
            }
            if (!diverso) {
                return primo

            }
        }
        val numeroRighe = tabella.size
        val numeroColonne = tabella[0].size

        var diverso: Boolean
        var primo: String


        for (x in 0..<numeroRighe) {
            diverso = false
            primo = tabella[0][x]

            for (y in 0..<numeroColonne) {
                val elemento: String = tabella[y][x]
                if (elemento != primo || elemento == "-") {
                    diverso = true
                    break
                }
            }

            if (!diverso) {
                return primo
            }
        }

        diverso = false
        primo = tabella[0][0]

        for (indice in 0..<numeroRighe) {
            val elemento: String = tabella[indice][indice]

            if (primo != elemento || elemento == "-") {
                diverso = true
                break
            }
        }

        if (!diverso) {
            return primo
        }

        primo = tabella[numeroRighe - 1][0]

        for (x in 0..<numeroRighe) {
            val y: Int = 2 - x
            val elemento: String = tabella[y][x]
            if (elemento != primo || primo == "-") {
                return null
            }
        }

        return primo
    }

    /**
     * Function that asks for user input;
     */
    private fun gioca (segno : String){
        var x = 0
        var y = 0

        while(true) {
            println()
            println("> $segno's turn: ")

            print("> Coordinate x: ")
            x = try {
                readlnOrNull()!!.toInt()
            } catch (exception: Exception) {
                continue
            }

            if (x < 0 || x >= tabella[0].size)
                continue

            print("> Coordinate y: ")
            y = try {
                readlnOrNull()!!.toInt()
            } catch (exception: Exception) {
                continue
            }

            if (y < 0 || y >= tabella.size)
                continue

            if (tabella[y][x] == "-")
                break // Valid move
        }

        inserisci(x, y, segno)
    }

    /**
     * Prints the current state of the game board.
     */
    private fun stampaTabella() {
        println()

        println("> GAME BOARD")

        for (riga in tabella) {
            for (elemento in riga) {
                print(elemento)
            }
            println()
        }
    }

    /**
     * Inserts the player's symbol at the specified coordinates.
     *
     * @param x     The x-coordinate (column) of the move.
     * @param y     The y-coordinate (row) of the move.
     * @param segno The symbol of the player ("x" or "o").
     */
    private fun inserisci(x: Int, y: Int, segno: String) {
        tabella[y][x] = segno
    }
}