package org.example.monstre

import org.example.dresseur.Entraineur
import kotlin.math.pow
import kotlin.random.Random

/**
 * Représente chaque individu, c'est-à-dire les monstres avec lesquels le joueur va interagir
 * (les monstres sauvages, les monstres de l'équipe du joueur, les monstres des autres dresseurs).
 * Deux individus peuvent appartenir à la même espèce exemple Canaros.
 */
class IndividuMonstre(
    val id: Int,
    var nom: String,
    val espece: EspeceMonstre,
    val entraineur: Entraineur?,
    expInit: Double
) {
    var niveau: Int = 1
    var attaque: Int = espece.baseAttaque + Random.nextInt(-2, 3)
    var defense: Int = espece.baseDefense + Random.nextInt(-2, 3)
    var vitesse: Int = espece.baseVitesse + Random.nextInt(-2, 3)
    var attaqueSpe: Int = espece.baseAttaqueSpe + Random.nextInt(-2, 3)
    var defenseSpe: Int = espece.baseDefenseSpe + Random.nextInt(-2, 3)
    var pvMax: Int = espece.basePv + Random.nextInt(-5, 6)
    var potentiel: Double = Random.nextDouble(0.5, 2.000001)

    var exp: Double = 0.0
        get() = field
        set(value) {
            field = value
            while (field >= palierExp(niveau + 1)) {
                levelUp()
            }
        }

    /**
     * @property pv Points de vie actuels.
     * Ne peut pas être inférieur à 0 ni supérieur à [pvMax].
     */
    var pv: Int = pvMax
        get() = field
        set(value) {
            if (value < 0) {
                field = 0
            } else if (value > pvMax) {
                field = pvMax
            } else {
                field = value
            }
        }

    init {
        this.exp = expInit // applique le setter et déclenche un éventuel level-up
    }

    /**
     * Calcule l'expérience totale nécessaire pour atteindre un niveau donné.
     *
     * @param niveau Niveau cible.
     * @return Expérience cumulée nécessaire pour atteindre ce niveau.
     */
    fun palierExp(niveau: Int): Double {
        return 100 * (niveau - 1).toDouble().pow(2.0)
    }

    /** Augmente le niveau et les caractéristiques du monstre. */
    fun levelUp() {
        niveau++
        val pvMaxAvant = pvMax

        attaque += Math.round(espece.modAttaque * potentiel).toInt() + Random.nextInt(-2, 3)
        defense += Math.round(espece.modDefense * potentiel).toInt() + Random.nextInt(-2, 3)
        vitesse += Math.round(espece.modVitesse * potentiel).toInt() + Random.nextInt(-2, 3)
        attaqueSpe += Math.round(espece.modAttaqueSpe * potentiel).toInt() + Random.nextInt(-2, 3)
        defenseSpe += Math.round(espece.modDefenseSpe * potentiel).toInt() + Random.nextInt(-2, 3)
        pvMax += Math.round(espece.modPv * potentiel).toInt() + Random.nextInt(-5, 6)

        pv += (pvMax - pvMaxAvant)
    }
    /**
     * Attaque un autre monstre. Les dégâts valent attaque - défense / 2,
     * avec au moins 1 point de dégât.
     */
    fun attaquer(cible: IndividuMonstre) {
        if (pv == 0 || cible.pv == 0) return
        val degats = maxOf(1, attaque - cible.defense / 2)
        cible.pv -= degats
        println("$nom attaque ${cible.nom} et inflige $degats dégâts.")
    }

    /** Demande un nouveau nom. Une réponse vide conserve le nom actuel. */
    fun renommer() {
        print("Nouveau nom de $nom : ")
        val nouveauNom = readlnOrNull()?.trim()
        if (!nouveauNom.isNullOrEmpty()) nom = nouveauNom
    }

    /** Affiche les caractéristiques et l'art du monstre. */
    fun afficheDetail() {
        println("Nom : $nom (${espece.nom})")
        println("Niveau : $niveau | Expérience : $exp")
        println("PV : $pv/$pvMax")
        println("Attaque : $attaque | Défense : $defense | Vitesse : $vitesse")
        println("Attaque spéciale : $attaqueSpe | Défense spéciale : $defenseSpe")
        println(espece.afficheArt())
    }
}
