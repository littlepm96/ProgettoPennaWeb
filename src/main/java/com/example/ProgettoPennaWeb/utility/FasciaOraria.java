package com.example.ProgettoPennaWeb.utility;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;

//Classe di utility per la conversione delle fasce orarie
public class FasciaOraria {
    private static final String PART_SEPARATOR = "-";
    private static final String SEPARATOR = ";";

    //Costanti usati nel controllo della formattazione
    private static final int LUNGHEZZA_STRINGA_DI_ENCODING_SINGOLA = 11; //hh:mm<separatore>hh:mm , per un totale di 11 caratteri
    private static final int POSIZIONE_DEL_SEPARATORE = 5;

    /**
     * Ritorna il separatore usato per distinguere le fasce orarie encodate in una stringa
     * @return il carattere separatore di fasce orarie
     */
    public static String getSeparator(){
        return SEPARATOR;
    }

    /**
     * Ritorna il separatore usato per distinguere le due parti che compongono una fascia oraria (inizio e fine).
     * @return il carattere separatore delle due parti di una fascia oraria
     */
    public static String getPartSeparator(){
        return PART_SEPARATOR;
    }

    /**
     * Crea una stringa che rappresenta la fascia oraria data dai due orari in input
     * @return una stringa che rappresenta la fascia oraria data dagli orari passati nei parametri
     * @throws MalformedFasciaOrariaException se gli orari contengono secondi o millisecondi (l'applicazione non ne fa uso)
     */
    public static String encode(LocalTime inizio, LocalTime fine) throws MalformedFasciaOrariaException {
        if(inizio.getSecond() > 0 || inizio.getNano() > 0 || fine.getSecond() > 0 || fine.getNano() > 0){
            throw new MalformedFasciaOrariaException("Gli orari non devono contenere secondi o millisecondi! Riprova dopo averli azzerati.");
        }
        return inizio.toString() + getPartSeparator() + fine.toString();
    }

    /**
     * Decodifica una stringa che rappresenta una fascia oraria nelle sue due parti (inizio e fine)
     * @param stringaFasciaOraria stringa che rappresenta la fascia oraria da decodificare
     * @return un array contenente le due parti della fascia oraria: indice 0 = ora di inizio, indice 1 = ora di fine
     * @throws MalformedFasciaOrariaException se la stringa non rispetta la formattazione attesa da una fascia oraria, ovvero lunghezza di 11 caratteri, con il separatore in posizione 5 e orari del tipo "hh:mm"
     */
    public static LocalTime[] decode(String stringaFasciaOraria) throws MalformedFasciaOrariaException {
        if(stringaFasciaOraria.length()== LUNGHEZZA_STRINGA_DI_ENCODING_SINGOLA){
            //La lunghezza ?? corretta, controlliamo la formattazione
            //controllo che il separatore si trovi dove ce lo aspettiamo
            if(stringaFasciaOraria.indexOf(getPartSeparator())==POSIZIONE_DEL_SEPARATORE){
                //a questo punto splitto la stringa nell'array contenente le due parti, sollevando eccezione in caso ci sia un problema nel parsing
                String[] stringaSplittata = stringaFasciaOraria.split(getPartSeparator());
                try {
                    LocalTime[] decoded = new LocalTime[]{LocalTime.parse(stringaSplittata[0]), LocalTime.parse(stringaSplittata[1])};
                    return decoded;
                }catch (DateTimeParseException pe){
                    throw new MalformedFasciaOrariaException("Fascia oraria malformata: errore nel parsing dei due orari.", pe);
                }
            }else{
                throw new MalformedFasciaOrariaException("Fascia oraria malformata: il separatore non si trova nella posizione attesa. (posizione trovata: "+stringaFasciaOraria.indexOf(getPartSeparator())+", valore atteso: "+POSIZIONE_DEL_SEPARATORE+").");
            }

        }else{
            throw new MalformedFasciaOrariaException("Si sta tentando di decodificare una fascia oraria di lunghezza errata! (lunghezza: "+stringaFasciaOraria.length()+", valore atteso: "+LUNGHEZZA_STRINGA_DI_ENCODING_SINGOLA+").");
        }
    }

    /**
     * Corregge la formattazione di questa stringa (aggiunge gli zeri davanti a ore e minuti inferiori a 10)
     * @param orario
     * @return La stringa correttamente formattata
     * @throws MalformedFasciaOrariaException se la stringa passata non rappresenta un orario valido
     */
    public static String formatOrario(String orario) throws IllegalArgumentException{
        int lunghezzaIniziale = orario.length();
        int posizioneSeparatore = orario.indexOf(":");
        if(posizioneSeparatore < 1 || posizioneSeparatore > 2){
            throw new IllegalArgumentException("La stringa che si sta tentando di formattare non ?? valida (Stringa: "+orario+").");
        }
        if(lunghezzaIniziale - posizioneSeparatore < 2 || lunghezzaIniziale - posizioneSeparatore > 3){//hh:mm len=5, ind=2; h:mm len=4, ind=1; hh:m len=4, ind=2; h:m len=3, ind=1 QUESTI SONO OK
            throw new IllegalArgumentException("La stringa che si sta tentando di formattare non ?? valida (Stringa: "+orario+").");
        }

        //Ora siamo sicuri che la stringa ?? formattabile
        StringBuilder sb = new StringBuilder();
        String ore = orario.substring(0,posizioneSeparatore);
        String minuti = orario.substring(posizioneSeparatore+1);

        if(ore.length()==1){
            sb.append("0"); sb.append(ore);
        }else{
            sb.append(ore);
        }

        sb.append(":");

        if(minuti.length()==1){
            sb.append("0"); sb.append(minuti);
        }else{
            sb.append(minuti);
        }

        return sb.toString();
    }

    public static boolean isContained(LocalTime orario, String fasciaOrariaCodificata) throws MalformedFasciaOrariaException {
        //decodifichiamo la fascia oraria in inizio e fine
        LocalTime[] fasciaOraria = decode(fasciaOrariaCodificata);

        //controlliamo che l'orario passato cadi dentro questa fascia oraria
        if(orario.compareTo(fasciaOraria[0])>= 0 && orario.compareTo(fasciaOraria[1])<=0){
            return true;
        }
        else{
            return false;
        }
    }
}
