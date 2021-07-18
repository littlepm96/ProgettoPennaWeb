package com.example.ProgettoPennaWeb.model.comparators;

import com.example.ProgettoPennaWeb.model.ProgrammaTelevisivo;

import java.util.Comparator;

public class TrasmissioneProgrammaComparator implements Comparator<ProgrammaTelevisivo> {

    /**
     * Comparatore per l'ordinamento usato nella pagina del dettaglio di un canale. Ordina i canali per data di trasmissione e ora di inizio
     * @param p1 il primo programma televisivo da comparare
     * @param p2 il secondo programma televisivo da comparare
     * @return 1 se questo programma viene trasmesso dopo il programma passato come parametro, -1 altrimenti. 0 Se hanno stessa data e ora di trasmissione.
     */
    @Override
    public int compare(ProgrammaTelevisivo p1, ProgrammaTelevisivo p2) {
        //I programmi senza titolo vengono spinti in fondo alla lista
        if(p1.getTitolo()==null||p1.getTitolo().isEmpty()){
            return 1; //questo programma non ha titolo, spingilo giù
        }
        if(p2.getTitolo()==null||p2.getTitolo().isEmpty()){
            return -1; //l'altro programma non ha titolo, spingilo giù
        }
        //Confrontiamo le date di trasmissione
        int dateCompare = p1.getDataTrasmissione().compareTo(p2.getDataTrasmissione());

        //Questo programma è trasmesso in giorno precedente rispetto all'altro
        if(dateCompare < 0){
            return -1;
        }else if(dateCompare>0){//L'altro programma è trasmesso in un giorno precedente rispetto a questo
            return 1;
        }else{ //i programmi vengono trasmessi lo stesso giorno, confronta le date di inizio
            int orarioCompare = p1.getOrarioInizio().compareTo(p2.getOrarioInizio());
            //Questo programma va in onda prima dell'altro
            if(orarioCompare < 0){
                return -1;
            }else if(orarioCompare > 0){//L'altro programma va in onda prima di questo
                return 1;
            }else{ //I due programmi vanno in onda allo stesso momento
                return 0;
            }
        }
    }
}
