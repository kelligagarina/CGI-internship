package com.example.CGI_Test.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:8081/")
public class KinosaalController {

    @GetMapping("/kinosaal")
    public int[][] looKinosaal() {
        int[][] kinosaal  = taidaSaal(7, 14);
        return kinosaal;
    }

    private int[][] taidaSaal(int read, int kohad) {
        Random random = new Random();
        int[][] saal = new int[read][kohad];
        for (int i = 0; i < read; i++) {
            for (int j = 0; j < kohad; j++) {
                saal[i][j] = random.nextInt(2); //Täidab isekohad suvaliselt kas 0 või 1-ga. 0-vaba, 1-kinni.
            }
        }
        return saal;
    }
}
