package quotesbot;

import com.google.gson.Gson;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JOptionPane;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class LetsCallThisMain {

    public static void doAutomatedVersion() {
        String google = "http://ajax.googleapis.com/ajax/services/search/web?v=1.0&q=";
        String search = "";
        ArrayList<String> listOfPeople = new ArrayList<String>() {
            {
                add("Jesus Christ");
                add("Thomas Jefferson");
                add("Mikhail Gorbachev");
                add("Martin Luther King");
                add("Nelson Mandela");
                add("Buddha");
                add("Winston Churchill");
                add("William Shakespeare");
                add("Adolf Hitler");
                add("Abraham Lincoln");
                add("Muhammad");
                add("St Paul");
                add("George Washington");
                add("Sri Krishna");
                add("Constantine");
                add("Martin Luther");
                add("Socrates");
                add("Karl Marx");
                add("Charles de Gaulle");
                add("Franklin D. Roosevelt");
                add("Charles Darwin");
                add("Sir Isaac Newton");
                add("Akbar");
                add("Queen Victoria");
                add("Konrad Adenauer");
                add("Jawaharlal Nehru");
                add("Ramses II");
                add("Benjamin Franklin");
                add("Confucius");
                add("Alexander the Great");
                add("Woodrow Wilson");
                add("William Wilberforce");
                add("Christopher Columbus");
                add("Marcus Aurelius");
                add("Galileo Galilei");
                add("Mahatma Gandhi");
                add("Joan of Arc");
                add("Charlemagne");
                add("Aristotle");
                add("Mother Teresa");
                add("Saladin");
                add("Simon Bolivar");
                add("Babur");
                add("Sir Walter Raleigh");
                add("Voltaire");
                add("Samuel Johnson");
                add("Catherine the Great");
                add("Mozart");
                add("Princess Diana");
                add("Oscar Wilde");
                add("Napoleon Bonaparte");
                add("Beethoven");
                add("Benjamin Disraeli");
                add("Leonardo da Vinci");
                add("Louis Pasteur");
                add("Leo Tolstoy");
                add("Albert Einstein");
                add("Ataturk");
                add("Pablo Picasso");
                add("Pope John Paul II");
                add("Margaret Thatcher ");
                add("Mohammed Ali");
                add("Aung San Suu Kyi ");
                add("John F. Kennedy");
                add("Lord Nelson");
                add("Boris Yeltsin");
                add("Lyndon Johnson");
                add("Indira Gandhi");
                add("Eva Peron");
                add("William Tyndale");
                add("Tim Berners Lee");
                add("Rosa Parks");
                add("Benazir Bhutto");
                add("Schubert");
                add("Kofi Annan");
                add("Dalai Lama");
                add("Barack Obama");
                add("Albert Schweitzer");
                add("Malcolm X");
                add("Lech Walesa");
                add("John M Keynes");
                add("Mary Wollstonecraft");
                add("Marie Curie");
                add("Elvis Presley");
                add("Oliver Cromwell");
                add("Oprah Winfrey");
                add("Boudicca");
                add("Jesse Owens");
                add("Bill Gates");
                add("Ernest Hemingway");
                add("John Lennon");
                add("Henry Ford");
                add("Genghis Khan");
                add("Haile Selassie");
                add("George Orwell");
                add("Thomas Edison");
                add("Dwight Eisenhower");
                add("Nikola Tesla");
                add("Queen Elizabeth II");
                add("Plato ");
                add("Johann Gutenberg");
                add("John Locke");
                add("Epicurus");
                add("Avicenna");
                add("Thomas Aquinas");
                add("Descartes");
                add("Aristotle");
            }
        };
        Random random = new Random();
        long timeOne = (int) (System.currentTimeMillis() / 1000) % 60;
        long timeTwo;

        while (true) {
            timeTwo = (int) (System.currentTimeMillis() / 1000) % 60;
            if ((timeTwo - timeOne) % 180 == 0) {

                int randomNumber = random.nextInt(listOfPeople.size() + 1);
                search = "site:brainyquote.com \"" + listOfPeople.get(randomNumber) + "\"";

                String charset = "UTF-8";

                URL url = null;
                try {
                    url = new URL(google + URLEncoder.encode(search, charset));
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "ERROR: Couldn't load the URL correctly!");
                    System.exit(1);
                }
                Reader reader = null;
                try {
                    reader = new InputStreamReader(url.openStream(), charset);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "ERROR: Couldn't open the URL Stream correctly!");
                }
                GoogleResults results = new Gson().fromJson(reader, GoogleResults.class);


                String urlOfTheKeyWord = results.getResponseData().getResults().get(0).getUrl();
                ArrayList<BrainyQuote> quotes = new ArrayList<BrainyQuote>();
                BrainyQuote brainyQuote = new BrainyQuote(urlOfTheKeyWord);

                if (brainyQuote.isBrainyQuote()) {
                    Twitter twitter = TwitterFactory.getSingleton();
                    try {
                        String quote = brainyQuote.getRandomQuote();
                        Status newStatus = null;
                        List<Status> statuses = twitter.getHomeTimeline();
                        for (Status status : statuses) {
                            if (!quote.equalsIgnoreCase(status.getText()) && quote.length() < 134) {
                                try {
                                    newStatus = twitter.updateStatus(quote + "#Quote");
                                    Thread.sleep(100000);
                                } catch (Exception e) {
                                    System.err.println("ERROR. PROBABLY DUPLICATE ERROR.");
                                    continue;
                                }
                            }
                        }

                    } catch (TwitterException ex) {
                        JOptionPane.showMessageDialog(null, "ERROR: Something with updating the tweet went wrong.");
                    }
                }
            }
        }


    }

    public static void doNotAutomatedVersion() {
        String search = "site:brainyquote.com \"" + JOptionPane.showInputDialog("Enter a keyword to find quotes for here.") + "\"";
        String google = "http://ajax.googleapis.com/ajax/services/search/web?v=1.0&q=";
        String charset = "UTF-8";

        URL url = null;
        try {
            url = new URL(google + URLEncoder.encode(search, charset));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Reader reader = null;
        try {
            reader = new InputStreamReader(url.openStream(), charset);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        GoogleResults results = new Gson().fromJson(reader, GoogleResults.class);


        String urlOfTheKeyWord = results.getResponseData().getResults().get(0).getUrl();

        BrainyQuote brainyQuote = new BrainyQuote(urlOfTheKeyWord);
        if (brainyQuote.isBrainyQuote()) {
            Twitter twitter = TwitterFactory.getSingleton();
            try {
                String quote = brainyQuote.getRandomQuote();
                Status newStatus = null;
                List<Status> statuses = twitter.getHomeTimeline();
                for (Status status : statuses) {
                    if (!quote.equalsIgnoreCase(status.getText()) && quote.length() < 134) {

                        newStatus = twitter.updateStatus(quote + " #Quote");
                    }
                }

            } catch (TwitterException ex) {
                JOptionPane.showMessageDialog(null, "There was an error tyring to post a tweet!");
            }

        }
    }

    public static void main(String[] args) {

        boolean isAutomated = JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "Do you want this to be automated?");
        if (!isAutomated) {
            doNotAutomatedVersion();
        } else {
            doAutomatedVersion();
        }
    }
}
