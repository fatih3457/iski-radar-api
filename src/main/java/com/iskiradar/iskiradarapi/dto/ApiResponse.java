package com.iskiradar.iskiradarapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

// "<T>" bu sınıfın jenerik olduğunu belirtir. T, herhangi bir tip olabilir.
public class ApiResponse<T> {

    @JsonProperty("data")
    private List<T> data; // Artık List<DamData> değil, List<T>

    // --- Getter ve Setter da jenerik hale geldi ---
    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}