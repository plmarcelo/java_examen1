package com.privalia.service;

import com.privalia.model.SharePrice;
import lombok.extern.log4j.Log4j;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import static org.junit.Assert.*;

public class SharesReaderTest {


    static Properties prop = null;

    @Mock
    private BufferedReader bufferedReader;

    @BeforeClass
    public static void setUp() throws Exception {
        prop = new Properties();
        try {
            InputStream input = SharesReaderTest.class.getResourceAsStream("/config.test.properties");
            prop.load(input);
        } catch (IOException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    @Before
    public void setUpTest() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void allLinesAreRead() throws IOException {
        SharesReader sharesReader = new SharesReader();

        List<SharePrice> sharesList = sharesReader.read(prop.getProperty("shares.file"));

        int expectedShares = 3;
        assertEquals(expectedShares, sharesList.size());
    }

    @Test
    public void wrongLinesAreIgnored() throws IOException {
        SharesReader sharesReader = new SharesReader();

        List<SharePrice> sharesList = sharesReader.read(prop.getProperty("wrong.shares.file"));

        int expectedShares = 2;
        assertEquals(expectedShares, sharesList.size());
    }
}