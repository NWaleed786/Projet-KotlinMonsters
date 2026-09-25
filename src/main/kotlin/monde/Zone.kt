package monde

import org.example.monstre.EspeceMonstre

// TODO genereMonstre()
// TODO rencontreMonstre()

/**
 * Représente un lieu (route, caverne, mer, etc.) où le joueur peut se déplacer
 * et rencontrer des monstres sauvages.
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
)