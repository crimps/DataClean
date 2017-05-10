package com.crimps.poetry_dataClean;

import com.crimps.poetry_dataClean.AnalysisPoetry;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author crimps
 * @version 1.0  17/3/3
 * @modified crimps  17/3/3  <创建>
 */
public class AnalysisPoetryTest {
    private AnalysisPoetry analysisPoetry = new AnalysisPoetry();

    @Test
    public void saveSentence() throws Exception {
        analysisPoetry.saveSentence();
    }

}