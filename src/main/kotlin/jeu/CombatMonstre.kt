package org.example.jeu

import joueur
import org.example.item.Utilisable
import org.example.monstre.IndividuMonstre

class CombatMonstre(
    var monstreJoueur: IndividuMonstre,
    var monstreSauvage: IndividuMonstre
) {
    var round: Int = 1
    private var fuite: Boolean = false
    private var experienceDonnee: Boolean = false

    /**
     * Vérifie si le joueur a perdu le combat.
     *
     * Condition de défaite :
     * - Aucun monstre de l'équipe du joueur n'a de PV > 0.
     *
     * @return `true` si le joueur a perdu, sinon `false`.
     */
    fun gameOver(): Boolean {
        for (monstre in joueur.equipeMonstre) {
            if (monstre.pv > 0) return false
        }
        return true
    }

    fun joueurGagne(): Boolean {
        if (monstreSauvage.pv == 0) {
            if (!experienceDonnee) {
                println("${joueur.nom} a gagné !")
                val gainExp = monstreSauvage.exp * 0.20
                monstreJoueur.exp += gainExp
                println("${monstreJoueur.nom} gagne $gainExp points d'expérience.")
                experienceDonnee = true
            }
            return true
        }
        if (monstreSauvage.entraineur == joueur) {
            println("${monstreSauvage.nom} a été capturé !")
            return true
        }
        return false
    }

    fun actionAdversaire() {
        if (monstreSauvage.pv > 0) {
            monstreSauvage.attaquer(monstreJoueur)
        }
    }

    fun actionJoueur(): Boolean {
        if (gameOver()) return false
        while (true) {
            println("1 : Attaquer")
            println("2 : Utiliser un objet")
            println("3 : Changer de monstre")
            println("4 : Fuir")
            println("5 : Attendre")
            val choix = readlnOrNull()
            if (choix == null) {
                fuite = true
                return false
            }
            when (choix) {
                "1" -> {
                    if (monstreJoueur.pv == 0) {
                        println("Ce monstre est KO, change de monstre.")
                        continue
                    }
                    monstreJoueur.attaquer(monstreSauvage)
                    return true
                }
                "2" -> {
                    if (joueur.sacAItems.isEmpty()) {
                        println("Le sac est vide.")
                        continue
                    }
                    for (i in joueur.sacAItems.indices) {
                        println("${i + 1} : ${joueur.sacAItems[i].nom}")
                    }
                    val numero = readlnOrNull()?.toIntOrNull()
                    if (numero == null || numero !in 1..joueur.sacAItems.size) {
                        println("Choix incorrect.")
                        continue
                    }
                    val objetChoisi = joueur.sacAItems[numero - 1]
                    if (objetChoisi is Utilisable) {
                        val captureReussie = objetChoisi.utiliser(monstreSauvage)
                        return !captureReussie
                    }
                    println("Objet non utilisable.")
                    return true
                }
                "3" -> {
                    for (i in joueur.equipeMonstre.indices) {
                        val monstre = joueur.equipeMonstre[i]
                        if (monstre.pv > 0) {
                            println("${i + 1} : ${monstre.nom} (${monstre.pv}/${monstre.pvMax} PV)")
                        }
                    }
                    val numero = readlnOrNull()?.toIntOrNull()
                    if (numero == null || numero !in 1..joueur.equipeMonstre.size) {
                        println("Choix incorrect.")
                        continue
                    }
                    val choixMonstre = joueur.equipeMonstre[numero - 1]
                    if (choixMonstre.pv == 0) {
                        println("Impossible ! Ce monstre est KO.")
                        continue
                    }
                    println("${choixMonstre.nom} remplace ${monstreJoueur.nom}.")
                    monstreJoueur = choixMonstre
                    return true
                }
                "4" -> {
                    fuite = true
                    println("Tu as fui le combat.")
                    return false
                }
                "5" -> return true
                else -> println("Choix incorrect.")
            }
        }
    }

    fun afficheCombat() {
        println("======== Début du Round : $round ========")
        println("${monstreSauvage.nom} | Niveau : ${monstreSauvage.niveau}")
        println("PV : ${monstreSauvage.pv}/${monstreSauvage.pvMax}")
        println(monstreSauvage.espece.afficheArt())
        println(monstreJoueur.espece.afficheArt(false))
        println("${monstreJoueur.nom} | Niveau : ${monstreJoueur.niveau}")
        println("PV : ${monstreJoueur.pv}/${monstreJoueur.pvMax}")
    }

    fun jouer() {
        val joueurPlusRapide = monstreJoueur.vitesse >= monstreSauvage.vitesse
        afficheCombat()
        if (joueurPlusRapide) {
            if (!actionJoueur()) return
            actionAdversaire()
        } else {
            actionAdversaire()
            if (!gameOver()) {
                if (!actionJoueur()) return
            }
        }
    }

    /**
     * Lance le combat et gère les rounds jusqu'à la victoire ou la défaite.
     *
     * Affiche un message de fin si le joueur perd et restaure les PV
     * de tous ses monstres.
     */
    fun lanceCombat() {
        while (!fuite && !gameOver() && !joueurGagne()) {
            jouer()
            println("======== Fin du Round : $round ========")
            round++
        }
        if (gameOver()) {
            joueur.equipeMonstre.forEach { it.pv = it.pvMax }
            println("Game Over !")
        }
    }
}
