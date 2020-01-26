package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class DummyNeighbourGenerator {

    public static final List<Neighbour> DUMMY_NEIGHBOURS = Arrays.asList(
            new Neighbour(1, "Caroline", "http://i.pravatar.cc/150?u=a042581f4e29026704d", "Saint pierre du mont à 5km", "+33 6 86 57 90 14", "www.facebook.fr/caroline", "Bonjour !Je souhaiterais faire de la marche nordique. Pas initiée, je recherche une ou plusieurs personnes susceptibles de m'accompagner !J'aime les jeux de cartes tels la belote et le tarot.."),
            new Neighbour(2, "Jack", "http://i.pravatar.cc/150?u=a042581f4e29026704e", "Mazerolles à 6.2km", "+33 6 74 52 87 96", "www.facebook.fr/jackouille.fripouille", "Je sui chaauuuuu! Se soir ces fiestaaaaa!"),
            new Neighbour(3, "Chloé", "http://i.pravatar.cc/150?u=a042581f4e29026704f", "Grand Moun à 5.4km", "+33 7 52 36 98 56", "www.facebook.fr/chlochette", " \"La poésie est le guide de l'imagination. \n Promenons-nous avec notre âme d'enfant. \n Restons toujours aussi insouciant, \n Afin de préserver nos passions.\""),
            new Neighbour(4, "Vincent", "http://i.pravatar.cc/150?u=a042581f4e29026704a", "Saint pierre du mont à 5km", "+33 6 87 52 41 36", "www.facebook.fr/vincent", "J'aime quand c'est salé, quand c'est sucré, quand c'est épicé, quand c'est cramé...\nEn fait, j'ai tout le temps faim."),
            new Neighbour(5, "Elodie", "https://i.pravatar.cc/150?img=9", "Campet et Lamolere à 8.1km", "+33 6 96 36 52 25", "www.facebook.fr/elodie", "#canyoning #ski #rando #InstaFun"),
            new Neighbour(6, "Sylvain", "http://i.pravatar.cc/150?u=a042581f4e29026704c", "Saint Perdon à 6.3km", "+33 7 82 64 78 94", "www.facebook.fr/sylvain", "Mon coup n'est pas fatal,\nMais je fais parfois mal.\nSouvent je suis dressée,\nEt sent bon la marrée.\nQui suiiiis-je?"),
            new Neighbour(7, "Laetitia", "http://i.pravatar.cc/150?u=a042581f4e29026703d", "Paix Taoushnok à +∞km", "+33 6 02 40 56 81", "www.facebook.fr/laetitia.nddl", "Le gouvernement est un voleur, ne nous laissons plus avoir! Faisons la greve de la faim, et quand nous serons morts alors le gouvernement ne nous fera plus payer d'impôrts! #GJ"),
            new Neighbour(8, "Ken", "http://i.pravatar.cc/150?u=a042581f4e29026703b", "Saint Vivant de l'Enfer à 666km", "+33 6 23 40 52 12", "www.facebook.fr/omaewamou.shindeiru", "NANI?!"),
            new Neighbour(9, "Delphine", "http://i.pravatar.cc/150?img=40", "Sainte Baignoire à 18km", "+33 6 12 50 13 45", "www.facebook.fr/delphine.jolie", "Heyyy boys! Do you wanna sum water of my bath? owo"),
            new Neighbour(10, "Emma", "http://i.pravatar.cc/150?u=a042581f4e29026706d", "Benquet à 15km", "+33 6 96 30 25 40", "www.facebook.fr/emmaeva", "\"Vous savez, moi je ne crois pas qu’il y ait de bonne ou de mauvaise situation. Moi, si je devais résumer ma vie aujourd’hui avec vous, je dirais que c’est d’abord des rencontres. Des gens qui m’ont tendu la main, peut-être à un moment où je ne pouvais pas, où j’étais seul chez moi. Et c’est assez curieux de se dire que les hasards, les rencontres forgent une destinée… Parce que quand on a le goût de la chose, quand on a le goût de la chose bien faite, le beau geste, parfois on ne trouve pas l’interlocuteur en face je dirais, le miroir qui vous aide à avancer. Alors ça n’est pas mon cas, comme je disais là, puisque moi au contraire, j’ai pu : et je dis merci à la vie, je lui dis merci, je chante la vie, je danse la vie… je ne suis qu’amour ! Et finalement, quand beaucoup de gens aujourd’hui me disent « Mais comment fais-tu pour avoir cette humanité ? », et bien je leur réponds très simplement, je leur dis que c’est ce goût de l’amour ce goût donc qui m’a poussé aujourd’hui à entreprendre une construction mécanique, mais demain qui sait ? Peut-être simplement à me mettre au service de la communauté, à faire le don, le don de soi…\""),
            new Neighbour(11, "Patrick", "http://i.pravatar.cc/150?u=a042581f4e29026702d", "Camping Les Flots Bleus à 85km", "+33 6 87 52 14 10", "www.facebook.fr/patrick", "Bah alors, on m'attends pas là?!"),
            new Neighbour(12, "Ludovic", "http://i.pravatar.cc/150?u=a042581f3e39026702d", "Bascons à 12.6km", "+33 6 20 06 98 49", "www.facebook.fr/ludovic", "Salut les neuneu! Alors aujourd'hui, pour votre petite bouche, ça va être un tuto \"TAISEZ-VOUS, MERCI!\" Vous allez voir, on va bien s'amuser! Hihihi *patate bien placée* ")
    );

    static List<Neighbour> generateNeighbours() {
        return new ArrayList<>(DUMMY_NEIGHBOURS);
    }
}
