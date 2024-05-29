import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, deckSize;

    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());
        deckSize = N << 1;

        Deck deck = new Deck(0, new LinkedList<>());
        String line = br.readLine();
        for (int i = 0; i < deckSize; i++) {
            deck.cards.add(line.charAt(i<<1));
        }

        Queue<Deck> queue = new ArrayDeque<>();
        queue.add(deck);
        while (!queue.isEmpty()) {
            Deck t = queue.poll();

            if (checkResult(t)) {
                System.out.println(t.swapCnt);
                break;
            }

            for (int i = 1; i < deckSize; i++) {
                Deck nDeck = new Deck(t.swapCnt + 1, new LinkedList<>(t.cards));
                nDeck.cards.addFirst(nDeck.cards.remove(i));
                queue.add(nDeck);
            }
        }
    }

    public static boolean checkResult(Deck deck) {
        int winCnt = 0;
        int drawCnt = 0;
        for (int i = 0; i < deckSize; i++) {
            char js = deck.cards.get(i);
            char sh = deck.cards.get(++i);

            if (js == sh) {
                drawCnt++;
            } else if (js == 'O' && sh == 'X') {
                winCnt++;
            }
        }
        return winCnt > (N-(winCnt+drawCnt));
    }

    static class Deck {
        int swapCnt;
        LinkedList<Character> cards;
        public Deck(int swapCnt, LinkedList<Character> cards) {
            this.swapCnt = swapCnt;
            this.cards = cards;
        }
    }
}
