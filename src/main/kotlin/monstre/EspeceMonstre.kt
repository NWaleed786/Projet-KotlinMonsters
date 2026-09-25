package org.example.monstre

import java.io.File

/**
 * Représente une espèce de monstre dans le jeu.
 *
 * @property id Identifiant unique de l'espèce.
 * @property nom Nom de l'espèce.
 * @property type Type élémentaire du monstre.
 * @property baseAttaque Statistique de base pour l'attaque.
 * @property baseDefense Statistique de base pour la défense.
 * @property baseVitesse Statistique de base pour la vitesse.
 * @property baseAttaqueSpe Statistique de base pour l'attaque spéciale.
 * @property baseDefenseSpe Statistique de base pour la défense spéciale.
 * @property basePv Statistique de base pour les points de vie.
 * @property modAttaque Modificateur d'attaque lors de la croissance.
 * @property modDefense Modificateur de défense lors de la croissance.
 * @property modVitesse Modificateur de vitesse lors de la croissance.
 * @property modAttaqueSpe Modificateur d'attaque spéciale lors de la croissance.
 * @property modDefenseSpe Modificateur de défense spéciale lors de la croissance.
 * @property modPv Modificateur de points de vie lors de la croissance.
 * @property description Courte description (maximum 255 caractères).
 * @property particularites Particularités propres à l'espèce.
 * @property caractères Traits de caractère de l'espèce.
 */
class EspeceMonstre(
    var id: Int,
    var nom: String,
    var type: String,
    val baseAttaque: Int,
    val baseDefense: Int,
    val baseVitesse: Int,
    val baseAttaqueSpe: Int,
    val baseDefenseSpe: Int,
    val basePv: Int,
    val modAttaque: Double,
    val modDefense: Double,
    val modVitesse: Double,
    val modAttaqueSpe: Double,
    val modDefenseSpe: Double,
    val modPv: Double,
    val description: String = "",
    val particularites: String = "",
    val caractères: String = ""
) {
    fun afficheArt(deFace: Boolean=true): String{
        val nomFichier = if(deFace) "front" else "back";
        val art=  File("src/main/resources/art/${this.nom.lowercase()}/$nomFichier.txt").readText()
        val safeArt = art.replace("/", "∕")
        return safeArt.replace("\\u001B", "\u001B")
    }
}


