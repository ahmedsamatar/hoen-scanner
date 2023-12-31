package com.skyscanner;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.ArrayList;
import java.util.List;




import io.dropwizard.core.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;

public class HoenScannerConfiguration extends Configuration {

    public class Search{

        @JsonProperty
        private String city;

        public Search() {

        }

        public Search(String city) { this.city = city; }

        public String getCity() { return city; }

    }

    public class SearchResult {

        @JsonProperty
        private String city;

        @JsonProperty
        private String title;

        @JsonProperty
        private String kind;


        public SearchResult(String city, String title, String kind) {
            this.city = city;
            this.title = title;
            this.kind = kind;
        }

        public String getCity() {
            return city;
        }

        public String getTitle() {
            return title;
        }

        public String getKind() {
            return kind;
        }

    }

    @Path("/search")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public class SearchResource {
        List<SearchResult> searchResults;
        public SearchResource(List<SearchResult> searchResults) { this.searchResults = searchResults; }

        @POST
        public List<SearchResult> search(@NotNull @Valid Search search) {
            List<SearchResult> response = new ArrayList<>();
            for (searchResult result : searchResults) {
                if (result.getCity().equals(search.getCity())) {
                    response.add(result);
                }
            }
            return response;
        }

    }
}
