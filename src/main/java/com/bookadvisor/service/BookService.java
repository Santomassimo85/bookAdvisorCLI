package com.bookadvisor.service;

import com.bookadvisor.model.BookDto;
import com.bookadvisor.factory.BookDtoBuilder;
import com.bookadvisor.factory.BookDtoFactory;
import com.bookadvisor.composite.BookGroup;
import com.bookadvisor.composite.BookLeaf;
import com.bookadvisor.util.AppLogger;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.logging.Logger;

import org.json.*;

/**
 * BookService is a service class that handles the logic for searching books
 * using the Open Library API.
 * It fetches book data based on a search query and returns a list of BookDto
 * objects.
 */
public class BookService {
    /**
     * Logger instance for logging messages and errors.
     * This logger is used to log search results and any errors that occur during
     * the search process.
     */
    private static final Logger logger = AppLogger.getInstance().getLogger();

    /**
     * Searches for books using the Open Library API based on the provided query.
     *
     * @param query The search query for finding books.
     * @return A list of BookDto objects containing book information.
     */
    public List<BookDto> searchBooks(String query) {
        final List<BookDto> resultList = Collections.synchronizedList(new ArrayList<>());

        Thread searchThread = new Thread(() -> {
            try {
                String urlString = "https://openlibrary.org/search.json?q=" + query.replace(" ", "+");
                URL url = java.net.URI.create(urlString).toURL();
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");

                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder json = new StringBuilder();
                String line;
                while ((line = in.readLine()) != null)
                    json.append(line);
                in.close();

                JSONObject obj = new JSONObject(json.toString());
                JSONArray docs = obj.getJSONArray("docs");

                BookGroup results = new BookGroup("Risultati");

                for (int i = 0; i < Math.min(10, docs.length()); i++) {
                    JSONObject d = docs.getJSONObject(i);
                    String title = d.optString("title");
                    String author = d.has("author_name") ? d.getJSONArray("author_name").optString(0) : "Sconosciuto";
                    String coverUrl = d.has("cover_i")
                            ? "https://covers.openlibrary.org/b/id/" + d.getInt("cover_i") + "-M.jpg"
                            : null;
                    String publishDate = d.has("first_publish_year") ? String.valueOf(d.getInt("first_publish_year"))
                            : "N.D.";
                    String key = d.optString("key");

                    // Descrizione (opzionale)
                    String description = "";
                    if (!key.isBlank()) {
                        try {
                            URL workUrl = new URL("https://openlibrary.org" + key + ".json");
                            HttpURLConnection workConn = (HttpURLConnection) workUrl.openConnection();
                            workConn.setRequestMethod("GET");

                            BufferedReader workIn = new BufferedReader(
                                    new InputStreamReader(workConn.getInputStream()));
                            StringBuilder workJson = new StringBuilder();
                            String workLine;
                            while ((workLine = workIn.readLine()) != null)
                                workJson.append(workLine);
                            workIn.close();

                            JSONObject workObj = new JSONObject(workJson.toString());
                            if (workObj.has("description")) {
                                Object descObj = workObj.get("description");
                                if (descObj instanceof JSONObject) {
                                    description = ((JSONObject) descObj).optString("value", "");
                                } else {
                                    description = descObj.toString();
                                }
                                if (description.length() > 150) {
                                    description = description.substring(0, 150) + "...";
                                }
                            }
                        } catch (Exception ex) {
                            description = "";
                        }
                    }

                    BookDto dto = new BookDtoBuilder()
                            .title(title)
                            .author(author)
                            .coverUrl(coverUrl)
                            .publishDate(publishDate)
                            .description(description)
                            .build();

                    results.add(new BookLeaf(dto));
                }

                resultList.addAll(results.toList());

            } catch (Exception e) {
                logger.severe("❌ Errore durante la ricerca: " + e.getMessage());
            }
        });

        searchThread.start();

        try {
            searchThread.join(); // aspetta il completamento
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        return resultList;
    }

}
