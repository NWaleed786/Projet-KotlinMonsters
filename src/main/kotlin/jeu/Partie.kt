package org.example.jeu

import especeSpringleaf
import especeFlamkip
import especeAquamy
import monde.Zone
import org.example.dresseur.Entraineur
import org.example.monstre.IndividuMonstre

/** Deux objets Partie peuvent exister dans le même jeu. */
class Partie(
    var id: Int,
    var joueur: Entraineur,
    var zone: Zone
) {
    fun choixStarter() {
        val monstre1 = IndividuMonstre(1, "Springleaf", especeSpringleaf, null, 1500.0)
        val monstre2 = IndividuMonstre(2, "Flamkip", especeFlamkip, null, 1500.0)
        val monstre3 = IndividuMonstre(3, "Aquamy", especeAquamy, null, 1500.0)
        while (true) {
            monstre1.afficheDetail()
            monstre2.afficheDetail()
            monstre3.afficheDetail()
            println("Choisis ton premier monstre : 1 Springleaf, 2 Flamkip, 3 Aquamy")
            val choix = readlnOrNull() ?: return
            val starter = when (choix) {
                "1" -> monstre1
                "2" -> monstre2
                "3" -> monstre3
                else -> {
                    println("Choix incorrect.")
                    continue
                }
            }
            starter.renommer()
            joueur.equipeMonstre.add(starter)
            starter.entraineur = joueur
            return
        }
    }

    fun modifierOrdreEquipe() {
        if (joueur.equipeMonstre.size < 2) {
            println("Il faut au moins deux monstres pour modifier l'ordre.")
            return
        }
        println("Position du monstre à déplacer :")
        val position = readlnOrNull()?.toIntOrNull()
        println("Nouvelle position :")
        val nouvellePosition = readlnOrNull()?.toIntOrNull()
        if (position == null || nouvellePosition == null ||
            position !in 1..joueur.equipeMonstre.size ||
            nouvellePosition !in 1..joueur.equipeMonstre.size) {
            println("Position incorrecte.")
            return
        }
        val monstre = joueur.equipeMonstre[position - 1]
        joueur.equipeMonstre[position - 1] = joueur.equipeMonstre[nouvellePosition - 1]
        joueur.equipeMonstre[nouvellePosition - 1] = monstre
    }

    fun examineEquipe() {
        while (true) {
            for (i in joueur.equipeMonstre.indices) {
                val monstre = joueur.equipeMonstre[i]
                println("${i + 1} : ${monstre.nom} | Niveau ${monstre.niveau} | PV ${monstre.pv}/${monstre.pvMax}")
            }
            println("Numéro : voir les détails | m : modifier l'ordre | q : retour")
            val choix = readlnOrNull() ?: return
            when (choix) {
                "q" -> return
                "m" -> modifierOrdreEquipe()
                else -> {
                    val numero = choix.toIntOrNull()
                    if (numero != null && numero in 1..joueur.equipeMonstre.size) {
                        joueur.equipeMonstre[numero - 1].afficheDetail()
                    } else {
                        println("Choix incorrect.")
                    }
                }
            }
        }
    }

    fun jouer() {
        while (true) {
            println("Zone actuelle : ${zone.nom}")
            println("1 : Rencontrer un monstre sauvage")
            println("2 : Examiner l'équipe")
            println("3 : Aller à la zone suivante")
            println("4 : Aller à la zone précédente")
            val choix = readlnOrNull() ?: return
            when (choix) {
                "1" -> zone.rencontreMonstre()
                "2" -> examineEquipe()
                "3" -> {
                    val suivante = zone.zoneSuivante
                    if (suivante != null) zone = suivante
                    else println("Il n'y a pas de zone suivante.")
                }
                "4" -> {
                    val precedente = zone.zonePrecedente
                    if (precedente != null) zone = precedente
                    else println("Il n'y a pas de zone précédente.")
                }
                else -> println("Choix incorrect.")
            }
        }
    }
}
