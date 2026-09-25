
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import monde.Zone
import org.example.dresseur.Entraineur

import org.example.monstre.EspeceMonstre


//Especes
val especeSpringleaf = EspeceMonstre(
    id = 1,
    nom = "Springleaf",
    type = "Graine",
    baseAttaque = 60,
    baseDefense = 9,
    baseVitesse = 11,
    baseAttaqueSpe = 10,
    baseDefenseSpe = 12,
    basePv = 14,
    modAttaque = 3.0,
    modDefense = 4.0,
    modVitesse = 6.0,
    modAttaqueSpe = 5.0,
    modDefenseSpe = 9.0,
    modPv = 8.0,
    description = "Petit monstre espiègle rond comme une graine, adore le soleil. Sa feuille sur la tête indique son humeur.",
    particularites = "",
    caractères = "Curieux, amical, timide"
)
val especeFlamkip = EspeceMonstre(
    id = 4,
    nom = "Flamkip",
    type = "Animal",
    baseAttaque = 50,
    baseDefense = 12,
    baseVitesse = 8,
    baseAttaqueSpe = 13,
    baseDefenseSpe = 16,
    basePv = 7,
    modAttaque = 2.0,
    modDefense = 2.0,
    modVitesse = 10.0,
    modAttaqueSpe = 5.5,
    modDefenseSpe = 9.0,
    modPv = 9.5,
    description = "Petit animal entouré de flammes, déteste le froid. Sa flamme change d’intensité selon son énergie.",
    particularites = "",
    caractères = "Impulsif, joueur, loyal"
)
val especeAquamy = EspeceMonstre(
    id = 7,
    nom = "Aquamy",
    type = "Meteo",
    baseAttaque = 55,
    baseDefense = 10,
    baseVitesse = 11,
    baseAttaqueSpe = 9,
    baseDefenseSpe = 14,
    basePv = 14,
    modAttaque = 2.0,
    modDefense = 7.0,
    modVitesse = 9.0,
    modAttaqueSpe = 10.0,
    modDefenseSpe = 7.5,
    modPv = 12.0,
    description = "Créature vaporeuse semblable à un nuage, produit des gouttes pures. Fait baisser la température en s’endormant.",
    particularites = "",
    caractères = "Calme, rêveur, mystérieux"
)
val especeLaoumi = EspeceMonstre(
    id = 8,
    nom = "Laoumi",
    type = "Animal",
    baseAttaque = 58,
    baseDefense = 11,
    baseVitesse = 10,
    baseAttaqueSpe = 9,
    baseDefenseSpe = 8,
    basePv = 11,
    modAttaque = 2.0,
    modDefense = 3.0,
    modVitesse = 11.0,
    modAttaqueSpe = 8.0,
    modDefenseSpe = 7.0,
    modPv = 6.0,
    description = "Petit ourson au pelage soyeux, aime se tenir debout. Son grognement est mignon mais il protège ses amis.",
    particularites = "",
    caractères = "Affectueux, protecteur, gourmand"
)
val especeBugsyface = EspeceMonstre(
    id = 10,
    nom = "Bugsyface",
    type = "Insecte",
    baseAttaque = 45,
    baseDefense = 10,
    baseVitesse = 13,
    baseAttaqueSpe = 8,
    baseDefenseSpe = 7,
    basePv = 13,
    modAttaque = 2.0,
    modDefense = 1.0,
    modVitesse = 7.0,
    modAttaqueSpe = 11.0,
    modDefenseSpe = 6.0,
    modPv = 5.0,
    description = "Insecte à carapace luisante, se déplace par bonds et vibre des antennes. Sa carapace devient plus dure après chaque mue.",
    particularites = "",
    caractères = "Travailleur, sociable, infatigable"
)
val especeGalum = EspeceMonstre(
    id = 13,
    nom = "Galum",
    type = "Minéral",
    baseAttaque = 55,
    baseDefense = 12,
    baseVitesse = 15,
    baseAttaqueSpe = 6,
    baseDefenseSpe = 8,
    basePv = 12,
    modAttaque = 1.0,
    modDefense = 3.0,
    modVitesse = 9.0,
    modAttaqueSpe = 13.0,
    modDefenseSpe = 4.0,
    modPv = 6.5,
    description = "Golem ancien de pierre, yeux lumineux en garde. Peut rester immobile des heures comme une statue.",
    particularites = "",
    caractères = "Sérieux, stoïque, fiable"
)
var joueur = Entraineur(1,"Sacha",100)
var rival = Entraineur(2,"Regis",200)

val route1 = Zone(
    id = 1,
    nom = "Route 1",
    expZone = 5,
    especesMonstres = mutableListOf(especeSpringleaf, especeFlamkip)
)

val route2 = Zone(
    id = 2,
    nom = "Route 2",
    expZone = 10,
    especesMonstres = mutableListOf(especeAquamy, especeLaoumi, especeBugsyface)
)

fun main() {
    route1.zoneSuivante = route2
    route2.zonePrecedente = route1

    joueur.afficheDetail()
    rival.afficheDetail()
    joueur.argents+=50
    joueur.afficheDetail()

}

/**
 * Change la couleur du message donné selon le nom de la couleur spécifié.
 * Cette fonction utilise les codes d'échappement ANSI pour appliquer une couleur à la sortie console. Si un nom de couleur
 * non reconnu ou une chaîne vide est fourni, aucune couleur n'est appliquée.
 *
 * @param message Le message auquel la couleur sera appliquée.
 * @param couleur Le nom de la couleur à appliquer (ex: "rouge", "vert", "bleu"). Par défaut c'est une chaîne vide, ce qui n'applique aucune couleur.
 * @return Le message coloré sous forme de chaîne, ou le même message si aucune couleur n'est appliquée.
 */
fun changeCouleur(message: String, couleur:String=""): String {
    val reset = "\u001B[0m"
    val codeCouleur = when (couleur.lowercase()) {
        "rouge" -> "\u001B[31m"
        "vert" -> "\u001B[32m"
        "jaune" -> "\u001B[33m"
        "bleu" -> "\u001B[34m"
        "magenta" -> "\u001B[35m"
        "cyan" -> "\u001B[36m"
        "blanc" -> "\u001B[37m"
        else -> "" // pas de couleur si non reconnu
    }
    return "$codeCouleur$message$reset"
}

