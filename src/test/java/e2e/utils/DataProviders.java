package e2e.utils;

import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviders {
    @DataProvider
    public Iterator<Object[]> newContact() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{"Sasha2", "Test2", "comment2"});
        list.add(new Object[]{"Sasha3", "Test3", "comment3"});
        list.add(new Object[]{"Sasha4", "Test4", "comment4"});

        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> newContactWithCSV() throws IOException {
        List<Object[]> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/data.csv")));
        String line = reader.readLine();
        while (line != null) {
            String[] split = line.split(",");
            list.add(new Object[]{split[0], split[1], split[2]});
            line = reader.readLine();
        }
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> editContactWithCSV() throws IOException {
        List<Object[]> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/DataForEditUserInfo - Лист1.csv")));
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> changeLastNameAndDescription() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{"Kelbas", "Best A"});
        list.add(new Object[]{"Sherif", "Best AAA"});
        list.add(new Object[]{"Golof", "Best B"});
        return list.iterator();
    }

}
