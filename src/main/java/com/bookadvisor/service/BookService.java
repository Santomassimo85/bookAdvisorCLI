package com.bookadvisor.service;

import com.bookadvisor.model.BookDto;
import com.bookadvisor.factory.BookDtoFactory;
import com.bookadvisor.composite.BookGroup;
import com.bookadvisor.composite.BookLeaf;
import com.bookadvisor.composite.BookComponent;
import com.bookadvisor.util.AppLogger;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.logging.Logger;

import org.json.*;

/**
 * BookService is a service class that handles the logic for searching books using the Open Library API.
 * It fetches book data based on a search query and returns a list of BookDto objects.
 */
public class BookService {
/**
 * Logger instance for logging messages and errors.
 * This logger is used to log search results and any errors that occur during the search process.
 */
    private static final Logger logger = AppLogger.getLogger();

    /**
     * Searches for books using the Open Library API based on the provided query.
     *
     * @param query The search query for finding books.
     * @return A list of BookDto objects containing book information.
     */
    public List<BookDto> searchBooks(String query) {
        List<BookDto> resultList = new ArrayList<>();
        try {
            // Construct the URL for the Open Library API
            String urlString = "https://openlibrary.org/search.json?q=" + query.replace(" ", "+");

            // Open a HTTP connection to the API using URI and URL (avoid deprecated constructor)
            java.net.URI uri = new java.net.URI(urlString);
            HttpURLConnection conn = (HttpURLConnection) uri.toURL().openConnection();
            conn.setRequestMethod("GET");

            // Read the response from the API
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder json = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) json.append(line);
            in.close();

            // Parse the JSON response
            JSONObject obj = new JSONObject(json.toString());
            JSONArray docs = obj.getJSONArray("docs");

            // Composite pattern: create a BookGroup to hold the results
            BookGroup results = new BookGroup("Results");

            for (int i = 0; i < Math.min(10, docs.length()); i++) {
                JSONObject d = docs.getJSONObject(i);
                String title = d.optString("title");
                String author = d.has("author_name") ? d.getJSONArray("author_name").optString(0) : "Unknown";
                String coverUrl = d.has("cover_i") ? "https://covers.openlibrary.org/b/id/" + d.getInt("cover_i") + "-M.jpg" : null;
                String publishDate = d.has("first_publish_year") ? String.valueOf(d.getInt("first_publish_year")) : "N/A";
                String key = d.optString("key");

                // Create a BookDto object using the factory method
                BookDto dto = BookDtoFactory.create(title, author, coverUrl, publishDate, key);
                results.add(new BookLeaf(dto));
            }

            // Add the results to the BookGroup
            resultList = results.toList();
            logger.info("🔎 Search completed: " + resultList.size() + " results found for '" + query + "'");

        } catch (Exception e) {
            logger.severe("❌ Error during search: " + e.getMessage());
        }

        return resultList;
    }
}
