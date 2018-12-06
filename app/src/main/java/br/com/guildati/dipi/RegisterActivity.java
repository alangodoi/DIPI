package br.com.guildati.dipi;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Spinner dia = findViewById(R.id.dia);
        Spinner mes = findViewById(R.id.mes);
        Spinner ano = findViewById(R.id.ano);

        //create a list of items for the spinner.
        String[] diaItems = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20"
                , "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
        String[] mesItems = new String[]{"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};
        String[] anoItems = new String[]{
                "2019", "2018", "2017", "2016", "2015", "2014", "2013", "2012", "2011", "2010",
                "2009", "2008", "2007", "2006", "2005", "2004", "2003", "2002", "2001", "2000",
                "1999", "1998", "1997", "1996", "1995", "1994", "1993", "1992", "1991", "1990",
                "1989", "1988", "1987", "1986", "1985", "1984", "1983", "1982", "1981", "1980",
                "1979", "1978", "1977", "1976", "1975", "1974", "1973", "1972", "1971", "1970",
                "1969", "1968", "1967", "1966", "1965", "1964", "1963", "1962" ,"1961" ,"1960",
                "1959", "1958", "1957", "1956", "1955", "1954", "1953", "1952", "1951", "1950",
                "1949", "1948", "1947", "1946", "1945", "1944", "1943", "1942", "1941", "1940",
                "1939", "1938", "1937", "1936", "1935", "1934", "1933", "1932", "1931", "1930",
                "1929", "1928", "1927", "1926", "1925", "1924", "1923", "1922", "1921", "1920"
        };
        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, diaItems);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, mesItems);
        ArrayAdapter<String> adapter3 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, anoItems);
        //set the spinners adapter to the previously created one.
        dia.setAdapter(adapter1);
        mes.setAdapter(adapter2);
        ano.setAdapter(adapter3);
    }
}
