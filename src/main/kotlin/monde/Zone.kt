package monde

import org.example.monstre.EspeceMonstre
import java.time.LocalDateTime
import joueur
import org.example.jeu.CombatMonstre
import org.example.monstre.IndividuMonstre
import kotlin.random.Random

/**
 * Représente un lieu (route, caverne, mer, etc.) où le joueur peut se déplacer
 * et rencontrer des monstres sauvages. Les zones forment une chaîne de routes.
 *
 * @property id Identifiant unique de la zone.
 * @property nom Nom de la zone.
 * @property expZone Niveau d'expérience associé à la zone.
 * @property especesMonstres Liste mutable des différentes espèces de monstres trouvables dans cette zone.
 * @property zoneSuivante Référence vers la zone suivante (ou null s'il n'y en a pas).
 * @property zonePrecedente Référence vers la zone précédente (ou null s'il n'y en a pas).
 */
class Zone(
    var id: Int,
    var nom: String,
    var expZone: Int,
    var especesMonstres: MutableList<EspeceMonstre> = mutableListOf(),
    var zoneSuivante: Zone? = null,
    var zonePrecedente: Zone? = null
) {
    fun genereMonstre(): IndividuMonstre {
        val espece = especesMonstres.random()
        val experience = expZone * Random.nextDouble(0.8, 1.2)
        return IndividuMonstre(0, espece.nom, espece, null, experience)
    }

    fun rencontreMonstre() {
        if (especesMonstres.isEmpty()) {
            println("Il n'y a pas de monstre sauvage dans cette zone.")
            return
        }
        val monstreSauvage = genereMonstre()
        for (monstre in joueur.equipeMonstre) {
            if (monstre.pv > 0) {
                val combat = CombatMonstre(monstre, monstreSauvage)
                combat.lanceCombat()
                return
            }
        }
        println("Aucun monstre de l'équipe ne peut combattre.")
    }
}
