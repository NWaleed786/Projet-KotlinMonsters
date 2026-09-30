package org.example.item

import org.example.monstre.IndividuMonstre
import kotlin.random.Random
import joueur

class MonsterKube(
    id: Int,
    nom: String,
    description: String,
    var chanceCapture: Double
) : Item(id, nom, description), Utilisable {
    override fun utiliser(cible: IndividuMonstre): Boolean {
        if (cible.entraineur != null) {
            println("Ce monstre appartient déjà à un entraîneur.")
            return false
        }
        val reussi = Random.nextDouble(0.0, 100.0) < chanceCapture
        if (reussi) {
            cible.entraineur = joueur
            joueur.equipeMonstre.add(cible)
            println("${cible.nom} a été capturé !")
        } else {
            println("${cible.nom} s'est échappé !")
        }
        return reussi
    }
}
